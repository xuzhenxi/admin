package com.edu.service;

import com.edu.vo.JsonBean;

public interface IRoleAutyService {
	
	/**
	 * 为角色授权
	 * @param aids 角色需要的授权 
	 * @param rid 角色id
	 * @param no 当前登录用户账号
	 */
	public void update(Integer[] aids, Integer rid, String no);
	
	/**
	 * 修改判断
	 * @param no 当前登录用户账号
	 * @return 
	 */
	public JsonBean updateJudge(String no);

}
