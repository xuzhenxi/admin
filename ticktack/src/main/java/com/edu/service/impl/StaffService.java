package com.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IStaffDao;
import com.edu.service.IStaffService;

@Service
public class StaffService implements IStaffService {
	
	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public Integer findCountByDid(int did) {
		int count = 0;
		if (did != 0) {
			count = staffDao.findCountByDid(did);
		}
		return count;
	}

}
