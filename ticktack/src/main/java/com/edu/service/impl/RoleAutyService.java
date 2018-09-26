package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IRoleauthorityDao;
import com.edu.dao.IUserDao;
import com.edu.dao.IUserroleDao;
import com.edu.entity.Roleauthority;
import com.edu.entity.User;
import com.edu.entity.Userrole;
import com.edu.service.IRoleAutyService;
import com.edu.utils.JsonUtil;
import com.edu.vo.JsonBean;

@Service
public class RoleAutyService implements IRoleAutyService {

	@Autowired
	private IRoleauthorityDao roleAutyDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserroleDao userRoleDao;
	
	@Override
	public void update(Integer[] aids, Integer rid, String no) {
		
		Roleauthority roleAuty = new Roleauthority();
		
		try {
			//删除所有用户权限
			roleAutyDao.delete(rid);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (aids != null) {
			for (Integer aid : aids) {
				roleAuty.setAid(aid);
				roleAuty.setRid(rid);
				try {
					roleAutyDao.add(roleAuty);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	@Override
	public JsonBean updateJudge(String no) {
		User user = userDao.findByNo(no);
		//获取当前登录用角色id
		List<Userrole> roleList = userRoleDao.findByUid(user.getId());
		int flag = 0;
		for (Userrole userRole2 : roleList) {
			if(userRole2.getRid() == 1) {
				flag = 1;
			}
			
		}
		
		if (flag != 1) {
			return JsonUtil.JsonBeanS(0, "没有管理员权限");
		}
		
		return JsonUtil.JsonBeanS(1, null);
	}

}
