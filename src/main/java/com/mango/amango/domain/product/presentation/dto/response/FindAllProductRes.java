package com.mango.amango.domain.product.presentation.dto.response;

import lombok.Builder;

@Builder
public record FindAllProductRes(

    Long productId,

    String title,

    String imageUrl,

    Long price,

    Integer like,

    Boolean isSold
) {
}
