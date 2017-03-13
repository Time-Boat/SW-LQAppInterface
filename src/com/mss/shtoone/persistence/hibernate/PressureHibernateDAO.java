package com.mss.shtoone.persistence.hibernate;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Mpressure;
import com.mss.shtoone.persistence.PressureDAO;

@Repository
public class PressureHibernateDAO extends GenericHibernateDAO<Mpressure, Integer> implements
PressureDAO {

	@Override
	public List<Mpressure> findByShebeiid(String shebeiid) {
		return find("from mpressure where yali=?", shebeiid);
	}


}



