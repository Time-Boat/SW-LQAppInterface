package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TemperaturedataView;
import com.mss.shtoone.persistence.TemperaturedataViewDAO;

@Repository
public class TemperaturedataViewHibernateDAO extends GenericHibernateDAO<TemperaturedataView, Integer> implements
TemperaturedataViewDAO {

}



