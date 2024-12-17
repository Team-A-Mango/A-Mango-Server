package com.mango.amango.global.file.s3;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("cloud.aws")
public class S3Properties {
    @NotEmpty
    private final String accessKey;
    @NotEmpty
    private final String accessSecret;
    @NotEmpty
    private final String region;

    public S3Properties(String accessKey, String secretKey, String region) {
        this.accessKey = accessKey;
        this.accessSecret = secretKey;
        this.region = region;
    }
}
