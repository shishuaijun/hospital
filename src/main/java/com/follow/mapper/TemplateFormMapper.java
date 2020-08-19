package com.follow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.follow.entity.TemplateForm;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/13
 */
public interface TemplateFormMapper extends BaseMapper<TemplateForm> {

   List<TemplateForm> findByConditions(
                                       @Param("name") String name,
                                       @Param("modifyMan") String modifyMan,
                                       @Param("page") Integer page,
                                       @Param("limit") Integer limit);
   /* List<TemplateForm> findByConditions(@Param("modifyMan") String modifyMan,
                                        @Param("page") Integer page,
                                        @Param("limit") Integer limit);*/
}
