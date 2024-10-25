package com.mango.amango.domain.auth.service.impl;

import com.mango.amango.domain.auth.entity.dto.request.LoginReq;
import com.mango.amango.domain.auth.entity.dto.response.TokenRes;
import com.mango.amango.domain.auth.service.SignInService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.security.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenRes signIn(LoginReq request, HttpServletResponse response) {
        User reqeustUser = userService.findUserByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), reqeustUser.getPassword())) {
            throw new CustomException(CustomErrorCode.INVALID_PASSWORD);
        }

        return jwtService.generateTokenResponse(reqeustUser);
    }
}
