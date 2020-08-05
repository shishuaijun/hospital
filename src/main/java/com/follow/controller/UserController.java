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
 * @date 2020/8/5
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getuserlist")
    @ResponseBody
    public String getUserList(){
        List<User> list = userService.list();
        return JSON.toJSONString(list);
    }
}
