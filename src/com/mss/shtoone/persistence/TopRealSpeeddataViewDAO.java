package com.mss.shtoone.persistence;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealSpeeddataView;


@Repository
public interface TopRealSpeeddataViewDAO extends GenericDAO<TopRealSpeeddataView, Integer> {
	public List<TopRealSpeeddataView> findTop();
}



