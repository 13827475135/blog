package com.blog.bean;

import java.util.Map;

/**
 * 缓存配置
 */
public class BlogCacheProperties {

    private long defaultTtl = 600;

    private int defaultMaxSize = 10000;

    private Map<String, Long> ttls;

    private Map<String, Integer> maxEntries;

    public long getDefaultTtl() {
        return defaultTtl;
    }

    public BlogCacheProperties setDefaultTtl(long defaultTtl) {
        this.defaultTtl = defaultTtl;
        return this;
    }

    public Map<String, Long> getTtls() {
        return ttls;
    }

    public BlogCacheProperties setTtls(Map<String, Long> ttls) {
        this.ttls = ttls;
        return this;
    }

    public int getDefaultMaxSize() {
        return defaultMaxSize;
    }

    public void setDefaultMaxSize(int defaultMaxSize) {
        this.defaultMaxSize = defaultMaxSize;
    }

    public Map<String, Integer> getMaxEntries() {
        return maxEntries;
    }

    public void setMaxEntries(Map<String, Integer> maxEntries) {
        this.maxEntries = maxEntries;
    }
}
