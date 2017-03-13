package com.mss.shtoone.persistence.hibernate;


import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.ShuiwenclDaily;
import com.mss.shtoone.persistence.ShuiwenclDailyDAO;



@Repository
public class ShuiwenclDailyHibernateDAO extends GenericHibernateDAO<ShuiwenclDaily, Integer> implements
ShuiwenclDailyDAO {

}



