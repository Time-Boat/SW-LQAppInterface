package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.Smsinfo;
import com.mss.shtoone.domain.SmsinfoPageMode;

@Repository
public interface SmsinfoDAO extends GenericDAO<Smsinfo, Integer> {
	public SmsinfoPageMode viewlist(String startTimeOne,String endTimeOne,Integer biaoduan, 
			Integer xiangmubu, String bhzid,String fn, int bsid, int offset, int pagesize);
	public SmsinfoPageMode viewsuccesslist(String startTimeOne,	String endTimeOne,Integer biaoduan, 
			Integer xiangmubu, String bhzid,String fn, int bsid, int offset, int pagesize);
	public List<LiqingView> lqsmstongji(String startTime, String endTime,String shebeibianhao);
	
	//预警统计
	public List<LiqingView> smstongji(String startTimeOne,String endTimeOne,Integer biaoduan, 
			Integer xiangmubu,String bhzid,String fn, int bsid, int fzlx, int jbsj);
}
