package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Resource;
import com.mss.shtoone.persistence.ResourceDAO;

@Repository
public class ResourceHibernateDAO extends GenericHibernateDAO<Resource, Integer> implements
		ResourceDAO {

}



