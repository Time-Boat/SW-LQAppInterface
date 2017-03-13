package com.mss.shtoone.domain;

import java.util.List;

public class DiuqishujuPageMode {
    private int pagetotal;
    private int recordcount;
    private List<Diuqishuju> datas;
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
	public List<Diuqishuju> getDatas() {
		return datas;
	}
	public void setDatas(List<Diuqishuju> datas) {
		this.datas = datas;
	}
}



