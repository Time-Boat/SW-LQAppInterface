package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealTjjdataView;
import com.mss.shtoone.persistence.TopRealTjjdataViewDAO;

@Repository
public class TopRealTjjdataViewHibernateDAO extends GenericHibernateDAO<TopRealTjjdataView, Integer> implements
TopRealTjjdataViewDAO {
	@Override
	public List<TopRealTjjdataView> findTop() {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from TopRealSpeeddataView");
		query.setMaxResults(1);	
		return query.list();
	}
}



