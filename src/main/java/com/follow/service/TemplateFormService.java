package com.follow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.follow.entity.TemplateForm;
import com.github.pagehelper.PageInfo;

import java.util.Date;

/**
 * @author wangchunjun
 * @date 2020/8/13
 */
public interface TemplateFormService extends IService<TemplateForm> {

    /**
     * 引用 生成 模板
     * @param ids
     * @param name
     * @return
     */
    boolean saveTemplate(String ids, String name);

    /**
     * 修改 模板信息
     * @param id
     * @param text
     * @return
     */
    boolean updateMessageTemplate(Integer id, String text);


    /**
     * 功能描述： TODO[ 模糊查询 + 分页 ]
     * @auther:  Zuan~
     * @date:  2020/8/17  16:29
     * @param:
     * @return:
     */
    PageInfo<TemplateForm> findByConditions( String name, String modifyMan, Integer page, Integer limit);


    /**
     * 添加 模板信息
     * @param text
     * @return
     */
    boolean savemessagetemplate(String name,String text);
}
