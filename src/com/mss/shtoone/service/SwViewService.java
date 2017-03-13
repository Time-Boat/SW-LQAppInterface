package com.mss.shtoone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;

import com.mss.shtoone.persistence.LiqingziduancfgDAO;
import com.mss.shtoone.persistence.ShuiwenmanualphbViewDAO;
import com.mss.shtoone.persistence.ShuiwenphbViewDAO;
import com.mss.shtoone.persistence.TopRealMaxShuiwenxixxViewDAO;


@Service
public class SwViewService {

	@Autowired
	private ShuiwenphbViewDAO swphbViewDao;
	
	@Autowired
	private ShuiwenmanualphbViewDAO swmanualphbViewDao;
	
	@Autowired
	private TopRealMaxShuiwenxixxViewDAO TopRealMaxSwViewDao;
	
	@Autowired
	private LiqingziduancfgDAO lqcfgDAO;
	
	
	public GenericPageMode swwuchamanualphbviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize){
		return swmanualphbViewDao.swwuchamanualphbviewlist(shebeibianhao, startTimeOne, endTimeOne, biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	
	public List<TopRealMaxShuiwenxixxView> findByDateTime(String nowDateTime){
		return TopRealMaxSwViewDao.findByDateTime(nowDateTime);
	}
	
	public Liqingziduancfg getDefaultsmshighlqcfg(String gprsbh) {
		Liqingziduancfg lqsmshighcfg = new Liqingziduancfg();
		lqsmshighcfg.setLeixin("6");
		lqsmshighcfg.setGprsbianhao(gprsbh);
		lqsmshighcfg.setSjg1("3");
		lqsmshighcfg.setSjg2("3");
		lqsmshighcfg.setSjg3("3");
		lqsmshighcfg.setSjg4("3");
		lqsmshighcfg.setSjg5("3");
		lqsmshighcfg.setSjg6("3");
		lqsmshighcfg.setSjg7("3");
		lqsmshighcfg.setSjf1("2");
		lqsmshighcfg.setSjf2("2");
		lqsmshighcfg.setSjlq("2");
		lqsmshighcfg.setSjtjj("2");
		lqsmshighcfg.setSjysb("2");
		return lqsmshighcfg;
	}
	
	
	public GenericPageMode swchaobiaomanualviewlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize,Integer chaobiaolx,ShuiwenziduancfgView swisshow,Integer cllx,String bianhao) {
		return swmanualphbViewDao.swchaobiaomanualviewlist(shebeibianhao, startTimeOne, endTimeOne, biaoduan, xiangmubu,
				fn, bsid, offset, pagesize,chaobiaolx,swisshow,cllx,bianhao);
	}
	
	public ShuiwenphbView swmateriallist(String startTime,String endTime,String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid){
		return swmanualphbViewDao.swmateriallist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,fn,bsid);
	}
}
