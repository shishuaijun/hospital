package com.follow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.follow.entity.JoinGroup;
import com.follow.vo.CustomPatientVO;
import com.follow.vo.CustomVo;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

/**
 * 入组管理 业务层
 * @author wangchunjun
 * @date 2020/8/6
 */
public interface JoinGroupService extends IService<JoinGroup> {

    /**
     * 根据条件 将患者 进行入组
     * @param desk
     * @param illnessCoded
     * @param illnessName
     * @return
     */
    boolean intoTheGroup(Integer desk, String illnessCoded, String illnessName, String array, Integer userId);


    /**
     * 自定义条件 将患者 进行入组
     * @param patient
     * @return
     */
    boolean intoTheGroups(CustomPatientVO patient,Integer userId);

    /**
     * 根据 结果集id 进行入组
     * @param id
     * @return
     */
    boolean getResult(Integer id,Integer userId);

    /**
     * 导出 模板
     * @return
     */
    Workbook exportExcel(String name)throws IOException;

    /**
     * 自 定义导入 患者信息
     * @param newPath
     * @return
     */
    boolean importExcel(String newPath,Integer userId) throws Exception;

    /**
     * 自定义患者 进行入组
     * @param patient
     * @return
     */
    boolean customPatient(CustomVo patient,Integer userId);

    /**
     * 入组时间
     * @param standards
     * @param basiss
     * @param degrees
     * @param doctors
     * @param date
     * @return
     */
    boolean saveGroupSetTime(String standards, String basiss, String degrees, String doctors, String date);
}
