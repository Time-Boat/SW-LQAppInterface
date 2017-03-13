package com.mss.shtoone.domain;

import java.util.List;

public class HunnintuPageMode {
    private int pagetotal;
    private int recordcount;
    private List<HunnintuView> datas;
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
	public List<HunnintuView> getDatas() {
		return datas;
	}
	public void setDatas(List<HunnintuView> datas) {
		this.datas = datas;
	}
}



