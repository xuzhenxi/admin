package com.edu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.service.IRoleAutyService;
import com.edu.utils.JsonUtil;
import com.edu.vo.JsonBean;

@Controller
public class RoleAuthorityController {
	
	@Autowired
	private IRoleAutyService roleAutyService;
	
	/**
	 * 修改角色权限
	 * @param aids 权限id
	 * @param rid 角色id
	 * @param session 
	 * @return
	 */
	@RequestMapping("/roleAutyEdit.do")
	@ResponseBody
	public JsonBean update(Integer[] aids, Integer rid, HttpSession session) {
		
		String no = (String) session.getAttribute("no");
		
		try {
			roleAutyService.update(aids, rid, no);
			return JsonUtil.JsonBeanS(1000, null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
	}

}
