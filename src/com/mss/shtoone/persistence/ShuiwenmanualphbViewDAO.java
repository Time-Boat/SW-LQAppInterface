package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.ShuiwenziduancfgView;

@Repository
public interface ShuiwenmanualphbViewDAO extends GenericDAO<ShuiwenmanualphbView, Integer> {
	public GenericPageMode swwuchamanualphbviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	
	public GenericPageMode swchaobiaomanualviewlist(String shebeibianhao, 
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize,Integer chaobiaolx,ShuiwenziduancfgView swisshow,Integer cllx,String bianhao);
	
	public ShuiwenphbView swmateriallist(String startTime,String endTime,String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid);
	
	public ShuiwenphbView appSwmateriallist(String startTime,String endTime,String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, Integer bsid);
	
}



