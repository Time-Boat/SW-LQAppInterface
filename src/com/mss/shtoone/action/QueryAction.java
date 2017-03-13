package com.mss.shtoone.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.HunnintuPageMode;
import com.mss.shtoone.domain.HunnintuView;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.Liqingxixxjieguo;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.Muser;
import com.mss.shtoone.domain.ShaifenjieguoView;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.Shuiwenxixxjieguo;
import com.mss.shtoone.domain.ShuiwenxixxlilunView;
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.Smsinfo;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.TophunnintuView;
import com.mss.shtoone.domain.UserInfo;
import com.mss.shtoone.domain.UserlogView;
import com.mss.shtoone.domain.WuchaIsShowData;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.Xiangxixx;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.ShaifenshiyanService;
import com.mss.shtoone.service.ShuiwenxixxlilunService;
import com.mss.shtoone.service.SmsinfoService;
import com.mss.shtoone.service.SwViewService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.service.UserService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class QueryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2422818359966271113L;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SwViewService swViewService;
	
	@Autowired
	private ShaifenshiyanService shaifenService;
	
	@Autowired
	private SmsinfoService smsInfoService;
	
	@Autowired
	private GetdataService getDataService;
	
	@Autowired
	private ShuiwenxixxlilunService swllService;

	private HunnintuPageMode hunnintus;
	private GenericPageMode logshowpagemode;
	private GenericPageMode lqsjpageMode;
	private HntbhzziduancfgView hntbhzisShow;
	private HntbhzziduancfgView hntbhzField;
	private Xiangxixx xiangxixx;
	private Shuiwenxixxjieguo swxixxjieguo;
	private Liqingxixxjieguo lqxixxjieguo;
	private String startTime;
	private String endTime;
	private String jiaozhubuwei;
	private Integer biaoduan;
	private Integer xiangmubu;
	private Integer maxPageItems;
	private Integer pageNo;
	private LiqingziduancfgView liqingisShow;
	private LiqingziduancfgView liqingField;
	private String username;
	private String danwei;
	private String zhiwei;
	private Integer usertype;
	private List<UserlogView> userloglist;
	private ShuiwenziduancfgView swisShow;	
    private ShuiwenziduancfgView swField;
	private String swid;
	private String lqid;
	private ShuiwenxixxView shuiwenxx;
	private GenericPageMode shaifenjieguopgs;
	private GenericPageMode swcls;
	private Integer[] clselect;
	private List<WuchaIsShowData> cllist;
	private Map<String, String> listmap;
	private Map<String, String> gdhlistmap;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<String, String> bhzlistmap;
	private Map<Integer, String> usertypelistmap;
	private Map<String,String> llbuweilistMap;
	private String shebeibianhao;
	private String gongdanhao;
	private String bhzname;
	private GenericPageMode swmanualphb;
	private Map<String, String> jzbwlistmap;
	public String excelName;
	private ShuiwenmanualphbView swmanualphbxixx;
	private LiqingmanualphbView lqmanualphbxixx;
	private Integer[] wuchaselect;
	private List<WuchaIsShowData> wuchalist;
	private List<Smsinfo> smsInfoList;
	private Integer bsid;
	private String method;
	private String llbuwei;
	
	
	public String getLlbuwei() {
		return llbuwei;
	}

	public void setLlbuwei(String llbuwei) {
		this.llbuwei = llbuwei;
	}

	public Map<String, String> getLlbuweilistMap() {
		return llbuweilistMap;
	}

	public void setLlbuweilistMap(Map<String, String> llbuweilistMap) {
		this.llbuweilistMap = llbuweilistMap;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getBsid() {
		return bsid;
	}

	public void setBsid(Integer bsid) {
		this.bsid = bsid;
	}

	public List<Smsinfo> getSmsInfoList() {
		return smsInfoList;
	}

	public void setSmsInfoList(List<Smsinfo> smsInfoList) {
		this.smsInfoList = smsInfoList;
	}

	public Shuiwenxixxjieguo getSwxixxjieguo() {
		return swxixxjieguo;
	}

	public void setSwxixxjieguo(Shuiwenxixxjieguo swxixxjieguo) {
		this.swxixxjieguo = swxixxjieguo;
	}

	public ShuiwenmanualphbView getSwmanualphbxixx() {
		return swmanualphbxixx;
	}

	public void setSwmanualphbxixx(ShuiwenmanualphbView swmanualphbxixx) {
		this.swmanualphbxixx = swmanualphbxixx;
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

	public GenericPageMode getSwcls() {
		return swcls;
	}

	public void setSwcls(GenericPageMode swcls) {
		this.swcls = swcls;
	}
	public GenericPageMode getShaifenjieguopgs() {
		return shaifenjieguopgs;
	}

	public void setShaifenjieguopgs(GenericPageMode shaifenjieguopgs) {
		this.shaifenjieguopgs = shaifenjieguopgs;
	}

	public String getSwid() {
		return swid;
	}

	public void setSwid(String swid) {
		this.swid = swid;
	}

	public ShuiwenxixxView getShuiwenxx() {
		return shuiwenxx;
	}

	public void setShuiwenxx(ShuiwenxixxView shuiwenxx) {
		this.shuiwenxx = shuiwenxx;
	}

	public ShuiwenziduancfgView getSwisShow() {
		return swisShow;
	}

	public void setSwisShow(ShuiwenziduancfgView swisShow) {
		this.swisShow = swisShow;
	}

	public ShuiwenziduancfgView getSwField() {
		return swField;
	}

	public void setSwField(ShuiwenziduancfgView swField) {
		this.swField = swField;
	}

	public List<UserlogView> getUserloglist() {
		return userloglist;
	}

	public void setUserloglist(List<UserlogView> userloglist) {
		this.userloglist = userloglist;
	}

	public GenericPageMode getLogshowpagemode() {
		return logshowpagemode;
	}

	public void setLogshowpagemode(GenericPageMode logshowpagemode) {
		this.logshowpagemode = logshowpagemode;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getZhiwei() {
		return zhiwei;
	}

	public void setZhiwei(String zhiwei) {
		this.zhiwei = zhiwei;
	}

	public LiqingziduancfgView getLiqingisShow() {
		return liqingisShow;
	}

	public void setLiqingisShow(LiqingziduancfgView liqingisShow) {
		this.liqingisShow = liqingisShow;
	}

	public LiqingziduancfgView getLiqingField() {
		return liqingField;
	}

	public void setLiqingField(LiqingziduancfgView liqingField) {
		this.liqingField = liqingField;
	}

	public GenericPageMode getLqsjpageMode() {
		return lqsjpageMode;
	}

	public void setLqsjpageMode(GenericPageMode lqsjpageMode) {
		this.lqsjpageMode = lqsjpageMode;
	}

	public int getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}

	public String getJiaozhubuwei() {
		return jiaozhubuwei;
	}

	public void setJiaozhubuwei(String jiaozhubuwei) {
		this.jiaozhubuwei = jiaozhubuwei;
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

	public Xiangxixx getXiangxixx() {
		return xiangxixx;
	}

	public void setXiangxixx(Xiangxixx xiangxixx) {
		this.xiangxixx = xiangxixx;
	}

	public GenericPageMode getSwmanualphb() {
		return swmanualphb;
	}

	public void setSwmanualphb(GenericPageMode swmanualphb) {
		this.swmanualphb = swmanualphb;
	}

	public String getGongdanhao() {
		return gongdanhao;
	}

	public void setGongdanhao(String gongdanhao) {
		this.gongdanhao = gongdanhao;
	}
	
	public String getExcelName() {
		return excelName;
	}
	
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	public Map<String, String> getBhzlistmap() {
		return bhzlistmap;
	}

	public void setBhzlistmap(Map<String, String> bhzlistmap) {
		this.bhzlistmap = bhzlistmap;
	}

	public Map<String, String> getJzbwlistmap() {
		return jzbwlistmap;
	}

	public void setJzbwlistmap(Map<String, String> jzbwlistmap) {
		this.jzbwlistmap = jzbwlistmap;
	}

	public Map<String, String> getGdhlistmap() {
		return gdhlistmap;
	}

	public void setGdhlistmap(Map<String, String> gdhlistmap) {
		this.gdhlistmap = gdhlistmap;
	}

	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	public HntbhzziduancfgView getHntbhzisShow() {
		return hntbhzisShow;
	}

	public void setHntbhzisShow(HntbhzziduancfgView hntbhzisShow) {
		this.hntbhzisShow = hntbhzisShow;
	}

	public HntbhzziduancfgView getHntbhzField() {
		return hntbhzField;
	}

	public void setHntbhzField(HntbhzziduancfgView hntbhzField) {
		this.hntbhzField = hntbhzField;
	}

	public HunnintuPageMode getHunnintus() {
		return hunnintus;
	}

	public void setHunnintus(HunnintuPageMode hunnintus) {
		this.hunnintus = hunnintus;
	}

	public Map<Integer, String> getUsertypelistmap() {
		return usertypelistmap;
	}

	public void setUsertypelistmap(Map<Integer, String> usertypelistmap) {
		this.usertypelistmap = usertypelistmap;
	}

	@Action("hntsjlist")
	public String hntsjlist(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		setShebeibianhao(request.getParameter("shebeibianhao"));
		setJiaozhubuwei(request.getParameter("jiaozhubuwei"));
		setGongdanhao(request.getParameter("gongdanhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		setHunnintus(queryService.viewlist(shebeibianhao, gongdanhao,
				startTime, endTime, jiaozhubuwei, biaoduan, xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		if (null != hunnintus && null != hunnintus.getDatas() && hunnintus.getDatas().size() > 0) {
			request.setAttribute("strXML", queryService.getHntBanheshijianXml(hunnintus.getDatas()));
		} else {
			request.setAttribute("strXML", "");
		}
		setHntbhzField(queryService.gethntcfgfield(shebeibianhao));
		setHntbhzisShow(queryService.gethntcfgisShow(shebeibianhao));
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		listmap = new LinkedHashMap<String, String>();
		List<TophunnintuView> toplist = sysService.limithntlist(request, biaoduan, xiangmubu);
		for (TophunnintuView tophunnintuView : toplist) {
			listmap.put(tophunnintuView.getShebeibianhao(), tophunnintuView
					.getBanhezhanminchen());
		}
		gdhlistmap = new LinkedHashMap<String, String>();
		List gdhtoplist = sysService.limitgetListbyField(request, "gongchengmingcheng", biaoduan, xiangmubu, shebeibianhao);
		for (int i = 0; i < gdhtoplist.size(); i++) {
			gdhlistmap.put(String.valueOf(gdhtoplist.get(i)), String
					.valueOf(gdhtoplist.get(i)));
		}

		jzbwlistmap = new LinkedHashMap<String, String>();
		List jzbwtoplist = sysService.limitgetListbyField(request, "jiaozuobuwei", biaoduan, xiangmubu, shebeibianhao);
		for (int i = 0; i < jzbwtoplist.size(); i++) {
			jzbwlistmap.put(String.valueOf(jzbwtoplist.get(i)), String
					.valueOf(jzbwtoplist.get(i)));
		}
		return SUCCESS;
	}

	@Action("hntcllist")
	public String hntcllist() {
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

		setShebeibianhao(request.getParameter("shebeibianhao"));
		setJiaozhubuwei(request.getParameter("jiaozhubuwei"));
		setGongdanhao(request.getParameter("gongdanhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		setHunnintus(queryService.viewlist(shebeibianhao, gongdanhao,
				startTime, endTime, jiaozhubuwei, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		HntbhzziduancfgView hbfield = queryService.gethntcfgfield(shebeibianhao);
		HntbhzziduancfgView hbisshow = queryService.gethntcfgisShow(shebeibianhao);		
		setHntbhzField(hbfield);
		setHntbhzisShow(hbisshow);
		
		if (null != hunnintus && null != hunnintus.getDatas() && hunnintus.getDatas().size() > 0) {
			request.setAttribute("strXML", queryService.getHntCailiaoXml(hunnintus.getDatas(), hntbhzField, hntbhzisShow));
		} else {
			request.setAttribute("strXML", "");
		}

		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		listmap = new LinkedHashMap<String, String>();
		List<TophunnintuView> toplist = sysService.limithntlist(request, biaoduan, xiangmubu);
		for (TophunnintuView tophunnintuView : toplist) {
			listmap.put(tophunnintuView.getShebeibianhao(), tophunnintuView
					.getBanhezhanminchen());
		}
		gdhlistmap = new LinkedHashMap<String, String>();
		List gdhtoplist = sysService.limitgetListbyField(request, "gongchengmingcheng", biaoduan, xiangmubu, shebeibianhao);
		for (int i = 0; i < gdhtoplist.size(); i++) {
			gdhlistmap.put(String.valueOf(gdhtoplist.get(i)), String
					.valueOf(gdhtoplist.get(i)));
		}

		jzbwlistmap = new LinkedHashMap<String, String>();
		List jzbwtoplist = sysService.limitgetListbyField(request, "jiaozuobuwei", biaoduan, xiangmubu, shebeibianhao);
		for (int i = 0; i < jzbwtoplist.size(); i++) {
			jzbwlistmap.put(String.valueOf(jzbwtoplist.get(i)), String
					.valueOf(jzbwtoplist.get(i)));
		}
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& null != hbfield && null != hbisshow){
				List<String> dataList=new ArrayList<String>();
				List<String> headerList = new ArrayList<String>();
				
				if (StringUtil.Null2Blank(hbisshow.getGongdanhao()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getGongdanhao()));
				}
				if (StringUtil.Null2Blank(hbisshow.getGongchengmingcheng()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getGongchengmingcheng()));
				}
				if (StringUtil.Null2Blank(hbisshow.getJiaozuobuwei()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getJiaozuobuwei()));
				}
				if (StringUtil.Null2Blank(hbisshow.getQiangdudengji()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getQiangdudengji()));
				}
				if (StringUtil.Null2Blank(hbisshow.getChuliaoshijian()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getChuliaoshijian()));
				}
				if (StringUtil.Null2Blank(hbisshow.getBaocunshijian()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getBaocunshijian()));
				}
				if (StringUtil.Null2Blank(hbisshow.getPeifanghao()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getPeifanghao()));
				}
				if (StringUtil.Null2Blank(hbisshow.getGujifangshu()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getGujifangshu()));
				}
				if (StringUtil.Null2Blank(hbisshow.getShui1_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getShui1_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getShui1_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getShui2_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getShui2_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getShui2_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getShuini1_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getShuini1_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getShuini1_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getShuini2_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getShuini2_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getShuini2_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getSha1_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getSha1_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getSha1_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getShi1_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getShi1_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getShi1_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getShi2_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getShi2_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getShi2_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getSha2_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getSha2_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getSha2_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getGuliao5_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getGuliao5_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getGuliao5_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getKuangfen3_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getKuangfen3_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getKuangfen3_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getFeimeihui4_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getFeimeihui4_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getFeimeihui4_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getFenliao5_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getFenliao5_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getFenliao5_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getFenliao6_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getFenliao6_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getFenliao6_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getWaijiaji1_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getWaijiaji1_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getWaijiaji1_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getWaijiaji2_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getWaijiaji2_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getWaijiaji2_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getWaijiaji3_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getWaijiaji3_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getWaijiaji3_lilunzhi()));
				}
				
				if (StringUtil.Null2Blank(hbisshow.getWaijiaji4_shijizhi()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(hbfield.getWaijiaji4_shijizhi()));
					headerList.add(StringUtil.Null2Blank(hbfield.getWaijiaji4_lilunzhi()));
				}
							
				
				
				for (HunnintuView  hnt: hunnintus.getDatas()) {
					StringBuilder databuilder = new StringBuilder();
					if (StringUtil.Null2Blank(hbisshow.getGongdanhao()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getGongdanhao()));
						databuilder.append("&^&");			
					}
					if (StringUtil.Null2Blank(hbisshow.getGongchengmingcheng()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getGongchengmingcheng()));
						databuilder.append("&^&");			
					}
					if (StringUtil.Null2Blank(hbisshow.getJiaozuobuwei()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getJiaozuobuwei()));
						databuilder.append("&^&");			
					}
					if (StringUtil.Null2Blank(hbisshow.getQiangdudengji()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getQiangdudengji()));
						databuilder.append("&^&");			
					}
					if (StringUtil.Null2Blank(hbisshow.getChuliaoshijian()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getChuliaoshijian()));
						databuilder.append("&^&");			
					}
					if (StringUtil.Null2Blank(hbisshow.getBaocunshijian()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getBaocunshijian()));
						databuilder.append("&^&");			
					}
					if (StringUtil.Null2Blank(hbisshow.getPeifanghao()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getPeifanghao()));
						databuilder.append("&^&");	
					}
					if (StringUtil.Null2Blank(hbisshow.getGujifangshu()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getGujifangshu()));
						databuilder.append("&^&");
					}
					if (StringUtil.Null2Blank(hbisshow.getShui1_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getShui1_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getShui1_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getShui2_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getShui2_shijizhi()));
						databuilder.append("&^&");		
						databuilder.append(StringUtil.Null2Blank(hnt.getShui2_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getShuini1_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getShuini1_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getShuini1_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getShuini2_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getShuini2_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getShuini2_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getSha1_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getSha1_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getSha1_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getShi1_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getShi1_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getShi1_lilunzhi()));
						databuilder.append("&^&");	
					}
					
					if (StringUtil.Null2Blank(hbisshow.getShi2_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getShi2_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getShi2_lilunzhi()));
						databuilder.append("&^&");	
					}
					
					if (StringUtil.Null2Blank(hbisshow.getSha2_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getSha2_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getSha2_lilunzhi()));
						databuilder.append("&^&");	
					}
					
					if (StringUtil.Null2Blank(hbisshow.getGuliao5_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getGuliao5_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getGuliao5_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getKuangfen3_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getKuangfen3_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getKuangfen3_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getFeimeihui4_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getFeimeihui4_shijizhi()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(hnt.getFeimeihui4_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getFenliao5_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getFenliao5_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getFenliao5_lilunzhi()));
						databuilder.append("&^&");	
					}
					
					if (StringUtil.Null2Blank(hbisshow.getFenliao6_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getFenliao6_shijizhi()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(hnt.getFenliao6_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getWaijiaji1_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji1_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji1_lilunzhi()));
						databuilder.append("&^&");
					}
					
					if (StringUtil.Null2Blank(hbisshow.getWaijiaji2_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji2_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji2_lilunzhi()));
						databuilder.append("&^&");	
					}
					
					if (StringUtil.Null2Blank(hbisshow.getWaijiaji3_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji3_shijizhi()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji3_lilunzhi()));
						databuilder.append("&^&");	
					}
					
					if (StringUtil.Null2Blank(hbisshow.getWaijiaji4_shijizhi()).equalsIgnoreCase("1")) {
						databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji4_shijizhi()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji4_lilunzhi()));
					}
					dataList.add(databuilder.toString());
				}
				String path=request.getSession().getServletContext().getRealPath("/");
				String excelName="excel/"+System.currentTimeMillis()+".xls";
				setExcelName(excelName);
				File file=new File(path+"excel");
				if(!file.exists()){
					file.mkdir();
				}
				
				//String [] header=new String[]{"拌和站名称","工程部位","浇筑部位","保存时间","水","粉料1","粉料2","粉料3","粉料4","骨料1","骨料2","骨料3","骨料4","外加剂1"};
				String[] header = new String[headerList.size()];
				int j = 0;
				for (Iterator iterator = headerList.iterator(); iterator
						.hasNext();) {
					header[j] = (String) iterator.next();
					j++;
				}
				HntExcelUtil.importListExcel(path+"excel/台帐.xls", path+excelName, StringUtil.Null2Blank(bhzname), header, dataList);
				//HntExcelUtil.importListExcel(path, path+excelName, hbfield.getBanhezhanminchen(), header, dataList);
				try {
					response.reset();
					response.setContentType("bin");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + excelName + "\"");
					java.io.FileInputStream in = new java.io.FileInputStream(path
							+ excelName);
					// response.flushBuffer();
					PrintWriter out = response.getWriter();
					int i;
					while ((i = in.read()) != -1) {
						out.write(i);
					}
					in.close();
			        out.flush();
			        out.close();
				} catch (Exception e) {
				}
		}
		
		return SUCCESS;
	}

	@Action("hntclbdchange")
	public String hntclbdchange() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        	List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, bd);
	        	StringBuilder outstr = new StringBuilder();
	    		for (Xiangmubuxinxi xmb : xmblist) {
	    			outstr.append(xmb.getId());
	    			outstr.append(",");
	    			outstr.append(xmb.getXiangmubuminchen());
	    			outstr.append("|");	    			
	    		}
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("hntclchangebhz")
	public String hntclchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        List<TophunnintuView> toplist = sysService.limithntlist(request, bd, null);			
	        	StringBuilder outstr = new StringBuilder();
	    		for (TophunnintuView hnt : toplist) {
	    			outstr.append(hnt.getShebeibianhao());
	    			outstr.append(",");
	    			outstr.append(hnt.getBanhezhanminchen());
	    			outstr.append("|");	    			
	    		}
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("userchangebhz")
	public String userchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        List<TopLiqingView> toplist = sysService.limitlqlist(request, bd, null);			
	        	StringBuilder outstr = new StringBuilder();
	    		for (TopLiqingView hnt : toplist) {
	    			outstr.append(hnt.getShebeibianhao());
	    			outstr.append(",");
	    			outstr.append(hnt.getBanhezhanminchen());
	    			outstr.append("|");	    			
	    		}
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("hntclxmbchangebhz")
	public String hntclxmbchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String xmbmc = request.getParameter("xmbmc");
			Integer xmb = null;
	        if(StringUtil.Null2Blank(xmbmc).length() > 0) {
	        	xmb = Integer.valueOf(xmbmc);
	        }
	        List<TophunnintuView> toplist = sysService.limithntlist(request, null, xmb);			
	        	StringBuilder outstr = new StringBuilder();
	    		for (TophunnintuView hnt : toplist) {
	    			outstr.append(hnt.getShebeibianhao());
	    			outstr.append(",");
	    			outstr.append(hnt.getBanhezhanminchen());
	    			outstr.append("|");	    			
	    		}
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("userxmbchangebhz")
	public String userxmbchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String xmbmc = request.getParameter("xmbmc");
			Integer xmb = null;
	        if(StringUtil.Null2Blank(xmbmc).length() > 0) {
	        	xmb = Integer.valueOf(xmbmc);
	        }
	        List<TopLiqingView> toplist = sysService.limitlqlist(request, null, xmb);			
	        	StringBuilder outstr = new StringBuilder();
	    		for (TopLiqingView hnt : toplist) {
	    			outstr.append(hnt.getShebeibianhao());
	    			outstr.append(",");
	    			outstr.append(hnt.getBanhezhanminchen());
	    			outstr.append("|");	    			
	    		}
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("hntclchangegcmc")
	public String hntclchangegcmc() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        List gdhtoplist = sysService.limitgetListbyField(request, "gongchengmingcheng", bd, null, null);
	        StringBuilder outstr = new StringBuilder();
	        for (int i = 0; i < gdhtoplist.size(); i++) {				
				outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append(",");
    			outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append("|");	 
			}	
	    		
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("hntclxmbchangegcmc")
	public String hntclxmbchangegcmc() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String xmbmc = request.getParameter("xmbmc");
			Integer xmb = null;
	        if(StringUtil.Null2Blank(xmbmc).length() > 0) {
	        	xmb = Integer.valueOf(xmbmc);
	        }
	        List gdhtoplist = sysService.limitgetListbyField(request, "gongchengmingcheng", null, xmb, null);
	        StringBuilder outstr = new StringBuilder();
	        for (int i = 0; i < gdhtoplist.size(); i++) {				
				outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append(",");
    			outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append("|");	 
			}	
	    		
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("hntclbhzchangegcmc")
	public String hntclbhzchangegcmc() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String bhzmc = request.getParameter("bhzmc");			
	        List gdhtoplist = sysService.limitgetListbyField(request, "gongchengmingcheng", null, null, bhzmc);
	        StringBuilder outstr = new StringBuilder();
	        for (int i = 0; i < gdhtoplist.size(); i++) {				
				outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append(",");
    			outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append("|");	 
			}	
	    		
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("hntclchangejzbw")
	public String hntclchangejzbw() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        List gdhtoplist = sysService.limitgetListbyField(request, "jiaozuobuwei", bd, null, null);
	        StringBuilder outstr = new StringBuilder();
	        for (int i = 0; i < gdhtoplist.size(); i++) {				
				outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append(",");
    			outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append("|");	 
			}	
	    		
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("hntclxmbchangejzbw")
	public String hntclxmbchangejzbw() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String xmbmc = request.getParameter("xmbmc");
			Integer xmb = null;
	        if(StringUtil.Null2Blank(xmbmc).length() > 0) {
	        	xmb = Integer.valueOf(xmbmc);
	        }
	        List gdhtoplist = sysService.limitgetListbyField(request, "jiaozuobuwei", null, xmb, null);
	        StringBuilder outstr = new StringBuilder();
	        for (int i = 0; i < gdhtoplist.size(); i++) {				
				outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append(",");
    			outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append("|");	 
			}	
	    		
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("hntclbhzchangejzbw")
	public String hntclbhzchangejzbw() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String bhzmc = request.getParameter("bhzmc");			
	        List gdhtoplist = sysService.limitgetListbyField(request, "jiaozuobuwei", null, null, bhzmc);
	        StringBuilder outstr = new StringBuilder();
	        for (int i = 0; i < gdhtoplist.size(); i++) {				
				outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append(",");
    			outstr.append(String.valueOf(gdhtoplist.get(i)));
    			outstr.append("|");	 
			}	
	    		
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("hntxiangxi")
	public String hntxiangxi() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		String xxid = request.getParameter("xxid");
		setShebeibianhao(request.getParameter("shebeibianhao"));
		HntbhzziduancfgView hbfield = queryService.gethntcfgfield(shebeibianhao);
		setHntbhzField(hbfield);
		if (null != xxid) {
			Xiangxixx xx = queryService.xxfindById(Integer.parseInt(xxid));
			setXiangxixx(xx);
		}
		return SUCCESS;
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

	public Integer getBiaoduan() {
		return biaoduan;
	}

	public void setBiaoduan(Integer biaoduan) {
		this.biaoduan = biaoduan;
	}

	public Integer getXiangmubu() {
		return xiangmubu;
	}

	public void setXiangmubu(Integer xiangmubu) {
		this.xiangmubu = xiangmubu;
	}

	public String getBhzname() {
		return bhzname;
	}

	public void setBhzname(String bhzname) {
		this.bhzname = bhzname;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
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

	@Action("swcllist")
	public String swcllist() throws UnsupportedEncodingException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);	
		HttpServletResponse response= (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if(StringUtil.Null2Blank(request.getParameter("startTime")).length()>0){
			setStartTime(request.getParameter("startTime"));
		}
		if(StringUtil.Null2Blank(request.getParameter("endTime")).length()>0){
			setEndTime(request.getParameter("endTime"));
		}
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		//这里用来解决使用部位中文乱码问题
		String zhongwen="";
		if (StringUtil.Null2Blank(request.getParameter("luanma")).length()>0) {
			//服务器上的tomcat和本地的tomcat配置不一样，本地的需要转码，服务器上的不需要转码
			zhongwen = new String(request.getParameter("zhongwen").getBytes("iso-8859-1"), "utf-8");
			System.out.println("zhongwen分页转码："+zhongwen);
			//这个setLlbuwei是服务器上没有转码用的
			//setLlbuwei(request.getParameter("zhongwen"));
			//本地的
			setLlbuwei(zhongwen);
			System.out.println("zhongwen分页没有转码："+request.getParameter("zhongwen"));
			String clvalue = java.net.URLDecoder.decode(request.getParameter("zhongwen") , "UTF-8");
			System.out.println("clvalue:"+clvalue);
			
		}
		if(StringUtil.Null2Blank(request.getParameter("llbuwei")).length()>0){	
			System.out.println("request.getParameter:"+request.getParameter("llbuwei"));
			setLlbuwei(request.getParameter("llbuwei"));
			
		}
		if (null != clselect && clselect.length > 0) {
			Shuiwenziduancfg swcfgisshow = queryService.getswcfg(shebeibianhao);
			if (null == swcfgisshow) {
				swcfgisshow = new Shuiwenziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					swcfgisshow.setGprsbianhao(shebeibianhao);
					swcfgisshow.setLeixing("21");
				} else {
					swcfgisshow.setGprsbianhao("all");
					swcfgisshow.setLeixing("20");
				}
			}
			
			List<Integer> selectlist = new ArrayList<Integer>();
			for (int h = 0; h < clselect.length; h++) {
				selectlist.add(clselect[h]);
			}
			if (selectlist.contains(1)) {
				swcfgisshow.setSjgl1("1");
			} else {
				swcfgisshow.setSjgl1("0");
			}
			if (selectlist.contains(2)) {
				swcfgisshow.setSjgl2("1");
			} else {
				swcfgisshow.setSjgl2("0");
			}
			if (selectlist.contains(3)) {
				swcfgisshow.setSjgl3("1");
			} else {
				swcfgisshow.setSjgl3("0");
			}
			if (selectlist.contains(4)) {
				swcfgisshow.setSjgl4("1");
			} else {
				swcfgisshow.setSjgl4("0");
			}
			if (selectlist.contains(5)) {
				swcfgisshow.setSjgl5("1");
			} else {
				swcfgisshow.setSjgl5("0");
			}
			if (selectlist.contains(6)) {
				swcfgisshow.setSjfl1("1");
			} else {
				swcfgisshow.setSjfl1("0");
			}
			if (selectlist.contains(7)) {
				swcfgisshow.setSjfl2("1");
			} else {
				swcfgisshow.setSjfl2("0");
			}
			if (selectlist.contains(8)) {
				swcfgisshow.setSjshui("1");
			} else {
				swcfgisshow.setSjshui("0");
			} 
			if (selectlist.contains(9)) {
				swcfgisshow.setShijian("1");
			} else {
				swcfgisshow.setShijian("0");
			}
			if (selectlist.contains(10)) {
				swcfgisshow.setGlchangliang("1");
			} else {
				swcfgisshow.setGlchangliang("0");
			}
			queryService.saveswcfg(swcfgisshow);
		}
		
		ShuiwenziduancfgView swziduanfield = queryService.getSwfield(shebeibianhao);
		ShuiwenziduancfgView swisshow = queryService.getswcfgisShow(shebeibianhao);	
		if (null == swisshow) {
			Shuiwenziduancfg swcfgisshow = queryService.getswcfg(shebeibianhao);
			if (null == swcfgisshow) {
				swcfgisshow = new Shuiwenziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					swcfgisshow.setGprsbianhao(shebeibianhao);
					swcfgisshow.setLeixing("21");
				} else {
					swcfgisshow.setGprsbianhao("all");
					swcfgisshow.setLeixing("20");
				}
			}
			swcfgisshow.setSjgl1("1");
			swcfgisshow.setSjgl2("1");
			swcfgisshow.setSjgl3("1");
			swcfgisshow.setSjgl4("1");
			swcfgisshow.setSjgl5("1");
			swcfgisshow.setSjfl1("1");
			swcfgisshow.setSjfl2("1");
			swcfgisshow.setSjshui("1");
			swcfgisshow.setShijian("1");
			swcfgisshow.setGlchangliang("1");
			queryService.saveswcfg(swcfgisshow);
			swisshow = queryService.getswcfgisShow(shebeibianhao);
		}		
		cllist = new ArrayList<WuchaIsShowData>();
		clselect = new Integer[10];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl1()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl1());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl2()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl2());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl3()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl3());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl4()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl4());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl5()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl5());
		cllist.add(wd);
		
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjfl1()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjfl1());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjfl2()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjfl2());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjshui()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjshui());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getShijian()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getShijian());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getGlchangliang()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getGlchangliang());
		cllist.add(wd);
	
		setSwField(swziduanfield);
		setSwisShow(swisshow);
		
		setSwcls(queryService.viewswlist(shebeibianhao, 
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems,llbuwei));
	
		setShaifenjieguopgs(shaifenService.shaifenjieguoviewlist(shebeibianhao, startTime, endTime,
				biaoduan, xiangmubu,StringUtil.getQueryFieldNameByRequest(request),
				StringUtil.getBiaoshiId(request), pageNo, maxPageItems,llbuwei));
		
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		listmap = new LinkedHashMap<String, String>();
		List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request, biaoduan, xiangmubu);
		for (TopRealMaxShuiwenxixxView topsw : toplist) {
			listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
		}
		
		llbuweilistMap=new LinkedHashMap<String, String>();
		List<ShuiwenxixxlilunView> swLilunlist = swllService.getAll(biaoduan,xiangmubu,shebeibianhao,StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request));
		for(ShuiwenxixxlilunView swxixxLilun:swLilunlist){
			llbuweilistMap.put(swxixxLilun.getLlbuwei(), swxixxLilun.getLlbuwei());
		}
		
		if(shaifenjieguopgs!=null && shaifenjieguopgs.getDatas()!=null && shaifenjieguopgs.getDatas().size()>0){
			request.setAttribute("strShaifenXML",queryService.getShijiShaifenXml(shaifenjieguopgs.getDatas()));
		}else{
			request.setAttribute("strShaifenXML","");
		}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")){
		}
				
		return SUCCESS;
	}
	
	
	@Action(value = "/output/loadswcllist", results = {@Result(name = "hnt", type = "redirectAction", location = "loadhntdata",params={"shebeibianhao","${shebeibianhao}"}),
			@Result(name = "sw", type = "redirectAction", location = "loadswcllist",params={"shebeibianhao","${shebeibianhao}"}),
			@Result(name = "lq", type = "redirectAction", location = "loadliqingcllist",params={"shebeibianhao","${shebeibianhao}"})})
	public String loadswcllist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		Integer bsid = 1;
		String fn = "all";
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}

		setShebeibianhao(request.getParameter("shebeibianhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			Banhezhanxinxi bhz = sysService.getBhzbysbbh(shebeibianhao);
			if (null != bhz) {
				String sblx = StringUtil.Null2Blank(bhz.getShebeileixin());
				if (sblx.equalsIgnoreCase("2")) {
					return "lq";
				} else if (sblx.equalsIgnoreCase("1")){
					return "hnt";
				} else {
					setSwcls(queryService.viewswlist(shebeibianhao, 
							startTime, endTime, biaoduan, xiangmubu, 
							StringUtil.getQueryFieldNameByRequest(request), 
							StringUtil.getBiaoshiId(request),pageNo, maxPageItems,llbuwei));

					
					if (null != swcls && null != swcls.getDatas() && swcls.getDatas().size() > 0) {
						request.setAttribute("strXML", queryService.getSwCailiaoXml(swcls.getDatas()));
					} else {
						request.setAttribute("strXML", "");
					}

					biaoduanlistmap = new LinkedHashMap<Integer, String>();
					List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
					for (Biaoduanxinxi bd : bdlist) {
						biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
					}
					xmblistmap = new LinkedHashMap<Integer, String>();
					List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, biaoduan);
					for (Xiangmubuxinxi xmb : xmblist) {
						xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
					}		
					
					
					listmap = new LinkedHashMap<String, String>();
					List<Banhezhanxinxi> toplist = sysService.loadbhzlist(fn, bsid, biaoduan, xiangmubu);
					for (Banhezhanxinxi bhzxx : toplist) {
						listmap.put(bhzxx.getGprsbianhao(), bhzxx
								.getBanhezhanminchen());
					}
							
					
					return SUCCESS;
				}
			} else {
				return null;
			}
		
		} else {
			setSwcls(queryService.viewswlist(shebeibianhao, 
					startTime, endTime, biaoduan, xiangmubu, 
					StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request),pageNo, maxPageItems,llbuwei));

			
			if (null != swcls && null != swcls.getDatas() && swcls.getDatas().size() > 0) {
				request.setAttribute("strXML", queryService.getSwCailiaoXml(swcls.getDatas()));
			} else {
				request.setAttribute("strXML", "");
			}

			biaoduanlistmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
			for (Biaoduanxinxi bd : bdlist) {
				biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
			}
			xmblistmap = new LinkedHashMap<Integer, String>();
			List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, biaoduan);
			for (Xiangmubuxinxi xmb : xmblist) {
				xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
			}		
			
			
			listmap = new LinkedHashMap<String, String>();
			List<Banhezhanxinxi> toplist = sysService.loadbhzlist(fn, bsid, biaoduan, xiangmubu);
			for (Banhezhanxinxi bhzxx : toplist) {
				listmap.put(bhzxx.getGprsbianhao(), bhzxx
						.getBanhezhanminchen());
			}
					
			
			return SUCCESS;
		}
		
	}

	@Action("lqsjlist")
	public String lqsjlist(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		setShebeibianhao(request.getParameter("shebeibianhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		setLqsjpageMode(queryService.lqsjviewlist(shebeibianhao,
				startTime, endTime, biaoduan, xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		if (null != lqsjpageMode && null != lqsjpageMode.getDatas() && lqsjpageMode.getDatas().size() > 0) {
			request.setAttribute("strXML", queryService.getlqBanheshijianXml(lqsjpageMode.getDatas()));
		} else {
			request.setAttribute("strXML", "");
		}
		this.setLiqingField(queryService.getlqcfgfield(shebeibianhao));
		this.setLiqingisShow(queryService.getlqcfgisShow(shebeibianhao));
		
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		bhzlistmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			bhzlistmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		return SUCCESS;
	}

	
//	@Action("userloglist")
//	public String userloglist() {
//		ActionContext context = ActionContext.getContext();
//		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
//		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
//		setPageNo(1);
//		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
//			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
//		}
//		setMaxPageItems(20);
//		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
//			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
//		}
//		biaoduanlistmap = new LinkedHashMap<Integer, String>();
//		List<Biaoduanxinxi> bdlist =sysService.limitbdlist(request);
//		for (Biaoduanxinxi bd : bdlist) {
//			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
//		}
//		xmblistmap = new LinkedHashMap<Integer, String>();
//		List<Xiangmubuxinxi> xmblist =sysService.limitxmblist(request, biaoduan);
//		for (Xiangmubuxinxi xmb : xmblist) {
//			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
//		}
//		
//		bhzlistmap = new LinkedHashMap<Integer, String>();
//		List<TopLiqingView> toplist =sysService.limitlqlist(request, biaoduan, xiangmubu);
//		for (TopLiqingView toplqView : toplist) {
//			bhzlistmap.put(toplqView.getId(), toplqView
//					.getBanhezhanminchen());
//		}
//		
//		usertypelistmap = new LinkedHashMap<Integer, String>();	
//		List<Usertypexinxi> utlist =sysService.limitutlist(request);
//		for (Usertypexinxi ut : utlist) {
//			usertypelistmap.put(ut.getId(), ut.getMinchen());
//		}		
//		if(StringUtil.Null2Blank(request.getParameter("username")).trim().length()>0){
//			this.setUsername(request.getParameter("username"));
//		}
//		if(StringUtil.Null2Blank(request.getParameter("danwei"))!=null && StringUtil.Null2Blank(request.getParameter("danwei")).trim().length()>0){
//			this.setDanwei(request.getParameter("danwei"));
//		}
//		if(StringUtil.Null2Blank(request.getParameter("zhiwei"))!=null && StringUtil.Null2Blank(request.getParameter("zhiwei")).trim().length()>0){
//			this.setZhiwei(request.getParameter("zhiwei"));
//		}
//		String startTimeOne = request.getParameter("startTime");
//		String endTimeOne = request.getParameter("endTime");
//		setStartTime(startTimeOne);
//		setEndTime(endTimeOne);
//		setUserloglist(queryService.viewuserlogtongjilist(username,danwei,zhiwei, 
//				startTimeOne, endTimeOne,usertype,biaoduan,xiangmubu,pageNo,maxPageItems,shebeibianhao));	
//		String chaxun = request.getParameter("chaxun");
//		String xiazai = request.getParameter("xiazai");
//		
//		if(null==chaxun && null!=xiazai && xiazai.equals("123")) {
//			List<String> dataList=new ArrayList<String>();
//			for (Object  obj: userloglist.getDatas()) {
//				UserlogView ul=(UserlogView) obj;
//				StringBuilder databuilder = new StringBuilder();
//				databuilder.append(StringUtil.Null2Blank(ul.getName()));
//				databuilder.append("&^&");	
//				String danweixm = StringUtil.Null2Blank(ul.getFullname());
//				String danwei = "";
//				String xm = "";
//				if (danweixm.indexOf("-")>0) {
//					danwei = danweixm.substring(0, danweixm.indexOf("-"));
//					xm = danweixm.substring(danweixm.indexOf("-")+1);
//				} else if (danweixm.length()>2) {
//					danwei = danweixm.substring(0, 2);
//					xm = danweixm.substring(2);
//				} else {
//					xm = danweixm;	
//				}
//				databuilder.append(danwei);
//				databuilder.append("&^&");	
//				databuilder.append(xm);
//				databuilder.append("&^&");
//				databuilder.append(danweixm);
//				databuilder.append("&^&");
//				databuilder.append(StringUtil.Null2Blank(ul.getRemark()));
//				databuilder.append("&^&");
//				databuilder.append(StringUtil.Null2Blank(ul.getLogip()));
//				databuilder.append("&^&");	
//				databuilder.append(StringUtil.Null2Blank(ul.getLogdate()));
//				dataList.add(databuilder.toString());
//			}
//			String path=request.getSession().getServletContext().getRealPath("/");
//			String excelName="excel/"+System.currentTimeMillis()+".xls";
//			setExcelName(excelName);
//			File file=new File(path+"excel");
//			if(!file.exists()){
//				file.mkdir();
//			}
//			
//			HntExcelUtil.importuserlogExcel(path+"excel/登录日志.xls", path+excelName, 
//					StringUtil.Null2Blank(startTimeOne)+" "+StringUtil.Null2Blank(endTimeOne),dataList);
//			try {
//				response.reset();
//				response.setContentType("bin");
//				response.setHeader("Content-Disposition",
//						"attachment; filename=\"" + excelName + "\"");
//				java.io.FileInputStream in = new java.io.FileInputStream(path
//						+ excelName);
//				PrintWriter out = response.getWriter();
//				int i;
//				while ((i = in.read()) != -1) {
//					out.write(i);
//				}
//				in.close();
//		        out.flush();
//		        out.close();
//			} catch (Exception e) {
//			}
//	    }
//		return SUCCESS;
//	}
	
	@Action("oneuserloglist")
	public String oneuserloglist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		String userid="";
		userid = request.getParameter("userid");
		
		if (StringUtil.Null2Blank(userid).length()>0) {
			Muser user=userService.getUserById(Integer.valueOf(userid));
			if (null != user) {
				setShebeibianhao(user.getName());
			}
		}
		  //shebeibianhao = request.getParameter("shebeibianhao");//java.net.URLDecoder.decode(request.getParameter("shebeibianhao"),"GBK");

		String startTimeOne = request.getParameter("startTime");
		String endTimeOne = request.getParameter("endTime");
		setStartTime(startTimeOne);
		setEndTime(endTimeOne);
		setLogshowpagemode(queryService.viewuserloglist(shebeibianhao, 
				startTimeOne, endTimeOne, pageNo, maxPageItems));		
		return SUCCESS;
	}
	
	@Action("userloglist")
	public String userloglist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		
		String shebeibianhao = request.getParameter("shebeibianhao");
		setShebeibianhao(shebeibianhao);		

		String startTimeOne = request.getParameter("startTime");
		String endTimeOne = request.getParameter("endTime");
		setStartTime(startTimeOne);
		setEndTime(endTimeOne);
		setUserloglist(queryService.viewuserlogtongjilist(shebeibianhao, 
				startTimeOne, endTimeOne));	
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")) {
			List<String> dataList=new ArrayList<String>();
			for (UserlogView  ul: userloglist) {
				StringBuilder databuilder = new StringBuilder();
				databuilder.append(StringUtil.Null2Blank(ul.getName()));
				databuilder.append("&^&");	
				String danweixm = StringUtil.Null2Blank(ul.getFullname());
				String danwei = "";
				String xm = "";
				if (danweixm.indexOf("-")>0) {
					danwei = danweixm.substring(0, danweixm.indexOf("-"));
					xm = danweixm.substring(danweixm.indexOf("-")+1);
				} else if (danweixm.length()>2) {
					danwei = danweixm.substring(0, 2);
					xm = danweixm.substring(2);
				} else {
					xm = danweixm;	
				}
				databuilder.append(danwei);
				databuilder.append("&^&");	
				databuilder.append(xm);
				databuilder.append("&^&");
				databuilder.append(danweixm);
				databuilder.append("&^&");
				databuilder.append(StringUtil.Null2Blank(ul.getRemark()));
				databuilder.append("&^&");
				databuilder.append(StringUtil.Null2Blank(ul.getLogip()));
				databuilder.append("&^&");	
				databuilder.append(StringUtil.Null2Blank(ul.getLogdate()));
				dataList.add(databuilder.toString());
			}
			String path=request.getSession().getServletContext().getRealPath("/");
			String excelName="excel/"+System.currentTimeMillis()+".xls";
			setExcelName(excelName);
			File file=new File(path+"excel");
			if(!file.exists()){
				file.mkdir();
			}
			
			HntExcelUtil.importuserlogExcel(path+"excel/登录日志.xls", path+excelName, 
					StringUtil.Null2Blank(startTimeOne)+" "+StringUtil.Null2Blank(endTimeOne),dataList);
			try {
				response.reset();
				response.setContentType("bin");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + excelName + "\"");
				java.io.FileInputStream in = new java.io.FileInputStream(path
						+ excelName);
				PrintWriter out = response.getWriter();
				int i;
				while ((i = in.read()) != -1) {
					out.write(i);
				}
				in.close();
		        out.flush();
		        out.close();
			} catch (Exception e) {
			}
	    }
		return SUCCESS;
	}
	
	@Action("shuiwenxixx")
	public String shuiwenxixx() {
		/**
		 * 这里需要添加一个误差对比，类似水稳超标查询的详情查询
		 */
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("swid")).length()>0){
			this.setSwid(request.getParameter("swid"));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			this.setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		ShuiwenziduancfgView swfield = queryService.getSwfield(shebeibianhao);
		this.setSwField(swfield);
		//超标查询的数据
		setSwmanualphbxixx(queryService.swmanualphbfindById(Integer.parseInt(swid)));
		if (this.getSwid()!=null) {
			ShuiwenxixxView swxx = queryService.swxxfindById(Integer.parseInt(swid));
			this.setShuiwenxx(swxx);
		}
		return SUCCESS;
	}
	
	@Action("shuiwensjfx")
	public String shuiwensjfx(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			this.setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if(StringUtil.Null2Blank(request.getParameter("startTime")).length()>0){
			this.setStartTime(request.getParameter("startTime"));
		}
		if(StringUtil.Null2Blank(request.getParameter("endTime")).length()>0){
			this.setEndTime(request.getParameter("startTime"));
		}
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		listmap = new LinkedHashMap<String, String>();
		List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request, biaoduan, xiangmubu);
		for (TopRealMaxShuiwenxixxView topsw : toplist) {
			listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
		}
		setSwisShow(queryService.getSwcfgisShow(shebeibianhao));
		setSwField(queryService.getSwfield(shebeibianhao));
		
		setSwmanualphb(swViewService.swwuchamanualphbviewlist(shebeibianhao, 
				startTime, endTime,  biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		return SUCCESS;
	}
	
	//查看水稳超标情况详情
	@Action("swmanualphbxixx")
	public String swmanualphbxixx(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("swid")).length()>0){
			this.setSwid(request.getParameter("swid"));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			this.setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if(StringUtil.Null2Blank(request.getParameter("method")).length()>0){
			setMethod(request.getParameter("method"));
		}
		this.setSwField(queryService.getSwfield(shebeibianhao));
		if(StringUtil.Null2Blank(swid).length()>0){
			setSwmanualphbxixx(queryService.swmanualphbfindById(Integer.parseInt(swid)));
			setSwxixxjieguo(sysService.getSwjieguobybh(Integer.parseInt(swid)));
			setSmsInfoList(smsInfoService.getSmsInfoAll(Integer.parseInt(swid)));
		}
		return SUCCESS;
	}
	
	//查看沥青超标情况详情
		@Action("lqmanualphbxixx")
		public String lqmanualphbxixx(){
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			if(StringUtil.Null2Blank(request.getParameter("lqid")).length()>0){
				this.setLqid(request.getParameter("lqid"));
			}
			if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
				this.setShebeibianhao(request.getParameter("shebeibianhao"));
			}
			if(StringUtil.Null2Blank(request.getParameter("method")).length()>0){
				setMethod(request.getParameter("method"));
			}
			this.setLiqingField(queryService.getLqfield(shebeibianhao));
			if(StringUtil.Null2Blank(lqid).length()>0){
				setLqmanualphbxixx(queryService.lqmanualphbfindById(Integer.parseInt(lqid)));
				setLqxixxjieguo(sysService.getLljieguobybh(Integer.parseInt(lqid)));
				setSmsInfoList(smsInfoService.getLqSmsInfoAll(Integer.parseInt(lqid)));
			}
			return SUCCESS;
		}
	
	//提交水稳的结果
	@Action(value="updateswjieguo",results = {
			@Result(name = "shuiwenchaobiaolist", type = "redirect", location ="shuiwenchaobiaolist?pid=6&record=26&"),
			@Result(name = "querylist", type = "redirect", location ="querylist?pid=6&record=34&")
			})
	public String updateswjieguo(){
		String returnStr="";
    	ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session =request.getSession();
		//得到提交的处理人
		SecurityContext sc = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication auth = sc.getAuthentication();
		String username=((UserInfo)auth.getPrincipal()).getUsername();
		
		if(StringUtil.Null2Blank(request.getParameter("swid")).length()>0){
			this.setSwid(request.getParameter("swid"));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			this.setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if(StringUtil.Null2Blank(swid).length()>0){
			Shuiwenxixxjieguo swjieguo=sysService.getSwjieguobybh(Integer.parseInt(swid));
			swjieguo.setWentiyuanyin(swxixxjieguo.getWentiyuanyin());
			swjieguo.setChulishijian(swxixxjieguo.getChulishijian());
			swjieguo.setChulijieguo(swxixxjieguo.getChulijieguo());
			swjieguo.setChulifangshi(swxixxjieguo.getChulifangshi());
			swjieguo.setChuliren(swxixxjieguo.getChuliren());
			//
			swjieguo.setJianlishenpi(swxixxjieguo.getJianlishenpi());
			swjieguo.setJianliresult(swxixxjieguo.getJianliresult());
			swjieguo.setShenpidate(swxixxjieguo.getShenpidate());
			//
			swjieguo.setConfirmdate(swxixxjieguo.getConfirmdate());
			swjieguo.setBeizhu(swxixxjieguo.getBeizhu());
			swjieguo.setYezhuyijian(swxixxjieguo.getYezhuyijian());
			if (StringUtil.checkAuthByurl(session, "/query/jianlishenpi")){
				swjieguo.setJianliren(username);
			}
				
			
			/*
			if(StringUtil.Null2Blank(swxixxjieguo.getChulijieguo()).length()>0 || 
					StringUtil.Null2Blank(swxixxjieguo.getWentiyuanyin()).length()>0 ||
					StringUtil.Null2Blank(swxixxjieguo.getChulifangshi()).length()>0){
				if(StringUtil.Null2Blank(swjieguo.getJianliren()).length()==0){
					
				}
			}*/
			if(StringUtil.Null2Blank(swxixxjieguo.getYezhuyijian()).length()>0){
				if(StringUtil.Null2Blank(swjieguo.getYezhuren()).length()==0){
					swjieguo.setYezhuren(username);
				}
			}
			sysService.saveSwjieguo(swjieguo);
		}
		if(StringUtil.Null2Blank(request.getParameter("method")).equalsIgnoreCase("2")){
			returnStr="shuiwenchaobiaolist";
		}else if(StringUtil.Null2Blank(request.getParameter("method")).equalsIgnoreCase("1")){
			returnStr="querylist";
		}
		return returnStr;
	}
	
	//提交水稳的结果
		@Action(value="updatelqjieguo",results = {
				@Result(name = "liqingchaobiaolist", type = "redirect", location ="liqingchaobiaolist?pid=5&record=4&"),
				@Result(name = "Lqquerylist", type = "redirect", location ="Lqquerylist?pid=5&record=4&")
				})
		public String updatelqjieguo(){
			String returnStr="";
	    	ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			HttpSession session =request.getSession();
			//得到提交的处理人
			SecurityContext sc = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
			Authentication auth = sc.getAuthentication();
			String username=((UserInfo)auth.getPrincipal()).getUsername();
			if(StringUtil.Null2Blank(request.getParameter("lqid")).length()>0){
				this.setSwid(request.getParameter("lqid"));
			}
			if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
				this.setShebeibianhao(request.getParameter("shebeibianhao"));
			}
			if(StringUtil.Null2Blank(lqid).length()>0){
				Liqingxixxjieguo lqjieguo=sysService.getLljieguobybh(Integer.parseInt(lqid));
				lqjieguo.setWentiyuanyin(lqxixxjieguo.getWentiyuanyin());
//				lqjieguo.setChulishijian(lqxixxjieguo.getChulishijian());
				lqjieguo.setChulijieguo(lqxixxjieguo.getChulijieguo());
				lqjieguo.setChulifangshi(lqxixxjieguo.getChulifangshi());
//				lqjieguo.setChuliren(lqxixxjieguo.getChuliren());
//				lqjieguo.setJianlishenpi(lqxixxjieguo.getJianlishenpi());
//				lqjieguo.setJianliresult(lqxixxjieguo.getJianliresult());
				lqjieguo.setShenpidate(lqxixxjieguo.getShenpidate());
				lqjieguo.setConfirmdate(lqxixxjieguo.getConfirmdate());
				lqjieguo.setBeizhu(lqxixxjieguo.getBeizhu());
				lqjieguo.setYezhuyijian(lqxixxjieguo.getYezhuyijian());
				
				if(StringUtil.Null2Blank(lqxixxjieguo.getChulijieguo()).length()>0 || 
						StringUtil.Null2Blank(lqxixxjieguo.getWentiyuanyin()).length()>0 ||
						StringUtil.Null2Blank(lqxixxjieguo.getChulifangshi()).length()>0){
					if(StringUtil.Null2Blank(lqjieguo.getJianliren()).length()==0){
						lqjieguo.setJianliren(username);
					}
				}
				if(StringUtil.Null2Blank(lqxixxjieguo.getYezhuyijian()).length()>0){
					if(StringUtil.Null2Blank(lqjieguo.getYezhuren()).length()==0){
						lqjieguo.setYezhuren(username);
					}
				}
				sysService.saveLqjieguo(lqjieguo);
			}
			if(StringUtil.Null2Blank(request.getParameter("method")).equalsIgnoreCase("2")){
				returnStr="liqingchaobiaolist";
			}else if(StringUtil.Null2Blank(request.getParameter("method")).equalsIgnoreCase("1")){
				returnStr="Lqquerylist";
			}
			return returnStr;
		}
	
	@Action("showUsepic")
	public String showUsepic(){
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);
		if(StringUtil.Null2Blank(request.getParameter("swid")).length()>0){
			this.setSwid(request.getParameter("swid"));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			this.setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		this.setSwField(queryService.getSwfield(shebeibianhao));
		String xmlStr="";
		if(StringUtil.Null2Blank(swid).length()>0){
			setSwmanualphbxixx(queryService.swmanualphbfindById(Integer.parseInt(swid)));
			if(swmanualphbxixx!=null){
				xmlStr=getDataService.showShuiwenUsepic(swmanualphbxixx,swField);
			}
		}
		PrintWriter out= null;
		try {
			response.setContentType("text/xml;charset=utf-8");  
		    response.setHeader("Cache-Control", "no-cache");  
			out = response.getWriter();
			out.print(xmlStr);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}
	@Action("LqshowUsepic")
	public String LqshowUsepic(){
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);
		if(StringUtil.Null2Blank(request.getParameter("lqid")).length()>0){
			this.setLqid(request.getParameter("lqid"));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			this.setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		this.setLiqingField(queryService.getLqfield(shebeibianhao));
		String xmlStr="";
		if(StringUtil.Null2Blank(lqid).length()>0){
			setLqmanualphbxixx(queryService.lqmanualphbfindById(Integer.parseInt(lqid)));
			if(lqmanualphbxixx!=null){
				xmlStr=getDataService.showLqUsepic(lqmanualphbxixx,liqingField);
			}
		}
		PrintWriter out= null;
		try {
			response.setContentType("text/xml;charset=utf-8");  
		    response.setHeader("Cache-Control", "no-cache");  
			out = response.getWriter();
			out.print(xmlStr);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}

	public String getLqid() {
		return lqid;
	}

	public void setLqid(String lqid) {
		this.lqid = lqid;
	}

	public Liqingxixxjieguo getLqxixxjieguo() {
		return lqxixxjieguo;
	}

	public void setLqxixxjieguo(Liqingxixxjieguo lqxixxjieguo) {
		this.lqxixxjieguo = lqxixxjieguo;
	}

	public LiqingmanualphbView getLqmanualphbxixx() {
		return lqmanualphbxixx;
	}

	public void setLqmanualphbxixx(LiqingmanualphbView lqmanualphbxixx) {
		this.lqmanualphbxixx = lqmanualphbxixx;
	}
}
