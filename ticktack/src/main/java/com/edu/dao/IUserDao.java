package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.User;

public interface IUserDao {
    
    public void login(String no, String pass);
    
    public User findByNo(String no);
    
    public int count(Map<String, Object> map);
    
    public List<User> findByIndexAndSize(Map<String, Object> map);
    
    public List<String> findWorkById(int id);
    
    public void deleteUserById(int id);
    
    public User findUserById(int id);
    
    public List<User> search(Map<String, Object> map);
}