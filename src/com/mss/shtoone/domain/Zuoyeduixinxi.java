package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Zuoyeduixinxi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439296919750849071L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String zuoyeduiminchen;
	private Integer xiangmubuid;
	private Integer biaoduanid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZuoyeduiminchen() {
		return zuoyeduiminchen;
	}
	public void setZuoyeduiminchen(String zuoyeduiminchen) {
		this.zuoyeduiminchen = zuoyeduiminchen;
	}
	public Integer getXiangmubuid() {
		return xiangmubuid;
	}
	public void setXiangmubuid(Integer xiangmubuid) {
		this.xiangmubuid = xiangmubuid;
	}
	public Integer getBiaoduanid() {
		return biaoduanid;
	}
	public void setBiaoduanid(Integer biaoduanid) {
		this.biaoduanid = biaoduanid;
	}


}
