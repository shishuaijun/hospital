package com.follow.controller;


import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.service.PatientService;
import com.follow.vo.PatientUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


}

