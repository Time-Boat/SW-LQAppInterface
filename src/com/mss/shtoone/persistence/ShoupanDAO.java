package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Shoupan;



@Repository
public interface ShoupanDAO extends GenericDAO<Shoupan, Integer> {
	public Shoupan getShoupanbybianhao(String shebeibianhao);
}
