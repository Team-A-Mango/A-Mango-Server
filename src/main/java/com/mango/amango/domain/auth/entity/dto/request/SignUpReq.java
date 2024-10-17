package com.mango.amango.domain.auth.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpReq {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gsm.hs.kr$", message = "gsm.hs.kr 도메인을 사용하는 메일이 아닙니다.")
    private final String email;
    @NotBlank
    private final String nickName;
    @NotBlank
    private final String password;
}
