package com.mss.shtoone.app.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mss.shtoone.app.domain.AppInterfaceChaobiaoEntity;
import com.mss.shtoone.app.domain.AppLoginLogEntity;
import com.mss.shtoone.app.persistence.AppDAO;
import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.BiaoduanDAO;
import com.mss.shtoone.persistence.ShuiwenmanualphbViewDAO;
import com.mss.shtoone.persistence.ShuiwenxixxViewDAO;
import com.mss.shtoone.persistence.XiangmubuDAO;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SwViewService;
import com.mss.shtoone.util.StringUtil;

import antlr.StringUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class AppServiceHibernateDAO {

	@Autowired
	private BanhezhanDAO bhzDAO;

	@Autowired
	private AppDAO appDAO;

	@Autowired
	private BiaoduanDAO bdDAO;

	@Autowired
	private XiangmubuDAO xmbDAO;

	@Autowired
	private ShuiwenxixxViewDAO swViewDAO;

	@Autowired
	private ShuiwenmanualphbViewDAO swmanualphbViewDao;

	@Autowired
	private SwViewService swService;
	
	// 获取拌合站设备
	public List<Banhezhanxinxi> getMachine(String fn, String id, String leixing) {
		String queryString = "";
		if (StringUtil.Null2Blank(leixing).length() > 0) {
			if ("all".equals(fn)) {
				queryString = "from Banhezhanxinxi as model where model.shebeileixin='" + leixing + "' ";
			} else {
				queryString = "from Banhezhanxinxi as model where model.shebeileixin='" + leixing + "' and model." + fn + "='" + id + "' ";
			}
		}
		return bhzDAO.find(queryString);
	}

	// 保存appLog
	public void saveLog(AppLoginLogEntity log) {
		appDAO.save(log);
	}

	public List<Biaoduanxinxi> bdList() {
		return bdDAO.loadAll();
	}

	public Biaoduanxinxi getBd(String id) {
		if (StringUtil.isNumeric(id)) {
			return bdDAO.get(Integer.valueOf(id));
		}
		return null;
	}

	public List<Xiangmubuxinxi> xmbList() {
		return xmbDAO.loadAll();
	}

	// 通过标段id来查找对应的项目部
	public List<Xiangmubuxinxi> getXmbByBD(String id) {
		if (StringUtil.isNumeric(id)) {

			String queryString = "from Xiangmubuxinxi as model where model.biaoduanid='" + id + "' ";

			return xmbDAO.find(queryString);
		}
		return null;
	}

	public Xiangmubuxinxi getXmbByid(String id) {
		if (StringUtil.isNumeric(id)) {

			return xmbDAO.get(Integer.parseInt(id));
		}
		return null;
	}

	public List<Banhezhanxinxi> bhzList() {
		return bhzDAO.loadAll();
	}

	public List<Banhezhanxinxi> getBhzByXmb(String xmbId) {
		if (StringUtil.isNumeric(xmbId)) {
			String queryString = "from Banhezhanxinxi as model where model.xiangmubuid in(" + xmbId
					+ ") and shebeileixin = 6 ";
			return bhzDAO.find(queryString);
		}
		return null;
	}

	public Banhezhanxinxi getBhzById(String id) {
		if (StringUtil.isNumeric(id)) {
			return bhzDAO.get(Integer.parseInt(id));
		}
		return null;
	}

	// 水稳预警统计查询
	public List<ShuiwenxixxView> swsmstongji(String startTime, String endTime, Integer biaoduan, Integer xiangmubu,
			String shebeibianhao, String fn, Integer bsid, Integer fzlx) {
		return swViewDAO.swsmstongji(startTime, endTime, biaoduan, xiangmubu, shebeibianhao, fn, bsid, fzlx);
	}

	// 水稳获取被处置的数据条数
	public Map<String, String> getCbcz(String startTime, String endTime, Integer biaoduan, Integer xiangmubu,
			String shebeibianhao) {
		return appDAO.getCbcz(startTime, endTime, biaoduan, xiangmubu, shebeibianhao);
	}

	// 水稳超标查询
	public GenericPageMode swchaobiaomanualviewlist(String shebeibianhao, String startTimeOne, String endTimeOne,
			Integer biaoduan, Integer xiangmubu, String fn, int bsid, int offset, int pagesize, Integer chaobiaolx,
			ShuiwenziduancfgView swisshow, Integer cllx, String bianhao) {
		return swmanualphbViewDao.swchaobiaomanualviewlist(shebeibianhao, startTimeOne, endTimeOne, biaoduan, xiangmubu,
				fn, bsid, offset, pagesize, chaobiaolx, swisshow, cllx, bianhao);
	}

	// 水稳材料统计
	public ShuiwenphbView swmateriallist(String startTime,String endTime,String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid){
		return swmanualphbViewDao.swmateriallist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,fn,bsid);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 沥青接口-------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// 沥青预警统计查询
//	public List<AppInterfaceChaobiaoEntity> lqsmstongji(String startTime, String endTime, Integer biaoduan,
//			Integer xiangmubu, String shebeibianhao, String fn, Integer bsid, Integer fzlx) {
//		return appDAO.lqsmstongji(startTime, endTime, biaoduan, xiangmubu, shebeibianhao, fn, bsid, fzlx);
//	}
//
//	// 沥青获取被处置的数据条数
//	public Map<String, String> getLqCbcz(String startTime, String endTime, Integer biaoduan, Integer xiangmubu) {
//		return appDAO.getLqCbcz(startTime, endTime, biaoduan, xiangmubu);
//	}

	// public void departTree(List list, String fn, String biaoshiid) {
	// appDAO.departTree(list, fn, biaoshiid);
	// }

}
