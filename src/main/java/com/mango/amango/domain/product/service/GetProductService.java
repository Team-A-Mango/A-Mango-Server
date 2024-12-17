package com.mango.amango.domain.product.service;

import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;

public interface GetProductService {
    GetProductRes execute(Long productId);
}
