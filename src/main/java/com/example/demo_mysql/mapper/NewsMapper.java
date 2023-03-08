package com.example.demo_mysql.mapper;

import com.example.demo_mysql.pojo.news;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname NewsMapper
 * @Description TODO
 * @Date 2023/2/18 8:50
 * @Created by 余
 */
@Repository
@Mapper
public interface NewsMapper {
    public List<news> selectAllNewsByState(int state);
    public int countNews(int state);
    public List<news> selectAllNewsById(int id ,int state);
    public int updateNewsState(int id ,int state);
    public List<news> selectOneNewsById(int id);
    public int deleteNews(int id);
    public int deleteNewsByUser(int id);
    public List<news> selectAllNewsByUserId(int id );
    public List<news> selectAllNewsByUS(int id , int state);
    public int updateAllNews(int uId, int newId, String nTile,String nArticle,String nImage,String nTime,
        int nYC,int nSC);
    public int addNews(int uId,String uName, String nTile,String nArticle,String nImage,String nTime,
                             int nYC,int nSC,int nType,String nKind);
}
