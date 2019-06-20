package com.blog.business;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public abstract class AbstractBizSrv<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
}
