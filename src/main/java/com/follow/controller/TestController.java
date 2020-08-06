package com.follow.controller;

import com.alibaba.fastjson.JSON;
import com.follow.entity.User;
import com.follow.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;


    @RequestMapping("/test")
    @ResponseBody
    public String Test(){
        List<User> list = userService.list();
        return JSON.toJSONString(list);
    }
}
