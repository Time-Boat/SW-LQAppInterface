package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.persistence.ShuiwenziduancfgViewDAO;
@Repository
public class ShuiwenziduancfgViewHibernateDAO extends GenericHibernateDAO<ShuiwenziduancfgView, Integer> implements ShuiwenziduancfgViewDAO {

	@Override
	public ShuiwenziduancfgView findByGprsbhandleixin(String shebeibianhao,String leixing) {
		List<ShuiwenziduancfgView> hlist = find("from ShuiwenziduancfgView as model where model.gprsbianhao='"+shebeibianhao+"' and model.leixing='"+leixing+"'");
		if (hlist.size()>0) {
			return (ShuiwenziduancfgView) hlist.get(0);
		} else {
			return null;
		}	
	}


}
