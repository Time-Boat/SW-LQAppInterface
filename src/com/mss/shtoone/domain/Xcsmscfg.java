package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Xcsmscfg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1818182689168368111L;

	@Id
	private String shebeibianhao;
	
	private String tmplow;	

	private String tmphigh;	

	private String speedlow;		
	
	private String speedhigh;	
	
	private String smsbaojin;
	
	private String numberlow;
	
	private String numbermid;
	
	private String numberhigh;
	
	private String tmplow1;	

	private String tmphigh1;	

	private String speedlow1;		
	
	private String speedhigh1;	
	
	private String tmplow2;	

	private String tmphigh2;	

	private String speedlow2;		
	
	private String speedhigh2;	

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	public String getTmplow() {
		return tmplow;
	}

	public void setTmplow(String tmplow) {
		this.tmplow = tmplow;
	}

	public String getTmphigh() {
		return tmphigh;
	}

	public void setTmphigh(String tmphigh) {
		this.tmphigh = tmphigh;
	}

	public String getSpeedlow() {
		return speedlow;
	}

	public void setSpeedlow(String speedlow) {
		this.speedlow = speedlow;
	}

	public String getSpeedhigh() {
		return speedhigh;
	}

	public void setSpeedhigh(String speedhigh) {
		this.speedhigh = speedhigh;
	}

	public String getSmsbaojin() {
		return smsbaojin;
	}

	public void setSmsbaojin(String smsbaojin) {
		this.smsbaojin = smsbaojin;
	}

	public String getNumberlow() {
		return numberlow;
	}

	public void setNumberlow(String numberlow) {
		this.numberlow = numberlow;
	}

	public String getNumbermid() {
		return numbermid;
	}

	public void setNumbermid(String numbermid) {
		this.numbermid = numbermid;
	}

	public String getNumberhigh() {
		return numberhigh;
	}

	public void setNumberhigh(String numberhigh) {
		this.numberhigh = numberhigh;
	}

	public String getTmplow1() {
		return tmplow1;
	}

	public void setTmplow1(String tmplow1) {
		this.tmplow1 = tmplow1;
	}

	public String getTmphigh1() {
		return tmphigh1;
	}

	public void setTmphigh1(String tmphigh1) {
		this.tmphigh1 = tmphigh1;
	}

	public String getSpeedlow1() {
		return speedlow1;
	}

	public void setSpeedlow1(String speedlow1) {
		this.speedlow1 = speedlow1;
	}

	public String getSpeedhigh1() {
		return speedhigh1;
	}

	public void setSpeedhigh1(String speedhigh1) {
		this.speedhigh1 = speedhigh1;
	}

	public String getTmplow2() {
		return tmplow2;
	}

	public void setTmplow2(String tmplow2) {
		this.tmplow2 = tmplow2;
	}

	public String getTmphigh2() {
		return tmphigh2;
	}

	public void setTmphigh2(String tmphigh2) {
		this.tmphigh2 = tmphigh2;
	}

	public String getSpeedlow2() {
		return speedlow2;
	}

	public void setSpeedlow2(String speedlow2) {
		this.speedlow2 = speedlow2;
	}

	public String getSpeedhigh2() {
		return speedhigh2;
	}

	public void setSpeedhigh2(String speedhigh2) {
		this.speedhigh2 = speedhigh2;
	}	
	
}
