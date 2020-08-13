package com.follow.controller;


import com.alibaba.fastjson.JSON;
import com.follow.entity.User;
import com.follow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @Title: findAll
     * @Description: 全查
     * @return
     * @return: String
     */
    @RequestMapping(value = "/findAll")
    public String findAll() {

        List<User> list = userService.findAll();
        String jsonString = JSON.toJSONString(list);
        return jsonString;

    }

}

