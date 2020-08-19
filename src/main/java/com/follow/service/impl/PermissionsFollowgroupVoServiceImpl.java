package com.follow.service.impl;

import com.follow.entity.Permissions;
import com.follow.mapper.PermissionsFollowgroupVoMapper;
import com.follow.service.PermissionsFollowgroupVoService;
import com.follow.vo.PermissionsFollowgroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionsFollowgroupVoServiceImpl implements PermissionsFollowgroupVoService {

    @Autowired
    private PermissionsFollowgroupVoMapper permissionsFollowgroupVoMapper ;

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
    @Override
    @Transactional
    public String updatePermissions(String id ,
                                    String permissionsPerson,
                                    String permissionsName,
                                    String fphone,
                                    String level,
                                    String roleName,
                                    String jurisdiction){
        Map map = new HashMap();
        map.put("id",id);
        map.put("permissionsPerson",permissionsPerson);
        map.put("permissionsName",permissionsName);
        map.put("fphone",fphone);
        map.put("level",level);
        map.put("roleName",roleName);
        map.put("jurisdiction",jurisdiction);

        Integer integer = permissionsFollowgroupVoMapper.updatePermissionsByFollowgroup(map);
        if (integer>0){
            return "修改成功";
        }else{
            return "网络异常";
        }
    }

    @Override
    public List<PermissionsFollowgroupVo> queryPermissionsByRoleNameRemarks(String roleName, String remarks) {


        return null;
    }

}
