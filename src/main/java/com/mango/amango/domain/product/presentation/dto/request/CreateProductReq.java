package com.mango.amango.domain.product.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductReq(
        @NotBlank(message = "제목은 비어 있을 수 없습니다.")
        String title,

        @NotNull(message = "설명은 필수 입력 값 입니다.")
        String description,

        @NotNull(message = "가격은 필수 입력 값 입니다.")
        Long price,

        @NotNull(message = "계좌는 필수 입력 값 입니다.")
        String account
) {

}
