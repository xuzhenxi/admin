package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IDepartDao;
import com.edu.dao.IStaffDao;
import com.edu.entity.Depart;
import com.edu.entity.User;
import com.edu.service.IDepartService;
import com.edu.vo.PageBean;

@Service
public class DepartService implements IDepartService {
	
	@Autowired
	private IDepartDao departDao;
	
	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public PageBean<Depart> findDepartByPage(int page, int size) {
		PageBean<Depart> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		int count = departDao.count();
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
		List<Depart> list = departDao.findByIndexAndSize(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public void deleteDepartById(int id) {
		if (id != 0) {
			departDao.deleteDepartById(id);
		}
	}

	@Override
	public void updateDepart(Depart depart) {
		if (depart != null) {
			departDao.updateDepart(depart);
		}
	}
	
	@Override
	public void addDepart(Depart depart) {
		if (depart != null) {
			departDao.addDepart(depart);
		}
	}
	
	@Override
	public List<Depart> findAll() {
		List<Depart> list = null;
		try {
			list = departDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
