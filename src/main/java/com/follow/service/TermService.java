package com.follow.service;

import com.follow.entity.TermInformation;
import com.follow.vo.DataInromationByUserVo;

import java.util.List;

public interface TermService {

    List<TermInformation> queryTermInformation();

    String addByupdateDataInformation(String[] dataValue , String[] dataId , String userId);

    List<DataInromationByUserVo> qeruyDataInformation(Integer page , Integer limit , String userId);

    String deleteInromationById(String id);
}
