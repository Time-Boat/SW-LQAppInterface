package com.mss.shtoone.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class LqQueryjieguoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5565938971430219686L;

	@Autowired
	private QueryService queryService;

	@Autowired
	private SystemService sysService;

	private GenericPageMode liqings;

	private LiqingziduancfgView lqbhzisShow;

	private LiqingziduancfgView lqbhzField;


	private String startTime;
	private String endTime;
	
	private Integer biaoduan;
	private Integer xiangmubu;

	
	private String bhzname;

	private int maxPageItems;
	private Integer pageNo;	

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

	private String shebeibianhao;


	
	public String excelName;


	public String getExcelName() {
		return excelName;
	}



	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	private Map<String, String> listmap;
	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;


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
	
	@Action("lqjieguolist")
	public String lqjieguolist() {
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


		String startTimeOne = request.getParameter("startTime");
		String endTimeOne = request.getParameter("endTime");
		setStartTime(startTimeOne);
		setEndTime(endTimeOne);
		setLiqings(queryService.lqviewjieguolist(shebeibianhao, 
				startTimeOne, endTimeOne, biaoduan,xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request), pageNo, maxPageItems));
		LiqingziduancfgView hbfield = queryService.getlqcfgfield(shebeibianhao);
		LiqingziduancfgView hbisshow = queryService.getlqcfgisShow(shebeibianhao);		
		setLqbhzField(hbfield);
		setLqbhzisShow(hbisshow);
		
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
				&& (null != hbfield && null != hbisshow)){
			List<String> dataList=new ArrayList<String>();
			List<String> headerList = new ArrayList<String>();
			headerList.add("拌和站名称");
		
			String path=request.getSession().getServletContext().getRealPath("/");
			String excelName="excel/"+System.currentTimeMillis()+".xls";
			setExcelName(excelName);
			File file=new File(path+"excel");
			if(!file.exists()){
				file.mkdir();
			}
			String[] header = new String[headerList.size()];
			int j = 0;
			for (Iterator iterator = headerList.iterator(); iterator
					.hasNext();) {
				header[j] = StringUtil.Null2Blank((String)iterator.next());
				j++;
			}
			HntExcelUtil.importjieguoListExcel(path+"excel/预警统计.xls", path+excelName,
					StringUtil.Null2Blank(bhzname), header, dataList);
			try {
				response.reset();
				response.setContentType("bin");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + excelName + "\"");
				java.io.FileInputStream in = new java.io.FileInputStream(path
						+ excelName);
				PrintWriter out = response.getWriter();
				int k;
				while ((k = in.read()) != -1) {
					out.write(k);
				}
				in.close();
		        out.flush();
		        out.close();
			} catch (Exception e) {
			}
		}
		
		return SUCCESS;
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

	public GenericPageMode getLiqings() {
		return liqings;
	}

	public void setLiqings(GenericPageMode liqings) {
		this.liqings = liqings;
	}

	public LiqingziduancfgView getLqbhzisShow() {
		return lqbhzisShow;
	}

	public void setLqbhzisShow(LiqingziduancfgView lqbhzisShow) {
		this.lqbhzisShow = lqbhzisShow;
	}

	public LiqingziduancfgView getLqbhzField() {
		return lqbhzField;
	}

	public void setLqbhzField(LiqingziduancfgView lqbhzField) {
		this.lqbhzField = lqbhzField;
	}

}
