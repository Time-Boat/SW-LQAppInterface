package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.HntbhzziduancfgView;

@Repository
public interface HntbhzCfgViewDAO extends GenericDAO<HntbhzziduancfgView, Integer> {
	public List<HntbhzziduancfgView> findByProperty(String propertyName, Object value);	
	public List<HntbhzziduancfgView> findByProperty(String propertyName1, String propertyName2, Object value1, Object value2);
	public HntbhzziduancfgView findByGprsbhandleixin(String gprsbh, String leixin);
}



