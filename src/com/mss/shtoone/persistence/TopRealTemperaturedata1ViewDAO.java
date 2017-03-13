package com.mss.shtoone.persistence;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealTemperaturedata1View;


@Repository
public interface TopRealTemperaturedata1ViewDAO extends GenericDAO<TopRealTemperaturedata1View, Integer> {
	public List<TopRealTemperaturedata1View> findTop();
}



