package com.mango.amango.domain.auth.service.impl;

import com.mango.amango.domain.auth.entity.dto.request.SignUpReq;
import com.mango.amango.domain.auth.service.SignUpService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

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
}
