package com.example.demo_mysql.service.lmpl;

import com.example.demo_mysql.mapper.AdminMapper;
import com.example.demo_mysql.mapper.NewsMapper;
import com.example.demo_mysql.mapper.UserMapper;
import com.example.demo_mysql.pojo.news;
import com.example.demo_mysql.pojo.user;
import com.example.demo_mysql.pojo.userinfo;
import com.example.demo_mysql.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminServicelmpl implements adminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<userinfo> selectUser(String uname, String upassword) {
       return adminMapper.selectUser(uname, upassword);
    }

    @Override
    public List<news> selectAllNewsByState(int state) {
        return newsMapper.selectAllNewsByState(state);
    }

    @Override
    public int countNews(int state) {
        return newsMapper.countNews(state);
    }

    @Override
    public List<news> selectAllNewsById(int id, int state) {
        return newsMapper.selectAllNewsById(id,state);
    }

    @Override
    public int updateNewsState(int id, int state) {
        List<news> news = selectOneNewsById(id);
        if(news.size()==0)
            return 0;
        else
        return newsMapper.updateNewsState(id, state);
    }

    @Override
    public List<news> selectOneNewsById(int id) {
        return newsMapper.selectOneNewsById(id);
    }

    @Override
    public List<user> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public int countUsers() {
        return userMapper.countUsers();
    }

    @Override
    public int deleteNews(int id) {
        List<news> news = selectOneNewsById(id);
        if(news.size()==0)
            return 0;
        else
        return newsMapper.deleteNews(id);
    }
    @Override
    public List<news> selectAllNewsByUserId(int id) {
        return newsMapper.selectAllNewsByUserId(id);
    }

    @Override
    public List<user> selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<news> selectNewsByUserId(int id, int state) {
        return newsMapper.selectAllNewsByUS(id, state);
    }


    @Override
    public int deleteNewsByUser(int id) {
        return newsMapper.deleteNewsByUser(id);
    }
    @Override
    public int deleteUser(int id) {
        if(selectAllNewsByUserId(id).size()!=0)
        {
           newsMapper.deleteNewsByUser(id);
            return userMapper.deleteUser(id);
        }
        return userMapper.deleteUser(id);
    }






}
