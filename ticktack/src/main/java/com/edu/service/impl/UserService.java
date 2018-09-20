package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IUserDao;
import com.edu.entity.User;
import com.edu.service.IUserService;
import com.edu.vo.PageBean;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	

	@Override
	public PageBean<User> findUserByPage(int page, int size) {
		PageBean<User> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		int count = userDao.count();
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
		List<User> list = userDao.findByIndexAndSize(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}


	@Override
	public List<String> findWorkById(int id) {
		List<String> list = null;
		if (id != 0) {
			list = userDao.findWorkById(id);
		}
		return list;
	}


	@Override
	public void deleteUserById(int id) {
		if (id != 0) {
			userDao.deleteUserById(id);
		}
	}
	
	@Override
	public User findUserById(int id) {
		User user = new User();
		if (id != 0) {
			user = userDao.findUserById(id);
		}
		return user;
	}
	
	@Override
	public User findUserByNo(String no) {
		User user = new User();
		if (no != null) {
			user = userDao.findByNo(no);
		}
		return user;
	}
}
