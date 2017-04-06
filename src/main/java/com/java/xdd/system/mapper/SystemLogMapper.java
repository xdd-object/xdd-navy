package com.java.xdd.system.mapper;

import com.java.xdd.system.domain.SystemLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemLogMapper{
    int insert(@Param("pojo") SystemLog pojo);

    int insertSelective(@Param("pojo") SystemLog pojo);

    int insertList(@Param("pojos") List<SystemLog> pojo);

    int update(@Param("pojo") SystemLog pojo);

    List<SystemLog> findBySystemLogId(@Param("systemLogId")Long systemLogId);


}
