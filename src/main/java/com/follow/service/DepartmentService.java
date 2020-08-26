package com.follow.service;

import com.follow.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.follow.vo.DepartmentHospitalVo;
import com.github.pagehelper.PageInfo;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface DepartmentService extends IService<Department> {

    PageInfo<Department> findByDepartment(String departmentName, String departmentBoss, Integer page, Integer limit);

    DepartmentHospitalVo queryDepartmentByHospital(Integer id);

    String modifyByDepartment(Integer id , String departmentName,String hospitalId,String departmentBoss);
}
