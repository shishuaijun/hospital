package com.follow.mapper;

import com.follow.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface DepartmentMapper extends BaseMapper<Department> {


    List<Department> findByDepartment(@Param("departmentName") String departmentName,
                                      @Param("departmentBoss") String departmentBoss,
                                      @Param("page") Integer page,
                                      @Param("limit") Integer limit);
}
