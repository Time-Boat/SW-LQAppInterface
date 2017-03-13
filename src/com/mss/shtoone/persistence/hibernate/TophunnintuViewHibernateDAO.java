package com.mss.shtoone.persistence.hibernate;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TophunnintuView;
import com.mss.shtoone.persistence.TophunnintuViewDAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class TophunnintuViewHibernateDAO extends GenericHibernateDAO<TophunnintuView, Integer> implements
TophunnintuViewDAO {

	@Override
	public TophunnintuView findByGprsbianhao(String gprsbh) {
		List<TophunnintuView> hlist = findByProperty("shebeibianhao", gprsbh);
		if (hlist.size()>0) {
			return (TophunnintuView) hlist.get(0);
		} else {
			return null;
		}	
	}

	@Override
	public List<TophunnintuView> findByProperty(String propertyName, Object value) {
		String queryString = "from TophunnintuView as model where model."
			+ propertyName + "= ?";
		if (propertyName.equalsIgnoreCase("all")) {
			return find("from TophunnintuView");
		} else {
			return find(queryString, value);
		}
		
        
	}	
	
	@Override
	public List<TophunnintuView> findRealMaxByProperty(String propertyName, Object value) {
		String queryString = "from TopRealMaxhunnintuView as model where model."
			+ propertyName + "= ?";
        return find(queryString, value);
	}
	
	@Override
	public List<TophunnintuView> findBySql(String sql) {
		Query query = getTemplate().getSessionFactory().openSession().createSQLQuery(sql);
		return query.list();	
	}
	
	@Override
	public List<TophunnintuView> findAllByOrder(Integer biaoduan, Integer xiangmubu, String shebeibianhao){
		String querySql = "SELECT gongchengmingcheng FROM xiangxixx GROUP BY gongchengmingcheng";
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			querySql = "SELECT gongchengmingcheng FROM xiangxixx where shebeibianhao='"+shebeibianhao+"' GROUP BY gongchengmingcheng";
		} else {
			if (null != xiangmubu) {
				querySql = "SELECT gongchengmingcheng FROM HunnintuView where xiangmubuid="+xiangmubu+" GROUP BY gongchengmingcheng";
			} else {
				if (null != biaoduan) {
					querySql = "SELECT gongchengmingcheng FROM HunnintuView where biaoduanid="+biaoduan+" GROUP BY gongchengmingcheng";
				}
			}			
		}
		Query query = getTemplate().getSessionFactory().openSession().createSQLQuery(querySql);
		return query.list();
	}
	
	@Override
	public List<TophunnintuView> findJzbw(Integer biaoduan, Integer xiangmubu, String shebeibianhao){
		String querySql = "SELECT jiaozuobuwei FROM xiangxixx GROUP BY jiaozuobuwei";
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			querySql = "SELECT jiaozuobuwei FROM xiangxixx where shebeibianhao='"+shebeibianhao+"' GROUP BY jiaozuobuwei";
		} else {
			if (null != xiangmubu) {
				querySql = "SELECT jiaozuobuwei FROM HunnintuView where xiangmubuid="+xiangmubu+" GROUP BY jiaozuobuwei";
			} else {
				if (null != biaoduan) {
					querySql = "SELECT jiaozuobuwei FROM HunnintuView where biaoduanid="+biaoduan+" GROUP BY jiaozuobuwei";
				}
			}			
		}
		Query query = getTemplate().getSessionFactory().openSession().createSQLQuery(querySql);		
		return query.list();
	}

	@Override
	public List<TophunnintuView> findAllByField(String field){
		String sql = "SELECT "+field+" FROM HunnintuView GROUP BY "+field;
		Query query = getTemplate().getSessionFactory().openSession().createSQLQuery(sql);
		return query.list();
	}
	
	@Override
	public TophunnintuView findToponehntview(String sbbh, String gcmc,
			String sgbw, String sgdd, String biaoduanid, String xiangmubuid, String zuoyeduiid) {
		TophunnintuView tv = null;
		StringBuffer sqltest = new StringBuffer();
		sqltest.append("FROM TophunnintuView where 1=1");
		   if(StringUtil.Null2Blank(sbbh).length()>0){			
			    sqltest.append(" and shebeibianhao='"+sbbh+"'");
		   }
			if(StringUtil.Null2Blank(gcmc).length()>0){	
				sqltest.append(" and gongchengmingcheng='"+gcmc+"'");
			}
			if(StringUtil.Null2Blank(sgbw).length()>0){	
				sqltest.append(" and jiaozuobuwei='"+sgbw+"'");
			}
			if(StringUtil.Null2Blank(sgdd).length()>0){	
				sqltest.append(" and sigongdidian='"+sgdd+"'");
			}
			if(StringUtil.Null2Blank(biaoduanid).length()>0){	
				sqltest.append(" and biaoduanid="+biaoduanid);
			}
			if(StringUtil.Null2Blank(xiangmubuid).length()>0){	
				sqltest.append(" and xiangmubuid="+xiangmubuid);
			}
			if(StringUtil.Null2Blank(zuoyeduiid).length()>0){	
				sqltest.append(" and zuoyeduiid="+zuoyeduiid);
			}
			List<TophunnintuView> hlist = find(sqltest.toString());
			if (hlist.size()>0) {
				tv = (TophunnintuView) hlist.get(0);
			} 	
		return tv;
	}
}



