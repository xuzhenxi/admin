package com.edu.dao;

import com.edu.entity.Student;

public interface IStudentDao {
    int deleteByPrimaryKey(String no);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String no);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}