package com.example.demo_mysql.service;

import com.example.demo_mysql.pojo.history;
import com.example.demo_mysql.pojo.star;

import java.util.List;

/**
 * @Classname historyService
 * @Description TODO
 * @Date 2023/2/18 16:54
 * @Created by 余
 */
public interface historyService {
    public List<history> selectNewsByUid(int id);    //李
    public int addHistory(int uid,int nid);        //李
}
