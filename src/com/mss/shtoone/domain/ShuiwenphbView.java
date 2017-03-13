package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShuiwenphbView implements Serializable {
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
	private int id;
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

	
	public ShuiwenphbView() {
		super();
	}

	public ShuiwenphbView(int bianhao) {
		super();
		this.bianhao = bianhao;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
}
