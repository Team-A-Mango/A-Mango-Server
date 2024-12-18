package com.mango.amango.domain.product.service;

import com.mango.amango.domain.product.presentation.dto.request.OrderProductReq;

public interface BuyProductService {
    void execute(Long productId, OrderProductReq request);
}
