package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Hntbhzziduancfg;
import com.mss.shtoone.persistence.HntbhzCfgDAO;


@Repository
public class HntbhzCfgHibernateDAO extends GenericHibernateDAO<Hntbhzziduancfg, Integer> implements
HntbhzCfgDAO {
	
	@Override
	public Hntbhzziduancfg findByGprsbhandleixin(String gprsbh, String leixin) {
		String queryString = "from Hntbhzziduancfg as model where model.gprsbianhao=? and model.leixin=?";
		List<Hntbhzziduancfg> hlist = find(queryString, gprsbh, leixin);
		if (hlist.size()>0) {
			return (Hntbhzziduancfg) hlist.get(0);
		} else {
			return null;
		}	
	}

}



