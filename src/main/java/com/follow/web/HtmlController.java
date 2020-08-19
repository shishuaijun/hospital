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
     * 跳转到登录
     * @return login.html
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

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
     * 跳转到 分组管理  页
     * @return
     */
    @RequestMapping("/group_management")
    public String groupmanagement(){
        return "group_management";
    }
    /**
     * 跳转到  建立分组 页
     * @return
     */
    @RequestMapping("/set_groups")
    public String setgroups(){
        return "set_groups";
    }

    /**
     * 跳转到  分组修改 页
     * @return
     */
    @RequestMapping("/group_update")
    public String groupupdate(){
        return "group_update";
    }

    /**
     * 跳转到  组内权限管理  页
     * @return
     */
    @RequestMapping("/group_permission_management")
    public String grouppermissionmanagement(){
        return "group_permission_management";
    }

    /**
     * 跳转到  入组管理  页
     * @return
     */
    @RequestMapping("/join_group")
    public String joingroup(){
        return "join_group";
    }


    /**
     * 跳转到  表单模板设置  页
     * @return
     */
    @RequestMapping("/form_template_setting")
    public String formtemplatesetting(){
        return "form_template_setting";
    }



    /**
     * 跳转到  随访规则设定  页
     * @return
     */
    @RequestMapping("/follow_rules")
    public String followrules(){
        return "follow_rules";
    }

    /**
     * 跳转到  诊疗方案  页
     * @return
     */
    @RequestMapping("/treatment_plan")
    public String treatmentplan(){
        return "treatment_plan";
    }

    /**
     * 跳转到  诊疗方案  2 页
     * @return
     */
    @RequestMapping("/treatment_plan1")
    public String treatmentplan1(){
        return "treatment_plan1";
    }

    /**
     * 跳转到  随访进度管理  页
     * @return
     */
    @RequestMapping("/follow_progress_management")
    public String followprogressmanagement(){
        return "follow_progress_management";
    }


    /**
     * 跳转到  随访记录  页
     * @return
     */
    @RequestMapping("/follow_records")
    public String followrecords(){
        return "follow_records";
    }

    /**
     * 跳转到  随访进度概览  页
     * @return
     */
    @RequestMapping("/overview_progress")
    public String overviewprogress(){
        return "overview_progress";
    }

    /**
     * 跳转到  随访查询  页
     * @return
     */
    @RequestMapping("/follow_records_find")
    public String followrecordsfind(){
        return "follow_records_find";
    }

    /**
     * 跳转到  全院权限维护  页
     * @return
     */
    @RequestMapping("/authority_management_all")
    public String authoritymanagementall(){
        return "authority_management_all";
    }

    /**
     * 跳转到  标准化模板维护  页
     * @return
     */
    @RequestMapping("/standardized_template_maintenance")
    public String standardizedtemplatemaintenance(){
        return "standardized_template_maintenance";
    }


    /**
     * 跳转到  标准化模板添加  页
     * @return
     */
    @RequestMapping("/template_add")
    public String templateadd(){
        return "template_add";
    }


    /**
     * 跳转到  标准化术语维护  页
     * @return
     */
    @RequestMapping("/maintenance_standardized_terms")
    public String maintenancestandardizedterms(){
        return "maintenance_standardized_terms";
    }

    /**
     * 跳转到  CDR字典树建立  页
     * @return
     */
    @RequestMapping("/establishment_cdr")
    public String establishmentcdr(){
        return "establishment_cdr";
    }



    /**
     * 跳转到  CDR字典树建立 增加 页
     * @return
     */
    @RequestMapping("/dictionaries_add")
    public String dictionariesadd(){
        return "dictionaries_add";
    }

    /**
     * 跳转到  CDR字典树建立 修改 页
     * @return
     */
    @RequestMapping("/dictionaries_update")
    public String dictionariesupdate(){
        return "dictionaries_update";
    }


    /**
     * 跳转到  疾病病种数据库  页
     * @return
     */
    @RequestMapping("/disease_type_database")
    public String diseasetypedatabase(){
        return "disease_type_database";
    }


    /**
     * 跳转到  疾病病种数据库对应的添加诊疗方案页  页
     * @return
     */
    @RequestMapping("/disease_treatment_add")
    public String diseasetreatmentadd(){
        return "disease_treatment_add";
    }

    /**
     * 跳转到  疾病病种数据库对应的添加页  页
     * @return
     */
    @RequestMapping("/disease_add")
    public String diseaseadd(){
        return "disease_add";
    }

    /**
     * 跳转到  疾病病种数据库对应的修改页  页
     * @return
     */
    @RequestMapping("/disease_update")
    public String diseaseupdate(){
        return "disease_update";
    }





}
