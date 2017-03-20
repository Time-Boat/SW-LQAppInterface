package com.mss.shtoone.app.persistence;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.app.domain.AppLoginLogEntity;
import com.mss.shtoone.persistence.GenericDAO;

@Repository
public interface AppDAO extends GenericDAO<AppLoginLogEntity, Integer> {

	public Map<String, String> getCbcz(String startTime, String endTime, Integer biaoduan, Integer xiangmubu,
			String shebeibianhao);

	public Integer executeUpdate(String sql) throws Exception;

}
