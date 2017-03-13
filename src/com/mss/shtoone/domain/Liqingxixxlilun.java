package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Liqingxixxlilun implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6331240127507572263L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int llid;    
	private String llysb;	
	private String llg1;	
	private String llg2;		
	private String llg3;
	private String llg4;
	private String llg5;
	private String llg6;
	private String llg7;
	private String llf1;
	private String llf2;
	private String lllq;	
	private String lltjj;
	private String llshebeibianhao;
	private String lilunname;
	private String llbuwei;	
	private String llshijian;	
	private String llmoren;	
	private String llbeizhu;

	public int getLlid() {
		return llid;
	}

	public void setLlid(int llid) {
		this.llid = llid;
	}

	public String getLlysb() {
		return llysb;
	}

	public void setLlysb(String llysb) {
		this.llysb = llysb;
	}

	public String getLlg1() {
		return llg1;
	}

	public void setLlg1(String llg1) {
		this.llg1 = llg1;
	}

	public String getLlg2() {
		return llg2;
	}

	public void setLlg2(String llg2) {
		this.llg2 = llg2;
	}

	public String getLlg3() {
		return llg3;
	}

	public void setLlg3(String llg3) {
		this.llg3 = llg3;
	}

	public String getLlg4() {
		return llg4;
	}

	public void setLlg4(String llg4) {
		this.llg4 = llg4;
	}

	public String getLlg5() {
		return llg5;
	}

	public void setLlg5(String llg5) {
		this.llg5 = llg5;
	}

	public String getLlg6() {
		return llg6;
	}

	public void setLlg6(String llg6) {
		this.llg6 = llg6;
	}

	public String getLlg7() {
		return llg7;
	}

	public void setLlg7(String llg7) {
		this.llg7 = llg7;
	}

	public String getLlf1() {
		return llf1;
	}

	public void setLlf1(String llf1) {
		this.llf1 = llf1;
	}

	public String getLlf2() {
		return llf2;
	}

	public void setLlf2(String llf2) {
		this.llf2 = llf2;
	}

	public String getLllq() {
		return lllq;
	}

	public void setLllq(String lllq) {
		this.lllq = lllq;
	}

	public String getLltjj() {
		return lltjj;
	}

	public void setLltjj(String lltjj) {
		this.lltjj = lltjj;
	}


	public String getLilunname() {
		return lilunname;
	}

	public void setLilunname(String lilunname) {
		this.lilunname = lilunname;
	}

	public String getLlshebeibianhao() {
		return llshebeibianhao;
	}

	public void setLlshebeibianhao(String llshebeibianhao) {
		this.llshebeibianhao = llshebeibianhao;
	}

	public String getLlbuwei() {
		return llbuwei;
	}

	public void setLlbuwei(String llbuwei) {
		this.llbuwei = llbuwei;
	}

	public String getLlshijian() {
		return llshijian;
	}

	public void setLlshijian(String llshijian) {
		this.llshijian = llshijian;
	}

	public String getLlmoren() {
		return llmoren;
	}

	public void setLlmoren(String llmoren) {
		this.llmoren = llmoren;
	}

	public String getLlbeizhu() {
		return llbeizhu;
	}

	public void setLlbeizhu(String llbeizhu) {
		this.llbeizhu = llbeizhu;
	}

	
}
