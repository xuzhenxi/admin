package com.edu.vo;

import java.util.List;

public class PageBean<T> {

	private int count; // 总记录数
	private int currentPage;// 当前页
	private int totalPage;// 总页数
	private int pageSize;// 每页显示多少条记录
	private List<T> pageInfos;// 分页查询到的数据
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getPageInfos() {
		return pageInfos;
	}
	public void setPageInfos(List<T> pageInfos) {
		this.pageInfos = pageInfos;
	}
	
	
}
