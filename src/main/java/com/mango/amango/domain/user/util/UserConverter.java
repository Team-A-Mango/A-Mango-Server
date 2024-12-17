package com.mango.amango.domain.user.util;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.user.presentation.dto.response.GetMyOrdersRes;

import java.util.List;

public abstract class UserConverter {

    public static GetMyOrdersRes.MyOrders toDto(Product product) {
        return GetMyOrdersRes.MyOrders.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .like(product.getLikes())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public static GetMyOrdersRes toDtoRes(List<GetMyOrdersRes.MyOrders> sale, List<GetMyOrdersRes.MyOrders> purchase){
        return GetMyOrdersRes.builder()
                .sale(sale)
                .purchase(purchase)
                .build();
    }
}
