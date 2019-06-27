package com.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity {

    @TableId
    private Long id;

    @Version
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime updateTime;

    @JsonIgnore
    private LocalDateTime createTime;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDel;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
