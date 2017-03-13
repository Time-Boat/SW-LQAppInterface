package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Clksmscfg;

@Repository
public interface ClksmscfgDAO extends GenericDAO<Clksmscfg, String> {
	public Clksmscfg findByGprsbhandleixin(String gprsbh, String leixin);
}



