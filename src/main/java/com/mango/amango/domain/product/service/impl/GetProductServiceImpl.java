package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.inquiry.repository.InquiryRepository;
import com.mango.amango.domain.inquiry.util.InquiryConverter;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.product.repository.ProductLikeRepository;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.GetProductService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomException;
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
    private final ProductLikeRepository productLikeRepository;
    private final UserService userService;

    public GetProductRes execute(Long productId) {
        List<GetProductRes.GetInquiry> inquiries = inquiryRepository.findAllByProductId(productId)
                .stream()
                .map(InquiryConverter::toDto)
                .toList();

        return toGetProductRes(productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new), inquiries, check(productId));
    }

    private boolean check(Long productId) {
        try {
            User user = userService.getCurrentUser();
            return productLikeRepository.existsByProductIdAndUserId(productId, user.getId());
        } catch (CustomException e) {
            return false;
        }
    }
}
