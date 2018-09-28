package com.edu.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.entity.Staff;
import com.edu.service.IStaffService;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;

@Controller
public class StaffController {
	
	@Autowired
	private IStaffService staffService;
	
	@RequestMapping("/showcount11")
	@ResponseBody
	public JsonBean findCountByDid(int id) {
		int count = 0;
		JsonBean bean = new JsonBean();
		if (id != 0) {
			try {
				count = staffService.findCountByDid(id);
				bean.setCode(1);
				bean.setMsg(count);
			} catch (Exception e) {
				e.printStackTrace();
				bean.setCode(0);
				bean.setMsg(e.getMessage());
			}
		}
		return bean;
	}
	
	@RequestMapping("/staffpage")
	@ResponseBody
	public Map<String, Object> findAllStaffByPage(int page, int limit) {
		Map<String, Object> map = new HashMap<>();
		PageBean<Staff> pageInfo = staffService.findAllStaffByPage(page, limit);
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@RequestMapping("/staffdelete.do")
	@ResponseBody
	public JsonBean deleteStuff(String no) {
		JsonBean bean = new  JsonBean();
		try {
			staffService.deleteStaffByNo(no);
			bean.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return  bean;
	}
	
	@RequestMapping("/photoupload.do")
	@ResponseBody
	public JsonBean photoUpload(MultipartFile file) {
		JsonBean bean = new JsonBean();
		
		//获取文件的名称
		String fName = file.getOriginalFilename();
		
		//保存图片的目录
		String path = "D:/git_ropo_tick/ticktack/src/main/webapp/media/images";
		File file1 = new File(path);
		//如果目录不存在，创建
		if(!file1.exists()){
			file1.mkdir();
		}
		
		File f = new File(path, fName);
		
		try {
			//上传文件
			file.transferTo(f);
			bean.setCode(1);
			bean.setMsg(fName);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}

		
		return bean;
	}
}














