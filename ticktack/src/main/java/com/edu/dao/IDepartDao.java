package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.Depart;

public interface IDepartDao {
    
    public int count();
    
    public List<Depart> findByIndexAndSize(Map<String, Object> map);
    
    public void deleteDepartById(int id);
    
    public void updateDepart(Depart depart);
    
    public void addDepart(Depart depart);
    
    public Depart findById(int id);
    
}