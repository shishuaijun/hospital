package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Data
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 患者名称
     */
    @TableField(value = "patient_name")
    private String patientName;

    /**
     * 科室id
     */
    @TableField(value = "department_id")
    private Integer departmentId;

    /**
     * 疾病id
     */
    @TableField(value = "disease_id")
    private Integer diseaseId;

    /**
     * 住院号
     */
    private String adminssionnumber;

    /**
     * 门诊号
     */
    private String outpaientnumber;

    /**
     * 性别（1：男，0：女）
     */
    private Integer sex;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 出生地
     */
    private String address;

    /**
     * 学历文化程度id
     */
    @TableField(value = "education_id")
    private Integer educationId;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 血型
     */
    private String bloodtype;

    /**
     * 联系人
     */
    private String link;

    /**
     * 身份证号
     */
    private String idnumber;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",select = false)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time",select = false)
    private LocalDateTime modifyTime;

    /**
     * 是否入组（1：是，0：否）
     */
    @TableField(value = "is_joingroup",select = false)
    @TableLogic
    private Integer isJoingroup;


}
