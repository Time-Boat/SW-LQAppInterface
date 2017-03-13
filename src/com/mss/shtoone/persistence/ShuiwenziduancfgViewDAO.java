package com.mss.shtoone.persistence;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.ShuiwenziduancfgView;


@Repository
public interface ShuiwenziduancfgViewDAO extends GenericDAO<ShuiwenziduancfgView, Integer>{

	public ShuiwenziduancfgView findByGprsbhandleixin(String shebeibianhao, String leixing);

}
