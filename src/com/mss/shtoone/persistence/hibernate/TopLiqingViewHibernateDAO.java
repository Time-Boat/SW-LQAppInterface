package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.persistence.TopLiqingViewDAO;

@Repository
public class TopLiqingViewHibernateDAO extends GenericHibernateDAO<TopLiqingView, Integer> implements
TopLiqingViewDAO {
	@Override
	public List<TopLiqingView> findByProperty(String propertyName, Object value) {
		String queryString = "from TopLiqingView as model where model."+ propertyName + "= ?";
		if (propertyName.equalsIgnoreCase("all")) {
			return find("from TopLiqingView");
		} else {
			return find(queryString, value);
		}
	}	
	
	@Override
	public TopLiqingView findByGprsbianhao(String gprsbh) {
		List hlist = findByProperty("shebeibianhao", gprsbh);
		if (hlist.size()>0) {
			return (TopLiqingView) hlist.get(0);
		} else {
			return null;
		}	
	}
}



