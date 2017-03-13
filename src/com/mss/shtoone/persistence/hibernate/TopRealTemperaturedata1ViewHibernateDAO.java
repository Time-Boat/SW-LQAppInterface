package com.mss.shtoone.persistence.hibernate;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealTemperaturedata1View;
import com.mss.shtoone.persistence.TopRealTemperaturedata1ViewDAO;

@Repository
public class TopRealTemperaturedata1ViewHibernateDAO extends GenericHibernateDAO<TopRealTemperaturedata1View, Integer> implements
TopRealTemperaturedata1ViewDAO {
	@Override
	public List<TopRealTemperaturedata1View> findTop() {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from TopRealTemperaturedata1View");
		query.setMaxResults(1);	
		return query.list();
	}
}



