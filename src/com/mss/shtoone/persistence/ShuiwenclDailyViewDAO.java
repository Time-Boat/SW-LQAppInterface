package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShuiwenclDaily;
import com.mss.shtoone.domain.ShuiwenclDailyView;



@Repository
public interface ShuiwenclDailyViewDAO extends GenericDAO<ShuiwenclDailyView, Integer> {
	public GenericPageMode limitdailylist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);

	public List<ShuiwenclDaily> getSwdailycl(String startTime, String endTime);
	public String oneSwdailycl(String sbbh, String startTime, String endTime); 
}



