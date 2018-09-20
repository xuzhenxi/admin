package com.edu.service;

import java.util.List;

import com.edu.entity.Authority;
import com.edu.vo.PageBean;

public interface IAuthorityService {

	  /**
     * 根据账号查找左侧菜单栏
     * @return 返回Authority集合
     */
    public List<Authority> findTitleByNo(String no);
    
    public PageBean<Authority> findAllAuthByPage(int page, int size);
}
