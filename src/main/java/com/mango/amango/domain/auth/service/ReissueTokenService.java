package com.mango.amango.domain.auth.service;

import com.mango.amango.domain.auth.presentation.dto.response.TokenRes;
import jakarta.servlet.http.HttpServletRequest;

public interface ReissueTokenService {

    TokenRes reissueToken(HttpServletRequest request);
}
