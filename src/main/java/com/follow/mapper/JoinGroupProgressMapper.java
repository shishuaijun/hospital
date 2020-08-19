package com.follow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.entity.JoinGroupProgress;
import org.apache.ibatis.annotations.Select;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface JoinGroupProgressMapper extends BaseMapper<JoinGroupProgress> {

    @Select("SELECT id,patient_id,next_date,treatment_scheme treatmentScheme  FROM join_group_progress WHERE patient_id = #{id}")
    JoinGroupProgress selectjoinGroupProgress(Integer id);
}
