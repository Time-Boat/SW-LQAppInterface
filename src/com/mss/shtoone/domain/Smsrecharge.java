package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Smsrecharge implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5511066193160624933L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Integer biaoduanid;
	
	private Integer rechargecount;
	
	private Integer submitstate;
	
	private String applytime;
	
	private String rechargetime;
	
	private String rechargestate;
	
	private String remark;
	
	private String rechargeremark;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getBiaoduanid() {
		return biaoduanid;
	}

	public void setBiaoduanid(Integer biaoduanid) {
		this.biaoduanid = biaoduanid;
	}

	public Integer getRechargecount() {
		return rechargecount;
	}

	public void setRechargecount(Integer rechargecount) {
		this.rechargecount = rechargecount;
	}

	public String getApplytime() {
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	public String getRechargetime() {
		return rechargetime;
	}

	public void setRechargetime(String rechargetime) {
		this.rechargetime = rechargetime;
	}

	public String getRechargestate() {
		return rechargestate;
	}

	public void setRechargestate(String rechargestate) {
		this.rechargestate = rechargestate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSubmitstate() {
		return submitstate;
	}

	public void setSubmitstate(Integer submitstate) {
		this.submitstate = submitstate;
	}

	public String getRechargeremark() {
		return rechargeremark;
	}

	public void setRechargeremark(String rechargeremark) {
		this.rechargeremark = rechargeremark;
	}

}
