package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Xiangmubuxinxi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1905292256975175237L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String xiangmubuminchen;
	private Integer biaoduanid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getXiangmubuminchen() {
		return xiangmubuminchen;
	}
	public void setXiangmubuminchen(String xiangmubuminchen) {
		this.xiangmubuminchen = xiangmubuminchen;
	}
	public Integer getBiaoduanid() {
		return biaoduanid;
	}
	public void setBiaoduanid(Integer biaoduanid) {
		this.biaoduanid = biaoduanid;
	}

}
