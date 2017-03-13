package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diuqishuju implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8938175148034285046L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String diuqishuju;
	private String shijian;
	private String shebeibianhao;
	private String kehuduanbianhao;
	private Integer shuliang;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiuqishuju() {
		return diuqishuju;
	}
	public void setDiuqishuju(String diuqishuju) {
		this.diuqishuju = diuqishuju;
	}
	public String getShijian() {
		return shijian;
	}
	public void setShijian(String shijian) {
		this.shijian = shijian;
	}
	public String getShebeibianhao() {
		return shebeibianhao;
	}
	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}
	public String getKehuduanbianhao() {
		return kehuduanbianhao;
	}
	public void setKehuduanbianhao(String kehuduanbianhao) {
		this.kehuduanbianhao = kehuduanbianhao;
	}
	public Integer getShuliang() {
		return shuliang;
	}
	public void setShuliang(Integer shuliang) {
		this.shuliang = shuliang;
	}

}
