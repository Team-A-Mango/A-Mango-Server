package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.inquiry.repository.InquiryRepository;
import com.mango.amango.domain.inquiry.util.InquiryConverter;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.GetProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mango.amango.domain.product.util.ProductConverter.*;

@Service
@Transactional
@RequiredArgsConstructor
public class GetProductServiceImpl implements GetProductService {

    private final ProductRepository productRepository;
    private final InquiryRepository inquiryRepository;

    public GetProductRes execute(Long productId) {
        List<GetProductRes.GetInquiry> inquiries = inquiryRepository.findAllByProductId(productId)
                .stream()
                .map(InquiryConverter::toDto)
                .toList();

        return toGetProductRes(productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new), inquiries);
    }
}
