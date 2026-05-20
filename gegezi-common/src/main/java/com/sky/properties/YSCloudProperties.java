package com.sky.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.yscloud")
@Data
public class YSCloudProperties {
    private String appKey;
    private String appSecret;
    private String accessToken;
    private Long accessTokenExpireTime;
}