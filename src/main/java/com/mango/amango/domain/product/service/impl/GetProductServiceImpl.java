package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.GetProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mango.amango.domain.product.util.ProductConverter.*;

@Service
@Transactional
@RequiredArgsConstructor
public class GetProductServiceImpl implements GetProductService {

    private final ProductRepository productRepository;

    public GetProductRes execute(Long productId) {
        return toGetProductRes(productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new));
    }
}
