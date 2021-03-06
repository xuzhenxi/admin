package com.edu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Staff {
    private String no;

    private String name;

    private Integer did;

    private Integer flag;

    private String sex;

    private String email;

    private String qq;

    private String phone;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdate;

    private String photo;
    
    private Depart dept;
    

	public Depart getDept() {
		return dept;
	}

	public void setDept(Depart dept) {
		this.dept = dept;
	}

	public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
    
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

	@Override
	public String toString() {
		return "Staff [no=" + no + ", name=" + name + ", did=" + did + ", flag=" + flag + ", sex=" + sex + ", email="
				+ email + ", qq=" + qq + ", phone=" + phone + ", createdate=" + createdate + ", photo=" + photo
				+ ", dept=" + dept + "]";
	}
    
    
    
}