package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangchunjun
 * @date 2020/8/18
 */
@Data
public class JoinGroupProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "patient_id")
    private Integer patientId;

    @TableField(value = "condition")
    private Integer condition;

    @TableField(value = "next_date")
    private LocalDateTime nextDate;

    @TableField(value = "treatment_scheme")
    private String treatmentScheme;

}
