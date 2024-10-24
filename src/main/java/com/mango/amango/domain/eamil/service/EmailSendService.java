package com.mango.amango.domain.eamil.service;

import com.mango.amango.domain.eamil.presentation.dto.req.EmailSendReq;

public interface EmailSendService {
    void execute(EmailSendReq email);
}
