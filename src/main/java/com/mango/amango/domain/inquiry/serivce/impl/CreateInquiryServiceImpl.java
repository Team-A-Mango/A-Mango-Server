package com.mango.amango.domain.inquiry.serivce.impl;

import com.mango.amango.domain.inquiry.entity.Inquiry;
import com.mango.amango.domain.inquiry.presentation.dto.request.CreateInquiryReq;
import com.mango.amango.domain.inquiry.repository.InquiryRepository;
import com.mango.amango.domain.inquiry.serivce.CreateInquiryService;
import com.mango.amango.domain.inquiry.util.InquiryConverter;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateInquiryServiceImpl implements CreateInquiryService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final InquiryRepository inquiryRepository;

    public void execute(CreateInquiryReq req, Long productId) {
        User user = userService.getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        Inquiry inquiry = InquiryConverter.toEntity(req,product,user);

        inquiryRepository.save(inquiry);
    }
}
