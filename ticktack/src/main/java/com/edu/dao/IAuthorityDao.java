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
    
    public List<Integer> findTitleByRid(int rid);
    
    public List<Authority> findAllTitle();
    
    public List<Authority> findByParentId(int parentId);
    
    public void addAuth(Authority auth);
    
    public void deleteAuth(Integer aid);
    
    public void updateAuth(Authority auth);
    
    public Authority findAuthById(int id);
    
    public List<Authority> findAuthByTitile(String title);
    
    public List<Authority> findSonTitle(Map<String, Object> map);
    
    
    
    
}