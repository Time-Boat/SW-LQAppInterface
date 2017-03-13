package com.mss.shtoone.action;

import java.io.File;
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
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
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
public class SwcnfxAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private GetdataService getdataService;
	
	private Integer biaoduan;
	private Integer xiangmubu;
	private String shebeibianhao;
	private String startTime;
	private String endTime;
	private Integer pageNo;
	private Integer maxPageItems=15;
	private Integer cnfxlx=4;
	private Map<String, String> listmap;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private List<ShuiwenxixxView> swcnfxlist;
	public String excelName;
	private String bhzname;
	public String getBhzname() {
		return bhzname;
	}
	public void setBhzname(String bhzname) {
		this.bhzname = bhzname;
	}
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public List<ShuiwenxixxView> getSwcnfxlist() {
		return swcnfxlist;
	}
	public void setSwcnfxlist(List<ShuiwenxixxView> swcnfxlist) {
		this.swcnfxlist = swcnfxlist;
	}
	public Integer getCnfxlx() {
		return cnfxlx;
	}
	public void setCnfxlx(Integer cnfxlx) {
		this.cnfxlx = cnfxlx;
	}
	public Map<String, String> getListmap() {
		return listmap;
	}
	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
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
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getMaxPageItems() {
		return maxPageItems;
	}
	public void setMaxPageItems(Integer maxPageItems) {
		this.maxPageItems = maxPageItems;
	}

	@Action("swcnfx")
	public String swcnfx(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
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
			setStartTime(request.getParameter("startTime"));
		}
		if(StringUtil.Null2Blank(request.getParameter("endTime")).length()>0){
			setEndTime(request.getParameter("endTime"));
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
		
		this.setSwcnfxlist(queryService.swcnfxlist(startTime, endTime, shebeibianhao, biaoduan, xiangmubu, cnfxlx,
				StringUtil.getQueryFieldNameByRequest(request), StringUtil.getBiaoshiId(request)));
		if(this.getSwcnfxlist()!=null && this.getSwcnfxlist().size()>0){
			request.setAttribute("cnfxXML",  getdataService.getSwAnalysisColumnXml(this.getSwcnfxlist(), cnfxlx));
		}else{
			request.setAttribute("cnfxXML","");
		}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")){
			List<String> dataList = new ArrayList<String>();
			if (this.getSwcnfxlist()!=null && this.getSwcnfxlist().size()>0) {
				for (ShuiwenxixxView  lq: this.getSwcnfxlist()) {
					StringBuilder databuilder = new StringBuilder();
					databuilder.append(lq.getSmstype() + "&^&");
					databuilder.append(lq.getSendtype() + "&^&");
					databuilder.append(String.format("%1$.2f",Double.valueOf(lq.getGlchangliang())/1000) + "&^&");
					databuilder.append(lq.getPanshu()+ "&^&");
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
					header = "日";
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
}
