package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.persistence.ZuoyeduiDAO;


@Repository
public class ZuoyeduiHibernateDAO extends GenericHibernateDAO<Zuoyeduixinxi, Integer> implements
ZuoyeduiDAO {

}



