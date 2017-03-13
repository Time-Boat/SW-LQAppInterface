package com.mss.shtoone.persistence.hibernate;



import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.HandSet;
import com.mss.shtoone.persistence.HandSetDAO;

@Repository
public class HandSetHibernateDAO extends GenericHibernateDAO<HandSet, Integer> implements
		HandSetDAO {

	
}
