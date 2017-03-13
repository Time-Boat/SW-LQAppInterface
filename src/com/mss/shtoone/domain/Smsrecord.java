package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Smsrecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3365573158298208975L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Integer biaoduanid;
	
	private Integer smscount;
	
	private Integer totalcount;
	
	private Integer successcount;
	
	private Integer failcount;
	
	private Integer isalarm;
	
	private Integer completealarm;
	
	private Integer alarmcount;
	
	private String alarmnumber;
	

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Integer getSuccesscount() {
		return successcount;
	}

	public void setSuccesscount(Integer successcount) {
		this.successcount = successcount;
	}

	public Integer getBiaoduanid() {
		return biaoduanid;
	}

	public void setBiaoduanid(Integer biaoduanid) {
		this.biaoduanid = biaoduanid;
	}

	public Integer getSmscount() {
		return smscount;
	}

	public void setSmscount(Integer smscount) {
		this.smscount = smscount;
	}

	public Integer getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}

	public Integer getFailcount() {
		return failcount;
	}

	public void setFailcount(Integer failcount) {
		this.failcount = failcount;
	}

	public Integer getIsalarm() {
		return isalarm;
	}

	public void setIsalarm(Integer isalarm) {
		this.isalarm = isalarm;
	}

	public Integer getAlarmcount() {
		return alarmcount;
	}

	public void setAlarmcount(Integer alarmcount) {
		this.alarmcount = alarmcount;
	}

	public String getAlarmnumber() {
		return alarmnumber;
	}

	public void setAlarmnumber(String alarmnumber) {
		this.alarmnumber = alarmnumber;
	}

	public Integer getCompletealarm() {
		return completealarm;
	}

	public void setCompletealarm(Integer completealarm) {
		this.completealarm = completealarm;
	}

	

}
