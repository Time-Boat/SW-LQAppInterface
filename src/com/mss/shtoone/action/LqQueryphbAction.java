package com.mss.shtoone.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.Liqingxixx;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
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
public class LqQueryphbAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5938798257809625260L;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private GetdataService dataService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private QuerywuchaService querywcService;

	private GenericPageMode liqingphb;
	
	private GenericPageMode liqingmanualphb;

	private LiqingziduancfgView liqingisShow;

	private LiqingziduancfgView liqingField;
	
	private Liqingxixx lqxixx;
	
	private Integer bsid;
	
	private Integer cllx=0;  //处理结果
	private String bianhao;
	
	public Integer getCllx() {
		return cllx;
	}

	public void setCllx(Integer cllx) {
		this.cllx = cllx;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}



	private LiqingziduancfgView lqziduancfglow;  //初级下线
    private LiqingziduancfgView lqziduancfghigh;  //初级上线
    private LiqingziduancfgView lqziduancfglow1;  //中级下线
    private LiqingziduancfgView lqziduancfghigh1;  //中级上线
    private LiqingziduancfgView lqziduancfglow2;   //高级下线
    private LiqingziduancfgView lqziduancfghigh2;  //高级上线
	
	public LiqingziduancfgView getLqziduancfglow() {
		return lqziduancfglow;
	}

	public void setLqziduancfglow(LiqingziduancfgView lqziduancfglow) {
		this.lqziduancfglow = lqziduancfglow;
	}

	public LiqingziduancfgView getLqziduancfghigh() {
		return lqziduancfghigh;
	}

	public void setLqziduancfghigh(LiqingziduancfgView lqziduancfghigh) {
		this.lqziduancfghigh = lqziduancfghigh;
	}

	public LiqingziduancfgView getLqziduancfglow1() {
		return lqziduancfglow1;
	}

	public void setLqziduancfglow1(LiqingziduancfgView lqziduancfglow1) {
		this.lqziduancfglow1 = lqziduancfglow1;
	}

	public LiqingziduancfgView getLqziduancfghigh1() {
		return lqziduancfghigh1;
	}

	public void setLqziduancfghigh1(LiqingziduancfgView lqziduancfghigh1) {
		this.lqziduancfghigh1 = lqziduancfghigh1;
	}

	public LiqingziduancfgView getLqziduancfglow2() {
		return lqziduancfglow2;
	}

	public void setLqziduancfglow2(LiqingziduancfgView lqziduancfglow2) {
		this.lqziduancfglow2 = lqziduancfglow2;
	}

	public LiqingziduancfgView getLqziduancfghigh2() {
		return lqziduancfghigh2;
	}

	public void setLqziduancfghigh2(LiqingziduancfgView lqziduancfghigh2) {
		this.lqziduancfghigh2 = lqziduancfghigh2;
	}

	public Integer getBsid() {
		return bsid;
	}

	public void setBsid(Integer bsid) {
		this.bsid = bsid;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}



	private Integer usertype;

	private String startTime;
	private String endTime;

	private Integer biaoduan;
	private Integer xiangmubu;
	private Integer chaobiaolx=0;
	private Integer[] wuchaselect;
	private List<WuchaIsShowData> wuchalist;
	private int maxPageItems;
	private Integer pageNo;
	private String peifan;
	private Map<String,String> peifanmap;
	public Map<String, String> getPeifanmap() {
		return peifanmap;
	}

	public void setPeifanmap(Map<String, String> peifanmap) {
		this.peifanmap = peifanmap;
	}

	public String getPeifan() {
		return peifan;
	}

	public void setPeifan(String peifan) {
		this.peifan = peifan;
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

	private String bhzname;

	
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


	@Action("liqingphblist")
	public String liqingphblist() {
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

		setShebeibianhao(request.getParameter("shebeibianhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}

		setLiqingmanualphb(queryService.lqmanualphbviewlist(shebeibianhao, 
				startTime, endTime,  biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems,peifan));
		LiqingziduancfgView lqfield = queryService.getlqcfgfield(shebeibianhao);
		LiqingziduancfgView lqisshow = queryService.getlqcfgisShow(shebeibianhao);		
		setLiqingField(lqfield);
		setLiqingisShow(lqisshow);
		

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
		List peifanlist=queryService.getlqxinghao();
		for(int m=0;m<peifanlist.size();m++){
			String str=(String)peifanlist.get(m);
			peifanmap.put(str,str);
		}
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& null != lqfield && null != lqisshow){
				List<String> dataList=new ArrayList<String>();
				List<String> headerList = new ArrayList<String>();				
				
				if (StringUtil.Null2Blank(lqisshow.getBaocunshijian()).equalsIgnoreCase("1")) {
					headerList.add(StringUtil.Null2Blank(lqfield.getBaocunshijian()));
				}			
				
				
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
					header[j] = (String) iterator.next();
					j++;
				}
				HntExcelUtil.importListExcel(path+"excel/台帐.xls", path+excelName, StringUtil.Null2Blank(bhzname), header, dataList);
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
	
	@Action("liqingchaobiaolist")
	public String liqingchaobiaolist() {
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
		if (StringUtil.Null2Blank(request.getParameter("startTime")).length()>0) {
			setStartTime(request.getParameter("startTime"));
		}
		if (StringUtil.Null2Blank(request.getParameter("endTime")).length()>0) {
			setEndTime(request.getParameter("endTime"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		// 如果是从首页链接进来的话，那么就进入这里
		if (StringUtil.Null2Blank(request.getParameter("method")).equalsIgnoreCase("list")) {
			if (null == startTime && null == endTime) {
				Calendar day = Calendar.getInstance();
				setEndTime(sdf.format(day.getTime()));
				day.add(Calendar.DATE, -2);
				setStartTime(sdf.format(day.getTime()));
			}
		}

		setShebeibianhao(request.getParameter("shebeibianhao"));
		
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		
		if(StringUtil.Null2Blank(request.getParameter("cllx")).length()>0){
			setCllx(Integer.parseInt(request.getParameter("cllx")));
		}
		if(StringUtil.Null2Blank(request.getParameter("bianhao")).length()>0){
			setBianhao(request.getParameter("bianhao"));
		}
		
		if (null != wuchaselect && wuchaselect.length > 0) {
			Liqingziduancfg lqcfgisshow= queryService.getlqchaobiaocfg(shebeibianhao);
			if (null == lqcfgisshow) {
				lqcfgisshow = new Liqingziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					lqcfgisshow.setGprsbianhao(shebeibianhao);
					lqcfgisshow.setLeixin("31");
				} else {
					lqcfgisshow.setGprsbianhao("all");
					lqcfgisshow.setLeixin("30");
				}
			}
			
			List<Integer> selectlist = new ArrayList<Integer>();
			for (int h = 0; h < wuchaselect.length; h++) {
				selectlist.add(wuchaselect[h]);
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
			if (selectlist.contains(12)) {
				lqcfgisshow.setSjysb("1");
			} else {
				lqcfgisshow.setSjysb("0");
			}
			if (selectlist.contains(13)) {
				lqcfgisshow.setClwd("1");
			} else {
				lqcfgisshow.setClwd("0");
			}
			if (selectlist.contains(14)) {
				lqcfgisshow.setGlwd("1");
			} else {
				lqcfgisshow.setGlwd("0");
			}
			if (selectlist.contains(15)) {
				lqcfgisshow.setLqwd("1");
			} else {
				lqcfgisshow.setLqwd("0");
			}
			queryService.saveChaobiaocfg(lqcfgisshow);
		}
		
		LiqingziduancfgView lqfield = queryService.getlqcfgfield(shebeibianhao);
		LiqingziduancfgView lqisshow = queryService.getlqchaobiaoisshowcfg(shebeibianhao);	
		if (null == lqisshow) {
			Liqingziduancfg lqcfgisshow;
			lqcfgisshow = queryService.getlqchaobiaocfg(shebeibianhao);
			if (null == lqcfgisshow) {
				lqcfgisshow = new Liqingziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					lqcfgisshow.setGprsbianhao(shebeibianhao);
					lqcfgisshow.setLeixin("31");
				} else {
					lqcfgisshow.setGprsbianhao("all");
					lqcfgisshow.setLeixin("30");
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
			lqcfgisshow.setSjysb("1");
			lqcfgisshow.setClwd("1");
			lqcfgisshow.setGlwd("1");
			lqcfgisshow.setLqwd("1");
			queryService.saveChaobiaocfg(lqcfgisshow);
			lqisshow = queryService.getlqcfgisShow(shebeibianhao);	
		}		
		wuchalist = new ArrayList<WuchaIsShowData>();
		wuchaselect = new Integer[15];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
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
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getSjysb());
		wuchalist.add(wd);	
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getClwd()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getClwd());
		wuchalist.add(wd);	
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getGlwd());
		wuchalist.add(wd);	
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(lqfield.getLqwd());
		wuchalist.add(wd);	
	
		setLiqingField(lqfield);
		setLiqingisShow(lqisshow);
		
		setLiqingmanualphb(queryService.lqchaobiaomanualviewlist(lqisshow,chaobiaolx,shebeibianhao, 
				startTime, endTime,  biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems,cllx,bianhao));	

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
		
		return SUCCESS;
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

	public Liqingxixx getLqxixx() {
		return lqxixx;
	}

	public void setLqxixx(Liqingxixx lqxixx) {
		this.lqxixx = lqxixx;
	}

	public GenericPageMode getLiqingphb() {
		return liqingphb;
	}

	public void setLiqingphb(GenericPageMode liqingphb) {
		this.liqingphb = liqingphb;
	}

	public Integer getChaobiaolx() {
		return chaobiaolx;
	}

	public void setChaobiaolx(Integer chaobiaolx) {
		this.chaobiaolx = chaobiaolx;
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

	public GenericPageMode getLiqingmanualphb() {
		return liqingmanualphb;
	}

	public void setLiqingmanualphb(GenericPageMode liqingmanualphb) {
		this.liqingmanualphb = liqingmanualphb;
	}

	//沥青首页的附属页面
		@Action("Lqquerylist")
		public String querylist(){
			ActionContext context = ActionContext.getContext(); 
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			setUsertype(StringUtil.getUserType(request));
			setBsid(StringUtil.getBiaoshiId(request));
			if (1 == usertype) {
				if (null == biaoduan && bsid==0) {
					setBiaoduan(1);
				}
				if(bsid==0){
					setBsid(biaoduan);
				}
			}	
			biaoduanlistmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
			for (Biaoduanxinxi bdxx : bdlist) {
				biaoduanlistmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
			}
			if (null!=bdlist && bdlist.size()>0 && null == biaoduan) {
				setBiaoduan(bdlist.get(0).getId());
			}
			request.setAttribute("strXMLList", dataService.getlqchaobiaobhzXml(2,StringUtil.getQueryFieldNameByUserType(usertype),bsid));
			setLiqingField(queryService.getLqfield(shebeibianhao));
			setLiqingisShow(queryService.getlqcfgisShow40(shebeibianhao));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//如果是从首页链接进来的话，那么就进入这里
			if (null == startTime && null == endTime) {
				Calendar day=Calendar.getInstance(); 
				setEndTime(sdf.format(day.getTime()));
				day.add(Calendar.DATE, -3);
				setStartTime(sdf2.format(day.getTime()));
			}
			if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length() > 0) {
				setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
			}
			if (StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length() > 0) {
				setShebeibianhao(request.getParameter("shebeibianhao"));
			}
			setPageNo(1);
			if (StringUtil.Null2Blank(request.getParameter("pageNo")).length() > 0) {
				setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			}
			setMaxPageItems(15);
			if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length() > 0) {
				setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
			}
			if (null != wuchaselect && wuchaselect.length > 0) {
				Liqingziduancfg lqcfgisshow = queryService.getlqcfg70(shebeibianhao);
				if (null == lqcfgisshow) {
					lqcfgisshow = new Liqingziduancfg();
					if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
						lqcfgisshow.setGprsbianhao(shebeibianhao);
						lqcfgisshow.setLeixin("71");
					} else {
						lqcfgisshow.setGprsbianhao("all");
						lqcfgisshow.setLeixin("70");
					}
				}

				List<Integer> selectlist = new ArrayList<Integer>();
				for (int h = 0; h < wuchaselect.length; h++) {
					selectlist.add(wuchaselect[h]);
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
				if (selectlist.contains(12)) {
					lqcfgisshow.setShijian("1");
				} else {
					lqcfgisshow.setShijian("0");
				}
				if (selectlist.contains(13)) {
					lqcfgisshow.setChangliang("1");
				} else {
					lqcfgisshow.setChangliang("0");
				}
				queryService.savelqcfg(lqcfgisshow);
			}

			LiqingziduancfgView lqziduanfield = queryService.getLqfield(shebeibianhao);
			LiqingziduancfgView lqisshow = queryService.getlqcfgisShow70(shebeibianhao);
			if (null == lqisshow) {
				Liqingziduancfg lqcfgisshow = queryService.getlqcfg70(shebeibianhao);
				if (null == lqcfgisshow) {
					lqcfgisshow = new Liqingziduancfg();
					if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
						lqcfgisshow.setGprsbianhao(shebeibianhao);
						lqcfgisshow.setLeixin("71");
					} else {
						lqcfgisshow.setGprsbianhao("all");
						lqcfgisshow.setLeixin("70");
					}
				}
				lqcfgisshow.setSjg1("1");
				lqcfgisshow.setSjg2("1");
				lqcfgisshow.setSjg3("1");
				lqcfgisshow.setSjg4("1");
				lqcfgisshow.setSjg5("1");
				lqcfgisshow.setSjg6("1");
				lqcfgisshow.setSjg7("1");
				lqcfgisshow.setSjf1("1");
				lqcfgisshow.setSjf2("1");
				lqcfgisshow.setSjlq("1");
				lqcfgisshow.setSjtjj("1");
//				lqcfgisshow.setSjshui("1");
				lqcfgisshow.setShijian("1");
				lqcfgisshow.setChangliang("1");
				queryService.savelqcfg(lqcfgisshow);
				lqisshow = queryService.getlqcfgisShow(shebeibianhao);
			}
			wuchalist = new ArrayList<WuchaIsShowData>();
			wuchaselect = new Integer[13];
			int i = 1;
			WuchaIsShowData wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjg1());
			wuchalist.add(wd);

			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjg2());
			wuchalist.add(wd);

			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjg3());
			wuchalist.add(wd);

			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjg4());
			wuchalist.add(wd);

			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjg5());
			wuchalist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjg6());
			wuchalist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjg7());
			wuchalist.add(wd);

			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjf1());
			wuchalist.add(wd);

			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjf2());
			wuchalist.add(wd);

			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjlq());
			wuchalist.add(wd);
			
			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getSjtjj());
			wuchalist.add(wd);

			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getShijian()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getShijian());
			wuchalist.add(wd);

			wd = new WuchaIsShowData();
			if (StringUtil.Null2Blank(lqisshow.getChangliang()).equalsIgnoreCase("1")) {
				wuchaselect[i - 1] = i;
			}
			wd.setId(i);
			i++;
			wd.setName(lqziduanfield.getChangliang());
			wuchalist.add(wd);

			setLiqingField(lqziduanfield);
			setLiqingisShow(lqisshow);
			//从预警配置中提取出对应拌和机的上下线
			setLqziduancfglow(sysService.lqsmslowViewfindBybh(shebeibianhao));
			setLqziduancfghigh(sysService.lqsmshighViewfindBybh(shebeibianhao));
			setLqziduancfglow1(sysService.lqsmslowViewfindBybh2(shebeibianhao));
			setLqziduancfghigh1(sysService.lqsmshighViewfindBybh2(shebeibianhao));
			setLqziduancfglow2(sysService.lqsmslowfindBybh3View(shebeibianhao));
			setLqziduancfghigh2(sysService.lqsmshighfindBybh3View(shebeibianhao));

			/*setSwmanualphb(swViewService.swchaobiaomanualviewlist(shebeibianhao, startTime, endTime, biaoduan, xiangmubu,
					StringUtil.getQueryFieldNameByRequest(request), StringUtil.getBiaoshiId(request), pageNo, maxPageItems, chaobiaolx, lqisShow,cllx,bianhao));*/
			
			setLiqingmanualphb(queryService.lqchaobiaomanualviewlist(lqisshow,chaobiaolx,shebeibianhao, startTime, endTime,biaoduan,xiangmubu,
					StringUtil.getQueryFieldNameByRequest(request), StringUtil.getBiaoshiId(request), pageNo, maxPageItems,cllx,bianhao));
			return SUCCESS;
		}

}
