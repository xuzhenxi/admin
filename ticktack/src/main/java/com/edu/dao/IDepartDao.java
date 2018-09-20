package com.edu.dao;

import com.edu.entity.Depart;

public interface IDepartDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Depart record);

    int insertSelective(Depart record);

    Depart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Depart record);

    int updateByPrimaryKey(Depart record);
}