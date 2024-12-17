package com.mango.amango.domain.inquiry.presentation;

import com.mango.amango.domain.inquiry.presentation.dto.request.CreateInquiryReq;
import com.mango.amango.domain.inquiry.serivce.CreateInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final CreateInquiryService createInquiryService;

    @PostMapping("/{productId}")
    public ResponseEntity<Void> create (
            @RequestBody CreateInquiryReq req, @PathVariable Long productId
    ) {
        createInquiryService.execute(req, productId);
        return ResponseEntity.status(CREATED).build();
    }

}
