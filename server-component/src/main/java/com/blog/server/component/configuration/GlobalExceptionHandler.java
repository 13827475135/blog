package com.blog.server.component.configuration;

import com.blog.api.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

/**
 * 全局异常处理: 使用 @RestControllerAdvice + @ExceptionHandler 注解方式实现全局异常处理
 *
 * @author Nicholas
 * @since 2019-06-27
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public RestResponse<?> globalExceptionHandler(Exception e) {
        LOGGER.error(e.getMessage(), e);
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return RestResponse.error(100004);
        }
        return RestResponse.error(e.getMessage());
    }

    /**
     * 处理与Http请求消息转换有关的异常，通常发生的原因是POST请求体为空
     */
    @ExceptionHandler({HttpMessageConversionException.class})
    public RestResponse<?> httpMessageConvertExceptionHandler(HttpMessageConversionException e) {
        LOGGER.error(e.getMessage(), e);
        return RestResponse.error(100002);
    }

    /**
     * 处理与Shiro有关的异常
     */
   /* @ExceptionHandler({ShiroException.class})
    public RestResponse<?> shiroExceptionHandler(ShiroException e) {
        LOGGER.error(e.getMessage(), e);
        return RestResponse.error(ExceptionCodeUtils.convertShiroExceptionToCode(e));
    }*/


    /**
     * 处理与参数校验有关的异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RestResponse<?> validateExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error(e.getMessage(), e);
        FieldError fieldError = (FieldError) e.getBindingResult().getAllErrors().get(0);
        String messageCode = fieldError.getDefaultMessage();
        String field = fieldError.getField();
        if (StringUtils.isBlank(messageCode)) {
            return RestResponse.error(100603, Collections.singletonList(field));
        } else {
            return RestResponse.error(Integer.valueOf(messageCode), Collections.singletonList(field));
        }
    }

}
