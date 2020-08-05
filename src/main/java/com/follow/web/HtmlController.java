package com.follow.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面 控制器
 * @author wangchunjun
 * @date 2020/8/5
 */
@Controller
public class HtmlController {

    /**
     * 跳转到主页
     * @return index.html
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    /**
     * 跳转到欢迎页
     * @return welcome.html
     */
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 跳转到  页
     * @return
     */
    @RequestMapping("/createnewset")
    public String createnewset(){
        return "createnewset";
    }
    /**
     * 跳转到  页
     * @return
     */
    @RequestMapping("/groupmanagement")
    public String groupmanagement(){
        return "groupmanagement";
    }
    /**
     * 跳转到  页
     * @return
     */
    @RequestMapping("/schedule")
    public String schedule(){
        return "schedule";
    }
    /**
     * 跳转到  页
     * @return
     */
    @RequestMapping("/record")
    public String record(){
        return "record";
    }
}
