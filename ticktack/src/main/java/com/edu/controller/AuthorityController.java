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
import com.edu.utils.JsonUtil;
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
	
	@RequestMapping("/autyall")
	@ResponseBody
	public JsonBean findAllTitle() {
		List<Authority> list = null;
		JsonBean bean = new JsonBean();
		
		try {
			list = authService.findAllTitle();
			bean.setCode(1);
			bean.setMsg(list);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	
	/**
	 * 查询所有一级权限
	 * @return
	 */
	@RequestMapping("/authorityroot")
	@ResponseBody
	public JsonBean findAutyByParent0() {
		List<Authority> list = null;
		JsonBean bean = new JsonBean();
		
		try {
			list = authService.findByParentId(0);
			bean.setCode(1);
			bean.setMsg(list);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping("/authorityadd")
	@ResponseBody
	public JsonBean add(Authority auty, HttpSession session) {
		
		//获取当前登录用户账号
		String no = (String) session.getAttribute("no");
		
		try {
			JsonBean bean = authService.addJudge(auty, no);
			//如果返回bean.code不为1则判断失败不能添加返回错误信息
			if (bean.getCode() != 1) {
				return JsonUtil.JsonBeanS(0, bean.getMsg());
			}
			
			auty.setType(1);
			authService.add(auty, no);
			return JsonUtil.JsonBeanS(1, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.JsonBeanS(0, null);
		
	}
	
	@RequestMapping("/autyedit")
	@ResponseBody
	public JsonBean update(Authority auty, HttpSession session) {
		//获取当前登录用户账号
		String no = (String) session.getAttribute("no");
		
		try {
			JsonBean bean = authService.updateJudge(auty, no);
			//如果返回bean.code不为1则判断失败不能修改返回错误信息
			if (bean.getCode() != 1) {
				return JsonUtil.JsonBeanS(0, bean.getMsg());
			}
			
			authService.update(auty, no);
			return JsonUtil.JsonBeanS(1, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.JsonBeanS(0, null);
		
	}
	
	@RequestMapping("/authdelete")
	@ResponseBody
	public JsonBean delete(Integer id, HttpSession session) {
		
		String no = (String) session.getAttribute("no");
		try {
			//判断能否删除返回code=1能删除
			JsonBean bean = authService.deleteJudge(id, no);
			
			if (bean.getCode() != 1) {
				return JsonUtil.JsonBeanS(0, bean.getMsg());
			}
			
			authService.delete(id, no);
			return JsonUtil.JsonBeanS(1, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.JsonBeanS(0, null);
		
	}
	
}
