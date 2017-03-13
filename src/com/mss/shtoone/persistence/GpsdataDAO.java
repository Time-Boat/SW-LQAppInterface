package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Gpsdata;


@Repository
public interface GpsdataDAO extends GenericDAO<Gpsdata, Integer> {
	public Gpsdata getTopGpsdata(String sbbh);

}



