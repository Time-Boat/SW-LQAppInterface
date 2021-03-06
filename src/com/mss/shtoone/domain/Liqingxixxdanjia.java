package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Liqingxixxdanjia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409045376967768777L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int djid;    

	private String djg1;	

	private String djg2;		

	private String djg3;
	
	private String djg4;
	
	private String djg5;
	
	private String djg6;
	
	private String djg7;
	
	private String djf1;
	
	private String djf2;
	
	private String djlq;	

	private String djtjj;
	
	private String djshebeibianhao;
	
	private String djname;	
	
	private String djshijian;	
	
	private String djmoren;	
	
	private String djbeizhu;

	public int getDjid() {
		return djid;
	}

	public void setDjid(int djid) {
		this.djid = djid;
	}

	public String getDjg1() {
		return djg1;
	}

	public void setDjg1(String djg1) {
		this.djg1 = djg1;
	}

	public String getDjg2() {
		return djg2;
	}

	public void setDjg2(String djg2) {
		this.djg2 = djg2;
	}

	public String getDjg3() {
		return djg3;
	}

	public void setDjg3(String djg3) {
		this.djg3 = djg3;
	}

	public String getDjg4() {
		return djg4;
	}

	public void setDjg4(String djg4) {
		this.djg4 = djg4;
	}

	public String getDjg5() {
		return djg5;
	}

	public void setDjg5(String djg5) {
		this.djg5 = djg5;
	}

	public String getDjg6() {
		return djg6;
	}

	public void setDjg6(String djg6) {
		this.djg6 = djg6;
	}

	public String getDjg7() {
		return djg7;
	}

	public void setDjg7(String djg7) {
		this.djg7 = djg7;
	}

	public String getDjf1() {
		return djf1;
	}

	public void setDjf1(String djf1) {
		this.djf1 = djf1;
	}

	public String getDjf2() {
		return djf2;
	}

	public void setDjf2(String djf2) {
		this.djf2 = djf2;
	}

	public String getDjlq() {
		return djlq;
	}

	public void setDjlq(String djlq) {
		this.djlq = djlq;
	}

	public String getDjtjj() {
		return djtjj;
	}

	public void setDjtjj(String djtjj) {
		this.djtjj = djtjj;
	}

	public String getDjshebeibianhao() {
		return djshebeibianhao;
	}

	public void setDjshebeibianhao(String djshebeibianhao) {
		this.djshebeibianhao = djshebeibianhao;
	}

	public String getDjname() {
		return djname;
	}

	public void setDjname(String djname) {
		this.djname = djname;
	}

	public String getDjshijian() {
		return djshijian;
	}

	public void setDjshijian(String djshijian) {
		this.djshijian = djshijian;
	}

	public String getDjmoren() {
		return djmoren;
	}

	public void setDjmoren(String djmoren) {
		this.djmoren = djmoren;
	}

	public String getDjbeizhu() {
		return djbeizhu;
	}

	public void setDjbeizhu(String djbeizhu) {
		this.djbeizhu = djbeizhu;
	}
	
}
