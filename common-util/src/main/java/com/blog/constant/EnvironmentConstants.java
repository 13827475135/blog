package com.blog.constant;

/**
 * 系统支持的环境列表
 *
 * @author Nicholas
 * @since 2019-06-27
 */
public interface EnvironmentConstants {

    //生产环境
    String PROD = "prod";

    //测试环境
    String TEST = "test";

    //开发环境
    String DEV = "dev";

    //支持的环境列表
    String[] ENVIRONMENTS = new String[]{DEV, TEST, PROD};
}
