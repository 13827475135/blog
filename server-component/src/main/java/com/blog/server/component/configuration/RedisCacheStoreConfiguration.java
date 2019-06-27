package com.blog.server.component.configuration;

import com.blog.bean.BlogCacheProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis缓存配置，扩展并设置缓存空间的过期时间
 */
@Configuration
@EnableCaching(order = Ordered.HIGHEST_PRECEDENCE)
public class RedisCacheStoreConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "blog.cache")
    public BlogCacheProperties lepinCacheProperties() {
        return new BlogCacheProperties();
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config  = new HashMap<>();
        Map<String, Integer> maxEntries = lepinCacheProperties().getMaxEntries();
        if (lepinCacheProperties().getTtls() != null) {
            lepinCacheProperties().getTtls().forEach((store, ttl) -> {
                if (StringUtils.isNoneBlank(store)) {
                    ttl = (ttl < 1 ? 0 : ttl) * 1000;
                    CacheConfig ccf = new CacheConfig(ttl, 0);
                    if (maxEntries != null &&  maxEntries.containsKey(store) && maxEntries.get(store) != null) {
                        ccf.setMaxSize(maxEntries.get(store));
                    } else {
                        ccf.setMaxSize(lepinCacheProperties().getDefaultMaxSize());
                    }
                    config.put(store.trim(), ccf);
                }
            });
        }
        RedissonSpringCacheManager cacheManager = new RedissonSpringCacheManager(redissonClient, config);
        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }

    @Bean
    public JedisPool jedis(RedisProperties redisProperties) {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(20);
        genericObjectPoolConfig.setMaxIdle(10);
        genericObjectPoolConfig.setMinIdle(5);
        genericObjectPoolConfig.setMaxWaitMillis(10 * 1000);
        return new JedisPool(genericObjectPoolConfig, redisProperties.getHost(), redisProperties.getPort(), Protocol.DEFAULT_TIMEOUT, null,
                Protocol.DEFAULT_DATABASE, null);
    }
}
