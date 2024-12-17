package com.mango.amango.global.security.jwt.service;

import com.mango.amango.domain.auth.entity.RefreshToken;
import com.mango.amango.domain.auth.presentation.dto.response.TokenRes;
import com.mango.amango.domain.auth.repository.RefreshTokenRepository;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.security.CustomUserDetailService;
import com.mango.amango.global.security.jwt.JwtRule;
import com.mango.amango.global.security.jwt.TokenStatus;
import com.mango.amango.global.security.jwt.properties.JwtProperties;
import com.mango.amango.global.security.jwt.util.JwtGenerator;
import com.mango.amango.global.security.jwt.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;

import static java.time.LocalDateTime.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JwtService {

    private final CustomUserDetailService customUserDetailService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    public String generateAccessToken(User requestUser) {
        return JwtGenerator.generateAccessToken(
                jwtProperties.getAccessSecret(),
                jwtProperties.getAccessExpiration(),
                requestUser);
    }

    @Transactional
    public String generateRefreshToken(User requestUser) {
        String refreshToken = JwtGenerator.generateRefreshToken(
                jwtProperties.getRefreshSecret(),
                jwtProperties.getRefreshExpiration(),
                requestUser);

        refreshTokenRepository.save(new RefreshToken(requestUser.getNickname(), refreshToken));
        return refreshToken;
    }

    public TokenRes generateTokenResponse(User requestUser) {
        return TokenRes.builder()
                .accessToken(generateAccessToken(requestUser))
                .refreshToken(generateRefreshToken(requestUser))
                .accessTokenExpiresIn(now().plusSeconds(jwtProperties.getAccessExpiration() / 1000))
                .refreshTokenExpiresIn(now().plusSeconds(jwtProperties.getRefreshExpiration() / 1000))
                .build();
    }

    public String resolveTokenFromHeader(HttpServletRequest request, JwtRule headerPrefix) {
        return request.getHeader(headerPrefix.getValue());
    }

    public boolean validateAccessToken(String token) {
        return JwtUtil.getTokenStatus(token, jwtProperties.getAccessSecret()) == TokenStatus.AUTHENTICATED;
    }

    public boolean validateRefreshToken(String token, String username) {
        boolean isRefreshValid = JwtUtil.getTokenStatus(
                token, jwtProperties.getRefreshSecret()) == TokenStatus.AUTHENTICATED;

        RefreshToken savedRefreshToken = refreshTokenRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(CustomErrorCode.REFRESH_TOKEN_NOT_FOUND));

        boolean isRefreshTokenMatch = savedRefreshToken.getToken().equals(token);

        return isRefreshValid && isRefreshTokenMatch;
    }

    public Authentication getAuthentication(String token) {
        UserDetails principal = customUserDetailService.loadUserByUsername(
                getUserId(token, jwtProperties.getAccessSecret()));

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    private String getUserId(String token, Key secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void logout(User requestUser) {
        RefreshToken refreshToken = refreshTokenRepository.findByUsername(requestUser.getNickname())
                .orElseThrow(() -> new CustomException(CustomErrorCode.REFRESH_TOKEN_NOT_FOUND));

        refreshTokenRepository.delete(refreshToken);
    }

    public String getUsernameFromRefresh(String refreshToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getRefreshSecret())
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new CustomException(CustomErrorCode.MALFORMED_TOKEN);
        }
    }

}
