package com.java.xdd.blog.service;


import com.java.xdd.blog.domain.Blog;
import com.java.xdd.common.service.BaseService;

import java.util.List;
import java.util.Map;


public interface BlogService extends BaseService{

    Long saveBlog(Blog blog);


    Blog getBlog(Blog blog);

    Map<String, Object> get(Map<String, Object> params);

    List<Map<String, Object>> get2(Map<String, Object> params);


}
