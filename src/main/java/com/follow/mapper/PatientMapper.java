package com.follow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.entity.Patient;
import com.follow.vo.PatientUserVo;
import org.apache.ibatis.annotations.Param;
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

    @Select("select u.id,u.user_name as userName,d.disease_name as patientName,d.disease_number as diseaseNumber,dt.department_name as departmentName ,d.symptom ,p.create_time as newDate from patient p,patient_controlk pc,disease d,`user` u,department dt where u.id = pc.d_n_p_id AND pc.patient_id = p.id AND p.disease_id = d.id AND p.department_id = dt.id AND u.id = #{userId}")
    PatientUserVo selectPatientUserVoByUserId(@Param("userId") String userId);

    @Select("SELECT count(1) FROM follow_up_plan f ,patient p WHERE f.`patient_id`=p.`id` AND f.plan_date=#{date}")
    Integer selectCountsum(String date);

    @Select("SELECT count(1)  FROM follow_up_plan f ,patient p WHERE f.`patient_id`=p.`id` AND f.`plan_state`=1  AND f.plan_date=#{date}")
    Integer selectCountState(String date);

    @Select("SELECT DISTINCT f.`plan_date` actual FROM follow_up_plan f ,patient p WHERE f.`patient_id`=p.`id`")
    List<CalendarVO> selectNumList();

    @Select(" SELECT p.`patient_name` progress FROM follow_up_plan f ,patient p WHERE f.`patient_id`=p.`id` AND f.plan_date=#{date}")
    List<CalendarVO> selectPatientNameList(String date);
}
