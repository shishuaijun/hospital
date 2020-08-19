package com.follow.vo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangchunjun
 * @date 2020/8/17
 */
@Data
public class FollowUpProgressVO {

    private Integer id;
    private String groupName;
    private String groupTime;
    private Integer condition;
    private String nextDate;
    private String name;
    private String number;
    private Integer age;
    private Integer adminssionnumber;
    private Integer outpaientnumber;
    private Integer sex;
    private Integer birthday;

    public Integer getAge() {
        return Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())) -birthday;
    }

    public String getNumber() {
        if(outpaientnumber > 0){
           number = adminssionnumber +"/"+ outpaientnumber;
        }else {
            number = adminssionnumber +"";
        }

        return number;
    }
}
