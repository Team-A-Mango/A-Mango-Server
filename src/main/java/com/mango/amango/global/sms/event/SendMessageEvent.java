package com.mango.amango.global.sms.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SendMessageEvent {
    private final String fromPhoneNumber;
    private final String message;
}
