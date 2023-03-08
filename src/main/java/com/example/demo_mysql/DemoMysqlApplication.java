package com.example.demo_mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan( "com.example.demo_mysql.mapper")
public class DemoMysqlApplication {

//    下面是默认运行程序的代码
    public static void main(String[] args) {
        SpringApplication.run(DemoMysqlApplication.class, args);
    }



//    这段代码是可以指定打开浏览器，并跳转到指定的路径，，，目前没有设置templates的页面，
//    但是之前设置的数据库的测试的url是可以直接打开的
//public static void main(String[] args) {
//    SpringApplication app = new SpringApplication(DemoMysqlApplication.class);
//    app.run(args);
//    try {
////        设置的端口和，，，请求的路径
////        Runtime.getRuntime().exec("cmd /c start http://localhost:8080/userList");   请求的是一个用户数据库所有的用户数据列表
//          Runtime.getRuntime().exec("cmd /c start http://localhost:8080/updatepage");
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
}

//在这里设置快速启动  新写页面配置接口   要添加上路由设置

//
//
//}
