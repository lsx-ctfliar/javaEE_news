package com.example.demo_mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class MysqlConnectTests {


    @Autowired
    DataSource dataSource;

    //测试数据连接

    @Test
    void contextLoads() throws SQLException {

//        查看一下默认的数据源
        System.out.println(dataSource.getClass());

//        输出的结果获取到的数据源
//        class com.zaxxer.hikari.HikariDataSource


//        获得数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //关闭
        connection.close();




    }

}
