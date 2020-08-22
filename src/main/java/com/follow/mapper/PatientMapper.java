package com.follow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.entity.Patient;
import org.apache.ibatis.annotations.Select;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface PatientMapper extends BaseMapper<Patient> {

    @Select("SELECT * FROM patient where id =#{id}")
    Patient selectByokId(Integer id);
}
