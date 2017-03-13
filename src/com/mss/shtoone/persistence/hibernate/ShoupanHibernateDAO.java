package com.mss.shtoone.persistence.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Shoupan;
import com.mss.shtoone.persistence.ShoupanDAO;

@Repository
public class ShoupanHibernateDAO extends GenericHibernateDAO<Shoupan,Integer> implements ShoupanDAO {
	@Override
	public Shoupan getShoupanbybianhao(String shebeibianhao){
		List<Shoupan> bhzlist=this.find("from Shoupan where shebeibianhao=?", shebeibianhao);
		if (bhzlist.size()>0) {
			return bhzlist.get(0);
		} else {
            return null;
		}
	}
}
