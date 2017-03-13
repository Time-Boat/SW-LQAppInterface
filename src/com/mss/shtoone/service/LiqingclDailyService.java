package com.mss.shtoone.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingclDaily;
import com.mss.shtoone.persistence.LiqingclDailyDAO;
import com.mss.shtoone.persistence.LiqingclDailyViewDAO;

@Service
public class LiqingclDailyService {
	
	@Autowired
	private LiqingclDailyDAO lqdailyDAO;

	@Autowired
	private LiqingclDailyViewDAO lqdailyviewDAO;
	
	public void saveOrUpdate(LiqingclDaily lqdaily){		
		lqdailyDAO.saveOrUpdate(lqdaily);
	}
	
	public void del(int dailyid){
		lqdailyDAO.deleteByKey(dailyid);
	}
	
	public GenericPageMode limitdailylist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return lqdailyviewDAO.limitdailylist(shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	
	public LiqingclDaily getBeanById(int id){
		return lqdailyDAO.get(id);
	}
	
    public void refreshdaily() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar day=Calendar.getInstance();
		String endTime = sdf.format(day.getTime());
		day.add(Calendar.DATE, -1);		
		String startTime = sdf.format(day.getTime());
		List<LiqingclDaily> lqdailyList = lqdailyviewDAO.getlqdailycl(startTime, endTime);
		for (LiqingclDaily liqingclDaily : lqdailyList) {
			List<LiqingclDaily> dl = lqdailyDAO.find("from LiqingclDaily where dailyrq=? and dailysbbh=?", liqingclDaily.getDailyrq(), liqingclDaily.getDailysbbh());
			LiqingclDaily lqdl;
			if (dl.size() > 0) {
	        	lqdl = dl.get(0);
	        	lqdl.setDailycl(liqingclDaily.getDailycl());
	        	lqdl.setDailyps(liqingclDaily.getDailyps());
			} else {
                lqdl = new LiqingclDaily();
	        	lqdl.setDailycl(liqingclDaily.getDailycl());
	        	lqdl.setDailyps(liqingclDaily.getDailyps());
	        	lqdl.setDailyrq(liqingclDaily.getDailyrq());
	        	lqdl.setDailysbbh(liqingclDaily.getDailysbbh());
			}
			lqdailyDAO.saveOrUpdate(lqdl);
		}
    }
    
    public String onelqdailycl(String sbbh, String sttime) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar day=Calendar.getInstance();
		String startTime = sdf.format(day.getTime());
		try {
			day.setTime(sdf.parse(sttime));
		} catch (ParseException e) {
		}		
		day.add(Calendar.DATE, 1);	
		String endTime = sdf.format(day.getTime());		
		try {
			startTime = sdf.format(sdf.parse(sttime));
		} catch (ParseException e) {
		}
		return lqdailyviewDAO.onelqdailycl(sbbh, startTime, endTime);		
    }
}
