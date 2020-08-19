package com.follow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.follow.entity.Dictionaries;
import com.follow.entity.Disease;
import com.github.pagehelper.PageInfo;

/**
 * @author ssj
 * @date 2020/8/14
 */

public interface DictionariesService extends IService<Dictionaries> {

    PageInfo<Dictionaries> findByConditions1(String dictionariesName, String dictionariesCod, String dictionariesCodName, String shunxu, Integer page, Integer limit);
}
