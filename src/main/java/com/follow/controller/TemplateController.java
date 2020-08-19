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
import com.github.pagehelper.PageInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
     * 修改 模板 样式
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

    /**
     * 发布 版本
     * @param id
     * @return
     */
    @PostMapping("/updatestatetemplate")
    public String updatestatetemplate(Integer id){
        System.out.println(id);
        TemplateForm byId = templateFormService.getById(id);
        boolean isok = false;
        if(byId.getState()!=3){
           return JSON.toJSONString(857);
        }else {
            TemplateForm templateForm = new TemplateForm();
            templateForm.setId(id);
            templateForm.setModifyMan("获取登陆用户");
            templateForm.setState(1);
            templateForm.setModifyTime(LocalDateTime.now());
            isok = templateFormService.updateById(templateForm);
        }
        return JSON.toJSONString(isok);
    }


    /**
     * 功能描述： TODO[ 模糊查询 + 分页 ]
     * @auther:  Zuan~
     * @date:  2020/8/17  16:26
     * @param:
     * @return:
     */
    @RequestMapping("/findByConditions")
    @ResponseBody
    public String findByConditions(String name,String modifyMan,Integer page,Integer limit){
        PageInfo<TemplateForm> pageInfo = templateFormService.findByConditions( name, modifyMan, page, limit);

        // 解决bug：最后一页删除多条数据后，页面显示无数据，需要将页数减一，重新进行查询
        while (pageInfo.getList().size() == 0) {
            page = page - 1;
            pageInfo = templateFormService.findByConditions(name, modifyMan, page, limit);
        }

        //HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();

        map1.put("code", 0);
        map1.put("data", pageInfo.getList());
        map1.put("count", pageInfo.getTotal());

        String jsonString = JSON.toJSONString(map1);
        return jsonString;
    }



    /**
     * 添加 模板 样式
     * @param text
     * @return
     */
    @PostMapping("/savemessagetemplate")
    public String savemessagetemplate(String name,String text){
        boolean isok2 = false;
        try {
            isok2 = templateFormService.savemessagetemplate(name,text);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(isok2);
        }
    }

}
