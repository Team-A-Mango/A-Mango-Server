package com.mango.amango.domain.inquiry.presentation.dto.request;

import lombok.Builder;

@Builder
public record CreateInquiryReq(
        String content
) {
}
