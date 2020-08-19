package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Data
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 科室名称
     */
    @TableField(value="department_name")
    private String departmentName;

    /**
     * 医院id
     */
    @TableField("hospital_id")
    private Integer hospitalId;

    /**
     * 负责人
     */
    @TableField("department_boss")
    private String departmentBoss;

    public Department(Integer id, String departmentName, Integer hospitalId, String departmentBoss) {
        this.id = id;
        this.departmentName = departmentName;
        this.hospitalId = hospitalId;
        this.departmentBoss = departmentBoss;
    }
}
