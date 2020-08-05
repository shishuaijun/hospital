package com.follow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.follow.entity.User;
import com.follow.mapper.UserMapper;
import com.follow.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author wangchunjun
 * @since 2020-08-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
