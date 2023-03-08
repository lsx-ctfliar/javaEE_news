package com.example.demo_mysql.service.lmpl;

import com.example.demo_mysql.mapper.StarMapper;
import com.example.demo_mysql.pojo.star;
import com.example.demo_mysql.service.starService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname starServicelmpl
 * @Description TODO
 * @Date 2023/2/18 14:04
 * @Created by 余
 */
@Service
public class starServicelmpl implements starService {
    @Autowired
    private StarMapper starMapper;
    @Override
    public List<star> selectNewsByUid(int id) {
        return starMapper.selectNewsByUid(id);
    }

    @Override
    public int addNewStar(int uId, int nId) {
        return starMapper.addNewStar(uId,nId);
    }

    @Override
    public int deleteStar(int uId, int nId) {
        return starMapper.deleteStar(uId,nId);
    }
}
