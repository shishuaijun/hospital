package com.follow.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.follow.common.EmptyUtils;
import com.follow.common.ResultEum;
import com.follow.dto.DataResult;
import com.follow.dto.DataUtil;
import com.follow.entity.*;
import com.follow.service.*;
import com.follow.vo.CustomPatientVO;
import com.follow.vo.CustomVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 入组 管理  控制层
 * @author wangchunjun
 * @date 2020/8/6
 */
@RestController
public class JoinGroupController {

    @Autowired
    private JoinGroupService joinGroupService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private FollowgroupService followgroupService;

    /**
     * 根据条件 将患者 进行入组
     * @param desk
     * @param illnessCoded
     * @param illnessName
     * @return
     */
    @PostMapping("/intotheroupupdate")
    public String joinGroup(Integer desk, String illnessCoded, String illnessName, String array) {
        boolean isok = false;
        try {
            isok = joinGroupService.intoTheGroup(desk, illnessCoded, illnessName,array);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(isok);
        } finally {
            return JSON.toJSONString(isok);
        }
    }

    /**
     * 自定义条件 将患者 进行入组
     * @param patient
     * @return
     */
    @PostMapping("/intotheroupupdates")
    public String joinGroups(CustomPatientVO patient) {

        boolean isok = false;
        if(EmptyUtils.isEmpty(patient)){
            return JSON.toJSONString(isok);
        }
        try {
            isok = joinGroupService.intoTheGroups(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(isok);
        } finally {
            return JSON.toJSONString(isok);
        }
    }

    /**
     * 获取医生 列表
     * @return
     */
    @PostMapping("/getdoctorname")
    public DataResult getDoctorName() {
        String name = "医生";
        List<User> userList = roleService.getsUserName(name);
        DataResult jsonResult = null ;
        int size = userList.size();
        jsonResult = new DataResult(ResultEum.SUCCESS, (long) size,userList);
        return jsonResult;
    }

    /**
     * 获取护士 列表
     * @return
     */
    @PostMapping("/getnursename")
    public DataResult getNurseName() {
        String name = "护士";
        List<User> userList = roleService.getsUserName(name);
        DataResult jsonResult = null ;
        int size = userList.size();
        jsonResult = new DataResult(ResultEum.SUCCESS, (long) size,userList);
        return jsonResult;
    }

    /**
     * 获取药师 列表
     * @return
     */
    @PostMapping("/getmedicinename")
    public DataResult getMedicineName() {
        String name = "药师";
        List<User> userList = roleService.getsUserName(name);
        DataResult jsonResult = null ;
        int size = userList.size();
        jsonResult = new DataResult(ResultEum.SUCCESS, (long) size,userList);
        return jsonResult;
    }

    /**
     * 获取科室 列表
     * @return
     */
    @PostMapping("/getdepartmentname")
    public DataResult getDepartmentName() {
        List<Department> list = departmentService.list();
        DataResult jsonResult = null ;
        long size = list.size();
        jsonResult = new DataResult(ResultEum.SUCCESS, size,list);
        return jsonResult;
    }

    /**
     * 获取随访组 名称
     * @return
     */
    @PostMapping("/getfollowUpGroupname")
    public DataResult getFollowUpGroupName() {
        List<Followgroup> list = followgroupService.list();
        DataResult jsonResult = null ;
        long size = list.size();
        jsonResult = new DataResult(ResultEum.SUCCESS, size,list);
        return jsonResult;
    }

    /**
     * 获取患者 信息
     * @return
     */
    @PostMapping("/getpatientrecords")
    public DataUtil getatientrRecords(Integer id) {
        ArrayList<CustomVo> patients = new ArrayList<>();
        Patient byId = patientService.getByOneId(id);
        DataUtil<CustomVo> patientDataUtil = new DataUtil<>();
        if(EmptyUtils.isNotEmpty(byId)){
            CustomVo customVo = new CustomVo();
            customVo.setPatientName(byId.getPatientName());
            customVo.setAdminssionnumber(byId.getSex()==1?"男":"女");
            customVo.setOutpaientnumber(byId.getAdminssionnumber());
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String localTime = df.format(byId.getBirthday());
            String substring = localTime.substring(0,11);
            customVo.setBirthday(substring);
            patients.add(customVo);
            patientDataUtil.setData(patients);
        }

        return patientDataUtil;
    }
    /**
     * 获取结果集 列表
     * @return
     */
    @PostMapping("/getresult")
    public DataResult getResult() {
        List<Result> list = resultService.list();
        DataResult jsonResult = null ;
        long size = list.size();
        jsonResult = new DataResult(ResultEum.SUCCESS,size,list);
        return jsonResult;
    }

    /**
     * 上传 文件 并 入组
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        File path = new File("D:/upload");
        if (!path.exists()){
            path.mkdir();
        }
        String newPath = path + "/" + fileName;
        file.transferTo(new File(newPath));

        boolean isok = false;
        try {
            isok = joinGroupService.importExcel(newPath);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            File file1 = new File(newPath);
            file1.delete();
            return JSON.toJSONString(isok);
        }

    }

    /**
     * 导出 模板
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "exportexcel")
    public void importExcel(HttpServletResponse response) throws IOException {
        String name ="模板.xls";
        try {
            Workbook workbook = joinGroupService.exportExcel(name);
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

    /**
     * 根据结果集 入组
     * @param id
     * @return
     */
    @PostMapping("/getresultbyid")
    public String ResultId(Integer id){
        boolean isok =false;
        try {
             isok = joinGroupService.getResult(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(isok);
        }
    }

    /**
     * 自定义/导入  自定义患者入组
     * @param patient
     * @return
     */
    @PostMapping("/customPatient")
    public String customPatient(CustomVo patient){
        boolean isok = false;
        try {
            isok = joinGroupService.customPatient(patient);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(isok);
        }
    }
    @PostMapping("/groupsettime")
    public String groupSetTime(String standards, String basiss, String degrees, String doctors, String date){

        boolean isok = false;
        try {
            isok = joinGroupService.saveGroupSetTime(standards, basiss, degrees, doctors, date);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(isok);
        }
    }

}
