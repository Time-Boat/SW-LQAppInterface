package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.T_YanDu;
import com.mss.shtoone.domain.T_YanDuPageMode;



@Repository
public interface T_YanDuDAO extends GenericDAO<T_YanDu, String> {
	public T_YanDuPageMode viewlist(String testNo,
			String startTimeOne, String endTimeOne,
			String BiaoDuanId,int offset, int pagesize);
	public T_YanDu getT_YanDu(String testNo);

}



