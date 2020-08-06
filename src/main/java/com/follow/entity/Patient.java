package com.follow.entity;

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
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 患者名称
     */
    private String patientName;

    /**
     * 科室id
     */
    private Integer departmentId;

    /**
     * 疾病id
     */
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
    private Integer educationId;

    /**
     * 电话
     */
    private Integer phone;

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
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否入组（1：是，0：否）
     */
    private Integer isJoingroup;


}
