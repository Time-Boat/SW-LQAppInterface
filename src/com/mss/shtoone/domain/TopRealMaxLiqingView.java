package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopRealMaxLiqingView implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5959679575077726551L;

	@Id    
	private Integer bianhao;
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
	private String clwd;
	//开机提醒
	private String smsNOOFFbaojin;//是否开启开关机报警
	private String smsNOOFFPhone;//开关机提醒手机号
	private String smsNOOFFIntervalTime;//开关机提醒间隔时间(分钟)
	private String smsNOOFFDateTime;//开关机提醒时间（最新出料时间）
	private String smsNOOFFSendSign;//已发送短信标识 NO:开机提醒 OFF:关机提醒	
	public String getSmsNOOFFbaojin() {
		return smsNOOFFbaojin;
	}

	public void setSmsNOOFFbaojin(String smsNOOFFbaojin) {
		this.smsNOOFFbaojin = smsNOOFFbaojin;
	}

	public String getSmsNOOFFPhone() {
		return smsNOOFFPhone;
	}

	public void setSmsNOOFFPhone(String smsNOOFFPhone) {
		this.smsNOOFFPhone = smsNOOFFPhone;
	}

	public String getSmsNOOFFIntervalTime() {
		return smsNOOFFIntervalTime;
	}

	public void setSmsNOOFFIntervalTime(String smsNOOFFIntervalTime) {
		this.smsNOOFFIntervalTime = smsNOOFFIntervalTime;
	}

	public String getSmsNOOFFDateTime() {
		return smsNOOFFDateTime;
	}

	public void setSmsNOOFFDateTime(String smsNOOFFDateTime) {
		this.smsNOOFFDateTime = smsNOOFFDateTime;
	}

	public String getSmsNOOFFSendSign() {
		return smsNOOFFSendSign;
	}

	public void setSmsNOOFFSendSign(String smsNOOFFSendSign) {
		this.smsNOOFFSendSign = smsNOOFFSendSign;
	}

	public String getClwd() {
		return clwd;
	}

	public void setClwd(String clwd) {
		this.clwd = clwd;
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


	public Integer getBianhao() {
		return bianhao;
	}

	public void setBianhao(Integer bianhao) {
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
	
	
}
