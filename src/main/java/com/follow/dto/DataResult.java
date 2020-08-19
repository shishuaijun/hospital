package com.follow.dto;

import com.follow.common.ResultEum;
import lombok.Data;

import java.util.List;

@Data
public class DataResult<T> {

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
    private List<T> result;

    public DataResult(ResultEum resultEum, Long count, List result) {
        this.code = resultEum.getCode();
        this.msg = resultEum.getContent();
        this.count = count;
        this.result = result;
    }
}
