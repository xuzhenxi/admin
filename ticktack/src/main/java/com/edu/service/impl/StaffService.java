package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IStaffDao;
import com.edu.entity.Staff;
import com.edu.service.IStaffService;
import com.edu.vo.PageBean;

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

	@Override
	public PageBean<Staff> findAllStaffByPage(int page, int limit) {
		PageBean<Staff> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		pageInfo.setCurrentPage(page);
		
		int count = staffDao.count();
		pageInfo.setCount(count);
		
		if (count % limit == 0) {
			pageInfo.setTotalPage(count / limit); 
		} else {
			pageInfo.setTotalPage(count / limit + 1); 
		}
		
		int index = (page - 1) * limit;
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("limit", limit);
		List<Staff> list = staffDao.findByIndexAndSize(map);
		pageInfo.setPageInfos(list);
		
		
		return pageInfo;
	}
	
	@Override
	public void deleteStaffByNo(String no) {
		if (no != null) {
			staffDao.deleteStaffByNo(no);
		}
	}

	@Override
	public Staff findLastStaff() {
		Staff staff = null;
		
		try {
			staff = staffDao.findLastStaff();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}

	@Override
	public void addStaff(Staff staff) {
		if (staff != null) {
			staffDao.addStaff(staff);
		}
	}

	@Override
	public void updateStaff(Staff staff) {
		if (staff != null) {
			staffDao.updateStaff(staff);
		}
	}

	@Override
	public Staff findStaffByName(String name) {
		Staff staff = null;
		if (name != null) {
			staff = staffDao.findStaffByName(name);
		}
		return staff;
	}
	
	@Override
	public List<Staff> findAll() {
		List<Staff> list = null;
		
		try {
			list = staffDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
