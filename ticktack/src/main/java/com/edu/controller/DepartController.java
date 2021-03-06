package com.edu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
	
	@Autowired
	private IStaffService staffService;
	
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
	
	@RequestMapping("/departdelete")
	@ResponseBody
	public JsonBean deleteDepartById(int id) {
		JsonBean bean = new JsonBean();
		
		Integer count = staffService.findCountByDid(id);
		
		try {
			if (count == 0) {
				departService.deleteDepartById(id);
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
	
	@RequestMapping("/updatedepart")
	@ResponseBody
	public JsonBean updateDepart(Depart depart) {
		JsonBean bean = new JsonBean();
//		Depart depart = new Depart();
//		depart.setId(id);
//		depart.setName(name);
//		System.out.println(createtime);
//		depart.setCreatetime(createtime);
		
		try {
			if (depart.getName() != null) {
				departService.updateDepart(depart);
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
	
	@RequestMapping("/departadd.do")
	@ResponseBody
	public JsonBean addDepart(Depart depart) {
		JsonBean bean = new JsonBean();
		
		if (depart.getName() == null) {
			bean.setCode(2);
		} else if (depart.getCreatetime() == null) {
			bean.setCode(3);
		} else {
			departService.addDepart(depart);
			bean.setCode(1);
		}
		
		return bean;
	}
	
	@RequestMapping("/departall.do")
	@ResponseBody
	public JsonBean findAll() {
		JsonBean bean = new JsonBean();
		List<Depart> list = null;
		
		try {
			list = departService.findAll();
			bean.setCode(1);
			bean.setMsg(list);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
}








