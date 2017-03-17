package com.mss.shtoone.persistence;


import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Shaifenziduancfg;
import com.mss.shtoone.domain.Shuiwenziduancfg;



@Repository
public interface ShaifenziduancfgDAO extends GenericDAO<Shaifenziduancfg, Integer> {
	public Shaifenziduancfg findByGprsbhandleixin(String gprsbh, String leixin);
	
}



