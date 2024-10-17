package com.mango.amango.domain.auth.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginReq {
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
}
