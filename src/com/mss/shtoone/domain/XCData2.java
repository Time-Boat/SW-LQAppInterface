package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class XCData2 implements Serializable {

	/**
	 * 碾压温度数据
	 */

	private static final long serialVersionUID = 1399415205999530983L;

	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
	private String collectTime;	

	private int gprsDeviceId;	

	private Double grindTemp;		

	private String biaoDuanId;

	public XCData2(int id, String collectTime, int gprsDeviceId,
			Double grindTemp, String biaoDuanId) {
		super();
		this.id = id;
		this.collectTime = collectTime;
		this.gprsDeviceId = gprsDeviceId;
		this.grindTemp = grindTemp;
		this.biaoDuanId = biaoDuanId;
	}

	public XCData2(String collectTime, int gprsDeviceId, Double grindTemp,
			String biaoDuanId) {
		super();
		this.collectTime = collectTime;
		this.gprsDeviceId = gprsDeviceId;
		this.grindTemp = grindTemp;
		this.biaoDuanId = biaoDuanId;
	}

	public XCData2() {
		super();
		// TODO Auto-generated constructor stub
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

	public Double getGrindTemp() {
		return grindTemp;
	}

	public void setGrindTemp(Double grindTemp) {
		this.grindTemp = grindTemp;
	}

	public String getBiaoDuanId() {
		return biaoDuanId;
	}

	public void setBiaoDuanId(String biaoDuanId) {
		this.biaoDuanId = biaoDuanId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
}
