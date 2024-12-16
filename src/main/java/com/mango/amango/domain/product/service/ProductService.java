package com.mango.amango.domain.product.service;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.entity.dto.request.CreateProductReq;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.tag.service.TagService;
import com.mango.amango.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final TagService tagService;


    public void createProduct(CreateProductReq request, List<MultipartFile> images) {

        Product product = Product.builder()
                .title(request.title())
                .description(request.description())
                .price(request.price())
                .user(userService.getCurrentUser())
                .expirTime(request.expirTime())
                .auctionPrice(request.auctionPrice())
                .build();

        productRepository.save(product);
        tagService.saveTag(product, request.tags());
    }
}
