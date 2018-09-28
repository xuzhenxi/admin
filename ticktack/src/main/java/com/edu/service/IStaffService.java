package com.edu.service;

import com.edu.entity.Staff;
import com.edu.vo.PageBean;

public interface IStaffService {
	
	public Integer findCountByDid(int did);
	
	public PageBean<Staff> findAllStaffByPage(int page, int limit);
	
	public void deleteStaffByNo(String no);
}
