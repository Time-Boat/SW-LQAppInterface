package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Yandudata implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -303471616706907577L;

	@Id
	private Integer ydid;
	
	private String zh;	

	private String wd_set;	

	private String speed;		
	
	private String length1;	
	
	private String length2;	
	
	private String length3;
	
	private String length1_wd;		
	private String length2_wd;		
	private String length3_wd;		
	private String date_time;
	private String shebeibianhao;
	private String baocunshijian;
	private String kehuduanbianhao;
	private String biaoshi;
	private String caijishijian;
	
	public Integer getYdid() {
		return ydid;
	}
	public void setYdid(Integer ydid) {
		this.ydid = ydid;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getWd_set() {
		return wd_set;
	}
	public void setWd_set(String wdSet) {
		wd_set = wdSet;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getLength1() {
		return length1;
	}
	public void setLength1(String length1) {
		this.length1 = length1;
	}
	public String getLength2() {
		return length2;
	}
	public void setLength2(String length2) {
		this.length2 = length2;
	}
	public String getLength3() {
		return length3;
	}
	public void setLength3(String length3) {
		this.length3 = length3;
	}
	public String getLength1_wd() {
		return length1_wd;
	}
	public void setLength1_wd(String length1Wd) {
		length1_wd = length1Wd;
	}
	public String getLength2_wd() {
		return length2_wd;
	}
	public void setLength2_wd(String length2Wd) {
		length2_wd = length2Wd;
	}
	public String getLength3_wd() {
		return length3_wd;
	}
	public void setLength3_wd(String length3Wd) {
		length3_wd = length3Wd;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String dateTime) {
		date_time = dateTime;
	}
	public String getShebeibianhao() {
		return shebeibianhao;
	}
	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}
	public String getBaocunshijian() {
		return baocunshijian;
	}
	public void setBaocunshijian(String baocunshijian) {
		this.baocunshijian = baocunshijian;
	}
	public String getKehuduanbianhao() {
		return kehuduanbianhao;
	}
	public void setKehuduanbianhao(String kehuduanbianhao) {
		this.kehuduanbianhao = kehuduanbianhao;
	}
	public String getBiaoshi() {
		return biaoshi;
	}
	public void setBiaoshi(String biaoshi) {
		this.biaoshi = biaoshi;
	}
	public String getCaijishijian() {
		return caijishijian;
	}
	public void setCaijishijian(String caijishijian) {
		this.caijishijian = caijishijian;
	}
}
