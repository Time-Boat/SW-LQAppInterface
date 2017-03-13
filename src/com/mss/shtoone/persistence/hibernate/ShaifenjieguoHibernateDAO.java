package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Shaifenjieguo;
import com.mss.shtoone.persistence.ShaifenjieguoDAO;

@Repository
public class ShaifenjieguoHibernateDAO extends GenericHibernateDAO<Shaifenjieguo, Integer> implements ShaifenjieguoDAO{

}
