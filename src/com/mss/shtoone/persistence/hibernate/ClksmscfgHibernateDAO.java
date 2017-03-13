package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Clksmscfg;
import com.mss.shtoone.persistence.ClksmscfgDAO;

@Repository
public class ClksmscfgHibernateDAO extends GenericHibernateDAO<Clksmscfg, String> implements ClksmscfgDAO {
	
	@Override
	public Clksmscfg findByGprsbhandleixin(String gprsbh, String leixin) {
		String queryString = "from Clksmscfg as model where model.shebeibianhao=? and model.rank=?";
		List<Clksmscfg> hlist = find(queryString, gprsbh, leixin);
		if (hlist.size()>0) {
			return (Clksmscfg) hlist.get(0);
		} else {
			return null;
		}	
	}
}



