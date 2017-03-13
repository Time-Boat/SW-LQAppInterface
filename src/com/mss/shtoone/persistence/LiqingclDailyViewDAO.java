package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingclDaily;
import com.mss.shtoone.domain.LiqingclDailyView;



@Repository
public interface LiqingclDailyViewDAO extends GenericDAO<LiqingclDailyView, Integer> {
	public GenericPageMode limitdailylist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);

	public List<LiqingclDaily> getlqdailycl(String startTime, String endTime);
	public String onelqdailycl(String sbbh, String startTime, String endTime); 
}



