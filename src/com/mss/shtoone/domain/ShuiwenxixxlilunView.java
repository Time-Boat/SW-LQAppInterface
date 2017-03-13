package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShuiwenxixxlilunView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2494976708065762945L;

	@Id
	private int llid;    
	private String llpeibiName;//配比名称
	private String llbuwei;//使用部位
	private String llzhuanghao;//桩号
	private String llshigongjihua;//施工计划
	private String llshigongdateS;//施工开始时间
	private String llshigongdateE;//施工结束时间
	private String lllurudate;//录入时间
	private String llmoren;//是否默认  0:非默认  1：默认
	private String llg1;//骨料1	
	private String llg1upper;
	private String llg1floor;
	private String llg2;		
	private String llg2upper;
	private String llg2floor;
	private String llg3;
	private String llg3upper;
	private String llg3floor;
	private String llg4;
	private String llg4upper;
	private String llg4floor;
	private String llg5;
	private String llg5upper;
	private String llg5floor;
	private String llf1;//粉料1
	private String llf1upper;
	private String llf1floor;
	private String llf2;
	private String llf2upper;
	private String llf2floor;
	private String llshui;//水
	private String llshebeibianhao;//设备编号
	private String llbeizhu;
	
	private Integer id;
	private String xiangmubuminchen;
	private String banhezhanminchen;
	private String jianchen;
	private String gongkongleixin;
	private String gprsbianhao;
	private String shebeichanjia;
	private String jiekouleixin;
	private String gongkongruanjian;
	private String shujukuleixin;
	private String beizhu;
	private String shuliang;
	private String shebeileixin;
	private String simhao;
	private String shoujihao;
	private String botelu;
	private String taocan;
	private String smsbaojin;
	private String smssettype;
	private String smstype;
	private String sendtype;
	private String panshu;
	private String ambegin;
	private String amend;
	private String pmbegin;
	private String pmend;
	private Integer biaoduanid;
	private Integer xiangmubuid;
	private Integer zuoyeduiid;

	
	
	public int getLlid() {
		return llid;
	}

	public void setLlid(int llid) {
		this.llid = llid;
	}

	public String getLlpeibiName() {
		return llpeibiName;
	}

	public void setLlpeibiName(String llpeibiName) {
		this.llpeibiName = llpeibiName;
	}

	public String getLlbuwei() {
		return llbuwei;
	}

	public void setLlbuwei(String llbuwei) {
		this.llbuwei = llbuwei;
	}

	public String getLlzhuanghao() {
		return llzhuanghao;
	}

	public void setLlzhuanghao(String llzhuanghao) {
		this.llzhuanghao = llzhuanghao;
	}

	public String getLlshigongjihua() {
		return llshigongjihua;
	}

	public void setLlshigongjihua(String llshigongjihua) {
		this.llshigongjihua = llshigongjihua;
	}

	public String getLlshigongdateS() {
		return llshigongdateS;
	}

	public void setLlshigongdateS(String llshigongdateS) {
		this.llshigongdateS = llshigongdateS;
	}

	public String getLlshigongdateE() {
		return llshigongdateE;
	}

	public void setLlshigongdateE(String llshigongdateE) {
		this.llshigongdateE = llshigongdateE;
	}

	public String getLllurudate() {
		return lllurudate;
	}

	public void setLllurudate(String lllurudate) {
		this.lllurudate = lllurudate;
	}

	public String getLlmoren() {
		return llmoren;
	}

	public void setLlmoren(String llmoren) {
		this.llmoren = llmoren;
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

	public String getLlshui() {
		return llshui;
	}

	public void setLlshui(String llshui) {
		this.llshui = llshui;
	}

	public String getLlshebeibianhao() {
		return llshebeibianhao;
	}

	public void setLlshebeibianhao(String llshebeibianhao) {
		this.llshebeibianhao = llshebeibianhao;
	}

	public String getLlbeizhu() {
		return llbeizhu;
	}

	public void setLlbeizhu(String llbeizhu) {
		this.llbeizhu = llbeizhu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getXiangmubuminchen() {
		return xiangmubuminchen;
	}

	public void setXiangmubuminchen(String xiangmubuminchen) {
		this.xiangmubuminchen = xiangmubuminchen;
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

	public String getGongkongleixin() {
		return gongkongleixin;
	}

	public void setGongkongleixin(String gongkongleixin) {
		this.gongkongleixin = gongkongleixin;
	}

	public String getGprsbianhao() {
		return gprsbianhao;
	}

	public void setGprsbianhao(String gprsbianhao) {
		this.gprsbianhao = gprsbianhao;
	}

	public String getShebeichanjia() {
		return shebeichanjia;
	}

	public void setShebeichanjia(String shebeichanjia) {
		this.shebeichanjia = shebeichanjia;
	}

	public String getJiekouleixin() {
		return jiekouleixin;
	}

	public void setJiekouleixin(String jiekouleixin) {
		this.jiekouleixin = jiekouleixin;
	}

	public String getGongkongruanjian() {
		return gongkongruanjian;
	}

	public void setGongkongruanjian(String gongkongruanjian) {
		this.gongkongruanjian = gongkongruanjian;
	}

	public String getShujukuleixin() {
		return shujukuleixin;
	}

	public void setShujukuleixin(String shujukuleixin) {
		this.shujukuleixin = shujukuleixin;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getShuliang() {
		return shuliang;
	}

	public void setShuliang(String shuliang) {
		this.shuliang = shuliang;
	}

	public String getShebeileixin() {
		return shebeileixin;
	}

	public void setShebeileixin(String shebeileixin) {
		this.shebeileixin = shebeileixin;
	}

	public String getSimhao() {
		return simhao;
	}

	public void setSimhao(String simhao) {
		this.simhao = simhao;
	}

	public String getShoujihao() {
		return shoujihao;
	}

	public void setShoujihao(String shoujihao) {
		this.shoujihao = shoujihao;
	}

	public String getBotelu() {
		return botelu;
	}

	public void setBotelu(String botelu) {
		this.botelu = botelu;
	}

	public String getTaocan() {
		return taocan;
	}

	public void setTaocan(String taocan) {
		this.taocan = taocan;
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

	public String getLlg1upper() {
		return llg1upper;
	}

	public void setLlg1upper(String llg1upper) {
		this.llg1upper = llg1upper;
	}

	public String getLlg1floor() {
		return llg1floor;
	}

	public void setLlg1floor(String llg1floor) {
		this.llg1floor = llg1floor;
	}

	public String getLlg2upper() {
		return llg2upper;
	}

	public void setLlg2upper(String llg2upper) {
		this.llg2upper = llg2upper;
	}

	public String getLlg2floor() {
		return llg2floor;
	}

	public void setLlg2floor(String llg2floor) {
		this.llg2floor = llg2floor;
	}

	public String getLlg3upper() {
		return llg3upper;
	}

	public void setLlg3upper(String llg3upper) {
		this.llg3upper = llg3upper;
	}

	public String getLlg3floor() {
		return llg3floor;
	}

	public void setLlg3floor(String llg3floor) {
		this.llg3floor = llg3floor;
	}

	public String getLlg4upper() {
		return llg4upper;
	}

	public void setLlg4upper(String llg4upper) {
		this.llg4upper = llg4upper;
	}

	public String getLlg4floor() {
		return llg4floor;
	}

	public void setLlg4floor(String llg4floor) {
		this.llg4floor = llg4floor;
	}

	public String getLlg5upper() {
		return llg5upper;
	}

	public void setLlg5upper(String llg5upper) {
		this.llg5upper = llg5upper;
	}

	public String getLlg5floor() {
		return llg5floor;
	}

	public void setLlg5floor(String llg5floor) {
		this.llg5floor = llg5floor;
	}

	public String getLlf1upper() {
		return llf1upper;
	}

	public void setLlf1upper(String llf1upper) {
		this.llf1upper = llf1upper;
	}

	public String getLlf1floor() {
		return llf1floor;
	}

	public void setLlf1floor(String llf1floor) {
		this.llf1floor = llf1floor;
	}

	public String getLlf2upper() {
		return llf2upper;
	}

	public void setLlf2upper(String llf2upper) {
		this.llf2upper = llf2upper;
	}

	public String getLlf2floor() {
		return llf2floor;
	}

	public void setLlf2floor(String llf2floor) {
		this.llf2floor = llf2floor;
	}
	
}
