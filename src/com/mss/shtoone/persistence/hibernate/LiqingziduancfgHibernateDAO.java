package com.mss.shtoone.persistence.hibernate;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.persistence.LiqingziduancfgDAO;


@Repository
public class LiqingziduancfgHibernateDAO extends GenericHibernateDAO<Liqingziduancfg, Integer> implements
LiqingziduancfgDAO {
	@Override
	public Liqingziduancfg findByGprsbhandleixin(String gprsbh, String leixin) {
		String queryString = "from Liqingziduancfg as model where model.gprsbianhao=? and model.leixin=?";
		List<Liqingziduancfg> hlist = find(queryString, gprsbh, leixin);
		if (hlist.size()>0) {
			return (Liqingziduancfg) hlist.get(0);
		} else {
			return null;
		}	
	}
}



