package com.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Role;
import com.edu.entity.Userrole;
import com.edu.service.IRoleService;
import com.edu.service.IUserroleService;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;

@Controller
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserroleService userroleService;
	
	@RequestMapping("/rolepage")
	@ResponseBody
	public Map<String, Object> findAllRole(int page, int limit, String info, String name){
		Map<String, Object> map = new HashMap<>();
		PageBean<Role> pageInfo = roleService.findAllRoleByPage(page, limit, info, name);
		
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@RequestMapping("/roleall")
	@ResponseBody
	public JsonBean findAllRole() {
		JsonBean bean = new JsonBean();
		List<Role> list = null;
		
		try {
			list = roleService.findAllRole();
			bean.setCode(1);
			bean.setMsg(list);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
	@RequestMapping("/roledel")
	@ResponseBody
	public JsonBean deleteById(int id) {
		JsonBean bean = new JsonBean();
		Userrole userrole = new Userrole();
		List<Userrole> list = null;
		
		list = userroleService.findByRid(id);
		if (id != 0) {
			try {
				if (list.size() == 0) {
					roleService.deleteById(id);
					bean.setCode(1);
				} else {
					bean.setCode(2);
				}
			} catch (Exception e) {
				e.printStackTrace();
				bean.setCode(0);
				bean.setMsg(e.getMessage());
			}
		}
		
		return bean;
	}
	
	@RequestMapping("/sonroleall")
	@ResponseBody
	public JsonBean findAllSonRole() {
		JsonBean bean = new JsonBean();
		List<Role> list = null;
		
		try {
			list = roleService.findAllSonOfRole();
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
















