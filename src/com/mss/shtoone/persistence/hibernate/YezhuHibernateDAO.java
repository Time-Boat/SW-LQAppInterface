package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.YezhuFile;
import com.mss.shtoone.persistence.YezhuFileDAO;

@Repository
public class YezhuHibernateDAO extends GenericHibernateDAO<YezhuFile, Integer> implements YezhuFileDAO{

}
