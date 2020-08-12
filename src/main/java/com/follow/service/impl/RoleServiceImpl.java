package com.follow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.follow.entity.Role;
import com.follow.entity.User;
import com.follow.mapper.RoleMapper;
import com.follow.mapper.UserMapper;
import com.follow.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getsUserName(String name) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name",name);
        Role role = roleMapper.selectOne(wrapper);
        Integer roleId = role.getId();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("role_id",roleId);
        List<User> users = userMapper.selectList(userQueryWrapper);
        ArrayList<User> users1 = new ArrayList<>();
        for (User user : users) {
            User user1 = new User();
            user1.setId(user.getId());
            user1.setUserName(user.getUserName());
            users1.add(user1);
        }
        return users1;
    }
}
