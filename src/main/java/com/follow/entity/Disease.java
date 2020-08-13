package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Data
public class Disease implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 疾病编码
     */
    @TableField(value="disease_number")
    private String diseaseNumber;

    /**
     * 疾病名称
     */
    @TableField(value="disease_name")
    private String diseaseName;


    /**
     *  疾病id 的 父类id
     */
    @TableField(value="pid")
    private Integer pid;


}
