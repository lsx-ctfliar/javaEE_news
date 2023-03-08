package com.example.demo_mysql.service.lmpl;

import com.example.demo_mysql.mapper.NewsMapper;
import com.example.demo_mysql.service.newsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname newsServicelmpl
 * @Description TODO
 * @Date 2023/2/18 17:27
 * @Created by 余
 */
@Service
public class newsServicelmpl implements newsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public int updateAllNews(int uId, int newId, String nTile, String nArticle, String nImage,String nTime, int nYC, int nSC) {
        return newsMapper.updateAllNews(uId, newId, nTile, nArticle, nImage ,nTime, nYC, nSC);
    }

    @Override
    public int addNews(int uId,String uName, String nTile, String nArticle, String nImage, String nTime, int nYC, int nSC, int nType, String nKind) {
        return newsMapper.addNews(uId,uName,nTile, nArticle, nImage, nTime, nYC, nSC, nType, nKind);
    }



}
