package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.XCData2;
import com.mss.shtoone.domain.XCData2PageMode;



@Repository
public interface XCData2DAO extends GenericDAO<XCData2, Integer> {
	public XCData2PageMode viewlist(Integer gprsDeviceId,
			String startTimeOne, String endTimeOne,
			String BiaoDuanId,int offset, int pagesize);
	public XCData2 getXCData2(int gprsDeviceId);

}



