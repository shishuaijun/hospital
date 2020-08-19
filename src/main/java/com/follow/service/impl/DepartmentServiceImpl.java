package com.follow.service.impl;

import com.follow.entity.Department;
import com.follow.entity.TemplateForm;
import com.follow.mapper.DepartmentMapper;
import com.follow.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public PageInfo<Department> findByDepartment(String departmentName, String departmentBoss, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Department> list = departmentMapper.findByDepartment(departmentName,departmentBoss, page, limit);

        PageInfo<Department> pageInfo = new PageInfo<Department>(list);

        return pageInfo;
    }
}
