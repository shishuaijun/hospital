package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 石帅军
 * @date 2020/8/10
 */
@Data
public class TreatmentPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 诊疗方案名称
     */
    @TableField(value="treatname")
    private String treatname;

    /**
     * 用药
     */
    @TableField(value="medication")
    private String medication;

    /**
     * 检查
     */
    @TableField(value="examination")
    private String examination;

    /**
     * 治疗
     */
    @TableField(value="treatment")
    private String treatment;

    /**
     * 手术
     */
    @TableField(value="operation")
    private String operation;

    /**
     * 通知
     */
    @TableField(value="notice")
    private String notice;

    /**
     * 建议
     */
    @TableField(value="suggestion")
    private String suggestion;

    /**
     * 疾病id
     */
    @TableField(value = "disease_id")
    private Integer diseaseId;

}
