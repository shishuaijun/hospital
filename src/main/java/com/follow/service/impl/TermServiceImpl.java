package com.follow.service.impl;

import com.follow.entity.TermInformation;
import com.follow.mapper.TermMapping;
import com.follow.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermMapping termMapping ;

    public List<TermInformation> queryTermInformation(){
        List<TermInformation> termInformationList = new ArrayList<>();
        termInformationList.addAll(termMapping.selectTermInformationAll());
        termInformationList.addAll(termMapping.selectEssentialInformationAll());
        termInformationList.addAll(termMapping.selectHomepageInformationAll());
        termInformationList.addAll(termMapping.selectOperationInformationAll());
        return termInformationList ;
    }

}
