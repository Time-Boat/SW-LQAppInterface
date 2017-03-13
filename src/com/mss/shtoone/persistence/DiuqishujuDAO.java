package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Diuqishuju;
import com.mss.shtoone.domain.DiuqishujuPageMode;

@Repository
public interface DiuqishujuDAO extends GenericDAO<Diuqishuju, Integer> {
	public DiuqishujuPageMode viewlist(int offset, int pagesize);
}



