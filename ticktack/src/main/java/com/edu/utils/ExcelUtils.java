package com.edu.utils;

import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	
	
	// 导入
	public static List<Map<String,Object>> importExcel(String fileName, InputStream inputStream) throws Exception{
		List<Map<String, Object>>list = new ArrayList<>();
		boolean ret = isXls(fileName);
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
				
				map.put("did", cell.getStringCellValue());
				
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
			//6列邮箱email
			cell = row.getCell(5);
			if(cell != null){
				//1.2
				map.put("email", nf.format(cell.getNumericCellValue()));
				//System.out.println(cell.getNumericCellValue());
			}
			
			//7qq
			cell = row.getCell(6);
			if(cell != null){
				//1.2
				map.put("qq", nf.format(cell.getNumericCellValue()));
				//System.out.println(cell.getNumericCellValue());
			}
			//8入职日期
			cell = row.getCell(7);
			if(cell != null){
				//1.2
				map.put("createdate", cell.getDateCellValue());
				//System.out.println(cell.getDateCellValue());
			}
			//循环一次提交一次···,下一次就又覆盖掉了，在重新提交···
			
			list.add(map);
			
		}
		
		
		workBook.close();
		return list;
	
		
	}
	
	public static void exportExcel(OutputStream outputStream) throws Exception{
		List<Map<String, Object>> list = new ArrayList<>();
		for(int i = 0; i < 5; i++){
			Map<String, Object> map = new HashMap<>();
			map.put("name","zhangsan" + i);
			map.put("age", 20 + i);
			map.put("tel", "12345678901");
			list.add(map);
		}
		
		String[] titles = new String[]{"姓名", "年龄", "电话"};
		
		// 新建工作簿对象
		XSSFWorkbook workBook = new XSSFWorkbook();
		// 创建sheet对象
		XSSFSheet sheet = workBook.createSheet("学生信息");
		// 创建行,标题行
		XSSFRow row = sheet.createRow(0);
		for(int i = 0; i < titles.length; i++){
			// 创建单元格
			XSSFCell cell = row.createCell(i);
			// 设置单元格内容
			cell.setCellValue(titles[i]);
		}
		
		for(int i = 0; i < list.size(); i++){
			XSSFRow row2 = sheet.createRow(i + 1);
			row2.createCell(0).setCellValue((String)list.get(i).get("name"));
			row2.createCell(1).setCellValue((Integer)list.get(i).get("age"));
			row2.createCell(2).setCellValue((String)list.get(i).get("tel"));
		}
		
		workBook.write(outputStream);
				
		workBook.close();
	}
	
	// .xls .XLS .xlsx .XLSX
	// aaa.xls  ddd.XLSX
	public static boolean isXls(String fileName){
		//   js /^.+\.(xls)$/i
		// (?i) 右边的内容，忽略大小写
		if(fileName.matches("^.+\\.(?i)(xls)$")){
			return true;
		}else if(fileName.matches("^.+\\.(?i)(xlsx)$")){
			return false;
		}else{
			throw new RuntimeException("文件格式不对");
		}
		//Pattern p = Pattern.compile("^.+\\.(xls)$", )
		
	}
}
