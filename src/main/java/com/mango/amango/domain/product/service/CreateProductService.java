package com.mango.amango.domain.product.service;

import com.mango.amango.domain.product.entity.dto.request.CreateProductReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CreateProductService {
    void createProduct(CreateProductReq request, List<MultipartFile> images);
}
