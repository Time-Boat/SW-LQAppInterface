package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.persistence.BanhezhanDAO;


@Repository
public class BanhezhanHibernateDAO extends GenericHibernateDAO<Banhezhanxinxi, Integer> implements
BanhezhanDAO {
	
	@Override
	public Banhezhanxinxi getBhzbybianhao(String shebeibianhao) {
		List<Banhezhanxinxi> bhzlist=this.find("from Banhezhanxinxi where gprsbianhao=?", shebeibianhao);
		if (bhzlist.size()>0) {
			return bhzlist.get(0);
		} else {
            return null;
		}
	}
}



