package com.follow.service;

import com.follow.dto.DataUtil;
import com.follow.entity.JoinGroupProgress;
import com.follow.vo.FollowUpCheckVO;
import com.follow.vo.FollowUpProgressVO;
import com.follow.vo.FollowUpResultVO;
import com.follow.vo.FollowUpTheRateVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/17
 */
public interface FollowUpPorgressService {

    /**
     * 根据条件 查询
     * @param desk
     * @param groupName
     * @param dates
     * @param admissionNumber
     * @param name
     * @param state
     * @return
     */
    List<FollowUpProgressVO> list(Integer page,Integer limit,Integer desk, String groupName, String dates, Integer admissionNumber, String name,Integer state);

    /**
     * 根据患者id 查询 信息
     * @param id
     * @return
     */
    List<FollowUpCheckVO> getList(Integer id);

    /**
     * 修改患者id 查询 信息
     * @return
     */
    boolean modificationList(FollowUpCheckVO vo);

    /**
     * 查看随访组信息
     * @return
     */
    List<FollowUpResultVO> followUpResult();

    /**
     * 导出 随访组 信息
     * @return
     */
    Workbook exportExcel(String name)throws IOException;

    DataUtil<JoinGroupProgress> selectjoinGroupProgress(Integer id);

    /**
     * 选择 治疗方案
     * @param treatname
     * @return
     */
    boolean getByTreatName(String treatname,Integer id);

    /**
     * 查询 入组 进度 概况
     * @param principal
     * @param desk
     * @param state
     * @param time
     * @return
     */
    List<FollowUpTheRateVO> theRatelist(String principal, Integer desk, Integer state, String time,Integer page,Integer limit);
}
