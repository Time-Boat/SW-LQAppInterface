package com.mss.shtoone.persistence.hibernate;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.persistence.LiqingziduancfgViewDAO;


@Repository
public class LiqingziduancfgViewHibernateDAO extends GenericHibernateDAO<LiqingziduancfgView, Integer> implements
LiqingziduancfgViewDAO {
	@Override
	public LiqingziduancfgView findByGprsbhandleixin(String gprsbh, String leixin) {
		String queryString = "from LiqingziduancfgView as model where model.gprsbianhao=? and model.leixin=?";
		List<LiqingziduancfgView> hlist = find(queryString, gprsbh, leixin);
		if (hlist.size()>0) {
			return (LiqingziduancfgView) hlist.get(0);
		} else {
			return null;
		}	
	}
}



