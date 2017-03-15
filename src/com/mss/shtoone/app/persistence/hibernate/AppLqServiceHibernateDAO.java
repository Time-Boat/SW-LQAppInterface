package com.mss.shtoone.app.persistence.hibernate;

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
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.persistence.SmsinfoDAO;
import com.mss.shtoone.persistence.hibernate.GenericHibernateDAO;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class AppLqServiceHibernateDAO {
	
	@Autowired
	private SmsinfoDAO smsDAO;
	
	@Autowired
	private AppLqDAO appLqDAO;
	
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


}
