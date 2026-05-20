package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "sky.sms")
public class SMSProperty {
    /**
     * 区域ID
     */
    private String regionId;

    /**
     * 应用Key
     */
    private String appKey;

    /**
     * 应用密钥
     */
    private String appSecret;
}