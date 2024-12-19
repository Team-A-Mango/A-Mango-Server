package com.mango.amango.domain.product.exception;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;

public class OrderAlreadyTradedException extends CustomException {
  public OrderAlreadyTradedException() {
    super(CustomErrorCode.ORDER_ALREADY_TRADED);
  }
}
