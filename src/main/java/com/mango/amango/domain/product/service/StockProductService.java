package com.mango.amango.domain.product.service;

import com.mango.amango.domain.product.presentation.dto.request.StockProductReq;

public interface StockProductService {
    void execute(Long productId, StockProductReq request);
}
