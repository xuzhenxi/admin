package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.Grade;

public interface IGradeDao {
	
	public List<Grade> findGradeByCid(int cid);
	
	public int count();
	
    public List<Grade> findByIndexAndSize(Map<String, Object> map);
    
    public void deleteById(int id);
    
    public void addGrade(Grade grade);
    
    public void updateGrade(Grade grade);
    
    public Grade findGradeById(int id);
    
    public Grade findGradeByName(String name);
    
    public List<Grade> findAll();
}