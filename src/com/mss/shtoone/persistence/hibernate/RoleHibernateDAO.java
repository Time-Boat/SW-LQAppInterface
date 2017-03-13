package com.mss.shtoone.persistence.hibernate;


import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Role;
import com.mss.shtoone.persistence.RoleDAO;

@Repository
public class RoleHibernateDAO extends GenericHibernateDAO<Role, Integer> implements
		RoleDAO {

}



