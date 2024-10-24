package com.mango.amango.domain.eamil.exception;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;

public class MailDeliveryFailedException extends CustomException {
    public MailDeliveryFailedException() {
        super(CustomErrorCode.MAIL_DELIVERY_FAILED);
    }
}
