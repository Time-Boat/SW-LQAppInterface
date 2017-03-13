package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Sessionupdatetime;

@Repository
public interface SessionupdatetimeDAO extends GenericDAO<Sessionupdatetime, Integer> {
	public Sessionupdatetime findbyidbh(String sid, String sbh, String updatetype);
}



