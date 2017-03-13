package com.mss.shtoone.persistence.hibernate;


import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.persistence.LiqingphbViewDAO;


@Repository
public class LiqingphbViewHibernateDAO extends GenericHibernateDAO<LiqingphbView, Integer> implements
LiqingphbViewDAO {

}



