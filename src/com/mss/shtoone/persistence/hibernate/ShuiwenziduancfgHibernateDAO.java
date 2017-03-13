package com.mss.shtoone.persistence.hibernate;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.persistence.ShuiwenziduancfgDAO;
@Repository
public class ShuiwenziduancfgHibernateDAO extends GenericHibernateDAO<Shuiwenziduancfg, Integer> implements ShuiwenziduancfgDAO {

	@Override
	public Shuiwenziduancfg findByGprsbhandleixin(String gprsbh, String leixin) {
		List<Shuiwenziduancfg> hlist = find("from Shuiwenziduancfg as model where model.gprsbianhao='"+gprsbh+"' and model.leixing='"+leixin+"'");
		if (hlist.size()>0) {
			return (Shuiwenziduancfg) hlist.get(0);
		} else {
			return null;
		}	
	}


	
}
