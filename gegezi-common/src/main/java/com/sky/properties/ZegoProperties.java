package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.zego")
@Data
public class ZegoProperties {
    /**
     * ZEGO应用ID
     */
    private long appId;
    
    /**
     * ZEGO服务端密钥
     */
    private String serverSecret;
    
    /**
     * Token有效期（秒）
     */
    private int tokenTtl;


//    private String userId;
}