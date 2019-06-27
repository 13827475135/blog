package com.blog.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.blog.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@TableName(value = "user")
@Getter
@Setter
@Accessors(chain = true)
public class User extends BaseEntity {

    private String mobile;

    private String name;

    private String password;

    // 头像
    private String avatar;

    // 是否冻结
    private boolean frozen;

}
