package com.follow.service;

import com.follow.entity.Followgroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface FollowgroupService extends IService<Followgroup>{

    void insert(Followgroup followgroup);

}
