package com.edu.service;

import java.util.List;

import com.edu.entity.Staff;
import com.edu.vo.PageBean;

public interface IStaffService {
	
	public Integer findCountByDid(int did);
	
	public PageBean<Staff> findAllStaffByPage(int page, int limit);
	
	public void deleteStaffByNo(String no);
	
	public Staff findLastStaff();
	
	public void addStaff(Staff staff);
	
	public void updateStaff(Staff staff);
	
	public Staff findStaffByName(String name);
	
	public List<Staff> findAll();
}
