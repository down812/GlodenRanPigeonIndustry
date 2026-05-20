package com.sky.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {
    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return          生成的 JWT 字符串
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 新增密钥验证
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("密钥不能为空");
        }
        log.info("生成Token使用密钥内容：[{}]", secretKey);
        log.info("生成Token密钥字节数：{}", secretKey.getBytes(StandardCharsets.UTF_8).length);

        // 指定签名的时候使用的签名算法，也就是header那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // 添加签发时间声明（关键修复）
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")  // 明确声明类型
                .setIssuedAt(new Date())       // 必须放在setClaims之前
                .setClaims(claims)
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return 解析后的 JWT Claims 对象
     */
    public static Claims parseJWT(String secretKey, String token) {
        // 新增密钥验证
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("密钥不能为空");
        }
        log.info("验证Token使用密钥内容：[{}]", secretKey);
        log.info("验证Token密钥字节数：{}", secretKey.getBytes(StandardCharsets.UTF_8).length);

        log.info("生成Token使用密钥啊啊啊: {}", secretKey);
        log.info("生成密钥字节哈希: {}", Arrays.hashCode(secretKey.getBytes(StandardCharsets.UTF_8)));
        log.info("实际使用密钥内容: [{}]", secretKey);
        log.info("生成密钥字节哈希: {}", Arrays.hashCode(secretKey.getBytes(StandardCharsets.UTF_8)));

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();

            // 修复声明校验逻辑
            if (claims.getIssuer() == null || !"sky-server".equals(claims.getIssuer())) {
                throw new MalformedJwtException("Issuer校验失败");
            }
            if (claims.getSubject() == null) {
                throw new MalformedJwtException("Subject缺失");
            }

            return claims;
        } catch (SignatureException e) {
            throw new RuntimeException("JWT签名不匹配，请检查以下内容：\n"
                    + "1. 确保生成和验证使用相同密钥\n"
                    + "2. 检查密钥前后是否有空格\n"
                    + "3. 确认token未被篡改", e);
        } catch (Exception e) {
            throw new RuntimeException("JWT解析失败: " + e.getMessage(), e);
        }
    }

}
