package com.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
