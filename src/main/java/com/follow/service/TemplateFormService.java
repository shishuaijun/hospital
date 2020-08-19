package com.follow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.follow.entity.TemplateForm;

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
     * 添加 模板信息
     * @param id
     * @param text
     * @return
     */
    boolean updateMessageTemplate(Integer id, String text);

}
