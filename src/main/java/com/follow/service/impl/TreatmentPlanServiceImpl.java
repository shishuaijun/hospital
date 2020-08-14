package com.follow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.follow.entity.TreatmentPlan;
import com.follow.mapper.TreatmentPlanMapper;
import com.follow.service.TreatmentPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ssj
 * @date 2020/8/10
 */
@Service
public class TreatmentPlanServiceImpl extends ServiceImpl<TreatmentPlanMapper, TreatmentPlan> implements TreatmentPlanService {

    @Resource
    private TreatmentPlanMapper treatmentPlanMapper;


    @Override
    public List<TreatmentPlan> getBydid(Integer diseaseId) {
        return treatmentPlanMapper.getBydid(diseaseId);
    }

    @Override
    public int saveDiseaseTreatment(String treatname, String medication, String examination, String treatment, String operation, String notice, String suggestion, Integer diseaseId) {
        return treatmentPlanMapper.saveDiseaseTreatment(treatname,medication,examination,treatment,operation,notice,suggestion,diseaseId);
    }
}
