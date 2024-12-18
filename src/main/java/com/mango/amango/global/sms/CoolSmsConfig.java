package com.mango.amango.global.sms;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(CoolSmsProperties.class)
public class CoolSmsConfig {

    private final CoolSmsProperties coolSmsProperties;

    @Bean
    public DefaultMessageService defaultMessageService() {
        return NurigoApp.INSTANCE.initialize(coolSmsProperties.getKey(), coolSmsProperties.getSecretKey(), "https://api.coolsms.co.kr");
    }
}
