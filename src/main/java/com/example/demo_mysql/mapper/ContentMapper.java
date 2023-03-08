package com.example.demo_mysql.mapper;

import com.example.demo_mysql.pojo.content;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname ContentMapper
 * @Description TODO
 * @Date 2023/2/18 15:43
 * @Created by 余
 */
@Repository
@Mapper
public interface ContentMapper {
    public List<content> selectContentByNId(int id);
    public int addContent(int uId,int nId,String uImage,String content,String time,String uName);
}