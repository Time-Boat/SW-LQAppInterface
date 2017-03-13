package com.mss.shtoone.persistence.hibernate;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealChuliaokouTemperaturedataView;
import com.mss.shtoone.persistence.TopRealChuliaokouTemperaturedataViewDAO;

@Repository
public class TopRealChuliaokouTemperaturedataViewHibernateDAO extends GenericHibernateDAO<TopRealChuliaokouTemperaturedataView, Integer> implements TopRealChuliaokouTemperaturedataViewDAO {
	@Override
	public List<TopRealChuliaokouTemperaturedataView> findTop() {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from TopRealChuliaokouTemperaturedataView");
		query.setMaxResults(1);	
		return query.list();
	}
}



