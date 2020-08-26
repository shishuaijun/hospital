package com.follow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.entity.Patient;
import com.follow.vo.CalendarVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface PatientMapper extends BaseMapper<Patient> {

    @Select("SELECT * FROM patient where id =#{id}")
    Patient selectByokId(Integer id);

    @Select("SELECT count(1) FROM follow_up_plan f ,patient p WHERE f.`patient_id`=p.`id` AND f.plan_date=#{date}")
    Integer selectCountsum(String date);

    @Select("SELECT count(1)  FROM follow_up_plan f ,patient p WHERE f.`patient_id`=p.`id` AND f.`plan_state`=1  AND f.plan_date=#{date}")
    Integer selectCountState(String date);

    @Select("SELECT DISTINCT f.`plan_date` actual FROM follow_up_plan f ,patient p WHERE f.`patient_id`=p.`id`")
    List<CalendarVO> selectNumList();

    @Select(" SELECT p.`patient_name` progress FROM follow_up_plan f ,patient p WHERE f.`patient_id`=p.`id` AND f.plan_date=#{date}")
    List<CalendarVO> selectPatientNameList(String date);
}
