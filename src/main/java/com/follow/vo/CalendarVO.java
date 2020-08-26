package com.follow.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wangchunjun
 * @date 2020/8/25
 */
@Data
public class CalendarVO {

    private String week;

    private String plan;

    private String actual;

    private String progress;

    private List<String> patient;

}
