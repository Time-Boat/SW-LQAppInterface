package com.mss.shtoone.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.persistence.TopRealMaxShuiwenxixxViewDAO;

@Repository
public class TopRealMaxShuiwenxixxViewHibernateDAO extends GenericHibernateDAO<TopRealMaxShuiwenxixxView, Integer> implements
TopRealMaxShuiwenxixxViewDAO {
	@Override
	public List<TopRealMaxShuiwenxixxView> findByProperty(String propertyName, Object value) {
		String queryString = "from TopRealMaxShuiwenxixxView as model where model."
			+ propertyName + "= ?";
        return find(queryString, value);
	}	
	
	@Override
	public TopRealMaxShuiwenxixxView findByGprsbianhao(String gprsbh) {
		List hlist = findByProperty("shebeibianhao", gprsbh);
		if (hlist.size()>0) {
			return (TopRealMaxShuiwenxixxView) hlist.get(0);
		} else {
			return null;
		}	
	}
	
	@Override
	public List findTop() {
		// TODO Auto-generated method stub
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from TopRealMaxShuiwenxixxView ORDER BY bianhao DESC");
		query.setMaxResults(1);	
		return query.list();
	}

	@Override
	public List<TopRealMaxShuiwenxixxView> findByDateTime(String nowDateTime) {
		// TODO Auto-generated method stub
		String queryString = "from TopRealMaxShuiwenxixxView as model";
		List<TopRealMaxShuiwenxixxView> toplist = new ArrayList<TopRealMaxShuiwenxixxView>();
		//String fn = StringUtil.getQueryFieldNameByRequest(request);
		//int bsid = StringUtil.getBiaoshiId(request);
		if (null != nowDateTime) {
			queryString=queryString+" where model.shijian>='"+nowDateTime+"'";
		}
		return find(queryString);
	}
}



