package com.example.demo_mysql.service;

import com.example.demo_mysql.pojo.news;
import com.example.demo_mysql.pojo.user;
import com.example.demo_mysql.pojo.userinfo;

import java.util.List;

public interface adminService {
    public List<userinfo> selectUser(String uname, String upassword);      //李
    public List<news> selectAllNewsByState(int state);    //李
    public int countNews(int state);
    public List<news> selectAllNewsById(int id, int state);
    public int updateNewsState(int id, int state);
    public List<news> selectOneNewsById(int id);
    public List<user>  selectAllUser();                       //李
    public int countUsers();
    public int deleteNews(int id);
    public int deleteUser(int id);
    public int deleteNewsByUser(int id);
    public List<news> selectAllNewsByUserId(int id );
    public List<user> selectUserById(int id);
    public List<news> selectNewsByUserId(int id , int state);
}
