package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Liqingxixxjieguo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8109534041418581400L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jieguoid;
	
	private int lqbianhao;

	private String jieguoshijian;	

	private String jbsj;	
	
	private String lqwd;
	
	private String glwd;
	
	private String sjysb;
	
	private String sjg1;
	
	private String sjg2;
	
	private String sjg3;	

	private String sjg4;
	
	private String sjg5;
	
	private String sjg6;
	
	private String sjg7;	
	
	private String sjf1;	
	
	private String sjf2;	
	
	private String sjlq;	
	
	private String sjtjj;
	
	private String beiy1;	
	
	private String beiy2;	
	
	private String beiy3;	
	
	private Integer leiji;
	
	private String chulijieguo;	

	private String leixing;
	private String filepath;
	private String wentiyuanyin;
	private String chulifangshi;
	private String jianliren;
	private String shenpidate;
	private String yezhuyijian;
	private String yezhuren;
	private String confirmdate;
	private String beizhu;
	public String getWentiyuanyin() {
		return wentiyuanyin;
	}

	public void setWentiyuanyin(String wentiyuanyin) {
		this.wentiyuanyin = wentiyuanyin;
	}

	public String getChulifangshi() {
		return chulifangshi;
	}

	public void setChulifangshi(String chulifangshi) {
		this.chulifangshi = chulifangshi;
	}

	public String getJianliren() {
		return jianliren;
	}

	public void setJianliren(String jianliren) {
		this.jianliren = jianliren;
	}

	public String getShenpidate() {
		return shenpidate;
	}

	public void setShenpidate(String shenpidate) {
		this.shenpidate = shenpidate;
	}

	public String getYezhuyijian() {
		return yezhuyijian;
	}

	public void setYezhuyijian(String yezhuyijian) {
		this.yezhuyijian = yezhuyijian;
	}

	public String getYezhuren() {
		return yezhuren;
	}

	public void setYezhuren(String yezhuren) {
		this.yezhuren = yezhuren;
	}

	public String getConfirmdate() {
		return confirmdate;
	}

	public void setConfirmdate(String confirmdate) {
		this.confirmdate = confirmdate;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getJbsj() {
		return jbsj;
	}

	public void setJbsj(String jbsj) {
		this.jbsj = jbsj;
	}

	public String getLqwd() {
		return lqwd;
	}

	public void setLqwd(String lqwd) {
		this.lqwd = lqwd;
	}

	public String getGlwd() {
		return glwd;
	}

	public void setGlwd(String glwd) {
		this.glwd = glwd;
	}
	
	public String getSjysb() {
		return sjysb;
	}

	public void setSjysb(String sjysb) {
		this.sjysb = sjysb;
	}

	public String getSjg1() {
		return sjg1;
	}

	public void setSjg1(String sjg1) {
		this.sjg1 = sjg1;
	}

	public String getSjg2() {
		return sjg2;
	}

	public void setSjg2(String sjg2) {
		this.sjg2 = sjg2;
	}

	public String getSjg3() {
		return sjg3;
	}

	public void setSjg3(String sjg3) {
		this.sjg3 = sjg3;
	}

	public String getSjg4() {
		return sjg4;
	}

	public void setSjg4(String sjg4) {
		this.sjg4 = sjg4;
	}

	public String getSjg5() {
		return sjg5;
	}

	public void setSjg5(String sjg5) {
		this.sjg5 = sjg5;
	}

	public String getSjg6() {
		return sjg6;
	}

	public void setSjg6(String sjg6) {
		this.sjg6 = sjg6;
	}

	public String getSjg7() {
		return sjg7;
	}

	public void setSjg7(String sjg7) {
		this.sjg7 = sjg7;
	}

	public String getSjf1() {
		return sjf1;
	}

	public void setSjf1(String sjf1) {
		this.sjf1 = sjf1;
	}

	public String getSjf2() {
		return sjf2;
	}

	public void setSjf2(String sjf2) {
		this.sjf2 = sjf2;
	}

	public String getSjlq() {
		return sjlq;
	}

	public void setSjlq(String sjlq) {
		this.sjlq = sjlq;
	}

	public String getSjtjj() {
		return sjtjj;
	}

	public void setSjtjj(String sjtjj) {
		this.sjtjj = sjtjj;
	}

	public int getJieguoid() {
		return jieguoid;
	}

	public void setJieguoid(int jieguoid) {
		this.jieguoid = jieguoid;
	}

	public int getLqbianhao() {
		return lqbianhao;
	}

	public void setLqbianhao(int lqbianhao) {
		this.lqbianhao = lqbianhao;
	}

	public String getJieguoshijian() {
		return jieguoshijian;
	}

	public void setJieguoshijian(String jieguoshijian) {
		this.jieguoshijian = jieguoshijian;
	}

	public Integer getLeiji() {
		return leiji;
	}

	public void setLeiji(Integer leiji) {
		this.leiji = leiji;
	}

	public String getChulijieguo() {
		return chulijieguo;
	}

	public void setChulijieguo(String chulijieguo) {
		this.chulijieguo = chulijieguo;
	}

	public String getBeiy1() {
		return beiy1;
	}

	public void setBeiy1(String beiy1) {
		this.beiy1 = beiy1;
	}

	public String getBeiy2() {
		return beiy2;
	}

	public void setBeiy2(String beiy2) {
		this.beiy2 = beiy2;
	}

	public String getBeiy3() {
		return beiy3;
	}

	public void setBeiy3(String beiy3) {
		this.beiy3 = beiy3;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
