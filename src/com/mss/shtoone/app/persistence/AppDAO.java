package com.mss.shtoone.app.persistence;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.app.domain.AppInterfaceChaobiaoEntity;
import com.mss.shtoone.app.domain.AppLoginLogEntity;
import com.mss.shtoone.persistence.GenericDAO;

@Repository
public interface AppDAO extends GenericDAO<AppLoginLogEntity, Integer>{
	
	public Map<String,String> getCbcz(String startTime, String endTime, Integer biaoduan, Integer xiangmubu, String shebeibianhao);
	
	public Map<String,String> getLqCbcz(String startTime, String endTime, Integer biaoduan, Integer xiangmubu);
	
	public List<AppInterfaceChaobiaoEntity> lqsmstongji(String startTime,String endTime,Integer biaoduan,Integer xiangmubu,String shebeibianhao,
			String fn,Integer bsid,Integer fzlx);

//	public void departTree(List list, String queryFieldNameByUserType, String biaoshiid);
}
