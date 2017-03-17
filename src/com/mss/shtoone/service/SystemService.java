package com.mss.shtoone.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.ChuliaokouTemperaturejieguo;
import com.mss.shtoone.domain.Clksmscfg;
import com.mss.shtoone.domain.Gpsdata;
import com.mss.shtoone.domain.HandSet;
import com.mss.shtoone.domain.Hntbhzziduancfg;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.Hntjieguoxinxi;
import com.mss.shtoone.domain.Hntxiangxixxmax;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.Liqingsclsheji;
import com.mss.shtoone.domain.Liqingxixx;
import com.mss.shtoone.domain.Liqingxixxjieguo;
import com.mss.shtoone.domain.Liqingxixxlilun;
import com.mss.shtoone.domain.Liqingxixxmax;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.Lqshaifenjieguo;
import com.mss.shtoone.domain.Lqshaifenshiyan;
import com.mss.shtoone.domain.Shaifenjieguo;
import com.mss.shtoone.domain.Shaifenshiyan;
import com.mss.shtoone.domain.Shaifenziduancfg;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.Shuiwentongji;
import com.mss.shtoone.domain.Shuiwenxixxjieguo;
import com.mss.shtoone.domain.Shuiwenxixxlilun;
import com.mss.shtoone.domain.Shuiwenxixxmax;
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.Smsinfo;
import com.mss.shtoone.domain.Smsrecord;
import com.mss.shtoone.domain.Speedjieguo;
import com.mss.shtoone.domain.Temperaturedata;
import com.mss.shtoone.domain.Temperaturejieguo;
import com.mss.shtoone.domain.TopChuliaokouTemperaturedataView;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.TopRealChuliaokouTemperaturedataView;
import com.mss.shtoone.domain.TopRealEnvironmentView;
import com.mss.shtoone.domain.TopRealGpsdataView;
import com.mss.shtoone.domain.TopRealMaxLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.TopRealMaxhunnintuView;
import com.mss.shtoone.domain.TopRealSpeeddata1View;
import com.mss.shtoone.domain.TopRealSpeeddataView;
import com.mss.shtoone.domain.TopRealTemperaturedata1View;
import com.mss.shtoone.domain.TopRealTemperaturedataView;
import com.mss.shtoone.domain.TopRealTjjdataView;
import com.mss.shtoone.domain.TophunnintuView;
import com.mss.shtoone.domain.Usertypexinxi;
import com.mss.shtoone.domain.Xcsmscfg;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.Xiangxixxjieguo;
import com.mss.shtoone.domain.Xiangxixxsms;
import com.mss.shtoone.domain.YezhuFile;
import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.BiaoduanDAO;
import com.mss.shtoone.persistence.ChuliaokouTemperaturejieguoDAO;
import com.mss.shtoone.persistence.ClksmscfgDAO;
import com.mss.shtoone.persistence.GpsdataDAO;
import com.mss.shtoone.persistence.HandSetDAO;
import com.mss.shtoone.persistence.HntbhzCfgDAO;
import com.mss.shtoone.persistence.HntbhzCfgViewDAO;
import com.mss.shtoone.persistence.HntjieguoxinxiDAO;
import com.mss.shtoone.persistence.HntxiangxixxmaxDAO;
import com.mss.shtoone.persistence.LiqingsclshejiDAO;
import com.mss.shtoone.persistence.LiqingxixxDAO;
import com.mss.shtoone.persistence.LiqingxixxjieguoDAO;
import com.mss.shtoone.persistence.LiqingxixxlilunDAO;
import com.mss.shtoone.persistence.LiqingxixxmaxDAO;
import com.mss.shtoone.persistence.LiqingziduancfgDAO;
import com.mss.shtoone.persistence.LiqingziduancfgViewDAO;
import com.mss.shtoone.persistence.LqshaifenjieguoDAO;
import com.mss.shtoone.persistence.ShaifenziduancfgDAO;
import com.mss.shtoone.persistence.ShuiwentongjiDAO;
import com.mss.shtoone.persistence.ShuiwenxixxjieguoDAO;
import com.mss.shtoone.persistence.ShuiwenxixxlilunDAO;
import com.mss.shtoone.persistence.ShuiwenxixxmaxDAO;
import com.mss.shtoone.persistence.ShuiwenziduancfgDAO;
import com.mss.shtoone.persistence.ShuiwenziduancfgViewDAO;
import com.mss.shtoone.persistence.SmsinfoDAO;
import com.mss.shtoone.persistence.SpeedjieguoDAO;
import com.mss.shtoone.persistence.TemperaturedataDAO;
import com.mss.shtoone.persistence.TemperaturejieguoDAO;
import com.mss.shtoone.persistence.TopChuliaokouTemperaturedataViewDAO;
import com.mss.shtoone.persistence.TopLiqingViewDAO;
import com.mss.shtoone.persistence.TopRealChuliaokouTemperaturedataViewDAO;
import com.mss.shtoone.persistence.TopRealEnvironmentViewDAO;
import com.mss.shtoone.persistence.TopRealGpsdataViewDAO;
import com.mss.shtoone.persistence.TopRealMaxLiqingViewDAO;
import com.mss.shtoone.persistence.TopRealMaxShuiwenxixxViewDAO;
import com.mss.shtoone.persistence.TopRealMaxhunnintuViewDAO;
import com.mss.shtoone.persistence.TopRealSpeeddata1ViewDAO;
import com.mss.shtoone.persistence.TopRealSpeeddataViewDAO;
import com.mss.shtoone.persistence.TopRealTemperaturedata1ViewDAO;
import com.mss.shtoone.persistence.TopRealTemperaturedataViewDAO;
import com.mss.shtoone.persistence.TopRealTjjdataViewDAO;
import com.mss.shtoone.persistence.TophunnintuViewDAO;
import com.mss.shtoone.persistence.UsertypexinxiDAO;
import com.mss.shtoone.persistence.XcsmscfgDAO;
import com.mss.shtoone.persistence.XiangmubuDAO;
import com.mss.shtoone.persistence.XiangxixxDAO;
import com.mss.shtoone.persistence.XiangxixxjieguoDAO;
import com.mss.shtoone.persistence.XiangxixxsmsDAO;
import com.mss.shtoone.persistence.YezhuFileDAO;
import com.mss.shtoone.persistence.ZuoyeduiDAO;
import com.mss.shtoone.util.Smssender;
import com.mss.shtoone.util.StringUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class SystemService {
	private static Log logger = LogFactory.getLog(SystemService.class);
	@Autowired
	private BanhezhanDAO bhzDAO;
	
	@Autowired
	private HntbhzCfgViewDAO hntviewDAO;
	
	@Autowired
	private TopRealMaxShuiwenxixxViewDAO topswDAO;
	
	@Autowired
	private LiqingziduancfgViewDAO lqviewDAO;
	
	@Autowired
	private HntbhzCfgDAO hntDAO;
	
	@Autowired
	private LiqingziduancfgDAO lqcfgDAO;
	
	@Autowired
	private SmsinfoDAO smsDAO;
	
	@Autowired
	private HandSetDAO handsetDAO;
	
	@Autowired
	private BiaoduanDAO bdDAO;
	
	@Autowired
	private UsertypexinxiDAO utDAO;
	
	@Autowired
	private XiangmubuDAO xmbDAO;
	
	@Autowired
	private ZuoyeduiDAO zydDAO;
	
	@Autowired
	private TophunnintuViewDAO topDAO;	
	
	@Autowired
	private TopRealSpeeddataViewDAO topspeedDAO;
	
	@Autowired
	private TopRealSpeeddata1ViewDAO topspeed1DAO;
	
	@Autowired
	private TopRealGpsdataViewDAO topgpsDAO;
	
	@Autowired
	private TopRealTemperaturedataViewDAO toptmpDAO;
	
	@Autowired
	private TopRealTemperaturedata1ViewDAO toptmp1DAO;
	
	@Autowired
	private TopRealChuliaokouTemperaturedataViewDAO topchuliaokoutmpDAO;
	
	@Autowired
	private TopRealTjjdataViewDAO toptjjDAO;
	
	@Autowired
	private GpsdataDAO gpsDAO;
	
	@Autowired
	private TopLiqingViewDAO toplqDAO;	
	
	@Autowired
	private TopRealMaxhunnintuViewDAO realMaxDAO;
	
	@Autowired
	private TopRealMaxLiqingViewDAO realMaxlqDAO;
	
	@Autowired
	private XiangxixxjieguoDAO xxjieguoDAO;
	
	@Autowired
	private LiqingxixxjieguoDAO lqjieguoDAO;
	
	@Autowired
	private ShuiwenxixxjieguoDAO swjieguoDAO;
	
	@Autowired
	private TemperaturejieguoDAO tmpjieguoDAO;
	
	@Autowired
	private SpeedjieguoDAO speedjieguoDAO;
	
	@Autowired
	private XiangxixxsmsDAO xxsmsDAO;
	
	@Autowired
	private HntxiangxixxmaxDAO xxmaxDAO;
	
	@Autowired
	private LiqingxixxlilunDAO lqlilunDAO;
	
	@Autowired
	private LiqingxixxDAO lqDAO;
	
	@Autowired
	private LiqingxixxmaxDAO lqmaxDAO;
	
	@Autowired
	private XcsmscfgDAO xcsmscfgDAO;
	
	@Autowired
	private ClksmscfgDAO clksmscfgDAO;

	@Autowired
	private HntjieguoxinxiDAO jieguoDAO;
	
	@Autowired
	private SmsinfoService smsService;
	
	@Autowired
	private ChuliaokouTemperaturejieguoDAO clktmpjieguoDAO;
	
	@Autowired
	private TopRealEnvironmentViewDAO topEnvironmentDAO;
	
	@Autowired
	private TemperaturedataDAO bhzdataDAO;
	
	@Autowired
	private LiqingsclshejiDAO lqshejiDAO;
	
	@Autowired
	private ShuiwenziduancfgViewDAO swcfgViewDAO;
	
	@Autowired
	private ShuiwenziduancfgDAO swcfgDAO;
	
	@Autowired
	private TopRealMaxShuiwenxixxViewDAO realMaxswDAO;
	
	@Autowired
	private ShuiwenxixxmaxDAO swmaxDAO;
	
	@Autowired
	private ShuiwenxixxlilunDAO swlilunDAO;
	
	@Autowired
	private ShuiwentongjiDAO swDAO;
	
	@Autowired
	private ShuiwenxixxlilunService swlilunService;
	
	@Autowired
	private ShaifenshiyanService shaifenService;
	
	@Autowired
	private LqshaifenshiyanService lqshaifenService;
	
	@Autowired
	private XiangxixxDAO xiangDAO;	
	
	@Autowired
	private LiqingxixxlilunService lqlilunService;
	
	@Autowired
	private LqshaifenjieguoDAO lqsfjieguoDAO;
	
	@Autowired
	private YezhuFileDAO yezhuFileDAO;
	
	@Autowired
	private ShaifenziduancfgDAO sfzdDAO;
	
	public List<HandSet> handsetlist(Integer biaoduan) {
		if (null != biaoduan) {
			return handsetDAO.find("from handset where biaoduan=0 or biaoduan=?", biaoduan);
		} else {
			return handsetDAO.loadAll();
		}
		
	}
	
	public Xcsmscfg getXcsmscfg(String shebeibianhao) {
		Xcsmscfg xcsms = xcsmscfgDAO.get(shebeibianhao);
		if (null == xcsms) {
			xcsms = new Xcsmscfg();
			xcsms.setShebeibianhao(shebeibianhao);
			xcsms.setShebeibianhao(shebeibianhao);
			xcsms.setTmplow("100");
			xcsms.setTmphigh("100");
			xcsms.setTmplow1("100");
			xcsms.setTmphigh1("100");
			xcsms.setTmplow2("100");
			xcsms.setTmphigh2("180");
			xcsms.setSpeedlow("30");
			xcsms.setSpeedhigh("80");
			xcsms.setSpeedlow1("25");
			xcsms.setSpeedhigh1("90");
			xcsms.setSpeedlow2("20");
			xcsms.setSpeedhigh2("100");
			xcsms.setSmsbaojin("1");
		}
		return xcsms;
	}
	
	//出料口基质沥青初级
	public Clksmscfg getClksmscfglow(String shebeibianhao) {
		Clksmscfg xcsms = clksmscfgDAO.findByGprsbhandleixin(shebeibianhao, "low");
		if (null == xcsms) {
			xcsms = new Clksmscfg();
			xcsms.setShebeibianhao(shebeibianhao);
			xcsms.setRank("low");
			xcsms.setTmplow("100");
			xcsms.setTmphigh("100");
			xcsms.setTmplow1("100");
			xcsms.setTmphigh1("100");
			xcsms.setTmplow2("110");
			xcsms.setTmphigh2("180");
			xcsms.setSmsbaojin("1");
		}
		return xcsms;
	}
	
	//出料口PG76改性沥青
	public Clksmscfg getClksmscfgmid(String shebeibianhao) {
		Clksmscfg xcsms = clksmscfgDAO.findByGprsbhandleixin(shebeibianhao, "mid");
		if (null == xcsms) {
			xcsms = new Clksmscfg();
			xcsms.setShebeibianhao(shebeibianhao);
			xcsms.setRank("mid");
			xcsms.setTmplow("100");
			xcsms.setTmphigh("100");
			xcsms.setTmplow1("100");
			xcsms.setTmphigh1("100");
			xcsms.setTmplow2("110");
			xcsms.setTmphigh2("180");
			xcsms.setSmsbaojin("1");
		}
		return xcsms;
	}
	
	//出料口PG82改性沥青
	public Clksmscfg getClksmscfghigh(String shebeibianhao) {
		Clksmscfg xcsms = clksmscfgDAO.findByGprsbhandleixin(shebeibianhao, "high");
		if (null == xcsms) {
			xcsms = new Clksmscfg();
			xcsms.setShebeibianhao(shebeibianhao);
			xcsms.setRank("high");
			xcsms.setTmplow("100");
			xcsms.setTmphigh("100");
			xcsms.setTmplow1("100");
			xcsms.setTmphigh1("100");
			xcsms.setTmplow2("110");
			xcsms.setTmphigh2("180");
			xcsms.setSmsbaojin("1");
		}
		return xcsms;
	}
	
	public void clksmscfgdel(String gprsbh) {
		Clksmscfg xccfglow = getClksmscfglow(gprsbh);
		if (null != xccfglow) {
			clksmscfgDAO.delete(xccfglow);
		}
		Clksmscfg xccfgmid = getClksmscfgmid(gprsbh);
		if (null != xccfgmid) {
			clksmscfgDAO.delete(xccfgmid);
		}
		Clksmscfg xccfghigh = getClksmscfghigh(gprsbh);
		if (null != xccfghigh) {
			clksmscfgDAO.delete(xccfghigh);
		}
	}
	
	//出料口字段配置
	public Clksmscfg getClksmscfg(String shebeibianhao) {
		Clksmscfg xcsms = clksmscfgDAO.get(shebeibianhao);
		if (null == xcsms) {
			xcsms = new Clksmscfg();
			xcsms.setShebeibianhao(shebeibianhao);
			xcsms.setTmplow("100");
			xcsms.setTmphigh("100");
			xcsms.setTmplow1("100");
			xcsms.setTmphigh1("100");
			xcsms.setTmplow2("110");
			xcsms.setTmphigh2("180");
			xcsms.setSmsbaojin("1");
		}
		return xcsms;
	}
	
	public List<Biaoduanxinxi> biaoduanlist() {
		return bdDAO.find("from Biaoduanxinxi order by orderid");		
	}
	
	public Xiangxixxjieguo getXxjieguobybh(Integer bianhao) {
		Xiangxixxjieguo xxjg = null;
		List hlist = xxjieguoDAO.find("from Xiangxixxjieguo where xinxibianhao=?", bianhao);
		if (hlist.size()>0) {
			xxjg = (Xiangxixxjieguo) hlist.get(0);
		} 		
		return xxjg;
	}
	
	public Liqingxixxjieguo getLljieguobybh(Integer bianhao) {
		Liqingxixxjieguo lqjg = null;
		List hlist = lqjieguoDAO.find("from Liqingxixxjieguo where lqbianhao=?", bianhao);
		if (hlist.size()>0) {
			lqjg = (Liqingxixxjieguo) hlist.get(0);
		} 		
		return lqjg;
	}
	
	public Shuiwenxixxjieguo getSwjieguobybh(Integer bianhao) {
		List<Shuiwenxixxjieguo> hlist = swjieguoDAO.find("from Shuiwenxixxjieguo where swbianhao=?", bianhao);
		if (hlist.size()>0) {
			return (Shuiwenxixxjieguo) hlist.get(0);
		}else{
			return null;
		}		
	}
	
	public void saveXxjieguo(Xiangxixxjieguo xxjg) {
		xxjieguoDAO.saveOrUpdate(xxjg);
	}
	
	public void saveLqjieguo(Liqingxixxjieguo lqjg) {
		lqjieguoDAO.saveOrUpdate(lqjg);
	}
	
	public void saveSwjieguo(Shuiwenxixxjieguo lqjg) {
		swjieguoDAO.saveOrUpdate(lqjg);
	}
	
	public void saveTmpjieguo(Temperaturejieguo tmpjg) {
		tmpjieguoDAO.saveOrUpdate(tmpjg);
	}
	
	
	public void saveSpeedjieguo(Speedjieguo speedjg) {
		speedjieguoDAO.saveOrUpdate(speedjg);
	}
	
	public Xiangxixxsms getXxsmsbybh(String shebeibianhao) {
		List hlist = xxsmsDAO.find("from Xiangxixxsms where shebeibianhao=?", shebeibianhao);
		if (hlist.size()>0) {
			return (Xiangxixxsms) hlist.get(0);
		} else {
			return null;
		}	
	}
	
	public List<Hntjieguoxinxi> getHntjieguoxinxi() {
		return jieguoDAO.loadAll();
	}
	
	public void saveXxsms(Xiangxixxsms xxsms) {
		xxsmsDAO.saveOrUpdate(xxsms);
	}
	
	public List<Usertypexinxi> limitutlist(HttpServletRequest request) {
		int ut = StringUtil.getUserType(request);
		List<Usertypexinxi> bdlist = new ArrayList<Usertypexinxi>();
		String queryString = "from Usertypexinxi as model where model.id>=?";
		switch (ut) {
		case 1:
			bdlist = utDAO.loadAll();
			break;
		case 2:
		case 3:
		case 4:
		case 5:
			bdlist = bdDAO.find(queryString, ut);		
			break;
		default:
			break;
		}
		return bdlist;
	}
	
	public List<Biaoduanxinxi> bdlist() {
		return bdDAO.find("from Biaoduanxinxi order by orderid");
	}
	
	public List<Biaoduanxinxi> limitbdlist(HttpServletRequest request) {
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		List<Biaoduanxinxi> bdlist = new ArrayList<Biaoduanxinxi>();
		String queryString = "from Biaoduanxinxi as model where model.id=? order by orderid";
		switch (ut) {
		case 1:
			bdlist = bdDAO.find("from Biaoduanxinxi order by orderid");
			break;
		case 2:
			bdlist = bdDAO.find(queryString, bsid);		
			break;
		case 3:		
			Xiangmubuxinxi xmb = xmbDAO.get(bsid);
			if (null != xmb) {
				bdlist = bdDAO.find(queryString, xmb.getBiaoduanid());
			}
			break;
		case 4:		
			Zuoyeduixinxi zyd = zydDAO.get(bsid);
			if (null != zyd) {
				bdlist = bdDAO.find(queryString, zyd.getBiaoduanid());
			}
			break;
		case 5:		
			Banhezhanxinxi bhz = bhzDAO.get(bsid);
			if (null != bhz) {
				bdlist = bdDAO.find(queryString, bhz.getBiaoduanid());
			}			
			break;
		default:
			break;
		}
		return bdlist;
	}
	
	public List<Xiangmubuxinxi> xiangmubulist() {
		return xmbDAO.loadAll();
	}
	
	public List<Xiangmubuxinxi> xmblist(Integer biaoduan) {
		List<Xiangmubuxinxi> xmblist = new ArrayList<Xiangmubuxinxi>();
		if (null != biaoduan) {
			String queryString = "from Xiangmubuxinxi as model where model.biaoduanid=?";
			xmblist = xmbDAO.find(queryString, biaoduan);
		} else {
			xmblist = xmbDAO.loadAll();
		}
		return xmblist;
	}
	
	public List<Xiangmubuxinxi> limitxmblist(HttpServletRequest request, Integer biaoduan) {
		List<Xiangmubuxinxi> xmblist = new ArrayList<Xiangmubuxinxi>();
		String queryString = "from Xiangmubuxinxi as model where model.id=?";	
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		switch (ut) {
		case 1:
			if (null != biaoduan) {
				queryString = "from Xiangmubuxinxi as model where model.biaoduanid=?";
				xmblist = xmbDAO.find(queryString, biaoduan);
			} else {
				xmblist = xmbDAO.loadAll();
			}
			break;
		case 2:
			queryString = "from Xiangmubuxinxi as model where model.biaoduanid=?";
			xmblist = xmbDAO.find(queryString, bsid);
			break;
		case 3:
			xmblist = xmbDAO.find(queryString, bsid);
			break;
		case 4:
			Zuoyeduixinxi zyd = zydDAO.get(bsid);
			if (null != zyd) {
				xmblist = xmbDAO.find(queryString, zyd.getXiangmubuid());
			}
			break;
		case 5:
			Banhezhanxinxi bhz = bhzDAO.get(bsid);
			if (null != bhz) {
				xmblist = xmbDAO.find(queryString, bhz.getXiangmubuid());
			}
			break;
		default:
			break;
		}
		return xmblist;
	}
	
	public List<Zuoyeduixinxi> limitzydlist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		List<Zuoyeduixinxi> zydlist = new ArrayList<Zuoyeduixinxi>();
		String queryString = "from Zuoyeduixinxi as model where model.id=?";	
		int ut = StringUtil.getUserType(request);
		int bsid = StringUtil.getBiaoshiId(request);
		Banhezhanxinxi bhz;
		switch (ut) {
		case 1:
			if (null != xiangmubu) {
				queryString = "from Zuoyeduixinxi as model where model.xiangmubuid=?";
				zydlist = zydDAO.find(queryString, xiangmubu);
			} else if (null != biaoduan) {
				queryString = "from Zuoyeduixinxi as model where model.biaoduanid=?";
				zydlist = zydDAO.find(queryString, biaoduan);
			} else {
				zydlist = zydDAO.loadAll();
			}
			break;
		case 2:
			if (null != xiangmubu) {
				queryString = "from Zuoyeduixinxi as model where model.xiangmubuid=?";
				zydlist = zydDAO.find(queryString, xiangmubu);
			} else {
				queryString = "from Zuoyeduixinxi as model where model.biaoduanid=?";
				zydlist = zydDAO.find(queryString, bsid);
			}
			break;
		case 3:
			queryString = "from Zuoyeduixinxi as model where model.xiangmubuid=?";
			zydlist = zydDAO.find(queryString, bsid);
			break;
		case 4:
			zydlist = zydDAO.find(queryString, bsid);
			break;
		case 5:
			bhz = bhzDAO.get(bsid);
			if (null != bhz) {
				zydlist = zydDAO.find(queryString, bhz.getZuoyeduiid());
			}
			break;
		default:
			break;
		}
		return zydlist;
	}
	
	public List<Banhezhanxinxi> bhzlist() {
		return bhzDAO.loadAll();
	}
	
	public List<TophunnintuView> limithntlist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TophunnintuView as model";
		List<TophunnintuView> toplist = new ArrayList<TophunnintuView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = topDAO.find(queryString+" where model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topDAO.find(queryString+" where model.biaoduanid=?", biaoduan);
				} else {
					toplist = topDAO.loadAll();
				}
			}
		} else {
			queryString = "from TophunnintuView as model where model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = topDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = topDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<TopRealSpeeddataView> limitSpeedlist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealSpeeddataView as model";
		List<TopRealSpeeddataView> toplist = new ArrayList<TopRealSpeeddataView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = topspeedDAO.find(queryString+" where model.xiangmubuid=? ORDER BY gpsno", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topspeedDAO.find(queryString+" where model.biaoduanid=? ORDER BY gpsno", biaoduan);
				} else {
					toplist = topspeedDAO.find("from TopRealSpeeddataView ORDER BY gpsno");
				}
			}
		} else {
			queryString = "from TopRealSpeeddataView as model where model." + fn
					+ "=? ORDER BY gpsno";
			if (null != xiangmubu) {
				toplist = topspeedDAO.find(queryString+" and model.xiangmubuid=? ORDER BY gpsno", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topspeedDAO.find(queryString+" and model.biaoduanid=? ORDER BY gpsno", bsid, biaoduan);
				} else {
					toplist = topspeedDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<TopRealSpeeddata1View> limitTanpuSpeedlist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealSpeeddata1View as model";
		List<TopRealSpeeddata1View> toplist = new ArrayList<TopRealSpeeddata1View>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = topspeed1DAO.find(queryString+" where model.xiangmubuid=? ORDER BY gpsno", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topspeed1DAO.find(queryString+" where model.biaoduanid=? ORDER BY gpsno", biaoduan);
				} else {
					toplist = topspeed1DAO.find("from TopRealSpeeddata1View ORDER BY gpsno");
				}
			}
		} else {
			queryString = "from TopRealSpeeddata1View as model where model." + fn
					+ "=? ORDER BY gpsno";
			if (null != xiangmubu) {
				toplist = topspeed1DAO.find(queryString+" and model.xiangmubuid=? ORDER BY gpsno", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topspeed1DAO.find(queryString+" and model.biaoduanid=? ORDER BY gpsno", bsid, biaoduan);
				} else {
					toplist = topspeed1DAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<TopRealGpsdataView> limitTpSpeedlist(HttpServletRequest request) {
		String queryString = "from TopRealGpsdataView as model";
		List<TopRealGpsdataView> toplist = new ArrayList<TopRealGpsdataView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			toplist = topspeedDAO.find(queryString+" where model.shebeileixin=?", "5");
		} else {
			queryString = "from TopRealGpsdataView as model where model.shebeileixin=? and model." + fn
					+ "=?";
			toplist = topspeedDAO.find(queryString,"5",bsid);
		}
		return toplist;
	}
	
	public List<TopRealGpsdataView> limitYlSpeedlist(HttpServletRequest request) {
		String queryString = "from TopRealGpsdataView as model";
		List<TopRealGpsdataView> toplist = new ArrayList<TopRealGpsdataView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			toplist = topgpsDAO.find(queryString+" where model.shebeileixin=?", "4");
		} else {
			queryString = "from TopRealGpsdataView as model where model.shebeileixin=? and model." + fn
					+ "=?";
			toplist = topgpsDAO.find(queryString,"4",bsid);
		}
		
		return toplist;
	}
	
	public List<TopRealGpsdataView> limitGpslist(HttpServletRequest request) {
		String queryString = "from TopRealGpsdataView as model";
		List<TopRealGpsdataView> toplist = new ArrayList<TopRealGpsdataView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			toplist = topgpsDAO.find(queryString);
		} else {
			queryString = "from TopRealGpsdataView as model where model." + fn
					+ "=?";
			toplist = topgpsDAO.find(queryString,bsid);
		}
		return toplist;
	}
	
	public TopRealTemperaturedataView limitTpTemperaturedata(HttpServletRequest request, String sbbh) {
		String queryString = "from TopRealTemperaturedataView as model";
		List<TopRealTemperaturedataView> toplist = new ArrayList<TopRealTemperaturedataView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			toplist = toptmpDAO.find(queryString+" where model.shebeileixin=? and model.tmpno=?", "5", sbbh);
		} else {
			queryString = "from TopRealTemperaturedataView as model where model.shebeileixin=? and model.tmpno=? and model." + fn
					+ "=?";
			toplist = toptmpDAO.find(queryString,"5",sbbh,bsid);
		}
		if (null != toplist && toplist.size() > 0) {
			return toplist.get(0);
		} else {
            return null;
		}
	}
	
	public Gpsdata getTpGpsdata(String sbbh) {
		return gpsDAO.getTopGpsdata(sbbh);
	}
	
	public List<TopRealSpeeddataView> limitSpeedlist(Integer biaoduan, String proname, int bsid) {
		String queryString = "from TopRealSpeeddataView as model where model.biaoduanid=?";
		if (null != proname && !proname.equalsIgnoreCase("all")) {
			queryString = "from TopRealSpeeddataView as model where model.biaoduanid=? and "+ proname+"=?";
			return topspeedDAO.find(queryString, biaoduan, bsid);
		} else {
			return topspeedDAO.find(queryString, biaoduan);
		}
	}
	
	public List<TopRealTemperaturedataView> limitTemperaturelist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealTemperaturedataView as model";
		List<TopRealTemperaturedataView> toplist = new ArrayList<TopRealTemperaturedataView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = toptmpDAO.find(queryString+" where model.xiangmubuid=? ORDER BY tmpno", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = toptmpDAO.find(queryString+" where model.biaoduanid=? ORDER BY tmpno", biaoduan);
				} else {
					toplist = toptmpDAO.find("from TopRealTemperaturedataView ORDER BY tmpno");
				}
			}
		} else {
			queryString = "from TopRealTemperaturedataView as model where model." + fn
					+ "=? ORDER BY tmpno";
			if (null != xiangmubu) {
				toplist = toptmpDAO.find(queryString+" and model.xiangmubuid=? ORDER BY tmpno", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = toptmpDAO.find(queryString+" and model.biaoduanid=? ORDER BY tmpno", bsid, biaoduan);
				} else {
					toplist = toptmpDAO.find(queryString,bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<TopRealTemperaturedata1View> limitYaluTemperaturelist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealTemperaturedata1View as model";
		List<TopRealTemperaturedata1View> toplist = new ArrayList<TopRealTemperaturedata1View>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = toptmp1DAO.find(queryString+" where model.xiangmubuid=? ORDER BY tmpno", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = toptmp1DAO.find(queryString+" where model.biaoduanid=? ORDER BY tmpno", biaoduan);
				} else {
					toplist = toptmp1DAO.find("from TopRealTemperaturedata1View ORDER BY tmpno");
				}
			}
		} else {
			queryString = "from TopRealTemperaturedata1View as model where model." + fn
					+ "=? ORDER BY tmpno";
			if (null != xiangmubu) {
				toplist = toptmp1DAO.find(queryString+" and model.xiangmubuid=? ORDER BY tmpno", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = toptmp1DAO.find(queryString+" and model.biaoduanid=? ORDER BY tmpno", bsid, biaoduan);
				} else {
					toplist = toptmp1DAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	//出料口报警
	public List<TopRealChuliaokouTemperaturedataView> limitChuliaokouTemperaturelist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealChuliaokouTemperaturedataView as model";
		List<TopRealChuliaokouTemperaturedataView> toplist = new ArrayList<TopRealChuliaokouTemperaturedataView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = topchuliaokoutmpDAO.find(queryString+" where model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topchuliaokoutmpDAO.find(queryString+" where model.biaoduanid=?", biaoduan);
				} else {
					toplist = topchuliaokoutmpDAO.loadAll();
				}
			}
		} else {
			queryString = "from TopRealChuliaokouTemperaturedataView as model where model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = topchuliaokoutmpDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topchuliaokoutmpDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = topchuliaokoutmpDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<TopRealTjjdataView> limitTjjlist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealTjjdataView as model";
		List<TopRealTjjdataView> toplist = new ArrayList<TopRealTjjdataView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = toptjjDAO.find(queryString+" where model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = toptjjDAO.find(queryString+" where model.biaoduanid=?", biaoduan);
				} else {
					toplist = toptjjDAO.loadAll();
				}
			}
		} else {
			queryString = "from TopRealTjjdataView as model where model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = toptjjDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = toptjjDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = toptjjDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<TopRealTjjdataView> limitTjjlist(Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealTjjdataView as model";
		List<TopRealTjjdataView> toplist = new ArrayList<TopRealTjjdataView>();
		if (null != xiangmubu) {
			toplist = toptjjDAO.find(queryString+" where model.xiangmubuid=?", xiangmubu);
		} else {
			if (null != biaoduan) {
				toplist = toptjjDAO.find(queryString+" where model.biaoduanid=?", biaoduan);
			} else {
				toplist = toptjjDAO.loadAll();
			}
		}
		return toplist;
	}
	
	public List<TopRealTemperaturedataView> limitTemperaturelist(Integer biaoduan, String proname, int bsid) {
		String queryString = "from TopRealTemperaturedataView as model where model.biaoduanid=?";
		if (null != proname && !proname.equalsIgnoreCase("all")) {
			queryString = "from TopRealTemperaturedataView as model where model.biaoduanid=? and "+ proname+"=?";
			return toptmpDAO.find(queryString, biaoduan, bsid);
		} else {
			return toptmpDAO.find(queryString, biaoduan);
		}
	}
	
	public List<TopRealTjjdataView> limitTjjlist(Integer biaoduan, String proname, int bsid) {
		String queryString = "from TopRealTjjdataView as model where model.biaoduanid=?";
		if (null != proname && !proname.equalsIgnoreCase("all")) {
			queryString = "from TopRealTjjdataView as model where model.biaoduanid=? and "+ proname+"=?";
			return toptjjDAO.find(queryString, biaoduan, bsid);
		} else {
			return toptjjDAO.find(queryString, biaoduan);
		}
	}
	
	public List<TopLiqingView> limitlqlist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopLiqingView as model";
		List<TopLiqingView> toplist = new ArrayList<TopLiqingView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = toplqDAO.find(queryString+" where model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = toplqDAO.find(queryString+" where model.biaoduanid=?", biaoduan);
				} else {
					toplist = toplqDAO.loadAll();
				}
			}
		} else {
			queryString = "from TopLiqingView as model where model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = toplqDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = toplqDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = toplqDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<Banhezhanxinxi> limitbhzlist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu,String leixing) {
		String queryString="";
		if(StringUtil.Null2Blank(leixing).length()>0){
			queryString= "from Banhezhanxinxi as model where model.shebeileixin='"+leixing+"' and 1=1 ";
		}else{
			queryString= "from Banhezhanxinxi as model where 1=1 ";
		}
		List<Banhezhanxinxi> toplist = new ArrayList<Banhezhanxinxi>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = bhzDAO.find(queryString+" and model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = bhzDAO.find(queryString+" and model.biaoduanid=?", biaoduan);
				} else {
					toplist = bhzDAO.find(queryString);
				}
			}
		} else {
			if(StringUtil.Null2Blank(leixing).length()>0){
				queryString = "from Banhezhanxinxi as model where model.shebeileixin='"+leixing+"' and model." + fn+ "=? ";
			}else{
				queryString = "from Banhezhanxinxi as model where model." + fn+ "=? ";
			}
			if (null != xiangmubu) {
				toplist = bhzDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = bhzDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = bhzDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List limitgetListbyField(HttpServletRequest request, String fieldname, Integer biaoduan, 
			Integer xiangmubu, String shebeibianhao){
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		String querySql = "SELECT %s FROM xiangxixx GROUP BY %s";
		if (fn.equalsIgnoreCase("all")) {
			if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
				querySql = "SELECT %s FROM xiangxixx where shebeibianhao='"+shebeibianhao+"' GROUP BY %s";
			} else {
				if (null != xiangmubu) {
					querySql = "SELECT %s FROM HunnintuView where xiangmubuid="+xiangmubu+" GROUP BY %s";
				} else {
					if (null != biaoduan) {
						querySql = "SELECT %s FROM HunnintuView where biaoduanid="+biaoduan+" GROUP BY %s";
					}
				}			
			}
		} else {
			querySql = "SELECT %s FROM HunnintuView as model where model." + fn
					+ "="+bsid+" GROUP BY %s";
			if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
				querySql = "SELECT %s FROM HunnintuView as model where model." + fn
				+ "="+bsid+" and model.shebeibianhao='"+shebeibianhao+"' GROUP BY %s";
			} else {
				if (null != xiangmubu) {
					querySql = "SELECT %s FROM HunnintuView as model where model."
							+ fn
							+ "="
							+ bsid
							+ " and model.xiangmubuid="
							+ xiangmubu + " GROUP BY %s";
				} else {
					if (null != biaoduan) {
						querySql = "SELECT %s FROM HunnintuView as model where model."
								+ fn
								+ "="
								+ bsid
								+ " and model.biaoduanid="
								+ biaoduan + " GROUP BY %s";
					}
				}
			}
		}
		return topDAO.findBySql(String.format(querySql, fieldname, fieldname));
	}
	
	public List getListbyField(String tablename, String fieldname){
		String querySql = "SELECT %s FROM %S GROUP BY %s";
		return topDAO.findBySql(String.format(querySql, fieldname, tablename, fieldname));
	}
	
	public List limitgetListbyMulField(HttpServletRequest request, String fieldname, Integer biaoduan, 
			Integer xiangmubu, String shebeibianhao){
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		String querySql = "SELECT %s FROM xiangxixx GROUP BY %s";
		if (fn.equalsIgnoreCase("all")) {
			if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
				querySql = "SELECT %s FROM xiangxixx where shebeibianhao in ("+shebeibianhao+") GROUP BY %s";
			} else {
				if (null != xiangmubu) {
					querySql = "SELECT %s FROM HunnintuView where xiangmubuid="+xiangmubu+" GROUP BY %s";
				} else {
					if (null != biaoduan) {
						querySql = "SELECT %s FROM HunnintuView where biaoduanid="+biaoduan+" GROUP BY %s";
					}
				}			
			}
		} else {
			querySql = "SELECT %s FROM HunnintuView as model where model." + fn
					+ "="+bsid+" GROUP BY %s";
			if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
				querySql = "SELECT %s FROM HunnintuView as model where model." + fn
				+ "="+bsid+" and model.shebeibianhao in ("+shebeibianhao+") GROUP BY %s";
			} else {
				if (null != xiangmubu) {
					querySql = "SELECT %s FROM HunnintuView as model where model."
							+ fn
							+ "="
							+ bsid
							+ " and model.xiangmubuid="
							+ xiangmubu + " GROUP BY %s";
				} else {
					if (null != biaoduan) {
						querySql = "SELECT %s FROM HunnintuView as model where model."
								+ fn
								+ "="
								+ bsid
								+ " and model.biaoduanid="
								+ biaoduan + " GROUP BY %s";
					}
				}
			}
		}
		return topDAO.findBySql(String.format(querySql, fieldname, fieldname));
	}
	
	public void bhzadd(Banhezhanxinxi bhz) {
		bhzDAO.saveOrUpdate(bhz);
	}
	
	public void bhzdel(HttpServletRequest request, Integer bhzId) {
		Banhezhanxinxi bhzxx = bhzfindById(request, bhzId);
		if (null != bhzxx) {
			bhzDAO.delete(bhzxx);
		}
	}
	
	public Banhezhanxinxi bhzfindById(HttpServletRequest request, Integer bhzId) {
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		Banhezhanxinxi bhzxx = null;
		if (fn.equalsIgnoreCase("all")) {
			bhzxx = bhzDAO.get(bhzId);
		} else {
			String queryString = "from Banhezhanxinxi as model where model.id=? and model." + fn
					+ "=?";			
			List<Banhezhanxinxi> bhzlist = bdDAO.find(queryString, bhzId, bsid);
			if (bhzlist.size()>0) {
				bhzxx = bhzlist.get(0);
			}
		}
		return bhzxx;	
	}
	
	
	public List<HntbhzziduancfgView> limithntcfglist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from HntbhzziduancfgView as model where model.leixin='1'";
		List<HntbhzziduancfgView> toplist = new ArrayList<HntbhzziduancfgView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = hntviewDAO.find(queryString+" and model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = hntviewDAO.find(queryString+" and model.biaoduanid=?", biaoduan);
				} else {
					toplist = hntviewDAO.find(queryString);
				}
			}
		} else {
			queryString = "from HntbhzziduancfgView as model where model.leixin='1' and model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = hntviewDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = hntviewDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = hntviewDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<LiqingziduancfgView> limitliqingcfglist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from LiqingziduancfgView as model where model.leixin='1'";
		List<LiqingziduancfgView> toplist = new ArrayList<LiqingziduancfgView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = lqviewDAO.find(queryString+" and model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = lqviewDAO.find(queryString+" and model.biaoduanid=?", biaoduan);
				} else {
					toplist = lqviewDAO.find(queryString);
				}
			}
		} else {
			queryString = "from LiqingziduancfgView as model where model.leixin='1' and model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = lqviewDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = lqviewDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = lqviewDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<TopRealMaxhunnintuView> limitsmscfglist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealMaxhunnintuView as model where model.smsbaojin='1'";
		List<TopRealMaxhunnintuView> toplist = new ArrayList<TopRealMaxhunnintuView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = realMaxDAO.find(queryString+" and model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = realMaxDAO.find(queryString+" and model.biaoduanid=?", biaoduan);
				} else {
					toplist = realMaxDAO.find(queryString);
				}
			}
		} else {
			queryString = "from TopRealMaxhunnintuView as model where model.smsbaojin='1' and model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = realMaxDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = realMaxDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = realMaxDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public List<TopRealMaxLiqingView> limitlqsmscfglist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealMaxLiqingView as model where model.smsbaojin='1'";
		List<TopRealMaxLiqingView> toplist = new ArrayList<TopRealMaxLiqingView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = realMaxlqDAO.find(queryString+" and model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = realMaxlqDAO.find(queryString+" and model.biaoduanid=?", biaoduan);
				} else {
					toplist = realMaxlqDAO.find(queryString);
				}
			}
		} else {
			queryString = "from TopRealMaxLiqingView as model where model.smsbaojin='1' and model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = realMaxlqDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = realMaxlqDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = realMaxlqDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	public Hntbhzziduancfg gethntcfgHeader() {
		return hntDAO.findByGprsbhandleixin("1", "3");
	}
	
	public Liqingziduancfg getDefaultLiqingcfgHeader() {
		Liqingziduancfg lqcfg = new Liqingziduancfg();
		lqcfg.setBaocunshijian("baocunshijian");
		lqcfg.setShijian("shijian");
		lqcfg.setBeiy1("beiy1");
		lqcfg.setBeiy2("beiy2");
		lqcfg.setBeiy3("beiy3");
		lqcfg.setCaijishijian("caijishijian");
		lqcfg.setChangliang("changliang");
		lqcfg.setClwd("clwd");
		lqcfg.setGcmc("gcmc");
		lqcfg.setGlwd("glwd");
		lqcfg.setJbsj("jbsj");
		lqcfg.setKehuduanbianhao("kehuduanbianhao");
		lqcfg.setKhmc("khmc");
		lqcfg.setLjsl("ljsl");
		lqcfg.setLlf1("llf1");
		lqcfg.setLlf2("llf2");
		lqcfg.setLlg1("llg1");
		lqcfg.setLlg2("llg2");
		lqcfg.setLlg3("llg3");
		lqcfg.setLlg4("llg4");
		lqcfg.setLlg5("llg5");
		lqcfg.setLlg6("llg6");
		lqcfg.setLlg7("llg7");
		lqcfg.setLllq("lllq");
		lqcfg.setLltjj("lltjj");
		lqcfg.setLlysb("llysb");
		lqcfg.setLqwd("lqwd");
		lqcfg.setPeifan("peifan");
		lqcfg.setSgbw("sgbw");
		lqcfg.setShebeibianhao("shebeibianhao");
		lqcfg.setShijian("shijian");
		lqcfg.setSjf1("sjf1");
		lqcfg.setSjf2("sjf2");

		lqcfg.setSjg1("sjg1");
		lqcfg.setSjg2("sjg2");
		lqcfg.setSjg3("sjg3");
		lqcfg.setSjg4("sjg4");
		lqcfg.setSjg5("sjg5");
		lqcfg.setSjg6("sjg6");
		lqcfg.setSjg7("sjg7");

		lqcfg.setSjlq("sjlq");
		lqcfg.setSjtjj("sjtjj");
		lqcfg.setSjysb("sjysb");
		lqcfg.setYonghu("yonghu");

		return lqcfg;
	}	
	
	public Liqingziduancfg getliqingcfgHeader() {
		Liqingziduancfg lqcfg = lqcfgDAO.findByGprsbhandleixin("1", "3");
		if (null == lqcfg) {
			Liqingziduancfg lqfield = getDefaultLiqingcfgHeader();
			lqfield.setGprsbianhao("1");
			lqfield.setLeixin("3");
			lqcfgDAO.save(lqfield);
			lqcfg = lqfield;
		}
		return lqcfg;
	}
	
	public void hntcfgadd(Hntbhzziduancfg hntcfg) {
		hntDAO.saveOrUpdate(hntcfg);
	}
	
	public void lqcfgadd(Liqingziduancfg lqcfg) {
		lqcfgDAO.saveOrUpdate(lqcfg);
	}
	
	public void xccfgadd(Xcsmscfg xccfg) {
		xcsmscfgDAO.saveOrUpdate(xccfg);
	}
	
	
	public Xcsmscfg getXcsmscfg2(String shebeibianhao){
		return xcsmscfgDAO.get(shebeibianhao);
	}
	
	public Clksmscfg getClksmscfg2(String shebeibianhao){
		return clksmscfgDAO.get(shebeibianhao);
	}
	//保存出料口
	public void clkcfgadd(Clksmscfg clkcfg){
		clksmscfgDAO.saveOrUpdate(clkcfg);
	}
	
	public void liqingcfgadd(Liqingziduancfg lqcfg) {
		lqcfgDAO.saveOrUpdate(lqcfg);
	}
	
	public void hntcfgdel(Integer cfgId, String gprsbh) {
		hntDAO.deleteByKey(cfgId);
		Hntbhzziduancfg hntcfgisshow = hntisshowcfgBybh(gprsbh);
		if (null != hntcfgisshow) {
			hntDAO.delete(hntcfgisshow);
		}
	}
	
	public void liqingcfgdel(Integer cfgId, String gprsbh) {
		lqcfgDAO.deleteByKey(cfgId);
		Liqingziduancfg lqcfgisshow = liqingisshowcfgBybh(gprsbh);
		if (null != lqcfgisshow) {
			lqcfgDAO.delete(lqcfgisshow);
		}
	}
	
	public void smscfgdel(String gprsbh) {
		Hntbhzziduancfg hntcfg = hntissmsfindBybh(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmslowfindBybh(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmshighfindBybh(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmsnumberfindBybh(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmscontentfindBybh(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmsshejifindBybh(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmsshejifindBybh2(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmslowfindBybh2(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmshighfindBybh2(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmsnumberfindBybh2(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmslowfindBybh3(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmshighfindBybh3(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
		hntcfg = hntsmsnumberfindBybh3(gprsbh);
		if (null != hntcfg) {
			hntDAO.delete(hntcfg);
		}
	}
	
	public void lqsmscfgdel(String gprsbh) {
		Liqingziduancfg lqcfg = lqissmsfindBybh(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmslowfindBybh(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmshighfindBybh(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmsnumberfindBybh(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmscontentfindBybh(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmsshejifindBybh(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmsshejifindBybh2(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmslowfindBybh2(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmshighfindBybh2(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmsnumberfindBybh2(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmslowfindBybh3(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmshighfindBybh3(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
		lqcfg = lqsmsnumberfindBybh3(gprsbh);
		if (null != lqcfg) {
			lqcfgDAO.delete(lqcfg);
		}
	}
	
	
	public Hntbhzziduancfg hntcfgfindById(Integer cfgId) {
		return hntDAO.get(cfgId);	
	}
	
	public Liqingziduancfg liqingcfgfindById(Integer cfgId) {
		return lqcfgDAO.get(cfgId);	
	}
	
	public Hntbhzziduancfg getDefaultziduanshow() {
		Hntbhzziduancfg hbisshow = new Hntbhzziduancfg();
		hbisshow.setShui1_shijizhi("1");
		hbisshow.setShui1_lilunzhi("0");
		hbisshow.setShui2_shijizhi("0");
		hbisshow.setShui2_lilunzhi("0");
		hbisshow.setShuini1_shijizhi("1");
		hbisshow.setShuini1_lilunzhi("0");
		hbisshow.setShuini2_shijizhi("1");
		hbisshow.setShuini2_lilunzhi("0");
		hbisshow.setKuangfen3_shijizhi("1");
		hbisshow.setKuangfen3_lilunzhi("0");
		hbisshow.setFeimeihui4_shijizhi("1");
		hbisshow.setFeimeihui4_lilunzhi("0");
		hbisshow.setFenliao5_shijizhi("0");
		hbisshow.setFenliao5_lilunzhi("0");
		hbisshow.setFenliao6_shijizhi("0");
		hbisshow.setFenliao6_lilunzhi("0");
		hbisshow.setSha1_shijizhi("1");
		hbisshow.setSha1_lilunzhi("0");
		hbisshow.setShi1_shijizhi("1");
		hbisshow.setShi1_lilunzhi("0");
		hbisshow.setShi2_shijizhi("1");
		hbisshow.setShi2_lilunzhi("0");
		hbisshow.setSha2_shijizhi("1");
		hbisshow.setSha2_lilunzhi("0");
		hbisshow.setGuliao5_shijizhi("0");
		hbisshow.setGuliao5_lilunzhi("0");
		hbisshow.setWaijiaji1_shijizhi("1");
		hbisshow.setWaijiaji1_lilunzhi("0");
		hbisshow.setWaijiaji2_shijizhi("0");
		hbisshow.setWaijiaji2_lilunzhi("0");
		hbisshow.setWaijiaji3_shijizhi("0");
		hbisshow.setWaijiaji3_lilunzhi("0");
		hbisshow.setWaijiaji4_shijizhi("0");
		hbisshow.setWaijiaji4_lilunzhi("0");				
		hbisshow.setGongdanhao("0");
		hbisshow.setChaozuozhe("0");
		hbisshow.setChuliaoshijian("0");
		hbisshow.setGongchengmingcheng("1");
		hbisshow.setSigongdidian("0");
		hbisshow.setJiaozuobuwei("1");
		hbisshow.setShuinipingzhong("0");
		hbisshow.setWaijiajipingzhong("0");
		hbisshow.setPeifanghao("0");
		hbisshow.setQiangdudengji("1");
		hbisshow.setJiaobanshijian("0");
		hbisshow.setShebeibianhao("0");
		hbisshow.setBaocunshijian("1");
		hbisshow.setCaijishijian("0");
		hbisshow.setGujifangshu("1");
		return hbisshow;
	}
	
	public Liqingziduancfg getDefaultLiqingziduanshow() {
		Liqingziduancfg lqisshow = new Liqingziduancfg();
		lqisshow.setBaocunshijian("0");
		lqisshow.setBeiy1("0");
		lqisshow.setBeiy2("0");
		lqisshow.setBeiy3("0");
		lqisshow.setCaijishijian("0");
		lqisshow.setChangliang("0");
		lqisshow.setGlwd("1");
		lqisshow.setJbsj("0");
		lqisshow.setKehuduanbianhao("0");
		lqisshow.setLqwd("0");
		lqisshow.setClwd("1");
		lqisshow.setPeifan("0");
		lqisshow.setShebeibianhao("0");
		lqisshow.setShijian("1");
		lqisshow.setSjf1("1");
		lqisshow.setSjf2("0");
		lqisshow.setSjg1("1");
		lqisshow.setSjg2("1");
		lqisshow.setSjg3("1");
		lqisshow.setSjg4("1");
		lqisshow.setSjg5("1");
		lqisshow.setSjg6("0");
		lqisshow.setSjg7("0");
		lqisshow.setSjlq("1");
		lqisshow.setSjtjj("0");
		lqisshow.setSjysb("1");
		lqisshow.setLlf1("0");
		lqisshow.setLlf2("0");
		lqisshow.setLlg1("0");
		lqisshow.setLlg2("0");
		lqisshow.setLlg3("0");
		lqisshow.setLlg4("0");
		lqisshow.setLlg5("0");
		lqisshow.setLlg6("0");
		lqisshow.setLlg7("0");
		lqisshow.setLllq("0");
		lqisshow.setLltjj("0");
		lqisshow.setLlysb("0");
		lqisshow.setBh("0");
		lqisshow.setKhmc("0");
		lqisshow.setGcmc("0");
		lqisshow.setSgbw("0");
		lqisshow.setLjsl("0");
		lqisshow.setYonghu("0");
		return lqisshow;
	}
	
	//获取拌和站字段是否显示,并设置默认值
	public Hntbhzziduancfg hntisshowcfgBybh(String gprsbh) {
		Hntbhzziduancfg hcfg = hntDAO.findByGprsbhandleixin(gprsbh, "2");	
		if (null == hcfg) {
			hcfg = hntDAO.findByGprsbhandleixin("all", "101");
			if (null == hcfg) {
				Hntbhzziduancfg hbisshow = getDefaultziduanshow();
				hbisshow.setGprsbianhao("all");
				hbisshow.setLeixin("101");				
				hntDAO.save(hbisshow);
				hcfg = hbisshow;
			}
		}
		return hcfg;
	}
	
	public Liqingziduancfg liqingisshowcfgBybh(String gprsbh) {
		Liqingziduancfg lqcfg = lqcfgDAO.findByGprsbhandleixin(gprsbh, "2");	
		if (null == lqcfg) {
			lqcfg = lqcfgDAO.findByGprsbhandleixin("all", "101");
			if (null == lqcfg) {
				Liqingziduancfg lqisshow = getDefaultLiqingziduanshow();
				lqisshow.setGprsbianhao("all");
				lqisshow.setLeixin("101");				
				lqcfgDAO.save(lqisshow);
				lqcfg = lqisshow;
			}
		}
		return lqcfg;
	}

	public Hntbhzziduancfg getDefaultziduancfg() {
		Hntbhzziduancfg hbfield = new Hntbhzziduancfg();
		hbfield.setShui1_shijizhi("水");
		hbfield.setShui1_lilunzhi("水(施工)");
		hbfield.setShui2_shijizhi("水2");
		hbfield.setShui2_lilunzhi("水2(施工)");
		hbfield.setShuini1_shijizhi("水泥1");
		hbfield.setShuini1_lilunzhi("水泥1(施工)");
		hbfield.setShuini2_shijizhi("水泥2");
		hbfield.setShuini2_lilunzhi("水泥2(施工)");
		hbfield.setKuangfen3_shijizhi("矿粉");
		hbfield.setKuangfen3_lilunzhi("矿粉(施工)");
		hbfield.setFeimeihui4_shijizhi("煤灰");
		hbfield.setFeimeihui4_lilunzhi("煤灰(施工)");
		hbfield.setFenliao5_shijizhi("粉料5");
		hbfield.setFenliao5_lilunzhi("粉料5(施工)");
		hbfield.setFenliao6_shijizhi("粉料6");
		hbfield.setFenliao6_lilunzhi("粉料6(施工)");
		hbfield.setSha1_shijizhi("砂1");
		hbfield.setSha1_lilunzhi("砂1(施工)");
		hbfield.setShi1_shijizhi("砂2");
		hbfield.setShi1_lilunzhi("砂2(施工)");
		hbfield.setShi2_shijizhi("碎石1");
		hbfield.setShi2_lilunzhi("碎石1(施工)");
		hbfield.setSha2_shijizhi("碎石2");
		hbfield.setSha2_lilunzhi("碎石2(施工)");
		hbfield.setGuliao5_shijizhi("碎石3");
		hbfield.setGuliao5_lilunzhi("碎石3(施工)");
		hbfield.setWaijiaji1_shijizhi("减水剂1");
		hbfield.setWaijiaji1_lilunzhi("减水剂1(施工)");
		hbfield.setWaijiaji2_shijizhi("减水剂2");
		hbfield.setWaijiaji2_lilunzhi("减水剂2(施工)");
		hbfield.setWaijiaji3_shijizhi("外加剂3");
		hbfield.setWaijiaji3_lilunzhi("外加剂3(施工)");
		hbfield.setWaijiaji4_shijizhi("外加剂4");
		hbfield.setWaijiaji4_lilunzhi("外加剂4(施工)");				
		hbfield.setGongdanhao("工单号");
		hbfield.setChaozuozhe("操作者");
		hbfield.setChuliaoshijian("出料时间");
		hbfield.setGongchengmingcheng("工程名称");
		hbfield.setSigongdidian("地点/里程");
		hbfield.setJiaozuobuwei("浇筑部位");
		hbfield.setShuinipingzhong("水泥品种");
		hbfield.setWaijiajipingzhong("外加剂品种");
		hbfield.setPeifanghao("施工配合比编号");
		hbfield.setQiangdudengji("强度等级");
		hbfield.setJiaobanshijian("搅拌时间");
		hbfield.setShebeibianhao("设备编号");
		hbfield.setBaocunshijian("保存时间");
		hbfield.setCaijishijian("采集时间");
		hbfield.setGujifangshu("数量");
		return hbfield;
	}
	
	public Liqingziduancfg getDefaultLiqingziduancfg() {
		Liqingziduancfg lqfield = new Liqingziduancfg();
		lqfield.setBaocunshijian("保存时间");
		lqfield.setBeiy1("备用1");
		lqfield.setBeiy2("备用2");
		lqfield.setBeiy3("备用3");
		lqfield.setCaijishijian("采集时间");
		lqfield.setChangliang("每锅产量");
		lqfield.setGlwd("石料温度");
		lqfield.setJbsj("搅拌时间");
		lqfield.setKehuduanbianhao("客户端编号");
		lqfield.setLqwd("沥青温度");
		lqfield.setClwd("出料温度");
		lqfield.setPeifan("配方");
		lqfield.setShebeibianhao("设备编号");
		lqfield.setShijian("出料时间");
		lqfield.setSjf1("粉料1");
		lqfield.setSjf2("粉料2");
		lqfield.setSjg1("石料1");
		lqfield.setSjg2("石料2");
		lqfield.setSjg3("石料3");
		lqfield.setSjg4("石料4");
		lqfield.setSjg5("石料5");
		lqfield.setSjg6("石料6");
		lqfield.setSjg7("石料7");
		lqfield.setSjlq("沥青");
		lqfield.setSjtjj("添加剂");
		lqfield.setSjysb("油石比");
		lqfield.setLlf1("理论粉料1");
		lqfield.setLlf2("理论粉料2");
		lqfield.setLlg1("理论石料1");
		lqfield.setLlg2("理论石料2");
		lqfield.setLlg3("理论石料3");
		lqfield.setLlg4("理论石料4");
		lqfield.setLlg5("理论石料5");
		lqfield.setLlg6("理论石料6");
		lqfield.setLlg7("理论石料7");
		lqfield.setLllq("理论沥青");
		lqfield.setLltjj("理论添加剂");
		lqfield.setLlysb("理论油石比");
		lqfield.setBh("编号");
		lqfield.setKhmc("客户名称");
		lqfield.setGcmc("工程名称");
		lqfield.setSgbw("施工部位");
		lqfield.setLjsl("累计数量");
		lqfield.setYonghu("用户");
		return lqfield;
	}
	
	//获取拌和站字段名称,并设置默认值
	public Hntbhzziduancfg hntfieldnameBybh(String gprsbh) {
		Hntbhzziduancfg hcfg =  hntDAO.findByGprsbhandleixin(gprsbh, "1");	
		if (null == hcfg) {
			hcfg = hntDAO.findByGprsbhandleixin("all", "100");
			if (null == hcfg) {
				Hntbhzziduancfg hbfield = getDefaultziduancfg();
				hbfield.setGprsbianhao("all");
				hbfield.setLeixin("100");				
				hntDAO.save(hbfield);
				hcfg = hbfield;
			}
		}
		return hcfg;
	}
	
	public Liqingziduancfg liqingfieldnameBybh(String gprsbh) {
		Liqingziduancfg lqcfg =  lqcfgDAO.findByGprsbhandleixin(gprsbh, "1");	
		if (null == lqcfg) {
			lqcfg = lqcfgDAO.findByGprsbhandleixin("all", "100");
			if (null == lqcfg) {
				Liqingziduancfg lqfield = getDefaultLiqingziduancfg();
				lqfield.setGprsbianhao("all");
				lqfield.setLeixin("100");				
				lqcfgDAO.save(lqfield);
				lqcfg = lqfield;
			}
		}
		return lqcfg;
	}
	//获取拌和站字段名称
	public HntbhzziduancfgView hntfieldnamefindBybh(String gprsbh) {
		HntbhzziduancfgView hcfg =  hntviewDAO.findByGprsbhandleixin(gprsbh, "1");	
		if (null == hcfg) {
			hcfg = hntviewDAO.findByGprsbhandleixin("all", "100");
		}
		return hcfg;	
	}
	
	//获取拌和站字段名称
	public LiqingziduancfgView lqfieldnamefindBybh(String gprsbh) {
		LiqingziduancfgView lqcfg =  lqviewDAO.findByGprsbhandleixin(gprsbh, "1");	
		if (null == lqcfg) {
			lqcfg = lqviewDAO.findByGprsbhandleixin("all", "100");
		}
		return lqcfg;	
	}
	
	//获取拌和站字段是否短信报警
	public Hntbhzziduancfg hntissmsfindBybh(String gprsbh) {
		Hntbhzziduancfg issmscfg = hntDAO.findByGprsbhandleixin(gprsbh, "4");
		if (null == issmscfg) {
			issmscfg = getDefaultissmscfg(gprsbh);
		}
		return issmscfg;
	}
	
	//获取拌和站字段是否短信报警
	public Liqingziduancfg lqissmsfindBybh(String gprsbh) {
		Liqingziduancfg issmscfg = lqcfgDAO.findByGprsbhandleixin(gprsbh, "4");
		if (null == issmscfg) {
			issmscfg = getDefaultissmslqcfg(gprsbh);
		}
		return issmscfg;
	}
	
	public Hntbhzziduancfg getDefaultissmscfg(String gprsbh) {
		Hntbhzziduancfg hbissmscfg = new Hntbhzziduancfg();
		hbissmscfg.setLeixin("4");
		hbissmscfg.setGprsbianhao(gprsbh);
		hbissmscfg.setShui1_shijizhi("1");
		hbissmscfg.setShuini1_shijizhi("1");
		hbissmscfg.setShuini2_shijizhi("1");
		hbissmscfg.setKuangfen3_shijizhi("1");
		hbissmscfg.setFeimeihui4_shijizhi("1");
		hbissmscfg.setSha1_shijizhi("1");
		hbissmscfg.setShi1_shijizhi("1");
		hbissmscfg.setShi2_shijizhi("1");
		hbissmscfg.setSha2_shijizhi("1");
		hbissmscfg.setWaijiaji1_shijizhi("1");
		return hbissmscfg;
	}
	
	public Liqingziduancfg getDefaultissmslqcfg(String gprsbh) {
		Liqingziduancfg lqissmscfg = new Liqingziduancfg();
		lqissmscfg.setLeixin("4");
		lqissmscfg.setGprsbianhao(gprsbh);
		lqissmscfg.setSjg1("1");
		lqissmscfg.setSjg2("1");
		lqissmscfg.setSjg3("1");
		lqissmscfg.setSjg4("1");
		lqissmscfg.setSjg5("1");
		lqissmscfg.setSjg6("0");
		lqissmscfg.setSjg7("0");
		lqissmscfg.setSjf1("1");
		lqissmscfg.setSjf2("0");
		lqissmscfg.setSjlq("1");
		lqissmscfg.setSjtjj("0");
		lqissmscfg.setSjysb("1");
		lqissmscfg.setLqwd("0");
		lqissmscfg.setGlwd("0");
		lqissmscfg.setJbsj("0");
		return lqissmscfg;
	}
	
	//获取拌和站字段短信报警下限
	public Hntbhzziduancfg hntsmslowfindBybh(String gprsbh) {
		Hntbhzziduancfg smslowcfg = hntDAO.findByGprsbhandleixin(gprsbh, "5");
		if (null == smslowcfg) {
			smslowcfg = getDefaultsmslowcfg(gprsbh);
		}
		return smslowcfg;
	}
	
	public Liqingziduancfg lqsmslowfindBybh(String gprsbh) {
		Liqingziduancfg smslowcfg = lqcfgDAO.findByGprsbhandleixin(gprsbh, "5");
		if (null == smslowcfg) {
			smslowcfg = getDefaultsmslowlqcfg(gprsbh);
		}
		return smslowcfg;
	}
	
	public Hntbhzziduancfg getDefaultsmslowcfg(String gprsbh) {
		Hntbhzziduancfg hbsmslowcfg = new Hntbhzziduancfg();
		hbsmslowcfg.setLeixin("5");
		hbsmslowcfg.setGprsbianhao(gprsbh);
		hbsmslowcfg.setJiaobanshijian("10");
		hbsmslowcfg.setShui1_shijizhi("3");
		hbsmslowcfg.setShuini1_shijizhi("3");
		hbsmslowcfg.setShuini2_shijizhi("3");
		hbsmslowcfg.setKuangfen3_shijizhi("3");
		hbsmslowcfg.setFeimeihui4_shijizhi("3");
		hbsmslowcfg.setSha1_shijizhi("4");
		hbsmslowcfg.setShi1_shijizhi("4");
		hbsmslowcfg.setShi2_shijizhi("4");
		hbsmslowcfg.setSha2_shijizhi("4");
		hbsmslowcfg.setWaijiaji1_shijizhi("4");
		return hbsmslowcfg;
	}
	
	public Liqingziduancfg getDefaultsmslowlqcfg(String gprsbh) {
		Liqingziduancfg lqsmslowcfg = new Liqingziduancfg();
		lqsmslowcfg.setLeixin("5");
		lqsmslowcfg.setGprsbianhao(gprsbh);
		lqsmslowcfg.setSjg1("3");
		lqsmslowcfg.setSjg2("3");
		lqsmslowcfg.setSjg3("3");
		lqsmslowcfg.setSjg4("3");
		lqsmslowcfg.setSjg5("3");
		lqsmslowcfg.setSjg6("3");
		lqsmslowcfg.setSjg7("3");
		lqsmslowcfg.setSjf1("2");
		lqsmslowcfg.setSjf2("2");
		lqsmslowcfg.setSjlq("2");
		lqsmslowcfg.setSjtjj("2");
		lqsmslowcfg.setSjysb("2");
		return lqsmslowcfg;
	}
	
	//获取拌和站字段短信报警上限
	public Hntbhzziduancfg hntsmshighfindBybh(String gprsbh) {
		Hntbhzziduancfg smshighcfg = hntDAO.findByGprsbhandleixin(gprsbh, "6");
		if (null == smshighcfg) {
			smshighcfg = getDefaultsmshighcfg(gprsbh);
		}
		return smshighcfg;
	}
	
	public Liqingziduancfg lqsmshighfindBybh(String gprsbh) {
		Liqingziduancfg smshighcfg = lqcfgDAO.findByGprsbhandleixin(gprsbh, "6");
		if (null == smshighcfg) {
			smshighcfg = getDefaultsmshighlqcfg(gprsbh);
		}
		return smshighcfg;
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
	
	public Hntbhzziduancfg getDefaultsmshighcfg(String gprsbh) {
		Hntbhzziduancfg hbsmshighcfg = new Hntbhzziduancfg();
		hbsmshighcfg.setLeixin("6");
		hbsmshighcfg.setGprsbianhao(gprsbh);
		hbsmshighcfg.setShui1_shijizhi("3");
		hbsmshighcfg.setShuini1_shijizhi("3");
		hbsmshighcfg.setShuini2_shijizhi("3");
		hbsmshighcfg.setKuangfen3_shijizhi("3");
		hbsmshighcfg.setFeimeihui4_shijizhi("3");
		hbsmshighcfg.setSha1_shijizhi("4");
		hbsmshighcfg.setShi1_shijizhi("4");
		hbsmshighcfg.setShi2_shijizhi("4");
		hbsmshighcfg.setSha2_shijizhi("4");
		hbsmshighcfg.setWaijiaji1_shijizhi("4");
		return hbsmshighcfg;
	}
	
	//获取拌和站字段短信报警手机号码
	public Hntbhzziduancfg hntsmsnumberfindBybh(String gprsbh) {
		return hntDAO.findByGprsbhandleixin(gprsbh, "7");		
	}
	
	public Liqingziduancfg lqsmsnumberfindBybh(String gprsbh) {
		return lqcfgDAO.findByGprsbhandleixin(gprsbh, "7");		
	}
	
	//获取拌和站字段短信报警内容格式
	public Hntbhzziduancfg hntsmscontentfindBybh(String gprsbh) {
		return hntDAO.findByGprsbhandleixin(gprsbh, "8");	
	}
	
	public Liqingziduancfg lqsmscontentfindBybh(String gprsbh) {
		return lqcfgDAO.findByGprsbhandleixin(gprsbh, "8");	
	}
	
	//获取拌和站字段短信报警设计值
	public Hntbhzziduancfg hntsmsshejifindBybh(String gprsbh) {
		Hntbhzziduancfg smsshejicfg = hntDAO.findByGprsbhandleixin(gprsbh, "9");
		if (null == smsshejicfg) {
			smsshejicfg = getDefaultsmsshejicfg(gprsbh);
		}
		return smsshejicfg;
	}
	
	public Liqingziduancfg lqsmsshejifindBybh(String gprsbh) {
		Liqingziduancfg smsshejicfg = lqcfgDAO.findByGprsbhandleixin(gprsbh, "9");
		if (null == smsshejicfg) {
			smsshejicfg = getDefaultsmsshejilqcfg(gprsbh);
		}
		return smsshejicfg;
	}
	
	public Hntbhzziduancfg getDefaultsmsshejicfg(String gprsbh) {
		Hntbhzziduancfg hbsmsshejicfg = new Hntbhzziduancfg();
		hbsmsshejicfg.setLeixin("9");
		hbsmsshejicfg.setGprsbianhao(gprsbh);
		hbsmsshejicfg.setJiaobanshijian("80");
		return hbsmsshejicfg;
	}
	
	public Liqingziduancfg getDefaultsmsshejilqcfg(String gprsbh) {
		Liqingziduancfg lqsmsshejicfg = new Liqingziduancfg();
		lqsmsshejicfg.setLeixin("9");
		lqsmsshejicfg.setGprsbianhao(gprsbh);
		lqsmsshejicfg.setJbsj("40");
		lqsmsshejicfg.setLqwd("160");
		lqsmsshejicfg.setGlwd("160");
		return lqsmsshejicfg;
	}
	
	//获取拌和站字段短信报警下限2
	public Hntbhzziduancfg hntsmslowfindBybh2(String gprsbh) {
		Hntbhzziduancfg smslowcfg2 = hntDAO.findByGprsbhandleixin(gprsbh, "10");
		if (null == smslowcfg2) {
			smslowcfg2 = getDefaultsmslowcfg2(gprsbh);
		}
		return smslowcfg2;
	}
	
	public Liqingziduancfg lqsmslowfindBybh2(String gprsbh) {
		Liqingziduancfg smslowcfg2 = lqcfgDAO.findByGprsbhandleixin(gprsbh, "10");
		if (null == smslowcfg2) {
			smslowcfg2 = getDefaultsmslowlqcfg2(gprsbh);
		}
		return smslowcfg2;
	}
	
	public Liqingziduancfg lqsmslowRightfindBybh2(String gprsbh) {
		Liqingziduancfg smslowcfg2 = lqcfgDAO.findByGprsbhandleixin(gprsbh, "17");
		if (null == smslowcfg2) {
			smslowcfg2 = getDefaultsmslowRightlqcfg2(gprsbh);
		}
		return smslowcfg2;
	}
	
	public Hntbhzziduancfg getDefaultsmslowcfg2(String gprsbh) {
		Hntbhzziduancfg hbsmslowcfg2 = new Hntbhzziduancfg();
		hbsmslowcfg2.setLeixin("10");
		hbsmslowcfg2.setGprsbianhao(gprsbh);
		hbsmslowcfg2.setJiaobanshijian("20");
		hbsmslowcfg2.setShui1_shijizhi("6");
		hbsmslowcfg2.setShuini1_shijizhi("6");
		hbsmslowcfg2.setShuini2_shijizhi("6");
		hbsmslowcfg2.setKuangfen3_shijizhi("6");
		hbsmslowcfg2.setFeimeihui4_shijizhi("6");
		hbsmslowcfg2.setSha1_shijizhi("8");
		hbsmslowcfg2.setShi1_shijizhi("8");
		hbsmslowcfg2.setShi2_shijizhi("8");
		hbsmslowcfg2.setSha2_shijizhi("8");
		hbsmslowcfg2.setWaijiaji1_shijizhi("8");
		return hbsmslowcfg2;
	}
	
	public Liqingziduancfg getDefaultsmslowlqcfg2(String gprsbh) {
		Liqingziduancfg lqsmslowcfg = new Liqingziduancfg();
		lqsmslowcfg.setLeixin("10");
		lqsmslowcfg.setGprsbianhao(gprsbh);
		lqsmslowcfg.setSjg1("6");
		lqsmslowcfg.setSjg2("6");
		lqsmslowcfg.setSjg3("6");
		lqsmslowcfg.setSjg4("6");
		lqsmslowcfg.setSjg5("6");
		lqsmslowcfg.setSjg6("6");
		lqsmslowcfg.setSjg7("6");
		lqsmslowcfg.setSjf1("4");
		lqsmslowcfg.setSjf2("4");
		lqsmslowcfg.setSjlq("4");
		lqsmslowcfg.setSjtjj("4");
		lqsmslowcfg.setSjysb("4");
		return lqsmslowcfg;
	}
	
	public Liqingziduancfg getDefaultsmslowRightlqcfg2(String gprsbh) {
		Liqingziduancfg lqsmslowcfg = new Liqingziduancfg();
		lqsmslowcfg.setLeixin("17");
		lqsmslowcfg.setGprsbianhao(gprsbh);
		lqsmslowcfg.setSjysb("4");
		return lqsmslowcfg;
	}
	
	//获取拌和站字段短信报警上限2
	public Hntbhzziduancfg hntsmshighfindBybh2(String gprsbh) {
		Hntbhzziduancfg smshighcfg2 = hntDAO.findByGprsbhandleixin(gprsbh, "11");
		if (null == smshighcfg2) {
			smshighcfg2 = getDefaultsmshighcfg2(gprsbh);
		}
		return smshighcfg2;
	}
	
	public Liqingziduancfg lqsmshighfindBybh2(String gprsbh) {
		Liqingziduancfg smshighcfg2 = lqcfgDAO.findByGprsbhandleixin(gprsbh, "11");
		if (null == smshighcfg2) {
			smshighcfg2 = getDefaultsmshighlqcfg2(gprsbh);
		}
		return smshighcfg2;
	}
	
	public Liqingziduancfg lqsmshighRightfindBybh2(String gprsbh) {
		Liqingziduancfg smshighcfg2 = lqcfgDAO.findByGprsbhandleixin(gprsbh, "18");
		if (null == smshighcfg2) {
			smshighcfg2 = getDefaultsmshighRightlqcfg2(gprsbh);
		}
		return smshighcfg2;
	}
	
	public Hntbhzziduancfg getDefaultsmshighcfg2(String gprsbh) {
		Hntbhzziduancfg hbsmshighcfg2 = new Hntbhzziduancfg();
		hbsmshighcfg2.setLeixin("11");
		hbsmshighcfg2.setGprsbianhao(gprsbh);
		hbsmshighcfg2.setShui1_shijizhi("6");
		hbsmshighcfg2.setShuini1_shijizhi("6");
		hbsmshighcfg2.setShuini2_shijizhi("6");
		hbsmshighcfg2.setKuangfen3_shijizhi("6");
		hbsmshighcfg2.setFeimeihui4_shijizhi("6");
		hbsmshighcfg2.setSha1_shijizhi("8");
		hbsmshighcfg2.setShi1_shijizhi("8");
		hbsmshighcfg2.setShi2_shijizhi("8");
		hbsmshighcfg2.setSha2_shijizhi("8");
		hbsmshighcfg2.setWaijiaji1_shijizhi("8");
		return hbsmshighcfg2;
	}
	
	public Liqingziduancfg getDefaultsmshighlqcfg2(String gprsbh) {
		Liqingziduancfg lqsmshighcfg = new Liqingziduancfg();
		lqsmshighcfg.setLeixin("11");
		lqsmshighcfg.setGprsbianhao(gprsbh);
		lqsmshighcfg.setSjg1("6");
		lqsmshighcfg.setSjg2("6");
		lqsmshighcfg.setSjg3("6");
		lqsmshighcfg.setSjg4("6");
		lqsmshighcfg.setSjg5("6");
		lqsmshighcfg.setSjg6("6");
		lqsmshighcfg.setSjg7("6");
		lqsmshighcfg.setSjf1("4");
		lqsmshighcfg.setSjf2("4");
		lqsmshighcfg.setSjlq("4");
		lqsmshighcfg.setSjtjj("4");
		lqsmshighcfg.setSjysb("4");
		return lqsmshighcfg;
	}
	
	public Liqingziduancfg getDefaultsmshighRightlqcfg2(String gprsbh) {
		Liqingziduancfg lqsmshighcfg = new Liqingziduancfg();
		lqsmshighcfg.setLeixin("18");
		lqsmshighcfg.setGprsbianhao(gprsbh);
		lqsmshighcfg.setSjysb("4");
		return lqsmshighcfg;
	}
	
	
	//获取拌和站字段短信报警手机号码2
	public Hntbhzziduancfg hntsmsnumberfindBybh2(String gprsbh) {
		return hntDAO.findByGprsbhandleixin(gprsbh, "12");	
	}
	
	public Liqingziduancfg lqsmsnumberfindBybh2(String gprsbh) {
		return lqcfgDAO.findByGprsbhandleixin(gprsbh, "12");	
	}
	
	//获取拌和站字段短信报警下限3
	public Hntbhzziduancfg hntsmslowfindBybh3(String gprsbh) {
		Hntbhzziduancfg smslowcfg3 = hntDAO.findByGprsbhandleixin(gprsbh, "13");
		if (null == smslowcfg3) {
			smslowcfg3 = getDefaultsmslowcfg3(gprsbh);
		}
		return smslowcfg3;
	}
	
	public Liqingziduancfg lqsmslowfindBybh3(String gprsbh) {
		Liqingziduancfg smslowcfg3 = lqcfgDAO.findByGprsbhandleixin(gprsbh, "13");
		if (null == smslowcfg3) {
			smslowcfg3 = getDefaultsmslowlqcfg3(gprsbh);
		}
		return smslowcfg3;
	}
	
	public Hntbhzziduancfg getDefaultsmslowcfg3(String gprsbh) {
		Hntbhzziduancfg hbsmslowcfg3 = new Hntbhzziduancfg();
		hbsmslowcfg3.setLeixin("13");
		hbsmslowcfg3.setGprsbianhao(gprsbh);
		hbsmslowcfg3.setJiaobanshijian("30");
		hbsmslowcfg3.setShui1_shijizhi("10");
		hbsmslowcfg3.setShuini1_shijizhi("10");
		hbsmslowcfg3.setShuini2_shijizhi("10");
		hbsmslowcfg3.setKuangfen3_shijizhi("10");
		hbsmslowcfg3.setFeimeihui4_shijizhi("10");
		hbsmslowcfg3.setSha1_shijizhi("12");
		hbsmslowcfg3.setShi1_shijizhi("12");
		hbsmslowcfg3.setShi2_shijizhi("12");
		hbsmslowcfg3.setSha2_shijizhi("12");
		hbsmslowcfg3.setWaijiaji1_shijizhi("12");
		return hbsmslowcfg3;
	}
	
	public Liqingziduancfg getDefaultsmslowlqcfg3(String gprsbh) {
		Liqingziduancfg lqsmslowcfg = new Liqingziduancfg();
		lqsmslowcfg.setLeixin("13");
		lqsmslowcfg.setGprsbianhao(gprsbh);
		lqsmslowcfg.setSjg1("10");
		lqsmslowcfg.setSjg2("10");
		lqsmslowcfg.setSjg3("10");
		lqsmslowcfg.setSjg4("10");
		lqsmslowcfg.setSjg5("10");
		lqsmslowcfg.setSjg6("10");
		lqsmslowcfg.setSjg7("10");
		lqsmslowcfg.setSjf1("6");
		lqsmslowcfg.setSjf2("6");
		lqsmslowcfg.setSjlq("6");
		lqsmslowcfg.setSjtjj("6");
		lqsmslowcfg.setSjysb("6");
		return lqsmslowcfg;
	}
	
	//获取拌和站字段短信报警上限3
	public Hntbhzziduancfg hntsmshighfindBybh3(String gprsbh) {
		Hntbhzziduancfg smshighcfg3 = hntDAO.findByGprsbhandleixin(gprsbh, "14");
		if (null == smshighcfg3) {
			smshighcfg3 = getDefaultsmshighcfg3(gprsbh);
		}
		return smshighcfg3;
	}
	
	public Liqingziduancfg lqsmshighfindBybh3(String gprsbh) {
		Liqingziduancfg smshighcfg3 = lqcfgDAO.findByGprsbhandleixin(gprsbh, "14");
		if (null == smshighcfg3) {
			smshighcfg3 = getDefaultsmshighlqcfg3(gprsbh);
		}
		return smshighcfg3;
	}
	
	public Hntbhzziduancfg getDefaultsmshighcfg3(String gprsbh) {
		Hntbhzziduancfg hbsmshighcfg3 = new Hntbhzziduancfg();
		hbsmshighcfg3.setLeixin("14");
		hbsmshighcfg3.setGprsbianhao(gprsbh);
		hbsmshighcfg3.setShui1_shijizhi("10");
		hbsmshighcfg3.setShuini1_shijizhi("10");
		hbsmshighcfg3.setShuini2_shijizhi("10");
		hbsmshighcfg3.setKuangfen3_shijizhi("10");
		hbsmshighcfg3.setFeimeihui4_shijizhi("10");
		hbsmshighcfg3.setSha1_shijizhi("12");
		hbsmshighcfg3.setShi1_shijizhi("12");
		hbsmshighcfg3.setShi2_shijizhi("12");
		hbsmshighcfg3.setSha2_shijizhi("12");
		hbsmshighcfg3.setWaijiaji1_shijizhi("12");
		return hbsmshighcfg3;
	}
	
	public Liqingziduancfg getDefaultsmshighlqcfg3(String gprsbh) {
		Liqingziduancfg lqsmshighcfg = new Liqingziduancfg();
		lqsmshighcfg.setLeixin("14");
		lqsmshighcfg.setGprsbianhao(gprsbh);
		lqsmshighcfg.setSjg1("10");
		lqsmshighcfg.setSjg2("10");
		lqsmshighcfg.setSjg3("10");
		lqsmshighcfg.setSjg4("10");
		lqsmshighcfg.setSjg5("10");
		lqsmshighcfg.setSjg6("10");
		lqsmshighcfg.setSjg7("10");
		lqsmshighcfg.setSjf1("6");
		lqsmshighcfg.setSjf2("6");
		lqsmshighcfg.setSjlq("6");
		lqsmshighcfg.setSjtjj("6");
		lqsmshighcfg.setSjysb("6");
		return lqsmshighcfg;
	}
	
	//获取拌和站字段短信报警手机号码3
	public Hntbhzziduancfg hntsmsnumberfindBybh3(String gprsbh) {
		return hntDAO.findByGprsbhandleixin(gprsbh, "15");	
	}
	
	public Liqingziduancfg lqsmsnumberfindBybh3(String gprsbh) {
		return lqcfgDAO.findByGprsbhandleixin(gprsbh, "15");	
	}
	
	//获取拌和站字段短信报警设计值2
	public Hntbhzziduancfg hntsmsshejifindBybh2(String gprsbh) {
		Hntbhzziduancfg smsshejicfg2 = hntDAO.findByGprsbhandleixin(gprsbh, "16");
		if (null == smsshejicfg2) {
			smsshejicfg2 = getDefaultsmsshejicfg2(gprsbh);
		}
		return smsshejicfg2;
	}
	
	public Liqingziduancfg lqsmsshejifindBybh2(String gprsbh) {
		Liqingziduancfg smsshejicfg2 = lqcfgDAO.findByGprsbhandleixin(gprsbh, "16");
		if (null == smsshejicfg2) {
			smsshejicfg2 = getDefaultsmsshejilqcfg2(gprsbh);
		}
		return smsshejicfg2;
	}
	
	public Hntbhzziduancfg getDefaultsmsshejicfg2(String gprsbh) {
		Hntbhzziduancfg hbsmsshejicfg2 = new Hntbhzziduancfg();
		hbsmsshejicfg2.setLeixin("16");
		hbsmsshejicfg2.setGprsbianhao(gprsbh);
		hbsmsshejicfg2.setJiaobanshijian("60");
		return hbsmsshejicfg2;
	}
	
	public Liqingziduancfg getDefaultsmsshejilqcfg2(String gprsbh) {
		Liqingziduancfg lqsmsshejicfg2 = new Liqingziduancfg();
		lqsmsshejicfg2.setLeixin("16");
		lqsmsshejicfg2.setGprsbianhao(gprsbh);
		lqsmsshejicfg2.setGlwd("160");
		return lqsmsshejicfg2;
	}
	
	public Banhezhanxinxi getBhzbybiaoduanid(Integer biaoduanid) {
		String queryString = "from Banhezhanxinxi as model where model.biaoduanid=?";
		List<Banhezhanxinxi> bhzlist = bhzDAO.find(queryString, biaoduanid);
		if (bhzlist.size() > 0) {
			return bhzlist.get(0);
		} else {
			return null;
		}
	}
	
	public Hashtable saveandSendSms(String shebeibianhao, String phonenumber, String sms, String apitype) {		
		Hashtable recTable = null;
		if (StringUtil.Null2Blank(shebeibianhao).length()>0 && shebeibianhao.equalsIgnoreCase("test")) {
			logger.info(shebeibianhao+"发送短信("+phonenumber+"):"+sms);
			recTable = Smssender.SendSms(phonenumber, sms, apitype);
		} else {
			String queryString = "from Banhezhanxinxi as model where model.gprsbianhao=?";
			List<Banhezhanxinxi> bhzlist = bhzDAO.find(queryString, shebeibianhao);
			if (bhzlist.size() > 0) {
				Banhezhanxinxi bhz = bhzlist.get(0);
				if (null != bhz.getBiaoduanid() && bhz.getBiaoduanid() > 0) {
					Smsrecord smsrecord = smsService.getSmsrecordBybiaoduanid(bhz.getBiaoduanid());
					if (null != smsrecord && null != smsrecord.getSmscount() && smsrecord.getSmscount() > 0) {
						if (null == smsrecord.getTotalcount()) {
							smsrecord.setTotalcount(0);
						}
						if (null == smsrecord.getSuccesscount()) {
							smsrecord.setSuccesscount(0);
						}
						if (null == smsrecord.getFailcount()) {
							smsrecord.setFailcount(0);
						}
						logger.info(shebeibianhao+"发送短信("+phonenumber+"):"+sms);
						recTable = Smssender.SendSms(phonenumber, sms, apitype);
						if (null != recTable) {
							Smsinfo smsinfo = new Smsinfo();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String fsTime = sdf.format(System.currentTimeMillis());
							smsinfo.setShijian(fsTime);
							smsinfo.setShebeibianhao(shebeibianhao);
							smsinfo.setFasongphone(phonenumber);
							smsinfo.setFasongcount(phonenumber.split(",").length);
							smsinfo.setFasongcontent(sms);
							smsinfo.setFasongstatus((String)recTable.get("smsstatus"));
							smsinfo.setReturnmsg((String)recTable.get("mymsg"));
							String successphone = StringUtil.Null2Blank((String)recTable.get("successphone"));
							if (successphone.length() > 0) {
								smsinfo.setSuccessphone(successphone);
								smsinfo.setSuccesscount(successphone.split(",").length);
								smsrecord.setSmscount(smsrecord.getSmscount() - smsinfo.getSuccesscount());
								smsrecord.setTotalcount(smsrecord.getTotalcount() + smsinfo.getFasongcount());
								smsrecord.setSuccesscount(smsrecord.getSuccesscount() + smsinfo.getSuccesscount());
								smsrecord.setFailcount(smsrecord.getFailcount() + smsinfo.getFasongcount() - smsinfo.getSuccesscount());
							} else {
								logger.info("短信账号异常，发送失败");
								smsrecord.setTotalcount(smsrecord.getTotalcount() + smsinfo.getFasongcount());
								smsrecord.setFailcount(smsrecord.getFailcount() + smsinfo.getFasongcount());
							}
							smsService.saveOrUpdateSmsrecord(smsrecord);
							smsDAO.saveOrUpdate(smsinfo);
						}
					} else {
						logger.info("标段"+bhz.getBiaoduanid()+bhz.getBanhezhanminchen()+bhz.getGprsbianhao()+"短信余额不足");
					}
				} else {
					logger.info("设备"+shebeibianhao+"未指定标段");
				}
			} else {
				logger.info("设备"+shebeibianhao+"未指定标段");
			}
		}
		
		return recTable;
	}
	
	public void getMaxhntxx() {
		List<TopRealMaxhunnintuView> th = realMaxDAO.loadAll();
		if (th.size() > 0) {
			xxmaxDAO.deleteAll(xxmaxDAO.loadAll());
		}
		for (TopRealMaxhunnintuView topv : th) {
			Hntxiangxixxmax hxm = new Hntxiangxixxmax();
			try {
				BeanUtils.copyProperties(hxm, topv);
				xxmaxDAO.saveOrUpdate(hxm);
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		}
		
	}
	
	public void getMaxlqxx() {
		List<TopRealMaxLiqingView> th = realMaxlqDAO.loadAll();
		if (th.size() > 0) {
			lqmaxDAO.deleteAll(lqmaxDAO.loadAll());
		}
		for (TopRealMaxLiqingView topv : th) {
			Liqingxixxmax hxm = new Liqingxixxmax();
			try {
				BeanUtils.copyProperties(hxm, topv);
				lqmaxDAO.saveOrUpdate(hxm);
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		}
		
	}
	
	public String getRealPhoneNumber(String phoneNo) {
		String realPhone = "";
		if (StringUtil.Null2Blank(phoneNo).length()>0 && 
				StringUtil.Null2Blank(phoneNo).length()<10 && 
				  phoneNo.matches("[0-9]{1,}")) {
			try {
				HandSet hs = handsetDAO.get(Integer.parseInt(phoneNo));
				if (null != hs) {
					realPhone = hs.getPhone();
				}
			} catch (Exception e) {
			}
		}
		return realPhone;
	}
	//水稳
	public List<TopRealMaxShuiwenxixxView> limitswlist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealMaxShuiwenxixxView as model";
		List<TopRealMaxShuiwenxixxView> toplist = new ArrayList<TopRealMaxShuiwenxixxView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = topswDAO.find(queryString+" where model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topswDAO.find(queryString+" where model.biaoduanid=?", biaoduan);
				} else {
					toplist = topswDAO.loadAll();
				}
			}
		} else {
			queryString = "from TopRealMaxShuiwenxixxView as model where model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = topswDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topswDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = topswDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	
	
	//水稳短信报警
	public List<TopRealMaxShuiwenxixxView> limitSwsmscfglist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealMaxShuiwenxixxView as model where model.smsbaojin='1'";
		List<TopRealMaxShuiwenxixxView> toplist = new ArrayList<TopRealMaxShuiwenxixxView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = realMaxlqDAO.find(queryString+" and model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = realMaxlqDAO.find(queryString+" and model.biaoduanid=?", biaoduan);
				} else {
					toplist = realMaxlqDAO.find(queryString);
				}
			}
		} else {
			queryString = "from TopRealMaxShuiwenxixxView as model where model.smsbaojin='1' and model." + fn+ "=?";
			if (null != xiangmubu) {
				toplist = realMaxlqDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = realMaxlqDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = realMaxlqDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	
	public Banhezhanxinxi getBhzbysbbh(String sbbh) {
		Banhezhanxinxi bhzxx=null;
		List<Banhezhanxinxi> toplist =  bhzDAO.find("from Banhezhanxinxi as model where model.gprsbianhao=?", sbbh);
		if (toplist.size()>0) {
			bhzxx = toplist.get(0);
		}
		return bhzxx;
	}
	
	public List<Banhezhanxinxi> loadbhzlist(String fntype, int bsid, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from Banhezhanxinxi as model where (model.shebeileixin='1' or model.shebeileixin='2' or model.shebeileixin='3') ";
		List<Banhezhanxinxi> toplist = new ArrayList<Banhezhanxinxi>();
		if (fntype.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = bhzDAO.find(queryString+" and model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = bhzDAO.find(queryString+" and model.biaoduanid=?", biaoduan);
				} else {
					toplist = bhzDAO.find(queryString);
				}
			}
		} else {
			queryString = "from Banhezhanxinxi as model where (model.shebeileixin='1' or model.shebeileixin='2' or model.shebeileixin='3') and model." + fntype
					+ "=?";
			if (null != xiangmubu) {
				toplist = bhzDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = bhzDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = bhzDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	
	@Autowired
	private TopChuliaokouTemperaturedataViewDAO topchuliaotmpDAO;
	
	public List<TopChuliaokouTemperaturedataView> chuliaokouTemperaturelist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopChuliaokouTemperaturedataView as model";
		List<TopChuliaokouTemperaturedataView> toplist = new ArrayList<TopChuliaokouTemperaturedataView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = topchuliaotmpDAO.find(queryString+" where model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topchuliaotmpDAO.find(queryString+" where model.biaoduanid=?", biaoduan);
				} else {
					toplist = topchuliaotmpDAO.loadAll();
				}
			}
		} else {
			queryString = "from TopRealTemperaturedataView as model where model." + fn
					+ "=?";
			if (null != xiangmubu) {
				toplist = topchuliaotmpDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topchuliaotmpDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = topchuliaotmpDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	
	
	//出料口
		public void saveClkTmpjieguo(ChuliaokouTemperaturejieguo clktmpjg) {
			clktmpjieguoDAO.saveOrUpdate(clktmpjg);
		}
		
		
	public List<TopRealEnvironmentView> limitEnvironmentlist(HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from TopRealEnvironmentView as model";
		List<TopRealEnvironmentView> toplist = new ArrayList<TopRealEnvironmentView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = topEnvironmentDAO.find(queryString+" where model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topEnvironmentDAO.find(queryString+" where model.biaoduanid=?", biaoduan);
				} else {
					toplist = topEnvironmentDAO.loadAll();
				}
			}
		} else {
			queryString = "from TopRealEnvironmentView as model where model." + fn
				+ "=?";
			if (null != xiangmubu) {
				toplist = topEnvironmentDAO.find(queryString+" and model.xiangmubuid=?", bsid, xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = topEnvironmentDAO.find(queryString+" and model.biaoduanid=?", bsid, biaoduan);
				} else {
					toplist = topEnvironmentDAO.find(queryString, bsid);
				}
			}
		}
		return toplist;
	}
	
	//将拌和机上传的一笔数据的出料时间给12标1号拌和机测温的设备使用
	public Temperaturedata getOneclwd(String clwd){
		List<Temperaturedata> datalist=bhzdataDAO.find("from Temperaturedata where tmpshijian>='"+clwd+"'");
		if(datalist.size()>0){
			return (Temperaturedata)datalist.get(0);
		}else{
			return null;
		}
	}
	
	public Liqingxixx getlqbybianhao(Integer bianhao){
		return lqDAO.get(bianhao);
	}
	
	public void saveLiqing(Liqingxixx lq){
		lqDAO.saveOrUpdate(lq);
	}
	
	public Liqingsclsheji getlqSheji(Integer shejiid){
		return lqshejiDAO.get(shejiid);
	}
	
	public void saveLqSheji(Liqingsclsheji lqsheji){
		lqshejiDAO.saveOrUpdate(lqsheji);
	}
	
	public void delLqSheji(Integer shejiid){
		lqshejiDAO.deleteByKey(shejiid);
	}
	
	/******************水稳*********************/
	public List<ShuiwenziduancfgView> limitshuiwencfglist(
			HttpServletRequest request, Integer biaoduan, Integer xiangmubu) {
		String queryString = "from ShuiwenziduancfgView as model where model.leixing='1'";
		List<ShuiwenziduancfgView> toplist = new ArrayList<ShuiwenziduancfgView>();
		String fn = StringUtil.getQueryFieldNameByRequest(request);
		int bsid = StringUtil.getBiaoshiId(request);
		if (fn.equalsIgnoreCase("all")) {
			if (null != xiangmubu) {
				toplist = swcfgViewDAO.find(queryString+" and model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = swcfgViewDAO.find(queryString+" and model.biaoduanid=?", biaoduan);
				} else {
					toplist = swcfgViewDAO.find(queryString);
				}
			}
		} else {	
			queryString = "from ShuiwenziduancfgView as model where model." + fn+ "=?";
			if (null != xiangmubu) {
				toplist = swcfgViewDAO.find(queryString+" and model.xiangmubuid=?", xiangmubu);
			} else {
				if (null != biaoduan) {
					toplist = swcfgViewDAO.find(queryString+" and model.biaoduanid=?", biaoduan);
				} else {
					toplist = swcfgViewDAO.find(queryString,bsid);
				}
			}
		}
		return toplist;
	}
	
	public void shuiwencfgadd(Shuiwenziduancfg shuiwencfg) {
        swcfgDAO.saveOrUpdate(shuiwencfg);		
	}
	
	private Shuiwenziduancfg getDefaultShuiwencfgHeader() {
		Shuiwenziduancfg swfield=new Shuiwenziduancfg();
		swfield.setBaocunshijian("baocunshijian");
		swfield.setBeiy1("beiy1");
		swfield.setBeiy2("beiy2");
		swfield.setBeiy3("beiy3");
		swfield.setBiaoshi("biaoshi");
		swfield.setCaijishijian("caijishijian");
		swfield.setGlchangliang("glchangliang");
		swfield.setKehuduanbianhao("kuhuduanbiaohao");
		swfield.setLlfl1("llfl1");
		swfield.setLlfl2("llfl2");
		swfield.setLlgl1("llgl1");
		swfield.setLlgl2("llgl2");
		swfield.setLlgl3("llgl3");
		swfield.setLlgl4("llgl4");
		swfield.setLlgl5("llgl5");
		swfield.setLlshui("llshui");
		swfield.setSjfl1("sjfl1");
		swfield.setSjfl2("sjfl2");
		swfield.setSjgl1("sjgl1");
		swfield.setSjgl2("sjgl2");
		swfield.setSjgl3("sjgl3");
		swfield.setSjgl4("sjgl4");
		swfield.setSjgl5("sjgl5");
		swfield.setSjshui("sjshui");
		swfield.setPerfl1("perfl1");
		swfield.setPerfl2("perfl2");
		swfield.setPergl1("pergl1");
		swfield.setPergl2("pergl2");
		swfield.setPergl3("pergl3");
		swfield.setPergl4("pergl4");
		swfield.setPergl5("pergl5");
		swfield.setPershui("pershui");
		swfield.setShijian("shijian");
		swfield.setShebeibianhao("shebeibianhao");
		swfield.setChaobiaojisuanfangshi("chaobiaojisuanfangshi");
		swfield.setLilunpeibi("lilunpeibi");
		return swfield;
	}
	
	public Shuiwenziduancfg getshuiwencfgHeader() {
		Shuiwenziduancfg swcfg =  swcfgDAO.findByGprsbhandleixin("1", "3");	
			if (null == swcfg) {
				Shuiwenziduancfg swfield = getDefaultShuiwencfgHeader();
				swfield.setGprsbianhao("1");
				swfield.setLeixing("3");				
				swcfgDAO.save(swfield);
				swcfg = swfield;
		}
		return swcfg;
	}
	
	public Shuiwenziduancfg shuiwencfgfindById(int cfgId) {
		return swcfgDAO.get(cfgId);
	}
	
	public Shuiwenziduancfg getDefaultShuiwenziduanshow() {
		Shuiwenziduancfg swisshow=new Shuiwenziduancfg();
		swisshow.setBaocunshijian("0");
		swisshow.setBeiy1("0");
		swisshow.setBeiy2("0");
		swisshow.setBeiy3("0");
		swisshow.setBiaoshi("0");
		swisshow.setCaijishijian("0");
		swisshow.setGlchangliang("0");
		swisshow.setKehuduanbianhao("0");
		swisshow.setLlfl1("1");
		swisshow.setLlfl2("0");
		swisshow.setLlgl1("1");
		swisshow.setLlgl2("1");
		swisshow.setLlgl3("1");
		swisshow.setLlgl4("1");
		swisshow.setLlgl5("1");
		swisshow.setLlshui("1");
		swisshow.setSjfl1("1");
		swisshow.setSjfl2("0");
		swisshow.setSjgl1("1");
		swisshow.setSjgl2("1");
		swisshow.setSjgl3("1");
		swisshow.setSjgl4("1");
		swisshow.setSjgl5("1");
		swisshow.setSjshui("1");
		swisshow.setPerfl1("1");
		swisshow.setPerfl2("0");
		swisshow.setPergl1("1");
		swisshow.setPergl2("1");
		swisshow.setPergl3("1");
		swisshow.setPergl4("1");
		swisshow.setPergl5("1");
		swisshow.setPershui("1");
		swisshow.setShijian("1");
		swisshow.setShebeibianhao("0");
		swisshow.setChaobiaojisuanfangshi("0");
		swisshow.setLilunpeibi("0");
		return swisshow;
	}
	
	public Shuiwenziduancfg shuiwenisshowcfgBybh(String gprsbh) {
		Shuiwenziduancfg swcfg = swcfgDAO.findByGprsbhandleixin(gprsbh, "2");	
		if (null == swcfg) {
			swcfg = swcfgDAO.findByGprsbhandleixin("all", "101");
			if (null == swcfg) {
				Shuiwenziduancfg swisshow = getDefaultShuiwenziduanshow();
				swisshow.setGprsbianhao("all");
				swisshow.setLeixing("101");				
				swcfgDAO.save(swisshow);
				swcfg = swisshow;
			}
		}
		return swcfg;
	}
	
	public Shuiwenziduancfg getDefaultShuiwenzstziduanshow() {
		Shuiwenziduancfg swisshow=new Shuiwenziduancfg();
		swisshow.setLlfl1("0");
		swisshow.setLlfl2("0");
		swisshow.setLlgl1("0");
		swisshow.setLlgl2("0");
		swisshow.setLlgl3("0");
		swisshow.setLlgl4("0");
		swisshow.setLlgl5("0");
		swisshow.setLlshui("0");
		swisshow.setSjfl1("1");
		swisshow.setSjfl2("0");
		swisshow.setSjgl1("1");
		swisshow.setSjgl2("1");
		swisshow.setSjgl3("1");
		swisshow.setSjgl4("1");
		swisshow.setSjgl5("1");
		swisshow.setSjshui("0");
		swisshow.setPerfl1("0");
		swisshow.setPerfl2("0");
		swisshow.setPergl1("0");
		swisshow.setPergl2("0");
		swisshow.setPergl3("0");
		swisshow.setPergl4("0");
		swisshow.setPergl5("0");
		swisshow.setPershui("0");
		return swisshow;
	}
	
	public Shuiwenziduancfg shuiwenzstisshowcfgBybh(String gprsbh) {
		Shuiwenziduancfg swcfg = swcfgDAO.findByGprsbhandleixin(gprsbh, "16");	
		if (null == swcfg) {
			swcfg = swcfgDAO.findByGprsbhandleixin("all", "102");
			if (null == swcfg) {
				Shuiwenziduancfg swisshow = getDefaultShuiwenzstziduanshow();
				swisshow.setGprsbianhao("all");
				swisshow.setLeixing("102");				
				swcfgDAO.save(swisshow);
				swcfg = swisshow;
			}
		}
		return swcfg;
	}
	
	public Shuiwenziduancfg getDefaultShuiwenziduancfg() {
		Shuiwenziduancfg swfield=new Shuiwenziduancfg();
		swfield.setBaocunshijian("保存时间");
		swfield.setBeiy1("备用1");
		swfield.setBeiy2("备用2");
		swfield.setBeiy3("备用3");
		swfield.setBiaoshi("标识");
		swfield.setCaijishijian("采集时间");
		swfield.setGlchangliang("总产量");
		swfield.setKehuduanbianhao("客户端编号");
		swfield.setLlfl1("理论粉料1");
		swfield.setLlfl2("理论粉料2");
		swfield.setLlgl1("理论骨料1");
		swfield.setLlgl2("理论骨料2");
		swfield.setLlgl3("理论骨料3");
		swfield.setLlgl4("理论骨料4");
		swfield.setLlgl5("理论骨料5");
		swfield.setLlshui("理论水");
		swfield.setSjfl1("实际粉料1");
		swfield.setSjfl2("实际粉料2");
		swfield.setSjgl1("实际骨料1");
		swfield.setSjgl2("实际骨料2");
		swfield.setSjgl3("实际骨料3");
		swfield.setSjgl4("实际骨料4");
		swfield.setSjgl5("实际骨料5");
		swfield.setSjshui("实际水");
		swfield.setPerfl1("粉料比率1");
		swfield.setPerfl2("粉料比率2");
		swfield.setPergl1("骨料比率1");
		swfield.setPergl2("骨料比率2");
		swfield.setPergl3("骨料比率3");
		swfield.setPergl4("骨料比率4");
		swfield.setPergl5("骨料比率5");
		swfield.setPershui("水比率");
		swfield.setShijian("时间");
		swfield.setShebeibianhao("设备编号");
		swfield.setChaobiaojisuanfangshi("超标计算方式");
		swfield.setLilunpeibi("理论配比");
		return swfield;
	}
	
	public Shuiwenziduancfg shuiwenfieldnameBybh(String gprsbh) {
		Shuiwenziduancfg swcfg =  swcfgDAO.findByGprsbhandleixin(gprsbh, "1");	
		if (null == swcfg) {
			swcfg = swcfgDAO.findByGprsbhandleixin("all", "100");
			if (null == swcfg) {
				Shuiwenziduancfg swfield = getDefaultShuiwenziduancfg();
				swfield.setGprsbianhao("all");
				swfield.setLeixing("100");				
				swcfgDAO.save(swfield);
				swcfg = swfield;
			}
		}
		return swcfg;
	}
	
	public void shuiwencfgdel(int cfgId, String gprsbh) {
        swcfgDAO.deleteByKey(cfgId);
        Shuiwenziduancfg swisshow=shuiwenisshowcfgBybh(gprsbh);
        Shuiwenziduancfg swzstisshow=shuiwenisshowcfgBybh(gprsbh);
        if(swisshow!=null){
        	swcfgDAO.delete(swisshow);
        }
        if(swzstisshow!=null){
        	swcfgDAO.delete(swzstisshow);
        }
	}
	
	public void getMaxswxx() {
		List<TopRealMaxShuiwenxixxView> th = realMaxswDAO.loadAll();
		if (th.size() > 0) {
			swmaxDAO.deleteAll(swmaxDAO.loadAll());
		}
		for (TopRealMaxShuiwenxixxView topv : th) {
			Shuiwenxixxmax hxm = new Shuiwenxixxmax();
			try {
				BeanUtils.copyProperties(hxm, topv);
				swmaxDAO.saveOrUpdate(hxm);
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		}
		
	}

	public Shuiwenziduancfg getDefaultissmsswcfg(String gprsbh) {
		Shuiwenziduancfg swissmscfg = new Shuiwenziduancfg();
		swissmscfg.setLeixing("4");
		swissmscfg.setGprsbianhao(gprsbh);
		swissmscfg.setSjgl1("1");
		swissmscfg.setSjgl2("1");
		swissmscfg.setSjgl3("1");
		swissmscfg.setSjgl4("1");
		swissmscfg.setSjgl5("0");
		swissmscfg.setSjfl1("1");
		swissmscfg.setSjfl2("1");
		swissmscfg.setSjshui("0");
		return swissmscfg;
	}
	
	public Shuiwenziduancfg getDefaultiskaipansmsswcfg(String gprsbh) {
		Shuiwenziduancfg swissmscfg = new Shuiwenziduancfg();
		swissmscfg.setLeixing("200");
		swissmscfg.setGprsbianhao(gprsbh);
		swissmscfg.setSjgl1("1");
		swissmscfg.setSjgl2("1");
		swissmscfg.setSjgl3("1");
		swissmscfg.setSjgl4("1");
		swissmscfg.setSjgl5("0");
		swissmscfg.setSjfl1("1");
		swissmscfg.setSjfl2("1");
		swissmscfg.setSjshui("0");
		return swissmscfg;
	}
	
	//获取拌和站字段是否短信报警
	public Shuiwenziduancfg swissmsfindBybh(String gprsbh) {
		Shuiwenziduancfg issmscfg = swcfgDAO.findByGprsbhandleixin(gprsbh, "4");
		if (null == issmscfg) {
			issmscfg = getDefaultissmsswcfg(gprsbh);
		}
		return issmscfg;
	}
	
	//获取开盘拌和站字段是否短信报警
	public Shuiwenziduancfg swkaipanissmsfindBybh(String gprsbh) {
		Shuiwenziduancfg issmscfg = swcfgDAO.findByGprsbhandleixin(gprsbh, "200");
		if (null == issmscfg) {
			issmscfg = getDefaultiskaipansmsswcfg(gprsbh);
		}
		return issmscfg;
	}
	
	public Shuiwenziduancfg getDefaultsmslowswcfg(String gprsbh) {
		Shuiwenziduancfg swsmslowcfg = new Shuiwenziduancfg();
		swsmslowcfg.setLeixing("5");
		swsmslowcfg.setGprsbianhao(gprsbh);
		swsmslowcfg.setSjgl1("3");
		swsmslowcfg.setSjgl2("3");
		swsmslowcfg.setSjgl3("3");
		swsmslowcfg.setSjgl4("3");
		swsmslowcfg.setSjgl5("3");
		swsmslowcfg.setSjfl1("2");
		swsmslowcfg.setSjfl2("2");
		swsmslowcfg.setSjshui("3");
		return swsmslowcfg;
	}
	
	public ShuiwenziduancfgView getDefaultsmslowswcfgView(String gprsbh) {
		ShuiwenziduancfgView swsmslowcfg = new ShuiwenziduancfgView();
		swsmslowcfg.setLeixing("5");
		swsmslowcfg.setGprsbianhao(gprsbh);
		swsmslowcfg.setSjgl1("3");
		swsmslowcfg.setSjgl2("3");
		swsmslowcfg.setSjgl3("3");
		swsmslowcfg.setSjgl4("3");
		swsmslowcfg.setSjgl5("3");
		swsmslowcfg.setSjfl1("2");
		swsmslowcfg.setSjfl2("2");
		swsmslowcfg.setSjshui("3");
		return swsmslowcfg;
	}
	
	public LiqingziduancfgView getDefaultsmslowlqcfgView(String gprsbh) {
		LiqingziduancfgView lqsmslowcfg = new LiqingziduancfgView();
		lqsmslowcfg.setLeixin("5");
		lqsmslowcfg.setGprsbianhao(gprsbh);
		lqsmslowcfg.setSjg1("3");
		lqsmslowcfg.setSjg2("3");
		lqsmslowcfg.setSjg3("3");
		lqsmslowcfg.setSjg4("3");
		lqsmslowcfg.setSjg5("3");
		lqsmslowcfg.setSjg6("3");
		lqsmslowcfg.setSjg7("3");
		lqsmslowcfg.setSjf1("2");
		lqsmslowcfg.setSjf2("2");
		lqsmslowcfg.setSjlq("2");
		lqsmslowcfg.setSjtjj("2");
//		lqsmslowcfg.setSjshui("3");
		return lqsmslowcfg;
	}
	
	public Shuiwenziduancfg getDefaultsmskaipanlowswcfg(String gprsbh) {
		Shuiwenziduancfg swsmslowcfg = new Shuiwenziduancfg();
		swsmslowcfg.setLeixing("201");
		swsmslowcfg.setGprsbianhao(gprsbh);
		swsmslowcfg.setSjgl1("3");
		swsmslowcfg.setSjgl2("3");
		swsmslowcfg.setSjgl3("3");
		swsmslowcfg.setSjgl4("3");
		swsmslowcfg.setSjgl5("3");
		swsmslowcfg.setSjfl1("2");
		swsmslowcfg.setSjfl2("2");
		swsmslowcfg.setSjshui("3");
		return swsmslowcfg;
	}
	
	public Shuiwenziduancfg swsmslowfindBybh(String gprsbh) {
		Shuiwenziduancfg smslowcfg = swcfgDAO.findByGprsbhandleixin(gprsbh, "5");
		if (null == smslowcfg) {
			smslowcfg = getDefaultsmslowswcfg(gprsbh);
		}
		return smslowcfg;
	}
	
	public ShuiwenziduancfgView swsmslowViewfindBybh(String gprsbh) {
		ShuiwenziduancfgView smslowcfg = swcfgViewDAO.findByGprsbhandleixin(gprsbh, "5");
		if (null == smslowcfg) {
			smslowcfg = getDefaultsmslowswcfgView(gprsbh);
		}
		return smslowcfg;
	}
	public LiqingziduancfgView lqsmslowViewfindBybh(String gprsbh) {
		LiqingziduancfgView smslowcfg = lqviewDAO.findByGprsbhandleixin(gprsbh, "5");
		if (null == smslowcfg) {
			smslowcfg = getDefaultsmslowlqcfgView(gprsbh);
		}
		return smslowcfg;
	}
	
	public Shuiwenziduancfg swkaipansmslowfindBybh(String gprsbh) {
		Shuiwenziduancfg smslowcfg = swcfgDAO.findByGprsbhandleixin(gprsbh, "201");
		if (null == smslowcfg) {
			smslowcfg = getDefaultsmskaipanlowswcfg(gprsbh);
		}
		return smslowcfg;
	}
	
	public Shuiwenziduancfg getDefaultsmshighswcfg(String gprsbh) {
		Shuiwenziduancfg swsmshighcfg = new Shuiwenziduancfg();
		swsmshighcfg.setLeixing("6");
		swsmshighcfg.setGprsbianhao(gprsbh);
		swsmshighcfg.setSjgl1("3");
		swsmshighcfg.setSjgl2("3");
		swsmshighcfg.setSjgl3("3");
		swsmshighcfg.setSjgl4("3");
		swsmshighcfg.setSjgl5("3");
		swsmshighcfg.setSjfl1("2");
		swsmshighcfg.setSjfl2("2");
		swsmshighcfg.setSjshui("3");
		return swsmshighcfg;
	}
	
	public ShuiwenziduancfgView getDefaultsmshighswcfgView(String gprsbh) {
		ShuiwenziduancfgView swsmshighcfg = new ShuiwenziduancfgView();
		swsmshighcfg.setLeixing("6");
		swsmshighcfg.setGprsbianhao(gprsbh);
		swsmshighcfg.setSjgl1("3");
		swsmshighcfg.setSjgl2("3");
		swsmshighcfg.setSjgl3("3");
		swsmshighcfg.setSjgl4("3");
		swsmshighcfg.setSjgl5("3");
		swsmshighcfg.setSjfl1("2");
		swsmshighcfg.setSjfl2("2");
		swsmshighcfg.setSjshui("3");
		return swsmshighcfg;
	}
	
	public LiqingziduancfgView getDefaultsmshighlqcfgView(String gprsbh) {
		LiqingziduancfgView lqsmshighcfg = new LiqingziduancfgView();
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
//		lqsmshighcfg.setSjshui("3");
		return lqsmshighcfg;
	}
	
	public Shuiwenziduancfg getDefaultsmskaipanhighswcfg(String gprsbh) {
		Shuiwenziduancfg swsmshighcfg = new Shuiwenziduancfg();
		swsmshighcfg.setLeixing("202");
		swsmshighcfg.setGprsbianhao(gprsbh);
		swsmshighcfg.setSjgl1("3");
		swsmshighcfg.setSjgl2("3");
		swsmshighcfg.setSjgl3("3");
		swsmshighcfg.setSjgl4("3");
		swsmshighcfg.setSjgl5("3");
		swsmshighcfg.setSjfl1("2");
		swsmshighcfg.setSjfl2("2");
		swsmshighcfg.setSjshui("3");
		return swsmshighcfg;
	}
	
	public Shuiwenziduancfg swsmshighfindBybh(String gprsbh) {
		Shuiwenziduancfg smshighcfg = swcfgDAO.findByGprsbhandleixin(gprsbh, "6");
		if (null == smshighcfg) {
			smshighcfg = getDefaultsmshighswcfg(gprsbh);
		}
		return smshighcfg;
	}
	
	public ShuiwenziduancfgView swsmshighViewfindBybh(String gprsbh) {
		ShuiwenziduancfgView smshighcfg = swcfgViewDAO.findByGprsbhandleixin(gprsbh, "6");
		if (null == smshighcfg) {
			smshighcfg = getDefaultsmshighswcfgView(gprsbh);
		}
		return smshighcfg;
	}
	public LiqingziduancfgView lqsmshighViewfindBybh(String gprsbh) {
		LiqingziduancfgView smshighcfg = lqviewDAO.findByGprsbhandleixin(gprsbh, "6");
		if (null == smshighcfg) {
			smshighcfg = getDefaultsmshighlqcfgView(gprsbh);
		}
		return smshighcfg;
	}
	
	public Shuiwenziduancfg swkaipansmshighfindBybh(String gprsbh) {
		Shuiwenziduancfg smshighcfg = swcfgDAO.findByGprsbhandleixin(gprsbh, "202");
		if (null == smshighcfg) {
			smshighcfg = getDefaultsmskaipanhighswcfg(gprsbh);
		}
		return smshighcfg;
	}
	
	public Shuiwenziduancfg swsmsnumberfindBybh(String gprsbh) {
		return swcfgDAO.findByGprsbhandleixin(gprsbh, "7");		
	}
	
	public Shuiwenziduancfg swkaipansmsnumberfindBybh(String gprsbh) {
		return swcfgDAO.findByGprsbhandleixin(gprsbh, "203");		
	}
	
	public Shuiwenziduancfg swsmscontentfindBybh(String gprsbh) {
		return swcfgDAO.findByGprsbhandleixin(gprsbh, "8");	
	}
	
	public ShuiwenziduancfgView swfieldnamefindBybh(String gprsbh) {
		ShuiwenziduancfgView lqcfg =  swcfgViewDAO.findByGprsbhandleixin(gprsbh, "1");	
		if (null == lqcfg) {
			lqcfg = swcfgViewDAO.findByGprsbhandleixin("all", "100");
		}
		return lqcfg;	
	}
	
	public Shuiwenziduancfg getDefaultsmsshejiswcfg(String gprsbh) {
		Shuiwenziduancfg swsmsshejicfg = new Shuiwenziduancfg();
		swsmsshejicfg.setLeixing("9");
		swsmsshejicfg.setGprsbianhao(gprsbh);
		return swsmsshejicfg;
	}
	
	public Shuiwenziduancfg swsmsshejifindBybh(String gprsbh) {
		Shuiwenziduancfg smsshejicfg = swcfgDAO.findByGprsbhandleixin(gprsbh, "9");
		if (null == smsshejicfg) {
			smsshejicfg = getDefaultsmsshejiswcfg(gprsbh);
		}
		return smsshejicfg;
	}
	
	public Shuiwenziduancfg getDefaultsmslowswcfg2(String gprsbh) {
		Shuiwenziduancfg swsmslowcfg = new Shuiwenziduancfg();
		swsmslowcfg.setLeixing("10");
		swsmslowcfg.setGprsbianhao(gprsbh);
		swsmslowcfg.setSjgl1("6");
		swsmslowcfg.setSjgl2("6");
		swsmslowcfg.setSjgl3("6");
		swsmslowcfg.setSjgl4("6");
		swsmslowcfg.setSjgl5("6");
		swsmslowcfg.setSjfl1("4");
		swsmslowcfg.setSjfl2("4");
		swsmslowcfg.setSjshui("4");
		return swsmslowcfg;
	}
	public Shuiwenziduancfg getDefaultsmslowswcfg4(String gprsbh) {
		Shuiwenziduancfg swsmslowcfg = new Shuiwenziduancfg();
		swsmslowcfg.setLeixing("18");
		swsmslowcfg.setGprsbianhao(gprsbh);
		swsmslowcfg.setSjgl1("6");
		swsmslowcfg.setSjgl2("6");
		swsmslowcfg.setSjgl3("6");
		swsmslowcfg.setSjgl4("6");
		swsmslowcfg.setSjgl5("6");
		swsmslowcfg.setSjfl1("4");
		swsmslowcfg.setSjfl2("4");
		swsmslowcfg.setSjshui("4");
		return swsmslowcfg;
	}
	
	
	
	public ShuiwenziduancfgView getDefaultsmslowswcfg2View(String gprsbh) {
		ShuiwenziduancfgView swsmslowcfg = new ShuiwenziduancfgView();
		swsmslowcfg.setLeixing("10");
		swsmslowcfg.setGprsbianhao(gprsbh);
		swsmslowcfg.setSjgl1("6");
		swsmslowcfg.setSjgl2("6");
		swsmslowcfg.setSjgl3("6");
		swsmslowcfg.setSjgl4("6");
		swsmslowcfg.setSjgl5("6");
		swsmslowcfg.setSjfl1("4");
		swsmslowcfg.setSjfl2("4");
		swsmslowcfg.setSjshui("4");
		return swsmslowcfg;
	}
	
	public LiqingziduancfgView getDefaultsmslowlqcfg2View(String gprsbh) {
		LiqingziduancfgView lqsmslowcfg = new LiqingziduancfgView();
		lqsmslowcfg.setLeixin("10");
		lqsmslowcfg.setGprsbianhao(gprsbh);
		lqsmslowcfg.setSjg1("6");
		lqsmslowcfg.setSjg2("6");
		lqsmslowcfg.setSjg3("6");
		lqsmslowcfg.setSjg4("6");
		lqsmslowcfg.setSjg5("6");
		lqsmslowcfg.setSjg6("6");
		lqsmslowcfg.setSjg7("6");
		lqsmslowcfg.setSjf1("4");
		lqsmslowcfg.setSjf2("4");
		lqsmslowcfg.setSjlq("4");
		lqsmslowcfg.setSjtjj("4");
//		lqsmslowcfg.setSjshui("4");
		return lqsmslowcfg;
	}
	
	public Shuiwenziduancfg swsmslowfindBybh2(String gprsbh) {
		Shuiwenziduancfg smslowcfg2 = swcfgDAO.findByGprsbhandleixin(gprsbh, "10");
		if (null == smslowcfg2) {
			smslowcfg2 = getDefaultsmslowswcfg2(gprsbh);
		}
		return smslowcfg2;
	}
	public Shuiwenziduancfg swsmslowfindBybh4(String gprsbh) {
		Shuiwenziduancfg smslowcfg4 = swcfgDAO.findByGprsbhandleixin(gprsbh, "18");
		if (null == smslowcfg4) {
			smslowcfg4 = getDefaultsmslowswcfg4(gprsbh);
		}
		return smslowcfg4;	
	}
	
	
	public ShuiwenziduancfgView swsmslowViewfindBybh2(String gprsbh) {
		ShuiwenziduancfgView smslowcfg2 = swcfgViewDAO.findByGprsbhandleixin(gprsbh, "10");
		if (null == smslowcfg2) {
			smslowcfg2 = getDefaultsmslowswcfg2View(gprsbh);
		}
		return smslowcfg2;
	}
	public ShuiwenziduancfgView swsmslowViewfindBybh4(String gprsbh) {
		ShuiwenziduancfgView smslowcfg4 = swcfgViewDAO.findByGprsbhandleixin(gprsbh, "18");
		if (null == smslowcfg4) {
			smslowcfg4 = getDefaultsmslowswcfg2View(gprsbh);
		}
		return smslowcfg4;
	}
	
	public LiqingziduancfgView lqsmslowViewfindBybh2(String gprsbh) {
		LiqingziduancfgView smslowcfg2 = lqviewDAO.findByGprsbhandleixin(gprsbh, "10");
		if (null == smslowcfg2) {
			smslowcfg2 = getDefaultsmslowlqcfg2View(gprsbh);
		}
		return smslowcfg2;
	}
	
	public Shuiwenziduancfg getDefaultsmshighswcfg2(String gprsbh) {
		Shuiwenziduancfg swsmshighcfg = new Shuiwenziduancfg();
		swsmshighcfg.setLeixing("11");
		swsmshighcfg.setGprsbianhao(gprsbh);
		swsmshighcfg.setSjgl1("6");
		swsmshighcfg.setSjgl2("6");
		swsmshighcfg.setSjgl3("6");
		swsmshighcfg.setSjgl4("6");
		swsmshighcfg.setSjgl5("6");
		swsmshighcfg.setSjfl1("4");
		swsmshighcfg.setSjfl2("4");
		swsmshighcfg.setSjshui("4");
		return swsmshighcfg;
	}
	
	public Shuiwenziduancfg getDefaultsmshighswcfg4(String gprsbh) {
		Shuiwenziduancfg swsmshighcfg = new Shuiwenziduancfg();
		swsmshighcfg.setLeixing("19");
		swsmshighcfg.setGprsbianhao(gprsbh);
		swsmshighcfg.setSjgl1("6");
		swsmshighcfg.setSjgl2("6");
		swsmshighcfg.setSjgl3("6");
		swsmshighcfg.setSjgl4("6");
		swsmshighcfg.setSjgl5("6");
		swsmshighcfg.setSjfl1("4");
		swsmshighcfg.setSjfl2("4");
		swsmshighcfg.setSjshui("4");
		return swsmshighcfg;
	}
	
	public ShuiwenziduancfgView getDefaultsmshighswcfg2View(String gprsbh) {
		ShuiwenziduancfgView swsmshighcfg = new ShuiwenziduancfgView();
		swsmshighcfg.setLeixing("11");
		swsmshighcfg.setGprsbianhao(gprsbh);
		swsmshighcfg.setSjgl1("6");
		swsmshighcfg.setSjgl2("6");
		swsmshighcfg.setSjgl3("6");
		swsmshighcfg.setSjgl4("6");
		swsmshighcfg.setSjgl5("6");
		swsmshighcfg.setSjfl1("4");
		swsmshighcfg.setSjfl2("4");
		swsmshighcfg.setSjshui("4");
		return swsmshighcfg;
	}
	
	public LiqingziduancfgView getDefaultsmshighlqcfg2View(String gprsbh) {
		LiqingziduancfgView lqsmshighcfg = new LiqingziduancfgView();
		lqsmshighcfg.setLeixin("11");
		lqsmshighcfg.setGprsbianhao(gprsbh);
		lqsmshighcfg.setSjg1("6");
		lqsmshighcfg.setSjg2("6");
		lqsmshighcfg.setSjg3("6");
		lqsmshighcfg.setSjg4("6");
		lqsmshighcfg.setSjg5("6");
		lqsmshighcfg.setSjg6("6");
		lqsmshighcfg.setSjg7("6");
		lqsmshighcfg.setSjf1("4");
		lqsmshighcfg.setSjf2("4");
		lqsmshighcfg.setSjlq("4");
		lqsmshighcfg.setSjtjj("4");
//		lqsmshighcfg.setSjshui("4");
		return lqsmshighcfg;
	}
	
	public Shuiwenziduancfg swsmshighfindBybh2(String gprsbh) {
		Shuiwenziduancfg smshighcfg2 = swcfgDAO.findByGprsbhandleixin(gprsbh, "11");
		if (null == smshighcfg2) {
			smshighcfg2 = getDefaultsmshighswcfg2(gprsbh);
		}
		return smshighcfg2;
	}
	
	public Shuiwenziduancfg swsmshighfindBybh4(String gprsbh) {
		Shuiwenziduancfg smshighcfg4 = swcfgDAO.findByGprsbhandleixin(gprsbh, "19");
		if (null == smshighcfg4) {
			smshighcfg4 = getDefaultsmshighswcfg4(gprsbh);
		}
		return smshighcfg4;		
	}
	
	public ShuiwenziduancfgView swsmshighViewfindBybh2(String gprsbh) {
		ShuiwenziduancfgView smshighcfg2 = swcfgViewDAO.findByGprsbhandleixin(gprsbh, "11");
		if (null == smshighcfg2) {
			smshighcfg2 = getDefaultsmshighswcfg2View(gprsbh);
		}
		return smshighcfg2;
	}
	public ShuiwenziduancfgView swsmshighViewfindBybh4(String gprsbh) {
		ShuiwenziduancfgView smshighcfg4 = swcfgViewDAO.findByGprsbhandleixin(gprsbh, "19");
		if (null == smshighcfg4) {
			smshighcfg4 = getDefaultsmshighswcfg2View(gprsbh);
		}
		return smshighcfg4;
	}
	
	
	
	public LiqingziduancfgView lqsmshighViewfindBybh2(String gprsbh) {
		LiqingziduancfgView smshighcfg2 = lqviewDAO.findByGprsbhandleixin(gprsbh, "11");
		if (null == smshighcfg2) {
			smshighcfg2 = getDefaultsmshighlqcfg2View(gprsbh);
		}
		return smshighcfg2;
	}
	
	public Shuiwenziduancfg swsmsnumberfindBybh2(String gprsbh) {
		return swcfgDAO.findByGprsbhandleixin(gprsbh, "12");	
	}
	
	public Shuiwenziduancfg getDefaultsmslowswcfg3(String gprsbh) {
		Shuiwenziduancfg swsmslowcfg = new Shuiwenziduancfg();
		swsmslowcfg.setLeixing("13");
		swsmslowcfg.setGprsbianhao(gprsbh);
		swsmslowcfg.setSjgl1("10");
		swsmslowcfg.setSjgl2("10");
		swsmslowcfg.setSjgl3("10");
		swsmslowcfg.setSjgl4("10");
		swsmslowcfg.setSjgl5("10");
		swsmslowcfg.setSjfl1("6");
		swsmslowcfg.setSjfl2("6");
		swsmslowcfg.setSjshui("6");
		return swsmslowcfg;
	}
	
	public ShuiwenziduancfgView getDefaultsmslowswcfg3View(String gprsbh) {
		ShuiwenziduancfgView swsmslowcfg = new ShuiwenziduancfgView();
		swsmslowcfg.setLeixing("13");
		swsmslowcfg.setGprsbianhao(gprsbh);
		swsmslowcfg.setSjgl1("10");
		swsmslowcfg.setSjgl2("10");
		swsmslowcfg.setSjgl3("10");
		swsmslowcfg.setSjgl4("10");
		swsmslowcfg.setSjgl5("10");
		swsmslowcfg.setSjfl1("6");
		swsmslowcfg.setSjfl2("6");
		swsmslowcfg.setSjshui("6");
		return swsmslowcfg;
	}
	
	public LiqingziduancfgView getDefaultsmslowlqcfg3View(String gprsbh) {
		LiqingziduancfgView lqsmslowcfg = new LiqingziduancfgView();
		lqsmslowcfg.setLeixin("13");
		lqsmslowcfg.setGprsbianhao(gprsbh);
		lqsmslowcfg.setSjg1("10");
		lqsmslowcfg.setSjg2("10");
		lqsmslowcfg.setSjg3("10");
		lqsmslowcfg.setSjg4("10");
		lqsmslowcfg.setSjg5("10");
		lqsmslowcfg.setSjg6("10");
		lqsmslowcfg.setSjg7("10");
		lqsmslowcfg.setSjf1("6");
		lqsmslowcfg.setSjf2("6");
		lqsmslowcfg.setSjlq("6");
		lqsmslowcfg.setSjtjj("6");
//		lqsmslowcfg.setSjshui("6");
		return lqsmslowcfg;
	}
	
	public Shuiwenziduancfg swsmslowfindBybh3(String gprsbh) {
		Shuiwenziduancfg smslowcfg3 = swcfgDAO.findByGprsbhandleixin(gprsbh, "13");
		if (null == smslowcfg3) {
			smslowcfg3 = getDefaultsmslowswcfg3(gprsbh);
		}
		return smslowcfg3;
	}
	
	public ShuiwenziduancfgView swsmslowfindBybh3View(String gprsbh) {
		ShuiwenziduancfgView smslowcfg3 = swcfgViewDAO.findByGprsbhandleixin(gprsbh, "13");
		if (null == smslowcfg3) {
			smslowcfg3 = getDefaultsmslowswcfg3View(gprsbh);
		}
		return smslowcfg3;
	}
	public LiqingziduancfgView lqsmslowfindBybh3View(String gprsbh) {
		LiqingziduancfgView smslowcfg3 = lqviewDAO.findByGprsbhandleixin(gprsbh, "13");
		if (null == smslowcfg3) {
			smslowcfg3 = getDefaultsmslowlqcfg3View(gprsbh);
		}
		return smslowcfg3;
	}
	
	public Shuiwenziduancfg getDefaultsmshighswcfg3(String gprsbh) {
		Shuiwenziduancfg swsmshighcfg = new Shuiwenziduancfg();
		swsmshighcfg.setLeixing("14");
		swsmshighcfg.setGprsbianhao(gprsbh);
		swsmshighcfg.setSjgl1("10");
		swsmshighcfg.setSjgl2("10");
		swsmshighcfg.setSjgl3("10");
		swsmshighcfg.setSjgl4("10");
		swsmshighcfg.setSjgl5("10");
		swsmshighcfg.setSjfl1("6");
		swsmshighcfg.setSjfl2("6");
		swsmshighcfg.setSjshui("6");
		return swsmshighcfg;
	}
	
	public ShuiwenziduancfgView getDefaultsmshighswcfg3View(String gprsbh) {
		ShuiwenziduancfgView swsmshighcfg = new ShuiwenziduancfgView();
		swsmshighcfg.setLeixing("14");
		swsmshighcfg.setGprsbianhao(gprsbh);
		swsmshighcfg.setSjgl1("10");
		swsmshighcfg.setSjgl2("10");
		swsmshighcfg.setSjgl3("10");
		swsmshighcfg.setSjgl4("10");
		swsmshighcfg.setSjgl5("10");
		swsmshighcfg.setSjfl1("6");
		swsmshighcfg.setSjfl2("6");
		swsmshighcfg.setSjshui("6");
		return swsmshighcfg;
	}
	
	public LiqingziduancfgView getDefaultsmshighlqcfg3View(String gprsbh) {
		LiqingziduancfgView lqsmshighcfg = new LiqingziduancfgView();
		lqsmshighcfg.setLeixin("14");
		lqsmshighcfg.setGprsbianhao(gprsbh);
		lqsmshighcfg.setSjg1("10");
		lqsmshighcfg.setSjg2("10");
		lqsmshighcfg.setSjg3("10");
		lqsmshighcfg.setSjg4("10");
		lqsmshighcfg.setSjg5("10");
		lqsmshighcfg.setSjg6("10");
		lqsmshighcfg.setSjg7("10");
		lqsmshighcfg.setSjf1("6");
		lqsmshighcfg.setSjf2("6");
		lqsmshighcfg.setSjlq("6");
		lqsmshighcfg.setSjtjj("6");
//		lqsmshighcfg.setSjshui("6");
		return lqsmshighcfg;
	}
	
	public Shuiwenziduancfg swsmshighfindBybh3(String gprsbh) {
		Shuiwenziduancfg smshighcfg3 = swcfgDAO.findByGprsbhandleixin(gprsbh, "14");
		if (null == smshighcfg3) {
			smshighcfg3 = getDefaultsmshighswcfg3(gprsbh);
		}
		return smshighcfg3;
	}
	
	public ShuiwenziduancfgView swsmshighfindBybh3View(String gprsbh) {
		ShuiwenziduancfgView smshighcfg3 = swcfgViewDAO.findByGprsbhandleixin(gprsbh, "14");
		if (null == smshighcfg3) {
			smshighcfg3 = getDefaultsmshighswcfg3View(gprsbh);
		}
		return smshighcfg3;
	}
	
	public LiqingziduancfgView lqsmshighfindBybh3View(String gprsbh) {
		LiqingziduancfgView smshighcfg3 = lqviewDAO.findByGprsbhandleixin(gprsbh, "14");
		if (null == smshighcfg3) {
			smshighcfg3 = getDefaultsmshighlqcfg3View(gprsbh);
		}
		return smshighcfg3;
	}
	
	public Shuiwenziduancfg swsmsnumberfindBybh3(String gprsbh) {
		return swcfgDAO.findByGprsbhandleixin(gprsbh, "15");	
	}
	
	//获取水稳理论配合比
	public void swsetLilunphb(Integer xxbh, String gprsbh,String sjfl1,String sjfl2) {
		if(StringUtil.Null2Blank(sjfl1).length()>0 || StringUtil.Null2Blank(sjfl2).length()>0){
			Shuiwenxixxlilun swlilun=swlilunService.getswlilunMoren(gprsbh);
			if(swlilun!=null){
				Shuiwentongji swxx = swDAO.get(xxbh);
				if (null != swxx) {
					swxx.setBiaoshi(String.valueOf(swlilun.getLlid()));
					swDAO.saveOrUpdate(swxx);
				}
			}
		}
	}
	
	public void swcfgadd(Shuiwenziduancfg swcfg) {
		swcfgDAO.saveOrUpdate(swcfg);
	}
	
	public void swsmscfgdel(String gprsbh) {
		Shuiwenziduancfg swcfg = swissmsfindBybh(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmslowfindBybh(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmshighfindBybh(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmsnumberfindBybh(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmscontentfindBybh(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmsshejifindBybh(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmsshejifindBybh2(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmslowfindBybh2(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmshighfindBybh2(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmsnumberfindBybh2(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmslowfindBybh3(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmshighfindBybh3(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
		swcfg = swsmsnumberfindBybh3(gprsbh);
		if (null != swcfg) {
			swcfgDAO.delete(swcfg);
		}
	}
	
	public Shuiwenziduancfg swsmsshejifindBybh2(String gprsbh) {
		Shuiwenziduancfg smsshejicfg2 = swcfgDAO.findByGprsbhandleixin(gprsbh, "16");
		if (null == smsshejicfg2) {
			smsshejicfg2 = getDefaultsmsshejiswcfg2(gprsbh);
		}
		return smsshejicfg2;
	}
	
	public Shuiwenziduancfg getDefaultsmsshejiswcfg2(String gprsbh) {
		Shuiwenziduancfg swsmsshejicfg2 = new Shuiwenziduancfg();
		swsmsshejicfg2.setLeixing("16");
		swsmsshejicfg2.setGprsbianhao(gprsbh);
		return swsmsshejicfg2;
	}
	
	public void Calcjipei(ShuiwenmanualphbView manualhv,Shaifenshiyan shaifen,String strpers,String strbianhao,Integer bianhao){
		//计算标准级配和合成级配
		String[] Arrpassper={"passper1","passper2","passper3","passper4","passper5",
						     "passper6","passper7","passper8","passper9","passper10",
							 "passper11","passper12","passper13","passper14","passper15"};
				
		Shaifenjieguo sfjieguo=shaifenService.getShaifenjieguoBylqId(manualhv.getBianhao());
		if(sfjieguo==null){
			sfjieguo=new Shaifenjieguo();
			sfjieguo.setSwbianhao(manualhv.getBianhao());
		}
		//如果实时上传的数据已经计算过级配，那就将其级配给干掉，然后在重新计算。
		Shuiwentongji swxixx=getswBybianhao(bianhao, strbianhao);
		if(swxixx!=null){
			for(int x=0;x<Arrpassper.length;x++){
				//合成级配
				invokeSet(sfjieguo,Arrpassper[x],"");
			}
			swxixx.setIsdo("");
			swxixx.setSjglsf1("");
			swxixx.setSjglsf2("");
			swxixx.setSjglsf3("");
			swxixx.setSjglsf4("");
			swxixx.setSjglsf5("");
			swxixx.setSjflsf1("");
			swxixx.setSjflsf2("");
			swDAO.saveOrUpdate(swxixx);
			shaifenService.saveShaifenjieguo(sfjieguo);
			sfjieguo=shaifenService.getShaifenjieguoBylqId(manualhv.getBianhao());
		}else{
			swxixx=swDAO.get(manualhv.getBianhao());
		}
		
		for(int x=0;x<Arrpassper.length;x++){
			//合成级配
			//linjian
			try{
				invokeSet(sfjieguo,Arrpassper[x],
						String.valueOf(
								   StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{}))+
								   Float.parseFloat(String.format("%1$.1f",StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strpers.replaceFirst(strpers.substring(0,1),strpers.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{}))/100*
								              			                   StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))))));
				
				
				
				System.out.println("Arrpassper[x]:"+Arrpassper[x]);
				System.out.println("x当前刷新材料占总的百分比:"+StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{})));	
				System.out.println("y实际生产配比:"+StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strpers.replaceFirst(strpers.substring(0,1),strpers.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{})));
			    System.out.println("z筛分录入的值:"+StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{})));
			    System.out.println("x+y/100*z="+ String.valueOf(
						   StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{}))+
						   Float.parseFloat(String.format("%1$.1f",StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strpers.replaceFirst(strpers.substring(0,1),strpers.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{}))/100*
						              			                   StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{})))))
						);
			    //	System.out.println("m:"+StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strpers.replaceFirst(strpers.substring(0,1),strpers.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{})));
			
				
				
			
			}catch(Exception ex){}
				
			//存储编号
			try{
				invokeSet(swxixx,strbianhao,String.valueOf(shaifen.getId()));
			}catch(Exception ex){}
			
			if(strbianhao.equalsIgnoreCase("sjflsf2") && ((x+1)==Arrpassper.length)){  //表示此时累加完成！
				swxixx.setIsdo("1");
			}
		}
		shaifenService.saveShaifenjieguo(sfjieguo);
		swDAO.saveOrUpdate(swxixx);
	}
	
	public void Calcjipeiphb(ShuiwenphbView manualhv,Shaifenshiyan shaifen,String strper,String strpers,String strbianhao,Integer bianhao){
		//计算标准级配和合成级配
		String[] ArrstandPassper={"standPassper1","standPassper2","standPassper3","standPassper4","standPassper5",
								  "standPassper6","standPassper7","standPassper8","standPassper9","standPassper10",
								  "standPassper11","standPassper12","standPassper13","standPassper14","standPassper15"};
		String[] Arrpassper={"passper1","passper2","passper3","passper4","passper5",
						     "passper6","passper7","passper8","passper9","passper10",
							 "passper11","passper12","passper13","passper14","passper15"};
				
		Shaifenjieguo sfjieguo=shaifenService.getShaifenjieguoBylqId(manualhv.getBianhao());
		if(sfjieguo==null){
			sfjieguo=new Shaifenjieguo();
			sfjieguo.setSwbianhao(manualhv.getBianhao());
		}
		//如果实时上传的数据已经计算过级配，那就将其级配给干掉，然后在重新计算。
		Shuiwentongji swxixx=getswBybianhao(bianhao, strbianhao);
		if(swxixx!=null){
			for(int x=0;x<ArrstandPassper.length;x++){
				//标准级配
				invokeSet(sfjieguo,ArrstandPassper[x],"");
				
				//合成级配
				invokeSet(sfjieguo,Arrpassper[x],"");
			}
			swxixx.setIsdo("");
			swxixx.setSjglsf1("");
			swxixx.setSjglsf2("");
			swxixx.setSjglsf3("");
			swxixx.setSjglsf4("");
			swxixx.setSjglsf5("");
			swxixx.setSjflsf1("");
			swxixx.setSjflsf2("");
			swDAO.saveOrUpdate(swxixx);
			shaifenService.saveShaifenjieguo(sfjieguo);
			sfjieguo=shaifenService.getShaifenjieguoBylqId(manualhv.getBianhao());
		}else{
			swxixx=swDAO.get(manualhv.getBianhao());
		}
		
		for(int x=0;x<ArrstandPassper.length;x++){
			//标准级配
			try{
				invokeSet(sfjieguo,ArrstandPassper[x],
						String.valueOf(
								   StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+ArrstandPassper[x].replaceFirst(ArrstandPassper[x].substring(0,1),ArrstandPassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{}))+
								   Float.parseFloat(String.format("%1$.1f",StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strper.replaceFirst(strper.substring(0,1),strper.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{}))/100*
								              			                   StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))))));
			
//				System.out.println("x:"+StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+ArrstandPassper[x].replaceFirst(ArrstandPassper[x].substring(0,1),ArrstandPassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{})));
//				System.out.println("y:"+StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strper.replaceFirst(strper.substring(0,1),strper.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{})));
//				System.out.println("z:"+StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{})));
//				System.out.println("m:"+StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strpers.replaceFirst(strpers.substring(0,1),strpers.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{})));
			
			}catch(Exception ex){}
			
			//合成级配
			try{
				invokeSet(sfjieguo,Arrpassper[x],
						String.valueOf(
								   StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{}))+
								   Float.parseFloat(String.format("%1$.1f",StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strpers.replaceFirst(strpers.substring(0,1),strpers.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{}))/100*
								              			                   StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))))));
			}catch(Exception ex){}
				
			//存储编号
			try{
				invokeSet(swxixx,strbianhao,String.valueOf(shaifen.getId()));
			}catch(Exception ex){}
			
			if(strbianhao.equalsIgnoreCase("sjflsf2") && ((x+1)==ArrstandPassper.length)){  //表示此时累加完成！
				swxixx.setIsdo("1");
			}
		}
		shaifenService.saveShaifenjieguo(sfjieguo);
		swDAO.saveOrUpdate(swxixx);
	}
	
	/** 
     * java反射bean的set方法 
     *  
     * @param objectClass 
     * @param fieldName 
     * @return 
     */   
    public static Method getSetMethod(Class objectClass, String fieldName) {  
        try {  
            Class[] parameterTypes = new Class[1];  
            Field field = objectClass.getDeclaredField(fieldName);  
            parameterTypes[0] = field.getType();  
            StringBuffer sb = new StringBuffer();  
            sb.append("set");  
            sb.append(fieldName.substring(0, 1).toUpperCase());  
            sb.append(fieldName.substring(1));            
            Method method = objectClass.getMethod(sb.toString(), parameterTypes); 
            
            return method;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    /** 
     * 执行set方法 
     *  
     * @param o 执行对象 
     * @param fieldName 属性 
     * @param value 值 
     */  
    public static void invokeSet(Object o, String fieldName, Object value) {  
        Method method = getSetMethod(o.getClass(), fieldName);  
        try {  
            method.invoke(o, new Object[] { value });  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
	public Shuiwentongji getswBybianhao(Integer bianhao,String shaifenbianhao){
		List<Shuiwentongji> swlist=swDAO.find("from Shuiwentongji where isdo='1' and bianhao="+bianhao);
		if(swlist!=null && swlist.size()>0){
			return swlist.get(0);
		}else{
			return null;
		}
	}
	
	//清空客户端编号
	public boolean clearkehuduanbianhao(String shebeibianhao){
		boolean flag=false;
		List<Banhezhanxinxi> list=this.bhzDAO.find("from Banhezhanxinxi where gprsbianhao='"+shebeibianhao+"'");
		if(list!=null){
			for(Banhezhanxinxi banhezhan:list){
				if(StringUtil.Null2Blank(banhezhan.getShebeileixin())!=""){
					if(banhezhan.getShebeileixin().equals("1")){        //混凝土拌和站
						int count=this.xiangDAO.bulkUpdate("update xiangxixx set kehuduanbianhao='' where shebeibianhao='"+shebeibianhao+"' and kehuduanbianhao<>'' ");
						if(count>0){
							flag=true;
						}else{
							flag=false;
						}
					}else if(banhezhan.getShebeileixin().equals("2")){				//沥青拌和站	
						int count=this.lqDAO.bulkUpdate("update Liqingxixx set kehuduanbianhao='' where shebeibianhao='"+shebeibianhao+"' and kehuduanbianhao<>'' ");
						if(count>0){
							flag=true;
						}else{
							flag=false;
						}
					}else if(banhezhan.getShebeileixin().equals("3")){            //压力机
					}else if(banhezhan.getShebeileixin().equals("6")){          //水稳拌合站
						int count=this.swDAO.bulkUpdate("update Shuiwentongji set kehuduanbianhao='' where shebeibianhao='"+shebeibianhao+"' and kehuduanbianhao<>'' ");
						if(count>0){
							flag=true;
						}else{
							flag=false;
						}
					}
				}
			}
		}
		return flag;
	}
	
	//获取沥青理论配合比
	public void lqsetLilunphb(String sjysb,String sjlq,String shebeibianhao,Integer bianhao) {
		if(StringUtil.Null2Blank(sjysb).length()>0 || StringUtil.Null2Blank(sjlq).length()>0){
			Liqingxixxlilun lqlilun=lqlilunService.getlqlilunMoren(shebeibianhao);
			if(lqlilun!=null){
				Liqingxixx lqxx = lqDAO.get(bianhao);
				if(lqxx!=null){
					lqxx.setBiaoshi(String.valueOf(lqlilun.getLlid()));
					lqDAO.saveOrUpdate(lqxx);
				}
			}
		}
	}
	
	
	public void LqphbmanualCalcjipei(LiqingmanualphbView manualhv,Lqshaifenshiyan shaifen,String strper,String strpers,String strbianhao,Integer bianhao){
		//计算标准级配和合成级配
		String[] ArrstandPassper={"standPassper1","standPassper2","standPassper3","standPassper4","standPassper5",
								  "standPassper6","standPassper7","standPassper8","standPassper9","standPassper10",
								  "standPassper11","standPassper12","standPassper13","standPassper14","standPassper15"};
		String[] Arrpassper={"passper1","passper2","passper3","passper4","passper5",
						     "passper6","passper7","passper8","passper9","passper10",
							 "passper11","passper12","passper13","passper14","passper15"};
				
		Lqshaifenjieguo sfjieguo=lqshaifenService.getLqShaifenjieguoBylqId(manualhv.getBianhao());
		if(sfjieguo==null){
			sfjieguo=new Lqshaifenjieguo();
			sfjieguo.setLqbianhao(manualhv.getBianhao());
		}
		//如果实时上传的数据已经计算过级配，那就将其级配给干掉，然后在重新计算。
		Liqingxixx lqxixx=getlqBybianhao(bianhao, strbianhao);
		if(lqxixx!=null){
			for(int x=0;x<ArrstandPassper.length;x++){
				//标准级配
				invokeSet(sfjieguo,ArrstandPassper[x],"");
				
				//合成级配
				invokeSet(sfjieguo,Arrpassper[x],"");
			}
			lqxixx.setIsdo("");
			lqxixx.setSjglsf1("");
			lqxixx.setSjglsf2("");
			lqxixx.setSjglsf3("");
			lqxixx.setSjglsf4("");
			lqxixx.setSjglsf5("");
			lqxixx.setSjglsf6("");
			lqxixx.setSjglsf7("");
			lqxixx.setSjflsf1("");
			lqxixx.setSjflsf2("");
			lqDAO.saveOrUpdate(lqxixx);
			lqshaifenService.saveLqShaifenjieguo(sfjieguo);
			sfjieguo=lqshaifenService.getLqShaifenjieguoBylqId(manualhv.getBianhao());
		}else{
			lqxixx=lqDAO.get(manualhv.getBianhao());
		}
		
		for(int x=0;x<ArrstandPassper.length;x++){
			//标准级配
			try{
				invokeSet(sfjieguo,ArrstandPassper[x],
						String.valueOf(
								   StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+ArrstandPassper[x].replaceFirst(ArrstandPassper[x].substring(0,1),ArrstandPassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{}))+
								   Float.parseFloat(String.format("%1$.1f",StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strper.replaceFirst(strper.substring(0,1),strper.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{}))/100*
								              			                   StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))))));
			
			
			}catch(Exception ex){}
			
			//合成级配
			try{
				invokeSet(sfjieguo,Arrpassper[x],
						String.valueOf(
								   StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{}))+
								   Float.parseFloat(String.format("%1$.1f",StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strpers.replaceFirst(strpers.substring(0,1),strpers.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{}))/100*
								              			                   StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))))));
			
			}catch(Exception ex){}
				
			//存储编号
			try{
				invokeSet(lqxixx,strbianhao,String.valueOf(shaifen.getId()));
			}catch(Exception ex){}
			
			if(strbianhao.equalsIgnoreCase("sjflsf2") && ((x+1)==ArrstandPassper.length)){  //表示此时累加完成！
				lqxixx.setIsdo("1");
			}
		}
		lqshaifenService.saveLqShaifenjieguo(sfjieguo);
		lqDAO.saveOrUpdate(lqxixx);
	}
	
	/***
	public void LqphbCalcjipei(LiqingphbView phbhv,Shaifenshiyan shaifen,String strper,String strpers,String strbianhao,Integer bianhao){
		//计算标准级配和合成级配
		String[] ArrstandPassper={"standPassper1","standPassper2","standPassper3","standPassper4","standPassper5",
								  "standPassper6","standPassper7","standPassper8","standPassper9","standPassper10",
								  "standPassper11","standPassper12","standPassper13","standPassper14","standPassper15"};
		String[] Arrpassper={"passper1","passper2","passper3","passper4","passper5",
						     "passper6","passper7","passper8","passper9","passper10",
							 "passper11","passper12","passper13","passper14","passper15"};
				
		Lqshaifenjieguo sfjieguo=shaifenService.getLqShaifenjieguoBylqId(phbhv.getBianhao());
		if(sfjieguo==null){
			sfjieguo=new Lqshaifenjieguo();
			sfjieguo.setLqbianhao(phbhv.getBianhao());
		}
		//如果实时上传的数据已经计算过级配，那就将其级配给干掉，然后在重新计算。
		Liqingxixx lqxixx=getlqBybianhao(bianhao, strbianhao);
		if(lqxixx!=null){
			for(int x=0;x<ArrstandPassper.length;x++){
				//标准级配
				invokeSet(sfjieguo,ArrstandPassper[x],"");
				
				//合成级配
				invokeSet(sfjieguo,Arrpassper[x],"");
			}
			lqxixx.setIsdo("");
			lqxixx.setSjglsf1("");
			lqxixx.setSjglsf2("");
			lqxixx.setSjglsf3("");
			lqxixx.setSjglsf4("");
			lqxixx.setSjglsf5("");
			lqxixx.setSjglsf6("");
			lqxixx.setSjglsf7("");
			lqxixx.setSjflsf1("");
			lqxixx.setSjflsf2("");
			lqDAO.saveOrUpdate(lqxixx);
			shaifenService.saveLqShaifenjieguo(sfjieguo);
			sfjieguo=shaifenService.getLqShaifenjieguoBylqId(phbhv.getBianhao());
		}else{
			lqxixx=lqDAO.get(phbhv.getBianhao());
		}
		
		for(int x=0;x<ArrstandPassper.length;x++){
			//标准级配
			try{
				invokeSet(sfjieguo,ArrstandPassper[x],
						String.valueOf(
								   StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+ArrstandPassper[x].replaceFirst(ArrstandPassper[x].substring(0,1),ArrstandPassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{}))+
								   Float.parseFloat(String.format("%1$.1f",StringUtil.StrToZero((String)phbhv.getClass().getMethod("get"+strper.replaceFirst(strper.substring(0,1),strper.substring(0,1).toUpperCase()),new Class[]{}).invoke(phbhv,new Object[]{}))/100*
								              			                   StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))))));
			
//				System.out.println("x:"+StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+ArrstandPassper[x].replaceFirst(ArrstandPassper[x].substring(0,1),ArrstandPassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{})));
//				System.out.println("y:"+StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strper.replaceFirst(strper.substring(0,1),strper.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{})));
//				System.out.println("z:"+StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{})));
//				System.out.println("m:"+StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strpers.replaceFirst(strpers.substring(0,1),strpers.substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{})));
			
			}catch(Exception ex){}
			
			//合成级配
			try{
				invokeSet(sfjieguo,Arrpassper[x],
						String.valueOf(
								   StringUtil.StrToZero((String)sfjieguo.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(sfjieguo,new Object[]{}))+
								   Float.parseFloat(String.format("%1$.1f",StringUtil.StrToZero((String)phbhv.getClass().getMethod("get"+strpers.replaceFirst(strpers.substring(0,1),strpers.substring(0,1).toUpperCase()),new Class[]{}).invoke(phbhv,new Object[]{}))/100*
								              			                   StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+Arrpassper[x].replaceFirst(Arrpassper[x].substring(0,1),Arrpassper[x].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))))));
			
			}catch(Exception ex){}
				
			//存储编号
			try{
				invokeSet(lqxixx,strbianhao,String.valueOf(shaifen.getId()));
			}catch(Exception ex){}
			
			if(strbianhao.equalsIgnoreCase("sjflsf2") && ((x+1)==ArrstandPassper.length)){  //表示此时累加完成！
				lqxixx.setIsdo("1");
			}
		}
		shaifenService.saveLqShaifenjieguo(sfjieguo);
		lqDAO.saveOrUpdate(lqxixx);
	}
	***/
	
	public Liqingxixx getlqBybianhao(Integer bianhao,String shaifenbianhao){
		List<Liqingxixx> lqlist=lqDAO.find("from Liqingxixx where isdo='1' and bianhao="+bianhao);
		if(lqlist!=null && lqlist.size()>0){
			return lqlist.get(0);
		}else{
			return null;
		}
	}

	public HandSet getRealPhoneNumber2(String phoneNo) {
		HandSet hs=null;
		if (StringUtil.Null2Blank(phoneNo).length()>0 && 
			StringUtil.Null2Blank(phoneNo).length()<10 && 
			phoneNo.matches("[0-9]{1,}")) {
			try {
				hs = handsetDAO.get(Integer.parseInt(phoneNo));
			} catch (Exception e) {}
		}
		return hs;
	}

	public Hashtable saveandSendSms(int xxbh, String shebeibianhao, String phonenumber, String realphonename,String chuliaoshijian,String sms, String apitype,String type) {		
		Hashtable recTable = null;
		if (StringUtil.Null2Blank(shebeibianhao).length()>0 && shebeibianhao.equalsIgnoreCase("test")) {
			logger.info(shebeibianhao+"发送短信("+phonenumber+"):"+sms);
			recTable = Smssender.SendSms(phonenumber, sms, apitype);			
		} else {
			String queryString = "from Banhezhanxinxi as model where model.gprsbianhao=?";
			List<Banhezhanxinxi> bhzlist = bhzDAO.find(queryString, shebeibianhao);
			if (bhzlist.size() > 0) {
				Banhezhanxinxi bhz = bhzlist.get(0);
				if (null != bhz.getBiaoduanid() && bhz.getBiaoduanid() > 0) {
						Smsrecord smsrecord = smsService.getSmsrecordBybiaoduanid(bhz.getBiaoduanid());
					if (null != smsrecord && null != smsrecord.getSmscount() && smsrecord.getSmscount() > 0) {
						if (null == smsrecord.getTotalcount()) {
							smsrecord.setTotalcount(0);
						}
						if (null == smsrecord.getSuccesscount()) {
							smsrecord.setSuccesscount(0);
						}
						if (null == smsrecord.getFailcount()) {
							smsrecord.setFailcount(0);
						}
						logger.info(shebeibianhao+"发送短信("+phonenumber+"):"+sms);
						recTable = Smssender.SendSms(phonenumber, sms, apitype);
						if (null != recTable) {
							Smsinfo smsinfo = new Smsinfo();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String fsTime = sdf.format(System.currentTimeMillis());
							if(type.equalsIgnoreCase("lq")){
								smsinfo.setLqbianhao(String.valueOf(xxbh));
								smsinfo.setSwbianhao("");
							}else if(type.equalsIgnoreCase("sw")){
								smsinfo.setSwbianhao(String.valueOf(xxbh));
								smsinfo.setLqbianhao("");
							}
							smsinfo.setShijian(fsTime);
							smsinfo.setShebeibianhao(shebeibianhao);
							smsinfo.setFasongphone(phonenumber);
							smsinfo.setFasongcount(phonenumber.split(",").length);
							smsinfo.setFasongcontent(sms);
							smsinfo.setFasongstatus((String)recTable.get("smsstatus"));
							smsinfo.setReturnmsg((String)recTable.get("mymsg"));
							smsinfo.setYonghumingchen(realphonename);
							smsinfo.setChuliaoshijian(chuliaoshijian);
							String successphone = StringUtil.Null2Blank((String)recTable.get("successphone"));
							if (successphone.length() > 0) {
								smsinfo.setSuccessphone(successphone);
								smsinfo.setSuccesscount(successphone.split(",").length);
								smsrecord.setSmscount(smsrecord.getSmscount() - smsinfo.getSuccesscount());
								smsrecord.setTotalcount(smsrecord.getTotalcount() + smsinfo.getFasongcount());
								smsrecord.setSuccesscount(smsrecord.getSuccesscount() + smsinfo.getSuccesscount());
								smsrecord.setFailcount(smsrecord.getFailcount() + smsinfo.getFasongcount() - smsinfo.getSuccesscount());
							} else {
								logger.info("短信账号异常，发送失败");
								smsrecord.setTotalcount(smsrecord.getTotalcount() + smsinfo.getFasongcount());
								smsrecord.setFailcount(smsrecord.getFailcount() + smsinfo.getFasongcount());
							}
							smsService.saveOrUpdateSmsrecord(smsrecord);
							smsDAO.saveOrUpdate(smsinfo);
						}
					} else {
						logger.info("标段"+bhz.getBiaoduanid()+bhz.getBanhezhanminchen()+bhz.getGprsbianhao()+"短信余额不足");
					}
				} else {
					logger.info("设备"+shebeibianhao+"未指定标段");
				}
			} else {
				logger.info("设备"+shebeibianhao+"未指定标段");
			}
		}
		return recTable;
	}	
	
	public void updateSwclAttachment(int id, String value) {
		Shuiwenxixxjieguo swxxjieguo =getSwjieguobybh(id);
		if (null != swxxjieguo) {
			swxxjieguo.setFilepath(value);
			swjieguoDAO.saveOrUpdate(swxxjieguo);
		}
	}
	
	public void updateLqclAttachment(int id, String value) {
		Liqingxixxjieguo lqxxjieguo =getLljieguobybh(id);
		if (null != lqxxjieguo) {
			lqxxjieguo.setFilepath(value);
			lqjieguoDAO.saveOrUpdate(lqxxjieguo);
		}
	}
	
	public Shuiwentongji getShuiwenxixx(Integer bianhao){
		return swDAO.get(bianhao);
	}
	
	public void saveShuiwenxixx(Shuiwentongji swxixx){
		swDAO.saveOrUpdate(swxixx);
	}
	
	public void saveYezhuFile(YezhuFile yezhu){
		yezhuFileDAO.saveOrUpdate(yezhu);
	}
	
	public List<YezhuFile> getYezhuFile(){
		return yezhuFileDAO.find("from YezhuFile");
	}
	
	public void del(Integer id){
		 yezhuFileDAO.deleteByKey(id);
	}
	
	//判断数据是否已经进行了料仓合并，如果合并了，那么就不在合并;否则，反之
	public boolean ishebing(Integer bianhao){
		boolean flag=false;
		Shuiwentongji swxixx=swDAO.get(bianhao);
		if(swxixx!=null){
			if(StringUtil.Null2Blank(swxixx.getBeiy3()).equalsIgnoreCase("1")){
				flag=true;
			}else{
				flag=false;
			}
		}else{
			flag=false;
		}
		return flag;
	}
	
	public List<TopRealMaxShuiwenxixxView> getMaxswNOOFFList() {
		return topswDAO.loadAll();
	}
	
	public List<TopRealMaxLiqingView> getMaxlqNOOFFList() {
		return realMaxlqDAO.loadAll();
	}
	
	public void updateBhzNOOFFData(String shebeibianhao,String baocunshijian,String smsNOOFFSendSign){
		String queryString = "from Banhezhanxinxi as model where model.gprsbianhao=?";
		List<Banhezhanxinxi> bhzlist = bhzDAO.find(queryString, shebeibianhao);
		if (bhzlist.size() > 0) {
			Banhezhanxinxi bhz = bhzlist.get(0);
			bhz.setSmsNOOFFDateTime(baocunshijian);
			bhz.setSmsNOOFFSendSign(smsNOOFFSendSign);
			bhzDAO.saveOrUpdate(bhz);
			
		}
	}
	
	//获取沥青理论配合比
	public void lqsetLilunphb(Integer xxbh, String gprsbh,String sjysb,double lowsjysb,double highsjysb) {
		int Lqlilun=lqlilunDAO.lqgetlilunid(gprsbh,sjysb,lowsjysb,highsjysb);
		if (Lqlilun>0) {
			Liqingxixx lqxx = lqDAO.get(xxbh);
			if (null != lqxx) {
				lqxx.setBiaoshi(String.valueOf(Lqlilun));
				lqDAO.saveOrUpdate(lqxx);
			}
		}
	}
	
	public Shaifenziduancfg swjpsmslowfindBybh(String gprsbh) {
		Shaifenziduancfg jpsmslowcfg = sfzdDAO.findByGprsbhandleixin(gprsbh, "5");
		if (null == jpsmslowcfg) {
			jpsmslowcfg = getDefaultjpsmslowswcfg(gprsbh);
		}
		return jpsmslowcfg;
	}
    public Shaifenziduancfg getDefaultjpsmslowswcfg(String gprsbh) {
		Shaifenziduancfg swhcjpissmscfg = new Shaifenziduancfg();
		swhcjpissmscfg.setLeixing("5");
		swhcjpissmscfg.setGprsbianhao(gprsbh);
		swhcjpissmscfg.setPassper1("-2");
		swhcjpissmscfg.setPassper2("-4");
		swhcjpissmscfg.setPassper3("-4");
		swhcjpissmscfg.setPassper4("-4");
		swhcjpissmscfg.setPassper5("-4");
		swhcjpissmscfg.setPassper6("-4");
		swhcjpissmscfg.setPassper7("-6");
		swhcjpissmscfg.setPassper8("-6");
		swhcjpissmscfg.setPassper9("-6");
		swhcjpissmscfg.setPassper10("-6");
		swhcjpissmscfg.setPassper11("-6");
		swhcjpissmscfg.setPassper12("-6");
		swhcjpissmscfg.setPassper13("-6");
		swhcjpissmscfg.setPassper14("-6");
		swhcjpissmscfg.setPassper15("-6");
		return swhcjpissmscfg;
	}
    
    public Shaifenziduancfg swjpsmshighfindBybh(String gprsbh) {
		Shaifenziduancfg jpsmshighcfg = sfzdDAO.findByGprsbhandleixin(gprsbh, "6");
		if (null == jpsmshighcfg) {
			jpsmshighcfg = getDefaultjpsmshighswcfg(gprsbh);
		}
		return jpsmshighcfg;
	}
    
    public Shaifenziduancfg getDefaultjpsmshighswcfg(String gprsbh) {
		Shaifenziduancfg swhcjpissmscfg = new Shaifenziduancfg();
		swhcjpissmscfg.setLeixing("6");
		swhcjpissmscfg.setGprsbianhao(gprsbh);
		swhcjpissmscfg.setPassper1("2");
		swhcjpissmscfg.setPassper2("4");
		swhcjpissmscfg.setPassper3("4");
		swhcjpissmscfg.setPassper4("4");
		swhcjpissmscfg.setPassper5("4");
		swhcjpissmscfg.setPassper6("4");
		swhcjpissmscfg.setPassper7("6");
		swhcjpissmscfg.setPassper8("6");
		swhcjpissmscfg.setPassper9("6");
		swhcjpissmscfg.setPassper10("6");
		swhcjpissmscfg.setPassper11("6");
		swhcjpissmscfg.setPassper12("6");
		swhcjpissmscfg.setPassper13("6");
		swhcjpissmscfg.setPassper14("6");
		swhcjpissmscfg.setPassper15("6");
		return swhcjpissmscfg;
	}
}