package com.mango.amango.domain.eamil.service;

import com.mango.amango.domain.eamil.presentation.dto.req.EmailSendReq;
import jakarta.mail.MessagingException;

public interface EmailSendService {
    void execute(EmailSendReq email) throws MessagingException;
}
