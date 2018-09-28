package com.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Grade;
import com.edu.entity.Student;
import com.edu.service.IGradeService;
import com.edu.service.IStudentService;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;

@Controller
public class GradeController {
	
	@Autowired
	private IGradeService gradeService;
	
	@Autowired
	private IStudentService studentService;
	
	@RequestMapping("/gradepage.do")
	@ResponseBody
	public Map<String, Object> findAllUser(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<Grade> pageInfo = gradeService.findGradeByPage(page, limit);
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@RequestMapping("/gradedelete.do")
	@ResponseBody
	public JsonBean deleteById(int id) {
		JsonBean bean = new JsonBean();
		List<Student> list = studentService.findByGid(id);
		try {
			if (list.size() == 0) {
				gradeService.deleteById(id);
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
}






