package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Top10ysbView;
import com.mss.shtoone.persistence.Top10ysbViewDAO;

@Repository
public class Top10ysbViewHibernateDAO extends GenericHibernateDAO<Top10ysbView, Integer> implements Top10ysbViewDAO {
	@Override
	public List<Top10ysbView> findByProperty(String propertyName, Object value) {
		String queryString = "from Top10ysbView as model where model."
			+ propertyName + "= ? ORDER BY model.bianhao";
		if (propertyName.equalsIgnoreCase("all")) {
			return find("from Top10ysbView");
		} else {
			return find(queryString, value);
		}
	}	
	
	@Override
	public Top10ysbView findByGprsbianhao(String gprsbh) {
		List hlist = findByProperty("shebeibianhao", gprsbh);
		if (hlist.size()>0) {
			return (Top10ysbView) hlist.get(0);
		} else {
			return null;
		}	
	}	
}
