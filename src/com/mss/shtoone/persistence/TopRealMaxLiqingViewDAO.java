package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealMaxLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;



@Repository
public interface TopRealMaxLiqingViewDAO extends GenericDAO<TopRealMaxLiqingView, Integer> {
	public List<TopRealMaxLiqingView> findTop();
	public List<TopRealMaxLiqingView> findByProperty(String propertyName, Object value);
}



