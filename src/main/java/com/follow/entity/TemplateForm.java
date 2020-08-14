package com.follow.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangchunjun
 * @date 2020/8/13
 */
@Data
@TableName(value = "template_form")
public class TemplateForm  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @TableField(value = "basics_content")
    private String basicsContent;

    private String content;

    private Integer version;

    private Integer state;

    @TableField(value = "modify_man")
    private String modifyMan;

    @TableField(value = "creation_time")
    private LocalDateTime creationTime;

    @TableField(value = "modify_time")
    private LocalDateTime modifyTime;

    @TableField(value = "is_input_data")
    private Integer isInputData;

    @TableField(value = "is_delete",select = false)
    @TableLogic
    private Integer isDelete;

}
