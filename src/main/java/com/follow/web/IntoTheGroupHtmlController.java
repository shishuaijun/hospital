package com.follow.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 入组管理  页面跳转 控制层
 * @author wangchunjun
 * @date 2020/8/6
 */
@Controller
public class IntoTheGroupHtmlController {

    /**
     * 跳转到  入组范围  页
     * @return
     */
    @RequestMapping("/grouprange")
    public String grouprange(){
        return "intogroup/grouprange";
    }
    /**
     * 跳转到  入组时间  页
     * @return
     */
    @RequestMapping("/grouptime")
    public String grouptime(){
        return "intogroup/grouptime";
    }
    /**
     * 跳转到  自定义患者或导入文件入组  页
     * @return
     */
    @RequestMapping("/groupcustom")
    public String groupCustom(){
        return "intogroup/groupcustom";
    }
    /**
     * 自定义患者 页
     * @return
     */
    @RequestMapping("/custompatient")
    public String customPatient(){
        return "intogroup/custompatient";
    }
    /**
     * 导入患者 页
     * @return
     */
    @RequestMapping("/importpatient")
    public String importPatient(){
        return "intogroup/importpatient";
    }
    /**
     * 按 科室 页
     * @return
     */
    @RequestMapping("/ondesk")
    public String onDesk(){
        return "intogroup/ondesk";
    }

    /**
     * 按  病种  页
     * @return
     */
    @RequestMapping("/onentity")
    public String onEntity(){
        return "intogroup/onentity";
    }

    /**
     * 按  人员  页
     * @return
     */
    @RequestMapping("/onstaff")
    public String onStaff(){
        return "intogroup/onstaff";
    }

    /**
     * 按  自定义条件  页
     * @return
     */
    @RequestMapping("/oncustom")
    public String oncustom(){
        return "intogroup/oncustom";
    }

    /**
     * 按  结果集  页
     * @return
     */
    @RequestMapping("/onresult")
    public String onResult(){
        return "intogroup/onresult";
    }


    /**
     * 重定向 随访组
     * @return
     */
    @RequestMapping("/returntogroup")
    public String returnToGroup(){
        return "redirect:/set_groups";
    }

}
