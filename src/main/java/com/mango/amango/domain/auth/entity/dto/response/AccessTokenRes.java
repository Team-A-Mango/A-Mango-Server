package com.mango.amango.domain.auth.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AccessTokenRes {
    private String accessToken;
    private LocalDateTime accessTokenExpiresIn;
}
