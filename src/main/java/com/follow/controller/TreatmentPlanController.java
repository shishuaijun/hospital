package com.follow.controller;


import com.alibaba.fastjson.JSON;
import com.follow.entity.TreatmentPlan;
import com.follow.service.TreatmentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ssj
 * @date 2020/8/6
 */
@Controller
public class TreatmentPlanController {

    @Autowired
    private TreatmentPlanService treatmentPlanService;

    /**
     * 功能描述： TODO[ 根据疾病id 获取诊疗方案 ]
     * @auther:  Zuan~
     * @date:  2020/8/10  17:18
     * @param:      diseaseId
     * @return:     entity
     */
    @RequestMapping("/findBydid")
    @ResponseBody
    public String findBydid(Integer diseaseId){
        System.err.println(diseaseId);
        List<TreatmentPlan> treatmentPlan = treatmentPlanService.getBydid(diseaseId);
        return JSON.toJSONString(treatmentPlan);

    }



    /**
     * 功能描述： TODO[ 增加疾病诊疗方案 ]
     * @auther:  Zuan~
     * @date:  2020/8/14  9:26
     * @param:
     * @return:
     */
    @RequestMapping("/saveDiseaseTreatment")
    @ResponseBody
    public String saveDiseaseTreatment(String treatname,String medication,String examination,String treatment,String operation,String notice,String suggestion,Integer diseaseId){
        int i = treatmentPlanService.saveDiseaseTreatment(treatname,medication,examination,treatment,operation,notice,suggestion,diseaseId);
        if (i >= 1){
            return "yes";
        }
        return "no";
    }


}

