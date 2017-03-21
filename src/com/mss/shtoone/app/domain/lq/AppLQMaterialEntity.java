package com.mss.shtoone.app.domain.lq;

public class AppLQMaterialEntity {
	private String name;//材料名称
	private String yongliang;//实际生产用量
	private String scpeibi;//实际生产配比
	private String sgpeibi;//施工配比
	private String mbpeibi;

	private String wucha;//误差
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYongliang() {
		return yongliang;
	}
	public void setYongliang(String yongliang) {
		this.yongliang = yongliang;
	}
	public String getScpeibi() {
		return scpeibi;
	}
	public void setScpeibi(String scpeibi) {
		this.scpeibi = scpeibi;
	}
	public String getSgpeibi() {
		return sgpeibi;
	}
	public void setSgpeibi(String sgpeibi) {
		this.sgpeibi = sgpeibi;
	}

	public String getWucha() {
		return wucha;
	}
	public void setWucha(String wucha) {
		this.wucha = wucha;
	}
	public String getMbpeibi() {
		return mbpeibi;
	}
	public void setMbpeibi(String mbpeibi) {
		this.mbpeibi = mbpeibi;
	}
	
}
