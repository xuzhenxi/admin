package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.Course;

public interface ICourseDao {
	
	public int count();
	
    public List<Course> findByIndexAndSize(Map<String, Object> map);

	public void deleteCourseById(int id);
	
	public Course findById(int id);
	
	public void updateCourse(Course course);
	
	public void addCourse(Course course);
	
	public String findCnameById(int id);
	
}









