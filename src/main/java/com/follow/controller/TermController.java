package com.follow.controller;

import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.entity.TermInformation;
import com.follow.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TermController {

    @Autowired
    private TermService termService ;

    /**
     * 查询所有的分类
     * @return
     */
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

    /**
     * 保存数据
     * @param dataValue 值
     * @param dataId  对应id
     * @param userId    用户id
     * @return
     */
    @RequestMapping("addByupdateTermDataInformation")
    public JSONResult addByupdateTermDataInformation(@RequestParam(value = "dataValue",required = false) String dataValue,
                                                     @RequestParam(value = "dataId",required = false) String dataId  ,
                                                     @RequestParam(value = "userId",required = false) String userId){
        JSONResult jsonResult = null ;
        String[] dataValues = dataValue.split(",");
        String[] dataIds = dataId.split(",");
        for (String value : dataValues) {
            System.out.println(value);
        }
        for (String id : dataIds) {
            System.out.println(id);
        }
//        try{
            jsonResult = new JSONResult(ResultEum.SUCCESS,1L , termService.addByupdateDataInformation(dataValues,dataIds,userId));
//        }catch(Exception e){
//            jsonResult = new JSONResult(ResultEum.ERROR,0L,"服务异常");
//        }
        return jsonResult;
    }



    /**
     * 功能描述： TODO[ 全查+分页 ]
     * @auther:  Zuan~
     * @date:  2020/8/28  9:30
     * @param:  findPageMaintenance
     * @return:
     */
    @RequestMapping("/findPageMaintenance")
    public String findPageMaintenance(Integer page,Integer limit){
        return "";
    }
}
