package com.edu.service;

import java.util.List;

import com.edu.entity.User;
import com.edu.vo.PageBean;

public interface IUserService {
	
	public PageBean<User> findUserByPage(int page, int size);
	
	public List<String> findWorkById(int id);
}
