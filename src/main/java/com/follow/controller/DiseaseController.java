package com.follow.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.follow.dto.DataUtil;
import com.follow.entity.Disease;
import com.follow.entity.Followgroup;
import com.follow.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Controller
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    /**
     * 功能描述： TODO[ 分页全查病种疾病数据库 ]
     *
     * @auther: Zuan~
     * @date: 2020/8/13  14:38
     * @param: Integer page, Integer limit
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/findDisease", produces = {"application/json"})
    public String findDisease(Integer page, Integer limit) {
        Page<Disease> page1 = diseaseService.page(new Page<>(page, limit));
        DataUtil dataUtil = new DataUtil();
        dataUtil.setCode(0);
        dataUtil.setMsg("success");
        dataUtil.setCount((int) page1.getTotal());
        dataUtil.setData(page1.getRecords());
        JSONObject json = new JSONObject();
        return json.toJSONString(dataUtil);

    }


    /**
     * 功能描述： TODO[ 根据id 进行删除 ]
     *
     * @auther: Zuan~
     * @date: 2020/8/13  17:13
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/deleteDisease")
    public String deleteDisease(Integer id) {
        boolean a = diseaseService.removeById(id);
        if (a = true) {
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 添加/保存 病种记录 ]
     *
     * @auther: Zuan~
     * @date: 2020/8/14  13:55
     * @param:
     * @return:
     */
    @RequestMapping("/saveDiseaseDabtabase")
    @ResponseBody
    public String saveDiseaseDabtabase(Disease disease) {
        boolean a = diseaseService.saveOrUpdate(disease);
        if (a == true) {
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 根据id 查询单条 ]
     *
     * @auther: Zuan~
     * @date: 2020/8/14  13:58
     * @param:
     * @return:
     */
    @RequestMapping("/findByIdDisease")
    @ResponseBody
    public String findByIdDisease(Integer id) {
        Disease di = diseaseService.getById(id);
        return JSON.toJSONString(di);
    }


}

