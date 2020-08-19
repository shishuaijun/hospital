package com.follow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.follow.entity.Dictionaries;
import com.follow.entity.TemplateForm;
import com.follow.mapper.DictionariesMapper;
import com.follow.service.DictionariesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Zuan~
 * @version 1.0
 * @ClassName: DictionariesServiceImpl
 * @description: TODO[  ]
 * @date: 2020/8/14  16:07
 */
@Service
public class DictionariesServiceImpl extends ServiceImpl<DictionariesMapper, Dictionaries> implements DictionariesService {

    @Autowired
    private DictionariesMapper dictionariesMapper;

    @Override
    public PageInfo<Dictionaries> findByConditions1(String dictionariesName, String dictionariesCod, String dictionariesCodName, String shunxu, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Dictionaries> list = dictionariesMapper.findByConditions1(dictionariesName,dictionariesCod, dictionariesCodName, shunxu, page, limit);

        PageInfo<Dictionaries> pageInfo = new PageInfo<Dictionaries>(list);

        return pageInfo;
    }
}
