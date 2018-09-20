package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IRoleDao;
import com.edu.entity.Role;
import com.edu.entity.User;
import com.edu.service.IRoleService;
import com.edu.vo.PageBean;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public PageBean<Role> findAllRoleByPage(int page, int size) {
		PageBean<Role> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		int count = roleDao.count();
		pageInfo.setCount(count);
		
		if (count % size == 0) {
			pageInfo.setTotalPage(count / size); 
		} else {
			pageInfo.setTotalPage(count / size + 1); 
		}
		
		int index = (page - 1) * size;
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", size);
		List<Role> list = roleDao.findAllRoleByPage(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

}
