package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Xiangxixx;
import com.mss.shtoone.persistence.XiangxixxDAO;

@Repository
public class XiangxixxHibernateDAO extends GenericHibernateDAO<Xiangxixx, Integer> implements
XiangxixxDAO {

}



