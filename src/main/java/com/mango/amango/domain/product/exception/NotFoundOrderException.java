package com.mango.amango.domain.product.exception;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;

public class NotFoundOrderException extends CustomException {
  public NotFoundOrderException() {
    super(CustomErrorCode.NOT_FOUND_ORDER);
  }
}
