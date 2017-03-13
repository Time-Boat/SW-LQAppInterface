package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Liqingsclsheji implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shejiid;
	private String baocunshijian;
	private String peifang;
	private String shebeibianhao;
	private String shijichanliang;
	public int getShejiid() {
		return shejiid;
	}
	public void setShejiid(int shejiid) {
		this.shejiid = shejiid;
	}
	public String getBaocunshijian() {
		return baocunshijian;
	}
	public void setBaocunshijian(String baocunshijian) {
		this.baocunshijian = baocunshijian;
	}
	public String getPeifang() {
		return peifang;
	}
	public void setPeifang(String peifang) {
		this.peifang = peifang;
	}
	public String getShebeibianhao() {
		return shebeibianhao;
	}
	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}
	public String getShijichanliang() {
		return shijichanliang;
	}
	public void setShijichanliang(String shijichanliang) {
		this.shijichanliang = shijichanliang;
	}
}
