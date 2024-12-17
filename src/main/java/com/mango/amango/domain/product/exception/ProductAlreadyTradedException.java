package com.mango.amango.domain.product.exception;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;

public class ProductAlreadyTradedException extends CustomException {
    public ProductAlreadyTradedException() {
        super(CustomErrorCode.PRODUCT_ALREADY_TRADED);
    }
}
