package com.example.demo_mysql.mapper;

import com.example.demo_mysql.pojo.history;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @Classname HistoryMapper
 * @Description TODO
 * @Date 2023/2/18 16:51
 * @Created by 余
 */
@Repository
@Mapper
public interface HistoryMapper {
    public List<history> selectNewsByUid(int id);
    public int addHistory(int uid,int nid);

}
