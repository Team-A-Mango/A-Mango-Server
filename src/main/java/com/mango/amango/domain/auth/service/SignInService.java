package com.mango.amango.domain.auth.service;

import com.mango.amango.domain.auth.presentation.dto.request.LoginReq;
import com.mango.amango.domain.auth.presentation.dto.response.TokenRes;
import jakarta.servlet.http.HttpServletResponse;

public interface SignInService {

    TokenRes signIn(LoginReq request, HttpServletResponse response);
}
