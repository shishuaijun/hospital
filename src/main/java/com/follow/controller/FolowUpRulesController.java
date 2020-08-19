package com.follow.controller;

import com.alibaba.fastjson.JSON;
import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.dto.DataUtil;
import com.follow.entity.FollowUpRules;
import com.follow.entity.JoinGroupProgress;
import com.follow.entity.TreatmentPlan;
import com.follow.service.FollowUpPorgressService;
import com.follow.service.FollowUpRulesService;
import com.follow.service.TreatmentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/14
 */
@RestController
public class FolowUpRulesController {

    @Autowired
    private FollowUpRulesService followUpRulesService;
    @Autowired
    private FollowUpPorgressService followUpPorgressService;
    @Autowired
    private TreatmentPlanService treatmentPlanService;

    /**
     * 获取 随访 规则
     * @return
     */
    @PostMapping("/getFollowUpRulesList")
    public JSONResult getFollowUpRulesList(){
        List<FollowUpRules> list = followUpRulesService.list();
        return new JSONResult(ResultEum.SUCCESS,(long)list.size(),list);
    }

    /**
     * 添加 随访规则
     * @param followUpRules
     * @return
     */
    @PostMapping("/addfollowuprules")
    public String savefollowuprules(FollowUpRules followUpRules){
        boolean save = false;
        try {
            save = followUpRulesService.save(followUpRules);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(save);
        }
    }

    @PostMapping("/getprogress")
    public DataUtil<JoinGroupProgress> getprogress(Integer id){

        DataUtil<JoinGroupProgress> dataUtil = followUpPorgressService.selectjoinGroupProgress(id);

        return dataUtil;
    }
    @PostMapping("/gettemplateplan")
    public DataUtil<TreatmentPlan> gettemplateplan(){
        List<TreatmentPlan> treatmentPlans = treatmentPlanService.list();
        DataUtil<TreatmentPlan> treatmentPlanDataUtil = new DataUtil<>();
        treatmentPlanDataUtil.setData(treatmentPlans);
        return treatmentPlanDataUtil;
    }


}
