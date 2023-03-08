package com.example.demo_mysql.service;

import com.example.demo_mysql.pojo.content;

import java.util.List;

/**
 * @Classname contentService
 * @Description TODO
 * @Date 2023/2/18 16:08
 * @Created by 余
 */
public interface contentService {
    public List<content> selectContentByNId(int id);   // 周
}
