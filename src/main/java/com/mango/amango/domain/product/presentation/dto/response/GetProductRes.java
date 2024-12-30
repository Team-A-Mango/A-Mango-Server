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
        String imageUrl,
        String profileImg,
        Boolean isSold,
        Integer likes,
        String account,
        boolean check,
        List<GetInquiry> inquiries
) {
    @Builder
    public record GetInquiry(
            String content,
            String author,
            String profileImag
    ) {
    }
}