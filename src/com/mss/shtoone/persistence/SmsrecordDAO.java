package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Smsrecord;


@Repository
public interface SmsrecordDAO extends GenericDAO<Smsrecord, Integer> {
}
