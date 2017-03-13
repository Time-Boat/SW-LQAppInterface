package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopRealGpsdataView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2899639591103793941L;

	@Id
	private Integer gpsid;
	
	private String beiwei;	

	private String donjin;	

	private String sudu;		
	private String gaodu;		
	private String shijian;		
	private String gpsno;		
	private String station;		

	private int id;	
	private Integer biaoduanid;
	private Integer xiangmubuid;
	private Integer zuoyeduiid;
	private String smsbaojin;
	private String smssettype;
	private String smstype;
	private String sendtype;
	private String panshu;
	private String ambegin;
	private String amend;
	private String pmbegin;
	private String pmend;
	private String banhezhanminchen;
	private String jianchen;
	private String shebeileixin;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getBiaoduanid() {
		return biaoduanid;
	}

	public void setBiaoduanid(Integer biaoduanid) {
		this.biaoduanid = biaoduanid;
	}

	public Integer getXiangmubuid() {
		return xiangmubuid;
	}

	public void setXiangmubuid(Integer xiangmubuid) {
		this.xiangmubuid = xiangmubuid;
	}

	public Integer getZuoyeduiid() {
		return zuoyeduiid;
	}

	public void setZuoyeduiid(Integer zuoyeduiid) {
		this.zuoyeduiid = zuoyeduiid;
	}

	public String getSmsbaojin() {
		return smsbaojin;
	}

	public void setSmsbaojin(String smsbaojin) {
		this.smsbaojin = smsbaojin;
	}

	public String getSmssettype() {
		return smssettype;
	}

	public void setSmssettype(String smssettype) {
		this.smssettype = smssettype;
	}

	public String getSmstype() {
		return smstype;
	}

	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}

	public String getSendtype() {
		return sendtype;
	}

	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}

	public String getPanshu() {
		return panshu;
	}

	public void setPanshu(String panshu) {
		this.panshu = panshu;
	}

	public String getAmbegin() {
		return ambegin;
	}

	public void setAmbegin(String ambegin) {
		this.ambegin = ambegin;
	}

	public String getAmend() {
		return amend;
	}

	public void setAmend(String amend) {
		this.amend = amend;
	}

	public String getPmbegin() {
		return pmbegin;
	}

	public void setPmbegin(String pmbegin) {
		this.pmbegin = pmbegin;
	}

	public String getPmend() {
		return pmend;
	}

	public void setPmend(String pmend) {
		this.pmend = pmend;
	}

	public String getBanhezhanminchen() {
		return banhezhanminchen;
	}

	public void setBanhezhanminchen(String banhezhanminchen) {
		this.banhezhanminchen = banhezhanminchen;
	}

	public String getJianchen() {
		return jianchen;
	}

	public void setJianchen(String jianchen) {
		this.jianchen = jianchen;
	}

	public String getShebeileixin() {
		return shebeileixin;
	}

	public void setShebeileixin(String shebeileixin) {
		this.shebeileixin = shebeileixin;
	}

	public Integer getGpsid() {
		return gpsid;
	}

	public void setGpsid(Integer gpsid) {
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
