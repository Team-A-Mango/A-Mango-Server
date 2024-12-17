package com.mango.amango.domain.inquiry.util;

import com.mango.amango.domain.inquiry.entity.Inquiry;
import com.mango.amango.domain.inquiry.presentation.dto.request.CreateInquiryReq;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.user.entity.User;

public interface InquiryConverter {
    Inquiry toEntity (CreateInquiryReq req, Product product, User user);
}
