package com.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医院名称
     */
    private String hospitalName;


}
