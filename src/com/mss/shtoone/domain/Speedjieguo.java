package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Speedjieguo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5902617367240006888L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jieguoid;
	
	private Integer speedid;
	
	private String speedno;	

	private String speeddata;	

	private String jieguoshijian;	


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

	public Integer getSpeedid() {
		return speedid;
	}

	public void setSpeedid(Integer speedid) {
		this.speedid = speedid;
	}

	public String getSpeedno() {
		return speedno;
	}

	public void setSpeedno(String speedno) {
		this.speedno = speedno;
	}

	public String getSpeeddata() {
		return speeddata;
	}

	public void setSpeeddata(String speeddata) {
		this.speeddata = speeddata;
	}
}
