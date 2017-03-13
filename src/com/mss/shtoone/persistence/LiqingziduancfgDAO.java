package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Liqingziduancfg;

@Repository
public interface LiqingziduancfgDAO extends GenericDAO<Liqingziduancfg, Integer> {
	public Liqingziduancfg findByGprsbhandleixin(String gprsbh, String leixin);
}



