package com.follow.service.impl;

import com.follow.entity.Followgroup;
import com.follow.mapper.FollowgroupMapper;
import com.follow.service.FollowgroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Service
public class FollowgroupServiceImpl extends ServiceImpl<FollowgroupMapper, Followgroup> implements FollowgroupService {

    @Resource
    private FollowgroupMapper followgroupMapper;

    @Override
    public void insert(Followgroup followgroup) {
        followgroupMapper.insert(followgroup);
    }

}
