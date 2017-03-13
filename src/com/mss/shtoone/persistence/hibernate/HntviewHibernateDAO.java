package com.mss.shtoone.persistence.hibernate;



import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Hntview;
import com.mss.shtoone.persistence.HntviewDAO;


@Repository
public class HntviewHibernateDAO extends
		GenericHibernateDAO<Hntview, Integer> implements HntviewDAO {
	
}
