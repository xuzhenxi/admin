package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IGradeDao;
import com.edu.entity.Grade;
import com.edu.service.IGradeService;
import com.edu.vo.PageBean;

@Service
public class GradeService implements IGradeService {
	
	@Autowired
	private IGradeDao gradeDao;
	
	@Override
	public List<Grade> findGradeByCid(int cid) {
		List<Grade> list = null;
		if (cid != 0) {
			list = gradeDao.findGradeByCid(cid);
		}
		return list;
	}

	@Override
	public PageBean<Grade> findGradeByPage(int page, int size) {
		PageBean<Grade> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		int count = gradeDao.count();
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
		List<Grade> list = gradeDao.findByIndexAndSize(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}
	
	@Override
	public void deleteById(int id) {
		if (id != 0) {
			gradeDao.deleteById(id);
		}
	}
}
