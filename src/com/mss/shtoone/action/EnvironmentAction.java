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
import com.mss.shtoone.domain.EnvironmentView;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.TopRealEnvironmentView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.LqExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Namespace("/query")
public class EnvironmentAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Autowired
	private SystemService sysService;
	@Autowired
	private QueryService queryService;
	
	private Map<Integer, String> biaoduanlistmap;//标段集合
	private Map<Integer, String> xmblistmap;
	private Map<String, String> shebeilistmap;	
	private Integer biaoduan;//标段编号
	private Integer xiangmubu;
	private Integer maxPageItems;
	private Integer pageNo;	
	private String shebeibianhao;
	private String startTime;
	private String endTime;
	private String excelName;
	private GenericPageMode environmentpageMode;
	public GenericPageMode getEnvironmentpageMode() {
		return environmentpageMode;
	}

	public void setEnvironmentpageMode(GenericPageMode environmentpageMode) {
		this.environmentpageMode = environmentpageMode;
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

	public Map<String, String> getShebeilistmap() {
		return shebeilistmap;
	}

	public void setShebeilistmap(Map<String, String> shebeilistmap) {
		this.shebeilistmap = shebeilistmap;
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

	public Integer getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(Integer maxPageItems) {
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

	@Action("environmentlist")
	public String environmentlist(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request,biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		shebeilistmap = new LinkedHashMap<String, String>();
		List<TopRealEnvironmentView> toplist = sysService.limitEnvironmentlist(request, biaoduan, xiangmubu);
		for (TopRealEnvironmentView topView : toplist) {
			shebeilistmap.put(topView.getFensuno(), topView.getBanhezhanminchen());
		}	
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
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
		this.setEnvironmentpageMode(queryService.environmentView(shebeibianhao, startTime, endTime, biaoduan, 
				xiangmubu,StringUtil.getQueryFieldNameByRequest(request),StringUtil.getBiaoshiId(request),pageNo,maxPageItems));
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		if(null==chaxun && null!=xiazai && xiazai.equals("123")){
			List<String> dataList = new ArrayList<String>();
			if (null !=environmentpageMode.getDatas() && environmentpageMode.getDatas().size() > 0) {
				for (Object  obj: environmentpageMode.getDatas()) {
					EnvironmentView tanputemp=(EnvironmentView)obj;
					StringBuilder databuilder = new StringBuilder();					
					databuilder.append(tanputemp.getBanhezhanminchen());
					databuilder.append("&^&");
					databuilder.append(tanputemp.getFensu());
					databuilder.append("&^&");
					databuilder.append(tanputemp.getWendu());
					databuilder.append("&^&");
					databuilder.append(tanputemp.getShidu());
					databuilder.append("&^&");
					databuilder.append(tanputemp.getFensushijian());
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
				LqExcelUtil.importEnvironmentExcel(path+"excel/环境参数.xls", path + excelName, 
						startTime+"~"+endTime,dataList);
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
	
	@Action("environmentchangebhz")
	public String environmentchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
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
	        List<TopRealEnvironmentView> toplist = sysService.limitEnvironmentlist(request, bd, null);			
	        StringBuilder outstr = new StringBuilder();
	    	for (TopRealEnvironmentView speeddata : toplist) {
	    		outstr.append(speeddata.getFensuno());
	    		outstr.append(",");
	    		outstr.append(speeddata.getBanhezhanminchen());
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
	
	@Action("environmentxmbchangebhz")
	public String environmentxmbchangebhz() {
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
	        List<TopRealEnvironmentView> toplist = sysService.limitEnvironmentlist(request, null, xmb);			
        	StringBuilder outstr = new StringBuilder();
    		for (TopRealEnvironmentView speeddata : toplist) {
    			outstr.append(speeddata.getFensuno());
    			outstr.append(",");
    			outstr.append(speeddata.getBanhezhanminchen());
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
}
