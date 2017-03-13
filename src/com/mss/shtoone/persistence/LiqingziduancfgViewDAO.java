package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.LiqingziduancfgView;



@Repository
public interface LiqingziduancfgViewDAO extends GenericDAO<LiqingziduancfgView, Integer> {
	public LiqingziduancfgView findByGprsbhandleixin(String gprsbh, String leixin);
}



