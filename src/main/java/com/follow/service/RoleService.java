package com.follow.service;

import com.follow.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.follow.entity.User;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据角色获取用户姓名列表
     * @param name
     * @return
     */
    List<User> getsUserName(String name);
}
