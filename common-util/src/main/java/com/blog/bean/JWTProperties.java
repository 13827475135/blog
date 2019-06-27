package com.blog.bean;

/**
 * JWT 配置文件
 *
 * @author Nicholas
 * @since 2019-06-27
 */
public class JWTProperties {

    //token的过期时间
    private long tokenExpireTime;

    //临时token的过期时间
    private long tempTokenExpireTime;

    //token的公共加密盐
    private String secret;

    public long getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(long tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getTempTokenExpireTime() {
        return tempTokenExpireTime;
    }

    public JWTProperties setTempTokenExpireTime(long tempTokenExpireTime) {
        this.tempTokenExpireTime = tempTokenExpireTime;
        return this;
    }
}
