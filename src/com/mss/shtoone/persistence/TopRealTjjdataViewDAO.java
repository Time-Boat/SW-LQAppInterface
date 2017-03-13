package com.mss.shtoone.persistence;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealTjjdataView;


@Repository
public interface TopRealTjjdataViewDAO extends GenericDAO<TopRealTjjdataView, Integer> {
	public List<TopRealTjjdataView> findTop();
}



