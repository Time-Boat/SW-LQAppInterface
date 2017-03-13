package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.ShuiwenxixxView;

@Repository
public interface ShuiwenxixxViewDAO extends GenericDAO<ShuiwenxixxView,Integer>{
	
	//水稳产能分析
	public List<ShuiwenxixxView> swcnfxlist(String startTime,String endTime,String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid);
	
	//水稳统计分析
	public List<ShuiwenxixxView> swsmstongji(String startTime,String endTime,Integer biaoduan,Integer xiangmubu,String shebeibianhao,
			String fn,Integer bsid,int fzlx);
	
	public List<ShuiwenxixxView> swzhfxlist(String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid);

	//首页的饼状图
	public ShuiwenxixxView swstatisticsinfo();
}
