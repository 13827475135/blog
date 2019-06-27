package com.blog.constant;

/**
 * web端常用参数
 *
 * @author nicholas
 * @since 2019-06-27
 */
public interface WebConstants {

    /**
     * query传参的分隔符
     */
    String QUERY_SPLIT = "?";
    /**
     * 参数分隔符
     */
    String PARAM_SPLIT = "&";

    /**
     * 参数与值的连接符
     */
    String PARAM_VALUE_JOIN = "=";

    /**
     * Query传参时间戳
     */
    String LPR_TIMESTAMP_PARAM = "_ts";

    /**
     * Query传参签名
     */
    String LPR_SIGNATURE_PARAM = "_signature";

    /**
     * Query传参语言
     */
    String LPR_LANGUAGE_PARAM = "language";

    /**
     * Request域内的当前用户参数名
     */
    String REQUEST_SCOPE_CURRENT_USER = "current_user";

    /**
     * Request域内的当前请求的token是否过期标志位参数
     */
    String REQUEST_SCOPE_TOKEN_EXPIRED = "is_token_expired";

}
