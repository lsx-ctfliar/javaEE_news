package com.example.demo_mysql.mapper;

import com.example.demo_mysql.pojo.star;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname star
 * @Description TODO
 * @Date 2023/2/18 13:41
 * @Created by 余
 */
@Repository
@Mapper
public interface StarMapper {
    public List<star> selectNewsByUid(int id);
   public int addNewStar(int uId,int nId);
   public int deleteStar(int uId,int nId);
}
