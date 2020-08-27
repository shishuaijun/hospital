package com.follow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.entity.Followgroup;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface FollowgroupMapper extends BaseMapper<Followgroup> {



    @Select("select * from followgroup")
    List<Followgroup> selectAll();

    @Select("SELECT f_name,user_id FROM followgroup WHERE user_id = #{userId}")
    List<Followgroup> selectByUserIdList(Integer userId);

}
