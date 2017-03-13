package com.mss.shtoone.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.ShuiwenxixxlilunView;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.WuchaIsShowData;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.QuerywuchaService;
import com.mss.shtoone.service.ShaifenshiyanService;
import com.mss.shtoone.service.ShuiwenxixxlilunService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.LqExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class LqQueryAction extends ActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1249941574631326575L;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private QuerywuchaService querywcService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private ShaifenshiyanService shaifenService;
	
	@Autowired
	private ShuiwenxixxlilunService swllService;

	private GenericPageMode liqingpgs;
	private GenericPageMode shaifenjieguopgs;
	private LiqingziduancfgView liqingisShow;
	private LiqingziduancfgView liqingField;
	private LiqingView lqxixx;
	private Integer[] clselect;
	private List<WuchaIsShowData> cllist;
	private String startTime;
	private String endTime;
	private Integer biaoduan;
	private Integer xiangmubu;
	private Integer maxPageItems;
	private Integer pageNo;
    private String peifan;
	private GenericPageMode lqsjPageMode;
	private String shebeibianhao;
	private String bhzname;
	public String excelName;
	private Map<String, String> listmap;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<Integer, String> bhzlistmap;
	private Map<String,String> peifanmap;
	private String xxid;
	private LiqingmanualphbView lqmanualphbxixx;
	
	
	
	public LiqingmanualphbView getLqmanualphbxixx() {
		return lqmanualphbxixx;
	}

	public void setLqmanualphbxixx(LiqingmanualphbView lqmanualphbxixx) {
		this.lqmanualphbxixx = lqmanualphbxixx;
	}

	public String getXxid() {
		return xxid;
	}

	public void setXxid(String xxid) {
		this.xxid = xxid;
	}

	public GenericPageMode getShaifenjieguopgs() {
		return shaifenjieguopgs;
	}

	public void setShaifenjieguopgs(GenericPageMode shaifenjieguopgs) {
		this.shaifenjieguopgs = shaifenjieguopgs;
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

	public GenericPageMode getLiqingpgs() {
		return liqingpgs;
	}

	public void setLiqingpgs(GenericPageMode liqingpgs) {
		this.liqingpgs = liqingpgs;
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

	public LiqingView getLqxixx() {
		return lqxixx;
	}

	public void setLqxixx(LiqingView lqxixx) {
		this.lqxixx = lqxixx;
	}
	public GenericPageMode getLqsjPageMode() {
		return lqsjPageMode;
	}

	public void setLqsjPageMode(GenericPageMode lqsjPageMode) {
		this.lqsjPageMode = lqsjPageMode;
	}

	public String getPeifan() {
		return peifan;
	}

	public void setPeifan(String peifan) {
		this.peifan = peifan;
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
	public int getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
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

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public Map<String, String> getPeifanmap() {
		return peifanmap;
	}

	public void setPeifanmap(Map<String, String> peifanmap) {
		this.peifanmap = peifanmap;
	}

	public Map<Integer, String> getBhzlistmap() {
		return bhzlistmap;
	}

	public void setBhzlistmap(Map<Integer, String> bhzlistmap) {
		this.bhzlistmap = bhzlistmap;
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
	
	@Action("runstatelist")
	public String runstatelist() {
		int offset = 1;
		int pagesize = 15;
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		if (null != request.getParameter("pageNo")) {
			offset = Integer.parseInt(request.getParameter("pageNo"));
		}
		if (null != request.getParameter("maxPageItems")) {
			pagesize = Integer.parseInt(request.getParameter("maxPageItems"));
		}
		setMaxPageItems(pagesize);
		String shebeibianhao = request.getParameter("shebeibianhao");
		setShebeibianhao(shebeibianhao);	

		String startTimeOne = request.getParameter("startTime");
		String endTimeOne = request.getParameter("endTime");
		setStartTime(startTimeOne);
		setEndTime(endTimeOne);
		setLiqingpgs(queryService.viewrunstatelist(shebeibianhao, 
				startTimeOne, endTimeOne, biaoduan, xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),offset, pagesize));

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
		List<TopLiqingView> toplist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			listmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		return SUCCESS;
	}


	@Action("liqingcllist")
	public String liqingcllist() {
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

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		day.add(Calendar.DATE, -3);
		setStartTime(sdf.format(day.getTime()));
		if (null != request.getParameter("startTime")) {
			String startTimeOne = request.getParameter("startTime");
			try {
				if (sdf.parse(startTimeOne).after(day.getTime())) {
					setStartTime(request.getParameter("startTime"));
				}
			} catch (Exception e) {
			}
			
		}
		
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		setLiqingpgs(queryService.lqviewlist(shebeibianhao, 
				startTime, endTime,  biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems, 0,peifan));
		LiqingziduancfgView lqfield = queryService.getlqcfgfield(shebeibianhao);
		LiqingziduancfgView lqisshow = queryService.getlqcfgisShow(shebeibianhao);		
		setLiqingField(lqfield);
		setLiqingisShow(lqisshow);
		
		if (null != liqingpgs && null != liqingpgs.getDatas() && liqingpgs.getDatas().size() > 0) {
			request.setAttribute("strXML", queryService.getLiqingCailiaoXml(liqingpgs.getDatas(), liqingField, liqingisShow));
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
		List<TopLiqingView> toplist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			listmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& null != lqfield && null != lqisshow){
				List<String> dataList=new ArrayList<String>();
				List<String> headerList = new ArrayList<String>();				
				
				if (StringUtil.Null2Blank(lqisshow.getBaocunshijian()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(lqfield.getBaocunshijian()));
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

	@Action("liqingallcllist")
	public String liqingallcllist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		if (null != clselect && clselect.length > 0) {
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
			for (int h = 0; h < clselect.length; h++) {
				selectlist.add(clselect[h]);
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
			if(selectlist.contains(13)){
				lqcfgisshow.setGlwd("1");
			}else{
				lqcfgisshow.setGlwd("0");
			}
			if(selectlist.contains(14)){
				lqcfgisshow.setLqwd("1");
			}else{
				lqcfgisshow.setLqwd("0");
			}
			if(selectlist.contains(15)){
				lqcfgisshow.setClwd("1");
			}else{
				lqcfgisshow.setClwd("0");
			}
			querywcService.saveLqcfg(lqcfgisshow);
		}

        setPeifan(request.getParameter("peifan"));
		setShebeibianhao(request.getParameter("shebeibianhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		setLiqingpgs(queryService.lqviewlist(shebeibianhao, 
				startTime, endTime,  biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems, 1,peifan));

/**		
		setShaifenjieguopgs(shaifenService.Lqshaifenjieguoviewlist(shebeibianhao, startTime, endTime,
				biaoduan, xiangmubu,StringUtil.getQueryFieldNameByRequest(request),
				StringUtil.getBiaoshiId(request), pageNo, maxPageItems, 1, peifan));
**/
		
		LiqingziduancfgView lqfield = queryService.getlqcfgfield(shebeibianhao);
		LiqingziduancfgView lqisshow = queryService.getlqcfgisShow2(shebeibianhao);	
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
			lqcfgisshow.setGlwd("1");
			lqcfgisshow.setLqwd("1");
			lqcfgisshow.setClwd("1");
			querywcService.saveLqcfg(lqcfgisshow);
			lqisshow = queryService.getlqcfgisShow(shebeibianhao);	
		}		
		cllist = new ArrayList<WuchaIsShowData>();
		clselect = new Integer[15];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjysb());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
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
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getGlwd());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getLqwd());
		cllist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getClwd()).equalsIgnoreCase("1")) {
			clselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getClwd());
		cllist.add(wd);
	
		setLiqingField(lqfield);
		setLiqingisShow(lqisshow);
		
		if (null != liqingpgs && null != liqingpgs.getDatas() && liqingpgs.getDatas().size() > 0) {
			request.setAttribute("strXML", queryService.getLiqingCailiaoXml(liqingpgs.getDatas(), liqingField, liqingisShow));
			request.setAttribute("strXMLtemp", queryService.getLiqingTempXml(liqingpgs.getDatas(), liqingField, liqingisShow));
			request.setAttribute("strXMLysb", queryService.getLiqingCailiaoXmlysb(liqingpgs.getDatas(), liqingField, liqingisShow));
		} else {
			request.setAttribute("strXML", "");
			request.setAttribute("strXMLtemp", "");
			request.setAttribute("strXMLysb", "");
		}
		
		if(shaifenjieguopgs!=null && shaifenjieguopgs.getDatas()!=null && shaifenjieguopgs.getDatas().size()>0){
			request.setAttribute("strShaifenXML",queryService.getLqShijiShaifenXml(shaifenjieguopgs.getDatas()));
		}else{
			request.setAttribute("strShaifenXML","");
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
		List<TopLiqingView> toplist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			listmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		peifanmap=new LinkedHashMap<String, String>();
		List peifanlist=queryService.getlqxinghao();
		for(int m=0;m<peifanlist.size();m++){
			String str=(String)peifanlist.get(m);
			peifanmap.put(str,str);
		}
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& null != lqfield && null != lqisshow){
			 List<String> dataList=new ArrayList<String>();
			    List<String> headerList = new ArrayList<String>();			    
			    headerList.add("拌和站名称");
			    //headerList.add(StringUtil.Null2Blank(liqingField.getGcmc()));
			    //headerList.add(StringUtil.Null2Blank(liqingField.getSgbw()));
			    //headerList.add(StringUtil.Null2Blank(liqingField.getLjsl()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getShijian()));
			    //headerList.add(StringUtil.Null2Blank(liqingField.getJbsj()));
			    //headerList.add(StringUtil.Null2Blank(liqingField.getYonghu()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getPeifan()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getChangliang()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjysb()));			    
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjg1()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjg2()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjg3()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjg4()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjg5()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjg6()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjg7()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjf1()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjf2()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjlq()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getSjtjj()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getLqwd()));
			    headerList.add(StringUtil.Null2Blank(liqingField.getGlwd())); 
			    headerList.add(StringUtil.Null2Blank(liqingField.getClwd()));
			    String[] header = new String[headerList.size()];
				int j = 0;
				for (Iterator iterator = headerList.iterator(); iterator
						.hasNext();) {
					header[j] = (String) iterator.next();
					j++;
				}
				GenericPageMode xiazaipgs = queryService.lqviewlist(shebeibianhao, 
						startTime, endTime,  biaoduan, xiangmubu, 
						StringUtil.getQueryFieldNameByRequest(request), 
						StringUtil.getBiaoshiId(request),pageNo, liqingpgs.getRecordcount(), 1,peifan);
				for (Object  obj: xiazaipgs.getDatas()) {
					StringBuilder databuilder = new StringBuilder();
					LiqingView  lq = (LiqingView)obj;
					databuilder.append(StringUtil.Null2Blank(lq.getBanhezhanminchen()));
					databuilder.append("&^&");
					//databuilder.append(StringUtil.Null2Blank(lq.getGcmc()));
					//databuilder.append("&^&");
					//databuilder.append(StringUtil.Null2Blank(lq.getSgbw()));
					//databuilder.append("&^&");
					//databuilder.append(StringUtil.Null2Blank(lq.getLjsl()));
					//databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(lq.getShijian()));
					databuilder.append("&^&");
					//databuilder.append(StringUtil.Null2Blank(lq.getJbsj()));
					//databuilder.append("&^&");
					//databuilder.append(StringUtil.Null2Blank(lq.getYonghu()));
					//databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(lq.getPeifan()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getChangliang()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjysb()));
					databuilder.append("&^&");					
					databuilder.append(StringUtil.Null2Zero(lq.getSjg1()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjg2()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjg3()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjg4()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjg5()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjg6()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjg7()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjf1()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjf2()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjlq()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Zero(lq.getSjtjj()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(lq.getLqwd()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(lq.getGlwd()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(lq.getClwd()));
					dataList.add(databuilder.toString());
				}
				String path=request.getSession().getServletContext().getRealPath("/");
				String excelName="excel/"+System.currentTimeMillis()+".xls";
				setExcelName(excelName);
				File file=new File(path+"excel");
				if(!file.exists()){
					file.mkdir();
				}
				
				LqExcelUtil.exportListExcel(path+"excel/沥青拌合站生产情况统计表.xls", path+excelName, StringUtil.Null2Blank(bhzname), header, dataList);
				
				try {
					response.reset();
					response.setContentType("bin");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + excelName + "\"");
					java.io.FileInputStream in = new java.io.FileInputStream(path
							+ excelName);
					PrintWriter out = response.getWriter();
					int l;
					while ((l = in.read()) != -1) {
						out.write(l);
					}
					in.close();
			        out.flush();
			        out.close();
				} catch (Exception e) {
				}
		}
		
		return SUCCESS;
	}
	
	@Action("liqingxixx")
	public String liqingxixx() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("xxid")).length()>0){
			setXxid(request.getParameter("xxid"));
		}
		
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		setLiqingField(queryService.getlqcfgfield(shebeibianhao));
		if (null != xxid) {
			setLqxixx(queryService.lqxxfindById(Integer.parseInt(xxid)));
			setLqmanualphbxixx(queryService.lqmanualphbfindById(Integer.parseInt(xxid)));
		}
		return SUCCESS;
	}
	
	@Action("lqclchangebhz")
	public String lqclchangebhz() {
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
	    		for (TopLiqingView lq : toplist) {
	    			outstr.append(lq.getShebeibianhao());
	    			outstr.append(",");
	    			outstr.append(lq.getBanhezhanminchen());
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
	
	@Action("lqclxmbchangebhz")
	public String lqclxmbchangebhz() {
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
	    		for (TopLiqingView lq : toplist) {
	    			outstr.append(lq.getShebeibianhao());
	    			outstr.append(",");
	    			outstr.append(lq.getBanhezhanminchen());
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
	
	@Action("swclchangebhz")
	public String swclchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			Integer bd = null;
	        if(StringUtil.Null2Blank(request.getParameter("biaoduan")).length() > 0) {
	        	bd = Integer.valueOf(request.getParameter("biaoduan"));
	        }
	        List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request, bd, null);
	        StringBuilder outstr = new StringBuilder();
	    	for (TopRealMaxShuiwenxixxView sw : toplist) {
	    		outstr.append(sw.getShebeibianhao());
	    		outstr.append(",");
	    		outstr.append(sw.getBanhezhanminchen());
	    		outstr.append("|");	    			
	    	}
	    	if (outstr.length() > 0) {
	    		outstr.deleteCharAt(outstr.length()-1);
	    		out.print(outstr.toString());
	    		out.flush();
			}
	        out.close();
		} catch (IOException e) {}
		return null;
	}
	
	@Action("swllbdchangebhz")
	public String swllbdchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			Integer bd = null;
	        if(StringUtil.Null2Blank(request.getParameter("biaoduan")).length() > 0) {
	        	bd = Integer.valueOf(request.getParameter("biaoduan"));
	        }
	        List<ShuiwenxixxlilunView> toplist = swllService.getAll(bd,null,null,StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request));
	        StringBuilder outstr = new StringBuilder();
	    	for (ShuiwenxixxlilunView sw : toplist) {
	    		outstr.append(sw.getLlbuwei());
	    		outstr.append(",");
	    		outstr.append(sw.getLlbuwei());
	    		outstr.append("|");	    			
	    	}
	    	if (outstr.length() > 0) {
	    		outstr.deleteCharAt(outstr.length()-1);
	    		out.print(outstr.toString());
	    		out.flush();
			}
	        out.close();
		} catch (IOException e) {}
		return null;
	}

	@Action("swclxmbchangebhz")
	public String swclxmbchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			Integer xmb = null;
	        if(StringUtil.Null2Blank(request.getParameter("xmbmc")).length() > 0) {
	        	xmb = Integer.valueOf(request.getParameter("xmbmc"));
	        }
	        List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request, xmb, null);		
	        StringBuilder outstr = new StringBuilder();
	    	for (TopRealMaxShuiwenxixxView sw : toplist) {
	    		outstr.append(sw.getShebeibianhao());
	    		outstr.append(",");
	    		outstr.append(sw.getBanhezhanminchen());
	    		outstr.append("|");	    			
	    	}
	    	if (outstr.length() > 0) {
	    		outstr.deleteCharAt(outstr.length()-1);
	    		out.print(outstr.toString());
	    		out.flush();
			}
	        out.close();
		} catch (IOException e) {}
		return null;
	}
	
	@Action("swllxmbchangebhz")
	public String swllxmbchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			Integer xmb = null;
	        if(StringUtil.Null2Blank(request.getParameter("xmbmc")).length() > 0) {
	        	xmb = Integer.valueOf(request.getParameter("xmbmc"));
	        }
	        List<ShuiwenxixxlilunView> toplist = swllService.getAll(null,xmb,null,StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request));
	        StringBuilder outstr = new StringBuilder();
	    	for (ShuiwenxixxlilunView sw : toplist) {
	    		outstr.append(sw.getLlbuwei());
	    		outstr.append(",");
	    		outstr.append(sw.getLlbuwei());
	    		outstr.append("|");	    			
	    	}
	    	if (outstr.length() > 0) {
	    		outstr.deleteCharAt(outstr.length()-1);
	    		out.print(outstr.toString());
	    		out.flush();
			}
	        out.close();
		} catch (IOException e) {}
		return null;
	}
	
	@Action("swllbhzchangellbuwei")
	public String swllbhzchangellbuwei(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String bhz = null;
	        if(StringUtil.Null2Blank(request.getParameter("llshebeibianhao")).length() > 0) {
	        	bhz =request.getParameter("llshebeibianhao");
	        }
	        List<ShuiwenxixxlilunView> toplist = swllService.getAll(null,null,bhz,StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request));
	        StringBuilder outstr = new StringBuilder();
	    	for (ShuiwenxixxlilunView sw : toplist) {
	    		outstr.append(sw.getLlbuwei());
	    		outstr.append(",");
	    		outstr.append(sw.getLlbuwei());
	    		outstr.append("|");	    			
	    	}
	    	if (outstr.length() > 0) {
	    		outstr.deleteCharAt(outstr.length()-1);
	    		out.print(outstr.toString());
	    		out.flush();
			}
	        out.close();
		} catch (IOException e) {}
		return null;
	}
	
	@Action(value = "/output/loadliqingcllist", results = {@Result(name = "hnt", type = "redirectAction", location = "loadhntdata",params={"shebeibianhao","${shebeibianhao}"}),
			@Result(name = "sw", type = "redirectAction", location = "loadswcllist",params={"shebeibianhao","${shebeibianhao}"}),
			@Result(name = "lq", type = "redirectAction", location = "loadliqingcllist",params={"shebeibianhao","${shebeibianhao}"})})
	public String loadliqingcllist() {
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
				if (sblx.equalsIgnoreCase("1")) {
					return "hnt";
				} else if (sblx.equalsIgnoreCase("3")){
					return "sw";
				} else {
					setLiqingpgs(queryService.lqviewlist(shebeibianhao, 
							startTime, endTime,  biaoduan, xiangmubu, 
							StringUtil.getQueryFieldNameByRequest(request), 
							StringUtil.getBiaoshiId(request),pageNo, maxPageItems, 1,peifan));
					LiqingziduancfgView lqfield = queryService.getlqcfgfield(shebeibianhao);
					LiqingziduancfgView lqisshow = queryService.getlqcfgisShow(shebeibianhao);		
					setLiqingField(lqfield);
					setLiqingisShow(lqisshow);
					
					if (null != liqingpgs && null != liqingpgs.getDatas() && liqingpgs.getDatas().size() > 0) {
						request.setAttribute("strXML", queryService.getLiqingCailiaoXml(liqingpgs.getDatas(), liqingField, liqingisShow));
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
			setLiqingpgs(queryService.lqviewlist(shebeibianhao, 
					startTime, endTime,  biaoduan, xiangmubu, 
					StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request),pageNo, maxPageItems, 1,peifan));
			LiqingziduancfgView lqfield = queryService.getlqcfgfield(shebeibianhao);
			LiqingziduancfgView lqisshow = queryService.getlqcfgisShow(shebeibianhao);		
			setLiqingField(lqfield);
			setLiqingisShow(lqisshow);
			
			if (null != liqingpgs && null != liqingpgs.getDatas() && liqingpgs.getDatas().size() > 0) {
				request.setAttribute("strXML", queryService.getLiqingCailiaoXml(liqingpgs.getDatas(), liqingField, liqingisShow));
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

}