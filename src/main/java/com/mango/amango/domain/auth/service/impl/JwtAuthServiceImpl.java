package com.mango.amango.domain.auth.service.impl;

import com.mango.amango.domain.auth.entity.dto.request.LoginReq;
import com.mango.amango.domain.auth.entity.dto.request.SignUpReq;
import com.mango.amango.domain.auth.entity.dto.response.TokenRes;
import com.mango.amango.domain.auth.service.AuthService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.jwt.JwtRule;
import com.mango.amango.global.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JwtAuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenRes signIn(LoginReq request, HttpServletResponse response) {
        User reqeustUser = userService.findUserByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), reqeustUser.getPassword())) {
            throw new CustomException(CustomErrorCode.INVALID_PASSWORD);
        }

        return jwtService.generateTokenResponse(reqeustUser);
    }

    @Override
    public void signUp(SignUpReq request) {

        //TODO 이메일 인증

        userService.saveUser(User.builder()
                .email(request.getEmail())
                .nickname(request.getNickName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build()
        );
    }

    @Override
    public void logout(HttpServletRequest request) {
        User currentUser = userService.getCurrentUser();
        jwtService.logout(currentUser);
    }

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
