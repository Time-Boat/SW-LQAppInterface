package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ShuiwenziduancfgView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1232996282920895630L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cfgid;
    

	private String sjgl1;	

	private String sjgl2;	

	private String sjgl3;		

	private String sjgl4;
	
	private String sjgl5;
	
	private String sjfl1;
	
	private String sjfl2;
	
	private String sjshui;
	
	private String shijian;
	
	private String glchangliang;
	
	private String llgl1;	

	private String llgl2;
	
	private String llgl3;
	
	private String llgl4;
	
	private String llgl5;	
	
	private String llfl1;	
	
	private String llfl2;	
	
	private String llshui;	
	
	private String pergl1;	
	
	private String pergl2;	
	
	private String pergl3;
	
	private String pergl4;	
	
	private String pergl5;
	
	private String perfl1;
	
	private String perfl2;
	
	private String pershui;
	
	private String beiy1;
	
	private String beiy2;
	
	private String beiy3;
	
	private String shebeibianhao;
	
	private String baocunshijian;
	
	private String kehuduanbianhao;
	
	private String biaoshi;
	
	private String caijishijian;
	
	private String leixing;
	
	private String gprsbianhao;
	
	
    private String banhezhanminchen;
	
	private String jianchen;
	
	private String smsbaojin;
	
	private Integer biaoduanid;
	private Integer xiangmubuid;
	private Integer id;
	
	public int getCfgid() {
		return cfgid;
	}
	public void setCfgid(int cfgid) {
		this.cfgid = cfgid;
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
	public String getPergl1() {
		return pergl1;
	}
	public void setPergl1(String pergl1) {
		this.pergl1 = pergl1;
	}
	public String getPergl2() {
		return pergl2;
	}
	public void setPergl2(String pergl2) {
		this.pergl2 = pergl2;
	}
	public String getPergl3() {
		return pergl3;
	}
	public void setPergl3(String pergl3) {
		this.pergl3 = pergl3;
	}
	public String getPergl4() {
		return pergl4;
	}
	public void setPergl4(String pergl4) {
		this.pergl4 = pergl4;
	}
	public String getPergl5() {
		return pergl5;
	}
	public void setPergl5(String pergl5) {
		this.pergl5 = pergl5;
	}
	public String getPerfl1() {
		return perfl1;
	}
	public void setPerfl1(String perfl1) {
		this.perfl1 = perfl1;
	}
	public String getPerfl2() {
		return perfl2;
	}
	public void setPerfl2(String perfl2) {
		this.perfl2 = perfl2;
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
	public String getLeixing() {
		return leixing;
	}
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	public String getGprsbianhao() {
		return gprsbianhao;
	}
	public void setGprsbianhao(String gprsbianhao) {
		this.gprsbianhao = gprsbianhao;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
