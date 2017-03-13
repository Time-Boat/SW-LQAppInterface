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
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.WuchaIsShowData;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.QuerywuchaService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class LqQuerywuchaAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4325086943278796699L;

	@Autowired
	private QuerywuchaService queryService;

	@Autowired
	private QueryService qService;
	
	@Autowired
	private SystemService sysService;

	private GenericPageMode liqings;
	private GenericPageMode liqingmanualphbs;
	private LiqingziduancfgView lqbhzisShow;
	private LiqingziduancfgView lqbhzField;
	private String startTime;
	private String endTime;
	private Integer biaoduan;
	private Integer xiangmubu;
	private String bhzname;
	private int maxPageItems;
	private Integer pageNo;	
	private Integer[] wuchaselect;
	private List<WuchaIsShowData> wuchalist;
	private Map<String,String> peifanmap;
	
	public GenericPageMode getLiqingmanualphbs() {
		return liqingmanualphbs;
	}

	public void setLiqingmanualphbs(GenericPageMode liqingmanualphbs) {
		this.liqingmanualphbs = liqingmanualphbs;
	}

	public Map<String, String> getPeifanmap() {
		return peifanmap;
	}

	public void setPeifanmap(Map<String, String> peifanmap) {
		this.peifanmap = peifanmap;
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

	private String shebeibianhao;
	
	public String excelName;
	
	private String peifan;

    
	public String getPeifan() {
		return peifan;
	}

	public void setPeifan(String peifan) {
		this.peifan = peifan;
	}

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

	
	@Action("lqwuchalist")
	public String lqwuchalist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.parseInt(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
		    setXiangmubu(Integer.parseInt(request.getParameter("xiangmubu")));
		}	
		if (StringUtil.Null2Blank(request.getParameter("startTime")).length()>0) {
		    setStartTime(request.getParameter("startTime"));
		}
		if (StringUtil.Null2Blank(request.getParameter("endTime")).length()>0) {
		    setEndTime(request.getParameter("endTime"));
		}
		if (null != wuchaselect && wuchaselect.length > 0) {
			Liqingziduancfg lqcfgisshow;
			lqcfgisshow = queryService.getlqcfg(shebeibianhao);
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
			for (int h = 0; h < wuchaselect.length; h++) {
				selectlist.add(wuchaselect[h]);
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
			queryService.saveLqcfg(lqcfgisshow);
		}

		setLiqingmanualphbs(queryService.lqmanualphbviewwuchalist(shebeibianhao, 
				startTime, endTime, biaoduan,xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request), pageNo, maxPageItems,peifan));
		
		LiqingziduancfgView lqfield = queryService.getlqcfgfield(shebeibianhao);
		LiqingziduancfgView lqisshow = queryService.getlqcfgisShow(shebeibianhao);	
		//这里是记录单选按钮选中的材料
		int recordcheckbox=0;
		if(lqisshow!=null){
			if(lqisshow.getSjysb().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjg1().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjg2().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjg3().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjg4().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjg5().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjg6().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjg7().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjf1().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjf2().equals("1")){
				recordcheckbox++;
			}
			if(lqisshow.getSjlq().equals("1")){
				recordcheckbox++;
			}if(lqisshow.getSjtjj().equals("1")){
				recordcheckbox++;
			}
		}
		//
		if (null == lqisshow) {
			Liqingziduancfg lqcfgisshow;
			lqcfgisshow = queryService.getlqcfg(shebeibianhao);
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
			queryService.saveLqcfg(lqcfgisshow);
			lqisshow = queryService.getlqcfgisShow(shebeibianhao);	
		}		
		wuchalist = new ArrayList<WuchaIsShowData>();
		wuchaselect = new Integer[12];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjysb());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg1());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg2());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg3());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg4());
		wuchalist.add(wd);
		
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg5());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg6());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjg7());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjf1());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjf2());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjlq());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjtjj());
		wuchalist.add(wd);	
		
		setLqbhzField(lqfield);
		setLqbhzisShow(lqisshow);
		
		if (null != liqingmanualphbs && null != liqingmanualphbs.getDatas() && liqingmanualphbs.getDatas().size() > 0) {
			request.setAttribute("strXML", queryService.getLiqingCailiaoXml(liqingmanualphbs.getDatas(), lqbhzField, lqbhzisShow));
			request.setAttribute("strXMLWucha", queryService.getLqCailiaoWuchaXml(liqingmanualphbs.getDatas(), lqbhzField, lqbhzisShow,recordcheckbox,shebeibianhao));
		} else {
			request.setAttribute("strXML", "");
			request.setAttribute("strXMLWucha", "");
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
		List peifanlist=qService.getlqxinghao();
		for(int m=0;m<peifanlist.size();m++){
			String str=(String)peifanlist.get(m);
			peifanmap.put(str,str);
		}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& (null != lqfield && null != lqisshow)){
			List<String> dataList=new ArrayList<String>();
			List<String> headerList = new ArrayList<String>();
			
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
			HntExcelUtil.importWuchaExcel(path+"excel/配合比消耗.xls", path+excelName,
					StringUtil.Null2Blank(bhzname),startTime+"～"+endTime, header, dataList);
			try {
				response.reset();
				response.setContentType("bin");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + excelName + "\"");
				java.io.FileInputStream in = new java.io.FileInputStream(path
						+ excelName);
				// response.flushBuffer();
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



	public Integer[] getWuchaselect() {
		return wuchaselect;
	}

	public void setWuchaselect(Integer[] wuchaselect) {
		this.wuchaselect = wuchaselect;
	}

	public List<WuchaIsShowData> getWuchalist() {
		return wuchalist;
	}

	public void setWuchalist(List<WuchaIsShowData> wuchalist) {
		this.wuchalist = wuchalist;
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
