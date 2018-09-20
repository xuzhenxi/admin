package com.edu.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.ILoginlogDao;
import com.edu.entity.Loginlog;
import com.edu.service.ILoginlogService;
import com.edu.utils.AddressUtils;
import com.edu.vo.PageBean;

@Service
public class LoginlogService implements ILoginlogService{
	
	@Autowired
	private ILoginlogDao loginlogDao;
	
	@Override
	public PageBean<Loginlog> findAllLoginlogByPage(int page, int size) {
		PageBean<Loginlog> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		int count = loginlogDao.count();
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
		List<Loginlog> list = loginlogDao.findAllLoginlogByIndexAndSize(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public void addLoginlog(String ip, String no) {
		if (ip != null && no != null) {
			Loginlog loginlog = new Loginlog();
			
			AddressUtils addrUtils = new AddressUtils();
			
			try {
				String address = AddressUtils.getAddresses("ip=" + ip, "utf-8");
				loginlog.setCreatetime(new Date());
				loginlog.setIp(ip);
				loginlog.setLocation(address);
				loginlog.setNo(no);
				loginlogDao.addLoginlog(loginlog);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
	}

}
