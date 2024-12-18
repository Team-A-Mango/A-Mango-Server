package com.mango.amango.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignUpReq(

        @NotBlank(message = "이메일은 필수 값 입니다")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gsm.hs.kr$", message = "gsm.hs.kr 도메인을 사용하는 메일이 아닙니다.")
        String email,

        @NotBlank(message = "전화번호는 필수 값 입니다")
        @Pattern(regexp = "^01[016789]-\\d{3,4}-\\d{4}$", message = "전화번호가 01X-XXXX-XXXX 형식이 아닙니다.")
        String phone,

        @NotBlank(message = "닉네임은 필수 값 입니다")
        String nickName,

        @NotBlank(message = "비밀번호는 필수 값 입니다")
        String password) {
}
