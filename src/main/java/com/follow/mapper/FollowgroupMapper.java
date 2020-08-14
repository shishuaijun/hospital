package com.follow.mapper;

import com.follow.entity.Followgroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface FollowgroupMapper extends BaseMapper<Followgroup> {



    @Select("select * from followgroup")
    List<Followgroup> selectAll();


}
