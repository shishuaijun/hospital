package com.follow.mapper;

import com.follow.entity.Permissions;
import com.follow.vo.PermissionsFollowgroupVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionsFollowgroupVoMapper {

    /**
     * 修改随访表
     * @param map
     * @return
     */
    @Update("update permissions p , followgroup f set " +
            "f.f_name= #{permissionsPerson} ,f.contrastgroup = #{permissionsName} ,f.fphone =  #{fphone} ,f.flevel =  #{level} , p.role_name= #{roleName} ,p.jurisdiction =  #{jurisdiction} " +
            "where f.id = #{id} and p.followgroup_id  = f.id ")
    Integer updatePermissionsByFollowgroup(Map map);


}
