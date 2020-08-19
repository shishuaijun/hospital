package com.follow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.follow.common.EmptyUtils;
import com.follow.common.ExportExcelUtil;
import com.follow.common.ReaderExcelUtil;
import com.follow.entity.*;
import com.follow.mapper.*;
import com.follow.service.JoinGroupService;
import com.follow.vo.CustomPatientVO;
import com.follow.vo.CustomVo;
import com.follow.vo.ImportPatientVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 入组管理  业务实现层
 * @author wangchunjun
 * @date 2020/8/6
 */
@Service
public class JoinGroupServiceImpl extends ServiceImpl<JoinGroupMapper, JoinGroup> implements JoinGroupService {
    String KEY_DESK = "科室";
    String KEY_ILLNESS = "疾病";
    @Autowired
    private JoinGroupMapper joinGroupMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private DiseaseMapper diseaseMapper;
    @Autowired
    private PatientControlkMapper patientControlkMapper;
    @Autowired
    private PatientOperationInformationMapper patientOperationInformationMapper;
    @Autowired
    private PatientAdmissionInformationMapper patientAdmissionInformationMapper;
    @Autowired
    private ResultMapper resultMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private JoinGroupTimeMapper joinGroupTimeMapper ;
    @Autowired
    private JoinGroupProgressMapper joinGroupProgressMapper;

    @Override
    public boolean intoTheGroup(Integer desk, String illnessCoded, String illnessName, String array) {

        // 科室 条件
        if(EmptyUtils.isNotEmpty(desk)){
            QueryWrapper<Patient> patientQueryWrapper = new QueryWrapper<>();
            patientQueryWrapper.eq("department_id",desk);
            Department department = departmentMapper.selectById(desk);

            QueryWrapper<Result> resultQueryWrapper = new QueryWrapper<>();
            resultQueryWrapper.eq("result",department.getDepartmentName());
            resultQueryWrapper.eq("name",KEY_DESK);
            Result result1 = resultMapper.selectOne(resultQueryWrapper);
            if(EmptyUtils.isEmpty(result1)){
                Result result = new Result();
                result.setResult(department.getDepartmentName());
                result.setName(KEY_DESK);
                resultMapper.insert(result);
            }
            List<Patient> patients = patientMapper.selectList(patientQueryWrapper);
            // 患者入组、更改患者状态(复用）
            addUpateJoinGroup(patients, KEY_DESK);

        }
        // 病种 条件
        if(EmptyUtils.isNotEmpty(illnessCoded) || EmptyUtils.isNotEmpty(illnessName)){
            QueryWrapper<Disease> diseaseQueryWrapper = new QueryWrapper<>();
            if(EmptyUtils.isNotEmpty(illnessCoded)){
                diseaseQueryWrapper.like("disease_number",illnessCoded);
            }
            if(EmptyUtils.isNotEmpty(illnessName)) {
                diseaseQueryWrapper.like("disease_name", illnessName);
                QueryWrapper<Result> resultQueryWrapper = new QueryWrapper<>();
                resultQueryWrapper.eq("result",illnessName);
                resultQueryWrapper.eq("name",KEY_ILLNESS);
                Result result1 = resultMapper.selectOne(resultQueryWrapper);
                if(EmptyUtils.isEmpty(result1)){
                    Result result = new Result();
                    result.setResult(illnessName);
                    result.setName(KEY_ILLNESS);
                    resultMapper.insert(result);
                }
            }
            List<Disease> diseases = diseaseMapper.selectList(diseaseQueryWrapper);
            for (Disease disease : diseases) {
                QueryWrapper<Patient> patientQueryWrapper = new QueryWrapper<>();
                patientQueryWrapper.eq("disease_id",disease.getId());
                List<Patient> patients = patientMapper.selectList(patientQueryWrapper);
                // 患者入组、更改患者状态(复用）
                addUpateJoinGroup(patients,"病种");
            }
        }
        // 人员 条件
        if(EmptyUtils.isNotEmpty(array)){
            String[] split = array.split(",");
            for (int i = 0; i < split.length; i++) {
                System.out.println(split[i]);
                QueryWrapper<PatientControlk> patientControlkQueryWrapper = new QueryWrapper<>();
                patientControlkQueryWrapper.eq("d_n_p_id",split[i]);
                List<PatientControlk> patientControlks = patientControlkMapper.selectList(patientControlkQueryWrapper);
                if(EmptyUtils.isNotEmpty(patientControlks)){
                    for (PatientControlk patientControlk : patientControlks) {

                        JoinGroup joinGroup = new JoinGroup();
                        // 入组名称 获取
                        joinGroup.setGroupName("根据当前用户获取");
                        // 规则
                        joinGroup.setRule("人员");
                        // 患者id
                        joinGroup.setPatientControlId(patientControlk.getPatientId());
                        joinGroup.setGroupTime(LocalDateTime.now());
                        joinGroupMapper.insert(joinGroup);
                        patientMapper.deleteById(patientControlk.getPatientId());
                        JoinGroupProgress joinGroupProgress = new JoinGroupProgress();
                        joinGroupProgress.setPatientId(patientControlk.getPatientId());
                        joinGroupProgress.setCondition(0);
                        joinGroupProgress.setNextDate(LocalDateTime.now());
                        joinGroupProgressMapper.insert(joinGroupProgress);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean intoTheGroups(CustomPatientVO patient) {
        QueryWrapper<Patient> patientQueryWrapper = new QueryWrapper<>();
        boolean a = false;
        if(EmptyUtils.isNotEmpty(patient.getId())){
            patientQueryWrapper.eq("id",patient.getId());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getPatientName())){
            patientQueryWrapper.eq("patient_name",patient.getPatientName());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getSex())){
            patientQueryWrapper.eq("sex",patient.getSex());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getBirthday())){
            patientQueryWrapper.eq("birthday",patient.getBirthday());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getAddress())){
            patientQueryWrapper.eq("address",patient.getAddress());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getEducationId())){
            patientQueryWrapper.eq("education_id",patient.getEducationId());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getPhone())){
            patientQueryWrapper.eq("phone",patient.getPhone());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getEmail())){
            patientQueryWrapper.eq("email",patient.getEmail());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getBloodtype())){
            patientQueryWrapper.eq("bloodtype",patient.getBloodtype());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getIdnumber())){
            patientQueryWrapper.eq("idnumber",patient.getIdnumber());
            a = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getLink())){
            patientQueryWrapper.eq("link",patient.getLink());
            a = true;
        }
        List<Patient> patients = null;
        if(a){
            patients = patientMapper.selectList(patientQueryWrapper);
        }
        // 患者入组、更改患者状态(复用）
        addUpateJoinGroup(patients,"自定义-基本信息");
        boolean b =false;
        QueryWrapper<PatientOperationInformation> oQueryWrapper = new QueryWrapper<>();
        if(EmptyUtils.isNotEmpty(patient.getTermOfOperation())){
            oQueryWrapper.eq("term_of_operation",patient.getTermOfOperation());
            b= true;
        }
        if(EmptyUtils.isNotEmpty(patient.getDateOfSurgery())){
            oQueryWrapper.eq("date_of_surgery",patient.getDateOfSurgery());
            b= true;
        }
        if(EmptyUtils.isNotEmpty(patient.getOperationDepartment())){
            oQueryWrapper.eq("operation_department",patient.getOperationDepartment());
            b= true;
        }
        if(EmptyUtils.isNotEmpty(patient.getOperationStartTime())){
            LocalDateTime dateTime = transformTime(patient.getDischargeTime());
            oQueryWrapper.eq("operation_start_time",dateTime);
            b= true;
        }
        if(EmptyUtils.isNotEmpty(patient.getOperationStopTime())){
            LocalDateTime dateTime = transformTime(patient.getDischargeTime());
            oQueryWrapper.eq("operation_stop_time",dateTime);
            b= true;
        }
        if(EmptyUtils.isNotEmpty(patient.getSurgicalSpot())){
            oQueryWrapper.eq("surgical_spot",patient.getSurgicalSpot());
            b= true;
        }
        if(b) {
            List<PatientOperationInformation> patientOperationInformations = patientOperationInformationMapper.selectList(oQueryWrapper);
            if(EmptyUtils.isNotEmpty(patientOperationInformations)) {
                for (PatientOperationInformation patientOperationInformation : patientOperationInformations) {
                    JoinGroup joinGroup = new JoinGroup();
                    // 入组名称 获取
                    joinGroup.setGroupName("根据当前用户获取");
                    // 规则
                    joinGroup.setRule("自定义-手术信息");
                    // 患者id
                    joinGroup.setPatientControlId(patientOperationInformation.getPatientId());
                    joinGroup.setGroupTime(LocalDateTime.now());
                    joinGroupMapper.insert(joinGroup);
                    patientMapper.deleteById(patientOperationInformation.getPatientId());
                    JoinGroupProgress joinGroupProgress = new JoinGroupProgress();
                    joinGroupProgress.setPatientId(patient.getId());
                    joinGroupProgress.setCondition(0);
                    joinGroupProgress.setNextDate(LocalDateTime.now());
                    joinGroupProgressMapper.insert(joinGroupProgress);
                }
            }
        }
        boolean c = false;
        QueryWrapper<PatientAdmissionInformation> aQueryWrapper = new QueryWrapper<>();
        if(EmptyUtils.isNotEmpty(patient.getDiagnosis())){
            aQueryWrapper.eq("diagnosis",patient.getDiagnosis());
            c =true;
        }
        if(EmptyUtils.isNotEmpty(patient.getHospitalWay())){
            aQueryWrapper.eq("hospital_way",patient.getHospitalWay());
            c =true;
        }
        if(EmptyUtils.isNotEmpty(patient.getDesk())){
            aQueryWrapper.eq("desk",patient.getDesk());
            c =true;
        }
        if(EmptyUtils.isNotEmpty(patient.getHospitalDays())){
            aQueryWrapper.eq("hospital_days",patient.getHospitalDays());
            c =true;
        }
        if(EmptyUtils.isNotEmpty(patient.getAdmissionTime())){
            LocalDateTime dateTime = transformTime(patient.getAdmissionTime());
            aQueryWrapper.eq("admission_time",dateTime);
            c =true;
        }
        if(EmptyUtils.isNotEmpty(patient.getDischargeTime())){
            LocalDateTime dateTime = transformTime(patient.getDischargeTime());
            aQueryWrapper.eq("discharge_time",dateTime);
            c =true;
        }
        if(EmptyUtils.isNotEmpty(patient.getIsDie())){
            aQueryWrapper.eq("is_die",patient.getIsDie());
            c =true;
        }
        if(c){
            List<PatientAdmissionInformation> patientAdmissionInformations = patientAdmissionInformationMapper.selectList(aQueryWrapper);
            if(EmptyUtils.isNotEmpty(patientAdmissionInformations)) {
                for (PatientAdmissionInformation patientOperationInformation : patientAdmissionInformations) {

                    System.out.println(patientOperationInformation.getPatientId());
                    JoinGroup joinGroup = new JoinGroup();
                    // 入组名称 获取
                    joinGroup.setGroupName("根据当前用户获取");
                    // 规则
                    joinGroup.setRule("自定义-住院信息");
                    // 患者id
                    joinGroup.setPatientControlId(patientOperationInformation.getPatientId());
                    joinGroup.setGroupTime(LocalDateTime.now());
                    joinGroupMapper.insert(joinGroup);
                    patientMapper.deleteById(patientOperationInformation.getPatientId());

                    JoinGroupProgress joinGroupProgress = new JoinGroupProgress();
                    joinGroupProgress.setPatientId(patientOperationInformation.getPatientId());
                    joinGroupProgress.setCondition(0);
                    joinGroupProgress.setNextDate(LocalDateTime.now());
                    joinGroupProgressMapper.insert(joinGroupProgress);
                }
            }
        }
        return true;
    }

    @Override
    public boolean getResult(Integer id) {
        boolean isok = false;
        Result result = resultMapper.selectById(id);
        if(KEY_DESK.equals( result.getName())){
            String deskName = result.getResult();
            QueryWrapper<Department> deskQueryWrapper = new QueryWrapper<>();
            deskQueryWrapper.eq("department_name",deskName);
            Department department = departmentMapper.selectOne(deskQueryWrapper);
            Integer deskId = department.getId();
            QueryWrapper<Patient> patientQueryWrapper = new QueryWrapper<>();
            patientQueryWrapper.eq("department_id",deskId);
            List<Patient> patients = patientMapper.selectList(patientQueryWrapper);
            // 患者入组、更改患者状态(复用）
            addUpateJoinGroup(patients, KEY_DESK);
            isok =true;
        }else if(KEY_ILLNESS.equals( result.getName())){
            String diseaseName = result.getResult();
            QueryWrapper<Disease> diseaseQueryWrapper = new QueryWrapper<>();
            diseaseQueryWrapper.eq("disease_name",diseaseName);
            Disease disease = diseaseMapper.selectOne(diseaseQueryWrapper);
            Integer diseaseId = disease.getId();

            QueryWrapper<Patient> patientQueryWrapper = new QueryWrapper<>();
            patientQueryWrapper.eq("disease_id",diseaseId);
            List<Patient> patients = patientMapper.selectList(patientQueryWrapper);
            // 患者入组、更改患者状态(复用）
            addUpateJoinGroup(patients, KEY_ILLNESS);
            isok =true;
        }
        return isok;
    }

    @Override
    public Workbook exportExcel(String name) throws IOException {
        String[] henders = {"姓名","性别","出生日期","出生地","电话","邮箱","身份证号","联系人"};
        ExportExcelUtil<ImportPatientVo> excelUtil = new ExportExcelUtil<>();
        ArrayList<ImportPatientVo> list = new ArrayList<>();
        ImportPatientVo vo = new ImportPatientVo();
        vo.setName("小明");
        vo.setSex("男");
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        vo.setBrithDate(format);
        vo.setBrithPlace("北京市");
        vo.setPhone("12345678910");
        vo.setEmail("123456789@qq.com");
        vo.setIdNumber("152527200001105342");
        vo.setLink("大明");
        list.add(vo);
        Workbook export = excelUtil.export(henders, name, list);
        return export;
    }

    @Override
    public boolean importExcel(String newPath) throws Exception{
        List<List<Object>> lists = ReaderExcelUtil.readerExcel(newPath);

        for (List<Object> list : lists) {
            Patient patient = new Patient();
            patient.setPatientName(list.get(0).toString());
            patient.setSex(list.get(1).toString()=="男"?1:0);
            String str = list.get(2).toString();
            if (str.length()<=10){
                str+=" 00:00:00";
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime parse = LocalDateTime.parse(str, dateTimeFormatter);
            patient.setBirthday(parse);
            patient.setAddress(list.get(3).toString());
            patient.setPhone(list.get(4).toString());
            patient.setEmail(list.get(5).toString());
            patient.setIdnumber(list.get(6).toString());
            patient.setLink(list.get(7).toString());
            patient.setCreateTime(LocalDateTime.now());
            patient.setModifyTime(LocalDateTime.now());
            patient.setIsJoingroup(1);
            patientMapper.insert(patient);
            JoinGroup joinGroup = new JoinGroup();
            joinGroup.setGroupName("根据当前用户获取");
            joinGroup.setRule("自定义/导入-导入");
            joinGroup.setPatientControlId(patient.getId());
            joinGroup.setGroupTime(LocalDateTime.now());
            joinGroupMapper.insert(joinGroup);
            JoinGroupProgress joinGroupProgress = new JoinGroupProgress();
            joinGroupProgress.setPatientId(patient.getId());
            joinGroupProgress.setCondition(0);
            joinGroupProgress.setNextDate(LocalDateTime.now());
            joinGroupProgressMapper.insert(joinGroupProgress);
        }
        return true;
    }

    @Override
    public boolean customPatient(CustomVo patient) {
        boolean isok =false;
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        if(EmptyUtils.isNotEmpty(patient.getPatientName())){
            wrapper.eq("patient_name",patient.getPatientName());
            isok = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getAdminssionnumber())){
            wrapper.eq("adminssionnumber",patient.getAdminssionnumber());
            isok = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getOutpaientnumber())){
            wrapper.eq("outpaientnumber",patient.getOutpaientnumber());
            isok = true;
        }
        if(EmptyUtils.isNotEmpty(patient.getBirthday())){
            wrapper.eq("birthday",patient.getBirthday());
            isok = true;
        }
        List<Patient> patients = null;
        if(isok){
            patients  = patientMapper.selectList(wrapper);
        }
        addUpateJoinGroup(patients,"自定义/导入-患者");
        return true;
    }


    @Override
    public boolean saveGroupSetTime(String standards, String basiss, String degrees, String doctors, String date) {

        JoinGroupTime joinGroupTime = new JoinGroupTime();
        joinGroupTime.setStandards(standards);
        if (EmptyUtils.isNotEmpty(basiss)){
            joinGroupTime.setBasiss(basiss=="本院"?1:2);
        }
        if (EmptyUtils.isNotEmpty(degrees)){
            joinGroupTime.setDegrees(degrees=="首次"?1:2);
        }
        if (EmptyUtils.isNotEmpty(doctors)){
            joinGroupTime.setDoctors(doctors=="本就诊"?1:2);
        }
        LocalDateTime dateTime = transformTime(date);
        joinGroupTime.setGroupDate(dateTime);

        return joinGroupTimeMapper.insert(joinGroupTime) > 0 ;
    }
    /**
     * 患者入组、更改患者状态 复用
     * @param patients
     * @param rule
     * @return
     */
    protected  void addUpateJoinGroup(List<Patient> patients,String rule){
        if (EmptyUtils.isNotEmpty(patients)){
            for (Patient patient : patients) {
                JoinGroup joinGroup = new JoinGroup();
                // 入组名称 获取
                joinGroup.setGroupName("根据当前用户获取");
                // 规则
                joinGroup.setRule(rule);
                // 患者id
                joinGroup.setPatientControlId(patient.getId());
                joinGroup.setGroupTime(LocalDateTime.now());
                joinGroupMapper.insert(joinGroup);
                patientMapper.deleteById(patient.getId());

                JoinGroupProgress joinGroupProgress = new JoinGroupProgress();
                joinGroupProgress.setPatientId(patient.getId());
                joinGroupProgress.setCondition(0);
                joinGroupProgress.setNextDate(LocalDateTime.now());
               joinGroupProgressMapper.insert(joinGroupProgress);


            }
        }
    }

    /**
     * 时间 处理  复用
     * @param date
     * @return
     */
    protected LocalDateTime transformTime(String date) {
        String[] ts = date.split("T");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ts.length; i++) {
            if (sb.length()>0){
                sb.append(" ");
            }
            sb.append(ts[i]);
        }
        sb.append(":00");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(sb, dateTimeFormatter);
    }
}

