package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Smsinfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * 发送时间
	 */
	private String shijian;
	
	private String shebeibianhao;
	
	private String successphone;
	
	private Integer successcount;
	
	private Integer fasongcount;
	
	private String fasongphone;
	
	private String fasongcontent;
	
	private String fasongstatus;
	
	private String returnmsg;
	
	private String swbianhao;
	
	private String lqbianhao;

	private String yonghumingchen;
	
	private String chuliaoshijian;
	
	public String getYonghumingchen() {
		return yonghumingchen;
	}

	public void setYonghumingchen(String yonghumingchen) {
		this.yonghumingchen = yonghumingchen;
	}

	public String getChuliaoshijian() {
		return chuliaoshijian;
	}

	public void setChuliaoshijian(String chuliaoshijian) {
		this.chuliaoshijian = chuliaoshijian;
	}

	public String getSwbianhao() {
		return swbianhao;
	}

	public void setSwbianhao(String swbianhao) {
		this.swbianhao = swbianhao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getLqbianhao() {
		return lqbianhao;
	}

	public void setLqbianhao(String lqbianhao) {
		this.lqbianhao = lqbianhao;
	}

}
