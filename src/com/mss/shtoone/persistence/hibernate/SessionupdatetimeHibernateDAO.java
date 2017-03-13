package com.mss.shtoone.persistence.hibernate;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Sessionupdatetime;
import com.mss.shtoone.persistence.SessionupdatetimeDAO;

@Repository
public class SessionupdatetimeHibernateDAO extends GenericHibernateDAO<Sessionupdatetime, Integer> implements
SessionupdatetimeDAO {

	@Override
	public Sessionupdatetime findbyidbh(String sid, String sbh, String updatetype) {
		List<Sessionupdatetime> sl = find("from Sessionupdatetime where sessionid=? and shebeibianhao=? and updatetype=?", sid, sbh, updatetype);
		if (sl.size()>0) {
			return (Sessionupdatetime)sl.get(0);
		} else {
			return null;
		}
	}

}



