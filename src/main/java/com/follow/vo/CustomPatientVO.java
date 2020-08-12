package com.follow.vo;

import lombok.Data;

/**
 * @author wangchunjun
 * @date 2020/8/10
 */
@Data
public class CustomPatientVO {

    private Integer id;
    private String patientName;
    private Integer departmentId;
    private Integer sex;
    private String birthday;
    private String address;
    private Integer educationId;
    private Integer phone;
    private String email;
    private String bloodtype;
    private String idnumber;
    private String link;

    private  String diagnosis;
    private Integer hospitalWay;
    private Integer desk;
    private Integer hospitalDays;
    private String admissionTime;
    private String dischargeTime;
    private  Integer isDie;

    private String termOfOperation;
    private String dateOfSurgery;
    private Integer operationDepartment;
    private String operationStartTime;
    private String operationStopTime;
    private String surgicalSpot;

}
