package com.mango.amango.domain.product.entity.dto.request;

import com.mango.amango.domain.tag.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateProductReq {

    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    private final String title;

    private final String description;

    private final Long price;

    @Size(min = 1, message = "태그는 최소 1개 이상 포함되어야 합니다.")
    private final List<Category> tags;

    private final Long auctionPrice;

    @NotNull(message = "시간은 필수 입력 값 입니다.")
    private final LocalDateTime expirTime;
}
