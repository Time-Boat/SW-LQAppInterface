package com.mss.shtoone.persistence.hibernate;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealSpeeddata1View;
import com.mss.shtoone.persistence.TopRealSpeeddata1ViewDAO;

@Repository
public class TopRealSpeeddata1ViewHibernateDAO extends GenericHibernateDAO<TopRealSpeeddata1View, Integer> implements
TopRealSpeeddata1ViewDAO {
	@Override
	public List<TopRealSpeeddata1View> findTop() {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from TopRealSpeeddata1View");
		query.setMaxResults(1);	
		return query.list();
	}
}



