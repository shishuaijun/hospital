package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangchunjun
 * @date 2020/8/10
 */
@Data
@TableName(value = "patient_admission_information")
public class PatientAdmissionInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "patient_id")
    private Integer patientId;

    private String diagnosis;

    @TableField(value = "hospital_way")
    private Integer hospitalWay;

    private Integer desk;
    @TableField(value = "hospital_days")
    private Integer hospitalDays;

    @TableField(value = "admission_time")
    private LocalDateTime admissionTime;

    @TableField(value = "discharge_time")
    private LocalDateTime dischargeTime;

    @TableField(value = "is_die")
    private Integer  isDie;

}
