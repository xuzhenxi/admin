package com.edu.dao;

import com.edu.entity.Loginlog;

public interface ILoginlogDao {
    int deleteByPrimaryKey(Long id);

    int insert(Loginlog record);

    int insertSelective(Loginlog record);

    Loginlog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Loginlog record);

    int updateByPrimaryKey(Loginlog record);
}