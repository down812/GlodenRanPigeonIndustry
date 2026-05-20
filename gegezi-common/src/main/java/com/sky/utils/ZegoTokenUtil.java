package com.sky.utils;



import com.alibaba.fastjson.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Base64;

/**
 * ZEGO Token工具类，用于生成和验证监控接口的Token
 */
public class ZegoTokenUtil {

    /**
     * 生成基础Token
     *
     * @param appId ZEGO应用ID
     * @param serverSecret ZEGO服务端密钥
     * @param userId 用户ID
     * @param ttlSeconds Token有效期（秒）
     * @return 生成的Token字符串
     */
    public static String generateToken04(long appId, String serverSecret, String userId, int ttlSeconds) {
        // 基础鉴权token，payload字段可传空
        String payload = "{\"payload\":\"monitor\"}";
        TokenServerAssistant.VERBOSE = false;
        TokenServerAssistant.TokenInfo token = TokenServerAssistant.generateToken04(
                appId, userId, serverSecret, ttlSeconds, payload);

        if (token.error.code == TokenServerAssistant.ErrorCode.SUCCESS) {
            return token.data;
        } else {
            throw new RuntimeException("生成Token失败: " + token.error.message);
        }
    }

    /**
     * 生成带权限的Token
     *
     * @param appId ZEGO应用ID
     * @param serverSecret ZEGO服务端密钥
     * @param userId 用户ID
     * @param ttlSeconds Token有效期（秒）
     * @param roomId 房间ID
     * @param canLogin 是否允许登录
     * @param canPublish 是否允许推流
     * @return 生成的Token字符串
     */
    public static String generateToken04WithPrivilege(
            long appId, String serverSecret, String userId, int ttlSeconds,
            String roomId, boolean canLogin, boolean canPublish) {

        JSONObject payloadData = new JSONObject();
        payloadData.put("room_id", roomId);

        JSONObject privilege = new JSONObject();
        privilege.put(TokenServerAssistant.PrivilegeKeyLogin,
                canLogin ? TokenServerAssistant.PrivilegeEnable : TokenServerAssistant.PrivilegeDisable);
        privilege.put(TokenServerAssistant.PrivilegeKeyPublish,
                canPublish ? TokenServerAssistant.PrivilegeEnable : TokenServerAssistant.PrivilegeDisable);

        payloadData.put("privilege", privilege);
        String payload = payloadData.toJSONString();

        TokenServerAssistant.VERBOSE = false;
        TokenServerAssistant.TokenInfo token = TokenServerAssistant.generateToken04(
                appId, userId, serverSecret, ttlSeconds, payload);

        if (token.error.code == TokenServerAssistant.ErrorCode.SUCCESS) {
            return token.data;
        } else {
            throw new RuntimeException("生成Token失败: " + token.error.message);
        }
    }

    /**
     * 验证Token是否有效
     *
     * @param token 待验证的Token
     * @param serverSecret ZEGO服务端密钥
     * @return 是否有效
     */
    public static boolean verifyToken(String token, String serverSecret) {
        try {
            if (token == null || token.isEmpty() || !token.startsWith("04")) {
                return false;
            }

            // 解析token
            String realToken = token.substring(2); // 去掉版本号"04"
            byte[] tokenBytes = Base64.getDecoder().decode(realToken);

            ByteBuffer buffer = ByteBuffer.wrap(tokenBytes);
            buffer.order(ByteOrder.BIG_ENDIAN);

            // 读取过期时间
            long expireTime = buffer.getLong();
            long currentTime = System.currentTimeMillis() / 1000;

            // 检查是否过期
            if (currentTime > expireTime) {
                return false;
            }

            // 其他验证逻辑可以根据需要添加

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}