package com.edu.service;

import java.util.List;
import java.util.Map;

import com.edu.entity.Student;
import com.edu.vo.PageBean;

public interface IStudentService {
	
	public List<Student> findByGid(int id);
	
	public PageBean<Student> findStudentByPage(int page, int size);
	
	public void deleteStudentByNo(String no);
	
	public void add(Student stu);
	
	public void update(Student stu);
	
	public Student findLastStu();
	
	public void addStu(Map<String,Object> map);
	
}
