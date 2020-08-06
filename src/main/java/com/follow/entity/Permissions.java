package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Data
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 权限
     */
    private String jurisdiction;

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
