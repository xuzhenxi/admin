package com.edu.utils;

import com.edu.vo.JsonBean;

public class JsonUtil {
	
	public static JsonBean JsonBeanS(Integer code, Object msg) {
		
		JsonBean bean = new JsonBean();
		bean.setCode(code);
		bean.setMsg(msg);
		return bean;
	}

}
