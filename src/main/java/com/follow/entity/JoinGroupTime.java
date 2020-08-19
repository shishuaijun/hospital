package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wangchunjun
 * @date 2020/8/12
 */
@Data
@TableName(value = "join_group_time")
public class JoinGroupTime {

    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private String standards;

    private Integer basiss;

    private Integer degrees;

    private Integer doctors;

    @TableField(value = "group_date")
    private LocalDateTime groupDate;

}
