package com.mango.amango.domain.auth.service.impl;

import com.mango.amango.domain.auth.service.LogoutService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.security.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public void logout(HttpServletRequest request) {
        User currentUser = userService.getCurrentUser();
        jwtService.logout(currentUser);
    }
}
