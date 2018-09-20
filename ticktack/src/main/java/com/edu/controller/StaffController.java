package com.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.service.IStaffService;
import com.edu.vo.JsonBean;

@Controller
public class StaffController {
	
	@Autowired
	private IStaffService staffService;
	
	@RequestMapping("/showcount11")
	@ResponseBody
	public JsonBean findCountByDid(int id) {
		int count = 0;
		JsonBean bean = new JsonBean();
		if (id != 0) {
			try {
				count = staffService.findCountByDid(id);
				bean.setCode(1);
				bean.setMsg(count);
			} catch (Exception e) {
				e.printStackTrace();
				bean.setCode(0);
				bean.setMsg(e.getMessage());
			}
		}
		return bean;
	}
	
}
