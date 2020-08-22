package com.follow.vo;

import com.follow.common.EmptyUtils;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangchunjun
 * @date 2020/8/21
 */
@Data
public class FollowUpQueryVO {

    private  Integer id;

    private  String number;

    private String adminssionnumber;

    private String outpaientnumber;

    private  String name;

    private  Integer sex;

    private Integer birthday;

    private  Integer age;

    private  String groupTime;

    private  String operationTime;

    private  String technique;

    private  String operator;

    public String getNumber() {
        if(EmptyUtils.isEmpty(outpaientnumber)){
            number = adminssionnumber ;
        }else {
            number = adminssionnumber +"/"+ outpaientnumber;
        }
        return number;
    }

    public Integer getAge() {
        return Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())) -birthday;
    }
}
