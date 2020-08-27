package com.follow.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.follow.common.EmptyUtils;
import com.follow.common.JSONResult;
import com.follow.common.ResultEum;
import com.follow.entity.Department;
import com.follow.entity.Permissions;
import com.follow.entity.User;
import com.follow.service.FollowgroupService;
import com.follow.service.PermissionsFollowgroupVoService;
import com.follow.service.PermissionsService;
import com.follow.service.UserService;
import com.follow.vo.PermissionsFollowgroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author wangchunjun
 * @date 2020/8/6
 */
@RestController
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private UserService userService;

    @Autowired
    private FollowgroupService followgroupService;


    @Autowired
    private PermissionsFollowgroupVoService permissionsFollowgroupVoService;

    @RequestMapping("queryGroupPermission")
    public JSONResult queryGroupPermission(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                           @RequestParam(value = "userName", required = false) String userName,
                                           @RequestParam(value = "remarks", required = false) String remarks
    ) {
        JSONResult jsonResult = null;
//        try {
        System.out.println(remarks);
        List<PermissionsFollowgroupVo> permissionsFollowgroupVos = permissionsService.queryPermissionsFollowgroup(page, limit, userName, remarks);
        Long integer = (long) permissionsService.queryPermissionsFollowgroupSize();
        jsonResult = new JSONResult(ResultEum.SUCCESS, integer, permissionsFollowgroupVos);
//        } catch (Exception e) {
//            jsonResult = new JSONResult(ResultEum.ERROR, 0L, null);
//        }
        return jsonResult;
    }

    /**
     * 删除随访表
     *
     * @param ids
     * @return
     */
    @RequestMapping("dislodgePermissions")
    public JSONResult dislodgePermissions(@RequestParam("ids") String ids) {
        JSONResult jsonResult = null;
        String[] perIds = ids.split(",");
        boolean b = followgroupService.removeByIds(Arrays.asList(perIds));
        Long a = 0L;
        String c = "";
        if (b) {
            a = (long) perIds.length;
            c = "删除成功";
        } else {
            c = "删除失败";
        }
        try {
            jsonResult = new JSONResult(ResultEum.SUCCESS, a, c);
        } catch (Exception e) {
            jsonResult = new JSONResult(ResultEum.ERROR, a, c);
        }
        return jsonResult;
    }

    /**
     * 查询随访表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectPermissionsById")
    public JSONResult selectPermissionsById(@RequestParam("id") String id) {
        JSONResult jsonResult = null;
        PermissionsFollowgroupVo permissionsFollowgroupVo = permissionsService.queryPermissionsById(id);
        try {
            jsonResult = new JSONResult(ResultEum.SUCCESS, 1L, permissionsFollowgroupVo);
        } catch (Exception e) {
            jsonResult = new JSONResult(ResultEum.ERROR, 0L, "查询失败");
        }
        return jsonResult;
    }

    /**
     * 修改 随访表
     *
     * @param id                随访id
     * @param permissionsPerson 随访名称
     * @param permissionsName   随访小组
     * @param fphone            联系方式
     * @param level             级别
     * @param roleName          角色名称
     * @param jurisdiction      权限
     * @return
     */
    @RequestMapping("updatePermission")
    public JSONResult updatePermission(String id,
                                       String permissionsPerson,
                                       String permissionsName,
                                       String fphone,
                                       String level,
                                       String roleName,
                                       String jurisdiction) {
        JSONResult jsonResult = null;
        String permissions = permissionsFollowgroupVoService.updatePermissions(id, permissionsPerson, permissionsName, fphone, level, roleName, jurisdiction);
        try {
            jsonResult = new JSONResult(ResultEum.SUCCESS, 1L, permissions);
        } catch (Exception e) {
            jsonResult = new JSONResult(ResultEum.ERROR, 0L, "修改失败");
        }
        return jsonResult;
    }

    /**
     * 查询当前随访表的用户
     *
     * @param permissionId
     * @return
     */
    @RequestMapping("getPermissionByIdAllUserName")
    public JSONResult getPermissionByIdAllUserName(@RequestParam("id") String permissionId) {
        JSONResult jsonResult = null;
        Map map = new HashMap();
        map.put("followgroup_id", permissionId);
        List<Permissions> list = permissionsService.listByMap(map);
        try {
            jsonResult = new JSONResult(ResultEum.SUCCESS, (long) list.size(), list);
        } catch (Exception e) {
            jsonResult = new JSONResult(ResultEum.ERROR, 0L, null);
        }
        return jsonResult;
    }

    /**
     * 用户添加
     *
     * @param userName
     * @param id
     * @return
     */
    @RequestMapping("updatePermissionsByUserName")
    public JSONResult updatePermissionsByUserName(@RequestParam(value = "userName", required = false) String userName,
                                                  @RequestParam("id") Integer id) {
        JSONResult jsonResult = null;
        boolean save = false;
        Set<String> names = new HashSet() ;
        if (userName != null && !"".equals(userName)) {
          /*  for (String name :userName.split(",")) {
                names.add(name);
            }*/
            System.out.println("============="+userName);

            String[] split = userName.split(",");
            for (int i = 0; i < split.length; i++) {
                names.add(split[i]);
            }
        }
        Map map = new HashMap();
        map.put("followgroup_id", id);
        List<Permissions> list = permissionsService.listByMap(map);
        List<String> listNames = new ArrayList<>();
        for (String namess : names) {
            for (Permissions permission: list) {
                if (namess.equals(permission.getUserName())){
                    listNames.add(namess);
                }
            }
        }
        for (int i = 0; i < listNames.size() ; i++) {
            names.remove(listNames.get(i));
        }
        for (String name : names){
            QueryWrapper<Permissions> objectQueryWrapper = new QueryWrapper<>();
            objectQueryWrapper.eq("user_name", name);
            Permissions one = permissionsService.getOne(objectQueryWrapper);
            System.out.println(one.toString());
            Permissions p = new Permissions();
            p.setRoleId(one.getRoleId());
            p.setRoleName(one.getRoleName());
            p.setJurisdiction(one.getJurisdiction());
            p.setFollowgroupId(id);
            p.setUserName(one.getUserName());
            p.setCreateTime(new Date());
            save = permissionsService.save(p);
        }
        try {
            jsonResult = new JSONResult(ResultEum.SUCCESS, save?1L:0L, save?"添加成功":"没有添加数据");
        }catch(Exception e){
            jsonResult = new JSONResult(ResultEum.SUCCESS,0L,"添加失败");
        }
        return jsonResult;
    }

    @RequestMapping("getPermissionByRoleName")
    public JSONResult getPermissionByRoleName(@RequestParam(value = "userName", required = false) String roleName,
                                              @RequestParam(value = "remarks", required = false) String remarks) {
        JSONResult jsonResult = null;

        permissionsFollowgroupVoService.queryPermissionsByRoleNameRemarks(roleName, remarks);

        return jsonResult;
    }

}

