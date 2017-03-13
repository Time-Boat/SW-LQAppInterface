package com.mss.shtoone.persistence.hibernate;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.SpeeddataView;
import com.mss.shtoone.persistence.SpeeddataViewDAO;

@Repository
public class SpeeddataViewHibernateDAO extends GenericHibernateDAO<SpeeddataView, Integer> implements
SpeeddataViewDAO {

}



