package com.mss.shtoone.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.LiqingziduancfgView;



@Repository
public interface LiqingViewDAO extends GenericDAO<LiqingView, Integer> {
	public GenericPageMode lqviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize, int queryalldata, String peifan);
	
	public GenericPageMode lqphbviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize, String peifan);
	
	public GenericPageMode lqmanualphbviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize, String peifan);
	
	
	public GenericPageMode lqchaobiaoviewlist(LiqingziduancfgView lqisshow, Integer chaobiaolx, String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	
	public GenericPageMode lqchaobiaomanualviewlist(LiqingziduancfgView lqisshow, Integer chaobiaolx, String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize,Integer cllx,String bianhao);

	public List<LiqingView> lqzhfxlist(String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid);
	
	public LiqingView lqstatisticsinfo();
	
	public List<LiqingView> lqcnfxlist(String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid);
	public LiqingView lqcnfxdetail(String startTime,String endTime,String shebeibianhao, 
			String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid);
	public LiqingView lqcnfxlilundetail(String startTime,String endTime,String shebeibianhao, 
			String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid);
	
	public GenericPageMode lqviewjieguolist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	
	public GenericPageMode lqviewwuchalist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid,int offset, int pagesize, String peifan);
	
	public LiqingphbView lqmateriallist(String startTime,
			String endTime,	String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid);
	
	public List<LiqingView> lqsclhslist(String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid, String peifan);
	
	public List<LiqingView> lqscsjjcphb(String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid, String peifan);
	
	public List<LiqingView> lqscsjjcmanualphb(String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid, String peifan);
	
	public GenericPageMode viewrunstatelist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu,
			String fn, int bsid,int offset, int pagesize);
	//沥青拌和时间查询
	public GenericPageMode lqsjviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	
	public GenericPageMode lqllviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize);
	
	//生产管理中查询最新的前10条数据
	public List<LiqingView> findTop10(String shebeibianhao);
	
	public GenericPageMode lqmanualphbviewwuchalist(String shebeibianhao,String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize,String peifan);
}



