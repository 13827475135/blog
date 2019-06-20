package com.blog.entity.user;

import com.blog.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseEntity {

    private String mobile;

    private String name;

    private String password;

    // 头像
    private String avatar;

    // 是否冻结
    private boolean frozen;

}
