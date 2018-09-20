package com.edu.dao;

import com.edu.entity.Roleauthority;

public interface IRoleauthorityDao {
    int deleteByPrimaryKey(Roleauthority key);

    int insert(Roleauthority record);

    int insertSelective(Roleauthority record);
}