package com.follow.controller;

import javax.servlet.http.Cookie;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.entity.User;
import com.follow.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;


import java.util.Date;
import java.util.Calendar;

import java.text.SimpleDateFormat;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 根据科室id 查询用户
     *
     * @param id
     * @return
     */
    @RequestMapping("getUserAll")
    public JSONResult getUserAll(@RequestParam(value = "id", required = false) String id) {
        JSONResult jsonResult = null;
        try {
            Map map = new HashMap();
            if (!id.equals("0") && id != null) {
                map.put("department_id", id);
            }
            List<User> list = userService.listByMap(map);
            jsonResult = new JSONResult(ResultEum.SUCCESS, (long) list.size(), list);
        } catch (Exception e) {
            jsonResult = new JSONResult(ResultEum.ERROR, 0L, null);
        }
        return jsonResult;
    }

    @RequestMapping("getUserByUserName")
    public JSONResult getUserByUserName(@RequestParam("userName") String userName) {
        JSONResult jsonResult = null;
        try {
            Map map = new HashMap();
            if (userName != null) {
                map.put("user_name", userName);
            }
            List<User> list = userService.listByMap(map);
            jsonResult = new JSONResult(ResultEum.SUCCESS, (long) list.size(), list);
        } catch (Exception e) {
            jsonResult = new JSONResult(ResultEum.ERROR, 0L, null);
        }
        return jsonResult;
    }

    /**
     * @return
     * @Title: findAll
     * @Description: 全查
     * @return: String
     */
    @RequestMapping(value = "/findAll")
    public String findAll() {

        List<User> list = userService.findAll();
        String jsonString = JSON.toJSONString(list);
        return jsonString;

    }


    /**
     * 功能描述： TODO[ 登录校验  session]
     * @auther: Zuan~
     * @date: 2020/8/19  17:13
     * @param: user
     * @return: 0: 普通用户、1：管理员、2：账号或密码错误
     * @return： String

     @RequestMapping("/denglu") public String denglu(String account, String passWord, HttpSession session) {
     User user = new User();
     user.setAccount(account);
     user.setPassWord(passWord);
     User checkUser = userService.checkUser(user);

     //校验账号和密码是否正确
     if (checkUser != null) {
     //判断登录的是用户还是管理员
     if (checkUser.getRoleId() == null) {
     return "0";
     } else if (checkUser.getRoleId() == 2) {
     session.setAttribute("roleId", 2);
     return "2";
     } else if (checkUser.getRoleId() == 1) {
     session.setAttribute("roleId", 1);
     return "1";
     } else {
     return "0";
     }
     } else {
     return "yes";
     }
     }
     */
    /**
     * 功能描述： TODO[ 登录校验 cookie/IO ]
     * @auther: Zuan~
     * @date: 2020/8/19  17:13
     * @param: user
     * @return: 0: 普通用户、1：管理员、2：账号或密码错误
     * @return： String

     @RequestMapping("/denglu") public String denglu(String account, String passWord, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
     User user = new User();
     user.setAccount(account);
     user.setPassWord(passWord);
     User checkUser = userService.checkUser(user);

     Integer roleId =  checkUser.getRoleId();
     String roleId1 = Integer.toString(roleId);
     //校验账号和密码是否正确
     if (checkUser != null) {
     //判断登录的是用户还是管理员
     if (checkUser.getRoleId() == null) {
     return "5";
     } else if (checkUser.getRoleId() == 2) {
     //Cookie（名称，值）
     Cookie ck = new Cookie("name", roleId1);
     ck.setMaxAge(30);
     response.addCookie(ck);
     return "2";
     } else if (checkUser.getRoleId() == 1) {
     Cookie[] cook = request.getCookies();
     for (Cookie co:cook){
     //获取所有的 name value
     //Cookie(a,b)   a= co.getName()
     //获取所有 cookie 的名称
     String cookiename = co.getName();
     if ("name".equals(cookiename)){
     String val = co.getValue();
     roleId1 = val;
     }
     }
     return "1";
     } else {
     return "0";
     }
     } else {
     return "yes";
     }
     }
     */
    /**
     * 功能描述： TODO[ 登录校验 shiro]
     *
     * @auther: Zuan~
     * @date: 2020/8/22 14：16
     * @param: user
     * @return: 0: 普通用户、1：管理员、2：账号或密码错误
     * @return： String
     */
    @RequestMapping("/denglu")
    public String denglu(String account, String passWord, Model model,HttpServletRequest request) {
        User user = new User();
        user.setAccount(account);
        user.setPassWord(passWord);
        User checkUser = userService.checkUser(user);

        //使用 shiro 编写认证操作
        //1、 获取 Subject
        Subject subject = SecurityUtils.getSubject();
        //保存登录信息
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();


        Integer roleId = checkUser.getRoleId();
        Long id = checkUser.getId();

        //存储到session 中
        HttpSession session = request.getSession();
        session.setAttribute("checkUser", checkUser);
        session.setAttribute("id",id );
        session.setAttribute("roleId1", roleId);

        //2、 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(account, passWord);

        //3、 登录判断
        if (checkUser != null) {
            //判断登录的是用户还是管理员
            if (checkUser.getRoleId() == null) {
                return "5";
            } else if (checkUser.getRoleId() == 4) {
                return "4";
            } else if (checkUser.getRoleId() == 3) {
                return "3";
            } else if (checkUser.getRoleId() == 2) {
                return "2";
            } else if (checkUser.getRoleId() == 1) {
                return "1";
            } else {
                return "0";
            }
        }
        return "yes";
    }



        /**
         * 功能描述： TODO[ 注册 ]
         *
         * @auther: Zuan~
         * @date: 2020/8/20  14:17
         * @param:
         * @return:
         */
        @RequestMapping("/saveUser")
        public String saveUser (String userName, String account, String passWord){
            User user = new User();
            user.setUserName(userName);
            user.setAccount(account);
            user.setPassWord(passWord);

            boolean a = userService.save(user);
            if (a == true) {
                return "yes";
            }
            return "no";
        }


        /**
         * 功能描述： TODO[ 获取用户角色 ] 判断用户是否登录，如果登录则判断用户角色
         *
         * @auther: Zuan~
         * @date: 2020/8/21  15:22
         * @param: 1-普通用户，2-管理员 5-未登录
         * @return: String
         */
        @RequestMapping("/getUserRole")
        public String getUserRole (HttpSession session){
            Integer role = (Integer) session.getAttribute("roleId");
            if (role == null) {
                return "5";
            } else if (role == 1) {

                return "1";
            } else {
                return "2";
            }

        }
    }

