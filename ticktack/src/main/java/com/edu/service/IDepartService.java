package com.edu.service;

import com.edu.entity.Depart;
import com.edu.vo.PageBean;

public interface IDepartService {
	
	public PageBean<Depart> findDepartByPage(int page, int size);
	
	public void deleteDepartById(int id);
	
	public void updateDepart(Depart depart);
	
	public void addDepart(Depart depart);

}
