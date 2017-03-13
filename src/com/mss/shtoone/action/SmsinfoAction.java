package com.mss.shtoone.action;

import java.io.File;
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
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.SmsinfoPageMode;
import com.mss.shtoone.domain.SmsinfoView;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SmsinfoService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class SmsinfoAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7334974649736593717L;
	
	@Autowired
	private SmsinfoService smsService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private QueryService queryService;
	
	private SmsinfoPageMode smsinfo;
	
	private List<LiqingView> smstjlist;
	private Integer biaoduan;
	private Integer xiangmubu;
	private String shebeibianhao;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<String, String> bhzlistmap;
	private String startTime;
	private String endTime;
	private int maxPageItems;
	private Integer pageNo;
	private String bhzname;
	private LiqingziduancfgView lqzdcfg;
	private Integer fzlx=0;
	private Integer jbsj=0;
	private ShuiwenziduancfgView swField;
	private List<ShuiwenxixxView> swsmstjlist;
	
	@Action("smsinfolist")
	 public String smsinfoList(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = systemService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = systemService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		bhzlistmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist = systemService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			bhzlistmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0) {
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		setSmsinfo(smsService.getSuccess(startTime, endTime, biaoduan, xiangmubu, shebeibianhao,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& null != smsinfo && null != smsinfo.getDatas() && smsinfo.getDatas().size()>0){
				List<String> dataList=new ArrayList<String>();			
				for (SmsinfoView  smsview: smsinfo.getDatas()) {
					StringBuilder databuilder = new StringBuilder();
					databuilder.append(StringUtil.Null2Blank(smsview.getBanhezhanminchen()));
					databuilder.append("&^&");
					
					databuilder.append(StringUtil.Null2Blank(smsview.getShijian()));
					databuilder.append("&^&");
					
					databuilder.append(StringUtil.Null2Blank(smsview.getFasongcontent()));
					databuilder.append("&^&");
					
					databuilder.append(StringUtil.Null2Blank(smsview.getSuccessphone()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(smsview.getOwername()));
					dataList.add(databuilder.toString());
				}
				String path=request.getSession().getServletContext().getRealPath("/");
				String excelName="excel/"+System.currentTimeMillis()+".xls";
				File file=new File(path+"excel");
				if(!file.exists()){
					file.mkdir();
				}				
				
				HntExcelUtil.importsmsinfoExcel(path+"excel/短消息.xls", path+excelName, 
						StringUtil.Null2Blank(bhzname), startTime+"～"+endTime, dataList);
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
	
	@Action("smsinfoalllist")
	 public String smsinfoallList(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = systemService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = systemService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		bhzlistmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist = systemService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			bhzlistmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0) {
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		setSmsinfo(smsService.getAll(startTime, endTime, biaoduan, xiangmubu, shebeibianhao,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		return SUCCESS; 
	 }
	
	@Action("lqsmstongji")
	public String lqsmstongji() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if (null == startTime && null == endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		setEndTime(sdf.format(day.getTime()));
    	day.add(Calendar.MONTH, -1);
    	setStartTime(sdf.format(day.getTime()));
		}
    	setSmstjlist(smsService.lqsmstongji(startTime, endTime, shebeibianhao));
		
		bhzlistmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist = systemService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			bhzlistmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		return SUCCESS;
	}

	@Action("smstongji")
	 public String smstongji(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = systemService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = systemService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		bhzlistmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist =systemService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			bhzlistmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		if (null == startTime && null == endTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar day=Calendar.getInstance(); 
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
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& null != smstjlist && smstjlist.size()>0){
				List<String> dataList=new ArrayList<String>();			
				for (LiqingView  hview : smstjlist) {
					StringBuilder databuilder = new StringBuilder();
					databuilder.append(StringUtil.Null2Blank(hview.getBanhezhanminchen()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hview.getBeizhu()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hview.getChangliang()));
					databuilder.append("&^&");					
					databuilder.append(StringUtil.Null2Blank(hview.getPangshu()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getAmbegin()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getAmend()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjysb()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjg1()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjg2()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjg3()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjg4()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjg5()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjg6()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjg7()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjf1()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjf2()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjlq()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjtjj()));
					dataList.add(databuilder.toString());
				}
				String path=request.getSession().getServletContext().getRealPath("/");
				String excelName="excel/"+System.currentTimeMillis()+".xls";
				File file=new File(path+"excel");
				if(!file.exists()){
					file.mkdir();
				}				
				
				HntExcelUtil.importsmstjExcel(path+"excel/预警统计.xls", path+excelName,StringUtil.Null2Blank(bhzname), startTime+"～"+endTime, dataList);
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
	
	@Action("swsmstongji")
	public String swsmstongji() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = systemService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = systemService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}		
		bhzlistmap = new LinkedHashMap<String, String>();
		List<TopRealMaxShuiwenxixxView> toplist = systemService.limitswlist(request, biaoduan, xiangmubu);
		for (TopRealMaxShuiwenxixxView topsw : toplist) {
			bhzlistmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
		}
		
		if (null == startTime && null == endTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar day=Calendar.getInstance(); 
			setEndTime(sdf.format(day.getTime()));
	    	day.add(Calendar.MONTH, -1);
	    	setStartTime(sdf.format(day.getTime()));
		}
		ShuiwenziduancfgView swfield=queryService.getSwfield(shebeibianhao);
		this.setSwField(swfield);
		setSwsmstjlist(queryService.swsmstongji(startTime, endTime, biaoduan, xiangmubu, shebeibianhao,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),fzlx));
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& null != swsmstjlist && swsmstjlist.size()>0){
				List<String> dataList=new ArrayList<String>();			
				for (ShuiwenxixxView  hview : swsmstjlist) {
					StringBuilder databuilder = new StringBuilder();
					databuilder.append(StringUtil.Null2Blank(hview.getBanhezhanminchen()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hview.getBeizhu()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hview.getGlchangliang()));
					databuilder.append("&^&");					
					databuilder.append(StringUtil.Null2Blank(hview.getPanshu()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getAmbegin()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getAmend()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjgl1()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjgl2()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjgl3()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjgl4()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjgl5()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjfl1()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hview.getSjfl2()));
					databuilder.append("&^&");
					dataList.add(databuilder.toString());
				}
				String path=request.getSession().getServletContext().getRealPath("/");
				String excelName="excel/"+System.currentTimeMillis()+".xls";
				File file=new File(path+"excel");
				if(!file.exists()){
					file.mkdir();
				}				
				
				HntExcelUtil.importswsmstjExcel(path+"excel/预警统计.xls", path+excelName,StringUtil.Null2Blank(bhzname), startTime+"～"+endTime, dataList);
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

	public int getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}

	public SmsinfoPageMode getSmsinfo() {
		return smsinfo;
	}

	public void setSmsinfo(SmsinfoPageMode smsinfo) {
		this.smsinfo = smsinfo;
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

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
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

	public Map<String, String> getBhzlistmap() {
		return bhzlistmap;
	}

	public void setBhzlistmap(Map<String, String> bhzlistmap) {
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

	public List<LiqingView> getSmstjlist() {
		return smstjlist;
	}

	public void setSmstjlist(List<LiqingView> smstjlist) {
		this.smstjlist = smstjlist;
	}

	public LiqingziduancfgView getLqzdcfg() {
		return lqzdcfg;
	}

	public void setLqzdcfg(LiqingziduancfgView lqzdcfg) {
		this.lqzdcfg = lqzdcfg;
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

	public ShuiwenziduancfgView getSwField() {
		return swField;
	}

	public void setSwField(ShuiwenziduancfgView swField) {
		this.swField = swField;
	}

	public List<ShuiwenxixxView> getSwsmstjlist() {
		return swsmstjlist;
	}

	public void setSwsmstjlist(List<ShuiwenxixxView> swsmstjlist) {
		this.swsmstjlist = swsmstjlist;
	}


	

}
