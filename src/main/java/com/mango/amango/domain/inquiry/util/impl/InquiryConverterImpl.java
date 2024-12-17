package com.mango.amango.domain.inquiry.util.impl;

import com.mango.amango.domain.inquiry.entity.Inquiry;
import com.mango.amango.domain.inquiry.presentation.dto.request.CreateInquiryReq;
import com.mango.amango.domain.inquiry.util.InquiryConverter;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class InquiryConverterImpl implements InquiryConverter {

    public Inquiry toEntity(CreateInquiryReq req, Product product, User user) {
        return Inquiry.builder()
                .content(req.content())
                .product(product)
                .user(user)
                .build();
    }
}
