package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 温度采集数据表
 */
@Entity(name="mtemperature")
public class Mtemperature implements Serializable {

	private static final long serialVersionUID = 7571970026686421754L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/**
	 * 采集时间
	 */
	@Column(length = 50)
	private String shijian;
	
	/**
	 * 温度
	 */
	@Column
	private float wendu;

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



	public float getWendu() {
		return wendu;
	}

	public void setWendu(float wendu) {
		this.wendu = wendu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
