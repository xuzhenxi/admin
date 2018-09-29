package com.edu.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("/staffadd.do")
	@ResponseBody
	public JsonBean addStaff(Staff staff) {
		JsonBean bean = new JsonBean();
		try {
			if (staff != null) {
				staffService.addStaff(staff);
				bean.setCode(1);
			} else {
				bean.setCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return bean;
	}
	
	@RequestMapping("/staffno.do")
	@ResponseBody
	public JsonBean findLastStaff() {
		JsonBean bean = new JsonBean();
		try {
			Staff staff = staffService.findLastStaff();
			if (staff != null) {
				String no = staff.getNo();
				no = no.substring(2);
				no = "1" + no;
				no = "qf" + (Integer.parseInt(no) + 1);
				no = "qf" + no.substring(3);
				bean.setCode(1);
				bean.setMsg(no);
			} else {
				bean.setCode(1);
				bean.setMsg("qf000001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg("获取账号出错");
		}
		
		return bean;
	}
	
	@RequestMapping("/staffupdate.do")
	@ResponseBody
	public JsonBean updateStaff(Staff staff) {
		JsonBean bean = new JsonBean();
		try {
			if (staff != null) {
				staffService.updateStaff(staff);
				bean.setCode(1);
			} else {
				bean.setCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return bean;
	}
	
	@RequestMapping("/staffall.do")
	@ResponseBody
	public JsonBean findAllStaff() {
		JsonBean bean = new JsonBean();
		List<Staff> list = null;
		try {
			list = staffService.findAll();
			bean.setCode(1);
			bean.setMsg(list);
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		return bean;
	}
}














