package com.blog.server.component.configuration;

import com.blog.bean.AliyunOssProperties;
import com.blog.bean.JWTProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 公共组件配置
 *
 * @author Nicholas
 * @since 2019-06-27
 */
@Configuration
public class ComponentConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "blog.jwt")
    public JWTProperties jwtProperties() {
        return new JWTProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "aliyun.oss")
    public AliyunOssProperties aliyunOssProperties() {
        return new AliyunOssProperties();
    }




    /*@Bean
    public IJwtTokenHelper jwtTokenHelper(CacheManager cacheManager) {
        return new JwtTokenHelper(jwtProperties(), cacheManager);
    }



    @Bean
    public ExpiredTokenValidateInterceptor expiredTokenValidateInterceptor() {
        return new ExpiredTokenValidateInterceptor();
    }


    @Bean
    public RedisSequenceFactory redisSequenceFactory() {
        return new RedisSequenceFactory();
    }*/

}
