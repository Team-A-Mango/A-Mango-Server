package com.mango.amango.domain.auth.service;

import com.mango.amango.domain.auth.presentation.dto.request.SignUpReq;
import org.springframework.web.multipart.MultipartFile;

public interface SignUpService {

    void signUp(SignUpReq request, MultipartFile image);
}
