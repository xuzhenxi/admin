package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IAuthorityDao;
import com.edu.entity.Authority;
import com.edu.service.IAuthorityService;
import com.edu.vo.PageBean;

@Service
public class AuthorityService implements IAuthorityService {

	@Autowired
	private IAuthorityDao authDao;
	
	@Override
	public List<Authority> findTitleByNo(String no) {
		
		List<Authority> list = null;
		
		try {
			list = authDao.findTitleByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public PageBean<Authority> findAllAuthByPage(int page, int size) {
		PageBean<Authority> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		int count = authDao.count();
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
		List<Authority> list = authDao.findAllAuthByPage(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

}
