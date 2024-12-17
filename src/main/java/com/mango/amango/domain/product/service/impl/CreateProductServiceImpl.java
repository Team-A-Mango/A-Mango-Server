package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.presentation.dto.request.CreateProductReq;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.CreateProductService;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.file.s3.S3ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static software.amazon.awssdk.services.s3.model.ObjectCannedACL.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateProductServiceImpl implements CreateProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final S3ClientService s3ClientService;

    @Override
    public void createProduct(CreateProductReq request, MultipartFile image) {
        productRepository.save(Product.builder()
                .title(request.title())
                .description(request.description())
                .price(request.price())
                .imageUrl(s3ClientService.upload(image, PUBLIC_READ).orElse(null))
                .user(userService.getCurrentUser())
                .build());
    }
}
