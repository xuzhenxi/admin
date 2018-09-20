package com.edu.service;

import com.edu.entity.Role;
import com.edu.vo.PageBean;

public interface IRoleService {
	
	public PageBean<Role> findAllRoleByPage(int page, int size);
}
