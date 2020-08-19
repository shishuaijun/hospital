package com.follow.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.follow.common.EmptyUtils;
import com.follow.entity.TemplateCommon;
import com.follow.entity.TemplateForm;
import com.follow.mapper.TemplateCommonMapper;
import com.follow.mapper.TemplateFormMapper;
import com.follow.service.TemplateFormService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
        int i = templateFormMapper.updateById(templateForm1);
        return i > 0;
    }


    /**
     * 功能描述： TODO[ 模糊查询 + 分页 ]
     * @auther:  Zuan~
     * @date:  2020/8/17  16:31
     * @param:
     * @return:
     */
    @Override
    public PageInfo<TemplateForm> findByConditions( String name, String modifyMan, Integer page, Integer limit) {

        PageHelper.startPage(page, limit);
        List<TemplateForm> list = templateFormMapper.findByConditions(name,modifyMan, page, limit);

        PageInfo<TemplateForm> pageInfo = new PageInfo<TemplateForm>(list);

        return pageInfo;
    }

    @Override
    public boolean savemessagetemplate(String name,String text) {
        TemplateForm templateForm1 = new TemplateForm();
        templateForm1.setName(name);
        templateForm1.setBasicsContent(text);
        templateForm1.setCreationTime(LocalDateTime.now());
        templateForm1.setModifyTime(LocalDateTime.now());
        templateForm1.setModifyMan("当前登录用户");

        int i = templateFormMapper.insert(templateForm1);
        return i > 0;
    }
}
