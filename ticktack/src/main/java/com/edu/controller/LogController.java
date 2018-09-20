package com.edu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Loginlog;
import com.edu.service.ILoginlogService;
import com.edu.vo.PageBean;

@Controller
public class LogController {
	
	@Autowired
	private ILoginlogService loginlogService;
	
	@RequestMapping("/loginloglist")
	@ResponseBody
	public Map<String, Object> findAllLoginlog(int page, int limit) {
		Map<String, Object> map = new HashMap<>();
		PageBean<Loginlog> pageInfo = loginlogService.findAllLoginlogByPage(page, limit);
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
}
