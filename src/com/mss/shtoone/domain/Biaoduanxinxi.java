package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Biaoduanxinxi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5075491324739793497L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String biaoduanminchen;
	private Integer orderid;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBiaoduanminchen() {
		return biaoduanminchen;
	}
	public void setBiaoduanminchen(String biaoduanminchen) {
		this.biaoduanminchen = biaoduanminchen;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
}
