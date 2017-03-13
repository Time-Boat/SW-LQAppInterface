package com.mss.shtoone.persistence;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.TopRealChuliaokouTemperaturedataView;


@Repository
public interface TopRealChuliaokouTemperaturedataViewDAO extends GenericDAO<TopRealChuliaokouTemperaturedataView, Integer> {
	public List<TopRealChuliaokouTemperaturedataView> findTop();
}



