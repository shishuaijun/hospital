package com.follow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.follow.entity.Result;
import com.follow.mapper.ResultMapper;
import com.follow.service.ResultService;
import org.springframework.stereotype.Service;

/**
 * @author wangchunjun
 * @date 2020/8/10
 */
@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result> implements ResultService {
}
