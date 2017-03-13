package com.mss.shtoone.persistence;


import java.util.List;
import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;

@Repository
public interface TopRealMaxShuiwenxixxViewDAO extends GenericDAO<TopRealMaxShuiwenxixxView, Integer> {
	public List<TopRealMaxShuiwenxixxView> findByProperty(String propertyName, Object value);
	public TopRealMaxShuiwenxixxView findByGprsbianhao(String gprsbh);
	public List<TopRealMaxShuiwenxixxView> findTop();
	public List<TopRealMaxShuiwenxixxView> findByDateTime(String nowDateTime);
}



