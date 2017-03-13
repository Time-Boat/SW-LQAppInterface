package com.mss.shtoone.persistence.hibernate;



import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Smsrecord;
import com.mss.shtoone.persistence.SmsrecordDAO;

@Repository
public class SmsrecordHibernateDAO extends GenericHibernateDAO<Smsrecord, Integer> implements
SmsrecordDAO {
}
