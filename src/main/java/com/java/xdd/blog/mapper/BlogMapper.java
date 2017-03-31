package com.java.xdd.blog.mapper;

import com.github.abel533.mapper.Mapper;
import com.java.xdd.blog.domain.Blog;

import java.util.Map;

public interface BlogMapper extends Mapper<Map<String, Object>>{

    Long saveBlog(Blog blog);

    Blog getBlog(Blog blog);
}
