package com.mss.shtoone.app.persistence.hibernate.lq;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mss.shtoone.app.domain.AppLoginLogEntity;
import com.mss.shtoone.app.persistence.AppLqDAO;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.persistence.LiqingViewDAO;
import com.mss.shtoone.persistence.LiqingclDailyViewDAO;
import com.mss.shtoone.persistence.SmsinfoDAO;
import com.mss.shtoone.persistence.hibernate.GenericHibernateDAO;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class AppLqServiceHibernateDAO {
	
	@Autowired
	private SmsinfoDAO smsDAO;
	
	@Autowired
	private AppLqDAO appLqDAO;
	
	
	@Autowired
	private LiqingViewDAO lqDAO;
	
	@Autowired
	private LiqingclDailyViewDAO lqdailyviewDAO;
	
	public List<LiqingView> smstongji(String startTime, String endTime, Integer biaoduan, Integer xiangmubu,
			String shebeibianhao, String fn, Integer bsid, Integer fzlx,int jbsj) {
		return smsDAO.smstongji(startTime, endTime, biaoduan, xiangmubu, shebeibianhao, fn, bsid, fzlx,jbsj);
	}

	// 沥青获取被处置的数据条数
	public Map<String, String> getCbcz(String startTime, String endTime,
			Integer biaoduan, Integer xiangmubu, String shebeibianhao) {
		// TODO Auto-generated method stub
		return appLqDAO.getCbcz(startTime, endTime, biaoduan, xiangmubu, shebeibianhao);
	}
	
	//沥青超标查询
	public GenericPageMode lqchaobiaomanualviewlist(LiqingziduancfgView lqisshow, Integer chaobiaolx,String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize,Integer cllx,String str) {
		return lqDAO.lqchaobiaomanualviewlist(lqisshow, chaobiaolx,shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize,cllx,str);
	}
	
	//沥青材料统计
	public LiqingphbView appLqmateriallist(String startTime,String endTime,String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid){
		return lqDAO.appLqmateriallist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,fn,bsid);
	}
	
	
	public GenericPageMode lqviewlist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize, int queryalldata,String str) {
		return lqDAO.lqviewlist(shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize, queryalldata,str);
	}
	
	//查询沥青日产量统计方法
	public GenericPageMode limitdailylist(String shebeibianhao,String startTimeOne,
			String endTimeOne,Integer biaoduan, Integer xiangmubu, 
			String fn, int bsid, int offset, int pagesize) {
		return lqdailyviewDAO.limitdailylist(shebeibianhao,startTimeOne,endTimeOne,
				biaoduan, xiangmubu, fn, bsid, offset, pagesize);
	}
	
	
}
