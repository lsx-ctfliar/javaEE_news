package com.example.demo_mysql.service;

import com.example.demo_mysql.pojo.star;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname starService
 * @Description TODO
 * @Date 2023/2/18 14:03
 * @Created by 周
 */
public interface starService {
    public List<star> selectNewsByUid(int id);   // 周
    public int addNewStar(int uId,int nId);// 周
    public int deleteStar(int uId,int nId);      // 周
}
