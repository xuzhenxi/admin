package com.edu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Depart;
import com.edu.service.IDepartService;
import com.edu.service.IStaffService;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;

@Controller
public class DepartController {
	
	@Autowired
	private IDepartService departService;
	
	@RequestMapping("/departpage")
	@ResponseBody
	public Map<String, Object> findAllUser(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<Depart> pageInfo = departService.findDepartByPage(page, limit);
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@Autowired
	private IStaffService staffService;
	
	@RequestMapping("/showcount")
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
