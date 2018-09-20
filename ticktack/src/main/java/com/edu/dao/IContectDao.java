package com.edu.dao;

import com.edu.entity.Contect;

public interface IContectDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Contect record);

    int insertSelective(Contect record);

    Contect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contect record);

    int updateByPrimaryKey(Contect record);
}