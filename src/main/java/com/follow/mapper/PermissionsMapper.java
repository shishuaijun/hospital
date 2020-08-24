package com.follow.mapper;

import com.follow.entity.Permissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.vo.PermissionsFollowgroupVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface PermissionsMapper extends BaseMapper<Permissions> {

    List<PermissionsFollowgroupVo> selectPermissionsFollowgroupVo(@Param("page") Integer page , @Param("limit") Integer limit,@Param("userName") String userName,@Param("remarks")String remarks);

    @Select("select count(f.id) from followgroup f where f.is_delete = 0 ")
    Integer selectPermissionsFollowgroupSize();

    @Select("select f.id , f.flevel as level , p.role_name as roleName , GROUP_CONCAT(p.user_name) as userName , p.jurisdiction, f.f_name as permissionsPerson ,f.contrastgroup as permissionsName ,f.fphone as fPhone from followgroup f , permissions p WHERE p.followgroup_id = f.id and f.is_delete = 0 and f.id=#{id} GROUP BY f.id ")
    PermissionsFollowgroupVo selectPermissionsByid(@Param("id") String id);
}
