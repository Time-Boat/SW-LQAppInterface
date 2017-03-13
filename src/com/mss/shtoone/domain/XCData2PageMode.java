package com.mss.shtoone.domain;

import java.util.List;

public class XCData2PageMode {
    private int pagetotal;
    private int recordcount;
    private List<XCData2> datas;
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
	public List<XCData2> getDatas() {
		return datas;
	}
	public void setDatas(List<XCData2> datas) {
		this.datas = datas;
	}
}



