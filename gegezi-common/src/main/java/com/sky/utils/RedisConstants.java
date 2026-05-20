package com.sky.utils;

public class RedisConstants {
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;

    public static final String LOGIN_USER_KEY = "login:user:";
    public static final Long LOGIN_USER_TTL = 36000L;

    public static final String LOGIN_TOKEN_KEY = "login:token:";
    public static final Long LOGIN_TOKEN_TTL = 36000L;

    // 修改前缀使其与登录区分开，如果是forget:code:就会报错
    public static final String FORGET_CODE_KEY = "forget:pwd:code:";
    public static final Long FORGET_CODE_TTL=5L;
}
