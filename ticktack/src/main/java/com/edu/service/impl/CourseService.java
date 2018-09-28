package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.ICourseDao;
import com.edu.entity.Course;
import com.edu.entity.Depart;
import com.edu.service.ICourseService;
import com.edu.vo.PageBean;

@Service
public class CourseService implements ICourseService {
	
	@Autowired
	private ICourseDao courseDao;
	
	@Override
	public PageBean<Course> findCourseByPage(int page, int size) {
		PageBean<Course> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		int count = courseDao.count();
		pageInfo.setCount(count);
		
		if (count % size == 0) {
			pageInfo.setTotalPage(count / size); 
		} else {
			pageInfo.setTotalPage(count / size + 1); 
		}
		
		int index = (page - 1) * size;
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", size);
		List<Course> list = courseDao.findByIndexAndSize(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public void deleteCourseById(int id) {
		if (id != 0) {
			courseDao.deleteCourseById(id);
		}
	}
	
	public Course findById(int id) {
		return courseDao.findById(id);
	}
	
	@Override
	public void updateCourse(Course course) {
		if (course != null) {
			courseDao.updateCourse(course);
		}
	}
	
	@Override
	public void addCourse(Course course) {
		if (course != null) {
			courseDao.addCourse(course);
		}
	}
}









