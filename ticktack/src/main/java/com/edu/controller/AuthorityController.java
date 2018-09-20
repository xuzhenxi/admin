package com.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Authority;
import com.edu.entity.User;
import com.edu.service.IAuthorityService;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;
import com.mysql.fabric.xmlrpc.base.Array;

@Controller
public class AuthorityController {
	
	@Autowired
	private IAuthorityService authService;
	
	@RequestMapping("/usermenu")
	@ResponseBody
	public JsonBean findTitleByNo(HttpSession session) {
		String no = (String) session.getAttribute("no");
		List<Authority> list = null;
		
		JsonBean bean = new JsonBean();
		
		try {
			list = authService.findTitleByNo(no);
			bean.setCode(1);
			bean.setMsg(list);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping("/authoritylist")
	@ResponseBody
	public Map<String, Object> findAllUser(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<Authority> pageInfo = authService.findAllAuthByPage(page, limit);
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
}
