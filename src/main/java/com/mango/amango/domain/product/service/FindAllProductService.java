package com.mango.amango.domain.product.service;

import com.mango.amango.domain.product.presentation.dto.response.FindAllProductRes;

import java.util.List;

public interface FindAllProductService {
    List<FindAllProductRes> execute();
}
