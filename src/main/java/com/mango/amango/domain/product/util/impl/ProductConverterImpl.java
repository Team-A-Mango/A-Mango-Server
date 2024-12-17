package com.mango.amango.domain.product.util.impl;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.product.util.ProductConverter;
import org.springframework.stereotype.Component;

@Component
public class ProductConverterImpl implements ProductConverter {

    public GetProductRes toDto(Product product) {
        return GetProductRes.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .author(product.getUser().getNickname())
                .profileImg(product.getUser().getProfile())
                .build();
    }
}
