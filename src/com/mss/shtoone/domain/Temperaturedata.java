package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Temperaturedata implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tmpid;
	private String tmpno;
	private String tmpdata;
	private String tmpshijian;
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
	public String getTmpshijian() {
		return tmpshijian;
	}
	public void setTmpshijian(String tmpshijian) {
		this.tmpshijian = tmpshijian;
	}
}
