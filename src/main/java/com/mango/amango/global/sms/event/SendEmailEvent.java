package com.mango.amango.global.sms.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SendEmailEvent {
    private final String fromPhoneNumber;
    private final String message;
}
