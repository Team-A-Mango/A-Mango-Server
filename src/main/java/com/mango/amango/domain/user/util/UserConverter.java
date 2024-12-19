package com.mango.amango.domain.user.util;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.presentation.dto.response.GetMyInfoRes;
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

    public static GetMyOrdersRes toDtoRes(List<GetMyOrdersRes.MyOrders> sale, List<GetMyOrdersRes.MyOrders> purchase, List<GetMyOrdersRes.MyLikes> likes){
        return GetMyOrdersRes.builder()
                .sale(sale)
                .purchase(purchase)
                .likes(likes)
                .build();
    }

    public static GetMyOrdersRes.MyLikes toLikeDto(Product product) {
        return GetMyOrdersRes.MyLikes.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .like(product.getLikes())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public static GetMyInfoRes toUserDto(User user) {
        return GetMyInfoRes.builder()
                .email(user.getEmail())
                .phone(user.getPhoneNumber())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .build();
    }
}
