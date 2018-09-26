package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IRoleDao;
import com.edu.entity.Role;
import com.edu.service.IRoleService;
import com.edu.vo.PageBean;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public PageBean<Role> findAllRoleByPage(int page, int size, String info, String name) {
		if (info != null && !info.equals("")) {
			info = "%" + info + "%";
		} else {
			info = null;
		}
		if (name != null && !name.equals("")) {
			name = "%" + name + "%";
		} else {
			name = null;
		}
		
		
		PageBean<Role> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		Map<String, Object> fmap = new HashMap<>();
		fmap.put("info", info);
		fmap.put("name", name);
		
		
		int count = roleDao.count(fmap);
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
		map.put("info", info);
		map.put("name", name);
		List<Role> list = roleDao.findAllRoleByPage(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public List<Role> findAllRole() {
		List<Role> list = null;
		try {
			list = roleDao.findAllRole();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteById(int id) {
		if (id != 0) {
			roleDao.deleteById(id);
		}
	}

	@Override
	public List<Role> findAllSonOfRole() {
		List<Role> list = null;
		try {
			list = roleDao.findAllSonOfRole();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}









