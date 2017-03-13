package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.UserTestView;

@Repository
public interface UserTestViewDAO extends GenericDAO<UserTestView, Integer> {
	public GenericPageMode viewlist(String startTimeOne, String endTimeOne, String testname, int offset, int pagesize);
}



