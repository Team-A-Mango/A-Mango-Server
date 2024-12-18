package com.mango.amango.global.sms;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("coolsms.api")
public class CoolSmsProperties {
    @NotEmpty
    private final String key;
    @NotEmpty
    private final String secretKey;
    @NotEmpty
    private final String toPhoneNumber;

    public CoolSmsProperties(String key, String secret, String phoneNumber) {
        this.key = key;
        this.secretKey = secret;
        this.toPhoneNumber = phoneNumber;
    }
}
