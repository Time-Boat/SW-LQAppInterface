package com.mss.shtoone.persistence.hibernate;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealMaxLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.persistence.TopRealMaxLiqingViewDAO;

@Repository
public class TopRealMaxLiqingViewHibernateDAO extends GenericHibernateDAO<TopRealMaxLiqingView, Integer> implements
TopRealMaxLiqingViewDAO {
	@Override
	public List<TopRealMaxLiqingView> findTop() {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from TopRealMaxLiqingView ORDER BY bianhao DESC");
		query.setMaxResults(1);	
		return query.list();
	}
	
	@Override
	public List<TopRealMaxLiqingView> findByProperty(String propertyName, Object value) {
		String queryString = "from TopRealMaxLiqingView as model where model."
			+ propertyName + "= ?";
        return find(queryString, value);
	}
}



