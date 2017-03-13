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
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.Liqingxixxdanjia;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.WuchaIsShowData;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.QuerywuchaService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class LqMaterialAction extends ActionSupport {
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
	private LiqingphbView lqviews;
	private Liqingxixxdanjia lqdanjia;
	private LiqingziduancfgView lqbhzField;
	private String startTime;
	private String endTime;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Integer biaoduan;
	private Integer xiangmubu;
	private Integer[] clselect;
	private List<WuchaIsShowData> cllist;
	private LiqingziduancfgView liqingisShow;
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

	private String shebeibianhao;
	private String xiangmubuminchen;

	public String excelName;

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	private Map<String, String> listmap;
	
	private String testbhz;
	private String bhztext;

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


	@Action("lqmaterial")
	public String lqmaterial() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		if (null == startTime && null == endTime) {
		Calendar day=Calendar.getInstance(); 
		setEndTime(sdf.format(day.getTime()));
    	day.add(Calendar.MONTH, -1);
    	setStartTime(sdf.format(day.getTime()));
		}
		if (null != clselect && clselect.length > 0) {
			Liqingziduancfg lqcfgisshow= querywcService.getlqcfg4(shebeibianhao);
			if (null == lqcfgisshow) {
				lqcfgisshow = new Liqingziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					lqcfgisshow.setGprsbianhao(shebeibianhao);
					lqcfgisshow.setLeixin("41");
				} else {
					lqcfgisshow.setGprsbianhao("all");
					lqcfgisshow.setLeixin("40");
				}
			}
			
			List<Integer> selectlist = new ArrayList<Integer>();
			for (int h = 0; h < clselect.length; h++) {
				selectlist.add(clselect[h]);
			}
			if (selectlist.contains(1)) {
				lqcfgisshow.setSjg1("1");
			} else {
				lqcfgisshow.setSjg1("0");
			}
			if (selectlist.contains(2)) {
				lqcfgisshow.setSjg2("1");
			} else {
				lqcfgisshow.setSjg2("0");
			}
			if (selectlist.contains(3)) {
				lqcfgisshow.setSjg3("1");
			} else {
				lqcfgisshow.setSjg3("0");
			}
			if (selectlist.contains(4)) {
				lqcfgisshow.setSjg4("1");
			} else {
				lqcfgisshow.setSjg4("0");
			}
			if (selectlist.contains(5)) {
				lqcfgisshow.setSjg5("1");
			} else {
				lqcfgisshow.setSjg5("0");
			}
			if (selectlist.contains(6)) {
				lqcfgisshow.setSjg6("1");
			} else {
				lqcfgisshow.setSjg6("0");
			}
			if (selectlist.contains(7)) {
				lqcfgisshow.setSjg7("1");
			} else {
				lqcfgisshow.setSjg7("0");
			} 
			if (selectlist.contains(8)) {
				lqcfgisshow.setSjf1("1");
			} else {
				lqcfgisshow.setSjf1("0");
			}
			if (selectlist.contains(9)) {
				lqcfgisshow.setSjf2("1");
			} else {
				lqcfgisshow.setSjf2("0");
			}
			if (selectlist.contains(10)) {
				lqcfgisshow.setSjlq("1");
			} else {
				lqcfgisshow.setSjlq("0");
			}
			if (selectlist.contains(11)) {
				lqcfgisshow.setSjtjj("1");
			} else {
				lqcfgisshow.setSjtjj("0");
			}
			querywcService.saveLqcfg(lqcfgisshow);
		}
		
		LiqingziduancfgView lqfield = queryService.getlqcfgfield(shebeibianhao);
		LiqingziduancfgView lqisshow = queryService.getlqcfgisShow4(shebeibianhao);	
		if (null == lqisshow) {
			Liqingziduancfg lqcfgisshow;
			lqcfgisshow = querywcService.getlqcfg(shebeibianhao);
			if (null == lqcfgisshow) {
				lqcfgisshow = new Liqingziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					lqcfgisshow.setGprsbianhao(shebeibianhao);
					lqcfgisshow.setLeixin("41");
				} else {
					lqcfgisshow.setGprsbianhao("all");
					lqcfgisshow.setLeixin("40");
				}
			}
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
			querywcService.saveLqcfg(lqcfgisshow);
			lqisshow = queryService.getlqcfgisShow(shebeibianhao);	
		}		
		cllist = new ArrayList<WuchaIsShowData>();
		clselect = new Integer[11];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
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
	
		setLiqingField(lqfield);
		setLiqingisShow(lqisshow);
		
		if (null == request.getParameter("frommenu")) {
		setLqviews(queryService.lqmateriallist(startTime, endTime, 
				testbhz,biaoduan,xiangmubu,StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)));
		}
		setLqdanjia(queryService.calTotaljiage(lqviews, shebeibianhao));		
    	if (null != lqviews) {
    		request.setAttribute("strXML", getdataService.getlqmaterialhsXml(lqviews,liqingField, liqingisShow));
    	} else {
    		request.setAttribute("strXML","");
    	}
		setLqbhzField(lqfield);
		

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
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123") && null != lqfield){
			List<String> dataList = new ArrayList<String>();
			List<String> headerList = new ArrayList<String>();
			if (null != lqviews) {
				String path = request.getSession().getServletContext()
						.getRealPath("/");
				String excelName = "excel/" + System.currentTimeMillis()
						+ ".xls";
				setExcelName(excelName);
				File file = new File(path + "excel");
				if (!file.exists()) {
					file.mkdir();
				}
				
				String[] header = new String[headerList.size()];
				int j = 0;
				for (Iterator iterator = headerList.iterator(); iterator
						.hasNext();) {
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
					// response.flushBuffer();
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



	public LiqingziduancfgView getLqbhzField() {
		return lqbhzField;
	}

	public void setLqbhzField(LiqingziduancfgView lqbhzField) {
		this.lqbhzField = lqbhzField;
	}

	public LiqingphbView getLqviews() {
		return lqviews;
	}

	public void setLqviews(LiqingphbView lqviews) {
		this.lqviews = lqviews;
	}

	public Liqingxixxdanjia getLqdanjia() {
		return lqdanjia;
	}

	public void setLqdanjia(Liqingxixxdanjia lqdanjia) {
		this.lqdanjia = lqdanjia;
	}
}
