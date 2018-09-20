package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.Loginlog;

public interface ILoginlogDao {
    
    public List<Loginlog> findAllLoginlogByIndexAndSize(Map<String, Object> map);
    
    public int count();
    
    public void addLoginlog(Loginlog loginlog);
}