package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopLiqingView;



@Repository
public interface TopLiqingViewDAO extends GenericDAO<TopLiqingView, Integer> {
	public List<TopLiqingView> findByProperty(String propertyName, Object value);
	public TopLiqingView findByGprsbianhao(String gprsbh);
}



