package com.follow.common;

import lombok.Data;

import java.util.List;

@Data
public class JSONResult<T> {

    /**
     * 状态吗
     */
    private Integer code ;

    /**
     * 消息
     */
    private String msg ;

    /**
     * 总条数
     */
    private Long count ;

    /**
     * 数据
     */
    private List<T> data;

    public JSONResult(ResultEum resultEum, Long count, List<T> data) {
        this.code = resultEum.getCode();
        this.msg = resultEum.getContent();
        this.count = count;
        this.data = data;
    }
}
