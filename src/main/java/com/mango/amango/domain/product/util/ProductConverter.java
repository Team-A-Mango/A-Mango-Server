package com.mango.amango.domain.product.util;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.presentation.dto.response.FindAllProductRes;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.product.presentation.dto.response.SearchProductRes;

import java.util.List;

public abstract class ProductConverter {

    public static GetProductRes toGetProductRes(Product product, List<GetProductRes.GetInquiry> inquiries, boolean check) {
        return GetProductRes.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .author(product.getUser().getNickname())
                .profileImg(product.getUser().getProfile())
                .imageUrl(product.getImageUrl())
                .isSold(product.getIsSold())
                .likes(product.getLikes())
                .check(check)
                .inquiries(inquiries)
                .build();
    }

    public static FindAllProductRes toFindAllProductRes(Product product) {
        return FindAllProductRes.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .like(product.getLikes())
                .isSold(product.getIsSold())
                .build();
    }

    public static SearchProductRes toSearchProductRes(Product product) {
        return SearchProductRes.builder()
                .title(product.getTitle())
                .productId(product.getId())
                .imageUrl(product.getImageUrl())
                .isSold(product.getIsSold())
                .price(product.getPrice())
                .like(product.getLikes())
                .build();
    }
}
