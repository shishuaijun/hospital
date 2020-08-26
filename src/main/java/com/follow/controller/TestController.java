package com.follow.controller;

import com.follow.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    public void Test(HttpServletRequest request){
        val session = request.getSession();
        Object id = session.getAttribute("id");
        Integer userId = Integer.parseInt(id.toString());
        System.out.println(userId);
    }
}
