package com.example.demo_mysql.mapper;

import com.example.demo_mysql.pojo.news;
import com.example.demo_mysql.pojo.user;
import com.example.demo_mysql.pojo.userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface AdminMapper {
      public List<userinfo> selectUser(String uname,String upassword);
//      public List<news> selectAllNewsByState(int state);
//      public int countNews(int state);
//      public List<news> selectAllNewsById(int id ,int state);
//      public int updateNewsState(int id ,int state);
//      public List<news> selectOneNewsById(int id);
//      public List<user> selectAllUser();
//      public int countUsers();
//      public int deleteNews(int id);
////      public int deleteNewsByUser(int id);
//      public int deleteUser(int id);
//      public List<news> selectAllNewsByUserId(int id );

}
