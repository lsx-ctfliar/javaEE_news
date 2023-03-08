package com.example.demo_mysql.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//再添加一个路径，作为测试数据库和项目使用的，，控制器分开
//使用resttcontroller的返回的结果是一个字符串，，而不是一个视图解析器
@RestController
@RequestMapping("/JdbcTest")
public class JDBCcontrollerTest {

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
    public String addUser()
    {
        String sql = "insert  into web.userinfo(username,userpassword) values('liu','123')";
        jdbcTemplate.update(sql);
        return "update-ok";

    }

//    更新用户操作
//    id在url里面输入，，作为参数设置
    @RequestMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
    String sql = "update web.userinfo set username=?,userpassword=? where id="+id;

//    在这里，，，sql里面使用的是  写好的固定的格式，，是一个预编译sql，，在这里使用对象对sql语句
        //封装
        Object[] objects = new Object[2];
        objects[0]="小明";
        objects[1]="234";

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
