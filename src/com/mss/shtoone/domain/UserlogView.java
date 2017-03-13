package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UserlogView implements Serializable{
	private static final long serialVersionUID = 6477534393139687850L;

	@Id
	private int logid;
	
	private Integer loguserid;

	private String logname;
	
	private String logdate;
	
	private String logoperate;
	
	private String logip;
	
	private int id;
	private String name;	
	private String fullname;
	private String pwd;
	private String remark; 
	private Integer usertype; 
	private Integer biaoshiid; 
	private Integer firstlogin; 
	private String disabled;  
	private String ismanager; 

	public int getLogid() {
		return logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}


	public String getLogdate() {
		return logdate;
	}

	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}

	public String getLogoperate() {
		return logoperate;
	}

	public void setLogoperate(String logoperate) {
		this.logoperate = logoperate;
	}

	public String getLogip() {
		return logip;
	}

	public void setLogip(String logip) {
		this.logip = logip;
	}

	public Integer getLoguserid() {
		return loguserid;
	}

	public void setLoguserid(Integer loguserid) {
		this.loguserid = loguserid;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getBiaoshiid() {
		return biaoshiid;
	}

	public void setBiaoshiid(Integer biaoshiid) {
		this.biaoshiid = biaoshiid;
	}

	public Integer getFirstlogin() {
		return firstlogin;
	}

	public void setFirstlogin(Integer firstlogin) {
		this.firstlogin = firstlogin;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getIsmanager() {
		return ismanager;
	}

	public void setIsmanager(String ismanager) {
		this.ismanager = ismanager;
	}
}
