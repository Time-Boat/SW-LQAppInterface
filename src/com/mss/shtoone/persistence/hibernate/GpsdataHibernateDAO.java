package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Gpsdata;
import com.mss.shtoone.persistence.GpsdataDAO;

@Repository
public class GpsdataHibernateDAO extends GenericHibernateDAO<Gpsdata, Integer> implements
GpsdataDAO {
	
	@Override
	public Gpsdata getTopGpsdata(String sbbh) {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from Gpsdata as model where model.gpsno='"+sbbh+"' order by gpsid DESC");
		query.setMaxResults(1);	
		List<Gpsdata> gpslist = (List<Gpsdata>)query.list();
		if (gpslist.size()>0) {
			return gpslist.get(0);
		} else {
            return null;
		}
	}
}



