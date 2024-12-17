package com.mango.amango.domain.product.presentation.dto.response;

import lombok.Builder;

@Builder
public record GetProductRes (
        Long productId,
        String title,
        String description,
        Long price,
        String author,
        String imageUrl,
        String profileImg
) {
}