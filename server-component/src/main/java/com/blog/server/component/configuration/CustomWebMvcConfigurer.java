package com.blog.server.component.configuration;


import com.blog.shiro.CurrentUserMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Web Configurer
 *
 * @author nicholas
 * @since 2019-06-27
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

   /* @Autowired
    private ExpiredTokenValidateInterceptor expiredTokenValidateInterceptor;*/


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       /* registry.addInterceptor(this.expiredTokenValidateInterceptor).addPathPatterns("/**");
        registry.addInterceptor(this.requestSignatureValidationInterceptor).addPathPatterns("/**");
        registry.addInterceptor(this.extApiSecurityInterceptor).addPathPatterns("/ext/api/**"); //仅对第三方api生效*/
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }


   /* @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }*/
}
