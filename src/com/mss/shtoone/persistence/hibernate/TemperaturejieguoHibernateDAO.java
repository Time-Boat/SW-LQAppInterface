package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Temperaturejieguo;
import com.mss.shtoone.persistence.TemperaturejieguoDAO;

@Repository
public class TemperaturejieguoHibernateDAO extends GenericHibernateDAO<Temperaturejieguo, Integer> implements
TemperaturejieguoDAO {

}



