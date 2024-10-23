package com.mango.amango.global.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class KeyUtil {

    public String keyIssuance() {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        Integer num = 0;

        while (buffer.length() < 6) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }
}
