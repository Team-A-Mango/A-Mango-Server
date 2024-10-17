package com.mango.amango.global.jwt.service;

import com.mango.amango.domain.auth.entity.RefreshToken;
import com.mango.amango.domain.auth.entity.dto.response.TokenRes;
import com.mango.amango.domain.auth.repository.RefreshTokenRepository;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.jwt.JwtRule;
import com.mango.amango.global.jwt.TokenStatus;
import com.mango.amango.global.jwt.util.JwtGenerator;
import com.mango.amango.global.jwt.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;

import static java.time.LocalDateTime.*;

@Service
@Transactional(readOnly = true)
public class JwtService {

    private final CustomUserDetailService customUserDetailService;
    private final RefreshTokenRepository refreshTokenRepository;

    private final Key ACCESS_SECRET_KEY;
    private final Key REFRESH_SECRET_KEY;
    private final long ACCESS_EXPIRATION;
    private final long REFRESH_EXPIRATION;

    public JwtService(CustomUserDetailService customUserDetailService, RefreshTokenRepository refreshTokenRepository,
                      @Value("${jwt.access-secret}") String ACCESS_SECRET_KEY,
                      @Value("${jwt.refresh-secret}") String  REFRESH_SECRET_KEY,
                      @Value("${jwt.access-expiration}") long ACCESS_EXPIRATION,
                      @Value("${jwt.refresh-expiration}") long REFRESH_EXPIRATION) {
        this.customUserDetailService = customUserDetailService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.ACCESS_SECRET_KEY = JwtUtil.getSigningKey(ACCESS_SECRET_KEY);
        this.REFRESH_SECRET_KEY = JwtUtil.getSigningKey(REFRESH_SECRET_KEY);
        this.ACCESS_EXPIRATION = ACCESS_EXPIRATION;
        this.REFRESH_EXPIRATION = REFRESH_EXPIRATION;
    }

    public String generateAccessToken(User requestUser) {
        return JwtGenerator.generateAccessToken(ACCESS_SECRET_KEY, ACCESS_EXPIRATION, requestUser);
    }

    @Transactional
    public String generateRefreshToken(User requestUser) {
        String refreshToken = JwtGenerator.generateRefreshToken(REFRESH_SECRET_KEY, REFRESH_EXPIRATION, requestUser);

        refreshTokenRepository.save(new RefreshToken(requestUser.getNickname(), refreshToken));
        return refreshToken;
    }

    public TokenRes generateTokenResponse(User requestUser) {
        return TokenRes.builder()
                .accessToken(generateAccessToken(requestUser))
                .refreshToken(generateRefreshToken(requestUser))
                .accessTokenExpiresIn(now().plusSeconds(ACCESS_EXPIRATION / 1000))
                .refreshTokenExpiresIn(now().plusSeconds(REFRESH_EXPIRATION / 1000))
                .build();
    }

    public String resolveTokenFromHeader(HttpServletRequest request, JwtRule headerPrefix) {
        return request.getHeader(headerPrefix.getValue());
    }

    public boolean validateAccessToken(String token) {
        return JwtUtil.getTokenStatus(token, ACCESS_SECRET_KEY) == TokenStatus.AUTHENTICATED;
    }

    public boolean validateRefreshToken(String token, String username) {
        boolean isRefreshValid = JwtUtil.getTokenStatus(token, REFRESH_SECRET_KEY) == TokenStatus.AUTHENTICATED;

        RefreshToken savedRefreshToken = refreshTokenRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(CustomErrorCode.REFRESH_TOKEN_NOT_FOUND));
        boolean isRefreshTokenMatch = savedRefreshToken.getToken().equals(token);

        return isRefreshValid && isRefreshTokenMatch;
    }

    public Authentication getAuthentication(String token) {
        UserDetails principal = customUserDetailService.loadUserByUsername(getUserId(token, ACCESS_SECRET_KEY));
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
                    .setSigningKey(REFRESH_SECRET_KEY)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new CustomException(CustomErrorCode.MALFORMED_TOKEN);
        }
    }

}
