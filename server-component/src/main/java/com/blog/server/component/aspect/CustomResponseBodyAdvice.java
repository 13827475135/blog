/*
package com.blog.server.component.aspect;

import com.lepin.common.bean.JWTProperties;
import com.lepin.common.business.RedisSequenceFactory;
import com.lepin.common.constants.WebConstants;
import com.lepin.common.utils.IPUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

*/
/**
 * 在任何响应结果返回前检查cookie中是否存在creditme_client_key，不存在则设置一个值，用于放重复提交和请求限流
 *
 * @author Nicholas
 * @since 2019-06-27
 *//*

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private RedisSequenceFactory redisSequenceFactory;

    @Autowired
    private JWTProperties jwtProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        HttpServletResponse httpServletResponse = servletRequestAttributes.getResponse();
        //检查当前cookie中是否存在client_key
        boolean addCookie = false;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null || cookies.length == 0) {
            addCookie = true;
        }
        if (!addCookie) {
            addCookie = Stream.of(cookies).noneMatch(cookie -> cookie != null && WebConstants.COOKIE_CLIENT_KEY.equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue()));
        }
        if (addCookie) { //不存在则设置一个值
            String addr = IPUtils.getIpAddr(httpServletRequest);
            if (StringUtils.isBlank(addr)) {
                addr = request.getRemoteAddress().getHostString();
            }
            //client key中增加客户端地址和时间戳的信息并混淆，在接口限流和重复提交的限制中根据client_key和提交的数据进行验证，如果client_key不存在则根据请求来源的ip进行验证
            String key = addr + (System.currentTimeMillis() / 1000);
            Cookie c = new Cookie(WebConstants.COOKIE_CLIENT_KEY, DigestUtils.md5Hex(key + this.redisSequenceFactory.generateAtomicString(key)));
            c.setPath("/");
            c.setMaxAge(Long.valueOf(this.jwtProperties.getTokenExpireTime()).intValue());
            httpServletResponse.addCookie(c);
        }
        return body;
    }
}
*/
