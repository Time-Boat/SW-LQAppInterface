package com.mss.shtoone.persistence;


import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.HunnintuwuchaPageMode;
import com.mss.shtoone.domain.HunnintuwuchaView;


@Repository
public interface HunnintuwuchaViewDAO extends GenericDAO<HunnintuwuchaView, Integer> {
	public HunnintuwuchaPageMode viewlist(String shebeibianhao,String gongchenghao,
			String startTimeOne,String endTimeOne,String jiaozhubuwei, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid,int offset, int pagesize);
}



