package com.mango.amango.domain.inquiry.serivce;

import com.mango.amango.domain.inquiry.presentation.dto.request.CreateInquiryReq;

public interface CreateInquiryService {
    void execute (CreateInquiryReq req, Long productId);
}
