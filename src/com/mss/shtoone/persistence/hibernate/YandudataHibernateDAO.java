package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Yandudata;
import com.mss.shtoone.persistence.YandudataDAO;

@Repository
public class YandudataHibernateDAO extends GenericHibernateDAO<Yandudata, Integer> implements
YandudataDAO {
	static Log logger = LogFactory.getLog(YandudataHibernateDAO.class);
	@Override
	public Yandudata getYanDuData(String date_time){
		String queryString = "from Yandudata as t where t.date_time = '"+date_time+"'";
		List<Yandudata> list = find(queryString);
		Yandudata yanduData = null;
		if(!list.isEmpty()){
			yanduData = (Yandudata)list.get(0);
		}
        return yanduData;
	}
	@Override
	public List<Yandudata> GetYanDuDataTimeList(){
		String queryString = "from Yandudata order by date_time desc";
		return find(queryString);
	}
}



