package com.blog.exception;

import com.blog.util.HttpContextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 全局异常基类
 *
 * @author Nicholas
 * @since 2019-06-27
 */
public class BizException extends RuntimeException {

    //common error code
    private int code;

    //message params for the placeholders defined in the message
    private List<Object> messageParams = new ArrayList<>();

    /**
     * @param code 错误编码
     */
    public BizException(int code) {
        super(HttpContextUtils.getMessage(code));
        this.code = code;
    }

    public BizException(int code, List<Object> messageParams) {
        super(HttpContextUtils.getMessage(code, messageParams == null ? Collections.emptyList().toArray() : messageParams.toArray()));
        this.code = code;
        if (messageParams != null) {
            this.messageParams = messageParams;
        }
    }

    /**
     * @param code 错误编码
     * @param message 自定义消息
     */
    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(int code, Throwable th) {
        super(th);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public BizException setCode(int code) {
        this.code = code;
        return this;
    }

    public List<Object> getMessageParams() {
        return messageParams;
    }
}
