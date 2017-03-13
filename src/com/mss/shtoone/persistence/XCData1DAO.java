package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.XCData1;
import com.mss.shtoone.domain.XCData1PageMode;



@Repository
public interface XCData1DAO extends GenericDAO<XCData1, Integer> {
	public XCData1PageMode viewlist(Integer gprsDeviceId,
			String startTimeOne, String endTimeOne,
			String BiaoDuanId,int offset, int pagesize);
	public XCData1 getXCData1(int gprsDeviceId);

}



