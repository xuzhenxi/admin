package com.edu.service;

import java.util.List;

import com.edu.entity.Course;
import com.edu.vo.PageBean;

public interface ICourseService {
	
	public PageBean<Course> findCourseByPage(int page, int size);
	
	public void deleteCourseById(int id);
	
	public Course findById(int id);
	
	public void updateCourse(Course course);
	
	public void addCourse(Course course);
	
	public List<Course> findAllCourse();
}
