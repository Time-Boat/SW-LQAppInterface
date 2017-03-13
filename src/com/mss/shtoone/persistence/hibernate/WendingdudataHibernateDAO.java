package com.mss.shtoone.persistence.hibernate;



import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Wendingdudata;
import com.mss.shtoone.persistence.WendingdudataDAO;

@Repository
public class WendingdudataHibernateDAO extends GenericHibernateDAO<Wendingdudata, Integer> implements
WendingdudataDAO {
}



