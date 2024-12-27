package com.mango.amango.domain.order.util;

import com.mango.amango.domain.order.client.dto.request.PostOrderIdentityReq;
import com.mango.amango.domain.order.entity.HandSign;
import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.user.entity.User;

public abstract class OrderConverter {

    public static Order toEntity (Product product, User user, HandSign handSign) {
        return Order.builder()
                .product(product)
                .user(user)
                .handSign(handSign)
                .build();
    }

    public static PostOrderIdentityReq toDto(User user, Order order) {
        return PostOrderIdentityReq.builder()
                .image_url(user.getFaceImageUrl())
                .hand_sign(order.getHandSign().getValue())
                .storage_number(order.getStorageNumber())
                .build();
    }
}
