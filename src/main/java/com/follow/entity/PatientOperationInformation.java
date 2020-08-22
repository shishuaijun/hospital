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
@TableName(value = "patient_operation_information")
public class PatientOperationInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "patient_id")
    private Integer patientId;

    @TableField(value = "term_of_operation")
    private String termOfOperation;

    @TableField(value = "date_of_surgery")
    private LocalDateTime dateOfSurgery;

    @TableField(value = "operation_department")
    private Integer operationDepartment;

    @TableField(value = "operation_start_time")
    private LocalDateTime operationStartTime;

    @TableField(value = "operation_stop_time")
    private LocalDateTime operationStopTime;

    @TableField(value = "surgical_spot")
    private String surgicalSpot;

    private String technique;

    private String operator;
}
