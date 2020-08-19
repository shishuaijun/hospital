package com.follow.service.impl;

import com.follow.entity.User;
import com.follow.mapper.FollowgroupMapper;
import com.follow.mapper.PermissionsMapper;
import com.follow.mapper.UserMapper;
import com.follow.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private FollowgroupMapper followgroupMapper ;
    @Autowired
    private UserMapper userMapper ;
    @Autowired
    private PermissionsMapper permissionsMapper;


}
