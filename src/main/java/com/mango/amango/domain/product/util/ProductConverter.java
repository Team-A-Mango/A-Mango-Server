package com.mango.amango.domain.product.util;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;

public interface ProductConverter {
    GetProductRes toDto (Product product);
}
