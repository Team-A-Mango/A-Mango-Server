package com.mango.amango.domain.product.presentation.dto.response;

import com.mango.amango.domain.tag.entity.Category;
import lombok.Builder;

import java.util.List;

@Builder
public record FindAllProductRes(

    Long productId,

    String title,

    String imageUrl,

    Long price,

    List<Category> tag,

    Integer like
) {
}
