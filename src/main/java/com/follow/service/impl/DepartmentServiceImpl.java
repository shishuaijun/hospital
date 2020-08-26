package com.follow.service.impl;

import com.follow.entity.Department;
import com.follow.entity.TemplateForm;
import com.follow.mapper.DepartmentMapper;
import com.follow.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.follow.vo.DepartmentHospitalVo;
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
        List<Department> list = departmentMapper.findByDepartment(departmentName, departmentBoss, page, limit);

        PageInfo<Department> pageInfo = new PageInfo<Department>(list);

        return pageInfo;
    }

    @Override
    public DepartmentHospitalVo queryDepartmentByHospital(Integer id) {
        return departmentMapper.selectDeartmentByHospital(id);
    }

    @Override
    public String modifyByDepartment(Integer id, String departmentName, String hospitalId, String departmentBoss) {
        Integer integer = departmentMapper.updateBuDepartment(id, departmentName, hospitalId, departmentBoss);
        if (integer > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
}
