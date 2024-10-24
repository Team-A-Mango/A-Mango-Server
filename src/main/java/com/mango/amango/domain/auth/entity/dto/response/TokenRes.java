package com.mango.amango.domain.auth.entity.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TokenRes(
        String accessToken,
        String refreshToken,
        LocalDateTime accessTokenExpiresIn,
        LocalDateTime refreshTokenExpiresIn
) {

}
