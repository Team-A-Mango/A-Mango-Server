package com.mango.amango.domain.product.service;

import com.mango.amango.domain.product.presentation.dto.response.ToggleLikeRes;

public interface ToggleProductLikeService {
    ToggleLikeRes execute(Long productId);
}
