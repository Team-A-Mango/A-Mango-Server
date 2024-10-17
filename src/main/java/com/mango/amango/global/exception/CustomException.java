package com.mango.amango.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final CustomErrorCode customErrorCode;
    private final String detailMessage;

    public
    CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getStatusMessage());
        this.customErrorCode = customErrorCode;
        this.detailMessage = customErrorCode.getStatusMessage();
    }
}
