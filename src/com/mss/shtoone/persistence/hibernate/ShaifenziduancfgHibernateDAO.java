package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Shaifenziduancfg;
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.persistence.ShaifenziduancfgDAO;

@Repository
public class ShaifenziduancfgHibernateDAO extends GenericHibernateDAO<Shaifenziduancfg, Integer> 
			implements ShaifenziduancfgDAO {
	
	@Override
	public Shaifenziduancfg findByGprsbhandleixin(String gprsbh, String leixin) {
		List<Shaifenziduancfg> hlist = find("from Shaifenziduancfg as model where model.gprsbianhao='"+gprsbh+"' and model.leixing='"+leixin+"'");
		if (hlist.size()>0) {
			return (Shaifenziduancfg) hlist.get(0);
		} else {
			return null;
		}	
	}
	
}
