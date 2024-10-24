package com.mango.amango.domain.eamil.presentation.dto.req;

import lombok.Builder;

@Builder
public record EmailSendReq (
        String email
){
}
