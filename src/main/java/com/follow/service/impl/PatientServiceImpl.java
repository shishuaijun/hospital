package com.follow.service.impl;

import com.follow.entity.Patient;
import com.follow.mapper.PatientMapper;
import com.follow.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Patient getByOneId(Integer id) {
        return patientMapper.selectByokId(id);
    }
}
