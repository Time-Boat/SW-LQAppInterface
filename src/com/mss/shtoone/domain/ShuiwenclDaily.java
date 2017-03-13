package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShuiwenclDaily implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5383808469705834164L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dailyid;    

	private String dailyrq;	

	private String dailycl;	
	
	private String dailyps;	

	private String dailyxzcl;

	private String dailysbbh;
	
	private String dailybeizhu;

	public int getDailyid() {
		return dailyid;
	}

	public void setDailyid(int dailyid) {
		this.dailyid = dailyid;
	}

	public String getDailyrq() {
		return dailyrq;
	}

	public void setDailyrq(String dailyrq) {
		this.dailyrq = dailyrq;
	}

	public String getDailycl() {
		return dailycl;
	}

	public void setDailycl(String dailycl) {
		this.dailycl = dailycl;
	}

	public String getDailyps() {
		return dailyps;
	}

	public void setDailyps(String dailyps) {
		this.dailyps = dailyps;
	}

	public String getDailyxzcl() {
		return dailyxzcl;
	}

	public void setDailyxzcl(String dailyxzcl) {
		this.dailyxzcl = dailyxzcl;
	}

	public String getDailysbbh() {
		return dailysbbh;
	}

	public void setDailysbbh(String dailysbbh) {
		this.dailysbbh = dailysbbh;
	}

	public String getDailybeizhu() {
		return dailybeizhu;
	}

	public void setDailybeizhu(String dailybeizhu) {
		this.dailybeizhu = dailybeizhu;
	}

	
}
