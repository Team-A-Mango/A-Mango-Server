package com.mango.amango.domain.eamil.service.impl;

import com.mango.amango.domain.eamil.presentation.dto.req.EmailSendReq;
import com.mango.amango.domain.eamil.repository.EmailCertificateRepository;
import com.mango.amango.domain.eamil.service.EmailSendService;
import com.mango.amango.global.email.EmailSend;
import com.mango.amango.global.util.KeyUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailSendServiceImpl implements EmailSendService {

    private final KeyUtil keyUtil;
    private final EmailSend emailSend;
    private final EmailCertificateRepository emailRepository;

    public void execute(EmailSendReq email) throws MessagingException {
        String code = keyUtil.keyIssuance();
        emailSend.send(email.getEmail(), code);
    }
}
