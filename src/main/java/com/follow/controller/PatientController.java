package com.follow.controller;


import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.service.PatientService;
import com.follow.vo.PatientUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.follow.entity.Patient;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService ;

    @RequestMapping("getPatientUserVo")
    public JSONResult getPatientUserVo(@RequestParam("userId") String userId , HttpServletRequest request){
        Object attribute = request.getSession().getAttribute("");
        System.out.println(attribute.toString());
        JSONResult jsonResult = null ;
        try{
        PatientUserVo patientUserVo = patientService.queryPatientUserVoByUserId(userId);
            jsonResult = new JSONResult(ResultEum.SUCCESS,1L,patientUserVo);
        }catch(Exception e){
            e.getLocalizedMessage();
            jsonResult = new JSONResult(ResultEum.ERROR,0L,null);
        }
        return jsonResult ;
    }




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

