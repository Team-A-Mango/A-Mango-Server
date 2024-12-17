package com.mango.amango.domain.product.util;

import com.mango.amango.domain.image.entity.Image;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.presentation.dto.response.FindAllProductRes;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;

import java.util.List;

public abstract class ProductConverter {

    public static GetProductRes toDto(Product product) {
        return GetProductRes.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .author(product.getUser().getNickname())
                .profileImg(product.getUser().getProfile())
                .build();
    }

    public static FindAllProductRes toDto(Product product, List<Image> images) {
        String imageUrl = images.stream()
                .filter(image -> product.equals(image.getProduct()))
                .findFirst()
                .map((Image::getImageUrl))
                .orElse(null);

        return FindAllProductRes.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .imageUrl(imageUrl)
                .price(product.getPrice())
                .like(product.getLikes())
                .build();
    }
}
