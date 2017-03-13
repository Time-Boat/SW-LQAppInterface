package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.persistence.HntbhzCfgViewDAO;


@Repository
public class HntbhzCfgViewHibernateDAO extends GenericHibernateDAO<HntbhzziduancfgView, Integer> implements
HntbhzCfgViewDAO {

	@Override
	public List<HntbhzziduancfgView> findByProperty(String propertyName, Object value) {	
		String queryString = "from HntbhzziduancfgView as model where model."+ propertyName + "= ?";
		return find(queryString, value);
	}
	
	@Override
	public List<HntbhzziduancfgView> findByProperty(String propertyName1, String propertyName2, Object value1, Object value2) {	
		String queryString = "from HntbhzziduancfgView as model where model."+ propertyName1 + "= ? and model."+ propertyName2 + "= ?";
		return find(queryString, value1, value2);
	}

	@Override
	public HntbhzziduancfgView findByGprsbhandleixin(String gprsbh, String leixin) {
		String queryString = "from HntbhzziduancfgView as model where model.gprsbianhao=? and model.leixin=?";
		List<HntbhzziduancfgView> hlist = find(queryString, gprsbh, leixin);
		if (hlist.size()>0) {
			return (HntbhzziduancfgView) hlist.get(0);
		} else {
			return null;
		}	
	}


}



