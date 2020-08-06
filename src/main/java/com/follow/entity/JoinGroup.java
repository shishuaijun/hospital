package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Data
public class JoinGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 入组名称
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 患者id
     */
    @TableField(value = "patientControl_id")
    private String patientControlId;

    /**
     * 入组时间
     */
    @TableField(value = "group_time")
    private LocalDateTime groupTime;


}
