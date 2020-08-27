package com.follow.mapper;

import com.follow.entity.TermInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TermMapping {


    @Select("select id,termName ,erji as erjiId , sanji as sanjiId  from terminformation ")
    List<TermInformation> selectTermInformationAll();
    @Insert("insert into datainformation(data , class_id ,user_id) VALUES(#{dataValue} ,#{dataId} ,#{userId})")
    Integer  insertDataInformation(@Param("dataValue") String dataValue ,
                                  @Param("dataId") String dataId ,
                                  @Param("userId") String userId);
    @Select("select count(id) from  datainformation where class_id = #{dataId} and user_id = #{userId}")
    Integer selectDataInformation(@Param("dataId") String dataId,
                                  @Param("userId") String userId);

    @Update("update datainformation set data = #{dataValue} where class_id = #{dataId} and user_id = #{userId} ")
    Integer updateDataInformation(@Param("dataValue") String dataValue ,
                                  @Param("dataId") String dataId ,
                                  @Param("userId") String userId);

}
