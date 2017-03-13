package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Temperaturedata;
import com.mss.shtoone.persistence.TemperaturedataDAO;
@Repository
public class TemperaturedataHibernateDAO extends GenericHibernateDAO<Temperaturedata, Integer> implements TemperaturedataDAO{
	
}
