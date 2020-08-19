package com.follow.service;

import com.follow.entity.Permissions;
import com.follow.vo.PermissionsFollowgroupVo;

import java.util.List;

public interface PermissionsFollowgroupVoService {

    /**
     * 修改 随访表
     * @param id
     * @param permissionsPerson
     * @param permissionsName
     * @param fphone
     * @param level
     * @param roleName
     * @param jurisdiction
     * @return
     */
    String updatePermissions(String id ,
                             String permissionsPerson,
                             String permissionsName,
                             String fphone,
                             String level,
                             String roleName,
                             String jurisdiction) ;

    List<PermissionsFollowgroupVo> queryPermissionsByRoleNameRemarks(String roleName,
                                                                     String remarks);

}
