package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ssj
 * @date 2020/8/6
 */
@Data
public class Dictionaries implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 字典名称
     */
    @TableField(value="dictionaries_name")
    private String dictionariesName;

    /**
     * 值编码
     */
    @TableField(value="dictionaries_cod")
    private String dictionariesCod;


    /**
     *  值名称
     */
    @TableField(value="dictionaries_cod_name")
    private String dictionariesCodName;

    /**
     *  顺序
     */
    @TableField(value="shunxu")
    private String shunxu;


}
