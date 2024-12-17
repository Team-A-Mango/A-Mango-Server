package com.mango.amango.domain.inquiry.util;

import com.mango.amango.domain.inquiry.entity.Inquiry;
import com.mango.amango.domain.inquiry.presentation.dto.request.CreateInquiryReq;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.presentation.dto.response.GetProductRes;
import com.mango.amango.domain.user.entity.User;

public abstract class InquiryConverter {

    public static Inquiry toEntity(CreateInquiryReq req, Product product, User user) {
        return Inquiry.builder()
                .content(req.content())
                .product(product)
                .user(user)
                .build();
    }

    public static GetProductRes.GetInquiry toDto(Inquiry inquiry) {
        return GetProductRes.GetInquiry.builder()
                .author(inquiry.getUser().getNickname())
                .content(inquiry.getContent())
                .profileImag(inquiry.getUser().getProfile())
                .build();
    }
}
