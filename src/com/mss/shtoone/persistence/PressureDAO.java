package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Mpressure;


@Repository
public interface PressureDAO extends GenericDAO<Mpressure, Integer> {

	List<Mpressure> findByShebeiid(String shebeiid);

}



