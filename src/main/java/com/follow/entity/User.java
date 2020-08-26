package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户姓名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    @TableField(value = "pass_word")
    private String passWord;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;

    /**
     * 所属科室
     */
    @TableField(value = "department_id")
    private Integer departmentId;

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
     * 是否删除（0.否、1.是）
     */
    @TableField(value = "is_delete",select = false)
    @TableLogic
    private Integer isDelete;



}
