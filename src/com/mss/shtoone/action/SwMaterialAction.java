package com.mss.shtoone.action;

import java.text.SimpleDateFormat;
import java.io.File;
import java.io.PrintWriter;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.Shuiwenxixxdanjia;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.WuchaIsShowData;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.QuerywuchaService;
import com.mss.shtoone.service.SwViewService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class SwMaterialAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8250938045832375516L;

	@Autowired
	private QueryService queryService;

	@Autowired
	private GetdataService getdataService;
	
	@Autowired
	private QuerywuchaService querywcService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private SwViewService swService;
	
	private String startTime;
	private String endTime;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Integer biaoduan;
	private Integer xiangmubu;
	private Integer[] clselect;
	private List<WuchaIsShowData> cllist;
	private ShuiwenziduancfgView swField;
	private ShuiwenphbView swmanualview;
	private String shebeibianhao;
	private String xiangmubuminchen;
	public String excelName;
	private Map<String, String> listmap;	
	private String testbhz;
	private String bhztext;
	private Shuiwenxixxdanjia swdanjia;
	public Shuiwenxixxdanjia getSwdanjia() {
		return swdanjia;
	}

	public void setSwdanjia(Shuiwenxixxdanjia swdanjia) {
		this.swdanjia = swdanjia;
	}

	public ShuiwenphbView getSwmanualview() {
		return swmanualview;
	}

	public void setSwmanualview(ShuiwenphbView swmanualview) {
		this.swmanualview = swmanualview;
	}

	public ShuiwenziduancfgView getSwField() {
		return swField;
	}

	public void setSwField(ShuiwenziduancfgView swField) {
		this.swField = swField;
	}

	public LiqingziduancfgView getLiqingField() {
		return liqingField;
	}

	public void setLiqingField(LiqingziduancfgView liqingField) {
		this.liqingField = liqingField;
	}

	private LiqingziduancfgView liqingField;
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


	@Action("swmaterial")
	public String swmaterial() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		if (null == startTime && null == endTime) {
			Calendar day=Calendar.getInstance(); 
			setEndTime(sdf.format(day.getTime()));
			day.add(Calendar.MONTH, -1);
			setStartTime(sdf.format(day.getTime()));
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
		ShuiwenziduancfgView swfield=queryService.getSwfield(shebeibianhao);
		this.setSwField(swfield);
				
		if (null == request.getParameter("frommenu")) {
			setSwmanualview(swService.swmateriallist(startTime, endTime, 
					testbhz,biaoduan,xiangmubu,StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request)));
			
			setSwdanjia(queryService.SwcalTotaljiage(swmanualview, shebeibianhao));
		}
		
		if (null != swmanualview && swField!=null) {
    		request.setAttribute("strXML", queryService.getswmaterialhsXml(swmanualview,swField));
    	} else {
    		request.setAttribute("strXML","");
    	}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123") && null != swField){
			List<String> dataList = new ArrayList<String>();
			List<String> headerList = new ArrayList<String>();
			if (null != swField) {
				String path = request.getSession().getServletContext().getRealPath("/");
				String excelName = "excel/" + System.currentTimeMillis()+ ".xls";
				setExcelName(excelName);
				File file = new File(path + "excel");
				if (!file.exists()) {
					file.mkdir();
				}
				
				String[] header = new String[headerList.size()];
				int j = 0;
				for (Iterator iterator = headerList.iterator(); iterator.hasNext();) {
					header[j] = StringUtil.Null2Blank((String)iterator.next());
					j++;
				}
		
					HntExcelUtil.importListSumExcel(path+"excel/材料消耗表.xls", path + excelName, 
							StringUtil.Null2Blank(bhztext),startTime+"～"+endTime,
							header, dataList);
				
				try {
					response.reset();
					response.setContentType("bin");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + excelName + "\"");
					java.io.FileInputStream in = new java.io.FileInputStream(
							path + excelName);
					response.flushBuffer();
					PrintWriter out = response.getWriter();
					int x;
					while ((x = in.read()) != -1) {
						out.write(x);
					}
					in.close();
			        out.flush();
			        out.close();
				} catch (Exception e) {
				}
			}
		}

		return SUCCESS;
	}
	
	public String getXiangmubuminchen() {
		return xiangmubuminchen;
	}

	public void setXiangmubuminchen(String xiangmubuminchen) {
		this.xiangmubuminchen = xiangmubuminchen;
	}

	public String getTestbhz() {
		return testbhz;
	}

	public void setTestbhz(String testbhz) {
		this.testbhz = testbhz;
	}

	
	public String getBhztext() {
		return bhztext;
	}

	public void setBhztext(String bhztext) {
		this.bhztext = bhztext;
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
}
