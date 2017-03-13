package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChuliaokouTemperaturejieguo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3899974507066240729L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jieguoid;
	
	private Integer tmpid;
	
	private String tmpno;	

	private String tmpdata;	

	private String jieguoshijian;	

	
	public Integer getTmpid() {
		return tmpid;
	}

	public void setTmpid(Integer tmpid) {
		this.tmpid = tmpid;
	}

	public String getTmpno() {
		return tmpno;
	}

	public void setTmpno(String tmpno) {
		this.tmpno = tmpno;
	}

	public String getTmpdata() {
		return tmpdata;
	}

	public void setTmpdata(String tmpdata) {
		this.tmpdata = tmpdata;
	}

	public Integer getJieguoid() {
		return jieguoid;
	}

	public void setJieguoid(Integer jieguoid) {
		this.jieguoid = jieguoid;
	}

	public String getJieguoshijian() {
		return jieguoshijian;
	}

	public void setJieguoshijian(String jieguoshijian) {
		this.jieguoshijian = jieguoshijian;
	}
}
