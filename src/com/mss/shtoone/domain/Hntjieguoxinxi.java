package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hntjieguoxinxi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2940141349610982420L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String jieguomiaoshu;
	private Integer jieguoid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJieguomiaoshu() {
		return jieguomiaoshu;
	}
	public void setJieguomiaoshu(String jieguomiaoshu) {
		this.jieguomiaoshu = jieguomiaoshu;
	}
	public Integer getJieguoid() {
		return jieguoid;
	}
	public void setJieguoid(Integer jieguoid) {
		this.jieguoid = jieguoid;
	}

}
