package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.image.service.ImageUploadService;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.presentation.dto.request.CreateProductReq;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.CreateProductService;
import com.mango.amango.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateProductServiceImpl implements CreateProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ImageUploadService imageUploadService;

    @Override
    public void createProduct(CreateProductReq request, List<MultipartFile> images) {

        Product product = Product.builder()
                .title(request.title())
                .description(request.description())
                .price(request.price())
                .user(userService.getCurrentUser())
                .build();

        productRepository.save(product);
        imageUploadService.saveImage(product, images);
    }
}
