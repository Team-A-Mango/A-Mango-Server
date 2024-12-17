package com.mango.amango.domain.product.util;

import com.mango.amango.domain.image.entity.Image;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.presentation.dto.response.FindAllProductRes;
import com.mango.amango.domain.tag.entity.Category;
import com.mango.amango.domain.tag.entity.Tag;

import java.util.List;

public abstract class ProductConverter {

    public static FindAllProductRes toDto(Product product, List<Image> images, List<Tag> tags) {
        String imageUrl = images.stream()
                .filter(image -> product.equals(image.getProduct()))
                .findFirst()
                .map((Image::getImageUrl))
                .orElse(null);

        List<Category> categories = tags.stream()
                .filter(tag -> product.equals(tag.getProduct()))
                .map(Tag::getCategory)
                .toList();

        return FindAllProductRes.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .imageUrl(imageUrl)
                .price(product.getPrice())
                .tag(categories)
                .like(product.getLikes())
                .build();
    }
}
