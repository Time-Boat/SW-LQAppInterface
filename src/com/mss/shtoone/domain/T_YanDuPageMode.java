package com.mss.shtoone.domain;

import java.util.List;

public class T_YanDuPageMode {
    private int pagetotal;
    private int recordcount;
    private List<T_YanDu> datas;
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
	public List<T_YanDu> getDatas() {
		return datas;
	}
	public void setDatas(List<T_YanDu> datas) {
		this.datas = datas;
	}
}



