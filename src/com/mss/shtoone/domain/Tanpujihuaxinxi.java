package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 摊铺计划信息
 * @author sgy
 *
 */
@Entity
public class Tanpujihuaxinxi implements Serializable {

	private static final long serialVersionUID = -5075491324739793497L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/**
	 * 所属标段ID
	 */
	private String biaoduanid;
	/**
	 * 计划名称
	 */
	private String jihuamingcheng;
	/**
	 * 计划开始时间
	 */
	private String jihuastarttime;
	/**
	 * 计划结束时间
	 */
	private String jihuaendtime;
	/**
	 * 计划摊铺里程
	 */
	private String jihuatanpulicheng;
	/**
	 * 计划生产量
	 */
	private String jihuashengchanliang;
	/**
	 * 计划摊铺层面 (上面层，中面层，下面层)
	 */
	private String jihuatanpucengmian;
	/**
	 * 计划填写日期
	 */
	private String jihuatianxieriqi;
	/**
	 * 计划编辑人
	 */
	private String jihuabianjiren;
	/**
	 * 备注
	 */
	private String remark;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBiaoduanid() {
		return biaoduanid;
	}
	public void setBiaoduanid(String biaoduanid) {
		this.biaoduanid = biaoduanid;
	}
	public String getJihuamingcheng() {
		return jihuamingcheng;
	}
	public void setJihuamingcheng(String jihuamingcheng) {
		this.jihuamingcheng = jihuamingcheng;
	}
	public String getJihuastarttime() {
		return jihuastarttime;
	}
	public void setJihuastarttime(String jihuastarttime) {
		this.jihuastarttime = jihuastarttime;
	}
	public String getJihuaendtime() {
		return jihuaendtime;
	}
	public void setJihuaendtime(String jihuaendtime) {
		this.jihuaendtime = jihuaendtime;
	}
	public String getJihuatanpulicheng() {
		return jihuatanpulicheng;
	}
	public void setJihuatanpulicheng(String jihuatanpulicheng) {
		this.jihuatanpulicheng = jihuatanpulicheng;
	}
	public String getJihuashengchanliang() {
		return jihuashengchanliang;
	}
	public void setJihuashengchanliang(String jihuashengchanliang) {
		this.jihuashengchanliang = jihuashengchanliang;
	}
	public String getJihuatanpucengmian() {
		return jihuatanpucengmian;
	}
	public void setJihuatanpucengmian(String jihuatanpucengmian) {
		this.jihuatanpucengmian = jihuatanpucengmian;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJihuatianxieriqi() {
		return jihuatianxieriqi;
	}
	public void setJihuatianxieriqi(String jihuatianxieriqi) {
		this.jihuatianxieriqi = jihuatianxieriqi;
	}
	public String getJihuabianjiren() {
		return jihuabianjiren;
	}
	public void setJihuabianjiren(String jihuabianjiren) {
		this.jihuabianjiren = jihuabianjiren;
	}
	
	
	
}
