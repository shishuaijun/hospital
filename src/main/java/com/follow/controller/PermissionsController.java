package com.follow.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@RestController
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService ;

    @RequestMapping("queryGroupPermission")
    public JSONResult queryGroupPermission(@RequestParam("page")Integer page ,
                                           @RequestParam("limit")Integer limit){
        JSONResult jsonResult = null ;
        Page  page1  = new Page(page,limit);

        try{
            Page page2 = permissionsService.page(page1);
            jsonResult = new JSONResult(ResultEum.SUCCESS,page2.getTotal(),page2.getRecords());
        }catch(Exception e){
            jsonResult = new JSONResult(ResultEum.ERROR,0L,null);
        }
        return jsonResult ;
    }

}

