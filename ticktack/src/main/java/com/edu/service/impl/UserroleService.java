package com.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IUserroleDao;
import com.edu.service.IUserroleService;

@Service
public class UserroleService implements IUserroleService {
	
	@Autowired
	private IUserroleDao userroleDao;
	
	@Override
	public void deleteByUid(int id) {
		if (id != 0) {
			userroleDao.deleteByUid(id);
		}
	}

}
