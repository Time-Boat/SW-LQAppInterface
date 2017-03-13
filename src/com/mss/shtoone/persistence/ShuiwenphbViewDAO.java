package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShuiwenphbView;

@Repository
public interface ShuiwenphbViewDAO extends GenericDAO<ShuiwenphbView, Integer>{
	public GenericPageMode swphbviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	
	
}
