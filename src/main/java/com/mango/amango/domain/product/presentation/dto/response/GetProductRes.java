package com.mango.amango.domain.product.presentation.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GetProductRes (
        Long productId,
        String title,
        String description,
        Long price,
        String author,
        List<String> images,
        String profileImg
) {
}