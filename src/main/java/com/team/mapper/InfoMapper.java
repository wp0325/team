//package com.team.mapper;
//
//import com.team.model.Info;
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//
///**
// * Created by wang0 on 2016/9/9.
// */
//@Mapper
//public interface InfoMapper {
//
//    @Insert("insert into info(name,profession,phone,introduction,registe_time) values(#{name),#{profession},#{phone},#{introduction},#{registeTime})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void create(Info info);
//
//
//    @Select("select * from info where status=1")
//    @Results({
//            @Result(id = true, column = "id", property = "id"),
//            @Result(column = "registe_time", property = "registeTime")
//    })
//    List<Info> queryall();
//}
