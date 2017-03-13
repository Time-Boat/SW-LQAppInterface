package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Xiangxixxjieguo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5100099910786683322L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bianhao;
	
	private Integer xinxibianhao;
	
	private Integer leiji;

	private String sha1_shijizhi;		

	private String shi1_shijizhi;
	
	private String shi2_shijizhi;
	
	private String sha2_shijizhi;
	
	private String guliao5_shijizhi;
	
	private String shuini1_shijizhi;
	
	private String shuini2_shijizhi;
	
	private String kuangfen3_shijizhi;
	
	private String feimeihui4_shijizhi;	

	private String fenliao5_shijizhi;
	
	private String fenliao6_shijizhi;
	
	private String shui1_shijizhi;
	
	private String shui2_shijizhi;	
	
	private String waijiaji1_shijizhi;	
	
	private String waijiaji2_shijizhi;	
	
	private String waijiaji3_shijizhi;	
	
	private String waijiaji4_shijizhi;	
	
	private String jiaobanshijian;	


	public int getBianhao() {
		return bianhao;
	}

	public void setBianhao(int bianhao) {
		this.bianhao = bianhao;
	}
	

	public String getSha1_shijizhi() {
		return sha1_shijizhi;
	}

	public void setSha1_shijizhi(String sha1Shijizhi) {
		sha1_shijizhi = sha1Shijizhi;
	}

	public String getShi1_shijizhi() {
		return shi1_shijizhi;
	}

	public void setShi1_shijizhi(String shi1Shijizhi) {
		shi1_shijizhi = shi1Shijizhi;
	}

	public String getShi2_shijizhi() {
		return shi2_shijizhi;
	}

	public void setShi2_shijizhi(String shi2Shijizhi) {
		shi2_shijizhi = shi2Shijizhi;
	}

	public String getSha2_shijizhi() {
		return sha2_shijizhi;
	}

	public void setSha2_shijizhi(String sha2Shijizhi) {
		sha2_shijizhi = sha2Shijizhi;
	}

	public String getGuliao5_shijizhi() {
		return guliao5_shijizhi;
	}

	public void setGuliao5_shijizhi(String guliao5Shijizhi) {
		guliao5_shijizhi = guliao5Shijizhi;
	}

	public String getShuini1_shijizhi() {
		return shuini1_shijizhi;
	}

	public void setShuini1_shijizhi(String shuini1Shijizhi) {
		shuini1_shijizhi = shuini1Shijizhi;
	}

	public String getShuini2_shijizhi() {
		return shuini2_shijizhi;
	}

	public void setShuini2_shijizhi(String shuini2Shijizhi) {
		shuini2_shijizhi = shuini2Shijizhi;
	}

	public String getKuangfen3_shijizhi() {
		return kuangfen3_shijizhi;
	}

	public void setKuangfen3_shijizhi(String kuangfen3Shijizhi) {
		kuangfen3_shijizhi = kuangfen3Shijizhi;
	}

	public String getFeimeihui4_shijizhi() {
		return feimeihui4_shijizhi;
	}

	public void setFeimeihui4_shijizhi(String feimeihui4Shijizhi) {
		feimeihui4_shijizhi = feimeihui4Shijizhi;
	}

	public String getFenliao5_shijizhi() {
		return fenliao5_shijizhi;
	}

	public void setFenliao5_shijizhi(String fenliao5Shijizhi) {
		fenliao5_shijizhi = fenliao5Shijizhi;
	}

	public String getFenliao6_shijizhi() {
		return fenliao6_shijizhi;
	}

	public void setFenliao6_shijizhi(String fenliao6Shijizhi) {
		fenliao6_shijizhi = fenliao6Shijizhi;
	}

	public String getShui1_shijizhi() {
		return shui1_shijizhi;
	}

	public void setShui1_shijizhi(String shui1Shijizhi) {
		shui1_shijizhi = shui1Shijizhi;
	}

	public String getShui2_shijizhi() {
		return shui2_shijizhi;
	}

	public void setShui2_shijizhi(String shui2Shijizhi) {
		shui2_shijizhi = shui2Shijizhi;
	}

	public String getWaijiaji1_shijizhi() {
		return waijiaji1_shijizhi;
	}

	public void setWaijiaji1_shijizhi(String waijiaji1Shijizhi) {
		waijiaji1_shijizhi = waijiaji1Shijizhi;
	}

	public String getWaijiaji2_shijizhi() {
		return waijiaji2_shijizhi;
	}

	public void setWaijiaji2_shijizhi(String waijiaji2Shijizhi) {
		waijiaji2_shijizhi = waijiaji2Shijizhi;
	}

	public String getWaijiaji3_shijizhi() {
		return waijiaji3_shijizhi;
	}

	public void setWaijiaji3_shijizhi(String waijiaji3Shijizhi) {
		waijiaji3_shijizhi = waijiaji3Shijizhi;
	}

	public String getWaijiaji4_shijizhi() {
		return waijiaji4_shijizhi;
	}

	public void setWaijiaji4_shijizhi(String waijiaji4Shijizhi) {
		waijiaji4_shijizhi = waijiaji4Shijizhi;
	}	

	public String getJiaobanshijian() {
		return jiaobanshijian;
	}

	public void setJiaobanshijian(String jiaobanshijian) {
		this.jiaobanshijian = jiaobanshijian;
	}

	public Integer getXinxibianhao() {
		return xinxibianhao;
	}

	public void setXinxibianhao(Integer xinxibianhao) {
		this.xinxibianhao = xinxibianhao;
	}

	public Integer getLeiji() {
		return leiji;
	}

	public void setLeiji(Integer leiji) {
		this.leiji = leiji;
	}	
}
