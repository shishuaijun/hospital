package com.follow.service.impl;

import com.follow.entity.User;
import com.follow.mapper.UserMapper;
import com.follow.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
