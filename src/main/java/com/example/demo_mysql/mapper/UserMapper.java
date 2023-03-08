package com.example.demo_mysql.mapper;

import com.example.demo_mysql.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2023/2/18 8:53
 * @Created by 余
 */
@Repository
@Mapper
public interface UserMapper {
    public List<user> selectAllUser();
    public int countUsers();
    public int deleteUser(int id);
    public int registerUser(String uname,String upassword,String age,String sex,String phone);
    public List<user> selectUserByName(String uname);
    public List<user> selectUserById(int id);
    public List<user> userLogin(String uname,String upassword);
    public int updateUserInfo( String avatar, int age, String phone,int sex,int id);
}
