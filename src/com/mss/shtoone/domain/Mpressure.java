package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 压力机采集数据表
 */
@Entity(name="mpressure")
public class Mpressure implements Serializable {

	private static final long serialVersionUID = -6303784098294944236L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/**
	 * 采集时间
	 */
	@Column(length = 50)
	private String shijian;
	
	/**
	 * 压力值
	 */
	@Column
	private float yali;

	/**
	 * 设备号
	 */
	@Column(length = 50)
	private String shebeiid;


	public String getShebeiid() {
		return shebeiid;
	}

	public void setShebeiid(String shebeiid) {
		this.shebeiid = shebeiid;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public float getYali() {
		return yali;
	}

	public void setYali(float yali) {
		this.yali = yali;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
