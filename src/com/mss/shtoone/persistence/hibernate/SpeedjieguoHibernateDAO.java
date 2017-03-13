package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Speedjieguo;
import com.mss.shtoone.persistence.SpeedjieguoDAO;

@Repository
public class SpeedjieguoHibernateDAO extends GenericHibernateDAO<Speedjieguo, Integer> implements
SpeedjieguoDAO {

}



