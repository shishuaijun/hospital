package com.follow.controller;


import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.entity.Hospital;
import com.follow.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@RestController
public class HospitalController {

    @Autowired
    private HospitalService hospitalService ;

    @RequestMapping("getHospitalAll")
    public JSONResult getHospitalAll(){
        JSONResult jsonResult =  null ;
        try{
            List<Hospital> list = hospitalService.list();
            jsonResult = new JSONResult(ResultEum.SUCCESS,(long)list.size(),list);
        }catch(Exception e){
            jsonResult = new JSONResult(ResultEum.ERROR,0L,null);
        }
        return jsonResult ;
    }

}

