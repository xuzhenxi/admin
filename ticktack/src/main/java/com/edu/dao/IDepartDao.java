package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.Depart;

public interface IDepartDao {
    
    public int count();
    
    public List<Depart> findByIndexAndSize(Map<String, Object> map);
    
}