package com.follow.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.dto.DataUtil;
import com.follow.entity.Department;
import com.follow.entity.Disease;
import com.follow.entity.TemplateForm;
import com.follow.service.DepartmentService;
import com.follow.vo.DepartmentHospitalVo;
import com.github.pagehelper.PageInfo;
import jdk.internal.util.xml.impl.Input;
import lombok.val;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@RestController
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


        // 解决bug：最后一页删除多条数据后，页面显示无数据，需要将页数减一，重新进行查询
        while (page1.getSize() == 0) {
            page = page - 1;
            page1 = departmentService.page(new Page<>(page, limit));
        }


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
     *
     * @auther: Zuan~
     * @date: 2020/8/19  14:42
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/findByDepartment")
    public String findByDepartment(String departmentName, String departmentBoss, Integer page, Integer limit) {
        PageInfo<Department> pageInfo = departmentService.findByDepartment(departmentName, departmentBoss, page, limit);

        // 解决bug：最后一页删除多条数据后，页面显示无数据，需要将页数减一，重新进行查询
        while (pageInfo.getList().size() == 0) {
            page = page - 1;
            pageInfo = departmentService.findByDepartment(departmentName, departmentBoss, page, limit);
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
     *
     * @auther: Zuan~
     * @date: 2020/8/19  15:02
     * @param:
     * @return:
     */
    @RequestMapping("/saveDepartment")
    @ResponseBody
    public String saveDepartment(Department department) {
        boolean b = departmentService.saveOrUpdate(department);
        if (b == true) {
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 根据id 进行删除 ]
     *
     * @auther: Zuan~
     * @date: 2020/8/19  15:08
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/deleteDepartment")
    public String deleteDepartment(Integer id) {
        boolean c = departmentService.removeById(id);
        if (c == true) {
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 根据id 查询单条 ]
     *
     * @auther: Zuan~
     * @date: 2020/8/19  15:11
     * @param:
     * @return:
     */
    @RequestMapping("/findByIdDepartment")
    @ResponseBody
    public String findByIdDepartment(Integer id) {
        Department d = departmentService.getById(id);
        return JSON.toJSONString(d);
    }

    /**
     * 查询所有科室的数据
     *
     * @return lizihao
     */
    @RequestMapping("getDepartmentName")
    public JSONResult getDepartmentName(@RequestParam(value = "page",required = false, defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit",required = false,defaultValue = "10") Integer limit,
                                        @RequestParam(value = "departmentName", required = false) String departmentName,
                                        @RequestParam(value = "departmentBoss", required = false) String departmentBoss) {
        JSONResult jsonResult = null;
        Page<Department> page1 = new Page<>(page, limit);
        QueryWrapper<Department> objectQueryWrapper = new QueryWrapper<>();
        System.out.println(departmentName);
        System.out.println(departmentBoss);
        if (departmentName != null && !"".equals(departmentName)) {
            objectQueryWrapper.eq("department_name", departmentName);
        }
        if (departmentBoss != null && !"".equals(departmentBoss)) {
            objectQueryWrapper.eq("department_boss", departmentBoss);
        }
        try {
            Page<Department> page2 = departmentService.page(page1, objectQueryWrapper);

            jsonResult = new JSONResult(ResultEum.SUCCESS, page2.getTotal(), page2.getRecords());
        } catch (Exception e) {
            jsonResult = new JSONResult(ResultEum.ERROR, 0L, null);
        }
        return jsonResult;
    }

    /**
     * 根据id删除科室
     *
     * @param id
     * @return lizihao
     */
    @RequestMapping("dislodgeDepartment")
    public JSONResult dislodgeDepartment(@RequestParam("id") Integer id) {
        JSONResult jsonResult = null;
        boolean b = departmentService.removeById(id);
        try {
            jsonResult = new JSONResult(ResultEum.SUCCESS, b ? 1L : 0L, b ? "删除成功" : "删除失败");
        } catch (Exception e) {
            jsonResult = new JSONResult(ResultEum.ERROR, 0L, "网络异常");
        }
        return jsonResult;
    }

    /**
     * 查询所有科室
     * @return
     */
    @RequestMapping("getDepartment")
    public JSONResult getDepartment(){
        JSONResult jsonResult = null ;
        List<Department> list = departmentService.list();
        try{
            jsonResult = new JSONResult(ResultEum.SUCCESS,(long)list.size(),list);
        }catch(Exception e){
            jsonResult = new JSONResult(ResultEum.ERROR,0L,null);
        }
        return jsonResult ;
    }

    /**
     * 根据科室id 查询科室信息
     * @param id
     * @return
     */
    @RequestMapping("getDepartmentByHospital")
    public JSONResult getDepartmentByHospital(@Param("id")Integer id){
        JSONResult jsonResult = null ;
        try{
            DepartmentHospitalVo departmentHospitalVo = departmentService.queryDepartmentByHospital(id);
            jsonResult = new JSONResult(ResultEum.SUCCESS,1L,departmentHospitalVo);
        }catch(Exception e){
            jsonResult = new JSONResult(ResultEum.ERROR,0L,null);
        }
        return jsonResult ;
    }

    /**
     * 添加科室表
     * @param departmentName 科室名称
     * @param hospitalId   医院id
     * @param departmentBoss  科室负责人
     * @return
     */
    @RequestMapping("addDepartment")
    public JSONResult addDepartment(@RequestParam(value = "departmentName") String departmentName,
                                    @RequestParam(value = "hospitalId") Integer hospitalId,
                                    @RequestParam(value = "departmentBoss") String departmentBoss){
        JSONResult jsonResult = null ;
        try{
            Department department = new Department();
            department.setDepartmentName(departmentName);
            department.setDepartmentBoss(departmentBoss);
            department.setHospitalId(hospitalId);
            System.out.println(department.toString());
            boolean save = departmentService.save(department);
            jsonResult = new JSONResult(ResultEum.SUCCESS,save?1L:0L,save?"添加成功":"添加失败");
        }catch(Exception e){
            jsonResult = new JSONResult(ResultEum.ERROR,0L,"请输入数据");
        }
        return jsonResult ;
    }

    /**
     * 修改科室信息
     * @param departmentName
     * @param id
     * @param hospitalId
     * @param departmentBoss
     * @return
     */
    @RequestMapping("updateDepartment")
    public JSONResult updateDepartment(@RequestParam("departmentName")String departmentName,
                                       @RequestParam("id")Integer id ,
                                       @RequestParam("hospitalId")String hospitalId,
                                       @RequestParam("departmentBoss")String departmentBoss){
        JSONResult jsonResult = null ;
        try{
            String s = departmentService.modifyByDepartment(id, departmentName, hospitalId, departmentBoss);
            jsonResult = new JSONResult(ResultEum.SUCCESS,1L,s);
        }catch(Exception e){
            jsonResult = new JSONResult(ResultEum.ERROR,0L,"网络异常");
      }
        return jsonResult ;
    }



}

