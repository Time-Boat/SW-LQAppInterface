package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Shuiwenziduancfg;

@Repository
public interface ShuiwenziduancfgDAO extends GenericDAO<Shuiwenziduancfg, Integer>{
	public Shuiwenziduancfg findByGprsbhandleixin(String gprsbh, String leixin);
}
