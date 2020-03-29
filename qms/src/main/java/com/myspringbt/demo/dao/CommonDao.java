package com.myspringbt.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonDao {
    List<Map<String, Object>> findAllMessage();
}
