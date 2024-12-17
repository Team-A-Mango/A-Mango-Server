package com.mango.amango.domain.auth.service.impl;

import com.mango.amango.domain.auth.presentation.dto.response.TokenRes;
import com.mango.amango.domain.auth.service.ReissueTokenService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.security.jwt.JwtRule;
import com.mango.amango.global.security.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public TokenRes reissueToken(HttpServletRequest request) {
        String refreshToken = jwtService.resolveTokenFromHeader(request, JwtRule.REFRESH_PREFIX);
        User user = findUserByRefreshToken(refreshToken);

        if (!jwtService.validateRefreshToken(refreshToken, user.getNickname())) {
            throw new CustomException(CustomErrorCode.REFRESH_TOKEN_NOT_FOUND);
        }

            jwtService.logout(user);
            return jwtService.generateTokenResponse(user);
    }

    private User findUserByRefreshToken(String refreshToken) {
        String username = jwtService.getUsernameFromRefresh(refreshToken);
        return userService.findUserByUsername(username);
    }
}
