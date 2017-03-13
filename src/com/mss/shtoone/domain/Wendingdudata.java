package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wendingdudata implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1209435435870476339L;

	@Id
	private Integer wddid;
	
	private String nu;	

	private String f_maxpressure;	

	private String f_maxdisplacement;		
	
	private String f_98displacement;	
	
	private String date_time;	
	
	private String shebeibianhao;
	
	private String baocunshijian;		
	private String kehuduanbianhao;		
	private String biaoshi;		
	private String caijishijian;
	public Integer getWddid() {
		return wddid;
	}
	public void setWddid(Integer wddid) {
		this.wddid = wddid;
	}
	public String getNu() {
		return nu;
	}
	public void setNu(String nu) {
		this.nu = nu;
	}
	public String getF_maxpressure() {
		return f_maxpressure;
	}
	public void setF_maxpressure(String fMaxpressure) {
		f_maxpressure = fMaxpressure;
	}
	public String getF_maxdisplacement() {
		return f_maxdisplacement;
	}
	public void setF_maxdisplacement(String fMaxdisplacement) {
		f_maxdisplacement = fMaxdisplacement;
	}
	public String getF_98displacement() {
		return f_98displacement;
	}
	public void setF_98displacement(String f_98displacement) {
		this.f_98displacement = f_98displacement;
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
