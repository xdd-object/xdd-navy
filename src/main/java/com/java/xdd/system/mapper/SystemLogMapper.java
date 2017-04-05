package com.java.xdd.system.mapper;

import com.github.abel533.mapper.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.java.xdd.system.domain.SystemLog;

public interface SystemLogMapper{
    int insert(@Param("pojo") SystemLog pojo);

    int insertSelective(@Param("pojo") SystemLog pojo);

    int insertList(@Param("pojos") List<SystemLog> pojo);

    int update(@Param("pojo") SystemLog pojo);

    List<SystemLog> findBySystemLogId(@Param("systemLogId")Long systemLogId);


}
