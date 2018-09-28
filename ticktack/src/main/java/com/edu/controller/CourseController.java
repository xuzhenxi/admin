package com.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Course;
import com.edu.entity.Grade;
import com.edu.service.ICourseService;
import com.edu.service.IGradeService;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;

@Controller
public class CourseController {
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IGradeService gradeService;
	
	@RequestMapping("/coursepage.do")
	@ResponseBody
	public Map<String, Object> findAllCourse(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<Course> pageInfo = courseService.findCourseByPage(page, limit);
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@RequestMapping("/coursedelete.do")
	@ResponseBody
	public JsonBean deleteCourseById(int id) {
		JsonBean bean = new JsonBean();
		List<Grade> list = gradeService.findGradeByCid(id);
		try {
			if (list.size() == 0) {
				courseService.deleteCourseById(id);
				bean.setCode(1);
			} else {
				bean.setCode(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return bean;
	}
	
	@RequestMapping("/courseupdate.do")
	@ResponseBody
	public JsonBean updateCourse(Course course) {
		JsonBean bean = new JsonBean();
		try {
			if (course.getName() != null && course.getWeek() != null) {
				courseService.updateCourse(course);
				bean.setCode(1);
			} else {
				bean.setCode(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return bean;
	}
	
	@RequestMapping("/courseadd.do")
	@ResponseBody
	public JsonBean addCourse(Course course) {
		JsonBean bean = new JsonBean();
		
		try {
			if (course.getName() == null) {
				bean.setCode(2);
			} else if (course.getCreatedate() == null) {
				bean.setCode(3);
			} else if (course.getWeek() == null) {
				bean.setCode(4);
			} else {
				courseService.addCourse(course);
				bean.setCode(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return bean;
	}
}













