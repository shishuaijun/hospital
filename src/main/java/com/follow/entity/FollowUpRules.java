package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangchunjun
 * @date 2020/8/14
 */
@Data
@TableName(value ="follow_up_rules")
public class FollowUpRules implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "benchmark_events")
    private String benchmarkEvents;

    @TableField(value = "condition_one")
    private String conditionOne;

    @TableField(value = "condition_two")
    private String conditionTwo;

    @TableField(value = "condition_three")
    private String conditionThree;

    @TableField(value = "begin_month")
    private Integer beginMonth;

    @TableField(value = "interval_month")
    private Integer intervalMonth;

    private Integer continued;

    private Integer notice;

    @TableField(value = "template_id")
    private Integer templateId;


}
