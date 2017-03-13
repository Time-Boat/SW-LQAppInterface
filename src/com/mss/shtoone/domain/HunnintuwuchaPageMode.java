package com.mss.shtoone.domain;

import java.io.Serializable;
import java.util.List;

public class HunnintuwuchaPageMode implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -8496237359867628255L;
	private int pagetotal;
    private int recordcount;
    private List<HunnintuwuchaView> datas;
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
	public List<HunnintuwuchaView> getDatas() {
		return datas;
	}
	public void setDatas(List<HunnintuwuchaView> datas) {
		this.datas = datas;
	}
}



