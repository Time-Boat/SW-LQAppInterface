package com.mss.shtoone.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.mss.shtoone.domain.ShuiwenxixxlilunView;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.ShuiwenxixxlilunService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller
@Namespace("/getdata")
public class GetdataAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8785388051139993190L;

	@Autowired
	private GetdataService dataService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private ShuiwenxixxlilunService swxixxlilunService;
	private Integer biaoduan;
	private Integer usertype;
	private Integer bsid;
	private static String tanpu1 = "SpreadTemp";//摊铺温度
	private static String tanpu2 = "AirTemp";//大气温度
	private static String tanpu3 = "WindSpeed";//风速
	private static String tanpu4 = "AirShiDu";//大气湿度
	private static String tanpu5 = "sudu";//摊铺速度
	private static int gprsDeviceId1 = 11;//一标手持
	private static int gprsDeviceId2 = 21;//二标手持
	private Map<Integer, String> listmap;
	private Map<Integer, String> gprsDeviceIdlistmap;
	private List<ShuiwenxixxlilunView> swlilunphbList;//水稳理论配合比list
	private String nowTime;//当前日期
	private String shebeibianhao;
	
	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	public Integer getBiaoduan() {
		return biaoduan;
	}

	public void setBiaoduan(Integer biaoduan) {
		this.biaoduan = biaoduan;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getBsid() {
		return bsid;
	}

	public void setBsid(Integer bsid) {
		this.bsid = bsid;
	}

	public Map<Integer, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<Integer, String> listmap) {
		this.listmap = listmap;
	}

	public Map<Integer, String> getGprsDeviceIdlistmap() {
		return gprsDeviceIdlistmap;
	}

	public void setGprsDeviceIdlistmap(Map<Integer, String> gprsDeviceIdlistmap) {
		this.gprsDeviceIdlistmap = gprsDeviceIdlistmap;
	}

	public List<ShuiwenxixxlilunView> getSwlilunphbList() {
		return swlilunphbList;
	}

	public void setSwlilunphbList(List<ShuiwenxixxlilunView> swlilunphbList) {
		this.swlilunphbList = swlilunphbList;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}
	
    //沥青混凝土产能分析
	@Action("capacityanalysis")
	public String capacityanalysis() {
        ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
        request.setAttribute("strXML", dataService.getCapacityAnalysisColumnXml()); 
		return SUCCESS;
	}
	
	//沥青混凝土各材料用量饼图
	@Action("capacityanalysisdetail")
	public String capacityanalysisdetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String shebeiid = request.getParameter("shebeiid");  
	        if(shebeiid != null && !"".equals(shebeiid)) {
	            out.print(dataService.getCapacityAnalysisPieXml(shebeiid));
	        } 
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}	
	
	//实时表盘温度
	@Action("temperatureangularreal")
	public String temperatureangularreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateTemperatureAngularRealValue());
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//混凝土实时搅拌时间
	@Action("jiaobanshijianreal")
	public String jiaobanshijianreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);	   
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateJiaobanshijianValue(request.getSession().getId(),request.getParameter("shebeibianhao")));
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}	
	
	//现场实时数据
	@Action("xcqueryreal")
	public String xcqueryreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);	   
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache"); 
        String bd = request.getParameter("biaoduan"); 
        if (StringUtil.Null2Blank(bd).length() > 0) {
        	try {
        		biaoduan = Integer.valueOf(bd);
			} catch (Exception e) {
			}
		}
		setUsertype(StringUtil.getUserType(request));
		setBsid(StringUtil.getBiaoshiId(request));
		if (1 == usertype) {
			if (null == biaoduan) {
				setBiaoduan(1);
			}
			setBsid(biaoduan);			
		}
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updatexcqueryValue(request.getSession().getId(),biaoduan,
					StringUtil.getQueryFieldNameByUserType(usertype),bsid));
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}	
	
	@Action("tjjqueryreal")
	public String tjjqueryreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);	   
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache"); 
        String bd = request.getParameter("biaoduan"); 
        if (StringUtil.Null2Blank(bd).length() > 0) {
        	try {
        		biaoduan = Integer.valueOf(bd);
			} catch (Exception e) {
			}
		}
		setUsertype(1);
		//setBsid(StringUtil.getBiaoshiId(request));
		if (1 == usertype) {
			if (null == biaoduan) {
				setBiaoduan(1);
			}
			setBsid(biaoduan);			
		}
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updatetjjqueryValue(request.getSession().getId(),biaoduan,
					StringUtil.getQueryFieldNameByUserType(usertype),bsid));
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//沥青油石比
	@Action("lqysbreal")
	public String lqysbreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);	   
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updatelqysbValue(request.getSession().getId(),request.getParameter("shebeibianhao")));
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}	
	
	//沥青油石比
	@Action("top10lqysbreal")
	public String top10lqysbreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);	   
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updatetop10lqysbValue(request.getSession().getId(),request.getParameter("shebeibianhao")));
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}	
		
	//实时监控主界面
	@Action("mainlist")
	public String mainlist() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
        request.setAttribute("strXML", dataService.getPressureBubbleXml()); 
        request.setAttribute("strXML2", dataService.getTemperatureAngularXml());
        request.setAttribute("strXML3", dataService.getTemperatureAngularXml1());
        request.setAttribute("strXML4", dataService.getTemperatureLineXml());
        return SUCCESS; 
	}	
	
	//实时监控刷新圆形图
	@Action("updatemainlist")
	public String updatemainlist() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updatePressureBubbleXml());
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//试验检测监控主界面
	@Action("testmonitor")
	public String testmonitor() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
        request.setAttribute("strXML", dataService.getTestMonitorLineXml());         
        return SUCCESS; 
	}	
	
	//实时刷新试验检测压力值
	@Action("testmonitorreal")
	public String testmonitorreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateTestMonitorLineRealValue());
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//水泥混凝土材料用量监控主界面
	@Action("hntcailiaoyongliangmonitor")
	public String hntcailiaoyongliangmonitor() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        String shebeibianhao = request.getParameter("shebeibianhao");  
        String bd = request.getParameter("biaoduan"); 
        if (StringUtil.Null2Blank(bd).length() > 0) {
        	try {
        		biaoduan = Integer.valueOf(bd);
			} catch (Exception e) {
			}
		}
		setUsertype(StringUtil.getUserType(request));
		setBsid(StringUtil.getBiaoshiId(request));
		if (1 == usertype) {
			if (null == biaoduan) {
				setBiaoduan(1);
			}
			setBsid(biaoduan);	
			listmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.biaoduanlist();
			for (Biaoduanxinxi bdxx : bdlist) {
				listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
			}
		}
        request.setAttribute("strXML", dataService.gethntcailiaoyongliangXml(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        request.setAttribute("strXMLList", dataService.gethntbhzXml(2,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        return SUCCESS; 
	}	
	
	//沥青材料用量监控主界面
	@Action("lqcailiaoyongliangmonitor")
	public String lqcailiaoyongliangmonitor() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
        	setShebeibianhao(request.getParameter("shebeibianhao"));
        }
        if(StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0){
        	setBiaoduan(Integer.parseInt(request.getParameter("biaoduan")));
        }
		setUsertype(StringUtil.getUserType(request));
		setBsid(StringUtil.getBiaoshiId(request));
		if (1 == usertype) {
			if (null == biaoduan) {
				setBiaoduan(1);
			}
			if(bsid==0){
				setBsid(biaoduan);
			}
		}
		listmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bdxx : bdlist) {
			listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
		}
		if (null!=bdlist && bdlist.size()>0 && null == biaoduan) {
			setBiaoduan(bdlist.get(0).getId());
		}	
        request.setAttribute("strXML", dataService.getlqcailiaoyongliangXml(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        //按照东交给的要求， 显示近10或者更多盘的料，保证最后一盘是最新生产的
        request.setAttribute("strysbXML", dataService.gettop10lqysbXml(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        request.setAttribute("strXMLList", dataService.getliqingXml(2,biaoduan,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        request.setAttribute("strXMLbp1", dataService.getAsphaltAngularXml1(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        request.setAttribute("strXMLbp2", dataService.getAsphaltAngularXml2(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        //出料温度
        request.setAttribute("strXMLbp3", dataService.getAsphaltAngularXml3(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        return SUCCESS; 
	}
	
	
	//沥青骨料实时表盘温度
	@Action("liqingguliaowendureal")
	public String liqingguliaowendureal() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateLiqingguliaowenduRealValue(request.getSession().getId(),request.getParameter("shebeibianhao")));
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//沥青实时表盘温度
	@Action("liqingliqingwendureal")
	public String liqingliqingwendureal() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateLiqingliqingwenduRealValue(request.getSession().getId(),request.getParameter("shebeibianhao")));
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//沥青成品料实时表盘温度
	@Action("liqingchenpingwendureal")
	public String liqingchenpingwendureal() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateLiqingchenpingwenduRealValue(request.getSession().getId(),request.getParameter("shebeibianhao")));
		} catch (IOException e) {
		}  
        return null; 
	}

	//沥青混凝土实时材料用量
	@Action("asphaltdosagecolumnreal")
	public String asphaltdosagecolumnreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateAsphaltDosageColumnRealValue());
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}	
	
	//混凝土实时材料用量
	@Action("hunnintucailiaoreal")
	public String hunnintucailiaoreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updatehunnintucailiaorealvalue(request.getSession().getId(), request.getParameter("shebeibianhao")));
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//沥青实时材料用量
	@Action("lqcailiaoreal")
	public String lqcailiaoreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updatelqcailiaorealvalue(request.getSession().getId(), request.getParameter("shebeibianhao")));
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
    //试验检测统计分析
	@Action("testanalysis")
	public String testanalysis() {
		return SUCCESS;
	}
	
    //水泥混凝土统计分析
	@Action("concreteanalysis")
	public String concreteanalysis() {
		return SUCCESS;
	}	
	
	//水泥混凝土拌和时间主界面
	@Action("hntbanheshijianmonitor")
	public String hntbanheshijianmonitor() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        String shebeibianhao = request.getParameter("shebeibianhao"); 
        String bd = request.getParameter("biaoduan"); 
        if (StringUtil.Null2Blank(bd).length() > 0) {
        	try {
        		biaoduan = Integer.valueOf(bd);
			} catch (Exception e) {
			}
		}
		setUsertype(StringUtil.getUserType(request));
		setBsid(StringUtil.getBiaoshiId(request));
		if (1 == usertype) {
			if (null == biaoduan) {
				setBiaoduan(1);
			}
			setBsid(biaoduan);
			listmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.biaoduanlist();
			for (Biaoduanxinxi bdxx : bdlist) {
				listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
			}
		}
        request.setAttribute("strXML", dataService.gethntbanheshijianXml(request.getSession().getId(), shebeibianhao, StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        request.setAttribute("strXMLList", dataService.gethntbhzXml(2,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        return SUCCESS; 
	}
	
	//现场数据监控
	@Action("xcmonitor")
	public String xcmonitor() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        String bd = request.getParameter("biaoduan"); 
        if (StringUtil.Null2Blank(bd).length() > 0) {
        	try {
        		biaoduan = Integer.valueOf(bd);
			} catch (Exception e) {
			}
		}
		setUsertype(StringUtil.getUserType(request));
		setBsid(StringUtil.getBiaoshiId(request));
		
		if (1 == usertype) {
			listmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.biaoduanlist();
			for (Biaoduanxinxi bdxx : bdlist) {
				listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
			}
			if (null == biaoduan && bdlist.size()>0) {
				setBiaoduan(bdlist.get(0).getId());
			}
			setBsid(biaoduan);
		} else {
			setBiaoduan(bsid);
		}
        request.setAttribute("strXML", dataService.getxcqueryXml(request.getSession().getId(), biaoduan, StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        return SUCCESS; 
	}	
	
	//添加剂数据监控
	@Action("tjjmonitor")
	public String tjjmonitor() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        String bd = request.getParameter("biaoduan"); 
        if (StringUtil.Null2Blank(bd).length() > 0) {
        	try {
        		biaoduan = Integer.valueOf(bd);
			} catch (Exception e) {
			}
		}
		setUsertype(1);
		//setBsid(StringUtil.getBiaoshiId(request));
		
		if (1 == usertype) {
			listmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.biaoduanlist();
			for (Biaoduanxinxi bdxx : bdlist) {
				listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
			}
			if (null == biaoduan && bdlist.size()>0) {
				setBiaoduan(bdlist.get(0).getId());
			}
			setBsid(biaoduan);
		} else {
			setBiaoduan(bsid);
		}
        request.setAttribute("strXML", dataService.gettjjqueryXml(request.getSession().getId(), biaoduan, StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        return SUCCESS; 
	}	
	
	//
	@Action("hntbanheshijiandetail")
	public String hntbanheshijiandetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();	
	        out.print(dataService.hntbanheshijianXml(request.getSession().getId(), request.getParameter("shebeibianhao"), "id", 0));	       
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	@Action("hntbanhecailiaodetail")
	public String hntbanhecailiaodetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();	
	        out.print(dataService.hntbanhecailiaoXml(request.getSession().getId(), request.getParameter("shebeibianhao"), "id", 0));	       
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	@Action("lqcailiaodetail")
	public String lqcailiaodetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();	
	        out.print(dataService.lqcailiaoXml(request.getSession().getId(), request.getParameter("shebeibianhao"), "id", 0));	       
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	@Action("lqysbdetail")
	public String lqysbdetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();	
	        out.print(dataService.lqysbXml(request.getSession().getId(), request.getParameter("shebeibianhao"), "id", 0));	       
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	@Action("lqslwendudetail")
	public String lqslwendudetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();	
	        out.print(dataService.lqbanheguliaowenduXml(request.getSession().getId(), request.getParameter("shebeibianhao"), "id", 0));	       
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	@Action("lqlqwendudetail")
	public String lqlqwendudetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();	
	        out.print(dataService.lqbanheliqingwenduXml(request.getSession().getId(), request.getParameter("shebeibianhao"), "id", 0));	       
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//水泥混凝土实时材料用量
	@Action("concretedosagelinereal")
	public String concretedosagelinereal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateConcreteDosageLineRealValue());
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//路面施工监控主界面
	@Action("constructionmonitor")
	public String constructionmonitor() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
        request.setAttribute("strXML", dataService.getConstructionMonitorLineXml());         
        return SUCCESS; 
	}	
	
	//实时刷新路面施工温度
	@Action("constructmonitorreal")
	public String constructmonitorreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateConstructMonitorLineRealValue());
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}

	//摊铺机施工数据查询
	@Action("tanpujidata")
	public String tanpujidata() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        String shebeibianhao = request.getParameter("shebeibianhao"); 
        if(null==shebeibianhao||shebeibianhao.equals("")){
        	shebeibianhao = "44";
        }
    
       // request.setAttribute("strXML", dataService.getlqcailiaoyongliangXml(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        //request.setAttribute("strXMLList", dataService.getliqingXml(2,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        try{
        	gprsDeviceIdlistmap = new LinkedHashMap<Integer, String>();
    		gprsDeviceIdlistmap.put(11, "一标1号摊铺机");//----摊铺机id
    		gprsDeviceIdlistmap.put(12, "一标2号摊铺机");
    		
    		gprsDeviceIdlistmap.put(21, "二标1号摊铺机");//----摊铺机id
    		gprsDeviceIdlistmap.put(22, "二标2号摊铺机");
        	
        	
        	request.setAttribute("strXMLtp1", dataService.getTanPuJiXml1(request.getSession().getId(), shebeibianhao,tanpu1,0)); 
            request.setAttribute("strXMLtp2", dataService.getTanPuJiXml2(request.getSession().getId(), shebeibianhao,tanpu2,0)); 
            request.setAttribute("strXMLtp3", dataService.getTanPuJiXml3(request.getSession().getId(), shebeibianhao,tanpu3,0)); 
            request.setAttribute("strXMLtp4", dataService.getTanPuJiXml4(request.getSession().getId(), shebeibianhao,tanpu4,0)); 
            request.setAttribute("strXMLtp5", dataService.getTanPuJiXml5(request.getSession().getId(), shebeibianhao,tanpu5,0)); 
        }catch(Exception e){
        }
        
        return SUCCESS; 
	}
	//摊铺数据
	@Action("tanpureal")
	public String tanpureal() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.tanpuRealValue(request.getSession().getId(),request.getParameter("shebeibianhao"),request.getParameter("wmfield")));
		} catch (IOException e) {
		}  
        return null; 
	}
	
	//碾压施工数据查询
	@Action("nianyadata")
	public String nianyadata() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);    
        request.setAttribute("strXMLbp1", dataService.getNianYaXML1(request.getSession().getId(), gprsDeviceId1,"",0)); 
        request.setAttribute("strXMLbp2", dataService.getNianYaXML2(request.getSession().getId(), gprsDeviceId2,"",0)); 
        return SUCCESS; 
	}
	
	@Action("swcailiaoyongliangmonitor")
	public String swcailiaoyongliangmonitor() {
		ActionContext context = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        String shebeibianhao = request.getParameter("shebeibianhao");  
        String bd = request.getParameter("biaoduan"); 
        if (StringUtil.Null2Blank(bd).length() > 0) {
        	try {
        		biaoduan = Integer.valueOf(bd);
			} catch (Exception e) {
			}
		}
		setUsertype(StringUtil.getUserType(request));
		setBsid(StringUtil.getBiaoshiId(request));
		if (1 == usertype) {
			if (null == biaoduan) {
				setBiaoduan(4);
			}
			setBsid(biaoduan);	
			listmap = new LinkedHashMap<Integer, String>();
			List<Biaoduanxinxi> bdlist = sysService.biaoduanlist();
			for (Biaoduanxinxi bdxx : bdlist) {
				listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
			}
		}
		
		setSwlilunphbList(swxixxlilunService.getSwllpbhlistByToday(StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)));//水稳理论配合比
		setNowTime(GetDate.getNowTime("yyyy-MM-dd"));
        request.setAttribute("strXML", dataService.getswcailiaoyongliangXml(request.getSession().getId(), shebeibianhao,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        request.setAttribute("strXMLList", dataService.getswbhzXml(2,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
        return SUCCESS; 
	}
	
	
	@Action("swcailiaoreal")
	public String swcailiaoreal() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();			
			out.print(dataService.updateswcailiaorealvalue(request.getSession().getId(), request.getParameter("shebeibianhao")));
	        out.flush();
	        out.close();
		} catch (IOException e) {
		}  
        return null; 
	}
	
	@Action("swcailiaodetail")
	public String swcailiaodetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();	
			out.print(dataService.swbanhecailiaoXml(request.getSession().getId(), request.getParameter("shebeibianhao"),"id",0));
	        out.flush();
	        out.close();
		} catch (IOException e) {} 
        return null; 
	}
	
	@Action("clwendudetail")
	public String clwendudetail() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();	
	        out.print(dataService.lqbanhechenpingwenduXml(request.getSession().getId(), request.getParameter("shebeibianhao"), "id", 0));	       
	        out.flush();
	        out.close();
		} catch (IOException e) {}  
        return null; 
	}
}
