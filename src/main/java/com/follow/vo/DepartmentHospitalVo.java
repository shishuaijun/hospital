package com.follow.vo;

import lombok.Data;

@Data
public class DepartmentHospitalVo {
    //科室id
    private Integer id ;
    //科室名称
    private String departmentName;
    //医院名称
    private String hospitalName;
    //医院id
    private Integer hospitalId ;
    //科室负责人
    private String departmentBoss ;

}
