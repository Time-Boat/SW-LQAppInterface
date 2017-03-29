package com.mss.shtoone.app.persistence.hibernate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mss.shtoone.app.domain.AppLoginLogEntity;
import com.mss.shtoone.app.persistence.AppDAO;
import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShaifenjieguoView;
import com.mss.shtoone.domain.Shaifenziduancfg;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.BiaoduanDAO;
import com.mss.shtoone.persistence.HunnintuViewDAO;
import com.mss.shtoone.persistence.ShaifenjieguoViewDAO;
import com.mss.shtoone.persistence.ShaifenziduancfgDAO;
import com.mss.shtoone.persistence.ShuiwenmanualphbViewDAO;
import com.mss.shtoone.persistence.ShuiwenxixxViewDAO;
import com.mss.shtoone.persistence.XiangmubuDAO;
import com.mss.shtoone.service.ShaifenshiyanService;
import com.mss.shtoone.service.SwViewService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;

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
	private HunnintuViewDAO hntDAO;

	@Autowired
	private ShaifenjieguoViewDAO sfjieguoViewDAO;

	@Autowired
	private ShaifenziduancfgDAO sfzdDAO;

	// 根据sql插入数据
	public Integer updateBySql(String sql) throws Exception {
		return appDAO.executeUpdate(sql);
	}

	// 获取拌合站设备
	public List<Banhezhanxinxi> getMachine(String fn, String id, String leixing) {
		String queryString = "";
		if (StringUtil.Null2Blank(leixing).length() > 0) {
			if ("all".equals(fn)) {
				queryString = "from Banhezhanxinxi as model where model.shebeileixin='" + leixing + "' ";
			} else {
				queryString = "from Banhezhanxinxi as model where model.shebeileixin='" + leixing + "' and model." + fn
						+ "='" + id + "' ";
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

	public List<Banhezhanxinxi> bhzList(String modelType) {
		String queryString = "";
		queryString = "from Banhezhanxinxi as model where model.shebeileixin = '" + modelType + "' ";
		return bhzDAO.find(queryString);
	}

	public List<Banhezhanxinxi> getBhzByXmb(String xmbId, String modelType) {
		if (StringUtil.isNumeric(xmbId)) {
			String queryString = "from Banhezhanxinxi as model where model.xiangmubuid in(" + xmbId
					+ ") and shebeileixin = '" + modelType + "' ";
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

	public String getNameByType(String type, String fn, String id) {
		if (StringUtil.isNotEmpty(type)) {
			String queryString = " as model where model.id ='" + id + "'";
			String tableName = getTableName(type, queryString, id);
			return tableName;
		}
		return null;
	}

	public String getTableName(String type, String queryString, String id) {
		StringBuffer fn = new StringBuffer("from ");
		String tableName = "";
		switch (Integer.parseInt(type)) {
		case 1:
		case 2:
			fn.append(" Biaoduanxinxi ");
			List<Biaoduanxinxi> bd = bdDAO.find(fn.append(queryString).toString());
			if (bd != null && bd.size() > 0) {
				tableName = bd.get(0).getBiaoduanminchen();
			}
			break;
		case 3:
			fn.append(" Xiangmubuxinxi ");
			List<Xiangmubuxinxi> xmb = xmbDAO.find(fn.append(queryString).toString());
			if (xmb != null && xmb.size() > 0) {
				tableName = xmb.get(0).getXiangmubuminchen();
			}
			break;
		case 4:
		case 5:
			fn.append(" Banhezhanxinxi ");
			List<Banhezhanxinxi> bhz = bhzDAO.find(fn.append(queryString).toString());
			if (bhz != null && bhz.size() > 0) {
				tableName = bhz.get(0).getBanhezhanminchen();
			}
			break;
		case 6:     //自定义一个
			List<Banhezhanxinxi> bhz1 = bhzDAO.find("from Banhezhanxinxi as model where model.gprsbianhao = ' " + id + "'");
			if (bhz1 != null && bhz1.size() > 0) {
				tableName = bhz1.get(0).getBanhezhanminchen();
			}
			break;
		default:
			break;
		}
		return tableName;
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
	public ShuiwenphbView appSwmateriallist(String startTime, String endTime, String shebeibianhao, Integer biaoduan,
			Integer xiangmubu, String fn, Integer bsid) {
		return swmanualphbViewDao.appSwmateriallist(startTime, endTime, shebeibianhao, biaoduan, xiangmubu, fn, bsid);
	}

	// 水稳数据查询
	public GenericPageMode swcllist(String shebeibianhao, String startTimeOne, String endTimeOne, Integer biaoduan,
			Integer xiangmubu, String fn, int bsid, int offset, int pagesize, String llbuwei) {
		return hntDAO.viewswlist(shebeibianhao, startTimeOne, endTimeOne, biaoduan, xiangmubu, fn, bsid, offset,
				pagesize, llbuwei);
	}

	// 水稳材料统计
	public ShuiwenphbView swmateriallist(String startTime, String endTime, String shebeibianhao, Integer biaoduan,
			Integer xiangmubu, String fn, int bsid) {
		return swmanualphbViewDao.swmateriallist(startTime, endTime, shebeibianhao, biaoduan, xiangmubu, fn, bsid);
	}

	public ShaifenjieguoView getShaifenjieguoViewByswId(Integer swbianhao) {
		// List<ShaifenjieguoView> sfjieguoViewlist=sfjieguoViewDAO.find("from
		// ShaifenjieguoView where swbianhao="+swbianhao);
		List<ShaifenjieguoView> sfjieguoViewlist = sfjieguoViewDAO
				.findBySql("select * from ShaifenjieguoView where swbianhao=" + swbianhao);

		// List<ShaifenjieguoView> test = sfjieguoViewDAO.find(queryString,
		// values)
		if (sfjieguoViewlist != null && sfjieguoViewlist.size() > 0) {
			// return sfjieguoViewlist.get(0);
			return sfjieguoViewlist.get(sfjieguoViewlist.size() - 1);
		} else {
			return null;
		}
	}

	// 沥青接口-------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// 沥青预警统计查询
	// public List<AppInterfaceChaobiaoEntity> lqsmstongji(String startTime,
	// String endTime, Integer biaoduan,
	// Integer xiangmubu, String shebeibianhao, String fn, Integer bsid, Integer
	// fzlx) {
	// return appDAO.lqsmstongji(startTime, endTime, biaoduan, xiangmubu,
	// shebeibianhao, fn, bsid, fzlx);
	// }
	//
	// // 沥青获取被处置的数据条数
	// public Map<String, String> getLqCbcz(String startTime, String endTime,
	// Integer biaoduan, Integer xiangmubu) {
	// return appDAO.getLqCbcz(startTime, endTime, biaoduan, xiangmubu);
	// }

	// public void departTree(List list, String fn, String biaoshiid) {
	// appDAO.departTree(list, fn, biaoshiid);
	// }

}
