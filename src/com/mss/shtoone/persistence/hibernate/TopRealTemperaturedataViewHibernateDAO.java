package com.mss.shtoone.persistence.hibernate;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealTemperaturedataView;
import com.mss.shtoone.persistence.TopRealTemperaturedataViewDAO;

@Repository
public class TopRealTemperaturedataViewHibernateDAO extends GenericHibernateDAO<TopRealTemperaturedataView, Integer> implements
TopRealTemperaturedataViewDAO {
	@Override
	public List<TopRealTemperaturedataView> findTop() {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from TopRealTemperaturedataView");
		query.setMaxResults(1);	
		return query.list();
	}
}



