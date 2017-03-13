package com.mss.shtoone.persistence;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Biaoduanxinxi;

@Repository
public interface BiaoduanDAO extends GenericDAO<Biaoduanxinxi, Integer> {
	public void executeUpdate(String sql) throws Exception;
	public void executeUpdate(String sql, ArrayList<Biaoduanxinxi> imagelist) throws Exception;
	public ResultSetMetaData queryMetaData(String sql);
}



