package com.mango.amango.domain.product.presentation;

import com.mango.amango.domain.product.presentation.dto.request.CreateProductReq;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.product.service.CreateProductService;
import com.mango.amango.domain.product.service.GetProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    public final CreateProductService createProductService;
    public final GetProductService getProductService;

    @PostMapping
    public ResponseEntity<Void> createProduct(
            @Valid @RequestPart("request") CreateProductReq request,
            @Size(max = 3, message = "이미지는 최대 3장까지 입니다.") @RequestPart("images") List<MultipartFile> images
    ) {
        createProductService.createProduct(request, images);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductRes> getProduct(@PathVariable Long productId) {
        GetProductRes res = getProductService.execute(productId);
        return ResponseEntity.ok(res);
    }
}