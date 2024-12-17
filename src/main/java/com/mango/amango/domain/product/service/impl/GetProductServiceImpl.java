package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.GetProductService;
import com.mango.amango.domain.product.util.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GetProductServiceImpl implements GetProductService {

    private final ProductRepository productRepository;

    public GetProductRes execute(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        return ProductConverter.toDto(product);
    }
}
