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


}

