package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class XCData3 implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 9211604679233771763L;


	/**
	 * 摊铺机速度
	 */



	private int gpsid;
    

	private String beiwei;	

	private String donjin;
	
	private String sudu;
	
	private String gaodu;
	
	private String shijian;
	
	private String gpsno;
	
	private String station;

	public XCData3(int gpsid, String beiwei, String donjin, String sudu,
			String gaodu, String shijian, String gpsno, String station) {
		super();
		this.gpsid = gpsid;
		this.beiwei = beiwei;
		this.donjin = donjin;
		this.sudu = sudu;
		this.gaodu = gaodu;
		this.shijian = shijian;
		this.gpsno = gpsno;
		this.station = station;
	}

	public XCData3(String beiwei, String donjin, String sudu, String gaodu,
			String shijian, String gpsno, String station) {
		super();
		this.beiwei = beiwei;
		this.donjin = donjin;
		this.sudu = sudu;
		this.gaodu = gaodu;
		this.shijian = shijian;
		this.gpsno = gpsno;
		this.station = station;
	}

	public XCData3() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getGpsid() {
		return gpsid;
	}

	public void setGpsid(int gpsid) {
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
	
	
	
}