package com.mss.shtoone.domain;

import java.util.List;

public class UserPageMode {
    private int pagetotal;
    private int recordcount;
    private List<Muser> datas;
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
	public List<Muser> getDatas() {
		return datas;
	}
	public void setDatas(List<Muser> datas) {
		this.datas = datas;
	}
}



