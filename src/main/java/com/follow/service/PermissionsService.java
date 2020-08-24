package com.follow.service;

import com.follow.entity.Permissions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.follow.vo.PermissionsFollowgroupVo;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface PermissionsService extends IService<Permissions> {

    List<PermissionsFollowgroupVo>  queryPermissionsFollowgroup(Integer page , Integer limit,String userName, String remarks);

    Integer queryPermissionsFollowgroupSize();

    PermissionsFollowgroupVo queryPermissionsById(String id);
}
