package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LiqingmanualphbView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5111793913595247695L;

	@Id
	private int bianhao;
	private String shijian;	
	private String jbsj;	
	private String yonghu;		
	private String peifan;
	private String lqwd;
	private String glwd;
	private String changliang;
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
	private String shebeibianhao;
	private String baocunshijian;
	private String kehuduanbianhao;
	private String biaoshi;
	private String caijishijian;
	private String banhezhanminchen;
	private String jianchen;
	private Integer id;	
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
	private String bh;
	private String clwd;
	private String khmc;	
	private String gcmc;	
	private String sgbw;	
	private String ljsl;
	private String persjg1;
	private String persjg2;
	private String persjg3;	
	private String persjg4;
	private String persjg5;
	private String persjg6;
	private String persjg7;	
	private String persjf1;	
	private String persjf2;	
	private String persjlq;	
	private String persjtjj;
	private String wsjysb;
	private String wsjg1;
	private String wsjg2;
	private String wsjg3;	
	private String wsjg4;
	private String wsjg5;
	private String wsjg6;
	private String wsjg7;	
	private String wsjf1;	
	private String wsjf2;	
	private String wsjlq;	
	private String wsjtjj;
	
	private String perllg1;
	private String perllg2;
	private String perllg3;	
	private String perllg4;
	private String perllg5;
	private String perllg6;
	private String perllg7;	
	private String perllf1;	
	private String perllf2;	
	private String perlllq;	
	private String perlltjj;
	private String perllysb;
	private String manualwsjysb;
	private String manualwsjg1;
	private String manualwsjg2;
	private String manualwsjg3;	
	private String manualwsjg4;
	private String manualwsjg5;
	private String manualwsjg6;
	private String manualwsjg7;	
	private String manualwsjf1;	
	private String manualwsjf2;	
	private String manualwsjlq;	
	private String manualwsjtjj;
	//超标
	private String cbsjysb;
	private String cbsjysb_primary;
	private String cbsjysb_middle;
	private String cbsjysb_high;
	private String cbsjg1;
	private String cbsjg1_primary;
	private String cbsjg1_middle;
	private String cbsjg1_high;
	private String cbsjg2;
	private String cbsjg2_primary;
	private String cbsjg2_middle;
	private String cbsjg2_high;
	private String cbsjg3;
	private String cbsjg3_primary;
	private String cbsjg3_middle;
	private String cbsjg3_high;
	private String cbsjg4;
	private String cbsjg4_primary;
	private String cbsjg4_middle;
	private String cbsjg4_high;
	private String cbsjg5;
	private String cbsjg5_primary;
	private String cbsjg5_middle;
	private String cbsjg5_high;
    private String cbsjg6;
    private String cbsjg6_primary;
	private String cbsjg6_middle;
	private String cbsjg6_high;
    private String cbsjg7;
    private String cbsjg7_primary;
	private String cbsjg7_middle;
	private String cbsjg7_high;
    private String cbsjf1;
    private String cbsjf1_primary;
	private String cbsjf1_middle;
	private String cbsjf1_high;
    private String cbsjf2;
    private String cbsjf2_primary;
	private String cbsjf2_middle;
	private String cbsjf2_high;
    private String cbsjtjj;
    private String cbsjtjj_primary;
	private String cbsjtjj_middle;
	private String cbsjtjj_high;
    private String cbsjlq;
    private String cbsjlq_primary;
	private String cbsjlq_middle;
	private String cbsjlq_high;
    private String cbglwd;
    private String cbglwd_primary;
	private String cbglwd_middle;
	private String cbglwd_high;
    private String cblqwd;
    private String cblqwd_primary;
	private String cblqwd_middle;
	private String cblqwd_high;
    private String cbbeiy1;
    private String cbbeiy1_primary;
	private String cbbeiy1_middle;
	private String cbbeiy1_high;
    
	private String filepath;
	private String leixing;
	private String chulijieguo;
    private String yezhuyijian;
    
    public String getCbsjysb_primary() {
		return cbsjysb_primary;
	}

	public void setCbsjysb_primary(String cbsjysb_primary) {
		this.cbsjysb_primary = cbsjysb_primary;
	}

	public String getCbsjysb_middle() {
		return cbsjysb_middle;
	}

	public void setCbsjysb_middle(String cbsjysb_middle) {
		this.cbsjysb_middle = cbsjysb_middle;
	}

	public String getCbsjysb_high() {
		return cbsjysb_high;
	}

	public void setCbsjysb_high(String cbsjysb_high) {
		this.cbsjysb_high = cbsjysb_high;
	}

	public String getCbsjg1_primary() {
		return cbsjg1_primary;
	}

	public void setCbsjg1_primary(String cbsjg1_primary) {
		this.cbsjg1_primary = cbsjg1_primary;
	}

	public String getCbsjg1_middle() {
		return cbsjg1_middle;
	}

	public void setCbsjg1_middle(String cbsjg1_middle) {
		this.cbsjg1_middle = cbsjg1_middle;
	}

	public String getCbsjg1_high() {
		return cbsjg1_high;
	}

	public void setCbsjg1_high(String cbsjg1_high) {
		this.cbsjg1_high = cbsjg1_high;
	}

	public String getCbsjg2_primary() {
		return cbsjg2_primary;
	}

	public void setCbsjg2_primary(String cbsjg2_primary) {
		this.cbsjg2_primary = cbsjg2_primary;
	}

	public String getCbsjg2_middle() {
		return cbsjg2_middle;
	}

	public void setCbsjg2_middle(String cbsjg2_middle) {
		this.cbsjg2_middle = cbsjg2_middle;
	}

	public String getCbsjg2_high() {
		return cbsjg2_high;
	}

	public void setCbsjg2_high(String cbsjg2_high) {
		this.cbsjg2_high = cbsjg2_high;
	}

	public String getCbsjg3_primary() {
		return cbsjg3_primary;
	}

	public void setCbsjg3_primary(String cbsjg3_primary) {
		this.cbsjg3_primary = cbsjg3_primary;
	}

	public String getCbsjg3_middle() {
		return cbsjg3_middle;
	}

	public void setCbsjg3_middle(String cbsjg3_middle) {
		this.cbsjg3_middle = cbsjg3_middle;
	}

	public String getCbsjg3_high() {
		return cbsjg3_high;
	}

	public void setCbsjg3_high(String cbsjg3_high) {
		this.cbsjg3_high = cbsjg3_high;
	}

	public String getCbsjg4_primary() {
		return cbsjg4_primary;
	}

	public void setCbsjg4_primary(String cbsjg4_primary) {
		this.cbsjg4_primary = cbsjg4_primary;
	}

	public String getCbsjg4_middle() {
		return cbsjg4_middle;
	}

	public void setCbsjg4_middle(String cbsjg4_middle) {
		this.cbsjg4_middle = cbsjg4_middle;
	}

	public String getCbsjg4_high() {
		return cbsjg4_high;
	}

	public void setCbsjg4_high(String cbsjg4_high) {
		this.cbsjg4_high = cbsjg4_high;
	}

	public String getCbsjg5_primary() {
		return cbsjg5_primary;
	}

	public void setCbsjg5_primary(String cbsjg5_primary) {
		this.cbsjg5_primary = cbsjg5_primary;
	}

	public String getCbsjg5_middle() {
		return cbsjg5_middle;
	}

	public void setCbsjg5_middle(String cbsjg5_middle) {
		this.cbsjg5_middle = cbsjg5_middle;
	}

	public String getCbsjg5_high() {
		return cbsjg5_high;
	}

	public void setCbsjg5_high(String cbsjg5_high) {
		this.cbsjg5_high = cbsjg5_high;
	}

	public String getCbsjg6_primary() {
		return cbsjg6_primary;
	}

	public void setCbsjg6_primary(String cbsjg6_primary) {
		this.cbsjg6_primary = cbsjg6_primary;
	}

	public String getCbsjg6_middle() {
		return cbsjg6_middle;
	}

	public void setCbsjg6_middle(String cbsjg6_middle) {
		this.cbsjg6_middle = cbsjg6_middle;
	}

	public String getCbsjg6_high() {
		return cbsjg6_high;
	}

	public void setCbsjg6_high(String cbsjg6_high) {
		this.cbsjg6_high = cbsjg6_high;
	}

	public String getCbsjg7_primary() {
		return cbsjg7_primary;
	}

	public void setCbsjg7_primary(String cbsjg7_primary) {
		this.cbsjg7_primary = cbsjg7_primary;
	}

	public String getCbsjg7_middle() {
		return cbsjg7_middle;
	}

	public void setCbsjg7_middle(String cbsjg7_middle) {
		this.cbsjg7_middle = cbsjg7_middle;
	}

	public String getCbsjg7_high() {
		return cbsjg7_high;
	}

	public void setCbsjg7_high(String cbsjg7_high) {
		this.cbsjg7_high = cbsjg7_high;
	}

	public String getCbsjf1_primary() {
		return cbsjf1_primary;
	}

	public void setCbsjf1_primary(String cbsjf1_primary) {
		this.cbsjf1_primary = cbsjf1_primary;
	}

	public String getCbsjf1_middle() {
		return cbsjf1_middle;
	}

	public void setCbsjf1_middle(String cbsjf1_middle) {
		this.cbsjf1_middle = cbsjf1_middle;
	}

	public String getCbsjf1_high() {
		return cbsjf1_high;
	}

	public void setCbsjf1_high(String cbsjf1_high) {
		this.cbsjf1_high = cbsjf1_high;
	}

	public String getCbsjf2_primary() {
		return cbsjf2_primary;
	}

	public void setCbsjf2_primary(String cbsjf2_primary) {
		this.cbsjf2_primary = cbsjf2_primary;
	}

	public String getCbsjf2_middle() {
		return cbsjf2_middle;
	}

	public void setCbsjf2_middle(String cbsjf2_middle) {
		this.cbsjf2_middle = cbsjf2_middle;
	}

	public String getCbsjf2_high() {
		return cbsjf2_high;
	}

	public void setCbsjf2_high(String cbsjf2_high) {
		this.cbsjf2_high = cbsjf2_high;
	}

	public String getCbsjtjj_primary() {
		return cbsjtjj_primary;
	}

	public void setCbsjtjj_primary(String cbsjtjj_primary) {
		this.cbsjtjj_primary = cbsjtjj_primary;
	}

	public String getCbsjtjj_middle() {
		return cbsjtjj_middle;
	}

	public void setCbsjtjj_middle(String cbsjtjj_middle) {
		this.cbsjtjj_middle = cbsjtjj_middle;
	}

	public String getCbsjtjj_high() {
		return cbsjtjj_high;
	}

	public void setCbsjtjj_high(String cbsjtjj_high) {
		this.cbsjtjj_high = cbsjtjj_high;
	}

	public String getCbsjlq_primary() {
		return cbsjlq_primary;
	}

	public void setCbsjlq_primary(String cbsjlq_primary) {
		this.cbsjlq_primary = cbsjlq_primary;
	}

	public String getCbsjlq_middle() {
		return cbsjlq_middle;
	}

	public void setCbsjlq_middle(String cbsjlq_middle) {
		this.cbsjlq_middle = cbsjlq_middle;
	}

	public String getCbsjlq_high() {
		return cbsjlq_high;
	}

	public void setCbsjlq_high(String cbsjlq_high) {
		this.cbsjlq_high = cbsjlq_high;
	}

	public String getCbglwd_primary() {
		return cbglwd_primary;
	}

	public void setCbglwd_primary(String cbglwd_primary) {
		this.cbglwd_primary = cbglwd_primary;
	}

	public String getCbglwd_middle() {
		return cbglwd_middle;
	}

	public void setCbglwd_middle(String cbglwd_middle) {
		this.cbglwd_middle = cbglwd_middle;
	}

	public String getCbglwd_high() {
		return cbglwd_high;
	}

	public void setCbglwd_high(String cbglwd_high) {
		this.cbglwd_high = cbglwd_high;
	}

	public String getCblqwd_primary() {
		return cblqwd_primary;
	}

	public void setCblqwd_primary(String cblqwd_primary) {
		this.cblqwd_primary = cblqwd_primary;
	}

	public String getCblqwd_middle() {
		return cblqwd_middle;
	}

	public void setCblqwd_middle(String cblqwd_middle) {
		this.cblqwd_middle = cblqwd_middle;
	}

	public String getCblqwd_high() {
		return cblqwd_high;
	}

	public void setCblqwd_high(String cblqwd_high) {
		this.cblqwd_high = cblqwd_high;
	}

	public String getCbbeiy1_primary() {
		return cbbeiy1_primary;
	}

	public void setCbbeiy1_primary(String cbbeiy1_primary) {
		this.cbbeiy1_primary = cbbeiy1_primary;
	}

	public String getCbbeiy1_middle() {
		return cbbeiy1_middle;
	}

	public void setCbbeiy1_middle(String cbbeiy1_middle) {
		this.cbbeiy1_middle = cbbeiy1_middle;
	}

	public String getCbbeiy1_high() {
		return cbbeiy1_high;
	}

	public void setCbbeiy1_high(String cbbeiy1_high) {
		this.cbbeiy1_high = cbbeiy1_high;
	}

	public String getYezhuyijian() {
		return yezhuyijian;
	}

	public void setYezhuyijian(String yezhuyijian) {
		this.yezhuyijian = yezhuyijian;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	private Integer llid;    
	private String lilunname;//配比名称
	private String llbuwei;//使用部位
	private String llmoren;//是否默认  0:非默认  1：默认
	public String getPerllg1() {
		return perllg1;
	}

	public void setPerllg1(String perllg1) {
		this.perllg1 = perllg1;
	}

	public String getPerllg2() {
		return perllg2;
	}

	public void setPerllg2(String perllg2) {
		this.perllg2 = perllg2;
	}

	public String getPerllg3() {
		return perllg3;
	}

	public void setPerllg3(String perllg3) {
		this.perllg3 = perllg3;
	}

	public String getPerllg4() {
		return perllg4;
	}

	public void setPerllg4(String perllg4) {
		this.perllg4 = perllg4;
	}

	public String getPerllg5() {
		return perllg5;
	}

	public void setPerllg5(String perllg5) {
		this.perllg5 = perllg5;
	}

	public String getPerllg6() {
		return perllg6;
	}

	public void setPerllg6(String perllg6) {
		this.perllg6 = perllg6;
	}

	public String getPerllg7() {
		return perllg7;
	}

	public void setPerllg7(String perllg7) {
		this.perllg7 = perllg7;
	}

	public String getPerllf1() {
		return perllf1;
	}

	public void setPerllf1(String perllf1) {
		this.perllf1 = perllf1;
	}

	public String getPerllf2() {
		return perllf2;
	}

	public void setPerllf2(String perllf2) {
		this.perllf2 = perllf2;
	}

	public String getPerlllq() {
		return perlllq;
	}

	public void setPerlllq(String perlllq) {
		this.perlllq = perlllq;
	}

	public String getPerlltjj() {
		return perlltjj;
	}

	public void setPerlltjj(String perlltjj) {
		this.perlltjj = perlltjj;
	}

	public String getPerllysb() {
		return perllysb;
	}

	public void setPerllysb(String perllysb) {
		this.perllysb = perllysb;
	}

	public String getManualwsjysb() {
		return manualwsjysb;
	}

	public void setManualwsjysb(String manualwsjysb) {
		this.manualwsjysb = manualwsjysb;
	}

	public String getManualwsjg1() {
		return manualwsjg1;
	}

	public void setManualwsjg1(String manualwsjg1) {
		this.manualwsjg1 = manualwsjg1;
	}

	public String getManualwsjg2() {
		return manualwsjg2;
	}

	public void setManualwsjg2(String manualwsjg2) {
		this.manualwsjg2 = manualwsjg2;
	}

	public String getManualwsjg3() {
		return manualwsjg3;
	}

	public void setManualwsjg3(String manualwsjg3) {
		this.manualwsjg3 = manualwsjg3;
	}

	public String getManualwsjg4() {
		return manualwsjg4;
	}

	public void setManualwsjg4(String manualwsjg4) {
		this.manualwsjg4 = manualwsjg4;
	}

	public String getManualwsjg5() {
		return manualwsjg5;
	}

	public void setManualwsjg5(String manualwsjg5) {
		this.manualwsjg5 = manualwsjg5;
	}

	public String getManualwsjg6() {
		return manualwsjg6;
	}

	public void setManualwsjg6(String manualwsjg6) {
		this.manualwsjg6 = manualwsjg6;
	}

	public String getManualwsjg7() {
		return manualwsjg7;
	}

	public void setManualwsjg7(String manualwsjg7) {
		this.manualwsjg7 = manualwsjg7;
	}

	public String getManualwsjf1() {
		return manualwsjf1;
	}

	public void setManualwsjf1(String manualwsjf1) {
		this.manualwsjf1 = manualwsjf1;
	}

	public String getManualwsjf2() {
		return manualwsjf2;
	}

	public void setManualwsjf2(String manualwsjf2) {
		this.manualwsjf2 = manualwsjf2;
	}

	public String getManualwsjlq() {
		return manualwsjlq;
	}

	public void setManualwsjlq(String manualwsjlq) {
		this.manualwsjlq = manualwsjlq;
	}

	public String getManualwsjtjj() {
		return manualwsjtjj;
	}

	public void setManualwsjtjj(String manualwsjtjj) {
		this.manualwsjtjj = manualwsjtjj;
	}

	public Integer getLlid() {
		return llid;
	}

	public void setLlid(Integer llid) {
		this.llid = llid;
	}

	public String getLilunname() {
		return lilunname;
	}

	public void setLilunname(String lilunname) {
		this.lilunname = lilunname;
	}

	public String getLlbuwei() {
		return llbuwei;
	}

	public void setLlbuwei(String llbuwei) {
		this.llbuwei = llbuwei;
	}

	public String getLlmoren() {
		return llmoren;
	}

	public void setLlmoren(String llmoren) {
		this.llmoren = llmoren;
	}

	public String getCbsjysb() {
		return cbsjysb;
	}

	public void setCbsjysb(String cbsjysb) {
		this.cbsjysb = cbsjysb;
	}

	public String getCbsjg1() {
		return cbsjg1;
	}

	public void setCbsjg1(String cbsjg1) {
		this.cbsjg1 = cbsjg1;
	}

	public String getCbsjg2() {
		return cbsjg2;
	}

	public void setCbsjg2(String cbsjg2) {
		this.cbsjg2 = cbsjg2;
	}

	public String getCbsjg3() {
		return cbsjg3;
	}

	public void setCbsjg3(String cbsjg3) {
		this.cbsjg3 = cbsjg3;
	}

	public String getCbsjg4() {
		return cbsjg4;
	}

	public void setCbsjg4(String cbsjg4) {
		this.cbsjg4 = cbsjg4;
	}

	public String getCbsjg5() {
		return cbsjg5;
	}

	public void setCbsjg5(String cbsjg5) {
		this.cbsjg5 = cbsjg5;
	}

	public String getCbsjg6() {
		return cbsjg6;
	}

	public void setCbsjg6(String cbsjg6) {
		this.cbsjg6 = cbsjg6;
	}

	public String getCbsjg7() {
		return cbsjg7;
	}

	public void setCbsjg7(String cbsjg7) {
		this.cbsjg7 = cbsjg7;
	}

	public String getCbsjf1() {
		return cbsjf1;
	}

	public void setCbsjf1(String cbsjf1) {
		this.cbsjf1 = cbsjf1;
	}

	public String getCbsjf2() {
		return cbsjf2;
	}

	public void setCbsjf2(String cbsjf2) {
		this.cbsjf2 = cbsjf2;
	}

	public String getCbsjtjj() {
		return cbsjtjj;
	}

	public void setCbsjtjj(String cbsjtjj) {
		this.cbsjtjj = cbsjtjj;
	}

	public String getCbsjlq() {
		return cbsjlq;
	}

	public void setCbsjlq(String cbsjlq) {
		this.cbsjlq = cbsjlq;
	}

	public String getCbglwd() {
		return cbglwd;
	}

	public void setCbglwd(String cbglwd) {
		this.cbglwd = cbglwd;
	}

	public String getCblqwd() {
		return cblqwd;
	}

	public void setCblqwd(String cblqwd) {
		this.cblqwd = cblqwd;
	}

	public String getCbbeiy1() {
		return cbbeiy1;
	}

	public void setCbbeiy1(String cbbeiy1) {
		this.cbbeiy1 = cbbeiy1;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getJbsj() {
		return jbsj;
	}

	public void setJbsj(String jbsj) {
		this.jbsj = jbsj;
	}

	public String getYonghu() {
		return yonghu;
	}

	public void setYonghu(String yonghu) {
		this.yonghu = yonghu;
	}

	public String getPeifan() {
		return peifan;
	}

	public void setPeifan(String peifan) {
		this.peifan = peifan;
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

	public String getChangliang() {
		return changliang;
	}

	public void setChangliang(String changliang) {
		this.changliang = changliang;
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


	public int getBianhao() {
		return bianhao;
	}

	public void setBianhao(int bianhao) {
		this.bianhao = bianhao;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getPersjg1() {
		return persjg1;
	}

	public void setPersjg1(String persjg1) {
		this.persjg1 = persjg1;
	}

	public String getPersjg2() {
		return persjg2;
	}

	public void setPersjg2(String persjg2) {
		this.persjg2 = persjg2;
	}

	public String getPersjg3() {
		return persjg3;
	}

	public void setPersjg3(String persjg3) {
		this.persjg3 = persjg3;
	}

	public String getPersjg4() {
		return persjg4;
	}

	public void setPersjg4(String persjg4) {
		this.persjg4 = persjg4;
	}

	public String getPersjg5() {
		return persjg5;
	}

	public void setPersjg5(String persjg5) {
		this.persjg5 = persjg5;
	}

	public String getPersjg6() {
		return persjg6;
	}

	public void setPersjg6(String persjg6) {
		this.persjg6 = persjg6;
	}

	public String getPersjg7() {
		return persjg7;
	}

	public void setPersjg7(String persjg7) {
		this.persjg7 = persjg7;
	}

	public String getPersjf1() {
		return persjf1;
	}

	public void setPersjf1(String persjf1) {
		this.persjf1 = persjf1;
	}

	public String getPersjf2() {
		return persjf2;
	}

	public void setPersjf2(String persjf2) {
		this.persjf2 = persjf2;
	}

	public String getPersjlq() {
		return persjlq;
	}

	public void setPersjlq(String persjlq) {
		this.persjlq = persjlq;
	}

	public String getPersjtjj() {
		return persjtjj;
	}

	public void setPersjtjj(String persjtjj) {
		this.persjtjj = persjtjj;
	}

	public String getWsjysb() {
		return wsjysb;
	}

	public void setWsjysb(String wsjysb) {
		this.wsjysb = wsjysb;
	}

	public String getWsjg1() {
		return wsjg1;
	}

	public void setWsjg1(String wsjg1) {
		this.wsjg1 = wsjg1;
	}

	public String getWsjg2() {
		return wsjg2;
	}

	public void setWsjg2(String wsjg2) {
		this.wsjg2 = wsjg2;
	}

	public String getWsjg3() {
		return wsjg3;
	}

	public void setWsjg3(String wsjg3) {
		this.wsjg3 = wsjg3;
	}

	public String getWsjg4() {
		return wsjg4;
	}

	public void setWsjg4(String wsjg4) {
		this.wsjg4 = wsjg4;
	}

	public String getWsjg5() {
		return wsjg5;
	}

	public void setWsjg5(String wsjg5) {
		this.wsjg5 = wsjg5;
	}

	public String getWsjg6() {
		return wsjg6;
	}

	public void setWsjg6(String wsjg6) {
		this.wsjg6 = wsjg6;
	}

	public String getWsjg7() {
		return wsjg7;
	}

	public void setWsjg7(String wsjg7) {
		this.wsjg7 = wsjg7;
	}

	public String getWsjf1() {
		return wsjf1;
	}

	public void setWsjf1(String wsjf1) {
		this.wsjf1 = wsjf1;
	}

	public String getWsjf2() {
		return wsjf2;
	}

	public void setWsjf2(String wsjf2) {
		this.wsjf2 = wsjf2;
	}

	public String getWsjlq() {
		return wsjlq;
	}

	public void setWsjlq(String wsjlq) {
		this.wsjlq = wsjlq;
	}

	public String getWsjtjj() {
		return wsjtjj;
	}

	public void setWsjtjj(String wsjtjj) {
		this.wsjtjj = wsjtjj;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getClwd() {
		return clwd;
	}

	public void setClwd(String clwd) {
		this.clwd = clwd;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getGcmc() {
		return gcmc;
	}

	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}

	public String getSgbw() {
		return sgbw;
	}

	public void setSgbw(String sgbw) {
		this.sgbw = sgbw;
	}

	public String getLjsl() {
		return ljsl;
	}

	public void setLjsl(String ljsl) {
		this.ljsl = ljsl;
	}

	public String getChulijieguo() {
		return chulijieguo;
	}

	public void setChulijieguo(String chulijieguo) {
		this.chulijieguo = chulijieguo;
	}
	
	
}
