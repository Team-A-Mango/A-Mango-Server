package com.mango.amango.global.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomErrorRes {
    private final CustomErrorCode status;
    private final String statusMessage;
}
