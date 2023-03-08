package com.example.demo_mysql.service;

import com.example.demo_mysql.pojo.news;
import com.example.demo_mysql.pojo.user;

import java.util.List;

/**
 * @Classname userService
 * @Description TODO
 * @Date 2023/2/18 9:44
 * @Created by 余
 */
public interface userService {
    public int registerUser(String uname, String upassword, int age, String sex,String phone);   //李
    public List<user> selectUserByName(String uname);
    public List<user> userLogin(String uname, String upassword);   //李
    public List<user> selectUserById(int id);
    public int updateUserInfo( String avatar, int age, String phone,int sex,int id);
    public List<news> selectNewsByUserId(int id , int state);
    public List<news> selectNewsById(int id);
    public List<news> selectNewsByNS(int id , int state);
    public int addContent(int uId,int nId,String uImage,String content,String time,String uName);
}
