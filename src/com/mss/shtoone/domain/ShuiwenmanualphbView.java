package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShuiwenmanualphbView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8896972033164314376L;


	@Id
	private int bianhao;

	private String sjgl1;	

	private String sjgl2;	

	private String sjgl3;		

	private String sjgl4;
	
	private String sjgl5;
	
	private String sjfl1;
	
	private String sjfl2;
	
	private String sjshui;
	
	private String shijian;
	
	private String shijianS;
	
	private String  shijianE;
	
	private String glchangliang;
	
	private String llgl1;	

	private String llgl2;
	
	private String llgl3;
	
	private String llgl4;
	
	private String llgl5;	
	
	private String llfl1;	
	
	private String llfl2;	
	
	private String llshui;	
	
	private String persjgl1;	
	
	private String persjgl2;	
	
	private String persjgl3;
	
	private String persjgl4;	
	
	private String persjgl5;
	
	private String persjfl1;
	
	private String persjfl2;
	
	private String pershui;
	
	private String perllgl1;	
	
	private String perllgl2;	
	
	private String perllgl3;
	
	private String perllgl4;	
	
	private String perllgl5;
	
	private String perllfl1;
	
	private String perllfl2;
	
	private String wgl1;
	
	private String wgl2;

	private String wgl3;
	
	private String wgl4;
	
	private String wgl5;
	
	private String wfl1;
	
	private String wfl2;
	
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
	private String smsbaojin;
	private String smssettype;
	private String smstype;
	private String sendtype;
	private String panshu;
	private String ambegin;
	private String amend;
	private String pmbegin;
	private String pmend;
	private Integer id;
	private String xiangmubuminchen;
	private String gongkongleixin;
	private String gprsbianhao;
	private String shebeichanjia;
	private String jiekouleixin;
	private String gongkongruanjian;
	private String shujukuleixin;
	private String beizhu;
	private String shuliang;
	private Integer biaoduanid;
	private Integer xiangmubuid;
	private Integer zuoyeduiid;
	
	private Integer llid;    

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
	
	private String llshebeibianhao;//设备编号
	
	private String llbeizhu;
	
	private String manualwgl1;
	private String manualwgl2;
	private String manualwgl3;
	private String manualwgl4;
	private String manualwgl5;
	private String manualwfl1;
	private String manualwfl2;

	//超标级别
	private String sjg1;
	private String sjg2;
	private String sjg3;
	private String sjg4;
	private String sjg5;
	private String sjf1;
	private String sjf2;
	private String sjgl1_primary;
	private String sjgl1_middle;
	private String sjgl1_high;
	private String sjgl2_primary;
	private String sjgl2_middle;
	private String sjgl2_high;
	private String sjgl3_primary;
	private String sjgl3_middle;
	private String sjgl3_high;
	private String sjgl4_primary;
	private String sjgl4_middle;
	private String sjgl4_high;
	private String sjgl5_primary;
	private String sjgl5_middle;
	private String sjgl5_high;
	private String sjfl1_primary;
	private String sjfl1_middle;
	private String sjfl1_high;
	private String sjfl2_primary;
	private String sjfl2_middle;
	private String sjfl2_high;
	//附件
	private String filepath;
	private String leixing;
	private String chulijieguo;
	private String yezhuyijian;
	
	
	private String sjgl4and5;
	
	//app新增字段
	private String appSjShui;
	public String getAppSjShui() {
		return appSjShui;
	}
	public void setAppSjShui(String appSjShui) {
		this.appSjShui = appSjShui;
	}

	public String getSjgl4and5() {
		return sjgl4and5;
	}

	public void setSjgl4and5(String sjgl4and5) {
		this.sjgl4and5 = sjgl4and5;
	}

	public String getYezhuyijian() {
		return yezhuyijian;
	}

	public void setYezhuyijian(String yezhuyijian) {
		this.yezhuyijian = yezhuyijian;
	}

	public String getChulijieguo() {
		return chulijieguo;
	}

	public void setChulijieguo(String chulijieguo) {
		this.chulijieguo = chulijieguo;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
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

	public String getSjgl1_primary() {
		return sjgl1_primary;
	}

	public void setSjgl1_primary(String sjgl1_primary) {
		this.sjgl1_primary = sjgl1_primary;
	}

	public String getSjgl1_middle() {
		return sjgl1_middle;
	}

	public void setSjgl1_middle(String sjgl1_middle) {
		this.sjgl1_middle = sjgl1_middle;
	}

	public String getSjgl1_high() {
		return sjgl1_high;
	}

	public void setSjgl1_high(String sjgl1_high) {
		this.sjgl1_high = sjgl1_high;
	}

	public String getSjgl2_primary() {
		return sjgl2_primary;
	}

	public void setSjgl2_primary(String sjgl2_primary) {
		this.sjgl2_primary = sjgl2_primary;
	}

	public String getSjgl2_middle() {
		return sjgl2_middle;
	}

	public void setSjgl2_middle(String sjgl2_middle) {
		this.sjgl2_middle = sjgl2_middle;
	}

	public String getSjgl2_high() {
		return sjgl2_high;
	}

	public void setSjgl2_high(String sjgl2_high) {
		this.sjgl2_high = sjgl2_high;
	}

	public String getSjgl3_primary() {
		return sjgl3_primary;
	}

	public void setSjgl3_primary(String sjgl3_primary) {
		this.sjgl3_primary = sjgl3_primary;
	}

	public String getSjgl3_middle() {
		return sjgl3_middle;
	}

	public void setSjgl3_middle(String sjgl3_middle) {
		this.sjgl3_middle = sjgl3_middle;
	}

	public String getSjgl3_high() {
		return sjgl3_high;
	}

	public void setSjgl3_high(String sjgl3_high) {
		this.sjgl3_high = sjgl3_high;
	}

	public String getSjgl4_primary() {
		return sjgl4_primary;
	}

	public void setSjgl4_primary(String sjgl4_primary) {
		this.sjgl4_primary = sjgl4_primary;
	}

	public String getSjgl4_middle() {
		return sjgl4_middle;
	}

	public void setSjgl4_middle(String sjgl4_middle) {
		this.sjgl4_middle = sjgl4_middle;
	}

	public String getSjgl4_high() {
		return sjgl4_high;
	}

	public void setSjgl4_high(String sjgl4_high) {
		this.sjgl4_high = sjgl4_high;
	}

	public String getSjgl5_primary() {
		return sjgl5_primary;
	}

	public void setSjgl5_primary(String sjgl5_primary) {
		this.sjgl5_primary = sjgl5_primary;
	}

	public String getSjgl5_middle() {
		return sjgl5_middle;
	}

	public void setSjgl5_middle(String sjgl5_middle) {
		this.sjgl5_middle = sjgl5_middle;
	}

	public String getSjgl5_high() {
		return sjgl5_high;
	}

	public void setSjgl5_high(String sjgl5_high) {
		this.sjgl5_high = sjgl5_high;
	}

	public String getSjfl1_primary() {
		return sjfl1_primary;
	}

	public void setSjfl1_primary(String sjfl1_primary) {
		this.sjfl1_primary = sjfl1_primary;
	}

	public String getSjfl1_middle() {
		return sjfl1_middle;
	}

	public void setSjfl1_middle(String sjfl1_middle) {
		this.sjfl1_middle = sjfl1_middle;
	}

	public String getSjfl1_high() {
		return sjfl1_high;
	}

	public void setSjfl1_high(String sjfl1_high) {
		this.sjfl1_high = sjfl1_high;
	}

	public String getSjfl2_primary() {
		return sjfl2_primary;
	}

	public void setSjfl2_primary(String sjfl2_primary) {
		this.sjfl2_primary = sjfl2_primary;
	}

	public String getSjfl2_middle() {
		return sjfl2_middle;
	}

	public void setSjfl2_middle(String sjfl2_middle) {
		this.sjfl2_middle = sjfl2_middle;
	}

	public String getSjfl2_high() {
		return sjfl2_high;
	}

	public void setSjfl2_high(String sjfl2_high) {
		this.sjfl2_high = sjfl2_high;
	}

	public int getBianhao() {
		return bianhao;
	}

	public void setBianhao(int bianhao) {
		this.bianhao = bianhao;
	}

	public String getSjgl1() {
		return sjgl1;
	}

	public void setSjgl1(String sjgl1) {
		this.sjgl1 = sjgl1;
	}

	public String getSjgl2() {
		return sjgl2;
	}

	public void setSjgl2(String sjgl2) {
		this.sjgl2 = sjgl2;
	}

	public String getSjgl3() {
		return sjgl3;
	}

	public void setSjgl3(String sjgl3) {
		this.sjgl3 = sjgl3;
	}

	public String getSjgl4() {
		return sjgl4;
	}

	public void setSjgl4(String sjgl4) {
		this.sjgl4 = sjgl4;
	}

	public String getSjgl5() {
		return sjgl5;
	}

	public void setSjgl5(String sjgl5) {
		this.sjgl5 = sjgl5;
	}

	public String getSjfl1() {
		return sjfl1;
	}

	public void setSjfl1(String sjfl1) {
		this.sjfl1 = sjfl1;
	}

	public String getSjfl2() {
		return sjfl2;
	}

	public void setSjfl2(String sjfl2) {
		this.sjfl2 = sjfl2;
	}

	public String getSjshui() {
		return sjshui;
	}

	public void setSjshui(String sjshui) {
		this.sjshui = sjshui;
	}

	public String getShijianS() {
		return shijianS;
	}

	public void setShijianS(String shijianS) {
		this.shijianS = shijianS;
	}

	public String getShijianE() {
		return shijianE;
	}

	public void setShijianE(String shijianE) {
		this.shijianE = shijianE;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getGlchangliang() {
		return glchangliang;
	}

	public void setGlchangliang(String glchangliang) {
		this.glchangliang = glchangliang;
	}

	public String getLlgl1() {
		return llgl1;
	}

	public void setLlgl1(String llgl1) {
		this.llgl1 = llgl1;
	}

	public String getLlgl2() {
		return llgl2;
	}

	public void setLlgl2(String llgl2) {
		this.llgl2 = llgl2;
	}

	public String getLlgl3() {
		return llgl3;
	}

	public void setLlgl3(String llgl3) {
		this.llgl3 = llgl3;
	}

	public String getLlgl4() {
		return llgl4;
	}

	public void setLlgl4(String llgl4) {
		this.llgl4 = llgl4;
	}

	public String getLlgl5() {
		return llgl5;
	}

	public void setLlgl5(String llgl5) {
		this.llgl5 = llgl5;
	}

	public String getLlfl1() {
		return llfl1;
	}

	public void setLlfl1(String llfl1) {
		this.llfl1 = llfl1;
	}

	public String getLlfl2() {
		return llfl2;
	}

	public void setLlfl2(String llfl2) {
		this.llfl2 = llfl2;
	}

	public String getLlshui() {
		return llshui;
	}

	public void setLlshui(String llshui) {
		this.llshui = llshui;
	}

	public String getPersjgl1() {
		return persjgl1;
	}

	public void setPersjgl1(String persjgl1) {
		this.persjgl1 = persjgl1;
	}

	public String getPersjgl2() {
		return persjgl2;
	}

	public void setPersjgl2(String persjgl2) {
		this.persjgl2 = persjgl2;
	}

	public String getPersjgl3() {
		return persjgl3;
	}

	public void setPersjgl3(String persjgl3) {
		this.persjgl3 = persjgl3;
	}

	public String getPersjgl4() {
		return persjgl4;
	}

	public void setPersjgl4(String persjgl4) {
		this.persjgl4 = persjgl4;
	}

	public String getPersjgl5() {
		return persjgl5;
	}

	public void setPersjgl5(String persjgl5) {
		this.persjgl5 = persjgl5;
	}

	public String getPersjfl1() {
		return persjfl1;
	}

	public void setPersjfl1(String persjfl1) {
		this.persjfl1 = persjfl1;
	}

	public String getPersjfl2() {
		return persjfl2;
	}

	public void setPersjfl2(String persjfl2) {
		this.persjfl2 = persjfl2;
	}

	public String getPerllgl1() {
		return perllgl1;
	}

	public void setPerllgl1(String perllgl1) {
		this.perllgl1 = perllgl1;
	}

	public String getPerllgl2() {
		return perllgl2;
	}

	public void setPerllgl2(String perllgl2) {
		this.perllgl2 = perllgl2;
	}

	public String getPerllgl3() {
		return perllgl3;
	}

	public void setPerllgl3(String perllgl3) {
		this.perllgl3 = perllgl3;
	}

	public String getPerllgl4() {
		return perllgl4;
	}

	public void setPerllgl4(String perllgl4) {
		this.perllgl4 = perllgl4;
	}

	public String getPerllgl5() {
		return perllgl5;
	}

	public void setPerllgl5(String perllgl5) {
		this.perllgl5 = perllgl5;
	}

	public String getPerllfl1() {
		return perllfl1;
	}

	public void setPerllfl1(String perllfl1) {
		this.perllfl1 = perllfl1;
	}

	public String getPerllfl2() {
		return perllfl2;
	}

	public void setPerllfl2(String perllfl2) {
		this.perllfl2 = perllfl2;
	}

	
	public String getWgl1() {
		return wgl1;
	}

	public void setWgl1(String wgl1) {
		this.wgl1 = wgl1;
	}

	public String getWgl2() {
		return wgl2;
	}

	public void setWgl2(String wgl2) {
		this.wgl2 = wgl2;
	}

	public String getWgl3() {
		return wgl3;
	}

	public void setWgl3(String wgl3) {
		this.wgl3 = wgl3;
	}

	public String getWgl4() {
		return wgl4;
	}

	public void setWgl4(String wgl4) {
		this.wgl4 = wgl4;
	}

	public String getWgl5() {
		return wgl5;
	}

	public void setWgl5(String wgl5) {
		this.wgl5 = wgl5;
	}

	public String getWfl1() {
		return wfl1;
	}

	public void setWfl1(String wfl1) {
		this.wfl1 = wfl1;
	}

	public String getWfl2() {
		return wfl2;
	}

	public void setWfl2(String wfl2) {
		this.wfl2 = wfl2;
	}

	public String getPershui() {
		return pershui;
	}

	public void setPershui(String pershui) {
		this.pershui = pershui;
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

	public Integer getLlid() {
		return llid;
	}

	public void setLlid(Integer llid) {
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

	public String getManualwgl1() {
		return manualwgl1;
	}

	public void setManualwgl1(String manualwgl1) {
		this.manualwgl1 = manualwgl1;
	}

	public String getManualwgl2() {
		return manualwgl2;
	}

	public void setManualwgl2(String manualwgl2) {
		this.manualwgl2 = manualwgl2;
	}

	public String getManualwgl3() {
		return manualwgl3;
	}

	public void setManualwgl3(String manualwgl3) {
		this.manualwgl3 = manualwgl3;
	}

	public String getManualwgl4() {
		return manualwgl4;
	}

	public void setManualwgl4(String manualwgl4) {
		this.manualwgl4 = manualwgl4;
	}

	public String getManualwgl5() {
		return manualwgl5;
	}

	public void setManualwgl5(String manualwgl5) {
		this.manualwgl5 = manualwgl5;
	}

	public String getManualwfl1() {
		return manualwfl1;
	}

	public void setManualwfl1(String manualwfl1) {
		this.manualwfl1 = manualwfl1;
	}

	public String getManualwfl2() {
		return manualwfl2;
	}

	public void setManualwfl2(String manualwfl2) {
		this.manualwfl2 = manualwfl2;
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
