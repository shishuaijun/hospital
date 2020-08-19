package com.follow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.entity.Dictionaries;
import com.follow.entity.Followgroup;
import com.follow.entity.TemplateForm;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ssj
 * @date 2020/8/14
 */
public interface DictionariesMapper extends BaseMapper<Dictionaries> {


    List<Dictionaries> findByConditions1(
            @Param("dictionariesName") String dictionariesName,
            @Param("dictionariesCod")String dictionariesCod,
            @Param("dictionariesCodName")String dictionariesCodName,
            @Param("shunxu")String shunxu,
            @Param("page")Integer page,
            @Param("limit")Integer limit);
}
