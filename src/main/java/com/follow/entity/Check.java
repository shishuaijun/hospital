package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangchunjun
 * @date 2020/8/18
 */
@Data
@TableName(value = "check_msg")
public class Check implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    @TableField(value="patient_id")
    private  Integer patientId;

    private  String height;

    private  String weigth;

    private  String abbr;

    private  String hemameba;

    private  String glucometer;

}

