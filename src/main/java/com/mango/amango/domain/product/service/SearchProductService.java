package com.mango.amango.domain.product.service;

import com.mango.amango.domain.product.presentation.dto.response.SearchProductRes;

import java.util.List;

public interface SearchProductService {
    List<SearchProductRes> execute(String keyword);
}
