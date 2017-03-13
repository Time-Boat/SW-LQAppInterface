package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class XCData1 implements Serializable {


	/**
	 * 摊铺机数据
	 */
	private static final long serialVersionUID = 513214383098494393L;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    

	private String collectTime;	

	private int gprsDeviceId;	

	private Double spreadTemp;		

	private Double airTemp;
	
	private Double windSpeed;
	
	private Double airShiDu;
	
	private String tanPuJiWeiZhi;
	
	private String tanPuSpeed;
	
	private String biaoDuanId;
	
	private String nianYaTemp;
	

	public XCData1(int id, String collectTime, int gprsDeviceId,
			Double spreadTemp, Double airTemp, Double windSpeed,
			Double airShiDu, String tanPuJiWeiZhi, String tanPuSpeed,
			String biaoDuanId, String nianYaTemp) {
		super();
		this.id = id;
		this.collectTime = collectTime;
		this.gprsDeviceId = gprsDeviceId;
		this.spreadTemp = spreadTemp;
		this.airTemp = airTemp;
		this.windSpeed = windSpeed;
		this.airShiDu = airShiDu;
		this.tanPuJiWeiZhi = tanPuJiWeiZhi;
		this.tanPuSpeed = tanPuSpeed;
		this.biaoDuanId = biaoDuanId;
		this.nianYaTemp = nianYaTemp;
	}

	public String getTanPuJiWeiZhi() {
		return tanPuJiWeiZhi;
	}

	public void setTanPuJiWeiZhi(String tanPuJiWeiZhi) {
		this.tanPuJiWeiZhi = tanPuJiWeiZhi;
	}

	public String getTanPuSpeed() {
		return tanPuSpeed;
	}

	public void setTanPuSpeed(String tanPuSpeed) {
		this.tanPuSpeed = tanPuSpeed;
	}

	public String getNianYaTemp() {
		return nianYaTemp;
	}

	public void setNianYaTemp(String nianYaTemp) {
		this.nianYaTemp = nianYaTemp;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	public int getGprsDeviceId() {
		return gprsDeviceId;
	}

	public void setGprsDeviceId(int gprsDeviceId) {
		this.gprsDeviceId = gprsDeviceId;
	}

	public Double getSpreadTemp() {
		return spreadTemp;
	}

	public void setSpreadTemp(Double spreadTemp) {
		this.spreadTemp = spreadTemp;
	}

	public Double getAirTemp() {
		return airTemp;
	}

	public void setAirTemp(Double airTemp) {
		this.airTemp = airTemp;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Double getAirShiDu() {
		return airShiDu;
	}

	public void setAirShiDu(Double airShiDu) {
		this.airShiDu = airShiDu;
	}

	public String getBiaoDuanId() {
		return biaoDuanId;
	}

	public void setBiaoDuanId(String biaoDuanId) {
		this.biaoDuanId = biaoDuanId;
	}

	public XCData1() {
		super();
	}

	public XCData1(int id, String collectTime, int gprsDeviceId,
			Double spreadTemp, Double airTemp, Double windSpeed,
			Double airShiDu, String biaoDuanId) {
		super();
		this.id = id;
		this.collectTime = collectTime;
		this.gprsDeviceId = gprsDeviceId;
		this.spreadTemp = spreadTemp;
		this.airTemp = airTemp;
		this.windSpeed = windSpeed;
		this.airShiDu = airShiDu;
		this.biaoDuanId = biaoDuanId;
	}
	
	
	
}
