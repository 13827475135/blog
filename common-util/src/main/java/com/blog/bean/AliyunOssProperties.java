package com.blog.bean;


public class AliyunOssProperties {

    // 账户名
    private String accessKeyId;

    // 密码
    private String accessKeySecret;

    // ARN
    private String roleArn;

    // 启用内网访问地址
    private Boolean userInternal;

    // 公有的空间
    private String publicBucket;

    // 公有空间访问域名
    private String publicEndpoint;

    // 公有空间阿里云内网访问域名
    private String publicInternalEndpoint;

    // 公有的访问主机
    private String publicBucketHost;

    // 公有的阿里云内网访问主机
    private String publicInternalBucketHost;

    // 私有的空间
    private String privateBucket;

    // 私有空间访问域名
    private String privateEndpoint;

    // 私有空间阿里云内网访问域名
    private String privateInternalEndpoint;

    // 私有的访问主机
    private String privateBucketHost;

    // 私有的阿里云内网访问主机
    private String privateInternalBucketHost;

    // 过期时间秒
    private Long expireTime;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    public String getPublicBucket() {
        return publicBucket;
    }

    public void setPublicBucket(String publicBucket) {
        this.publicBucket = publicBucket;
    }

    public String getPublicBucketHost() {
        return publicBucketHost;
    }

    public void setPublicBucketHost(String publicBucketHost) {
        this.publicBucketHost = publicBucketHost;
    }

    public String getPrivateBucket() {
        return privateBucket;
    }

    public void setPrivateBucket(String privateBucket) {
        this.privateBucket = privateBucket;
    }

    public String getPrivateBucketHost() {
        return privateBucketHost;
    }

    public void setPrivateBucketHost(String privateBucketHost) {
        this.privateBucketHost = privateBucketHost;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getPublicEndpoint() {
        return publicEndpoint;
    }

    public void setPublicEndpoint(String publicEndpoint) {
        this.publicEndpoint = publicEndpoint;
    }

    public String getPrivateEndpoint() {
        return privateEndpoint;
    }

    public void setPrivateEndpoint(String privateEndpoint) {
        this.privateEndpoint = privateEndpoint;
    }

    public String getPublicInternalEndpoint() {
        return publicInternalEndpoint;
    }

    public void setPublicInternalEndpoint(String publicInternalEndpoint) {
        this.publicInternalEndpoint = publicInternalEndpoint;
    }

    public String getPublicInternalBucketHost() {
        return publicInternalBucketHost;
    }

    public void setPublicInternalBucketHost(String publicInternalBucketHost) {
        this.publicInternalBucketHost = publicInternalBucketHost;
    }

    public String getPrivateInternalEndpoint() {
        return privateInternalEndpoint;
    }

    public void setPrivateInternalEndpoint(String privateInternalEndpoint) {
        this.privateInternalEndpoint = privateInternalEndpoint;
    }

    public String getPrivateInternalBucketHost() {
        return privateInternalBucketHost;
    }

    public void setPrivateInternalBucketHost(String privateInternalBucketHost) {
        this.privateInternalBucketHost = privateInternalBucketHost;
    }

    public Boolean getUserInternal() {
        return userInternal;
    }

    public void setUserInternal(Boolean userInternal) {
        this.userInternal = userInternal;
    }
}
