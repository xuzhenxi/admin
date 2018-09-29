package com.edu.controller;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.entity.Grade;
import com.edu.entity.Staff;
import com.edu.entity.Student;
import com.edu.service.IGradeService;
import com.edu.service.IStaffService;
import com.edu.service.IStudentService;
import com.edu.utils.ExcelUtils;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;

@Controller
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private IGradeService gradeService;
	
	@Autowired
	private IStaffService staffService;
	
	@RequestMapping("/studentpage.do")
	@ResponseBody
	public Map<String, Object> findStudentByPage(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<Student> pageInfo = studentService.findStudentByPage(page, limit);
		map.put("code", 0);  //针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@RequestMapping("/studentdelete.do")
	@ResponseBody
	public JsonBean deleteStudentByNo(String no) {
		JsonBean bean = new JsonBean();
		try {
			if (no != null) {
				studentService.deleteStudentByNo(no);
				bean.setCode(1);
			} else {
				bean.setCode(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return bean;
	}
	
	@RequestMapping("/studentadd.do")
	@ResponseBody
	public JsonBean addStudent(Student stu) {
		JsonBean bean = new JsonBean();
		try {
			if (stu != null) {
				studentService.add(stu);
				bean.setCode(1);
			} else {
				bean.setCode(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return bean;
	}
	
	@RequestMapping("/studentupdate.do")
	@ResponseBody
	public JsonBean updateStudent(Student stu) {
		JsonBean bean = new JsonBean();
		try {
			if (stu != null) {
				studentService.update(stu);
				bean.setCode(1);
			} else {
				bean.setCode(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return bean;
	}
	
	@RequestMapping("/studentno.do")
	@ResponseBody
	public JsonBean findLastStuNo() {
		JsonBean bean = new JsonBean();
		try {
			Student stu = studentService.findLastStu();
			if (stu != null) {
				String no = stu.getNo();
				no = no.substring(3);
				no = "1" + no;
				no = "s" + (Integer.parseInt(no) + 1);
				no = "stu" + no.substring(2);
				bean.setCode(1);
				bean.setMsg(no);
			} else {
				bean.setCode(1);
				bean.setMsg("stu000001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg("获取账号出错");
		}
		return bean;
	}
	
	@RequestMapping("/studentbatch.do")
	@ResponseBody
	public JsonBean upload(MultipartFile file){
		JsonBean bean = new JsonBean();

		try {
			// 获取上传的文件的文件名
			String fileName = file.getOriginalFilename();
			// 获取文件的输入流
			InputStream inputStream = file.getInputStream();

			// 解析exel文件，进行导入操作

			List<Map<String, Object>>list = new ArrayList<>();
			boolean ret = ExcelUtils.isXls(fileName);
			// 根据不同的后缀，创建不同的对象
			Workbook workBook = null;
			if(ret){
				workBook = new HSSFWorkbook(inputStream);// xls
			}else{
				workBook = new XSSFWorkbook(inputStream);// xlsx
			}

			Sheet sheet = workBook.getSheetAt(0);
			int num = sheet.getLastRowNum();

			NumberFormat nf = NumberFormat.getInstance();
			//這個for循環是行数，行循环
			for(int i = 1; i <= num; i++){
				Map<String, Object> map = new HashMap<>();
				Row row = sheet.getRow(i);
				//这个是第一列，0就是第一列，工号no
				Cell cell = row.getCell(0);
				if(cell != null){
					//1.1
					map.put("no", cell.getStringCellValue());
					//System.out.println(cell.getStringCellValue());

				}
				//这是第二列 姓名name
				cell = row.getCell(1);
				if(cell != null){
					//1.2
					map.put("name", cell.getStringCellValue());
					//System.out.println(cell.getStringCellValue());
				}
				//第三列 部门department
				cell = row.getCell(2);
				if(cell != null){
					//1.2

					map.put("gid", cell.getStringCellValue());

				}
				//4列 sex
				cell = row.getCell(3);
				if(cell != null){
					//
					map.put("sex", cell.getStringCellValue());
					//System.out.println(cell.getStringCellValue());
				}

				//5列 phone
				cell = row.getCell(4);
				if(cell != null){
					//1.2
					
					map.put("phone", nf.format(cell.getNumericCellValue()));
					//System.out.println(cell.getNumericCellValue());
				}
				//6列qq
				cell = row.getCell(5);
				if(cell != null){
					//1.2
					
					map.put("qq", nf.format(cell.getNumericCellValue()));
					//System.out.println(cell.getNumericCellValue());
				}

				//7email
				cell = row.getCell(6);
				if(cell != null){
					//1.2
					map.put("email", cell.getStringCellValue());
					//System.out.println(cell.getStringCellValue());
				}
				//8身份证号
				cell = row.getCell(7);
				if(cell != null){
					//1.2
					map.put("cardno", nf.format(cell.getNumericCellValue()));
					//System.out.println(cell.getNumericCellValue());
				}

				//9毕业学校
				cell = row.getCell(8);
				if(cell != null){
					//1.2
					map.put("school", cell.getStringCellValue());
					//System.out.println(cell.getDateCellValue());
				}

				//10学历
				cell = row.getCell(9);
				if(cell != null){
					//1.2
					map.put("education", cell.getStringCellValue());
					//System.out.println(cell.getDateCellValue());
				}
				//11招生老师
				cell = row.getCell(10);
				if(cell != null){
					//1.2
					map.put("introno", cell.getStringCellValue());
					//System.out.println(cell.getDateCellValue());
				}
				//12出生日期
				cell = row.getCell(11);
				if(cell != null){
					//1.2
					map.put("birthday", cell.getDateCellValue());
					//System.out.println(cell.getDateCellValue());
				}

				//13入学日期
				cell = row.getCell(12);
				if(cell != null){
					//1.2
					map.put("createdate", cell.getDateCellValue());
					//System.out.println(cell.getDateCellValue());
				}
				//循环一次提交一次···,下一次就又覆盖掉了，在重新提交···

				list.add(map);

			}


			workBook.close();


			for (Map<String, Object> map : list) {
				String name =(String) map.get("gid");
				Grade grade = gradeService.findGradeByName(name);
				int gid = grade.getId();
				map.put("gid",gid );
				//获取招生老师姓名
				String staffName = (String)map.get("introno");
				Staff staff = staffService.findStaffByName(staffName);
				String introno = staff.getNo();
				map.put("introno", introno);
				//然后将数据添加入数据库中
				try {
					studentService.addStu(map);
					bean.setCode(1);
				} catch (Exception e) {
					e.printStackTrace();
					bean.setCode(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			bean.setCode(0);
		}
		return bean;
	}
}













