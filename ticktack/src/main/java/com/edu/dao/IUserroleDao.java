package com.edu.dao;

import java.util.List;

import com.edu.entity.Userrole;

public interface IUserroleDao {
    
    public void deleteByUid(int uid);
    
    public List<Userrole> findByUid(int id);
    
    public void addUserrole(Userrole userrole);
    
    public List<Userrole> findByRid(int rid);

}