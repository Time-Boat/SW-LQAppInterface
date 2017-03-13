package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TophunnintuView;


@Repository
public interface TophunnintuViewDAO extends GenericDAO<TophunnintuView, Integer> {
	public List<TophunnintuView> findByProperty(String propertyName, Object value);
	public List<TophunnintuView> findRealMaxByProperty(String propertyName, Object value);
	public TophunnintuView findByGprsbianhao(String gprsbh);
	public List<TophunnintuView> findBySql(String sql);
	public List<TophunnintuView> findAllByOrder(Integer biaoduan, Integer xiangmubu, String shebeibianhao);
	public List<TophunnintuView> findJzbw(Integer biaoduan, Integer xiangmubu, String shebeibianhao);
	public List<TophunnintuView> findAllByField(String field);
	public TophunnintuView findToponehntview(String sbbh, String gcmc, String sgbw, String sgdd, String biaoduanid, String xiangmubuid, String zuoyeduiid);
}



