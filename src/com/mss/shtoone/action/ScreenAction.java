package com.mss.shtoone.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.Liqingxixxdanjia;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.Tanpujihuaxinxi;
import com.mss.shtoone.domain.TopRealSpeeddata1View;
import com.mss.shtoone.domain.TopRealSpeeddataView;
import com.mss.shtoone.domain.TopRealTemperaturedata1View;
import com.mss.shtoone.domain.TopRealTemperaturedataView;
import com.mss.shtoone.domain.WuchaIsShowData;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.QuerywuchaService;
import com.mss.shtoone.service.SmsinfoService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.service.TanpujihuaxinxiService;
import com.mss.shtoone.service.UserService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/screen")
public class ScreenAction extends ActionSupport {

	/**
	 * 大屏展示action
	 */
	private static final long serialVersionUID = 2422818359966271113L;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private GetdataService dataService;
	
	@Autowired
	private TanpujihuaxinxiService tpjhxxService;
	
	@Autowired
	private SmsinfoService smsService;
	
	@Autowired
	private QuerywuchaService querywcService;
	
	//李长江开始增加
	
    private Integer biaoduan;
	
	private Integer usertype;
	
	private Integer bsid;

	private Map<Integer, String> listmap;
	
	private String startTime;
	private String endTime;
	private Integer xiangmubu;
	private String shebeibianhao;
	private int maxPageItems;
	private Integer pageNo;	
	private Map<Integer, String> biaoduanlistmap;//标段集合
	private Map<Integer, String> xmblistmap;
	private Map<Integer, String> bhzlistmap;
	private Map<String, String> shebeilistmap;
	private Map<String, String> shebeilistmap_tanpuwendu;
	private Map<String, String> shebeilistmap_tanpusudu;
	private Map<String, String> shebeilistmap_nianyawendu;
	private Map<String, String> shebeilistmap_nianyasudu;
	private GenericPageMode xcQuerytmppgs;
	private GenericPageMode xcQuerytmppgs_tanpusudu;
	private GenericPageMode xcQueryspeedpgs_nianyawendu;
	private GenericPageMode xcQueryspeedpgs_nianyasudu;
	private GenericPageMode environmentpageMode;
	
	private Integer cnfxlx=4;
	private List<LiqingView> lqviews_day;
	private List<LiqingView> lqviews_week;
	private List<LiqingView> lqviews_month;
	private Integer initialization;//标记是否是第一次进入0为第一次进入
	
	private Integer fzlx=0;
	private Integer jbsj=0;
	private String peifang;
	private LiqingziduancfgView lqzdcfg;
	
	private List<LiqingView> smstjlist;
	
	private Integer[] clselect;
	private List<WuchaIsShowData> cllist;
	private LiqingziduancfgView liqingisShow;
	private LiqingphbView lqviews;
	private Liqingxixxdanjia lqdanjia;
	private LiqingziduancfgView liqingField;
	private LiqingziduancfgView lqbhzField;
	private String testbhz;
	private String radio_name_tanpuwendu;
	private String radio_name_tanpusudu;
	private String radio_name_nianyawendu;
	private String radio_name_nianyasudu;
	private Integer[] wuchaselect;
	private List<WuchaIsShowData> wuchalist;
	private String peifan;
	private GenericPageMode liqings;
	private LiqingziduancfgView lqbhzisShow;
	private GenericPageMode sgjdpagemode;
	public String getPeifang() {
		return peifang;
	}

	public void setPeifang(String peifang) {
		this.peifang = peifang;
	}

	public GenericPageMode getSgjdpagemode() {
		return sgjdpagemode;
	}

	public void setSgjdpagemode(GenericPageMode sgjdpagemode) {
		this.sgjdpagemode = sgjdpagemode;
	}

	public LiqingziduancfgView getLqbhzisShow() {
		return lqbhzisShow;
	}

	public void setLqbhzisShow(LiqingziduancfgView lqbhzisShow) {
		this.lqbhzisShow = lqbhzisShow;
	}

	public GenericPageMode getLiqings() {
		return liqings;
	}

	public void setLiqings(GenericPageMode liqings) {
		this.liqings = liqings;
	}

	public String getPeifan() {
		return peifan;
	}

	public void setPeifan(String peifan) {
		this.peifan = peifan;
	}

	public Integer[] getWuchaselect() {
		return wuchaselect;
	}

	public void setWuchaselect(Integer[] wuchaselect) {
		this.wuchaselect = wuchaselect;
	}

	public List<WuchaIsShowData> getWuchalist() {
		return wuchalist;
	}

	public void setWuchalist(List<WuchaIsShowData> wuchalist) {
		this.wuchalist = wuchalist;
	}

	public String getRadio_name_tanpuwendu() {
		return radio_name_tanpuwendu;
	}

	public void setRadio_name_tanpuwendu(String radioNameTanpuwendu) {
		radio_name_tanpuwendu = radioNameTanpuwendu;
	}

	public String getRadio_name_tanpusudu() {
		return radio_name_tanpusudu;
	}

	public void setRadio_name_tanpusudu(String radioNameTanpusudu) {
		radio_name_tanpusudu = radioNameTanpusudu;
	}

	public String getRadio_name_nianyawendu() {
		return radio_name_nianyawendu;
	}

	public void setRadio_name_nianyawendu(String radioNameNianyawendu) {
		radio_name_nianyawendu = radioNameNianyawendu;
	}

	public String getRadio_name_nianyasudu() {
		return radio_name_nianyasudu;
	}

	public void setRadio_name_nianyasudu(String radioNameNianyasudu) {
		radio_name_nianyasudu = radioNameNianyasudu;
	}

	public Map<String, String> getShebeilistmap_tanpuwendu() {
		return shebeilistmap_tanpuwendu;
	}

	public void setShebeilistmap_tanpuwendu(
			Map<String, String> shebeilistmapTanpuwendu) {
		shebeilistmap_tanpuwendu = shebeilistmapTanpuwendu;
	}

	public Map<String, String> getShebeilistmap_tanpusudu() {
		return shebeilistmap_tanpusudu;
	}

	public void setShebeilistmap_tanpusudu(
			Map<String, String> shebeilistmapTanpusudu) {
		shebeilistmap_tanpusudu = shebeilistmapTanpusudu;
	}

	public Map<String, String> getShebeilistmap_nianyawendu() {
		return shebeilistmap_nianyawendu;
	}

	public void setShebeilistmap_nianyawendu(
			Map<String, String> shebeilistmapNianyawendu) {
		shebeilistmap_nianyawendu = shebeilistmapNianyawendu;
	}

	public Map<String, String> getShebeilistmap_nianyasudu() {
		return shebeilistmap_nianyasudu;
	}

	public void setShebeilistmap_nianyasudu(
			Map<String, String> shebeilistmapNianyasudu) {
		shebeilistmap_nianyasudu = shebeilistmapNianyasudu;
	}

	public LiqingziduancfgView getLqbhzField() {
		return lqbhzField;
	}

	public void setLqbhzField(LiqingziduancfgView lqbhzField) {
		this.lqbhzField = lqbhzField;
	}

	public String getTestbhz() {
		return testbhz;
	}

	public void setTestbhz(String testbhz) {
		this.testbhz = testbhz;
	}

	public LiqingziduancfgView getLiqingField() {
		return liqingField;
	}

	public void setLiqingField(LiqingziduancfgView liqingField) {
		this.liqingField = liqingField;
	}

	public Liqingxixxdanjia getLqdanjia() {
		return lqdanjia;
	}

	public void setLqdanjia(Liqingxixxdanjia lqdanjia) {
		this.lqdanjia = lqdanjia;
	}

	public LiqingphbView getLqviews() {
		return lqviews;
	}

	public void setLqviews(LiqingphbView lqviews) {
		this.lqviews = lqviews;
	}

	public Integer[] getClselect() {
		return clselect;
	}

	public void setClselect(Integer[] clselect) {
		this.clselect = clselect;
	}

	public List<WuchaIsShowData> getCllist() {
		return cllist;
	}

	public void setCllist(List<WuchaIsShowData> cllist) {
		this.cllist = cllist;
	}

	public LiqingziduancfgView getLiqingisShow() {
		return liqingisShow;
	}

	public void setLiqingisShow(LiqingziduancfgView liqingisShow) {
		this.liqingisShow = liqingisShow;
	}

	public List<LiqingView> getSmstjlist() {
		return smstjlist;
	}

	public void setSmstjlist(List<LiqingView> smstjlist) {
		this.smstjlist = smstjlist;
	}

	public Integer getFzlx() {
		return fzlx;
	}

	public void setFzlx(Integer fzlx) {
		this.fzlx = fzlx;
	}

	public Integer getJbsj() {
		return jbsj;
	}

	public void setJbsj(Integer jbsj) {
		this.jbsj = jbsj;
	}

	public LiqingziduancfgView getLqzdcfg() {
		return lqzdcfg;
	}

	public void setLqzdcfg(LiqingziduancfgView lqzdcfg) {
		this.lqzdcfg = lqzdcfg;
	}

	public Integer getInitialization() {
		return initialization;
	}

	public void setInitialization(Integer initialization) {
		this.initialization = initialization;
	}

	public Integer getCnfxlx() {
		return cnfxlx;
	}

	public void setCnfxlx(Integer cnfxlx) {
		this.cnfxlx = cnfxlx;
	}

	public List<LiqingView> getLqviews_day() {
		return lqviews_day;
	}

	public void setLqviews_day(List<LiqingView> lqviewsDay) {
		lqviews_day = lqviewsDay;
	}

	public List<LiqingView> getLqviews_week() {
		return lqviews_week;
	}

	public void setLqviews_week(List<LiqingView> lqviewsWeek) {
		lqviews_week = lqviewsWeek;
	}

	public List<LiqingView> getLqviews_month() {
		return lqviews_month;
	}

	public void setLqviews_month(List<LiqingView> lqviewsMonth) {
		lqviews_month = lqviewsMonth;
	}

	public GenericPageMode getEnvironmentpageMode() {
		return environmentpageMode;
	}

	public void setEnvironmentpageMode(GenericPageMode environmentpageMode) {
		this.environmentpageMode = environmentpageMode;
	}

	public GenericPageMode getXcQueryspeedpgs_nianyasudu() {
		return xcQueryspeedpgs_nianyasudu;
	}

	public void setXcQueryspeedpgs_nianyasudu(
			GenericPageMode xcQueryspeedpgsNianyasudu) {
		xcQueryspeedpgs_nianyasudu = xcQueryspeedpgsNianyasudu;
	}

	public GenericPageMode getXcQueryspeedpgs_nianyawendu() {
		return xcQueryspeedpgs_nianyawendu;
	}

	public void setXcQueryspeedpgs_nianyawendu(
			GenericPageMode xcQueryspeedpgsNianyawendu) {
		xcQueryspeedpgs_nianyawendu = xcQueryspeedpgsNianyawendu;
	}

	public GenericPageMode getXcQuerytmppgs_tanpusudu() {
		return xcQuerytmppgs_tanpusudu;
	}

	public void setXcQuerytmppgs_tanpusudu(GenericPageMode xcQuerytmppgsTanpusudu) {
		xcQuerytmppgs_tanpusudu = xcQuerytmppgsTanpusudu;
	}

	public GenericPageMode getXcQuerytmppgs() {
		return xcQuerytmppgs;
	}

	public void setXcQuerytmppgs(GenericPageMode xcQuerytmppgs) {
		this.xcQuerytmppgs = xcQuerytmppgs;
	}

	public Map<String, String> getShebeilistmap() {
		return shebeilistmap;
	}

	public void setShebeilistmap(Map<String, String> shebeilistmap) {
		this.shebeilistmap = shebeilistmap;
	}

	public Map<Integer, String> getBiaoduanlistmap() {
		return biaoduanlistmap;
	}

	public void setBiaoduanlistmap(Map<Integer, String> biaoduanlistmap) {
		this.biaoduanlistmap = biaoduanlistmap;
	}

	public Map<Integer, String> getXmblistmap() {
		return xmblistmap;
	}

	public void setXmblistmap(Map<Integer, String> xmblistmap) {
		this.xmblistmap = xmblistmap;
	}

	public Map<Integer, String> getBhzlistmap() {
		return bhzlistmap;
	}

	public void setBhzlistmap(Map<Integer, String> bhzlistmap) {
		this.bhzlistmap = bhzlistmap;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getXiangmubu() {
		return xiangmubu;
	}

	public void setXiangmubu(Integer xiangmubu) {
		this.xiangmubu = xiangmubu;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	public int getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Map<Integer, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<Integer, String> listmap) {
		this.listmap = listmap;
	}

	public Integer getBiaoduan() {
		return biaoduan;
	}

	public void setBiaoduan(Integer biaoduan) {
		this.biaoduan = biaoduan;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getBsid() {
		return bsid;
	}

	public void setBsid(Integer bsid) {
		this.bsid = bsid;
	}
	
	@Action("screen")
	public String screen(){
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        String bd = request.getParameter("biaoduan"); 
        if (StringUtil.Null2Blank(bd).length() > 0) {
        	try {
        		biaoduan = Integer.valueOf(bd);
			} catch (Exception e) {
			}
		}
		setUsertype(StringUtil.getUserType(request));
		setBsid(StringUtil.getBiaoshiId(request));
		/***
		if (1 == usertype) {
			listmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.biaoduanlist();
			for (Biaoduanxinxi bdxx : bdlist) {
				listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
			}
			if (null == biaoduan && bdlist.size()>0) {
				setBiaoduan(bdlist.get(0).getId());
			}
			setBsid(biaoduan);
		}
		**/
		if (1 == usertype) {
			if (null == biaoduan) {
				setBiaoduan(1);
			}
			setBsid(biaoduan);
			listmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.biaoduanlist();
			for (Biaoduanxinxi bdxx : bdlist) {
				listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
			}
		}
		
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		
		for (Biaoduanxinxi bdvar : bdlist) {
			biaoduanlistmap.put(bdvar.getId(), bdvar.getBiaoduanminchen());
		}
		
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request,biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
			
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(20);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			this.setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if(StringUtil.Null2Blank(request.getParameter("startTime")).length()>0){
			this.setStartTime(request.getParameter("startTime"));
		}
		if(StringUtil.Null2Blank(request.getParameter("endTime")).length()>0){
			this.setEndTime(request.getParameter("endTime"));
		}
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		
		/*
		 * 生产管理
		 */
        //油石比实时监控，按照东交给的要求， 显示近10或者更多盘的料，保证最后一盘是最新生产的
        request.setAttribute("strXML_youshibi", dataService.getdapingtop10lqysbXml(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        //材料监控
        request.setAttribute("strXML_cailiaoyongliang", dataService.getlqcailiaoyongliangXml(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        //出料温度
        request.setAttribute("strXML_chuliaowendu", dataService.getdapingAsphaltAngularXml3(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        // request.setAttribute("strXMLbp1", dataService.getAsphaltAngularXml1(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        //request.setAttribute("strXMLbp2", dataService.getAsphaltAngularXml2(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
      
        //误差查询
        if (null != wuchaselect && wuchaselect.length > 0) {
			Liqingziduancfg lqcfgisshow;
			lqcfgisshow = querywcService.getlqcfg(shebeibianhao);
			if (null == lqcfgisshow) {
				lqcfgisshow = new Liqingziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					lqcfgisshow.setGprsbianhao(shebeibianhao);
					lqcfgisshow.setLeixin("21");
				} else {
					lqcfgisshow.setGprsbianhao("all");
					lqcfgisshow.setLeixin("20");
				}
			}
			
			List<Integer> selectlist = new ArrayList<Integer>();
			for (int h = 0; h < wuchaselect.length; h++) {
				selectlist.add(wuchaselect[h]);
			}
			if (selectlist.contains(1)) {
				lqcfgisshow.setSjysb("1");
			} else {
				lqcfgisshow.setSjysb("0");
			}
			if (selectlist.contains(2)) {
				lqcfgisshow.setSjg1("1");
			} else {
				lqcfgisshow.setSjg1("0");
			}
			if (selectlist.contains(3)) {
				lqcfgisshow.setSjg2("1");
			} else {
				lqcfgisshow.setSjg2("0");
			}
			if (selectlist.contains(4)) {
				lqcfgisshow.setSjg3("1");
			} else {
				lqcfgisshow.setSjg3("0");
			}
			if (selectlist.contains(5)) {
				lqcfgisshow.setSjg4("1");
			} else {
				lqcfgisshow.setSjg4("0");
			}
			if (selectlist.contains(6)) {
				lqcfgisshow.setSjg5("1");
			} else {
				lqcfgisshow.setSjg5("0");
			}
			if (selectlist.contains(7)) {
				lqcfgisshow.setSjg6("1");
			} else {
				lqcfgisshow.setSjg6("0");
			}
			if (selectlist.contains(8)) {
				lqcfgisshow.setSjg7("1");
			} else {
				lqcfgisshow.setSjg7("0");
			} 
			if (selectlist.contains(9)) {
				lqcfgisshow.setSjf1("1");
			} else {
				lqcfgisshow.setSjf1("0");
			}
			if (selectlist.contains(10)) {
				lqcfgisshow.setSjf2("1");
			} else {
				lqcfgisshow.setSjf2("0");
			}
			if (selectlist.contains(11)) {
				lqcfgisshow.setSjlq("1");
			} else {
				lqcfgisshow.setSjlq("0");
			}
			if (selectlist.contains(12)) {
				lqcfgisshow.setSjtjj("1");
			} else {
				lqcfgisshow.setSjtjj("0");
			}			
			querywcService.saveLqcfg(lqcfgisshow);
		}


		String startTimeOne = request.getParameter("startTime");
		String endTimeOne = request.getParameter("endTime");
		setStartTime(startTimeOne);
		setEndTime(endTimeOne);
		setLiqings(querywcService.lqviewwuchalist(shebeibianhao, 
				startTimeOne, endTimeOne, biaoduan,xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request), pageNo, maxPageItems,peifan));
		LiqingziduancfgView lqfield = querywcService.getlqcfgfield(shebeibianhao);
		LiqingziduancfgView lqisshow = querywcService.getlqcfgisShow(shebeibianhao);		
		if (null == lqisshow) {
			Liqingziduancfg lqcfgisshow;
			lqcfgisshow = querywcService.getlqcfg(shebeibianhao);
			if (null == lqcfgisshow) {
				lqcfgisshow = new Liqingziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					lqcfgisshow.setGprsbianhao(shebeibianhao);
					lqcfgisshow.setLeixin("21");
				} else {
					lqcfgisshow.setGprsbianhao("all");
					lqcfgisshow.setLeixin("20");
				}
			}
			lqcfgisshow.setSjysb("1");
			lqcfgisshow.setSjg1("1");
			lqcfgisshow.setSjg2("1");
			lqcfgisshow.setSjg3("1");
			lqcfgisshow.setSjg4("1");
			lqcfgisshow.setSjg5("1");
			lqcfgisshow.setSjg6("0");
			lqcfgisshow.setSjg7("0");
			lqcfgisshow.setSjf1("1");
			lqcfgisshow.setSjf2("0");
			lqcfgisshow.setSjlq("1");
			lqcfgisshow.setSjtjj("0");
			querywcService.saveLqcfg(lqcfgisshow);
			lqisshow = querywcService.getlqcfgisShow(shebeibianhao);	
		}		
		wuchalist = new ArrayList<WuchaIsShowData>();
		wuchaselect = new Integer[17];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjysb());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg1());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg2());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg3());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg4());
		wuchalist.add(wd);
		
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg5());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg6());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg7());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjf1());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjf2());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjlq());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjtjj());
		wuchalist.add(wd);	
		
		setLqbhzField(lqfield);
		setLqbhzisShow(lqisshow);
		
		if (null != liqings && null != liqings.getDatas() && liqings.getDatas().size() > 0) {
			request.setAttribute("strXMLcailiaoyongliang", querywcService.getdapingLiqingCailiaoXml(liqings.getDatas(), lqbhzField, lqbhzisShow));
			request.setAttribute("strXMLWucha", querywcService.getdapingLqCailiaoWuchaXml(liqings.getDatas(), lqbhzField, lqbhzisShow));
		} else {
			request.setAttribute("strXMLcailiaoyongliang", "");
			request.setAttribute("strXMLWucha", "");
		}
        
        /*
         * 施工管理
         */
        shebeilistmap_tanpuwendu = new LinkedHashMap<String, String>();
		List<TopRealTemperaturedataView> toptmplist = sysService.limitTemperaturelist(request,biaoduan, xiangmubu);
		for (TopRealTemperaturedataView topView : toptmplist) {
			shebeilistmap_tanpuwendu.put(topView.getTmpno(), topView.getJianchen());
		}
		if(toptmplist!=null&&toptmplist.size()>0) 
		radio_name_tanpuwendu = toptmplist.get(0).getTmpno();
        //摊铺温度
        setXcQuerytmppgs(queryService.viewtmplist(radio_name_tanpuwendu,
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		if (null != xcQuerytmppgs.getDatas() && xcQuerytmppgs.getDatas().size()>0) {
			request.setAttribute("strXML_tanpuwendu", queryService.getDaPingTempXml(xcQuerytmppgs.getDatas()));
		} else {
			request.setAttribute("strXML_tanpuwendu", "");
		}
		//摊铺速度
		shebeilistmap_tanpusudu = new LinkedHashMap<String, String>();
		List<TopRealSpeeddata1View> toplist = sysService.limitTanpuSpeedlist(request, biaoduan, xiangmubu);
		for (TopRealSpeeddata1View topView : toplist) {
			shebeilistmap_tanpusudu.put(topView.getGpsno(), topView.getJianchen());
		}
		if(toplist!=null&&toplist.size()>0) 
			radio_name_tanpusudu = toplist.get(0).getGpsno();
		
		setXcQuerytmppgs_tanpusudu(queryService.viewtanpuspeedlist(radio_name_tanpusudu,
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		if (null != xcQuerytmppgs_tanpusudu.getDatas() && xcQuerytmppgs_tanpusudu.getDatas().size()>0) {
			request.setAttribute("strXML_tanpusudu", queryService.getDaPingTanpuSpeedXml(xcQuerytmppgs_tanpusudu.getDatas()));
		} else {
			request.setAttribute("strXML_tanpusudu", "");
		}
		
		//碾压温度
		shebeilistmap_nianyawendu = new LinkedHashMap<String, String>();
		List<TopRealTemperaturedata1View> topYalutmplist = sysService.limitYaluTemperaturelist(request,biaoduan, xiangmubu);
		for (TopRealTemperaturedata1View topView : topYalutmplist) {
			shebeilistmap_nianyawendu.put(topView.getTmpno(), topView.getJianchen());
		}
		if(topYalutmplist!=null&&topYalutmplist.size()>0) 
			radio_name_nianyawendu = topYalutmplist.get(0).getTmpno();
		
		setXcQueryspeedpgs_nianyawendu(queryService.viewNianyaTemplist(radio_name_nianyawendu,
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));	
		if (null != xcQueryspeedpgs_nianyawendu.getDatas() && xcQueryspeedpgs_nianyawendu.getDatas().size()>0) {
			request.setAttribute("strXML_nianyawendu", queryService.getDaPingnianyaTempXml(xcQueryspeedpgs_nianyawendu.getDatas()));
		} else {
			request.setAttribute("strXML_nianyawendu", "");
		}
		
		//碾压速度
		shebeilistmap_nianyasudu = new LinkedHashMap<String, String>();
		List<TopRealSpeeddataView> topnianyasudulist = sysService.limitSpeedlist(request, biaoduan, xiangmubu);
		for (TopRealSpeeddataView topView : topnianyasudulist) {
			shebeilistmap_nianyasudu.put(topView.getGpsno(), topView.getJianchen());
		}
		if(topnianyasudulist!=null&&topnianyasudulist.size()>0) 
			radio_name_nianyasudu = topnianyasudulist.get(0).getGpsno();
		
		setXcQueryspeedpgs_nianyasudu(queryService.viewspeedlist(radio_name_nianyasudu,
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));	
		if (null != xcQueryspeedpgs_nianyasudu.getDatas() && xcQueryspeedpgs_nianyasudu.getDatas().size()>0) {
			request.setAttribute("strXML_nianyasudu", queryService.getDaPingnianyaSpeedXml(xcQueryspeedpgs_nianyasudu.getDatas()));
		} else {
			request.setAttribute("strXML_nianyasudu", "");
		}
		
		//大气环境
		this.setEnvironmentpageMode(queryService.environmentView(shebeibianhao, startTime, endTime, biaoduan, 
				xiangmubu,StringUtil.getQueryFieldNameByRequest(request),StringUtil.getBiaoshiId(request),pageNo,maxPageItems));
		if (null != environmentpageMode.getDatas() && environmentpageMode.getDatas().size()>0) {
		    request.setAttribute("strXML_daqihuanjingwendu", dataService.getdapinghuanjingXml(request.getSession().getId(), shebeibianhao,environmentpageMode.getDatas()));  
		    request.setAttribute("strXML_daqihuanjingshidu", dataService.getdapinghuanjingshiduXml(request.getSession().getId(), shebeibianhao,environmentpageMode.getDatas()));  
		    request.setAttribute("strXML_daqihuanjingfengsu", dataService.getdapinghuanjingfengsuXml(request.getSession().getId(), shebeibianhao,environmentpageMode.getDatas()));  
		}else{
			request.setAttribute("strXML_daqihuanjingwendu","");
			request.setAttribute("strXML_daqihuanjingshidu","");
			request.setAttribute("strXML_daqihuanjingfengsu","");
		}
		
		//设备定位地图--直接在jsp页面上
		
		/*
		 * 产量统计
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		//日统计---注意这里只是默认
		if(initialization!=null&&initialization==0 ){
				setEndTime(sdf.format(day.getTime()));
		    	day.add(Calendar.DATE, -15);
		    	setStartTime(sdf.format(day.getTime()));	
		}
		setLqviews_day(queryService.lqzhfxlist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu, 
	    			4,StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request)));
    	
    	if (null != lqviews_day && lqviews_day.size() > 0) {
    		request.setAttribute("strXML_chanliangday", dataService.getdapinglqzhfxXml_day(lqviews_day, 4));
    	} else {
    		request.setAttribute("strXML_chanliangday","");
    	}
    	//周统计
    	day=Calendar.getInstance();
    	if(initialization!=null&&initialization==0 ){
			setEndTime(sdf.format(day.getTime()));
	    	day.add(Calendar.WEDNESDAY, -6);
	    	setStartTime(sdf.format(day.getTime()));		
	   }
    	setLqviews_week(queryService.lqzhfxlist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu, 
    			3,StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)));
	
	   if (null != lqviews_week && lqviews_week.size() > 0) {
		  request.setAttribute("strXML_chanliangweek", dataService.getdapinglqzhfxXml_week(lqviews_week, 3));
	   } else {
		  request.setAttribute("strXML_chanliangweek","");
	   }
    
	 //月统计
	   day=Calendar.getInstance();
	   if(initialization!=null&&initialization==0 ){
			setEndTime(sdf.format(day.getTime()));
	    	day.add(Calendar.MONTH, -6);
	    	setStartTime(sdf.format(day.getTime()));	
	   }
   	 setLqviews_month(queryService.lqzhfxlist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu, 
   			2,StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)));
	
	   if (null != lqviews_month && lqviews_month.size() > 0) {
		  request.setAttribute("strXML_chanliangmonth", dataService.getdapinglqzhfxXml_month(lqviews_month, 2));
	   } else {
		  request.setAttribute("strXML_chanliangmonth","");
	   }
	   
	   /*
		 * 统计分析
		 */
	   //施工进度
//	   List<Tanpujihuaxinxi> list=tpjhxxService.getTanpujihuaxinxiBybianduan(biaoduan);
//	   List<Tanpujihuaxinxi> yearList=tpjhxxService.getTanpujihuaYearBybianduan(biaoduan,"","");
//	   request.setAttribute("strXML_shigongjindu", getTPJHXML(bdlist,yearList,list,675,345));
	   sgjdpagemode=queryService.lqshejisclview(shebeibianhao, peifang, startTime, endTime, biaoduan, xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request),StringUtil.getBiaoshiId(request), pageNo, maxPageItems);
		
	   //报警
	   day=Calendar.getInstance();
	   if (initialization!=null&&initialization==0 ) {
			setEndTime(sdf.format(day.getTime()));
	    	day.add(Calendar.MONTH, -1);
	    	setStartTime(sdf.format(day.getTime())); 
			}
		this.setLqzdcfg(queryService.getlqcfgfield(shebeibianhao));
		if (null == request.getParameter("frommenu")) {
		   setSmstjlist(smsService.smstongji(startTime, endTime, biaoduan, xiangmubu, shebeibianhao,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),fzlx,jbsj));
		}
		
		//材料对比
		day=Calendar.getInstance();
		if (initialization!=null&&initialization==0) {
			setEndTime(sdf.format(day.getTime()));
	    	day.add(Calendar.MONTH, -1);
	    	setStartTime(sdf.format(day.getTime()));
			}
			if (null != clselect && clselect.length > 0) {
				Liqingziduancfg lqcfgisshow= querywcService.getlqcfg4(shebeibianhao);
				if (null == lqcfgisshow) {
					lqcfgisshow = new Liqingziduancfg();
					if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
						lqcfgisshow.setGprsbianhao(shebeibianhao);
						lqcfgisshow.setLeixin("41");
					} else {
						lqcfgisshow.setGprsbianhao("all");
						lqcfgisshow.setLeixin("40");
					}
				}
				
				List<Integer> selectlist = new ArrayList<Integer>();
				for (int h = 0; h < clselect.length; h++) {
					selectlist.add(clselect[h]);
				}
				if (selectlist.contains(1)) {
					lqcfgisshow.setSjg1("1");
				} else {
					lqcfgisshow.setSjg1("0");
				}
				if (selectlist.contains(2)) {
					lqcfgisshow.setSjg2("1");
				} else {
					lqcfgisshow.setSjg2("0");
				}
				if (selectlist.contains(3)) {
					lqcfgisshow.setSjg3("1");
				} else {
					lqcfgisshow.setSjg3("0");
				}
				if (selectlist.contains(4)) {
					lqcfgisshow.setSjg4("1");
				} else {
					lqcfgisshow.setSjg4("0");
				}
				if (selectlist.contains(5)) {
					lqcfgisshow.setSjg5("1");
				} else {
					lqcfgisshow.setSjg5("0");
				}
				if (selectlist.contains(6)) {
					lqcfgisshow.setSjg6("1");
				} else {
					lqcfgisshow.setSjg6("0");
				}
				if (selectlist.contains(7)) {
					lqcfgisshow.setSjg7("1");
				} else {
					lqcfgisshow.setSjg7("0");
				} 
				if (selectlist.contains(8)) {
					lqcfgisshow.setSjf1("1");
				} else {
					lqcfgisshow.setSjf1("0");
				}
				if (selectlist.contains(9)) {
					lqcfgisshow.setSjf2("1");
				} else {
					lqcfgisshow.setSjf2("0");
				}
				if (selectlist.contains(10)) {
					lqcfgisshow.setSjlq("1");
				} else {
					lqcfgisshow.setSjlq("0");
				}
				if (selectlist.contains(11)) {
					lqcfgisshow.setSjtjj("1");
				} else {
					lqcfgisshow.setSjtjj("0");
				}
				querywcService.saveLqcfg(lqcfgisshow);
			}
			
			lqfield = queryService.getlqcfgfield(shebeibianhao);
			lqisshow = queryService.getlqcfgisShow4(shebeibianhao);	
			if (null == lqisshow) {
				Liqingziduancfg lqcfgisshow;
				lqcfgisshow = querywcService.getlqcfg(shebeibianhao);
				if (null == lqcfgisshow) {
					lqcfgisshow = new Liqingziduancfg();
					if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
						lqcfgisshow.setGprsbianhao(shebeibianhao);
						lqcfgisshow.setLeixin("41");
					} else {
						lqcfgisshow.setGprsbianhao("all");
						lqcfgisshow.setLeixin("40");
					}
				}
				lqcfgisshow.setSjg1("1");
				lqcfgisshow.setSjg2("1");
				lqcfgisshow.setSjg3("1");
				lqcfgisshow.setSjg4("1");
				lqcfgisshow.setSjg5("1");
				lqcfgisshow.setSjg6("0");
				lqcfgisshow.setSjg7("0");
				lqcfgisshow.setSjf1("1");
				lqcfgisshow.setSjf2("0");
				lqcfgisshow.setSjlq("1");
				lqcfgisshow.setSjtjj("0");
				querywcService.saveLqcfg(lqcfgisshow);
				lqisshow = queryService.getlqcfgisShow(shebeibianhao);	
			}		
			cllist = new ArrayList<WuchaIsShowData>();
			clselect = new Integer[11];
		    i = 1;
		    wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 		 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjg1());
			cllist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjg2());
			cllist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjg3());
			cllist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjg4());
			cllist.add(wd);
			
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjg5());
			cllist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjg6());
			cllist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjg7());
			cllist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjf1());
			cllist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjf2());
			cllist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjlq());
			cllist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
				clselect[i-1] = i;			
			} 
			wd.setId(i);
			i++;
			wd.setName(lqfield.getSjtjj());
			cllist.add(wd);	
		
			setLiqingField(lqfield);
			setLiqingisShow(lqisshow);
			
			if (null == request.getParameter("frommenu")) {
			setLqviews(queryService.lqmateriallist(startTime, endTime, 
					testbhz,biaoduan,xiangmubu,StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request)));
			}
			setLqdanjia(queryService.calTotaljiage(lqviews, shebeibianhao));		
	    	if (null != lqviews) {
	    		request.setAttribute("strXML_cailiaoyonglianghesuan", dataService.getDapinglqmaterialhsXml(lqviews,liqingField, liqingisShow));
	    	} else {
	    		request.setAttribute("strXML_cailiaoyonglianghesuan","");
	    	}
			setLqbhzField(lqfield);
			
		return SUCCESS; 
	}
	
	//获取摊铺计划横道图XML
	public String getTPJHXML(List<Biaoduanxinxi> bdlist,List<Tanpujihuaxinxi> yearList,List<Tanpujihuaxinxi> list,int width,int height){
		String [] topMenu1=new String[yearList.size()];
		String [] topMenu2=new String[2];
		if(yearList.size()>1){
			int startTime=0;
			int endTime=0;
			int j=0;
			for(Tanpujihuaxinxi tpObj:yearList){
				if(j==0){//第一个
					topMenu1[j]=tpObj.getJihuastarttime()+"&"+tpObj.getJihuashengchanliang()+"-12-31&"+tpObj.getJihuashengchanliang();
					startTime=new Integer(tpObj.getJihuashengchanliang());//所有数据的开始年份
					topMenu2[0]=tpObj.getJihuastarttime();//所有数据的开始日期
				}
				
				if(j==yearList.size()-1){//最后一个
					topMenu1[j]=tpObj.getJihuatanpulicheng()+"-01-01&"+tpObj.getJihuaendtime()+"&"+tpObj.getJihuatanpulicheng();
					endTime=new Integer(tpObj.getJihuatanpulicheng());//所有数据的结束年份
					topMenu2[1]=tpObj.getJihuaendtime();//所有数据的结束日期
				}
				
				j++;
			}
			
			if((startTime+=1)!=endTime){
				int k=1;
				topMenu1[k]=startTime+"-01-01&"+startTime+"-12-31&"+startTime;
				k++;
			}
		}
		
		if(yearList.size()==1){
			Tanpujihuaxinxi tpObj=yearList.get(0);
			topMenu1[0]=tpObj.getJihuastarttime()+"&"+tpObj.getJihuaendtime()+"&"+tpObj.getJihuashengchanliang();
			
			topMenu2[0]=tpObj.getJihuastarttime();
			topMenu2[1]=tpObj.getJihuaendtime();
			
		}
		
		//String [] topMenu1={"2013-01-03&2013-04-20&2013"};
		//String [] topMenu2={"2013-01-03","2013-04-20"};
		String [] leftMenu=new String[bdlist.size()+1];
		int i=0;
		leftMenu[i]="标  段";
		for (Biaoduanxinxi bd : bdlist) {
			i++;
			leftMenu[i]=bd.getId()+"&"+bd.getBiaoduanminchen();
		}
		
		/*
		for(int u=0;u<topMenu1.length;u++){
			System.out.println("topMenu"+u+":"+topMenu1[u]);
		}
		System.out.println(topMenu2[0]+"*****"+topMenu2[1]);
		*/
		return dataService.TanpujihuaXML(topMenu1, topMenu2, leftMenu, list,width,height);
		// request.setAttribute("strXML", getdataService.TanpujihuaXML(topMenu1, topMenu2, leftMenu, list));
	}

	@Action("darwpic")
	//传入设备编号重新画图
	public String darwpic(){
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
        String bd = request.getParameter("biaoduan"); 
        if (StringUtil.Null2Blank(bd).length() > 0) {
        	try {
        		biaoduan = Integer.valueOf(bd);
			} catch (Exception e) {
			}
		}
		setUsertype(StringUtil.getUserType(request));
		setBsid(StringUtil.getBiaoshiId(request));
		if (1 == usertype) {
			listmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.biaoduanlist();
			for (Biaoduanxinxi bdxx : bdlist) {
				listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
			}
			if (null == biaoduan && bdlist.size()>0) {
				setBiaoduan(bdlist.get(0).getId());
			}
			setBsid(biaoduan);
		}
		
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(20);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		
        String mothed =request.getParameter("mothed");
		String strxml ="";
		if(mothed!=null){
			if(mothed.equalsIgnoreCase("radio_name_tanpuwendu")){
				//摊铺温度
		        setXcQuerytmppgs(queryService.viewtmplist(shebeibianhao,
						startTime, endTime, biaoduan, xiangmubu, 
						StringUtil.getQueryFieldNameByRequest(request), 
						StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
				if (null != xcQuerytmppgs.getDatas() && xcQuerytmppgs.getDatas().size()>0) {
					strxml = queryService.getDaPingTempXml(xcQuerytmppgs.getDatas());
				} else {
					strxml = "";
				}
			}else if(mothed.equalsIgnoreCase("radio_name_tanpusudu")){
				//摊铺速度
				setXcQuerytmppgs_tanpusudu(queryService.viewtanpuspeedlist(shebeibianhao,
						startTime, endTime, biaoduan, xiangmubu, 
						StringUtil.getQueryFieldNameByRequest(request), 
						StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
				if (null != xcQuerytmppgs_tanpusudu.getDatas() && xcQuerytmppgs_tanpusudu.getDatas().size()>0) {
					strxml = queryService.getDaPingTanpuSpeedXml(xcQuerytmppgs_tanpusudu.getDatas());
				} else {
					strxml = "";
				}
			} else if(mothed.equalsIgnoreCase("radio_name_nianyawendu")){
				//碾压温度
				setXcQueryspeedpgs_nianyawendu(queryService.viewNianyaTemplist(shebeibianhao,
						startTime, endTime, biaoduan, xiangmubu, 
						StringUtil.getQueryFieldNameByRequest(request), 
						StringUtil.getBiaoshiId(request),pageNo, maxPageItems));	
				if (null != xcQueryspeedpgs_nianyawendu.getDatas() && xcQueryspeedpgs_nianyawendu.getDatas().size()>0) {
					strxml = queryService.getDaPingnianyaTempXml(xcQueryspeedpgs_nianyawendu.getDatas());
				} else {
					strxml = "";
				}
			} else if(mothed.equalsIgnoreCase("radio_name_nianyasudu")){
				//碾压速度
				setXcQueryspeedpgs_nianyasudu(queryService.viewspeedlist(shebeibianhao,
						startTime, endTime, biaoduan, xiangmubu, 
						StringUtil.getQueryFieldNameByRequest(request), 
						StringUtil.getBiaoshiId(request),pageNo, maxPageItems));	
				if (null != xcQueryspeedpgs_nianyasudu.getDatas() && xcQueryspeedpgs_nianyasudu.getDatas().size()>0) {
					strxml =  queryService.getDaPingnianyaSpeedXml(xcQueryspeedpgs_nianyasudu.getDatas());
				} else {
					strxml = "";
				}
			}
		}
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
			try {
				out = response.getWriter();
				out.print(strxml.toString());
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	  }
	
	
	@Action("screenwuchafenxi")
	public String screenwuchafenxi() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		String shebeibianhao = request.getParameter("shebeibianhao");

		setShebeibianhao(shebeibianhao);
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}		
        //误差查询
		String divdisplay = request.getParameter("divdisplay");
		
        if (null != wuchaselect && wuchaselect.length > 0) {
			Liqingziduancfg lqcfgisshow;
			lqcfgisshow = querywcService.getlqcfg(shebeibianhao);
			if (null == lqcfgisshow) {
				lqcfgisshow = new Liqingziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					lqcfgisshow.setGprsbianhao(shebeibianhao);
					lqcfgisshow.setLeixin("21");
				} else {
					lqcfgisshow.setGprsbianhao("all");
					lqcfgisshow.setLeixin("20");
				}
			}
			
			List<Integer> selectlist = new ArrayList<Integer>();
			for (int h = 0; h < wuchaselect.length; h++) {
				selectlist.add(wuchaselect[h]);
			}
			if (selectlist.contains(1)) {
				lqcfgisshow.setSjysb("1");
			} else {
				lqcfgisshow.setSjysb("0");
			}
			if (selectlist.contains(2)) {
				lqcfgisshow.setSjg1("1");
			} else {
				lqcfgisshow.setSjg1("0");
			}
			if (selectlist.contains(3)) {
				lqcfgisshow.setSjg2("1");
			} else {
				lqcfgisshow.setSjg2("0");
			}
			if (selectlist.contains(4)) {
				lqcfgisshow.setSjg3("1");
			} else {
				lqcfgisshow.setSjg3("0");
			}
			if (selectlist.contains(5)) {
				lqcfgisshow.setSjg4("1");
			} else {
				lqcfgisshow.setSjg4("0");
			}
			if (selectlist.contains(6)) {
				lqcfgisshow.setSjg5("1");
			} else {
				lqcfgisshow.setSjg5("0");
			}
			if (selectlist.contains(7)) {
				lqcfgisshow.setSjg6("1");
			} else {
				lqcfgisshow.setSjg6("0");
			}
			if (selectlist.contains(8)) {
				lqcfgisshow.setSjg7("1");
			} else {
				lqcfgisshow.setSjg7("0");
			} 
			if (selectlist.contains(9)) {
				lqcfgisshow.setSjf1("1");
			} else {
				lqcfgisshow.setSjf1("0");
			}
			if (selectlist.contains(10)) {
				lqcfgisshow.setSjf2("1");
			} else {
				lqcfgisshow.setSjf2("0");
			}
			if (selectlist.contains(11)) {
				lqcfgisshow.setSjlq("1");
			} else {
				lqcfgisshow.setSjlq("0");
			}
			if (selectlist.contains(12)) {
				lqcfgisshow.setSjtjj("1");
			} else {
				lqcfgisshow.setSjtjj("0");
			}			
			querywcService.saveLqcfg(lqcfgisshow);
		}


		String startTimeOne = request.getParameter("startTime");
		String endTimeOne = request.getParameter("endTime");
		setStartTime(startTimeOne);
		setEndTime(endTimeOne);
		setLiqings(querywcService.lqviewwuchalist(shebeibianhao, 
				startTimeOne, endTimeOne, biaoduan,xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request), pageNo, maxPageItems,peifan));
		LiqingziduancfgView lqfield = querywcService.getlqcfgfield(shebeibianhao);
		LiqingziduancfgView lqisshow = querywcService.getlqcfgisShow(shebeibianhao);		
		if (null == lqisshow) {
			Liqingziduancfg lqcfgisshow;
			lqcfgisshow = querywcService.getlqcfg(shebeibianhao);
			if (null == lqcfgisshow) {
				lqcfgisshow = new Liqingziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					lqcfgisshow.setGprsbianhao(shebeibianhao);
					lqcfgisshow.setLeixin("21");
				} else {
					lqcfgisshow.setGprsbianhao("all");
					lqcfgisshow.setLeixin("20");
				}
			}
			lqcfgisshow.setSjysb("1");
			lqcfgisshow.setSjg1("1");
			lqcfgisshow.setSjg2("1");
			lqcfgisshow.setSjg3("1");
			lqcfgisshow.setSjg4("1");
			lqcfgisshow.setSjg5("1");
			lqcfgisshow.setSjg6("0");
			lqcfgisshow.setSjg7("0");
			lqcfgisshow.setSjf1("1");
			lqcfgisshow.setSjf2("0");
			lqcfgisshow.setSjlq("1");
			lqcfgisshow.setSjtjj("0");
			querywcService.saveLqcfg(lqcfgisshow);
			lqisshow = querywcService.getlqcfgisShow(shebeibianhao);	
		}		
		wuchalist = new ArrayList<WuchaIsShowData>();
		wuchaselect = new Integer[17];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjysb());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg1());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg2());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg3());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg4());
		wuchalist.add(wd);
		
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg5());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg6());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg7());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjf1());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjf2());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjlq());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjtjj());
		wuchalist.add(wd);	
		
		setLqbhzField(lqfield);
		setLqbhzisShow(lqisshow);
		String strxml ="";
		if (null != liqings && null != liqings.getDatas() && liqings.getDatas().size() > 0) {
			if (divdisplay.equals("div_wuchafenxicaiiaoyongliang"))
			{
			strxml = querywcService.getdapingLiqingCailiaoXml(liqings.getDatas(), lqbhzField, lqbhzisShow);
			}else{
			strxml = querywcService.getdapingLqCailiaoWuchaXml(liqings.getDatas(), lqbhzField, lqbhzisShow);
			}
		}		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out;
			try {
				out = response.getWriter();
				out.print(strxml.toString());
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null;
	}
	
}
