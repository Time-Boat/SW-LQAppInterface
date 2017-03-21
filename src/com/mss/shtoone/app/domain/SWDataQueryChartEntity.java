package com.mss.shtoone.app.domain;

public class SWDataQueryChartEntity {
	
	//筛选率
	private String name;
	//允许波动上限
	private String maxPassper;
	//允许波动下限
	private String minPassper;
	//标准级配
	private String standPassper;
	//实际级配
	private String passper;
	//预警上限
	private String yjsx;
	//预警下限
	private String yjxx;
	//误差值
	private String wcz;
	//预警值
	private String yjz;
	
	public String getWcz() {
		return wcz;
	}
	public void setWcz(String wcz) {
		this.wcz = wcz;
	}
	public String getYjz() {
		return yjz;
	}
	public void setYjz(String yjz) {
		this.yjz = yjz;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaxPassper() {
		return maxPassper;
	}
	public void setMaxPassper(String maxPassper) {
		this.maxPassper = maxPassper;
	}
	public String getMinPassper() {
		return minPassper;
	}
	public void setMinPassper(String minPassper) {
		this.minPassper = minPassper;
	}
	public String getStandPassper() {
		return standPassper;
	}
	public void setStandPassper(String standPassper) {
		this.standPassper = standPassper;
	}
	public String getPassper() {
		return passper;
	}
	public void setPassper(String passper) {
		this.passper = passper;
	}
	public String getYjsx() {
		return yjsx;
	}
	public void setYjsx(String yjsx) {
		this.yjsx = yjsx;
	}
	public String getYjxx() {
		return yjxx;
	}
	public void setYjxx(String yjxx) {
		this.yjxx = yjxx;
	}
	
}
