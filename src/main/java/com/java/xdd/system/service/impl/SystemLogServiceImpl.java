package com.java.xdd.system.service.impl;

import com.java.xdd.common.service.impl.BaseServiceImpl;
import com.java.xdd.system.domain.SystemLog;
import com.java.xdd.system.mapper.SystemLogMapper;
import com.java.xdd.system.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("systemLogService")
public class SystemLogServiceImpl extends BaseServiceImpl implements SystemLogService{

    @Autowired
    private SystemLogMapper systemLogMapper;

    public int insert(SystemLog pojo){
        return systemLogMapper.insert(pojo);
    }

    public int insertSelective(SystemLog pojo){
        return systemLogMapper.insertSelective(pojo);
    }

    public int insertList(List<SystemLog> pojos){
        return systemLogMapper.insertList(pojos);
    }

    public int update(SystemLog pojo){
        return systemLogMapper.update(pojo);
    }
}
