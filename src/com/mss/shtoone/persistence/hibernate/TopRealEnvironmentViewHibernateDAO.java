package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.TopRealEnvironmentView;
import com.mss.shtoone.persistence.TopRealEnvironmentViewDAO;

@Repository
public class TopRealEnvironmentViewHibernateDAO extends GenericHibernateDAO<TopRealEnvironmentView,Integer> implements TopRealEnvironmentViewDAO {
	
}



