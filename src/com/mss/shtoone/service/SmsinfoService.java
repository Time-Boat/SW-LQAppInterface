package com.mss.shtoone.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.Smsinfo;
import com.mss.shtoone.domain.SmsinfoPageMode;
import com.mss.shtoone.domain.Smsrecharge;
import com.mss.shtoone.domain.Smsrecord;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.SmsinfoDAO;
import com.mss.shtoone.persistence.SmsrechargeDAO;
import com.mss.shtoone.persistence.SmsrecordDAO;
import com.mss.shtoone.persistence.XiangmubuDAO;
import com.mss.shtoone.persistence.ZuoyeduiDAO;
import com.mss.shtoone.util.StringUtil;

@Service
public class SmsinfoService {

	@Autowired
	private SmsinfoDAO smsDAO;
	
	@Autowired
	private SmsrecordDAO smsrecordDAO;
	
	@Autowired
	private SmsrechargeDAO smsrechargeDAO;
	
	@Autowired
	private XiangmubuDAO xmbDAO;
	
	@Autowired
	private ZuoyeduiDAO zydDAO;
	
	@Autowired
	private BanhezhanDAO bhzDAO;

	public SmsinfoPageMode getAll(String startTimeOne,String endTimeOne, Integer biaoduan, 
			Integer xiangmubu, String bhzid,String fn, int bsid, int offset, int pagesize){
		return smsDAO.viewlist(startTimeOne, endTimeOne, biaoduan, xiangmubu, bhzid, 
				fn, bsid, offset, pagesize);
	}
	
	public SmsinfoPageMode getSuccess(String startTimeOne,String endTimeOne,Integer biaoduan, 
			Integer xiangmubu, String bhzid,String fn, int bsid,int offset, int pagesize){
		return smsDAO.viewsuccesslist(startTimeOne, endTimeOne, biaoduan, xiangmubu,bhzid, 
				fn, bsid, offset, pagesize);
	}
	
	public List<LiqingView> lqsmstongji(String startTime,String endTime,String shebeibianhao){
		return smsDAO.lqsmstongji(startTime, endTime, shebeibianhao);
	}
	
	public List<Smsrecord> limitrecordList(HttpServletRequest request) {
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		List<Smsrecord> recordlist = new ArrayList<Smsrecord>();
		String queryString = "from Smsrecord as model where model.biaoduanid=?";
		switch (ut) {
			case 1:
				recordlist = smsrecordDAO.loadAll();
				break;
			case 2:
				recordlist = smsrecordDAO.find(queryString, bsid);		
				break;
			case 3:		
				Xiangmubuxinxi xmb = xmbDAO.get(bsid);
				if (null != xmb) {
					recordlist = smsrecordDAO.find(queryString, xmb.getBiaoduanid());
				}
				break;
			case 4:		
				Zuoyeduixinxi zyd = zydDAO.get(bsid);
				if (null != zyd) {
					recordlist = smsrecordDAO.find(queryString, zyd.getBiaoduanid());
				}
				break;
			case 5:		
				Banhezhanxinxi bhz = bhzDAO.get(bsid);
				if (null != bhz) {
					recordlist = smsrecordDAO.find(queryString, bhz.getBiaoduanid());
				}			
				break;
			default:
				break;
		}	
		return recordlist;
	}
	
	public List<Smsrecord> getalarmrecordList() {
		String queryString = "from Smsrecord as model where model.isalarm=1 and model.completealarm=0";
		return smsrecordDAO.find(queryString);
	}
	
	public List<Smsrecharge> limitrechargeList(HttpServletRequest request) {
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		List<Smsrecharge> rechargelist = new ArrayList<Smsrecharge>();
		String queryString = "from Smsrecharge as model where model.biaoduanid=?";
		switch (ut) {
			case 1:
				rechargelist = smsrechargeDAO.loadAll();
				break;
			case 2:
				rechargelist = smsrechargeDAO.find(queryString, bsid);		
				break;
			case 3:		
				Xiangmubuxinxi xmb = xmbDAO.get(bsid);
				if (null != xmb) {
					rechargelist = smsrechargeDAO.find(queryString, xmb.getBiaoduanid());
				}
				break;
			case 4:		
				Zuoyeduixinxi zyd = zydDAO.get(bsid);
				if (null != zyd) {
					rechargelist = smsrechargeDAO.find(queryString, zyd.getBiaoduanid());
				}
				break;
			case 5:		
				Banhezhanxinxi bhz = bhzDAO.get(bsid);
				if (null != bhz) {
					rechargelist = smsrechargeDAO.find(queryString, bhz.getBiaoduanid());
				}			
				break;
			default:
				break;
		}	
		return rechargelist;
	}	

	
	public Smsrecord limitSmsrecordByid(HttpServletRequest request, Integer id) {
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		List<Smsrecord> recordlist = new ArrayList<Smsrecord>();
		String queryString = "from Smsrecord as model where model.id=? and model.biaoduanid=?";
		switch (ut) {
			case 1:
				queryString = "from Smsrecord as model where model.id=?";
				recordlist = smsrecordDAO.find(queryString, id);
				break;
			case 2:
				recordlist = smsrecordDAO.find(queryString, id, bsid);		
				break;
			case 3:		
				Xiangmubuxinxi xmb = xmbDAO.get(bsid);
				if (null != xmb) {
					recordlist = smsrecordDAO.find(queryString, id, xmb.getBiaoduanid());
				}
				break;
			case 4:		
				Zuoyeduixinxi zyd = zydDAO.get(bsid);
				if (null != zyd) {
					recordlist = smsrecordDAO.find(queryString, id, zyd.getBiaoduanid());
				}
				break;
			case 5:		
				Banhezhanxinxi bhz = bhzDAO.get(bsid);
				if (null != bhz) {
					recordlist = smsrecordDAO.find(queryString, id, bhz.getBiaoduanid());
				}			
				break;
			default:
				break;
		}	
		if (recordlist.size() > 0) {
			return recordlist.get(0);
		} else {
			return null;
		}
	}
	
	public Smsrecord getSmsrecordBybiaoduanid(Integer biaoduanid) {
		List<Smsrecord> recordlist = new ArrayList<Smsrecord>();
		String queryString = "from Smsrecord as model where model.biaoduanid=?";
		recordlist = smsrecordDAO.find(queryString, biaoduanid);
		if (recordlist.size() > 0) {
			return recordlist.get(0);
		} else {
			return null;
		}
	}
	
	
	public Smsrecharge limitSmsrechargeByid(HttpServletRequest request, Integer id) {
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		List<Smsrecharge> rechargelist = new ArrayList<Smsrecharge>();
		String queryString = "from Smsrecharge as model where model.id=? and model.biaoduanid=?";
		switch (ut) {
			case 1:
				queryString = "from Smsrecharge as model where model.id=?";
				rechargelist = smsrechargeDAO.find(queryString, id);
				break;
			case 2:
				rechargelist = smsrechargeDAO.find(queryString, id, bsid);		
				break;
			case 3:		
				Xiangmubuxinxi xmb = xmbDAO.get(bsid);
				if (null != xmb) {
					rechargelist = smsrechargeDAO.find(queryString, id, xmb.getBiaoduanid());
				}
				break;
			case 4:		
				Zuoyeduixinxi zyd = zydDAO.get(bsid);
				if (null != zyd) {
					rechargelist = smsrechargeDAO.find(queryString, id, zyd.getBiaoduanid());
				}
				break;
			case 5:		
				Banhezhanxinxi bhz = bhzDAO.get(bsid);
				if (null != bhz) {
					rechargelist = smsrechargeDAO.find(queryString, id, bhz.getBiaoduanid());
				}			
				break;
			default:
				break;
		}	
		if (rechargelist.size() > 0) {
			return rechargelist.get(0);
		} else {
			return null;
		}
	}
	
	public void saveOrUpdateSmsrecord(Smsrecord record){
		smsrecordDAO.saveOrUpdate(record);		
	}
	
	public void delSmsrecord(HttpServletRequest request, Integer recordId){
		Smsrecord record = limitSmsrecordByid(request, recordId);
		if (null != record) {
			smsrecordDAO.delete(record);
		}
				
	}
	
	public void delSmsrecharge(HttpServletRequest request, Integer rechargeId){
		Smsrecharge recharge = limitSmsrechargeByid(request, rechargeId);
		if (null != recharge) {
			smsrechargeDAO.delete(recharge);
		}
	}

	public void saveOrUpdateSmsrecharge(Smsrecharge recharge){
		smsrechargeDAO.saveOrUpdate(recharge);		
	}
	
	public void addsmsrecharge(Smsrecharge smsrecharge){
		if (null != smsrecharge && smsrecharge.getBiaoduanid() != null && smsrecharge.getBiaoduanid()>0) {
			smsrecharge.setSubmitstate(0);
			smsrecharge.setRechargestate("未提交");
			saveOrUpdateSmsrecharge(smsrecharge);
		}	
	}
	
	public void addandsubmitsmsrecharge(Smsrecharge smsrecharge){
		if (null != smsrecharge && smsrecharge.getBiaoduanid() != null && smsrecharge.getBiaoduanid()>0) {
			smsrecharge.setSubmitstate(1);
			smsrecharge.setRechargestate("等待充值");
			saveOrUpdateSmsrecharge(smsrecharge);
		}
	}
	
	public void submitrecharge(HttpServletRequest request, Integer rechargeId) {
		if (rechargeId > 0 ) {
			Smsrecharge smsrecharge = limitSmsrechargeByid(request, rechargeId);
			if (null != smsrecharge) {
				smsrecharge.setSubmitstate(1);
				smsrecharge.setRechargestate("等待充值");
				saveOrUpdateSmsrecharge(smsrecharge);
			}
		}
	}
	
	public void returnrecharge(HttpServletRequest request, Integer rechargeId) {
		if (rechargeId > 0 ) {
			Smsrecharge smsrecharge = limitSmsrechargeByid(request, rechargeId);
			if (null != smsrecharge) {
				smsrecharge.setSubmitstate(0);
				smsrecharge.setRechargestate("被退回");
				saveOrUpdateSmsrecharge(smsrecharge);
			}
		}
	}
	
	@Transactional
	public void completerecharge(Smsrecharge smsrecharge) {
		if (null != smsrecharge && smsrecharge.getBiaoduanid() != null 
				&& smsrecharge.getBiaoduanid() > 0
				&& smsrecharge.getRechargecount() != null 
				&& smsrecharge.getRechargecount() > 0) {
			Smsrecord record = getSmsrecordBybiaoduanid(smsrecharge.getBiaoduanid());
			if (null != record) {
				if (null == record.getSmscount()) {
					record.setSmscount(smsrecharge.getRechargecount());
				} else {
					record.setSmscount(smsrecharge.getRechargecount()+record.getSmscount());
				}
				if (null != record.getSmscount() && null != record.getIsalarm()
						&& record.getIsalarm() == 1 && 
						(null == record.getAlarmcount() || record.getSmscount()>record.getAlarmcount())) {
					record.setCompletealarm(0);
				}
				smsrecharge.setSubmitstate(2);
				smsrecharge.setRechargestate("完成充值");
				saveOrUpdateSmsrecharge(smsrecharge);
				saveOrUpdateSmsrecord(record);
			}
		}
	}
	
	//预警统计
	public List<LiqingView> smstongji(String startTimeOne,String endTimeOne,Integer biaoduan, 
			Integer xiangmubu, String bhzid,String fn, int bsid, int fzlx, int jbsj){
		return smsDAO.smstongji(startTimeOne, endTimeOne, biaoduan, xiangmubu, bhzid, 
				fn, bsid, fzlx, jbsj);
	}
	
	public List<Smsinfo> getSmsInfoAll(Integer swxxid){
		return smsDAO.find("from Smsinfo where swbianhao="+swxxid);
	}
	public List<Smsinfo> getLqSmsInfoAll(Integer lqxxid){
		return smsDAO.find("from Smsinfo where lqbianhao="+lqxxid);
	}
}
