package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Hntbhzziduancfg;

@Repository
public interface HntbhzCfgDAO extends GenericDAO<Hntbhzziduancfg, Integer> {
	public Hntbhzziduancfg findByGprsbhandleixin(String gprsbh, String leixin);
}



