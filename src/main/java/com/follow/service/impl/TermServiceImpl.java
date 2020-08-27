package com.follow.service.impl;

import com.follow.entity.TermInformation;
import com.follow.mapper.TermMapping;
import com.follow.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermMapping termMapping ;

    public List<TermInformation> queryTermInformation(){
        List<TermInformation> termInformationList = new ArrayList<>();
        termInformationList.addAll(termMapping.selectTermInformationAll());
        return termInformationList ;
    }

    @Transactional
    @Override
    public String addByupdateDataInformation(String[] dataValue, String[] dataId, String userId) {
        String tishi = "" ;
        for (int i = 0 ; i < dataId.length ; i ++) {
            Integer integer = termMapping.selectDataInformation(dataId[i], userId);
            if (integer > 0){
                Integer updateIngert = termMapping.updateDataInformation(dataValue[i], dataId[i], userId);
                if (updateIngert > 0){
                    tishi = "修改成功";
                }else{
                    tishi = "修改失败" ;
                }
            }else{
                Integer insertInteger = termMapping.insertDataInformation(dataValue[i], dataId[i], userId);
                if (insertInteger>0){
                    tishi = "添加成功" ;
                }else{
                    tishi = "添加失败" ;
                }
            }
        }
        return tishi ;
    }

}
