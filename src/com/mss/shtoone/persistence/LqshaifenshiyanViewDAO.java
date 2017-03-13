package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LqshaifenshiyanView;

@Repository
public interface LqshaifenshiyanViewDAO extends GenericDAO<LqshaifenshiyanView, Integer>{
	public GenericPageMode limitLqsflist(Integer biaoduan,Integer xiangmubu,String shebeibianhao,String startTimeOne,
			String endTimeOne ,String fn, int bsid, int offset, int pagesize,String moren,String ziduanminchen);
}
