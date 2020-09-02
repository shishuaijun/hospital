package com.follow.service.impl;

import com.follow.common.EmptyUtils;
import com.follow.entity.TermInformation;
import com.follow.mapper.TermMapping;
import com.follow.service.TermService;
import com.follow.vo.DataInromationByUserVo;
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

//            Integer integer = termMapping.selectDataInformation(dataId[i], userId);
            DataInromationByUserVo integer = termMapping.selectDataInformation(dataId[i], userId);
//            if (integer > 0){
            if(EmptyUtils.isNotEmpty(integer)){
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

    @Override
    public List<DataInromationByUserVo> qeruyDataInformation(Integer page, Integer limit, String userId) {

        Integer pagas = (page-1)*limit ;

        List<DataInromationByUserVo> dataInromationByUserVos = termMapping.selectDataInformationAll(pagas, limit, userId);
        if(EmptyUtils.isEmpty(dataInromationByUserVos))
            System.out.println("kong========saddddddddddddddd");
        else {
            for (DataInromationByUserVo dataInromationByUserVo : dataInromationByUserVos) {
                System.out.println(dataInromationByUserVo);
            }
        }
        return dataInromationByUserVos  ;
    }

    @Transactional
    @Override
    public String deleteInromationById(String id){
        Integer integer = termMapping.deleteDataInformation(id);
        if (integer>0){
            return "删除成功" ;
        }else{
            return "删除失败" ;
        }
    }
}
