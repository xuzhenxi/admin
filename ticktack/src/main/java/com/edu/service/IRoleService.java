package com.edu.service;

import java.util.List;

import com.edu.entity.Role;
import com.edu.vo.PageBean;

public interface IRoleService {
	
	public PageBean<Role> findAllRoleByPage(int page, int size, String info, String name);
	
	public List<Role> findAllRole();
	
	public void deleteById(int id);
	
	public List<Role> findAllSonOfRole();
}
