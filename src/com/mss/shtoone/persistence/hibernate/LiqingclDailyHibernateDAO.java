package com.mss.shtoone.persistence.hibernate;


import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.LiqingclDaily;
import com.mss.shtoone.persistence.LiqingclDailyDAO;



@Repository
public class LiqingclDailyHibernateDAO extends GenericHibernateDAO<LiqingclDaily, Integer> implements
LiqingclDailyDAO {

}



