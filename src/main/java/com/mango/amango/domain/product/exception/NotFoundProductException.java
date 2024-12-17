package com.mango.amango.domain.product.exception;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;

public class NotFoundProductException extends CustomException {
    public NotFoundProductException() {
        super(CustomErrorCode.NOT_FOUND_PRODUCT);
    }
}
