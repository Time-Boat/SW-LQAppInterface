package com.mss.shtoone.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShuiwenclDaily;
import com.mss.shtoone.persistence.ShuiwenclDailyDAO;
import com.mss.shtoone.persistence.ShuiwenclDailyViewDAO;

@Service
public class ShuiwenclDailyService {
	
	@Autowired
	private ShuiwenclDailyDAO swdailyDAO;

	@Autowired
	private ShuiwenclDailyViewDAO swdailyviewDAO;
	
	public void saveOrUpdate(ShuiwenclDaily swdaily){		
		swdailyDAO.saveOrUpdate(swdaily);
	}
	
	public void del(int dailyid){
		swdailyDAO.deleteByKey(dailyid);
	}


	public GenericPageMode limitdailylist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return swdailyviewDAO.limitdailylist(shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	
	public ShuiwenclDaily getBeanById(int id){
		return swdailyDAO.get(id);
	}
	
    public void refreshdaily() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar day=Calendar.getInstance();
		String endTime = sdf.format(day.getTime());
		day.add(Calendar.DATE, -1);		
		String startTime = sdf.format(day.getTime());
		List<ShuiwenclDaily> swdailyList = swdailyviewDAO.getSwdailycl(startTime, endTime);
		for (ShuiwenclDaily shuiwenclDaily : swdailyList) {
			List<ShuiwenclDaily> dl = swdailyDAO.find("from ShuiwenclDaily where dailyrq=? and dailysbbh=?", shuiwenclDaily.getDailyrq(), shuiwenclDaily.getDailysbbh());
			ShuiwenclDaily lqdl;
			if (dl.size() > 0) {
	        	lqdl = dl.get(0);
	        	lqdl.setDailycl(shuiwenclDaily.getDailycl());
	        	lqdl.setDailyps(shuiwenclDaily.getDailyps());
			} else {
                lqdl = new ShuiwenclDaily();
	        	lqdl.setDailycl(shuiwenclDaily.getDailycl());
	        	lqdl.setDailyps(shuiwenclDaily.getDailyps());
	        	lqdl.setDailyrq(shuiwenclDaily.getDailyrq());
	        	lqdl.setDailysbbh(shuiwenclDaily.getDailysbbh());
			}
			swdailyDAO.saveOrUpdate(lqdl);
		}
    }
    
    public String oneswdailycl(String sbbh, String sttime) {
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
		return swdailyviewDAO.oneSwdailycl(sbbh, startTime, endTime);		
    }
}
