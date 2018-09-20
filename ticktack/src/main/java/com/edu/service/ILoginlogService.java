package com.edu.service;

import com.edu.entity.Loginlog;
import com.edu.vo.PageBean;

public interface ILoginlogService {
	
	public PageBean<Loginlog> findAllLoginlogByPage(int page, int size);
	
	public void addLoginlog(String ip, String no);

}
