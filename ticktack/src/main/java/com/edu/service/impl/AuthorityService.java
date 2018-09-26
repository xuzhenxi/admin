package com.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IAuthorityDao;
import com.edu.dao.IRoleauthorityDao;
import com.edu.dao.IUserDao;
import com.edu.dao.IUserroleDao;
import com.edu.entity.Authority;
import com.edu.entity.Roleauthority;
import com.edu.entity.User;
import com.edu.entity.Userrole;
import com.edu.service.IAuthorityService;
import com.edu.utils.JsonUtil;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;

@Service
public class AuthorityService implements IAuthorityService {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserroleDao userRoleDao;
	
	@Autowired
	private IRoleauthorityDao roleAuthDao;
	
	@Autowired
	private IAuthorityDao authDao;
	
	@Override
	public List<Authority> findTitleByNo(String no) {
		
		List<Authority> list = null;
		
		try {
			list = authDao.findTitleByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public PageBean<Authority> findAllAuthByPage(int page, int size) {
		PageBean<Authority> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		int count = authDao.count();
		pageInfo.setCount(count);
		
		if (count % size == 0) {
			pageInfo.setTotalPage(count / size); 
		} else {
			pageInfo.setTotalPage(count / size + 1); 
		}
		
		int index = (page - 1) * size;
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", size);
		List<Authority> list = authDao.findAllAuthByPage(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public List<Authority> findAllTitle() {
		List<Authority> list = null;
		
		try {
			list = authDao.findAllTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	//按父级id查询权限
	@Override
	public List<Authority> findByParentId(int parentId) {
		
		List<Authority> list = null;
		
		try {
			list = authDao.findByParentId(parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	//添加权限
	@Override
	public void add(Authority auty, String no) {
		
		
		try {
			//判断能否添加返回code=1能添加
			JsonBean bean = addJudge(auty, no);
			
			if (bean.getCode() != 1) {
				throw new RuntimeException((String) bean.getMsg());
			}
			
			authDao.addAuth(auty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//删除权限
	@Override
	public void delete(int aid, String no) {
		
		try {
			//判断能否删除返回code=1能删除
			JsonBean bean = deleteJudge(aid, no);
			
			if (bean.getCode() != 1) {
				throw new RuntimeException((String) bean.getMsg());
			}
			
			authDao.deleteAuth(aid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//修改权限
	@Override
	public void update(Authority auty, String no) {
		
		
		try {
			//判断能否修改返回code=1能修改
			JsonBean bean = updateJudge(auty, no);
			
			if (bean.getCode() != 1) {
				throw new RuntimeException((String) bean.getMsg());
			}
			authDao.updateAuth(auty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//删除权限判断
	@Override
	public JsonBean deleteJudge(int aid, String no) {
		Authority auty = null;
		List<Roleauthority> roleAutyList = null;
		User user = null;
		
		try {
			//查询是否存在
			auty = authDao.findAuthById(aid);
			if (auty == null) {
				return JsonUtil.JsonBeanS(0, "该权限不存在，删除失败");
			}
			
			//判读是否是管理员
			user = userDao.findByNo(no);
			List<Userrole> userRole = userRoleDao.findByUid(user.getId());
			int i = 0;
			for (Userrole userRole2 : userRole) {
				if (userRole2.getRid() == 1) {
					i = 1;
				}
			}
			if (i != 1) {
				return JsonUtil.JsonBeanS(0, "抱歉，你不是管理员");
			}
			
			//根据权限id查询角色权限
			roleAutyList = roleAuthDao.findByAid(aid);
			if (roleAutyList.size() > 0) {
				return JsonUtil.JsonBeanS(0, "有职位正在使用该权限");
			}
			
			//判断通过 可以删除
			return JsonUtil.JsonBeanS(1, null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
	}
	
	@Override
	public JsonBean updateJudge(Authority auty, String no) {
		User user = null;
		
		try {
			//判断是否为空
			if (auty == null) {
				return JsonUtil.JsonBeanS(0, "权限为空，添加失败");
			}
			
			//判读是否是管理员
			user = userDao.findByNo(no);
			List<Userrole> userRole = userRoleDao.findByUid(user.getId());
			int i = 0;
			for (Userrole userRole2 : userRole) {
				if (userRole2.getRid() == 1) {
					i = 1;
				}
			}
			if (i != 1) {
				return JsonUtil.JsonBeanS(0, "抱歉，你不是管理员");
			}
			//判断父目录是否是自己
			
			if (auty.getId() == auty.getParentid()) {
				return JsonUtil.JsonBeanS(0, "不能设自己为父目录");
			}
			//判断是否存在同名权限
			//通过权限名称查找所有权限
			List<Authority> autyList =authDao.findAuthByTitile(auty.getTitle());
			//通过要修改的权限id查找该权限的名称
			Authority auty1 = authDao.findAuthById(auty.getId());
			if (auty1.getTitle().equals(auty.getTitle())) {
				//如果修改的名字与原名相同则查到的权限个数大于一个是判定存在同名
				if (autyList.size() > 1) {
					return JsonUtil.JsonBeanS(0, "存在同名权限");
				}
			} else {
				//如果修改的名字与原名不相同则查到的权限个数大于0个是判定存在同名
				if (autyList.size() > 0) {
					return JsonUtil.JsonBeanS(0, "存在同名权限");
				}
			}
			//名称不能为空
			if (auty.getTitle() == null || auty.getTitle().equals("")) {
				return JsonUtil.JsonBeanS(0, "名称不能为空");
			}
			//路径不能为空
			if (auty.getAurl() == null || auty.getAurl().equals("")) {
				return JsonUtil.JsonBeanS(0, "路径不能为空");
			}
			
			
			//判断通过 可以修改
			return JsonUtil.JsonBeanS(1, null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
	}
	
	@Override
	public JsonBean addJudge(Authority auty, String no) {
		User user = null;
		
		try {
			//判断是否为空
			if (auty == null) {
				return JsonUtil.JsonBeanS(0, "权限为空，添加失败");
			}
			
			//判读是否是管理员
			user = userDao.findByNo(no);
			List<Userrole> userRole = userRoleDao.findByUid(user.getId());
			int i = 0;
			for (Userrole userRole2 : userRole) {
				if (userRole2.getRid() == 1) {
					i = 1;
				}
			}
			if (i != 1) {
				return JsonUtil.JsonBeanS(0, "抱歉，你不是管理员");
			}
			
			//判断是否存在同名权限
			//通过权限名称查找所有权限
			List<Authority> autyList =authDao.findAuthByTitile(auty.getTitle());
			//通过名称搜索到的权限个数大于0则存在同名不能添加
			if (autyList.size() > 0) {
				return JsonUtil.JsonBeanS(0, "存在同名权限");
			}
			
			//名称不能为空
			if (auty.getTitle() == null || auty.getTitle().equals("")) {
				return JsonUtil.JsonBeanS(0, "名称不能为空");
			}
			//路径不能为空
			if (auty.getAurl() == null || auty.getAurl().equals("")) {
				return JsonUtil.JsonBeanS(0, "路径不能为空");
			}
			
			//判断通过 可以添加
			return JsonUtil.JsonBeanS(1, null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
	}

}
