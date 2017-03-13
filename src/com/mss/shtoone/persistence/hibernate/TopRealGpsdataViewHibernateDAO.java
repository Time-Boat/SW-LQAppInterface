package com.mss.shtoone.persistence.hibernate;



import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealGpsdataView;
import com.mss.shtoone.persistence.TopRealGpsdataViewDAO;

@Repository
public class TopRealGpsdataViewHibernateDAO extends GenericHibernateDAO<TopRealGpsdataView, Integer> implements
TopRealGpsdataViewDAO {
}



