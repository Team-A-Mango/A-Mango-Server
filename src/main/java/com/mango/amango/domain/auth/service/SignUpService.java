package com.mango.amango.domain.auth.service;

import com.mango.amango.domain.auth.presentation.dto.request.SignUpReq;

public interface SignUpService {

    void signUp(SignUpReq request);
}
