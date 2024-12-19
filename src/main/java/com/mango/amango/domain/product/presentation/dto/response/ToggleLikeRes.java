package com.mango.amango.domain.product.presentation.dto.response;

import lombok.Builder;

@Builder
public record ToggleLikeRes(
        Integer likes,
        boolean check
) {
}
