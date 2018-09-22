package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IUserroleDao;
import com.edu.entity.Userrole;
import com.edu.service.IUserroleService;

@Service
public class UserroleService implements IUserroleService {
	
	@Autowired
	private IUserroleDao userroleDao;
	
	@Override
	public void deleteByUid(int id) {
		if (id != 0) {
			userroleDao.deleteByUid(id);
		}
	}
	
	public void updateRole(Integer[] rids, Integer id) {
		Userrole userrole = new Userrole();
		
		if (id != 0) {
			if (rids == null) {
				userroleDao.deleteByUid(id);
			} else {
				try {
					userroleDao.deleteByUid(id);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				for (Integer rid : rids) {
					userrole.setUid(id);
					userrole.setRid(rid);
					userroleDao.addUserrole(userrole);
				}
			}
		}
	}

	@Override
	public List<Userrole> findByUid(int id) {
		List<Userrole> list = null;
		if (id != 0) {
			list = userroleDao.findByUid(id);
		}
		return list;
	}
	
	@Override
	public List<Userrole> findByRid(int rid) {
		List<Userrole> list = null;
		if (rid != 0) {
			list = userroleDao.findByRid(rid);
		}
		return list;
	}
}
