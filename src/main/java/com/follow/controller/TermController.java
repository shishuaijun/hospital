package com.follow.controller;

import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.entity.TermInformation;
import com.follow.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TermController {

    @Autowired
    private TermService termService ;

    @RequestMapping("getTermInforamtion")
    public JSONResult getTermInformation(){
        JSONResult jsonResult  = null ;
        try{
            List<TermInformation> termInformationList = termService.queryTermInformation();
            jsonResult = new JSONResult(ResultEum.SUCCESS,(long)termInformationList.size(),termInformationList);
        }catch(Exception e){
            jsonResult = new JSONResult(ResultEum.ERROR,0L,null);
        }
        return jsonResult ;
    }
}
