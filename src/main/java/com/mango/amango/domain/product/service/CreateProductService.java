package com.mango.amango.domain.product.service;

import com.mango.amango.domain.product.presentation.dto.request.CreateProductReq;
import org.springframework.web.multipart.MultipartFile;

public interface CreateProductService {
    void createProduct(CreateProductReq request, MultipartFile image);
}
