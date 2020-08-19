package com.follow.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class PermissionsFollowgroupVo {

    //随访id
    private Integer id;
    //角色级别
    private String level ;
    //角色名称
    private String roleName ;
    //用户名称
    private String userName ;
    //权限
    private String jurisdiction ;
    //联系人
    private String fPhone ;
    //随访名称
    private String permissionsPerson ;
    //随访负责人
    private String permissionsName ;
    //科室id
    private Integer departmentId;
    //科室名称
    private String departmentName;
}
