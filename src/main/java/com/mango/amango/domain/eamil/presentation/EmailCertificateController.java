package com.mango.amango.domain.eamil.presentation;

import com.mango.amango.domain.eamil.presentation.dto.req.EmailSendReq;
import com.mango.amango.domain.eamil.service.EmailSendService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailCertificateController {

    private final EmailSendService emailSendService;

    @PostMapping
    public ResponseEntity<Void> sendEmail (@RequestBody EmailSendReq email) throws MessagingException {
        emailSendService.execute(email);
        return ResponseEntity.ok().build();
    }
}
