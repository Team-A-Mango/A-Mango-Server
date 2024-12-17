package com.mango.amango.domain.product.entity.dto.request;

import com.mango.amango.domain.tag.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public record CreateProductReq(
        @NotBlank(message = "제목은 비어 있을 수 없습니다.")
        String title,

        @NotNull(message = "설명은 필수 입력 값 입니다.")
        String description,

        @NotNull(message = "가격은 필수 입력 값 입니다.")
        Long price,

        @NotNull(message = "태그는 필수 입력 값 입니다.")
        @Size(min = 1, message = "태그는 최소 1개 이상 포함되어야 합니다.")
        List<Category> tags,

        @NotNull(message = "시간은 필수 입력 값 입니다.")
        LocalDateTime expirTime
) {

}
