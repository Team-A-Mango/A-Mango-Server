package com.mango.amango.domain.auth.entity.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TokenRes {

    private final String accessToken;
    private final String refreshToken;
    private final LocalDateTime accessTokenExpiresIn;
    private final LocalDateTime refreshTokenExpiresIn;
}
