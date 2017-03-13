package com.mss.shtoone.persistence.hibernate;


import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Cinstru;
import com.mss.shtoone.persistence.InstruDAO;

@Repository
public class InstruHibernateDAO extends GenericHibernateDAO<Cinstru, Integer> implements
InstruDAO {

}



