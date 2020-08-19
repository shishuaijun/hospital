package com.follow.mapper;

import com.follow.vo.FollowUpProgressVO;
import com.follow.vo.FollowUpResultVO;
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
    //    @Select("SELECT j.group_name groupName,group_time groupTime,o.condition,next_date nexDate,p.id ,patient_name name,adminssionnumber,outpaientnumber,sex,birthday  FROM join_group j,join_group_progress o,patient p where p.is_joingroup = 0 and p.id = j.patient_control_id and p.id  =o.patient_id ")
//    List<FollowUpProgressVO> selectList();
          /*  "and j.group_time BETWEEN '#{beginDate}' AND '#{endDate}'" +
            "and o.next_date BETWEEN '#{beginDate}' AND '#{endDate}'"*/

           /* "and p.department_id = " +

            "and p.adminssionnumber =" +
            "and p.patient_name =" +*/
//            "and o.condition = #{condition} "
        /*    +
            " LIMIT 0 ,2"*/
//           )
//    List<FollowUpProgressVO> selectList(@Param("groupName") String groupName);
   /* @SelectProvider(type = listProvider.class, method = "selectList")*/
    /*List<FollowUpProgressVO> selectList(@Param("beginDate") String beginDate,
                                        @Param("endDate") String endDate,
                                        @Param("state") Integer state);*/

    /*class listProvider {
        public String selectList(@Param("beginDate") String beginDate, @Param("endDate") String endDate) {
            String sql = "SELECT j.group_name groupName,group_time groupTime,o.state,next_date nexDate,p.id ,patient_name name,adminssionnumber,outpaientnumber,sex,birthday  FROM join_group j,join_group_progress o,patient p where p.is_joingroup = 0 and p.id = j.patient_control_id and p.id  =o.patient_id";
            if(beginDate!=null && endDate !=null) {
                sql += "and j.group_time BETWEEN '#{beginDate}' AND '#{endDate}'";
            }
            return sql;
        }
    }*/
}

