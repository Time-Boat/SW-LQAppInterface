package com.mss.shtoone.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShuiwenclDaily;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.ShuiwenclDailyService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class ShuiwenclDailyAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8082979929423793371L;

	@Autowired
	private ShuiwenclDailyService swdailyService;
	
	@Autowired
	private SystemService sysService;
	
	private List<ShuiwenclDaily> list = new ArrayList<ShuiwenclDaily>();
	private ShuiwenclDaily swldaily;
	private Map<String, String> listmap;
	private Integer biaoduan;
	private Integer xiangmubu;
	private int maxPageItems;
	private Integer pageNo;
	private GenericPageMode swdailypgs;
	private String shebeibianhao;	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private String startTime;
	private String endTime;
	
	private int dailyid;

	public ShuiwenclDaily getSwldaily() {
		return swldaily;
	}

	public void setSwldaily(ShuiwenclDaily swldaily) {
		this.swldaily = swldaily;
	}

	public GenericPageMode getSwdailypgs() {
		return swdailypgs;
	}

	public void setSwdailypgs(GenericPageMode swdailypgs) {
		this.swdailypgs = swdailypgs;
	}

	public List<ShuiwenclDaily> getList() {
		return list;
	}

	public void setList(List<ShuiwenclDaily> list) {
		this.list = list;
	}

	@Action("swdailylist")
	 public String swdailylist(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
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
		/*
		listmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			listmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		*/
		listmap = new LinkedHashMap<String, String>();
		List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request, biaoduan, xiangmubu);
		for (TopRealMaxShuiwenxixxView topsw : toplist) {
			listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
		}
		
		setSwdailypgs(swdailyService.limitdailylist(shebeibianhao, 
				startTime, endTime,  biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		return SUCCESS; 
	 }
	
	@Action("swdailyinput")
	 public String lqdailyinput(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		listmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			listmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		if(dailyid>0){
			setSwldaily(swdailyService.getBeanById(dailyid));
		}
		return SUCCESS; 
	 }
	
	@Action(value = "swdailyAdd", results = @Result(name = "swdailylist", type = "redirect", location = "swdailylist?pid=5&"))
	 public String swdailyAdd(){		
		swdailyService.saveOrUpdate(swldaily);
		return "swdailylist"; 
	 }
	
	@Action(value = "swdailyDel", results = @Result(name = "swdailylist", type = "redirect", location = "swdailylist?pid=5&"))
	 public String swdailyDel(){	
		swdailyService.del(dailyid);
		return "swdailylist"; 
	 }

	
	@Action("oneswdailycl")
	public String oneswdailycl() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String sbbh = request.getParameter("sbbh");
			String sttime = request.getParameter("sttime");			
	        if(StringUtil.Null2Blank(sbbh).length() > 0 && StringUtil.Null2Blank(sttime).length() > 0) {
	        	out.print(swdailyService.oneswdailycl(sbbh, sttime));
    			out.flush();
		        out.close();
	        }
	        
		} catch (IOException e) {
		}
		return null;
	}





	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
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

	public int getDailyid() {
		return dailyid;
	}

	public void setDailyid(int dailyid) {
		this.dailyid = dailyid;
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
}
