package com.example.demo_mysql;

import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import org.junit.jupiter.api.Test;

public class test {
    @Test
    public void test(){

        Object ob = "[user{userId=2, userName='hh', password='123', sex='1', age=2, phone='null', avatar='null'}]";
        if(ob !=null){
//          放行
            System.out.println("正确");
        }
        else{
            System.out.println("66666");
        }
    }
}
