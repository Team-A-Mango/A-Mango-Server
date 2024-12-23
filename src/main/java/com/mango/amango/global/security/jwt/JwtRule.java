package com.mango.amango.global.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtRule {
    ACCESS_PREFIX("Authorization"),
    REFRESH_PREFIX("Refresh-Token");

    private final String value;
}
