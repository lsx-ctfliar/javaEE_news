package com.example.demo_mysql.service.lmpl;

import com.example.demo_mysql.mapper.HistoryMapper;
import com.example.demo_mysql.pojo.history;
import com.example.demo_mysql.service.historyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname historyServicelmpl
 * @Description TODO
 * @Date 2023/2/18 16:54
 * @Created by 余
 */
@Service
public class historyServicelmpl implements historyService {
    @Autowired
    private HistoryMapper historyMapper;
    public int addHistory(int uid,int nid){
        return historyMapper.addHistory(uid,nid);
    };
    @Override
    public List<history> selectNewsByUid(int id) {
        return historyMapper.selectNewsByUid(id);
    }
}
