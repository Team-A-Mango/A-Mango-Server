package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.product.presentation.dto.response.FindAllProductRes;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.FindAllProductService;
import com.mango.amango.domain.product.util.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindAllProductServiceImpl implements FindAllProductService {

    private final ProductRepository productRepository;

    @Override
    public List<FindAllProductRes> execute() {
        return productRepository.findByIsSoldFalse().stream()
                .map(ProductConverter::toFindAllProductRes)
                .toList();
    }
}
