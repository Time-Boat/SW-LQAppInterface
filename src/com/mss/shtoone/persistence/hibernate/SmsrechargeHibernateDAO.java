package com.mss.shtoone.persistence.hibernate;



import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Smsrecharge;
import com.mss.shtoone.persistence.SmsrechargeDAO;

@Repository
public class SmsrechargeHibernateDAO extends GenericHibernateDAO<Smsrecharge, Integer> implements
SmsrechargeDAO {
}
