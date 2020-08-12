package com.follow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.entity.TreatmentPlan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ssj
 * @date 2020/8/10
 */
public interface TreatmentPlanMapper extends BaseMapper<TreatmentPlan> {


    @Select("select a.* from treatment_plan a,followgroup b where a.disease_id = b.disease_id and a.disease_id = #{diseaseId}")
    List<TreatmentPlan> getBydid(Integer diseaseId);


}
