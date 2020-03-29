package com.myspringbt.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    List<Map<String, Object>> getAllUser();

    List<Map<String, Object>> searchname(@Param("username") String name);
}
