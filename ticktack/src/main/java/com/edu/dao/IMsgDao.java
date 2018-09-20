package com.edu.dao;

import com.edu.entity.Msg;

public interface IMsgDao {
    int deleteByPrimaryKey(Long id);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKeyWithBLOBs(Msg record);

    int updateByPrimaryKey(Msg record);
}