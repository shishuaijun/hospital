package com.follow.service;

import com.follow.entity.Patient;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface PatientService extends IService<Patient> {

    /**
     * 根据id 查询患者信息
     * @param id
     * @return
     */
    Patient getByOneId(Integer id);
}
