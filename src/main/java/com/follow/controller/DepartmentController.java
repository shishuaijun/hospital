package com.follow.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.follow.dto.DataUtil;
import com.follow.entity.Department;
import com.follow.entity.Disease;
import com.follow.entity.TemplateForm;
import com.follow.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    /**
     * 功能描述： TODO[ 全查 ]
     *
     * @auther: Zuan~
     * @date: 2020/8/19  14:38
     * @param:
     * @return:
     */
    @RequestMapping("/getdepartment")
    @ResponseBody
    public String getdepartment(Integer page, Integer limit) {
        Page<Department> page1 = departmentService.page(new Page<>(page, limit));
        DataUtil dataUtil = new DataUtil();
        dataUtil.setCode(0);
        dataUtil.setMsg("success");
        dataUtil.setCount((int) page1.getTotal());
        dataUtil.setData(page1.getRecords());
        JSONObject json = new JSONObject();
        return json.toJSONString(dataUtil);
    }


    /**
     * 功能描述： TODO[ 模糊查询+分页 ]
     * @auther:  Zuan~
     * @date:  2020/8/19  14:42
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/findByDepartment")
    public String findByDepartment(String departmentName,String departmentBoss,Integer page,Integer limit){
     PageInfo<Department> pageInfo = departmentService.findByDepartment( departmentName, departmentBoss, page, limit);

     // 解决bug：最后一页删除多条数据后，页面显示无数据，需要将页数减一，重新进行查询
     while (pageInfo.getList().size() == 0) {
      page = page - 1;
      pageInfo = departmentService.findByDepartment( departmentName, departmentBoss, page, limit);
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
     * 功能描述： TODO[ 添加保存 / 修改保存 ]
     * @auther:  Zuan~
     * @date:  2020/8/19  15:02
     * @param:
     * @return:
     */
    @RequestMapping("/saveDepartment")
    @ResponseBody
    public String saveDepartment(Department department){
     boolean b = departmentService.saveOrUpdate(department);
     if (b == true){
      return "yes";
     }
     return "no";
    }


    /**
     * 功能描述： TODO[ 根据id 进行删除 ]
     * @auther:  Zuan~
     * @date:  2020/8/19  15:08
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/deleteDepartment")
    public String deleteDepartment(Integer id){
     boolean c = departmentService.removeById(id);
     if (c == true){
      return "yes";
     }
     return "no";
    }



    /**
     * 功能描述： TODO[ 根据id 查询单条 ]
     * @auther:  Zuan~
     * @date:  2020/8/19  15:11
     * @param:
     * @return:
     */
    @RequestMapping("/findByIdDepartment")
    @ResponseBody
    public String findByIdDepartment(Integer id){
      Department d = departmentService.getById(id);
      return JSON.toJSONString(d);
    }




}

