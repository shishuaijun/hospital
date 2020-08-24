package com.follow.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 表单模板设置  页面跳转 控制层
 * @author wangchunjun
 * @date 2020/8/13
 */
@Controller
public class TemplateHtmlController {

    /**
     * 跳转 到引用模板
     * @return
     */
    @RequestMapping("/referTemplate")
    public String referTemplate(){
        return "templateSettings/refer_template";
    }

    /**
     * 模板 编辑页
     * @return
     */
     @RequestMapping("/editTemplate")
    public String editTemplate(Integer id){
        return "templateSettings/edit_template";
    }

    @RequestMapping("/follow_up_list")
    public String followUpList(){
        return "templateSettings/follow_up_list";
    }

    @RequestMapping("/choiceoftreatment")
    public String choiceoftreatment(){
        return "templateSettings/choiceoftreatment";
    }

    @RequestMapping("/basic_data")
    public String basic_data(){
        return "templateSettings/basic_data";
    }

}
