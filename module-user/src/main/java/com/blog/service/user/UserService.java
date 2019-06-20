package com.blog.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.user.User;

/**
 *
 * 用户信息service接口
 * @author Nicholas
 * @since 2019-06-20
 */
public interface UserService extends IService<User> {

    User getByMobile(String mobile);

}
