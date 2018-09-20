package com.edu.dao;

import com.edu.entity.Interviewquest;

public interface InterviewquestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Interviewquest record);

    int insertSelective(Interviewquest record);

    Interviewquest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Interviewquest record);

    int updateByPrimaryKeyWithBLOBs(Interviewquest record);

    int updateByPrimaryKey(Interviewquest record);
}