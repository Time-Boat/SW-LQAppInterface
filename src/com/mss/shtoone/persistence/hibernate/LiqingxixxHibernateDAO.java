package com.mss.shtoone.persistence.hibernate;


import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Liqingxixx;
import com.mss.shtoone.persistence.LiqingxixxDAO;


@Repository
public class LiqingxixxHibernateDAO extends GenericHibernateDAO<Liqingxixx, Integer> implements
LiqingxixxDAO {

}



