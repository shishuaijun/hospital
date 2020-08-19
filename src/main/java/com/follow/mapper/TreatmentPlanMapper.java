package com.follow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.entity.TreatmentPlan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ssj
 * @date 2020/8/10
 */
public interface TreatmentPlanMapper extends BaseMapper<TreatmentPlan> {


    @Select("select a.* from treatment_plan a,followgroup b,disease c where a.disease_id = b.disease_id and a.disease_id = c.id and a.disease_id = #{diseaseId}")
    List<TreatmentPlan> getBydid(Integer diseaseId);


    @Insert("insert into treatment_plan(treatname,medication,examination,treatment,operation,notice,suggestion,disease_id) values( #{treatname},#{medication},#{examination},#{treatment},#{operation},#{notice},#{suggestion}, #{disease_id})")
    int saveDiseaseTreatment(@Param("treatname") String treatname,@Param("medication") String medication,@Param("examination") String examination,@Param("treatment") String treatment,@Param("operation") String operation,@Param("notice") String notice,@Param("suggestion") String suggestion, @Param("disease_id") Integer diseaseId);
}
