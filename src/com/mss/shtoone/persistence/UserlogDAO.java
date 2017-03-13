package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Userlog;

@Repository
public interface UserlogDAO extends GenericDAO<Userlog, Integer> {

}
