package com.follow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.follow.common.EmptyUtils;
import com.follow.common.ExportExcelUtil;
import com.follow.dto.DataUtil;
import com.follow.entity.Check;
import com.follow.entity.JoinGroupProgress;
import com.follow.entity.Patient;
import com.follow.mapper.CheckMapper;
import com.follow.mapper.FollowUpPorgressMapper;
import com.follow.mapper.JoinGroupProgressMapper;
import com.follow.mapper.PatientMapper;
import com.follow.service.FollowUpPorgressService;
import com.follow.vo.FollowUpCheckVO;
import com.follow.vo.FollowUpProgressVO;
import com.follow.vo.FollowUpResultVO;
import com.follow.vo.FollowUpTheRateVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/17
 */
@Service
public class FollowUpPorgressServiceImpl implements FollowUpPorgressService {

    @Autowired
    private FollowUpPorgressMapper followUpPorgressMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private JoinGroupProgressMapper joinGroupProgressMapper;

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

        Patient patient = patientMapper.selectById(id);
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
        System.out.println(page);
        System.out.println(limit);
        System.out.println(principal);
        System.out.println(desk);
        System.out.println(state);
        System.out.println(time);
        String begintime ="2020-08-11";
        String endtime ="2020-08-20";
        Integer startPage = (page-1) * limit;
        List<FollowUpTheRateVO> theRatelist = followUpPorgressMapper.getTheRatelist(principal, desk, state, begintime, endtime ,startPage,limit);
        return theRatelist;
    }
}
