package com.example.demo_mysql;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@SpringBootTest
@RestController
public class MysqlReturnMessageUseTest {


    @Autowired
    JdbcTemplate jdbcTemplate;

    //测试数据连接

    @Test
    @RequestMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql = "select * from userinfo";

        List<Map<String,Object>> list_maps =  jdbcTemplate.queryForList(sql);

        for(int i=0; i<list_maps.size();i++)
        {
            System.out.println("一次循环打印出的数据，，就是查到的数据的一个子对象");
            System.out.println(list_maps.get(i));

        }
//        打印查到的数据
        System.out.println(list_maps.toString());

        return list_maps;
    };



    }


