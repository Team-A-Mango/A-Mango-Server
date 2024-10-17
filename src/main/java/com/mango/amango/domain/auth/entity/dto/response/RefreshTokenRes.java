package com.mango.amango.domain.auth.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RefreshTokenRes {
    private String refreshToken;
    private LocalDateTime refreshTokenExpiresIn;
}
