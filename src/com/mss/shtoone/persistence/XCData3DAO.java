package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.XCData3;

@Repository
public interface XCData3DAO extends GenericDAO<XCData3, Integer> {
	public GenericPageMode viewgpslist(String shebeibianhao,String startTimeOne,
			String endTimeOne,
			String fn, int bsid, int offset, int pagesize);
	public XCData3 getXCData3(String gpsno);

}



