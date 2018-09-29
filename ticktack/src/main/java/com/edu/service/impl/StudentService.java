package com.edu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.IStudentDao;
import com.edu.entity.Student;
import com.edu.service.IStudentService;
import com.edu.vo.PageBean;

@Service
public class StudentService implements IStudentService {
	
	@Autowired
	private IStudentDao studentDao;
	
	@Override
	public List<Student> findByGid(int id) {
		List<Student> list = null; 
		if (id != 0) {
			list = studentDao.findByGid(id);
		}
		return list;
	}

	@Override
	public PageBean<Student> findStudentByPage(int page, int size) {
		PageBean<Student> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(size);
		pageInfo.setCurrentPage(page);
		
		int count = studentDao.count();
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
		List<Student> list = studentDao.findByIndexAndSize(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}
	
	@Override
	public void deleteStudentByNo(String no) {
		if (no != null) {
			studentDao.deleteStudentByNo(no);
		}
	}

	@Override
	public void add(Student stu) {
		if (stu != null) {
			studentDao.add(stu);
		}
	}

	@Override
	public void update(Student stu) {
		if (stu != null) {
			studentDao.update(stu);
		}
	}

	@Override
	public Student findLastStu() {
		Student stu = null;
		try {
			stu = studentDao.findLastStu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stu;
	}

	@Override
	public void addStu(Map<String, Object> map) {
		Student stu = new Student();
		
		stu.setNo((String)map.get("no"));
		stu.setName((String)map.get("name"));
		stu.setGid((Integer)map.get("gid"));
		stu.setSex((String)map.get("sex"));
		stu.setPhone((String)map.get("phone"));
		stu.setQq((String)map.get("qq"));
		stu.setEmail((String)map.get("email"));
		stu.setCardno((String)map.get("cardno"));
		stu.setSchool((String)map.get("school"));
		stu.setEducation((String)map.get("education"));
		stu.setIntrono((String)map.get("introno"));
		stu.setBirthday((Date)map.get("birthday"));
		stu.setCreatedate((Date)map.get("createdate"));
		
		try {
			studentDao.add(stu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}









