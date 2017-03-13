package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LiqingphbView implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5739984822874526597L;

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
	
	//用于界面判断是否超标
	private String cbsjysb;
	private String cbsjg1;
	private String cbsjg2;
	private String cbsjg3;
	private String cbsjg4; 
	private String cbsjg5; 
    private String cbsjg6;
    private String cbsjg7;
    private String cbsjf1;
    private String cbsjf2;
    private String cbsjtjj;
    private String cbsjlq;
    private String cbglwd;
    private String cblqwd;
    private String cbbeiy1;
    


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
	
	
}
