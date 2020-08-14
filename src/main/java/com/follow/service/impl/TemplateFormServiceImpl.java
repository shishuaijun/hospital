package com.follow.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.follow.common.EmptyUtils;
import com.follow.entity.TemplateCommon;
import com.follow.entity.TemplateForm;
import com.follow.mapper.TemplateCommonMapper;
import com.follow.mapper.TemplateFormMapper;
import com.follow.service.TemplateFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author wangchunjun
 * @date 2020/8/13
 */
@Service
public class TemplateFormServiceImpl extends ServiceImpl<TemplateFormMapper , TemplateForm> implements TemplateFormService {

    @Autowired
    private TemplateFormMapper templateFormMapper;
    @Autowired
    private TemplateCommonMapper templateCommonMapper;

    @Override
    public boolean saveTemplate(String ids, String name) {
        if(EmptyUtils.isEmpty(name)){
            name ="信息名称";
        }
        String[] split = ids.split(",");

        TemplateForm templateForm = new TemplateForm();
        templateForm.setName(name);
        templateForm.setVersion(001);
        templateForm.setState(3);
        templateForm.setModifyMan("当前登录用户");
        templateForm.setCreationTime(LocalDateTime.now());
        templateForm.setModifyTime(LocalDateTime.now());
        templateForm.setIsInputData(0);
        templateForm.setIsDelete(0);
        String next="";
        String next2="";
        for (String s : split) {
            if("1".equals(s)){
                TemplateCommon templateCommon = templateCommonMapper.selectById(s);
                next = templateCommon.getContents();
            }else {
                TemplateCommon templateCommon = templateCommonMapper.selectById(s);
                next2 += templateCommon.getContents();
            }
        }
        templateForm.setBasicsContent(next);
        templateForm.setContent(next2);
        int insert = templateFormMapper.insert(templateForm);
        return insert > 0;
    }

    @Override
    public boolean updateMessageTemplate(Integer id, String text) {
        TemplateForm templateForm = templateFormMapper.selectById(id);
        String content= templateForm.getBasicsContent()+text;
        TemplateForm templateForm1 = new TemplateForm();
        templateForm1.setBasicsContent(content);
        templateForm1.setId(id);
        templateForm1.setModifyTime(LocalDateTime.now());
        templateForm1.setModifyMan("当前登录用户");
        int i = templateFormMapper.update(templateForm1, null);
        return i > 0;
    }
}
