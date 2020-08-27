package com.follow.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.follow.common.EmptyUtils;
import com.follow.common.ExportExcelUtil;
import com.follow.dto.DataUtil;
import com.follow.entity.*;
import com.follow.mapper.*;
import com.follow.service.FollowUpPorgressService;
import com.follow.vo.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/17
 */
@Service
public class FollowUpPorgressServiceImpl implements FollowUpPorgressService {

    private final Integer KEY_YI = 1;
    private final Integer KEY_ER  = 2;
    private final Integer KEY_SAN = 3;
    private final Integer KEY_SI  = 4;
    private final Integer KEY_WU  = 5;
    private final Integer KEY_LIU = 6;
    private final Integer KEY_QI = 7;

    @Autowired
    private FollowUpPorgressMapper followUpPorgressMapper;
    @Autowired
    private FollowgroupMapper followgroupMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private JoinGroupProgressMapper joinGroupProgressMapper;
    @Autowired
    private  JoinGroupMapper joinGroupMapper;

    @Override
    public List<FollowUpProgressVO> list(Integer page,Integer limit,Integer desk, String groupName, String dates, Integer admissionNumber, String name, Integer state) {
        String beginDate = null;
        String endDate = null;
        if(EmptyUtils.isNotEmpty(dates)){
            String[] split = dates.split(" - ");
            beginDate = split[0];
            endDate = split[1];
        }
        Integer startPage = (page -1 ) * limit;
        return followUpPorgressMapper.selectList(beginDate,endDate,desk,groupName,admissionNumber,name,state,startPage, limit);

    }

    @Override
    public List<FollowUpCheckVO> getList(Integer id) {
        FollowUpCheckVO followUpCheckVO = new FollowUpCheckVO();

        Patient patient = patientMapper.selectByokId(id);
        QueryWrapper<Check> checkQueryWrapper = new QueryWrapper<>();
        checkQueryWrapper.eq("patient_id",id);
        Check checks = checkMapper.selectOne(checkQueryWrapper);
        ArrayList<FollowUpCheckVO> followUpCheckVOS = new ArrayList<>();
        if(EmptyUtils.isNotEmpty(checks)){
            followUpCheckVO.setId(checks.getId());
            followUpCheckVO.setAbbr(checks.getAbbr());
            followUpCheckVO.setHeight(checks.getHeight());
            followUpCheckVO.setWeigth(checks.getWeigth());
            followUpCheckVO.setHemameba(checks.getHemameba());
            followUpCheckVO.setGlucometer(checks.getGlucometer());
        }
        if(EmptyUtils.isNotEmpty(patient)){
            followUpCheckVO.setPatientId(id);
            followUpCheckVO.setName(patient.getPatientName());
            followUpCheckVO.setSex(patient.getSex()==1?"男":"女");
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String localTime = df.format(patient.getBirthday());
            followUpCheckVO.setBirthday(localTime.substring(0,11));
            followUpCheckVO.setPhone(patient.getPhone());
        }

        followUpCheckVOS.add(followUpCheckVO);
        return followUpCheckVOS;
    }

    @Override
    public boolean modificationList(FollowUpCheckVO vo) {

        QueryWrapper<Check> checkQueryWrapper = new QueryWrapper<>();
        checkQueryWrapper.eq("patient_id",vo.getPatientId());
        Check checks = checkMapper.selectOne(checkQueryWrapper);
        Check check = new Check();
        check.setPatientId(vo.getPatientId());
        check.setHeight(vo.getHeight());
        check.setWeigth(vo.getWeigth());
        check.setAbbr(vo.getAbbr());
        check.setHemameba(vo.getHemameba());
        check.setGlucometer(vo.getGlucometer());
        int insert = 0;
        if(EmptyUtils.isNotEmpty(checks)){
            check.setId(vo.getId());
            insert =checkMapper.updateById(check);
        }else {
            insert = checkMapper.insert(check);
        }
        return insert > 0;
    }

    @Override
    public List<FollowUpResultVO> followUpResult() {
        List<FollowUpResultVO> list = followUpPorgressMapper.selectFollowUPStateList();
        return list;
    }

    @Override
    public Workbook exportExcel(String name) throws IOException {
        ExportExcelUtil<FollowUpResultVO> excelUtil = new ExportExcelUtil<>();
        String[] henders = {"随访时间","随访组","科室","随访状态"};
        List<FollowUpResultVO> list = followUpPorgressMapper.selectFollowUPStateList();
        Workbook export = null;
        if(EmptyUtils.isNotEmpty(list)){
            ArrayList<FollowUpResultVO> followUpResultVOS = new ArrayList<>();
            for (FollowUpResultVO followUpResultVO : list) {
                FollowUpResultVO followUpResultVO1 = new FollowUpResultVO();
                followUpResultVO1.setTime(followUpResultVO.getTime());
                followUpResultVO1.setName(followUpResultVO.getName());
                followUpResultVO1.setDesk(followUpResultVO.getDesk());
                followUpResultVO1.setState("1".equals(followUpResultVO.getState())?"已随访":"未随访");
                followUpResultVOS.add(followUpResultVO1);
            }
             export = excelUtil.export(henders, name, followUpResultVOS);
        }
        return export;
    }

    @Override
    public DataUtil<JoinGroupProgress> selectjoinGroupProgress(Integer id) {
        JoinGroupProgress joinGroupProgress = joinGroupProgressMapper.selectjoinGroupProgress(id);
        DataUtil<JoinGroupProgress> dataUtil = new DataUtil<>();
        ArrayList<JoinGroupProgress> joinGroupProgresses = new ArrayList<>();
        joinGroupProgresses.add(joinGroupProgress);
        dataUtil.setData(joinGroupProgresses);
        return dataUtil;
    }

    @Override
    public boolean getByTreatName(String treatname,Integer id) {
        JoinGroupProgress joinGroupProgress = joinGroupProgressMapper.selectjoinGroupProgress(id);
        int i = -1 ;
        if(EmptyUtils.isNotEmpty(joinGroupProgress)){
            JoinGroupProgress joinGroupProgress1 = new JoinGroupProgress();
            joinGroupProgress1.setId(joinGroupProgress.getId());

            if(EmptyUtils.isEmpty(joinGroupProgress.getTreatmentScheme())){
                joinGroupProgress1.setTreatmentScheme(treatname);
                i = joinGroupProgressMapper.updateById(joinGroupProgress1);
            }else {
                String[] split = joinGroupProgress.getTreatmentScheme().split(",");
                String treatnames = ","+treatname;
                for (String s : split) {
                    if(s.equals(treatname)){
                        treatnames = "";
                        break;
                    }
                }
                joinGroupProgress1.setTreatmentScheme(joinGroupProgress.getTreatmentScheme()+treatnames);
                i = joinGroupProgressMapper.updateById(joinGroupProgress1);
            }
        }


        return i > 0;
    }

    @Override
    public List<FollowUpTheRateVO> theRatelist(String principal, Integer desk, Integer state, String time,Integer page,Integer limit) {
        String begintime ="";
        String endtime ="";
        if(EmptyUtils.isNotEmpty(time)){
           String[] split = time.split(" , ");
            begintime = split[0];
            endtime = split[1];
        }
        Integer startPage = (page-1) * limit;
        List<FollowUpTheRateVO> theRatelist = followUpPorgressMapper.getTheRatelist(principal, desk, state, begintime, endtime ,startPage,limit);

        for (FollowUpTheRateVO followUpTheRateVO : theRatelist) {
            QueryWrapper<JoinGroup> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("group_name",followUpTheRateVO.getTitle());
            Integer integer = joinGroupMapper.selectCount(queryWrapper);
            if(EmptyUtils.isNotEmpty(followUpTheRateVO.getRate())){
                double dividend= integer;
                double divisor =followUpTheRateVO.getRate();
                double consult = dividend/divisor*100;
                consult = (double) Math.round(consult * 100) / 100;
                String d = consult+"";
                followUpTheRateVO.setRates(d);
            }else {
                followUpTheRateVO.setRates("0.0");
            }
        }

        return theRatelist;
    }

    @Override
    public DataUtil<BasicDataVO> getByBasicDataId(Integer id) {
        DataUtil<BasicDataVO> basicDataVODataUtil = new DataUtil<>();

        ArrayList<BasicDataVO> list = new ArrayList<>();
        BasicDataVO basicDataVO = new BasicDataVO();
        Followgroup followgroup = followgroupMapper.selectById(id);
        basicDataVO.setTable("患者总数");
        System.out.println(followgroup.getCreateTime()+"时间");
        basicDataVO.setBegin(new SimpleDateFormat("yyyy-MM-dd").format(followgroup.getCreateTime()));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localTime = df.format(LocalDateTime.now());
        basicDataVO.setEnd(localTime.substring(0,11));
        basicDataVO.setCount(followgroup.getPatientsnumber());

        QueryWrapper<JoinGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_name",followgroup.getFName());
        Integer integer = joinGroupMapper.selectCount(queryWrapper);

        double dividend= integer;
        double divisor = 0;
        if(EmptyUtils.isNotEmpty(followgroup.getPatientsnumber())){
            divisor =followgroup.getPatientsnumber();
        }
        double consult = dividend/divisor*100;
        consult = (double) Math.round(consult * 100) / 100;
        String d = consult+"%";
        basicDataVO.setRatio(d);
        BasicDataVO basicDataVO2 = new BasicDataVO();
        basicDataVO2.setTable("基本信息");
        basicDataVO2.setBegin(new SimpleDateFormat("yyyy-MM-dd").format(followgroup.getCreateTime()));
        basicDataVO2.setEnd(localTime.substring(0,11));
        basicDataVO2.setCount(followgroup.getPatientsnumber());
        basicDataVO2.setRatio("100%");
        list.add(basicDataVO);
        list.add(basicDataVO2);
        List<BasicDataVO> basicDataVOS = followUpPorgressMapper.getbasicDataByIdList(id);
        for (BasicDataVO dataVO : basicDataVOS) {
            list.add(dataVO);
        }
        basicDataVODataUtil.setCode(0);
        basicDataVODataUtil.setMsg("成功");
        basicDataVODataUtil.setData(list);

        return basicDataVODataUtil;
    }

    @Override
    public DataUtil<FollowUpQueryVO> getQueryList(Integer page, Integer limit, String array) {
        String sql ="";
        StringBuffer stringBuffer = new StringBuffer();
        if(EmptyUtils.isNotEmpty(array)){
            String[] split = array.split("=");
            for (int i = 0; i < split.length; i++) {
                String[] strings = split[i].toString().split(",");
                if(i==0){
                    if(EmptyUtils.isNotEmpty(strings[1])
                            && EmptyUtils.isNotEmpty(strings[2])
                            && EmptyUtils.isNotEmpty(strings[3])){
                        String val = "";
                        if("0".equals(strings[2])){
                            val = " > "+strings[3];
                        }
                        if("1".equals(strings[2])){
                            val = " = '"+strings[3]+"' ";
                        }
                        if("2".equals(strings[2])){
                            val = " < "+strings[3];
                        }
                        if("3".equals(strings[2])){
                            val = " like '%"+strings[3]+"%' ";
                        }
                        sql = " and "+strings[1]+" "+val;

                    }
                }else {
                    if(EmptyUtils.isNotEmpty(strings[2])
                            && EmptyUtils.isNotEmpty(strings[3])
                            && EmptyUtils.isNotEmpty(strings[4])){
                        String val = "";
                        if("0".equals(strings[3])){
                            val = "> "+strings[4];
                        }
                        if("1".equals(strings[3])){
                            val = " = '"+strings[4]+"' ";
                        }
                        if("2".equals(strings[3])){
                            val = " < "+strings[4]+" ";
                        }
                        if("3".equals(strings[3])){
                            val = " like '%"+strings[4]+"%' ";

                        }
                        sql = " "+strings[1]+" "+strings[2]+" "+val;
                    }
                }
                stringBuffer.append(sql);
            }
        }
        String string = stringBuffer+"";
        StringBuffer buffer = new StringBuffer();
        String[] ids = null;
        if(EmptyUtils.isNotEmpty(string)){
            List<FollowUpQueryVO> vOS = followUpPorgressMapper.getbyPatientId(string);
            for (FollowUpQueryVO vO : vOS) {
                if (buffer.length()>0){
                    buffer.append(",");
                }
                buffer.append(vO.getId());
            }
            ids = buffer.toString().split(",");
        }
        int startPage = (page - 1 ) * limit;
        List<FollowUpQueryVO> list = followUpPorgressMapper.selectQueryList(startPage,limit,ids);
        int count = 0;
        if(EmptyUtils.isNotEmpty(limit)){
            count = list.size();
        }
        DataUtil<FollowUpQueryVO> dataUtil = new DataUtil<>();
        dataUtil.setCode(0);
        dataUtil.setMsg("成功");
        dataUtil.setCount(count);
        dataUtil.setData(list);
        return dataUtil;
    }


    @Override
    public DataUtil<DateCalendarVO> getdateCalendar() {

        DataUtil<DateCalendarVO> dataUtil = new DataUtil<>();

        List<CalendarVO> calendarVOS = patientMapper.selectNumList();
        List<DateCalendarVO> list = new ArrayList<>();

        List<DateCalendarVO> lists = new ArrayList<>();

        for (CalendarVO calendarVO : calendarVOS) {
            DateCalendarVO vo = new DateCalendarVO();
            DateCalendarVO vo1 = new DateCalendarVO();

            String y = calendarVO.getActual().substring(0, 4);
            String m1 = calendarVO.getActual().substring(5, 6);
            String d = calendarVO.getActual().substring(8, 10);
            String mm =  calendarVO.getActual().substring(6, 7);
            Integer m = Integer.parseInt(mm)-1;

            Integer state = patientMapper.selectCountState(calendarVO.getActual());
            Integer integer = patientMapper.selectCountsum(calendarVO.getActual());
            List<CalendarVO> patientNames = patientMapper.selectPatientNameList(calendarVO.getActual());

            double dividend= state;
            double divisor =integer;
            double consult = dividend/divisor*100;
            consult = (double) Math.round(consult * 100) / 100;
            String t = "计划随访 : "+integer+" 实际随访 : "+state+" 随访进度:  "+consult+"%    ";
            String tt="";
            if(EmptyUtils.isNotEmpty(patientNames)){
                 StringBuffer buffer = new StringBuffer();
                 for (int i = 0; i < patientNames.size(); i++) {
                     if(buffer.length()>0){
                         buffer.append(" ");
                     }
                     buffer.append(i+1+":"+patientNames.get(i).getProgress());
                 }
                 tt= "患者列表 : "+buffer;
             }
            vo.setT(t);
            vo.setY(y);
            vo.setM(m1+m);
            vo.setD(d);
            vo1.setT(tt);
            vo1.setY(y);
            vo1.setM(m1+m);
            vo1.setD(d);
            list.add(vo);
            list.add(vo1);
        }
        dataUtil.setCode(0);
        dataUtil.setMsg("");
        dataUtil.setCount(0);
        dataUtil.setData(list);
        return dataUtil;
    }

}
