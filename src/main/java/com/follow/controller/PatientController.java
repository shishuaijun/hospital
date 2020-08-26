package com.follow.controller;
import com.follow.entity.Patient;
import com.follow.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * 生成 多条 患者信息  测试
     */
    @RequestMapping("/insertpatient")
    public void savePatient(){

        for (int i = 3; i < 20; i++) {
            Patient patient = new Patient();
            patient.setPatientName("小明"+i);
            patient.setDepartmentId(1);
            patient.setDiseaseId(1);
            patient.setAdminssionnumber("");
            patient.setOutpaientnumber("");
            patient.setSex(0);
            patient.setBirthday(LocalDateTime.now());
            patient.setAddress("");
            patient.setEducationId(0);
            patient.setPhone("");
            patient.setEmail("");
            patient.setBloodtype("");
            patient.setLink("");
            patient.setIdnumber("");
            patient.setCreateTime(LocalDateTime.now());
            patient.setModifyTime(LocalDateTime.now());
            patient.setIsJoingroup(0);

            patientService.save(patient);
        }
    }
}

