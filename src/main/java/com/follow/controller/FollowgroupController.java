package com.follow.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.follow.common.Execel;
import com.follow.common.*;
import com.follow.dto.DataUtil;
import com.follow.entity.Followgroup;
import com.follow.mapper.FollowgroupMapper;
import com.follow.service.FollowgroupService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分组
 *
 * @author wangchunjun
 * @date 2020/8/6
 */
@Controller
public class FollowgroupController {

    @Autowired
    private FollowgroupService followgroupService;

    @Resource
    private FollowgroupMapper followgroupMapper;


    /**
     * 全查
     *
     * @param ：Followgroup
     * @return
     */
    @RequestMapping(value = "findPage", produces = {"application/json"})
    @ResponseBody
    public String findPage(Integer page, Integer limit) {
        Page<Followgroup> page1 = followgroupService.page(new Page<>(page, limit));
        DataUtil dataUtil = new DataUtil();
        dataUtil.setCode(0);
        dataUtil.setMsg("success");
        dataUtil.setCount((int)page1.getTotal());
        dataUtil.setData(page1.getRecords());

        JSONObject json = new JSONObject();

        return json.toJSONString(dataUtil);
    }

    /**
     * 功能描述： TODO[ 添加保存 ]
     * @auther:  Zuan~
     * @date:  2020/8/7  16:27
     * @param: departmentId,departmentPerson,createTime, fName,fphone,fbackground,fstate,   fstratTime,fendTime
     * @return:  yes/no
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public String save(Integer departmentId, Integer departmentPerson, Date createTime, String fName, String fphone, String fbackground, Integer fstate){
        Followgroup f = new Followgroup();
        f.setDepartmentId(departmentId);
        f.setDepartmentPerson(departmentPerson);
        f.setCreateTime(createTime);
        f.setFName(fName);
        f.setFphone(fphone);
        f.setFbackground(fbackground);
        f.setFstate(fstate);
        //f.setFstratTime(fstratTime);
        //f.setFendTime(fendTime);

        boolean insert = followgroupService.save(f);
        if (insert == true){
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 删除 ]
     * @auther:  Zuan~
     * @date:  2020/8/7  17:53
     * @param:
     * @return:
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(Integer id){
        boolean i = followgroupService.removeById(id);
        if (i = true){
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 单查id 进行修改 ]
     * @auther:  Zuan~
     * @date:  2020/8/10  10:09
     * @param:  id
     * @return:  entity
     */
    @RequestMapping("/findById")
    @ResponseBody
    public String findById(Integer id){

        Followgroup byId = followgroupService.getById(id);

        return JSON.toJSONString(byId);
    }


    /**
     * 功能描述： TODO[  修改后的保存 ]
     * @auther:  Zuan~
     * @date:  2020/8/10  10:26
     * @param:  entity
     * @return:
     */
    @RequestMapping("/save2")
    @ResponseBody
    public String save2(Integer id,Integer departmentId, Integer departmentPerson, Date createTime, String fName, String fphone, String fbackground, Integer fstate){

        Followgroup f = new Followgroup();
        f.setId(id);
        f.setDepartmentId(departmentId);
        f.setDepartmentPerson(departmentPerson);
        f.setFName(fName);
        f.setFphone(fphone);
        f.setFbackground(fbackground);
        f.setFstate(fstate);
        //f.setFstratTime(fstratTime);
        //f.setFendTime(fendTime);

        Boolean b = followgroupService.saveOrUpdate(f);

        if (b == true){
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 进行导入导出，需要有 相应流方法 ]
     * @auther:  Zuan~
     * @date:  2020/8/13  9:05
     * @param:
     * @return:
     */
    public void setResponseHeader(HttpServletResponse response,String fileName){
        try{
            try {
                fileName = new String(fileName.getBytes(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control","no-cache");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 功能描述： TODO[ 文档导入，excel表格导入 ]
     * @auther:  Zuan~
     * @date:  2020/8/13  9:14
     * @param:
     */
    @RequestMapping("/daoru")
    @ResponseBody
    public String daoru(@RequestParam("file") MultipartFile[] file){
        List<Followgroup> list = new ArrayList<>();
        for(MultipartFile f : file){
            try{
                List<String[]> list1 = Execel.readExcel(f);
                int i=0;
                for(String[] strings : list1){
                    if(i == 1){
                        Followgroup registration = new Followgroup();

                        registration.setFName(strings[0]);
                        registration.setHospitalId(Integer.parseInt(strings[1]));
                        registration.setDepartmentPerson(Integer.parseInt(strings[2]));
                        registration.setDepartmentId(Integer.parseInt(strings[3]));
                        registration.setFphone(strings[4]);
                        registration.setFbackground(strings[5]);
                        registration.setDiseaseId(Integer.parseInt(strings[6]));
                        registration.setIsDelete(0);
                        registration.setCreateTime(new Date());
                        list.add(registration);
                    }else{
                        i++;
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        for(Followgroup followgroup : list){
            followgroupService.insert(followgroup);
        }
        return "成功";
    }


    @ResponseBody
    @RequestMapping("/daochu")
    public void daochu(HttpServletResponse response){
        String message="";

        //获取数据
        List<Followgroup> list = followgroupMapper.selectAll();

        //execel 标题
        String[] title = {"随访组名称","医院id","负责人","责任科室","联系电话","背景","疾病id"};

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        //execel 文件名
        String fileName = sf.format(date)+".xls";

        //sheet 名
        String sheetName = "随访组";

        String[][] content = new String[list.size()][];
        for(int i=0;i<list.size();i++){
            content[i] = new String[title.length];
            Followgroup reg = list.get(i);
            content[i][0] = reg.getFName()+"";
            content[i][1] = reg.getHospitalId()+"";
            content[i][2] = reg.getDepartmentPerson()+"";
            content[i][3] = reg.getDepartmentId()+"";
            content[i][4] = reg.getFphone()+"";
            content[i][5] = reg.getFbackground()+"";
            content[i][6] = reg.getDiseaseId()+"";
        }

        //创建 HSSFWorkbook
        HSSFWorkbook wb = ExecelUtil.getHSSFWorkbook(sheetName,title,content,null);

        //相应到客户端
        try{
            this.setResponseHeader(response,fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
            message = "SUCCESS";
        }catch(Exception e){
            e.printStackTrace();
            message = "ERROR";
        }
    }

}

