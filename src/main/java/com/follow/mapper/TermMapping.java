package com.follow.mapper;

import com.follow.entity.TermInformation;

import java.util.List;

public interface TermMapping {


    List<TermInformation> selectTermInformationAll();

    List<TermInformation> selectEssentialInformationAll();

    List<TermInformation> selectHomepageInformationAll();

    List<TermInformation> selectOperationInformationAll();
}
