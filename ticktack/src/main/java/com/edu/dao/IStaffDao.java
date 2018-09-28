package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.Staff;

public interface IStaffDao {
    
    public Integer findCountByDid(int did);
    
    public int count();
    
    public List<Staff> findByIndexAndSize(Map<String, Object> map);
    
    public String findDnameByNo(String no);
    
    public void deleteStaffByNo(String no);
}