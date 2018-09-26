package com.edu.service;

import java.util.List;

import com.edu.entity.Authority;
import com.edu.vo.JsonBean;
import com.edu.vo.PageBean;

public interface IAuthorityService {

    public List<Authority> findTitleByNo(String no);
    
    public PageBean<Authority> findAllAuthByPage(int page, int size);
    
    public List<Authority> findAllTitle();
    
    public List<Authority> findByParentId(int parentId);
    
    public void add(Authority auth, String no);
    
    public void delete(int aid, String no);
    
    public void update(Authority auty, String no);
    
    public JsonBean deleteJudge(int aid, String no);
    
    public JsonBean updateJudge(Authority auth, String no);
    
    public JsonBean addJudge(Authority auth, String no);
}
