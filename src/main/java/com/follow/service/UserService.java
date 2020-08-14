package com.follow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.follow.entity.User;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface UserService extends IService<User> {


    List<User> findAll();
}
