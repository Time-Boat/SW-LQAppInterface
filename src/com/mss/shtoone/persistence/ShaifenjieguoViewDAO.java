package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShaifenjieguoView;

@Repository
public interface ShaifenjieguoViewDAO extends GenericDAO<ShaifenjieguoView, Integer>{
	public GenericPageMode shaifenjieguoviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, Integer bsid, Integer offset, Integer pagesize,String llbuwei);
	public List<ShaifenjieguoView> findBySql(String sql);
}
