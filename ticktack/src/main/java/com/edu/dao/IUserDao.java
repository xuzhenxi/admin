package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.User;

public interface IUserDao {
    
    public void login(String no, String pass);
    
    public User findByNo(String no);
    
    public int count();
    
    public List<User> findByIndexAndSize(Map<String, Object> map);
    
    public List<String> findWorkById(int id);
}