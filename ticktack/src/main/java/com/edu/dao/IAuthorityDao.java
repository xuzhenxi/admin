package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.Authority;

public interface IAuthorityDao {
    
    public List<String> findAuthByNo(String no);
    
    public List<String> findPermitByNo(String no);
    

    public List<Authority> findTitleByNo(String no);
    
    public List<Authority> findSonByNo(int id);
    
    public int count();
    
    public List<Authority> findAllAuthByPage(Map<String, Object> map);
}