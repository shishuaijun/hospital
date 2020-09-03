package com.follow.controller;

import com.follow.common.EmptyUtils;
import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.entity.TermInformation;
import com.follow.service.TermService;
import com.follow.vo.DataInromationByUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
     * @return
     */
    @RequestMapping("addByupdateTermDataInformation")
    public JSONResult addByupdateTermDataInformation(@RequestParam(value = "dataValue",required = false) String dataValue,
                                                     @RequestParam(value = "dataId",required = false) String dataId,
                                                     HttpServletRequest request){
        JSONResult jsonResult = null ;
        String[] dataValues = dataValue.split(",");
        String[] dataIds = dataId.split(",");
        Object id = request.getSession().getAttribute("id");
        try{
            jsonResult = new JSONResult(ResultEum.SUCCESS,1L , termService.addByupdateDataInformation(dataValues,dataIds,id.toString()));
        }catch(Exception e){
            e.getLocalizedMessage();
            jsonResult = new JSONResult(ResultEum.ERROR,0L,"服务异常");
        }
        return jsonResult;
    }

    /**
     * 查询术语数据
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping("getDataInformation")
    public JSONResult getDataInformation(Integer page ,Integer limit , HttpServletRequest request){
        JSONResult jsonResult = null ;
        try {
            Object id = request.getSession().getAttribute("id");
            List<DataInromationByUserVo> dataInromationByUserVos = termService.qeruyDataInformation(page, limit, id.toString());
            Long count =0L;
            if (EmptyUtils.isNotEmpty(dataInromationByUserVos)){
                count = (long)dataInromationByUserVos.size();
            }
            jsonResult = new JSONResult(ResultEum.SUCCESS,count,dataInromationByUserVos);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JSONResult(ResultEum.ERROR,0L ,null);
        }

        return jsonResult ;
    }

    /**
     * 删除术语数据
     * @param id
     * @return
     */
    @RequestMapping("dalataDatainformation")
    public JSONResult deleteDatainformation(String id){
        JSONResult jsonResult = null;

        try {
            String s = termService.deleteInromationById(id);
            jsonResult = new JSONResult(ResultEum.SUCCESS , 1L , s);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JSONResult(ResultEum.ERROR , 0L , "服务器异常");
        }
        return jsonResult ;
    }
}
