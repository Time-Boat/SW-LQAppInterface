package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LiqingclDaily implements Serializable {

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

	private String dailycd;
	
	private String dailykd;
	
	private String dailyhd;
	
	private String dailymd;
	
	private String dailysjhd;
	
	private String dailyxh;

	private String dailysbbh;
	
	private String dailybeizhu;
	
	private String dailybuwei;
	
	
    
	public String getDailysjhd() {
		return dailysjhd;
	}

	public void setDailysjhd(String dailysjhd) {
		this.dailysjhd = dailysjhd;
	}

	public String getDailyxh() {
		return dailyxh;
	}

	public void setDailyxh(String dailyxh) {
		this.dailyxh = dailyxh;
	}

	public String getDailybuwei() {
		return dailybuwei;
	}

	public void setDailybuwei(String dailybuwei) {
		this.dailybuwei = dailybuwei;
	}

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

	public String getDailyxzcl() {
		return dailyxzcl;
	}

	public void setDailyxzcl(String dailyxzcl) {
		this.dailyxzcl = dailyxzcl;
	}

	public String getDailycd() {
		return dailycd;
	}

	public void setDailycd(String dailycd) {
		this.dailycd = dailycd;
	}

	public String getDailykd() {
		return dailykd;
	}

	public void setDailykd(String dailykd) {
		this.dailykd = dailykd;
	}

	public String getDailyhd() {
		return dailyhd;
	}

	public void setDailyhd(String dailyhd) {
		this.dailyhd = dailyhd;
	}

	public String getDailymd() {
		return dailymd;
	}

	public void setDailymd(String dailymd) {
		this.dailymd = dailymd;
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

	public String getDailyps() {
		return dailyps;
	}

	public void setDailyps(String dailyps) {
		this.dailyps = dailyps;
	}
}
