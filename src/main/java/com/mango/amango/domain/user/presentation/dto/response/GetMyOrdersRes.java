package com.mango.amango.domain.user.presentation.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GetMyOrdersRes(
        List<MyOrders> sale,
        List<MyOrders> purchase
) {
    @Builder
    public record MyOrders(
            Long productId,
            String title,
            String imageUrl,
            Long price,
            Integer like
    ) {}
}
