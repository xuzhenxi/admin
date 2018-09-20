package com.edu.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Loginlog {
    private Long id;

    private String ip;

    private String no;

    private Date createtime;

    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }
    
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
    public Date getCreatetime() {
    	
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}