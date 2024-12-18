package com.mango.amango.domain.product.presentation;

import com.mango.amango.domain.product.presentation.dto.request.CreateProductReq;
import com.mango.amango.domain.product.presentation.dto.request.OrderProductReq;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.product.presentation.dto.response.FindAllProductRes;
import com.mango.amango.domain.product.service.BuyProductService;
import com.mango.amango.domain.product.service.CreateProductService;
import com.mango.amango.domain.product.service.GetProductService;
import com.mango.amango.domain.product.service.FindAllProductService;
import jakarta.validation.Valid;
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

    private final CreateProductService createProductService;
    private final GetProductService getProductService;
    private final FindAllProductService findAllProductService;
    private final BuyProductService buyProductService;

    @PostMapping
    public ResponseEntity<Void> createProduct(
            @Valid @RequestPart("request") CreateProductReq request,
            @RequestPart("images") MultipartFile image
    ) {
        createProductService.createProduct(request, image);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductRes> getProduct(@PathVariable Long productId) {
        GetProductRes res = getProductService.execute(productId);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<List<FindAllProductRes>> findAllProducts() {
        List<FindAllProductRes> res = findAllProductService.execute();
        return ResponseEntity.ok(res);

    }

    @PostMapping("/{productId}/buy")
    public ResponseEntity<Void> orderProduct(@PathVariable Long productId,
                                             @RequestBody OrderProductReq request) {
        buyProductService.execute(productId, request);
        return ResponseEntity.status(CREATED).build();
    }
}
