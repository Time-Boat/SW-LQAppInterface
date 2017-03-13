package com.mss.shtoone.domain;

import java.util.List;

public class SmsinfoPageMode {
    private int pagetotal;
    private int recordcount;
    private List<SmsinfoView> datas;
	public int getPagetotal() {
		return pagetotal;
	}
	public void setPagetotal(int pagetotal) {
		this.pagetotal = pagetotal;
	}
	public int getRecordcount() {
		return recordcount;
	}
	public void setRecordcount(int recordcount) {
		this.recordcount = recordcount;
	}
	public List<SmsinfoView> getDatas() {
		return datas;
	}
	public void setDatas(List<SmsinfoView> datas) {
		this.datas = datas;
	}
}



