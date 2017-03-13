package com.mss.shtoone.domain;

import java.util.List;

public class HunnintujieguoPageMode {
    private int pagetotal;
    private int recordcount;
    private List<HunnintujieguoView> datas;
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
	public List<HunnintujieguoView> getDatas() {
		return datas;
	}
	public void setDatas(List<HunnintujieguoView> datas) {
		this.datas = datas;
	}
}



