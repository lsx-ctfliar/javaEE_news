package com.example.demo_mysql.controller;


import com.example.demo_mysql.pojo.userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class JDBCcontroller {

    //    使用内嵌的模板帮助开发
    @Autowired
    JdbcTemplate jdbcTemplate;

    //    查询数据库的所有信息
//    现在没有实体类，数据库的数据该怎么获取，，以什么样子的形式返回  Map
    @RequestMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql = "select * from userinfo";

        List<Map<String,Object>> list_maps =  jdbcTemplate.queryForList(sql);

        return list_maps;
    };



    @RequestMapping("/addUser")
    public String addUser(userinfo user)
    {
        String sql = "insert  into web.userinfo(username,userpassword) values(?,?)";
//        问号就相当于是之前学习python的时候使用到的，大括号占位符，再使用.format()
        Object[] objects = new Object[2];
        objects[0]=user.getUsername();
        objects[1]=user.getUserpassword();
        jdbcTemplate.update(sql,objects);
        //新增用户完成之后，，，直接返回登陆界面
        return "login";

    }




    //    更新用户操作
//    id在url里面输入，，作为参数设置
    @RequestMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id,userinfo user){
        String sql = "update web.userinfo set username=?,userpassword=? where id="+id;

//    在这里，，，sql里面使用的是  写好的固定的格式，，是一个预编译sql，，在这里使用对象对sql语句
        //封装
        Object[] objects = new Object[2];
        objects[0]=user.getUsername();
        objects[1]=user.getUserpassword();

        jdbcTemplate.update(sql,objects);   ///传入构造的参数让sql重编译
        return "update-ok";
    }


    //    删除用户操作
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql="delete from web.userinfo where id =?";

        jdbcTemplate.update(sql,id);

        return "delete-ok";
    }






}
