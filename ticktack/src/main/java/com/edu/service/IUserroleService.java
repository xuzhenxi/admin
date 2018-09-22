package com.edu.service;

import java.util.List;

import com.edu.entity.Userrole;

public interface IUserroleService {
	
	public void deleteByUid(int id);
	
	public void updateRole(Integer[] rids, Integer id);

	public List<Userrole> findByUid(int id);
	
	public List<Userrole> findByRid(int rid);

}
