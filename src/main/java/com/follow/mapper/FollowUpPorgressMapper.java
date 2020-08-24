package com.follow.mapper;

import com.follow.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/17
 */
public interface FollowUpPorgressMapper {

    List<FollowUpProgressVO> selectList(@Param("beginDate") String beginDate,
                                        @Param("endDate") String endDate,
                                        @Param("departmentId") Integer departmentId,
                                        @Param("groupName") String groupName,
                                        @Param("adminssionnumber") Integer adminssionnumber,
                                        @Param("patientName") String patientName,
                                        @Param("condition") Integer condition,
                                        @Param("startPage") Integer startPage,
                                        @Param("limit")Integer limit);

    @Select("SELECT  d.department_name desk,j.group_name name,p.condition state,next_date time FROM join_group j,join_group_progress p,department d,followgroup f WHERE j.patient_control_id = p.patient_id and j.group_name=f.f_name and  f.department_person = d.id")
    List<FollowUpResultVO> selectFollowUPStateList();

    List<FollowUpTheRateVO> getTheRatelist(@Param("principal") String principal,
                                           @Param("desk")  Integer desk,
                                           @Param("state") Integer state,
                                           @Param("beginDate") String beginDate,
                                           @Param("endDate") String endDate,
                                           @Param("startPage") Integer startPage,
                                           @Param("limit")Integer limit);

    List<FollowUpQueryVO> selectQueryList(@Param("page") Integer page,
                                          @Param("limit") Integer limit,
                                          @Param("array") String[] ids);


    @Select("SELECT patient_id id FROM patient_situation WHERE 1=1 ${sb}")
    List<FollowUpQueryVO> getbyPatientId(@Param("sb") String sb);

    @Select("SELECT `table`,`begin`,`end`,count ,ratio FROM `basic_data` where f_id = #{fid}")
    List<BasicDataVO> getbasicDataByIdList(@Param("fid") Integer fid);
}

