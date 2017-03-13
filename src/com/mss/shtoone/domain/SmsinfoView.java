package com.mss.shtoone.domain;

import java.io.Serializable;

public class SmsinfoView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3404136928995298292L;

	private Integer id;
	
	private Integer smsid;
	
	private Integer biaoduanid;
	
	private Integer xiangmubuid;
	
	private Integer zuoyeduiid;

	private String shijian;
	
	private String shebeibianhao;
	
	private String successphone;
	
	private Integer successcount;
	
	private Integer fasongcount;
	
	private String fasongphone;
	
	private String fasongcontent;
	
	private String fasongstatus;
	
	private String returnmsg;
	
	private String banhezhanminchen;
	
	private String jianchen;
	
	private String owername;
	
	public String getOwername() {
		return owername;
	}

	public void setOwername(String owername) {
		this.owername = owername;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getSuccessphone() {
		return successphone;
	}

	public void setSuccessphone(String successphone) {
		this.successphone = successphone;
	}

	public Integer getSuccesscount() {
		return successcount;
	}

	public void setSuccesscount(Integer successcount) {
		this.successcount = successcount;
	}

	public Integer getFasongcount() {
		return fasongcount;
	}

	public void setFasongcount(Integer fasongcount) {
		this.fasongcount = fasongcount;
	}

	public String getFasongphone() {
		return fasongphone;
	}

	public void setFasongphone(String fasongphone) {
		this.fasongphone = fasongphone;
	}

	public String getFasongcontent() {
		return fasongcontent;
	}

	public void setFasongcontent(String fasongcontent) {
		this.fasongcontent = fasongcontent;
	}

	public String getFasongstatus() {
		return fasongstatus;
	}

	public void setFasongstatus(String fasongstatus) {
		this.fasongstatus = fasongstatus;
	}

	public String getReturnmsg() {
		return returnmsg;
	}

	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSmsid() {
		return smsid;
	}

	public void setSmsid(Integer smsid) {
		this.smsid = smsid;
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

	public Integer getZuoyeduiid() {
		return zuoyeduiid;
	}

	public void setZuoyeduiid(Integer zuoyeduiid) {
		this.zuoyeduiid = zuoyeduiid;
	}

}
