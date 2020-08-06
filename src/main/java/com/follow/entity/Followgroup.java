package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Followgroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 随访组名称
     */
    private String fName;

    /**
     * 医院id
     */
    private Integer hospitalId;

    /**
     * 负责人
     */
    private Integer departmentPerson;

    /**
     * 科室id
     */
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
    private LocalDateTime fstratTime;

    /**
     * 结束时间
     */
    private LocalDateTime fendTime;

    /**
     * 用户id，创建人id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否删除（0：否，1：是）
     */
    private Integer isDelete;


}
