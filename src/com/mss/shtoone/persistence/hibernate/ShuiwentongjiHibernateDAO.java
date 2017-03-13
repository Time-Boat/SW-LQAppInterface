package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Shuiwentongji;
import com.mss.shtoone.persistence.ShuiwentongjiDAO;

@Repository
public class ShuiwentongjiHibernateDAO extends GenericHibernateDAO<Shuiwentongji,Integer> implements ShuiwentongjiDAO{

}
