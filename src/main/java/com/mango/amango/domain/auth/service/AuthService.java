package com.mango.amango.domain.auth.service;

import com.mango.amango.domain.auth.entity.dto.request.LoginReq;
import com.mango.amango.domain.auth.entity.dto.request.SignUpReq;
import com.mango.amango.domain.auth.entity.dto.response.TokenRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    TokenRes login(LoginReq request, HttpServletResponse response);

    void signUp(SignUpReq request);

    void logout(HttpServletRequest request);

    TokenRes reissueToken(HttpServletRequest request);
}
