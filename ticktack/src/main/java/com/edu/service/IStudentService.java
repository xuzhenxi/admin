package com.edu.service;

import java.util.List;

import com.edu.entity.Student;

public interface IStudentService {
	
	public List<Student> findByGid(int id);
}
