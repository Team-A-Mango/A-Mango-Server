package com.mango.amango.global.security.jwt.properties;

import com.mango.amango.global.security.jwt.util.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.Key;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    private Key accessSecret;
    private Key refreshSecret;
    private long accessExpiration;
    private long refreshExpiration;

    public void setAccessSecret(String ACCESS_SECRET_KEY) {
        this.accessSecret = JwtUtil.getSigningKey(ACCESS_SECRET_KEY);
    }

    public void setRefreshSecret(String REFRESH_SECRET_KEY) {
        this.refreshSecret = JwtUtil.getSigningKey(REFRESH_SECRET_KEY);
    }

}
