package com.edu.dao;

import java.util.List;
import java.util.Map;

import com.edu.entity.Student;

public interface IStudentDao {
	
	public int findScountByGid(int id);
	
	public List<Student> findByGid(int id);
	
    public int count();
	
    public List<Student> findByIndexAndSize(Map<String, Object> map);
    
    public void deleteStudentByNo(String no);
    
    public void add(Student stu);
    
    public void update(Student stu);
    
    public Student findLastStu();
    
    public Student findByNo(String no);

}