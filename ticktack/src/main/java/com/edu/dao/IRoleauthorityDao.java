package com.edu.dao;

import java.util.List;

import com.edu.entity.Roleauthority;

public interface IRoleauthorityDao {
	
public void add(Roleauthority roleAuty);
	
	/**
	 * 根据角色id删除角色权限
	 * @param id
	 */
	public void delete(int rid);
	
	/**
	 * 通过角色id查询角色权限
	 * @param rid 角色id
	 * @return
	 */
	public List<Roleauthority> findByRid(int rid);
	
	/**
	 * 通过角色id查询角色权限
	 * @param rid 角色id
	 * @return
	 */
	public List<Roleauthority> findByAid(int aid);

}