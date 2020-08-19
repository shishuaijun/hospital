package com.follow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.follow.entity.TreatmentPlan;

import java.util.List;

/**
 * @author ssj
 * @date 2020/8/10
 */
public interface TreatmentPlanService extends IService<TreatmentPlan> {

    List<TreatmentPlan> getBydid(Integer diseaseId);

    int saveDiseaseTreatment(String treatname, String medication, String examination, String treatment, String operation, String notice, String suggestion, Integer diseaseId);


}
