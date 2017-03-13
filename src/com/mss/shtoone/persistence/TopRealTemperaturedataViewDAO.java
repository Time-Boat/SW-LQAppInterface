package com.mss.shtoone.persistence;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealTemperaturedataView;


@Repository
public interface TopRealTemperaturedataViewDAO extends GenericDAO<TopRealTemperaturedataView, Integer> {
	public List<TopRealTemperaturedataView> findTop();
}



