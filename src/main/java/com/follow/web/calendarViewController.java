package com.follow.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 日历视图 页面跳转 控制器
 * @author wangchunjun
 * @date 2020/8/24
 */
@Controller
public class calendarViewController {

    @RequestMapping("/calendarview2")
    public String calendarVie(){
        return "calendarView/calendar_view";
    }

}
