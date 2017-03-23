package com.mss.shtoone.app.domain.lq;

public class LQDataQueryChartEntity {
	// 筛选率
	private String name;

	// 允许波动上限
	private String maxPassper;
	// 允许波动下限
	private String minPassper;
	// 标准级配
	private String standPassper;
	// 实际级配
	private String passper;
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
	
}
