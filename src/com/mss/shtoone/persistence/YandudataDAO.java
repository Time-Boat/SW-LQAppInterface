package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Yandudata;



@Repository
public interface YandudataDAO extends GenericDAO<Yandudata, Integer> {
	public Yandudata getYanDuData(String date_time);
	public List<Yandudata> GetYanDuDataTimeList();

}



