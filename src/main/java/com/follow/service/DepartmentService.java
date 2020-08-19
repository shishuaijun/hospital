package com.follow.service;

import com.follow.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface DepartmentService extends IService<Department> {

    PageInfo<Department> findByDepartment(String departmentName, String departmentBoss, Integer page, Integer limit);
}
