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
public class LqsclhsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2569196021130050062L;


	@Autowired
	private QueryService queryService;

	
	@Autowired
	private SystemService sysService;

	private List<LiqingView> lqviews;
	
	private List<LiqingView> lqmanualviews;

	private LiqingziduancfgView lqbhzField;

	private String startTime;
	private String endTime;
	
	private String peifan;
	
	

	public String getPeifan() {
		return peifan;
	}

	public void setPeifan(String peifan) {
		this.peifan = peifan;
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
	
	private String bhzname;
	
	private Integer biaoduan;
	private Integer xiangmubu;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	private Map<String, String> listmap;

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


	@Action("lqsclhs")
	public String lqsclhs() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		if (null == startTime && null == endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		setEndTime(sdf.format(day.getTime()));
    	day.add(Calendar.DATE, -3);
    	setStartTime(sdf.format(day.getTime()));
		}
    	setLqviews(queryService.lqsclhslist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,
    			StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),peifan));
		
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
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")){
			List<String> dataList = new ArrayList<String>();
				
				String path = request.getSession().getServletContext()
						.getRealPath("/");
				String excelName = "excel/" + System.currentTimeMillis()
						+ ".xls";
				setExcelName(excelName);
				File file = new File(path + "excel");
				if (!file.exists()) {
					file.mkdir();
				}				
				HntExcelUtil.importshengchanlExcel(path+"excel/生产量统计.xls", path + excelName, 
						StringUtil.Null2Blank(bhzname),startTime+"～"+endTime, dataList);
				try {
					response.reset();
					response.setContentType("bin");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + excelName + "\"");
					java.io.FileInputStream in = new java.io.FileInputStream(
							path + excelName);
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
	
	@Action("lqscsjjc")
	public String lqscsjjc() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		if (null == startTime && null == endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		setEndTime(sdf.format(day.getTime()));
    	day.add(Calendar.DATE, -1);
    	setStartTime(sdf.format(day.getTime()));
		}
    	setLqviews(queryService.lqscsjjcphb(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,
    			StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),peifan));
    	
    	setLqmanualviews(queryService.lqscsjjcmanualphb(startTime, endTime, shebeibianhao,biaoduan,xiangmubu,
    			StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),peifan));
		
		listmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			listmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		if(null==chaxun && null!=xiazai && xiazai.equals("123")){
				List<String> dataList=new ArrayList<String>();	
				if (null != lqviews) {
					for (LiqingView  lqview: lqviews) {
						StringBuilder databuilder = new StringBuilder();						
						databuilder.append(StringUtil.Null2Blank(lqview.getChangliang()));
						databuilder.append("&^&");						
						databuilder.append(StringUtil.Null2Blank(lqview.getLlysb()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqview.getSjg6()));
						databuilder.append("&^&");	
						databuilder.append(StringUtil.Null2Blank(lqview.getSjg5()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqview.getSjg4()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqview.getSjg3()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqview.getSjg2()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqview.getSjg1()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqview.getSjf1()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqview.getSjlq()));
						databuilder.append("&^&");			
						databuilder.append(StringUtil.Null2Blank(lqview.getSjtjj()));	
						dataList.add(databuilder.toString());
					}
				}
				if (null != lqmanualviews) {
					for (LiqingView  lqmanualview: lqmanualviews) {
						StringBuilder databuilder = new StringBuilder();	
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getChangliang()));
						databuilder.append("&^&");						
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getLlysb()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getSjg6()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getSjg5()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getSjg4()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getSjg3()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getSjg2()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getSjg1()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getSjf1()));	
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getSjlq()));
						databuilder.append("&^&");
						databuilder.append(StringUtil.Null2Blank(lqmanualview.getSjtjj()));	
						dataList.add(databuilder.toString());
					}
				}
				String path=request.getSession().getServletContext().getRealPath("/");
				String excelName="excel/"+System.currentTimeMillis()+".xls";
				File file=new File(path+"excel");
				if(!file.exists()){
					file.mkdir();
				}				
				
				HntExcelUtil.importlqscsjjcExcel(path+"excel/沥青混合料拌和楼生产数据总量检查表.xls", path+excelName, 
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



	public String getBhzname() {
		return bhzname;
	}

	public void setBhzname(String bhzname) {
		this.bhzname = bhzname;
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

	public List<LiqingView> getLqviews() {
		return lqviews;
	}

	public void setLqviews(List<LiqingView> lqviews) {
		this.lqviews = lqviews;
	}

	public LiqingziduancfgView getLqbhzField() {
		return lqbhzField;
	}

	public void setLqbhzField(LiqingziduancfgView lqbhzField) {
		this.lqbhzField = lqbhzField;
	}

	public List<LiqingView> getLqmanualviews() {
		return lqmanualviews;
	}

	public void setLqmanualviews(List<LiqingView> lqmanualviews) {
		this.lqmanualviews = lqmanualviews;
	}

	
}
