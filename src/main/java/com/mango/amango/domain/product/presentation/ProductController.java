package com.mango.amango.domain.product.presentation;

import com.mango.amango.domain.product.presentation.dto.request.CreateProductReq;
import com.mango.amango.domain.product.presentation.dto.request.OrderProductReq;
import com.mango.amango.domain.product.presentation.dto.request.StockProductReq;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.product.presentation.dto.response.FindAllProductRes;
import com.mango.amango.domain.product.presentation.dto.response.SearchProductRes;
import com.mango.amango.domain.product.presentation.dto.response.ToggleLikeRes;
import com.mango.amango.domain.product.service.*;
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
    private final DeleteProductService deleteProductService;
    private final BuyProductService buyProductService;
    private final StockProductService stockProductService;
    private final ProductCompletedService productCompletedService;
    private final ToggleProductLikeService toggleProductLikeService;
    private final SearchProductService searchProductService;

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

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        deleteProductService.execute(productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productId}/buy")
    public ResponseEntity<Void> orderProduct(@PathVariable Long productId,
                                             @RequestBody OrderProductReq request) {
        buyProductService.execute(productId, request);
        return ResponseEntity.status(CREATED).build();
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Void> stockProduct(@PathVariable Long productId, @RequestBody StockProductReq request) {
        stockProductService.execute(productId, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productId}/complete")
    public ResponseEntity<Void> completedProduct(@PathVariable Long productId) {
        productCompletedService.execute(productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productId}/like")
    public ResponseEntity<ToggleLikeRes> toggleProductLike(@PathVariable Long productId) {
        ToggleLikeRes res = toggleProductLikeService.execute(productId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchProductRes>> searchProducts(@RequestParam String keyword) {
        List<SearchProductRes> res = searchProductService.execute(keyword);
        return ResponseEntity.ok(res);
    }
}
