package com.edu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Role;
import com.edu.service.IRoleService;
import com.edu.vo.PageBean;

@Controller
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/rolepage")
	@ResponseBody
	public Map<String, Object> findAllRole(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<Role> pageInfo = roleService.findAllRoleByPage(page, limit);
		
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
}
