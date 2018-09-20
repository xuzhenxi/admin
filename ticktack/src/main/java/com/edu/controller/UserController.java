package com.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.User;
import com.edu.service.ILoginlogService;
import com.edu.service.IUserService;
import com.edu.service.IUserroleService;
import com.edu.utils.IpGet;
import com.edu.utils.MD5Utils;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILoginlogService loginlogService;
	
	@Autowired
	private IUserroleService userrolrService;
	
	@RequestMapping("/login1")
	public String login(String no, String pass, HttpServletRequest request){
		
		UsernamePasswordToken token = new UsernamePasswordToken(no, MD5Utils.getMD5(pass));
		// 设置 记住我=true
//		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
			HttpSession session = request.getSession(false);
			session.setAttribute("no", no);
			IpGet ipget = new IpGet();
			String ip = ipget.getIpAddr(request);
			loginlogService.addLoginlog(ip, no);
			return "redirect:index.html";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "redirect:login.html";
		}
	}
	
	@RequestMapping("/userall")
	@ResponseBody
	public Map<String, Object> findAllUser(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<User> pageInfo = userService.findUserByPage(page, limit);
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@RequestMapping("/showwork")
	@ResponseBody
	public JsonBean findWorkById(int id) {
		List<String> list = null;
		JsonBean bean = new JsonBean();
		try {
			list = userService.findWorkById(id);
			bean.setCode(1);
			bean.setMsg(list);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping("/userdel")
	@ResponseBody
	public JsonBean deleteUserById(int id, HttpSession session) {
		JsonBean bean = new JsonBean();
		User user = userService.findUserById(id);
		
		String no = (String) session.getAttribute("no");
		User user2 = userService.findUserByNo(no);
		
		if (id == 0) {
			bean.setCode(2);
			bean.setMsg("查无此人");
		} else if (user.getNo().equals(no)) {
			bean.setCode(3);
			bean.setMsg("无法删除自己");
		} else if (user2.getFlag() != 1) {
			bean.setCode(4);
			bean.setMsg("没有权限");
		} else {
			userrolrService.deleteByUid(id);
			userService.deleteUserById(id);
			bean.setCode(1);
			bean.setMsg("删除成功");
		}
		
		return bean;
	}

}





















