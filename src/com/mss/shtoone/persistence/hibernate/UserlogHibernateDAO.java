package com.mss.shtoone.persistence.hibernate;



import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Userlog;
import com.mss.shtoone.persistence.UserlogDAO;


@Repository
public class UserlogHibernateDAO extends GenericHibernateDAO<Userlog, Integer> implements
UserlogDAO {

	
}
