package com.mss.shtoone.persistence.hibernate;


import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Shaifenshiyan;
import com.mss.shtoone.persistence.ShaifenshiyanDAO;



@Repository
public class ShaifenshiyanHibernateDAO extends GenericHibernateDAO<Shaifenshiyan, Integer> implements
ShaifenshiyanDAO {

}



