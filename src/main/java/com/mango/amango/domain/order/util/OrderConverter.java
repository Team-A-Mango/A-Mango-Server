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

    public static PostOrderIdentityReq toDto(Order order) {
        return PostOrderIdentityReq.builder()
                .imageUrl(order.getUser().getFaceImageUrl())
                .handSign(order.getHandSign().getValue())
                .storageRoomNumber(String.valueOf(order.getStorageNumber()))
                .build();
    }
}
