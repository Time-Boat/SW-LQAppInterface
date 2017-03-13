package com.mss.shtoone.action;

import java.io.File;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class LqcnfxAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2213602947641365355L;

	@Autowired
	private QueryService queryService;

	@Autowired
	private GetdataService getdataService;
	
	@Autowired
	private SystemService sysService;

	private List<LiqingView> lqviews;

	private LiqingziduancfgView lqziduanField;
	
	private Integer cnfxlx=5;

	private String startTime;
	private String endTime;
	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Integer biaoduan;
	private Integer xiangmubu;

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
	private String bhzname;
	
	public String excelName;

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




	@Action("lqcnfx")
	public String lqcnfx() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
    	
		if (null == request.getParameter("frommenu")) {
			setLqviews(queryService.lqcnfxlist(startTime, endTime, shebeibianhao,biaoduan,xiangmubu, 
	    			cnfxlx,StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request)));
		}
    	
    	if (null != lqviews && lqviews.size() > 0) {
    		request.setAttribute("strXML", getdataService.getLqAnalysisColumnXml(lqviews, cnfxlx));
    	} else {
    		request.setAttribute("strXML","");
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
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")){
			List<String> dataList = new ArrayList<String>();
			if (null != lqviews && lqviews.size() > 0) {
				for (LiqingView  lq: lqviews) {
					StringBuilder databuilder = new StringBuilder();
					databuilder.append(lq.getXa() + "&^&");
					databuilder.append(lq.getXb() + "&^&");
					databuilder.append(lq.getChangliang() + "&^&");
					databuilder.append(lq.getPangshu()+ "&^&");
					databuilder.append(lq.getAmbegin()+ "&^&");
					databuilder.append(lq.getAmend()+ "&^&");					
					databuilder.append(lq.getPmbegin()+ "&^&");
					databuilder.append(lq.getPmend()+ "&^&");
					databuilder.append(lq.getBeizhu()+ "&^&");
					databuilder.append(lq.getBiaoshi());
					dataList.add(databuilder.toString());
				}
				}
				String path = request.getSession().getServletContext()
						.getRealPath("/");
				String excelName = "excel/" + System.currentTimeMillis()
						+ ".xls";
				setExcelName(excelName);
				File file = new File(path + "excel");
				if (!file.exists()) {
					file.mkdir();
				}	
				
				String header = "月份";
				switch (cnfxlx) {
				case 1:
					header = "季度";
					break;
				case 2:
					header = "月份";
					break;
				case 3:
					header = "周";
					break;
				default:
					header = "月份";
					break;
				}
				HntExcelUtil.importcnfxExcel(path+"excel/产能分析.xls", path + excelName, 
						bhzname,startTime+"～"+endTime,header,dataList);
				try {
					response.reset();
					response.setContentType("bin");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + excelName + "\"");
					java.io.FileInputStream in = new java.io.FileInputStream(
							path + excelName);
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
	
	@Action("lqcnfxdetail")
	public String lqcnfxdetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String myVar1 = request.getParameter("myVar1");  
			String myVar2 = request.getParameter("myVar2"); 
			String sbbh = request.getParameter("sbbh"); 
			String stt = request.getParameter("stt"); 
			String edt = request.getParameter("edt"); 
			String lx = request.getParameter("lx");
			String biaod = request.getParameter("biaod");
			String xiangmb = request.getParameter("xiangmb");
			int ilx = 1;
			if (StringUtil.Null2Blank(lx).length() > 0) {
				ilx = Integer.parseInt(lx);
			}
	        if(myVar1 != null && !"".equals(myVar1) && myVar2 != null && !"".equals(myVar2)) {
	            out.print(getdataService.getLqShijiAnalysisPieXml
	            		(queryService.lqcnfxdetail(stt, edt, sbbh,biaod,xiangmb, myVar1, myVar2, 
	            				ilx,StringUtil.getQueryFieldNameByRequest(request), 
	            				StringUtil.getBiaoshiId(request)),
	            				queryService.getlqcfgfield(sbbh)));
	        } 
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}	
	
	@Action("lqcnfxlilundetail")
	public String lqcnfxlilundetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String myVar1 = request.getParameter("myVar1");  
			String myVar2 = request.getParameter("myVar2"); 
			String sbbh = request.getParameter("sbbh"); 
			String stt = request.getParameter("stt"); 
			String edt = request.getParameter("edt"); 
			String lx = request.getParameter("lx");
			String biaod = request.getParameter("biaod");
			String xiangmb = request.getParameter("xiangmb");
			int ilx = 1;
			if (StringUtil.Null2Blank(lx).length() > 0) {
				ilx = Integer.parseInt(lx);
			}
	        if(myVar1 != null && !"".equals(myVar1) && myVar2 != null && !"".equals(myVar2)) {
	            out.print(getdataService.getLqLilunAnalysisPieXml
	            		(queryService.lqcnfxlilundetail(stt, edt, sbbh, biaod,xiangmb,
	            				myVar1, myVar2, ilx,StringUtil.getQueryFieldNameByRequest(request), 
	            				StringUtil.getBiaoshiId(request))));
	        } 
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}	
	

	public Integer getCnfxlx() {
		return cnfxlx;
	}

	public void setCnfxlx(Integer cnfxlx) {
		this.cnfxlx = cnfxlx;
	}

	public String getBhzname() {
		return bhzname;
	}

	public void setBhzname(String bhzname) {
		this.bhzname = bhzname;
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

	public List<LiqingView> getLqviews() {
		return lqviews;
	}

	public void setLqviews(List<LiqingView> lqviews) {
		this.lqviews = lqviews;
	}

	public LiqingziduancfgView getLqziduanField() {
		return lqziduanField;
	}

	public void setLqziduanField(LiqingziduancfgView lqziduanField) {
		this.lqziduanField = lqziduanField;
	}
}
