package com.blog.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.blog.util.DateCommonUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * 数据库实体自动填充字段处理
 *
 * @author Nicholas
 * @since 2018-12-11
 */
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoFillMetaObjectHandler.class);

    // 系统版本号控制字段名
    private static final String VERSION_FIELD_NAME = "updateTime";

    // 是否删除自动填充字段
    private static final String IS_DEL_FIELD_NAME = "isDel";

    @Override
    public void insertFill(MetaObject metaObject) {
        // 数据库timestamp目前未存储毫秒值，so
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        LOGGER.info("Fill fields automatically for fields: {}", VERSION_FIELD_NAME);
        this.setFieldValByName(VERSION_FIELD_NAME, DateCommonUtils.convertToLocalDateTime(calendar.getTime()), metaObject);
        this.setFieldValByName(IS_DEL_FIELD_NAME, Boolean.FALSE, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("No fields for updating need to fill!");
    }
}
