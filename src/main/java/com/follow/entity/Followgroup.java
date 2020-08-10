package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Data
public class Followgroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 随访组名称
     */
    @TableField(value = "f_name")
    private String fName;

    /**
     * 医院id
     */
    @TableField(value = "hospital_id")
    private Integer hospitalId;

    /**
     * 负责人
     */
    @TableField(value = "department_person")
    private Integer departmentPerson;

    /**
     * 科室id
     */
    @TableField(value = "department_id")
    private Integer departmentId;

    /**
     * 状态（0：开放、1：关闭）
     */
    private Integer fstate;

    /**
     * 对照小组
     */
    private Integer contrastgroup;

    /**
     * 级别（1：一级、2：二级、3：三级）
     */
    private Integer flevel;

    /**
     * 联系电话
     */
    private String fphone;

    /**
     * 课题编号
     */
    @TableField(value = "topic_id")
    private Integer topicId;

    /**
     * 患者数量
     */

    private Integer patientsnumber;

    /**
     * 背景
     */
    private String fbackground;

    /**
     * 开始时间
     */
    @TableField(value = "fstrat_time")
    private Date fstratTime;

    /**
     * 结束时间
     */
    @TableField(value = "fend_time")
    private Date fendTime;

    /**
     * 用户id，创建人id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",select = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time",select = false)
    private Date modifyTime;

    /**
     * 是否删除（0：否，1：是）
     */
    @TableField(value = "is_delete",select = false)
    @TableLogic
    private Integer isDelete;


}
