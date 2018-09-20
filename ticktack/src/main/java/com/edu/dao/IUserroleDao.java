package com.edu.dao;

import com.edu.entity.Userrole;

public interface IUserroleDao {
    int deleteByPrimaryKey(Userrole key);

    int insert(Userrole record);

    int insertSelective(Userrole record);
}