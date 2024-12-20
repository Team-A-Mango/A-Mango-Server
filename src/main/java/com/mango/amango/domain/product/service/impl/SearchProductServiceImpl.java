package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.product.presentation.dto.response.SearchProductRes;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.SearchProductService;
import com.mango.amango.domain.product.util.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchProductServiceImpl implements SearchProductService {

    private final ProductRepository productRepository;

    public List<SearchProductRes> execute(String keyword) {
        return productRepository.findByTitleContaining(keyword)
                .stream()
                .map(ProductConverter::toSearchProductRes)
                .toList();
    }
}
