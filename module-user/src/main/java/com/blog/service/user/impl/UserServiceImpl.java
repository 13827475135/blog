package com.blog.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.business.AbstractBizSrv;
import com.blog.dao.user.UserDao;
import com.blog.entity.user.User;
import com.blog.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractBizSrv<UserDao, User> implements UserService {

    @Override
    public User getByMobile(String mobile) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getMobile, mobile);
        return baseMapper.selectOne(wrapper);
    }
}
