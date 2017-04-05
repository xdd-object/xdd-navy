package com.java.xdd.system.service;

import com.java.xdd.common.service.BaseService;
import com.java.xdd.system.domain.SystemLog;

import java.util.List;

public interface SystemLogService extends BaseService{

    int insert(SystemLog pojo);

    int insertSelective(SystemLog pojo);

    int insertList(List<SystemLog> pojos);

    int update(SystemLog pojo);
}
