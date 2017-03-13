package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopShuiwenView;
import com.mss.shtoone.persistence.TopShuiwenViewDAO;

@Repository
public class TopShuiwenViewHibernateDAO extends GenericHibernateDAO<TopShuiwenView, Integer> implements TopShuiwenViewDAO{

}
