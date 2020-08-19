package com.follow.controller;


import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.entity.Department;
import com.follow.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService ;

    @RequestMapping("/getDepartment")
    public JSONResult getDepartment() {
        JSONResult jsonResult = null;

        List<Department> department = departmentService.list();
        jsonResult = new JSONResult(ResultEum.SUCCESS, 3L, department);
        System.out.println("请求成功");
        return jsonResult;
    }

}

