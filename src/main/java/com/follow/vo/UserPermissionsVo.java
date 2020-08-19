package com.follow.vo;

import lombok.Data;

@Data
public class UserPermissionsVo {

    private Long id ;

    private String userName ;

    /**
     * 是否是被选中的（0：否，1：是）
     */
    private Integer isSelect ;


}
