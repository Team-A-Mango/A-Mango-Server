package com.mango.amango.domain.product.controller;

import com.mango.amango.domain.product.entity.dto.request.CreateProductReq;
import com.mango.amango.domain.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    public final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(
            @Valid @RequestPart("request") CreateProductReq request,
            @Size(max = 3, message = "이미지는 최대 3장까지 입니다.") @RequestPart("images") List<MultipartFile> images
    ) {
        productService.createProduct(request, images);
        return ResponseEntity.ok().build();
    }

}
