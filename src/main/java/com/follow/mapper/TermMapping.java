package com.follow.mapper;

import com.follow.entity.TermInformation;
import com.follow.vo.DataInromationByUserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TermMapping {


    @Select("select id,termName ,erji as erjiId , sanji as sanjiId  from terminformation ")
    List<TermInformation> selectTermInformationAll();
    @Insert("insert into datainformation(data , class_id ,user_id) VALUES(#{dataValue} ,#{dataId} ,#{userId})")
    Integer  insertDataInformation(@Param("dataValue") String dataValue ,
                                  @Param("dataId") String dataId ,
                                  @Param("userId") String userId);
//    @Select("select count(id) from  datainformation where class_id = #{dataId} and user_id = #{userId}")
    @Select("select id from  datainformation where class_id = #{dataId} and user_id = #{userId}")
    DataInromationByUserVo selectDataInformation(@Param("dataId") String dataId,
                                  @Param("userId") String userId);

    @Update("update datainformation set data = #{dataValue} where class_id = #{dataId} and user_id = #{userId} ")
    Integer updateDataInformation(@Param("dataValue") String dataValue ,
                                  @Param("dataId") String dataId ,
                                  @Param("userId") String userId);

    @Select("select d.id,d.`data` `data`,t.termName as className,u.user_name as userName from `user` u , datainformation d , terminformation t where u.id = #{userId} and u.id = d.user_id and d.class_id = t.id LIMIT #{page},#{limit}")
    List<DataInromationByUserVo> selectDataInformationAll(@Param("page") Integer page ,
                                                 @Param("limit") Integer limit ,
                                                 @Param("userId") String userId);


    @Delete("DELETE from datainformation  where id = #{id}")
    Integer deleteDataInformation(String id);
}
