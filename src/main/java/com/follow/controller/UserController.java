package com.follow.controller;


import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.entity.User;
import com.follow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param id
     * @return
     */
    @RequestMapping("getUserAll")
    public JSONResult getUserAll(@RequestParam(value = "id", required = false) String id) {
        JSONResult jsonResult = null;
        try {
            Map map = new HashMap();
            if (!id.equals("0") && id != null){
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
    public JSONResult getUserByUserName(@RequestParam("userName") String userName){
        JSONResult jsonResult = null ;
        try{
            Map map = new HashMap();
            if (userName != null){
                map.put("user_name",userName);
            }
            List<User> list = userService.listByMap(map);
            jsonResult = new JSONResult(ResultEum.SUCCESS,(long)list.size(),list);
        }catch(Exception e){
            jsonResult = new JSONResult(ResultEum.ERROR,0L,null);
        }
        return jsonResult ;
    }
}

