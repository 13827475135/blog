package com.blog.entity.system;

import com.blog.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUser extends BaseEntity {

    private String mobile;

    private String name;

    private String password;

    // 头像
    private String avatar;

    // 是否冻结
    private boolean frozen;

}
