package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LqshaifenjieguoView;

@Repository
public interface LqshaifenjieguoViewDAO extends GenericDAO<LqshaifenjieguoView, Integer>{
	public GenericPageMode Lqshaifenjieguoviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize, int queryalldata, String peifan);
}
