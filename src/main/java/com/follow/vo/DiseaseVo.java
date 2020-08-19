package com.follow.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.follow.entity.Disease;
import com.follow.entity.TreatmentPlan;

import java.util.List;

/**
 * @author Zuan~
 * @version 1.0
 * @ClassName: DiseaseVo
 * @description: TODO[  ]
 * @date: 2020/8/13  13:54
 */
public class DiseaseVo {

    private Disease disease;
    private List<TreatmentPlan> tlist;


}
