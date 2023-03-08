package com.example.demo_mysql.service;

/**
 * @Classname newsService
 * @Description TODO
 * @Date 2023/2/18 17:26
 * @Created by 余
 */
public interface newsService {
    public int updateAllNews(int uId, int newsId, String nTile,String nArticle,String nImage
                            ,String nTime ,int nYC,int nSC);   // 周
    public int addNews(int uId, String userName,String nTile,String nArticle,String nImage,String nTime,
                       int nYC,int nSC,int nType,String nKind);  // 周
}
