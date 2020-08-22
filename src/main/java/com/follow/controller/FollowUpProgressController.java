package com.follow.controller;

import com.alibaba.fastjson.JSON;
import com.follow.common.EmptyUtils;
import com.follow.common.ResultEum;
import com.follow.dto.DataResult;
import com.follow.dto.DataUtil;
import com.follow.service.FollowUpPorgressService;
import com.follow.vo.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/17
 */
@RestController
public class FollowUpProgressController {

    @Autowired
    private FollowUpPorgressService followUpPorgressService;

    @RequestMapping("/getfollowprogresslist")
    public  DataUtil<FollowUpProgressVO> getFollowprogressList(Integer page,
                                        Integer limit,
                                        Integer desk,
                                        String groupName,
                                        String dates,
                                        Integer admissionNumber,
                                        String name,
                                        Integer state){
        List<FollowUpProgressVO> list = followUpPorgressService.list(page,limit,desk,groupName,dates,admissionNumber,name,state);
        Integer count = 0;
        if(EmptyUtils.isNotEmpty(list)){
            count = list.size();
        }
        DataUtil<FollowUpProgressVO> followUpProgressVODataUtil = new DataUtil<>();
        followUpProgressVODataUtil.setCode(0);
        followUpProgressVODataUtil.setMsg("成功");
        followUpProgressVODataUtil.setCount(count);
        followUpProgressVODataUtil.setData(list);

        return followUpProgressVODataUtil;
    }

    /**
     * 根据 患者id 查询信息
     * @param id
     * @return
     */
    @PostMapping("/getcheckbypatientidlist")
    public DataResult getCheckByPatientIdList(Integer id){
        List<FollowUpCheckVO> list = followUpPorgressService.getList(id);
        return new DataResult(ResultEum.SUCCESS,0L,list);
    }

    /**
     * 修改 信息
     * @param vo
     * @return
     */
    @PostMapping("/updatecheckbypatientid")
    public String updatecheckbypatientid(FollowUpCheckVO vo){

        boolean isok = false;
        try {
            isok = followUpPorgressService.modificationList(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(isok);
        }

    }

    /**
     * 获取 随访组 信息
     * @return
     */
    @PostMapping("/getfollowuplist")
    public DataResult getfollowuplist(){

        List<FollowUpResultVO> list = followUpPorgressService.followUpResult();
        return new DataResult(ResultEum.SUCCESS,0L,list);
    }
    /**
     * 导出 随访组 信息
     */
    @RequestMapping("/derivefollowuplist")
    public void derivefollowuplist(HttpServletResponse response){
        String name ="随访组随访.xls";
        try {
            Workbook workbook = followUpPorgressService.exportExcel(name);
            //转码，免得文件名中文乱码
            name = URLEncoder.encode(name,"UTF-8");
            //设置文件下载头
            response.addHeader("Content-Disposition", "attachment;filename=" + name);
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/updatefollowemplateplan")
    public String updatefollowemplateplan(String treatname,Integer id){
        boolean isok = false;
        try {
            isok = followUpPorgressService.getByTreatName(treatname,id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(isok);
        }
    }

    @RequestMapping("/getOverviewprogresslist")
    public DataUtil<FollowUpTheRateVO> getTheRatelist(String principal,Integer desk,Integer state,String time,Integer page,Integer limit){

       List<FollowUpTheRateVO> list = followUpPorgressService.theRatelist(principal, desk, state, time,page,limit);
        DataUtil<FollowUpTheRateVO> dataUtil = new DataUtil<>();
        int count = 0 ;
        if(EmptyUtils.isNotEmpty(list)){
            count = list.size();
        }
        dataUtil.setCode(0);
        dataUtil.setMsg("成功");
        dataUtil.setCount(count);
        dataUtil.setData(list);
        return dataUtil;
    }

    @PostMapping("/getbasicdata")
    public DataUtil<BasicDataVO> getBasicData(Integer id){
        DataUtil<BasicDataVO> dataUtil = followUpPorgressService.getByBasicDataId(id);
        return dataUtil;
    }

    /**
     * 随访 查询
     * @param page
     * @param limit
     * @param array
     * @return
     */
    @GetMapping("/getfollowquerylist")
    public DataUtil<FollowUpQueryVO> getBasicData(Integer page,Integer limit,String array){
        DataUtil<FollowUpQueryVO> dataUtil = followUpPorgressService.getQueryList(page,limit,array);
        return dataUtil;
    }

}
