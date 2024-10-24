package com.mango.amango.domain.eamil.service.impl;

import com.mango.amango.domain.eamil.exception.MailDeliveryFailedException;
import com.mango.amango.domain.eamil.presentation.dto.req.EmailSendReq;
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

    public void execute(EmailSendReq email){
        try {
            String code = keyUtil.keyIssuance();
            emailSend.send(email.email(), code);
        } catch (MessagingException e) {
            throw new MailDeliveryFailedException();
        }
    }
}
