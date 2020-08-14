package com.follow.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.follow.common.EmptyUtils;
import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.dto.DataUtil;
import com.follow.entity.TemplateCommon;
import com.follow.entity.TemplateForm;
import com.follow.service.TemplateCommonService;
import com.follow.service.TemplateFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 表单模板 控制层
 * @author wangchunjun
 * @date 2020/8/13
 */
@RestController
public class TemplateController {

    @Autowired
    private TemplateFormService templateFormService;
    @Autowired
    private TemplateCommonService templateCommonService;
    /**
     * 获取模板 列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/gettemplate")
    public String getTemplate(Integer page, Integer limit){

        Page<TemplateForm> pageList = templateFormService.page(new Page<>(page, limit));

        DataUtil<TemplateForm> dataUtil = new DataUtil<>();
        dataUtil.setCode(0);
        dataUtil.setCount((int)pageList.getTotal());
        dataUtil.setMsg("成功");
        dataUtil.setData(pageList.getRecords());
        return JSON.toJSONString(dataUtil);
    }

    /**
     * 根据id 删除 模板
     * @param id
     * @return
     */
    @PostMapping("/deletetemplateone")
    public String deleteTemplateOne(Integer id){

        TemplateForm byId = templateFormService.getById(id);
        if(EmptyUtils.isNotEmpty(byId.getIsInputData())){
            if(byId.getIsInputData()==1){
                return JSON.toJSONString(857);
            }
        }
        boolean isok = false;
        try {
            isok = templateFormService.removeById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(isok);
        }
    }

    /**
     * 获取 通用 模板信息
     * @return
     */
    @PostMapping("/getcommontemplate")
    public JSONResult getcommontemplate(){
        List<TemplateCommon> list = templateCommonService.list();
        return new JSONResult<TemplateCommon>(ResultEum.SUCCESS,(long)list.size(),list);

    }

    /**
     * 引用 生成 模板
     * @param ids
     * @param name
     * @return
     */
    @PostMapping("/addtemplatecontents")
    public String saveTemplatecontents(String ids,String name){

        boolean isok = false;
        try {
            isok = templateFormService.saveTemplate(ids,name);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(isok);
        }

    }

    /**
     * 根据 id 展示模板样式
     * @param id
     * @return
     */
    @RequestMapping("/getmessagetemplate")
    public JSONResult getMessageTemplate(Integer id){
        System.out.println(id);
        TemplateForm templateForm = templateFormService.getById(id);
        ArrayList<TemplateForm> templateForms = new ArrayList<>();
        templateForms.add(templateForm);
        return new JSONResult(ResultEum.SUCCESS,0L,templateForms);
    }

    /**
     * 添加 模板 样式
     * @param id
     * @param text
     * @return
     */
    @PostMapping("/updatemessagetemplate")
    public String updateMessageTemplate(Integer id ,String text){
        boolean isok = false;
        try {
            isok = templateFormService.updateMessageTemplate(id,text);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(isok);
        }
    }

}
