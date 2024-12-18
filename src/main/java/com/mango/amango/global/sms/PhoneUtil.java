package com.mango.amango.global.sms;

public abstract class PhoneUtil {

    public static String formatPhoneNumber(String phoneNumber) {
        return phoneNumber.replaceAll("-", "");
    }
}
