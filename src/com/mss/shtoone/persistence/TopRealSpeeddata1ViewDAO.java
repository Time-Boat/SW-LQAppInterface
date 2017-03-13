package com.mss.shtoone.persistence;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealSpeeddata1View;


@Repository
public interface TopRealSpeeddata1ViewDAO extends GenericDAO<TopRealSpeeddata1View, Integer> {
	public List<TopRealSpeeddata1View> findTop();
}



