package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IStudentDao;
import com.edu.entity.Student;
import com.edu.service.IStudentService;

@Service
public class StudentService implements IStudentService {
	
	@Autowired
	private IStudentDao studentDao;
	
	@Override
	public List<Student> findByGid(int id) {
		List<Student> list = null; 
		if (id != 0) {
			list = studentDao.findByGid(id);
		}
		return list;
	}

}
