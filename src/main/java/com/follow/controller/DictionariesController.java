package com.follow.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.follow.dto.DataUtil;
import com.follow.entity.Dictionaries;
import com.follow.entity.Disease;
import com.follow.entity.TemplateForm;
import com.follow.service.DictionariesService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author ssj
 * @date 2020/8/14
 */
@Controller
public class DictionariesController {

    @Resource
    private DictionariesService dictionariesService;

    /**
     * 功能描述： TODO[ 全查+分页+模糊 ]
     * @auther:  Zuan~
     * @date:  2020/8/14  16:30
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/findCdr")
    public String findCdr(Integer page, Integer limit){
        Page<Dictionaries> page1 = dictionariesService.page(new Page<>(page, limit));

        //解决bug：最后一页删除最后一条数据后，页面显示无数据，需要将页数减一，重新进行查询
        while (page1.getSize() == 0){
            page = page - 1;
            page1 = dictionariesService.page(new Page<>(page, limit));
        }

        DataUtil dataUtil = new DataUtil();
        dataUtil.setCode(0);
        dataUtil.setMsg("success");
        dataUtil.setCount((int)page1.getTotal());
        dataUtil.setData(page1.getRecords());

        JSONObject json = new JSONObject();

        return json.toJSONString(dataUtil);
    }


    /**
     * 功能描述： TODO[ 根据id 进行删除字典 ]
     * @auther:  Zuan~
     * @date:  2020/8/14  16:52
     * @param:
     * @return:
     */
    @RequestMapping("/deleteDictionaries")
    @ResponseBody
    public String deleteDictionaries(Integer id){
        boolean a = dictionariesService.removeById(id);
        if(a == true){
            return "yes";
        }
        return "no";
    }




    /**
     * 功能描述： TODO[ 添加/保存 字典记录 ]
     * @auther:  Zuan~
     * @date:  2020/8/14  13:55
     * @param:
     * @return:
     */
    @RequestMapping("/saveDictionaries")
    @ResponseBody
    public String saveDictionaries(Dictionaries dictionaries){
        boolean a = dictionariesService.saveOrUpdate(dictionaries);
        if (a == true){
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 根据id 查询单条 ]
     * @auther:  Zuan~
     * @date:  2020/8/14  13:58
     * @param:
     * @return:
     */
    @RequestMapping("/findByIdDictionaries")
    @ResponseBody
    public String findByIdDictionaries(Integer id){
        Dictionaries di = dictionariesService.getById(id);
        return JSON.toJSONString(di);
    }





    /**
     * 功能描述： TODO[ 模糊查询 + 分页 ]
     * @auther:  Zuan~
     * @date:  2020/8/18 10：54
     * @param:
     * @return:
     */
    @RequestMapping("/findByConditions1")
    @ResponseBody
    public String findByConditions1(String dictionariesName,String dictionariesCod,String dictionariesCodName,String shunxu,Integer page,Integer limit){
        PageInfo<Dictionaries> pageInfo = dictionariesService.findByConditions1( dictionariesName, dictionariesCod,dictionariesCodName,shunxu, page, limit);

        // 解决bug：最后一页删除多条数据后，页面显示无数据，需要将页数减一，重新进行查询
        while (pageInfo.getList().size() == 0) {
            page = page - 1;
            pageInfo = dictionariesService.findByConditions1(dictionariesName, dictionariesCod,dictionariesCodName,shunxu, page, limit);
        }

        //HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();

        map1.put("code", 0);
        map1.put("data", pageInfo.getList());
        map1.put("count", pageInfo.getTotal());

        String jsonString = JSON.toJSONString(map1);
        return jsonString;
    }

}

