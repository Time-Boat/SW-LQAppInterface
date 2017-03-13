package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="cinstru")
public class Cinstru implements Serializable {
	/**
	 * 设备配置表
	 */
	private static final long serialVersionUID = 5288161727199332878L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
	/**
	 * 合同号
	 */
	@Column(length = 50)
	private String hetongid;	

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
	
	public String getHetongid() {
		return hetongid;
	}

	public void setHetongid(String hetongid) {
		this.hetongid = hetongid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
