package com.example.demo_mysql.service.lmpl;

import com.example.demo_mysql.mapper.ContentMapper;
import com.example.demo_mysql.mapper.NewsMapper;
import com.example.demo_mysql.mapper.UserMapper;
import com.example.demo_mysql.pojo.news;
import com.example.demo_mysql.pojo.user;
import com.example.demo_mysql.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname userServicelmpl
 * @Description TODO
 * @Date 2023/2/18 9:44
 * @Created by 余
 */
@Service
public class userServicelmpl implements userService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private ContentMapper contentMapper;
    @Override
    public int registerUser(String uname, String upassword, int age, String sex,String phone) {
        if(selectUserByName(uname).size()!=0)
        {
            return 2;
        }
        return userMapper.registerUser(uname, upassword, String.valueOf(age), sex,phone);
    }

    @Override
    public List<user> selectUserByName(String uname) {
        return userMapper.selectUserByName(uname);
    }

    @Override
    public List<user> userLogin(String uname, String upassword) {
        return userMapper.userLogin(uname, upassword);
    }

    @Override
    public List<user> selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public int updateUserInfo(String avatar, int age, String phone, int sex ,int id) {
        if(selectUserById(id).size()==0)
        return 2;
        else
        {
            return userMapper.updateUserInfo(avatar, age, phone, sex,id );
        }
    }

    @Override
    public List<news> selectNewsByUserId(int id, int state) {
        return newsMapper.selectAllNewsByUS(id,state);
    }

    @Override
    public List<news> selectNewsById(int id) {
        return newsMapper.selectOneNewsById(id);
    }

    @Override
    public List<news> selectNewsByNS(int id, int state) {
        return newsMapper.selectAllNewsById(id,state);
    }

    @Override
    public int addContent(int uId, int nId,String uImage, String content, String time, String uName) {
        return contentMapper.addContent(uId, nId,uImage, content, time, uName);
    }
}
