package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gpsdata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1356306920202123406L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gpsid;
	private String beiwei;	
	private String donjin;	
	private String sudu;		
	private String gaodu;	
	private String shijian;	
	private String gpsno;
	private String station;		
	private String dinweishijian;		
	private String fanxiang;		
	private String zhuantai;		
	private String licheng;		

	public Integer getGpsid() {
		return gpsid;
	}

	public void setGpsid(Integer gpsid) {
		this.gpsid = gpsid;
	}

	public String getBeiwei() {
		return beiwei;
	}

	public void setBeiwei(String beiwei) {
		this.beiwei = beiwei;
	}

	public String getDonjin() {
		return donjin;
	}

	public void setDonjin(String donjin) {
		this.donjin = donjin;
	}

	public String getSudu() {
		return sudu;
	}

	public void setSudu(String sudu) {
		this.sudu = sudu;
	}

	public String getGaodu() {
		return gaodu;
	}

	public void setGaodu(String gaodu) {
		this.gaodu = gaodu;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getGpsno() {
		return gpsno;
	}

	public void setGpsno(String gpsno) {
		this.gpsno = gpsno;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getDinweishijian() {
		return dinweishijian;
	}

	public void setDinweishijian(String dinweishijian) {
		this.dinweishijian = dinweishijian;
	}

	public String getFanxiang() {
		return fanxiang;
	}

	public void setFanxiang(String fanxiang) {
		this.fanxiang = fanxiang;
	}

	public String getZhuantai() {
		return zhuantai;
	}

	public void setZhuantai(String zhuantai) {
		this.zhuantai = zhuantai;
	}

	public String getLicheng() {
		return licheng;
	}

	public void setLicheng(String licheng) {
		this.licheng = licheng;
	}
}
