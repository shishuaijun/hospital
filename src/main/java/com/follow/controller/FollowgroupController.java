package com.follow.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.follow.dto.DataUtil;
import com.follow.entity.Followgroup;
import com.follow.service.FollowgroupService;
import lombok.val;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 分组
 *
 * @author wangchunjun
 * @date 2020/8/6
 */
@Controller
public class FollowgroupController {

    @Autowired
    private FollowgroupService followgroupService;


    /**
     * 全查
     *
     * @param ：Followgroup
     * @return
     */
    @RequestMapping(value = "findPage", produces = {"application/json"})
    @ResponseBody
    public String findPage(Integer page, Integer limit) {
        List<Followgroup> a = followgroupService.list();

        DataUtil dataUtil = new DataUtil();
        dataUtil.setCode(0);
        dataUtil.setMsg("success");
        dataUtil.setCount(a.size());
        dataUtil.setData(a);

        JSONObject json = new JSONObject();

        return json.toJSONString(dataUtil);
    }

    /**
     * 功能描述： TODO[ 添加保存 ]
     * @auther:  Zuan~
     * @date:  2020/8/7  16:27
     * @param: departmentId,departmentPerson,createTime, fName,fphone,fbackground,fstate,   fstratTime,fendTime
     * @return:  yes/no
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public String save(Integer departmentId, Integer departmentPerson, Date createTime, String fName, String fphone, String fbackground, Integer fstate){
        Followgroup f = new Followgroup();
        f.setDepartmentId(departmentId);
        f.setDepartmentPerson(departmentPerson);
        f.setCreateTime(createTime);
        f.setFName(fName);
        f.setFphone(fphone);
        f.setFbackground(fbackground);
        f.setFstate(fstate);
        //f.setFstratTime(fstratTime);
        //f.setFendTime(fendTime);

        boolean insert = followgroupService.save(f);
        if (insert == true){
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 删除 ]
     * @auther:  Zuan~
     * @date:  2020/8/7  17:53
     * @param:
     * @return:
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(Integer id){
        boolean i = followgroupService.removeById(id);
        if (i = true){
            return "yes";
        }
        return "no";
    }


    /**
     * 功能描述： TODO[ 单查id 进行修改 ]
     * @auther:  Zuan~
     * @date:  2020/8/10  10:09
     * @param:  id
     * @return:  entity
     */
    @RequestMapping("/findById")
    @ResponseBody
    public String findById(Integer id){

        Followgroup byId = followgroupService.getById(id);

        return JSON.toJSONString(byId);
    }


    /**
     * 功能描述： TODO[  修改后的保存 ]
     * @auther:  Zuan~
     * @date:  2020/8/10  10:26
     * @param:  entity
     * @return:
     */
    @RequestMapping("/save2")
    @ResponseBody
    public String save2(Integer id,Integer departmentId, Integer departmentPerson, Date createTime, String fName, String fphone, String fbackground, Integer fstate){

        Followgroup f = new Followgroup();
        f.setId(id);
        f.setDepartmentId(departmentId);
        f.setDepartmentPerson(departmentPerson);
        f.setFName(fName);
        f.setFphone(fphone);
        f.setFbackground(fbackground);
        f.setFstate(fstate);
        //f.setFstratTime(fstratTime);
        //f.setFendTime(fendTime);

        Boolean b = followgroupService.saveOrUpdate(f);

        if (b == true){
            return "yes";
        }
        return "no";
    }
}

