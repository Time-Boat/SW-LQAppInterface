package com.mss.shtoone.persistence.hibernate;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealSpeeddataView;
import com.mss.shtoone.persistence.TopRealSpeeddataViewDAO;

@Repository
public class TopRealSpeeddataViewHibernateDAO extends GenericHibernateDAO<TopRealSpeeddataView, Integer> implements
TopRealSpeeddataViewDAO {
	@Override
	public List<TopRealSpeeddataView> findTop() {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from TopRealSpeeddataView");
		query.setMaxResults(1);	
		return query.list();
	}
}



