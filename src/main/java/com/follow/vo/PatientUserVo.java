package com.follow.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import groovy.transform.TimedInterrupt;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PatientUserVo {

    private Integer id ;
//    用户名称
    private String userName ;
//    疾病名称
    private String patientName;
//    疾病编号
    private String diseaseNumber ;
//    科室名称
    private String departmentName ;
//    内容
    private String symptom ;
//    创建时间
    private String newDate ;

}
