package com.follow.service.impl;

import com.follow.entity.Permissions;
import com.follow.mapper.PermissionsMapper;
import com.follow.service.PermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.follow.vo.PermissionsFollowgroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Override
    public List<PermissionsFollowgroupVo> queryPermissionsFollowgroup(Integer page, Integer limit,String userName , String remarks) {
        Integer topPage = (page-1)*limit;
        List<PermissionsFollowgroupVo> permissionsFollowgroupVos = new ArrayList<>();
        if (userName == null){
            userName = "";
        }
        if (remarks == null){
            remarks = "" ;
        }
        for (PermissionsFollowgroupVo vo : permissionsMapper.selectPermissionsFollowgroupVo(topPage, limit,userName,remarks)) {
            switch (vo.getLevel()){
                case "1":
                    vo.setLevel("一级");
                    break;
                case "2":
                    vo.setLevel("二级");
                    break;
                case "3":
                    vo.setLevel("三级");
                    break;
                default:
                    vo.setLevel("等级过高");
                    break;
            }
            permissionsFollowgroupVos.add(vo);
        }
        return permissionsFollowgroupVos;
    }

    @Override
    public Integer queryPermissionsFollowgroupSize(){
        return permissionsMapper.selectPermissionsFollowgroupSize();
    }

    @Override
    public PermissionsFollowgroupVo queryPermissionsById(String id) {
        PermissionsFollowgroupVo permissionsFollowgroupVo = permissionsMapper.selectPermissionsByid(id);
        return permissionsFollowgroupVo;
    }
}
