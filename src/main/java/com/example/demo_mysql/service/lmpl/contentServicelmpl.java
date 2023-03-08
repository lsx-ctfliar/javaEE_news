package com.example.demo_mysql.service.lmpl;

import com.example.demo_mysql.mapper.ContentMapper;
import com.example.demo_mysql.pojo.content;
import com.example.demo_mysql.service.contentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname contentServicelmpl
 * @Description TODO
 * @Date 2023/2/18 16:09
 * @Created by 余
 */
@Service
public class contentServicelmpl implements contentService {
    @Autowired
    private ContentMapper contentMapper;
    @Override
    public List<content> selectContentByNId(int id) {
        return contentMapper.selectContentByNId(id);
    }
}
