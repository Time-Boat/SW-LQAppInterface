package com.mss.shtoone.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.ChuliaokouTemperaturedataView;
import com.mss.shtoone.domain.ChuliaokouTemperaturejieguo;
import com.mss.shtoone.domain.Clksmscfg;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.HandSet;
import com.mss.shtoone.domain.Hntbhzziduancfg;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.Hntview;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.Liqingxixxjieguo;
import com.mss.shtoone.domain.Liqingxixxlilun;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.Shaifenshiyan;
import com.mss.shtoone.domain.Shoupan;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.Shuiwentongji;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.Shuiwenxixxjieguo;
import com.mss.shtoone.domain.Shuiwenxixxlilun;
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.Smsrecord;
import com.mss.shtoone.domain.SpeeddataView;
import com.mss.shtoone.domain.Speedjieguo;
import com.mss.shtoone.domain.TemperaturedataView;
import com.mss.shtoone.domain.Temperaturejieguo;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.TopRealChuliaokouTemperaturedataView;
import com.mss.shtoone.domain.TopRealMaxLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.TopRealMaxhunnintuView;
import com.mss.shtoone.domain.TopRealSpeeddataView;
import com.mss.shtoone.domain.TopRealTemperaturedataView;
import com.mss.shtoone.domain.Xcsmscfg;
import com.mss.shtoone.domain.Xiangxixxjieguo;
import com.mss.shtoone.domain.Xiangxixxsms;
import com.mss.shtoone.persistence.ShoupanDAO;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.LiqingclDailyService;
import com.mss.shtoone.service.LiqingxixxlilunService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.ShaifenshiyanService;
import com.mss.shtoone.service.ShuiwenclDailyService;
import com.mss.shtoone.service.ShuiwenxixxlilunService;
import com.mss.shtoone.service.SmsinfoService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.MapUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

@Controller
@Namespace("/main")
@Results(@Result(name = "list", type = "redirect", location = "list?pid=0&record=0&"))
public class MainAction extends ActionSupport {
	
	private static Log logger = LogFactory.getLog(MainAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -8285293084936512308L;

	@Autowired
	private LiqingclDailyService lqdailyService;
	
	@Autowired
	private ShuiwenclDailyService swdailyService;
	//这个是用于开盘前两盘不报警的表
	@Autowired
	private ShoupanDAO shoupanService;
	
	@Autowired
	private QueryService queryService;

	@Autowired
	private GetdataService dataService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private SmsinfoService smsService;
	
	@Autowired
	private ShuiwenxixxlilunService swllService;
	
	@Autowired
	private ShaifenshiyanService shaifenService;
	
	@Autowired
	private LiqingxixxlilunService lqllService;
	
	private List<TopLiqingView> Toplqs;
	private LiqingView lqdata;
	private GenericPageMode liqingpgs;
	private LiqingziduancfgView liqingisShow;
	private LiqingziduancfgView liqingField;
	private Map<Integer, String> listmap;
	private Integer biaoduan;
	private Integer usertype;
	private Integer bsid;
	private ShuiwenziduancfgView shuiwenisShow;
	private ShuiwenziduancfgView shuiwenField;
	private ShuiwenxixxView swdata;
	
	
	
	public ShuiwenxixxView getSwdata() {
		return swdata;
	}

	public void setSwdata(ShuiwenxixxView swdata) {
		this.swdata = swdata;
	}

	@Action("list")
	public String list() {		
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
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
		setLiqingField(queryService.getlqcfgfield("all"));
		setLiqingisShow(queryService.getlqcfgisShow("all"));
//      最新的1~5天是生产记录详情		
//		setLiqingpgs(queryService.lqviewlist(null, 
//				null, null,  biaoduan, null, 
//				StringUtil.getQueryFieldNameByRequest(request), 
//				StringUtil.getBiaoshiId(request),1, 5, 1,null));
		setLqdata(queryService.lqstatisticsinfo());
//      生产统计合格与不合格		
//		request.setAttribute("strXMLpie", dataService.mainlistgetPieXml(lqdata));
		request.setAttribute("strXMLList", dataService.mainlistgetliqingXml(1,biaoduan,StringUtil.getQueryFieldNameByUserType(usertype),bsid));
		request.setAttribute("strXMLzhfx", dataService.mainlistgetlqzhfxXml(queryService.lqmainlistzhfxlist(null,biaoduan,null, 
    			4,StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)), 4));
		return SUCCESS;
	}
	
	//水稳主页
	@Action("swlist")
	public String swlist() {		
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
		listmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bdxx : bdlist) {
			listmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
		}
		if (null!=bdlist && bdlist.size()>0 && null == biaoduan) {
			setBiaoduan(bdlist.get(0).getId());
		}	
		this.setShuiwenField(queryService.getSwfield("all"));
		this.setShuiwenisShow(queryService.getSwcfgisShow("all"));
		//超标
		setSwdata(queryService.swstatisticsinfo());
		//水稳数据生产监控
		request.setAttribute("strXMLList", dataService.mainlistgetShuiwenXml(1,biaoduan,StringUtil.getQueryFieldNameByUserType(usertype),bsid)); 
		//
		request.setAttribute("strXMLzhfx", dataService.mainlistgetswzhfxXml(queryService.swmainlistzhfxlist(null,biaoduan,null, 
    			4,StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)), 4));
		return SUCCESS;
	}
	
	@Action("lqrefreshdata")
	public String lqrefreshdata() {
		alarmsms();
		List qlist = dataService.findLiqingTop();
		if (qlist.size()>0) {
			ActionContext context = ActionContext.getContext(); 
			HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
			+"classes"+File.separator+"sms.ini";			
			File fp = new File(filename);			
			if(!fp.exists()){
				try {
					fp.createNewFile();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if(fp.exists()){
				try {
					Properties prop=new Properties();
					prop.load(new FileInputStream(fp));	
					
					String maxbh = prop.getProperty("lqbianhao", "1");
					String rdaily = prop.getProperty("rdaily", "0");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat dailyf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar day=Calendar.getInstance();
					Date dailyTime=day.getTime();
					try {
						dailyTime = dailyf.parse(sdf.format(day.getTime())+" 05:00:00");
					} catch (ParseException e) {
					}
					if (rdaily.equalsIgnoreCase("0") && day.getTime().after(dailyTime)) {
						lqdailyService.refreshdaily();
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("rdaily", "1");
						prop.store(fos, "sms");
						fos.close();
					} else if (rdaily.equalsIgnoreCase("1") && day.getTime().before(dailyTime)) {
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("rdaily", "0");
						prop.store(fos, "sms");
						fos.close();
					}
					
					TopRealMaxLiqingView hv = (TopRealMaxLiqingView)qlist.get(0);					
					if (null != hv && hv.getBianhao() > Integer.parseInt(maxbh)) {
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("lqbianhao", String.valueOf(hv.getBianhao()));
						prop.store(fos, "sms");
						fos.close();
						sysService.getMaxlqxx();
					}	
					
				} catch (FileNotFoundException e) {
					logger.error(e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return null;
	}
	
	
	//水稳
	@Action("swrefreshdata")
	public String swrefreshdata() {
		alarmsms();
		List qlist = dataService.findShuiwenTop();
		if (qlist.size()>0) {
			ActionContext context = ActionContext.getContext(); 
			HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"classes"+File.separator+"sms.ini";			
			File fp = new File(filename);			
			if(!fp.exists()){
				try {
					fp.createNewFile();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if(fp.exists()){
				try {
					Properties prop=new Properties();
					prop.load(new FileInputStream(fp));	
					
					String maxbh = prop.getProperty("swbianhao", "1");
					String swrdaily = prop.getProperty("swrdaily", "0");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat dailyf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar day=Calendar.getInstance();
					Date dailyTime=day.getTime();
					try {
						dailyTime = dailyf.parse(sdf.format(day.getTime())+" 05:00:00");
					} catch (ParseException e) {}
					if (swrdaily.equalsIgnoreCase("0") && day.getTime().after(dailyTime)) {
						swdailyService.refreshdaily();
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("swrdaily", "1");
						prop.store(fos, "sms");
						fos.close();
					} else if (swrdaily.equalsIgnoreCase("1") && day.getTime().before(dailyTime)) {
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("swrdaily", "0");
						prop.store(fos, "sms");
						fos.close();
					}
					
					TopRealMaxShuiwenxixxView hv = (TopRealMaxShuiwenxixxView)qlist.get(0);					
					if (null != hv && hv.getBianhao() > Integer.parseInt(maxbh)) {
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("swbianhao", String.valueOf(hv.getBianhao()));
						prop.store(fos, "sms");
						fos.close();
						sysService.getMaxswxx();
					}
					
				} catch (FileNotFoundException e) {
					logger.error(e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return null;
	}
	
	
	
	@Action("xcrefreshdata")
	public String xcrefreshdata() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
		+"classes"+File.separator+"sms.ini";			
		File fp = new File(filename);			
		if(!fp.exists()){
			try {
				fp.createNewFile();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		List qlist = dataService.findTemperatureTop();
		if (qlist.size()>0) {			
			if(fp.exists()){
				try {
					Properties prop=new Properties();
					prop.load(new FileInputStream(fp));	
					
					String maxbh = prop.getProperty("xctmpbianhao", "1");					
					TopRealTemperaturedataView hv = (TopRealTemperaturedataView)qlist.get(0);					
					if (null != hv && hv.getTmpid() > Integer.parseInt(maxbh)) {
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("xctmpbianhao", String.valueOf(hv.getTmpid()));
						prop.store(fos, "sms");
						fos.close();
					}	
				} catch (FileNotFoundException e) {
					logger.error(e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		qlist = dataService.findSpeedTop();
		if (qlist.size()>0) {			
			if(fp.exists()){
				try {
					Properties prop=new Properties();
					prop.load(new FileInputStream(fp));	
					
					String maxbh = prop.getProperty("xcspeedbianhao", "1");					
					TopRealSpeeddataView hv = (TopRealSpeeddataView)qlist.get(0);					
					if (null != hv && hv.getGpsid()> Integer.parseInt(maxbh)) {
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("xcspeedbianhao", String.valueOf(hv.getGpsid()));
						prop.store(fos, "sms");
						fos.close();
					}	
				} catch (FileNotFoundException e) {
					logger.error(e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return null;
	}

	private String dolqmanualsms(LiqingmanualphbView hv, String filename, Integer bianhao, String shebeibianhao, String smsbaojin, String issms, String biaozhun,String biaozhunhigh, String jiaobanphonenumber, String phonenumber, 
			String biaozhun2,String biaozhunhigh2,String biaozhunlowRight2,String biaozhuanhighRight2,String jiaobanphonenumber2, String phonenumber2, 
			String biaozhun3,String biaozhunhigh3,String jiaobanphonenumber3, String phonenumber3, String shijizhi, String wucha,
			String lilunzhi, String sheji, String smscontent, String fieldname, 
			String baocunshijian, String Chuliaoshijian, String isshowchuliao, String bhzminchen, int ziduantype, int sendcount, int onefrequency, String danwei) {
		String sms="";
		if (StringUtil.Null2Blank(shijizhi).length()>0 && 
				StringUtil.Null2Blank(wucha).length()>0 && 
				StringUtil.Null2Blank(lilunzhi).length()>0) {
			try {
				if (shijizhi.length()>8) {
					shijizhi = String.format("%1$.2f",Double.valueOf(shijizhi));
				}
				if (wucha.length()>8) {
					wucha = String.format("%1$.2f",Double.valueOf(wucha));
				}
				if (StringUtil.Null2Blank(lilunzhi).length()>0 && lilunzhi.length()>8) {
					lilunzhi = String.format("%1$.2f",Double.valueOf(lilunzhi));
				}
				double shiji = Double.valueOf(shijizhi);
				double sjwucha = 0;
				double bz = -1;
				double bzhigh=-1;
				double bz2 = -1;
				double bzhigh2 =-1;
				double bzRight2=-1;
				double bzhighRight2=-1;
				double bz3 = -1;
				double bzhigh3 =-1;
				double finalbz = -1;
				boolean denji1 = false;
				boolean denji2 = false;
				boolean denji3 = false;	
					
				if (StringUtil.Null2Blank(biaozhun).length()>0) {
					try {
						bz = Double.valueOf(biaozhun);
					} catch (Exception e) {}
				}
				
				if (StringUtil.Null2Blank(biaozhunhigh).length()>0) {
					try {
						bzhigh = Double.valueOf(biaozhunhigh);
					} catch (Exception e) {}
				}
					
				if (StringUtil.Null2Blank(biaozhun2).length()>0) {
					try {
						bz2 = Double.valueOf(biaozhun2);
					} catch (Exception e) {}
				}
				
				if (StringUtil.Null2Blank(biaozhunhigh2).length()>0) {
					try {
						bzhigh2 = Double.valueOf(biaozhunhigh2);
					} catch (Exception e) {}
				}
				
				if (StringUtil.Null2Blank(biaozhunlowRight2).length()>0) {
					try {
						bzRight2 = Double.valueOf(biaozhunlowRight2);
					} catch (Exception e) {}
				}
				
				if (StringUtil.Null2Blank(biaozhuanhighRight2).length()>0) {
					try {
						bzhighRight2 = Double.valueOf(biaozhuanhighRight2);
					} catch (Exception e) {}
				}
					
				if (StringUtil.Null2Blank(biaozhun3).length()>0 ) {
					try {
						bz3 = Double.valueOf(biaozhun3);
					} catch (Exception e) {}
				}
				
				if (StringUtil.Null2Blank(biaozhunhigh3).length()>0 ) {
					try {
						bzhigh3 = Double.valueOf(biaozhunhigh3);
					} catch (Exception e) {}
				}
					
				double lilun = -1;
				boolean issheji = false;
				if (StringUtil.Null2Blank(lilunzhi).length()>0) {
					try {
						lilun = Double.valueOf(lilunzhi);
						sjwucha = Double.valueOf(wucha);
					} catch (Exception e) {}
				} else if (StringUtil.Null2Blank(sheji).length()>0) {
					try {
						lilun = Double.valueOf(sheji);
						sjwucha = shiji - lilun;
						issheji = true;
					} catch (Exception e) {}
				}
					
				String gujifs = hv.getChangliang();
				boolean slok = false;
				if (StringUtil.Null2Blank(gujifs).length()>0) {
					try {
						if (Double.valueOf(gujifs)>200) {
							slok = true;
						}
					} catch (Exception e) {}
				}
				if (lilun > 0 && shiji > 0 && Math.abs(sjwucha) > 0 && slok && (ziduantype!=0 || sjwucha<0)) {
					String text = "";
					//初级
					if(bz!=-1 && bzhigh!=-1){
						if(Math.abs(sjwucha)>=Math.abs(bz) && Math.abs(sjwucha)<=Math.abs(bzhigh)){
							text="初超";
							finalbz = bz;
							denji1 = true;
						}
					}
					if(ziduantype==1 || ziduantype==11){  //表示用于油石比、沥青模块
						//中级
						if((bz2!=-1 && bz2<0) && (bzhigh2!=-1 && bzhigh2<0) &&
						   (bzRight2!=-1 && bzRight2>0) && (bzhighRight2!=-1 && bzhighRight2>0)){
							if(sjwucha<0){
								if(sjwucha<bz2 && sjwucha>=bzhigh2){
									text="中超";
									finalbz = Math.abs(bz2);
									denji2 = true;
									denji1 = true;
								}
							}
							if(sjwucha>0){
								if(sjwucha>bzRight2 && sjwucha<=bzhighRight2){
									text="中超";
									finalbz = bz2;
									denji2 = true;
									denji1 = true;
								}
							}
						}
						//高级
						if((bz3!=0 && bz3<0) && (bzhigh3!=0 && bzhigh3>0)){
							if(sjwucha<0){
								if(sjwucha<bz3){
									finalbz = Math.abs(bz3);
									text="高超";
									denji3 = true;
									denji2 = true;
									denji1 = true;
								}
							}
							if(sjwucha>0){
								if(sjwucha>bzhigh3){
									finalbz = bz3;
									text="高超";
									denji3 = true;
									denji2 = true;
									denji1 = true;
								}
							}
						}
						
					}else{  //其他材料
						//中级
						if(bz2!=-1 && bzhigh2!=-1){
							if(Math.abs(sjwucha)>Math.abs(bz2) && Math.abs(sjwucha)<=Math.abs(bzhigh2)){
								text="中超";
								finalbz = bz2;
								denji2 = true;
								denji1 = true;
							}
						}
						//高级
						if(bz3!=-1){
							if(Math.abs(sjwucha)>Math.abs(bz3) && bzhigh3==-1){
								finalbz = bz3;
								text="高超";
								denji3 = true;
								denji2 = true;
								denji1 = true;
							}
						}
					}
					
					if (finalbz>0) {	
					    int biaozhuntype=0;
					    if (sjwucha > 0) {
							biaozhuntype = 1;
						}
					    if(StringUtil.Null2Blank(issms).equalsIgnoreCase("1") && StringUtil.Null2Blank(smsbaojin).equalsIgnoreCase("1")){
							setLqSmsTypeDb(filename, bianhao, shebeibianhao, denji2, denji3, biaozhuntype, ziduantype);
							sms=fieldname+"实"+String.format("%1$.2f",shiji)+"%理"+String.format("%1$.2f",lilun)+"%误"+String.format("%1$.2f", sjwucha)+"%"+"("+text+")";
						}		
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} 
		return sms;
	}	

	
	@Action("sms")
	public String sms() {
			ActionContext context = ActionContext.getContext(); 
			HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
			+"classes"+File.separator+"sms.ini";			
			File fp = new File(filename);			
			if(!fp.exists()){
				try {
					fp.createNewFile();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if(fp.exists()){
				try {
					Properties prop=new Properties();
					prop.load(new FileInputStream(fp));					
					String maxbh = prop.getProperty("bianhao", "1");			
					String curstrbh = prop.getProperty("curbianhao", "0");	
					Integer curbh = Integer.parseInt(curstrbh);
					if (curbh == 0) {
						List qlist = dataService.findTop();
						if (qlist.size()>0) {
						  TopRealMaxhunnintuView tophv = (TopRealMaxhunnintuView)qlist.get(0);
						  curbh = tophv.getBianhao();
						  FileOutputStream fos = new FileOutputStream(fp);
						  prop.setProperty("curbianhao", String.valueOf(curbh));
						  prop.store(fos, "sms");
						  fos.close();
						}
					}
										
					if (curbh > 0 && curbh <= Integer.parseInt(maxbh)) {
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("curbianhao", String.valueOf(curbh+1));
						prop.store(fos, "sms");
						fos.close();
						Hntview hv = dataService.getHviewById(curbh);
						if (null != hv) {
							String shebeibianhao = hv.getShebeibianhao();
							Hntbhzziduancfg hntissms = sysService.hntissmsfindBybh(shebeibianhao);
							Hntbhzziduancfg hntsmslow = sysService.hntsmslowfindBybh(shebeibianhao);
							Hntbhzziduancfg hntsmshigh = sysService.hntsmshighfindBybh(shebeibianhao);
							Hntbhzziduancfg hntsmsnumber = sysService.hntsmsnumberfindBybh(shebeibianhao);
							Hntbhzziduancfg hntsmscontent = sysService.hntsmscontentfindBybh(shebeibianhao);
							HntbhzziduancfgView hntfieldname = sysService.hntfieldnamefindBybh(shebeibianhao);
							Hntbhzziduancfg hntsmssheji = sysService.hntsmsshejifindBybh(shebeibianhao);
							Hntbhzziduancfg hntsmssheji2 = sysService.hntsmsshejifindBybh2(shebeibianhao);
							Hntbhzziduancfg hntsmslow2 = sysService.hntsmslowfindBybh2(shebeibianhao);
							Hntbhzziduancfg hntsmshigh2 = sysService.hntsmshighfindBybh2(shebeibianhao);
							Hntbhzziduancfg hntsmsnumber2 = sysService.hntsmsnumberfindBybh2(shebeibianhao);
							Hntbhzziduancfg hntsmslow3 = sysService.hntsmslowfindBybh3(shebeibianhao);
							Hntbhzziduancfg hntsmshigh3 = sysService.hntsmshighfindBybh3(shebeibianhao);
							Hntbhzziduancfg hntsmsnumber3 = sysService.hntsmsnumberfindBybh3(shebeibianhao);
							Hntbhzziduancfg hntisshowcfg = sysService.hntisshowcfgBybh(shebeibianhao);
							
							if (null != hntissms && null != hntsmslow && null != hntsmshigh 
									&& null != hntsmsnumber && null != hntsmscontent 
									&& null != hntsmssheji && null != hntsmslow2 && null != hntsmshigh2 
									&& null != hntsmsnumber2 && null != hntsmslow3 && null != hntsmshigh3 
									&& null != hntsmsnumber3) {
								if (hv.getJiaozuobuwei().indexOf("喷浆")>-1 || hv.getJiaozuobuwei().indexOf("初支")>-1
										|| hv.getJiaozuobuwei().indexOf("喷射")>-1 || hv.getJiaozuobuwei().indexOf("CFG")>-1) {
									hntsmssheji = hntsmssheji2;
								}
								filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
								+"classes"+File.separator+"system.ini";
								fp = new File(filename);
								int onefrequency = 1;
								int ps = 100;
								String panshu = "100";
								if(fp.exists()){
									prop=new Properties();
									try {
										prop.load(new FileInputStream(fp));
										try {
											onefrequency = Integer.parseInt(prop.getProperty("onefrequency", "1"));
										} catch (Exception e) {
										}
										panshu = StringUtil.Null2Blank(prop.getProperty("panshu"));
								} catch (FileNotFoundException e) {
									logger.error(e.getMessage());
								} catch (IOException e) {
									logger.error(e.getMessage());
								}
								}
								
								if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
									panshu = StringUtil.Null2Blank(hv.getPanshu());
								} 
								
								if (panshu.length() > 0) {
									try {
										ps = Integer.valueOf(panshu);
									} catch (Exception e) {
										ps = 100;
									}
								}	
								Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
								if (null == xxsms) {
									xxsms = new Xiangxixxsms();
									xxsms.setShebeibianhao(shebeibianhao);
									xxsms.setLeiji(0);
									xxsms.setPanshu(1);
								} else {
									if (xxsms.getPanshu() >= ps) {
										xxsms.setPanshu(1);
										xxsms.setLeiji(0);
									} else {
										xxsms.setPanshu(xxsms.getPanshu()+1);
									}									
								}
								sysService.saveXxsms(xxsms);
								
								int sendcount = 0;
								//搅拌时间
								if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getJiaobanshijian(), hntsmslow.getJiaobanshijian(),
										hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getJiaobanshijian(), hntsmslow2.getJiaobanshijian(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getJiaobanshijian(), hntsmslow3.getJiaobanshijian(),
										hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getJiaobanshijian(), hv.getJiaobanshijian(),
										"", hntsmssheji.getJiaobanshijian(),
										hntsmscontent.getJiaobanshijian(), hntfieldname.getJiaobanshijian(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(),
										hv.getBanhezhanminchen(), 0, 0, sendcount, onefrequency)) {
									sendcount++;
								}
								//水泥1
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShuini1_shijizhi(), hntsmslow.getShuini1_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShuini1_shijizhi(), hntsmslow2.getShuini1_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShuini1_shijizhi(), hntsmslow3.getShuini1_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getShuini1_shijizhi(), hv.getShuini1_shijizhi(),
									hv.getShuini1_lilunzhi(), hntsmssheji.getShuini1_shijizhi(),
									hntsmscontent.getShuini1_shijizhi(), hntfieldname.getShuini1_shijizhi(),
									hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 1, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShuini1_shijizhi(), hntsmshigh.getShuini1_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShuini1_shijizhi(), hntsmshigh2.getShuini1_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShuini1_shijizhi(), hntsmshigh3.getShuini1_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getShuini1_shijizhi(), hv.getShuini1_shijizhi(),
										hv.getShuini1_lilunzhi(), hntsmssheji.getShuini1_shijizhi(),
										hntsmscontent.getShuini1_shijizhi(), hntfieldname.getShuini1_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 1, sendcount, onefrequency)){
										sendcount++;
									}
								} else {
									sendcount++;
								}
								//水泥2
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShuini2_shijizhi(), hntsmslow.getShuini2_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShuini2_shijizhi(), hntsmslow2.getShuini2_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShuini2_shijizhi(), hntsmslow3.getShuini2_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getShuini2_shijizhi(), hv.getShuini2_shijizhi(),
										hv.getShuini2_lilunzhi(), hntsmssheji.getShuini2_shijizhi(),
										hntsmscontent.getShuini2_shijizhi(), hntfieldname.getShuini2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 2, sendcount, onefrequency)) {
								if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShuini2_shijizhi(), hntsmshigh.getShuini2_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShuini2_shijizhi(), hntsmshigh2.getShuini2_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShuini2_shijizhi(), hntsmshigh3.getShuini2_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getShuini2_shijizhi(), hv.getShuini2_shijizhi(),
										hv.getShuini2_lilunzhi(), hntsmssheji.getShuini2_shijizhi(),
										hntsmscontent.getShuini2_shijizhi(), hntfieldname.getShuini2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 2, sendcount, onefrequency)) {
									sendcount++;
								}
								} else {
									sendcount++;
								}
								//水1
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShui1_shijizhi(), hntsmslow.getShui1_shijizhi(),
										hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getShui1_shijizhi(), hntsmslow2.getShui1_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShui1_shijizhi(), hntsmslow3.getShui1_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getShui1_shijizhi(), hv.getShui1_shijizhi(),
										hv.getShui1_lilunzhi(), hntsmssheji.getShui1_shijizhi(),
										hntsmscontent.getShui1_shijizhi(), hntfieldname.getShui1_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 3, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShui1_shijizhi(), hntsmshigh.getShui1_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShui1_shijizhi(), hntsmshigh2.getShui1_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShui1_shijizhi(), hntsmshigh3.getShui1_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getShui1_shijizhi(), hv.getShui1_shijizhi(),
										hv.getShui1_lilunzhi(), hntsmssheji.getShui1_shijizhi(),
										hntsmscontent.getShui1_shijizhi(), hntfieldname.getShui1_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 3, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//水2
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShui2_shijizhi(), hntsmslow.getShui2_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShui2_shijizhi(), hntsmslow2.getShui2_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShui2_shijizhi(), hntsmslow3.getShui2_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getShui2_shijizhi(), hv.getShui2_shijizhi(),
										hv.getShui2_lilunzhi(), hntsmssheji.getShui2_shijizhi(),
										hntsmscontent.getShui2_shijizhi(), hntfieldname.getShui2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 4, sendcount, onefrequency)){
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShui2_shijizhi(), hntsmshigh.getShui2_shijizhi(),
											hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getShui2_shijizhi(), hntsmshigh2.getShui2_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShui2_shijizhi(), hntsmshigh3.getShui2_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getShui2_shijizhi(), hv.getShui2_shijizhi(),
										hv.getShui2_lilunzhi(), hntsmssheji.getShui2_shijizhi(),
										hntsmscontent.getShui2_shijizhi(), hntfieldname.getShui2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 4, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//矿粉3
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getKuangfen3_shijizhi(), hntsmslow.getKuangfen3_shijizhi(),
										hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getKuangfen3_shijizhi(), hntsmslow2.getKuangfen3_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getKuangfen3_shijizhi(), hntsmslow3.getKuangfen3_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getKuangfen3_shijizhi(), hv.getKuangfen3_shijizhi(),
										hv.getKuangfen3_lilunzhi(), hntsmssheji.getKuangfen3_shijizhi(),
										hntsmscontent.getKuangfen3_shijizhi(), 
										hntfieldname.getKuangfen3_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 5, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getKuangfen3_shijizhi(), hntsmshigh.getKuangfen3_shijizhi(),
											hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getKuangfen3_shijizhi(), hntsmshigh2.getKuangfen3_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getKuangfen3_shijizhi(), hntsmshigh3.getKuangfen3_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getKuangfen3_shijizhi(), hv.getKuangfen3_shijizhi(),
										hv.getKuangfen3_lilunzhi(), hntsmssheji.getKuangfen3_shijizhi(),
										hntsmscontent.getKuangfen3_shijizhi(), 
										hntfieldname.getKuangfen3_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 5, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//粉煤灰4
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getFeimeihui4_shijizhi(), hntsmslow.getFeimeihui4_shijizhi(),
										hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getFeimeihui4_shijizhi(), hntsmslow2.getFeimeihui4_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getFeimeihui4_shijizhi(), hntsmslow3.getFeimeihui4_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getFeimeihui4_shijizhi(), hv.getFeimeihui4_shijizhi(),
										hv.getFeimeihui4_lilunzhi(), hntsmssheji.getFeimeihui4_shijizhi(),
										hntsmscontent.getFeimeihui4_shijizhi(), 
										hntfieldname.getFeimeihui4_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 6, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getFeimeihui4_shijizhi(), hntsmshigh.getFeimeihui4_shijizhi(),
											hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getFeimeihui4_shijizhi(), hntsmshigh2.getFeimeihui4_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getFeimeihui4_shijizhi(), hntsmshigh3.getFeimeihui4_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getFeimeihui4_shijizhi(), hv.getFeimeihui4_shijizhi(),
										hv.getFeimeihui4_lilunzhi(), hntsmssheji.getFeimeihui4_shijizhi(),
										hntsmscontent.getFeimeihui4_shijizhi(), 
										hntfieldname.getFeimeihui4_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 6, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//粉料5
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getFenliao5_shijizhi(), hntsmslow.getFenliao5_shijizhi(),
										hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getFenliao5_shijizhi(), hntsmslow2.getFenliao5_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getFenliao5_shijizhi(), hntsmslow3.getFenliao5_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getFenliao5_shijizhi(), hv.getFenliao5_shijizhi(),
										hv.getFenliao5_lilunzhi(), hntsmssheji.getFenliao5_shijizhi(),
										hntsmscontent.getFenliao5_shijizhi(), 
										hntfieldname.getFenliao5_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 7, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getFenliao5_shijizhi(), hntsmshigh.getFenliao5_shijizhi(),
											hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getFenliao5_shijizhi(), hntsmshigh2.getFenliao5_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getFenliao5_shijizhi(), hntsmshigh3.getFenliao5_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(), hntsmsnumber3.getFenliao5_shijizhi(), hv.getFenliao5_shijizhi(),
										hv.getFenliao5_lilunzhi(), hntsmssheji.getFenliao5_shijizhi(),
										hntsmscontent.getFenliao5_shijizhi(), 
										hntfieldname.getFenliao5_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 7, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//粉料6
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getFenliao6_shijizhi(), hntsmslow.getFenliao6_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getFenliao6_shijizhi(), hntsmslow2.getFenliao6_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(),hntsmsnumber2.getFenliao6_shijizhi(), hntsmslow3.getFenliao6_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getFenliao6_shijizhi(), hv.getFenliao6_shijizhi(),
										hv.getFenliao6_lilunzhi(), hntsmssheji.getFenliao6_shijizhi(),
										hntsmscontent.getFenliao6_shijizhi(), 
										hntfieldname.getFenliao6_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 8, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getFenliao6_shijizhi(), hntsmshigh.getFenliao6_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getFenliao6_shijizhi(), hntsmshigh2.getFenliao6_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getFenliao6_shijizhi(), hntsmshigh3.getFenliao6_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getFenliao6_shijizhi(), hv.getFenliao6_shijizhi(),
										hv.getFenliao6_lilunzhi(), hntsmssheji.getFenliao6_shijizhi(),
										hntsmscontent.getFenliao6_shijizhi(), 
										hntfieldname.getFenliao6_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 8, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//骨料1
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getSha1_shijizhi(), hntsmslow.getSha1_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getSha1_shijizhi(), hntsmslow2.getSha1_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(),hntsmsnumber2.getSha1_shijizhi(), hntsmslow3.getSha1_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getSha1_shijizhi(), hv.getSha1_shijizhi(),
										hv.getSha1_lilunzhi(), hntsmssheji.getSha1_shijizhi(),
										hntsmscontent.getSha1_shijizhi(), 
										hntfieldname.getSha1_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 9, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getSha1_shijizhi(), hntsmshigh.getSha1_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getSha1_shijizhi(), hntsmshigh2.getSha1_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(),hntsmsnumber2.getSha1_shijizhi(), hntsmshigh3.getSha1_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getSha1_shijizhi(), hv.getSha1_shijizhi(),
										hv.getSha1_lilunzhi(), hntsmssheji.getSha1_shijizhi(),
										hntsmscontent.getSha1_shijizhi(), 
										hntfieldname.getSha1_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 9, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//骨料2
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShi1_shijizhi(), hntsmslow.getShi1_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShi1_shijizhi(), hntsmslow2.getShi1_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShi1_shijizhi(), hntsmslow3.getShi1_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getShi1_shijizhi(), hv.getShi1_shijizhi(),
										hv.getShi1_lilunzhi(), hntsmssheji.getShi1_shijizhi(),
										hntsmscontent.getShi1_shijizhi(), 
										hntfieldname.getShi1_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 10, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShi1_shijizhi(), hntsmshigh.getShi1_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShi1_shijizhi(), hntsmshigh2.getShi1_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShi1_shijizhi(), hntsmshigh3.getShi1_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getShi1_shijizhi(), hv.getShi1_shijizhi(),
										hv.getShi1_lilunzhi(), hntsmssheji.getShi1_shijizhi(),
										hntsmscontent.getShi1_shijizhi(), 
										hntfieldname.getShi1_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 10, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//骨料3
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getSha2_shijizhi(), hntsmslow.getSha2_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getSha2_shijizhi(), hntsmslow2.getSha2_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getSha2_shijizhi(), hntsmslow3.getSha2_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getSha2_shijizhi(), hv.getSha2_shijizhi(),
										hv.getSha2_lilunzhi(), hntsmssheji.getSha2_shijizhi(),
										hntsmscontent.getSha2_shijizhi(), 
										hntfieldname.getSha2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 11, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getSha2_shijizhi(), hntsmshigh.getSha2_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getSha2_shijizhi(), hntsmshigh2.getSha2_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getSha2_shijizhi(), hntsmshigh3.getSha2_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getSha2_shijizhi(), hv.getSha2_shijizhi(),
										hv.getSha2_lilunzhi(), hntsmssheji.getSha2_shijizhi(),
										hntsmscontent.getSha2_shijizhi(), 
										hntfieldname.getSha2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 11, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//骨料4
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShi2_shijizhi(), hntsmslow.getShi2_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShi2_shijizhi(), hntsmslow2.getShi2_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShi2_shijizhi(), hntsmslow3.getShi2_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getShi2_shijizhi(), hv.getShi2_shijizhi(),
										hv.getShi2_lilunzhi(), hntsmssheji.getShi2_shijizhi(),
										hntsmscontent.getShi2_shijizhi(), 
										hntfieldname.getShi2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 12, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getShi2_shijizhi(), hntsmshigh.getShi2_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getShi2_shijizhi(), hntsmshigh2.getShi2_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getShi2_shijizhi(), hntsmshigh3.getShi2_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getShi2_shijizhi(), hv.getShi2_shijizhi(),
										hv.getShi2_lilunzhi(), hntsmssheji.getShi2_shijizhi(),
										hntsmscontent.getShi2_shijizhi(), 
										hntfieldname.getShi2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 12, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//骨料5
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getGuliao5_shijizhi(), hntsmslow.getGuliao5_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getGuliao5_shijizhi(), hntsmslow2.getGuliao5_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getGuliao5_shijizhi(), hntsmslow3.getGuliao5_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getGuliao5_shijizhi(), hv.getGuliao5_shijizhi(),
										hv.getGuliao5_lilunzhi(), hntsmssheji.getGuliao5_shijizhi(),
										hntsmscontent.getGuliao5_shijizhi(), 
										hntfieldname.getGuliao5_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 13, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getGuliao5_shijizhi(), hntsmshigh.getGuliao5_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getGuliao5_shijizhi(), hntsmshigh2.getGuliao5_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getGuliao5_shijizhi(), hntsmshigh3.getGuliao5_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getGuliao5_shijizhi(), hv.getGuliao5_shijizhi(),
										hv.getGuliao5_lilunzhi(), hntsmssheji.getGuliao5_shijizhi(),
										hntsmscontent.getGuliao5_shijizhi(), 
										hntfieldname.getGuliao5_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 13, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//外加剂1
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getWaijiaji1_shijizhi(), hntsmslow.getWaijiaji1_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getWaijiaji1_shijizhi(), hntsmslow2.getWaijiaji1_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getWaijiaji1_shijizhi(), hntsmslow3.getWaijiaji1_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getWaijiaji1_shijizhi(), hv.getWaijiaji1_shijizhi(),
										hv.getWaijiaji1_lilunzhi(), hntsmssheji.getWaijiaji1_shijizhi(),
										hntsmscontent.getWaijiaji1_shijizhi(), 
										hntfieldname.getWaijiaji1_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 14, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getWaijiaji1_shijizhi(), hntsmshigh.getWaijiaji1_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getWaijiaji1_shijizhi(), hntsmshigh2.getWaijiaji1_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getWaijiaji1_shijizhi(), hntsmshigh3.getWaijiaji1_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getWaijiaji1_shijizhi(), hv.getWaijiaji1_shijizhi(),
										hv.getWaijiaji1_lilunzhi(), hntsmssheji.getWaijiaji1_shijizhi(),
										hntsmscontent.getWaijiaji1_shijizhi(), 
										hntfieldname.getWaijiaji1_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 14, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//外加剂2
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getWaijiaji2_shijizhi(), hntsmslow.getWaijiaji2_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getWaijiaji2_shijizhi(), hntsmslow2.getWaijiaji2_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getWaijiaji2_shijizhi(), hntsmslow3.getWaijiaji2_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getWaijiaji2_shijizhi(), hv.getWaijiaji2_shijizhi(),
										hv.getWaijiaji2_lilunzhi(), hntsmssheji.getWaijiaji2_shijizhi(),
										hntsmscontent.getWaijiaji2_shijizhi(), 
										hntfieldname.getWaijiaji2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 15, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getWaijiaji2_shijizhi(), hntsmshigh.getWaijiaji2_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getWaijiaji2_shijizhi(), hntsmshigh2.getWaijiaji2_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getWaijiaji2_shijizhi(), hntsmshigh3.getWaijiaji2_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getWaijiaji2_shijizhi(), hv.getWaijiaji2_shijizhi(),
										hv.getWaijiaji2_lilunzhi(), hntsmssheji.getWaijiaji2_shijizhi(),
										hntsmscontent.getWaijiaji2_shijizhi(), 
										hntfieldname.getWaijiaji2_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 15, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//外加剂3
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getWaijiaji3_shijizhi(), hntsmslow.getWaijiaji3_shijizhi(),
										hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getWaijiaji3_shijizhi(), hntsmslow2.getWaijiaji3_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getWaijiaji3_shijizhi(), hntsmslow3.getWaijiaji3_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getWaijiaji3_shijizhi(), hv.getWaijiaji3_shijizhi(),
										hv.getWaijiaji3_lilunzhi(), hntsmssheji.getWaijiaji3_shijizhi(),
										hntsmscontent.getWaijiaji3_shijizhi(), 
										hntfieldname.getWaijiaji3_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 16, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getWaijiaji3_shijizhi(), hntsmshigh.getWaijiaji3_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getWaijiaji3_shijizhi(), hntsmshigh2.getWaijiaji3_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getWaijiaji3_shijizhi(), hntsmshigh3.getWaijiaji3_shijizhi(),
											hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getWaijiaji3_shijizhi(), hv.getWaijiaji3_shijizhi(),
										hv.getWaijiaji3_lilunzhi(), hntsmssheji.getWaijiaji3_shijizhi(),
										hntsmscontent.getWaijiaji3_shijizhi(), 
										hntfieldname.getWaijiaji3_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 16, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
								//外加剂4
								if (dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getWaijiaji4_shijizhi(), hntsmslow.getWaijiaji4_shijizhi(),
										hntsmsnumber.getJiaobanshijian(), hntsmsnumber.getWaijiaji4_shijizhi(), hntsmslow2.getWaijiaji4_shijizhi(),
										hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getWaijiaji4_shijizhi(), hntsmslow3.getWaijiaji4_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getWaijiaji4_shijizhi(), hv.getWaijiaji4_shijizhi(),
										hv.getWaijiaji4_lilunzhi(), hntsmssheji.getWaijiaji4_shijizhi(),
										hntsmscontent.getWaijiaji4_shijizhi(), 
										hntfieldname.getWaijiaji4_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 0, 17, sendcount, onefrequency)) {
									if (!dosms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), hntissms.getWaijiaji4_shijizhi(), hntsmshigh.getWaijiaji4_shijizhi(),
											hntsmsnumber.getJiaobanshijian(),hntsmsnumber.getWaijiaji4_shijizhi(), hntsmshigh2.getWaijiaji4_shijizhi(),
											hntsmsnumber2.getJiaobanshijian(), hntsmsnumber2.getWaijiaji4_shijizhi(), hntsmshigh3.getWaijiaji4_shijizhi(),
										hntsmsnumber3.getJiaobanshijian(),hntsmsnumber3.getWaijiaji4_shijizhi(), hv.getWaijiaji4_shijizhi(),
										hv.getWaijiaji4_lilunzhi(), hntsmssheji.getWaijiaji4_shijizhi(),
										hntsmscontent.getWaijiaji4_shijizhi(), 
										hntfieldname.getWaijiaji4_shijizhi(),
										hv.getBaocunshijian(), hv.getChuliaoshijian(), hntisshowcfg.getChuliaoshijian(), hv.getBanhezhanminchen(), 1, 17, sendcount, onefrequency)) {
											sendcount++;
										}
								} else {
									sendcount++;
								}
							}
						}
					}
				} catch (FileNotFoundException e) {
					logger.error(e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		return null;
	}
	
	
	@Action("lqsms")
	public String lqsms() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"classes"+File.separator+"sms.ini";			
		File fp = new File(filename);			
		if(!fp.exists()){
			try {
				fp.createNewFile();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		if(fp.exists()){
			try {
				Properties prop=new Properties();
				prop.load(new FileInputStream(fp));					
				String maxbh = prop.getProperty("lqbianhao", "1");			
				String curstrbh = prop.getProperty("lqcurbianhao", "0");	
				Integer curbh = Integer.parseInt(curstrbh);
				if (curbh == 0) {
					List qlist = dataService.findLiqingTop();
					if (qlist.size()>0) {
						TopRealMaxLiqingView tophv = (TopRealMaxLiqingView)qlist.get(0);
						curbh = tophv.getBianhao();
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("lqcurbianhao", String.valueOf(curbh));
						prop.store(fos, "sms");
						fos.close();
					}
				}
										
				if (curbh > 0 && curbh <= Integer.parseInt(maxbh)) {
					FileOutputStream fos = new FileOutputStream(fp);
					prop.setProperty("lqcurbianhao", String.valueOf(curbh+1));
					prop.store(fos, "sms");
					fos.close();
					LiqingmanualphbView hv = dataService.getLqphbmanualviewById(curbh);
					if (null != hv) {
						String shebeibianhao = hv.getShebeibianhao();
						Liqingziduancfg lqissms = sysService.lqissmsfindBybh(shebeibianhao);
						Liqingziduancfg lqsmslow = sysService.lqsmslowfindBybh(shebeibianhao);
						Liqingziduancfg lqsmshigh = sysService.lqsmshighfindBybh(shebeibianhao);
						Liqingziduancfg lqsmsnumber = sysService.lqsmsnumberfindBybh(shebeibianhao);
						Liqingziduancfg lqsmscontent = sysService.lqsmscontentfindBybh(shebeibianhao);
						LiqingziduancfgView lqfieldname = sysService.lqfieldnamefindBybh(shebeibianhao);
						Liqingziduancfg lqsmssheji = sysService.lqsmsshejifindBybh(shebeibianhao);
						Liqingziduancfg lqsmslow2 = sysService.lqsmslowfindBybh2(shebeibianhao);
						Liqingziduancfg lqsmshigh2 = sysService.lqsmshighfindBybh2(shebeibianhao);
						//捷博高速油石比
						Liqingziduancfg lqsmslowRight2=sysService.lqsmslowRightfindBybh2(shebeibianhao);
						Liqingziduancfg lqsmshighRight2=sysService.lqsmshighRightfindBybh2(shebeibianhao);
						Liqingziduancfg lqsmsnumber2 = sysService.lqsmsnumberfindBybh2(shebeibianhao);
						Liqingziduancfg lqsmslow3 = sysService.lqsmslowfindBybh3(shebeibianhao);
						Liqingziduancfg lqsmshigh3 = sysService.lqsmshighfindBybh3(shebeibianhao);
						Liqingziduancfg lqsmsnumber3 = sysService.lqsmsnumberfindBybh3(shebeibianhao);
						Liqingziduancfg lqisshowcfg = sysService.liqingisshowcfgBybh(shebeibianhao);
						filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"classes"+File.separator+"system.ini";
						fp = new File(filename);
						int onefrequency = 1;
						int panduanpanshu = 10;
						int ps = 100;
							
						String apitype=null;
						String panshu = "100";
						double lowsjysb = 0.92;
						double highsjysb = 1.06;
						if(fp.exists()){
							prop=new Properties();
							try {
								prop.load(new FileInputStream(fp));
								try {
									onefrequency = Integer.parseInt(prop.getProperty("onefrequency", "1"));
								} catch (Exception e) {}
								try {
									panduanpanshu = Integer.parseInt(prop.getProperty("panduanpanshu", "10"));
								} catch (Exception e) {}
								try {
									lowsjysb = Double.parseDouble(prop.getProperty("lowsjysb"));										
								} catch (Exception e) {}
								try {
									highsjysb = Double.parseDouble(prop.getProperty("highsjysb"));										
								} catch (Exception e) {}
								try{
									panshu = StringUtil.Null2Blank(prop.getProperty("panshu"));
								}catch(Exception ex){}
								try{
									apitype = StringUtil.Null2Blank(prop.getProperty("apitype", "97"));
								}catch(Exception ex){}
							} catch (FileNotFoundException e) {
								logger.error(e.getMessage());
							} catch (IOException e) {
								logger.error(e.getMessage());
							}
						}
						//没有录入目标配合比的，那么就使用采集上来的理论值报警
						if (null != lqissms && null != lqsmslow && null != lqsmshigh 
								&& null != lqsmsnumber && null != lqsmscontent 
								&& null != lqsmssheji && null != lqsmslow2 && null != lqsmshigh2 && null!=lqsmslowRight2 && null!=lqsmshighRight2
								&& null != lqsmsnumber2 && null != lqsmslow3 && null != lqsmshigh3 
								&& null != lqsmsnumber3) {
							if (null != hv && StringUtil.Null2Blank(hv.getPanshu()).length()>0) {
								try {
									panduanpanshu = Integer.valueOf(StringUtil.Null2Blank(hv.getPanshu()));
								} catch (Exception e) {}
							} 
							
							Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
							if (null == xxsms) {
								xxsms = new Xiangxixxsms();
								xxsms.setShebeibianhao(shebeibianhao);
								xxsms.setLeiji(0);
								xxsms.setPanshu(1);
							} else {
								if (xxsms.getPanshu() >= ps) {
									xxsms.setPanshu(1);
									xxsms.setLeiji(0);
								} else {
									xxsms.setPanshu(xxsms.getPanshu()+1);
								}									
							}
							sysService.saveXxsms(xxsms);
							
						
							//0匹配理论配合比，这样可使得生产数据分析、误差配合比中有数据
							if(StringUtil.StrToFloat(hv.getLlysb())==0 || StringUtil.StrToFloat(hv.getLllq())==0){
								//匹配理论配合比
								sysService.lqsetLilunphb(hv.getBianhao(), shebeibianhao,hv.getSjysb(),lowsjysb,highsjysb);
								hv = dataService.getLqphbmanualviewById(curbh);
										
								/*-----------------------------开关机提醒------------------------------------*/
								//提取开关机生产的内容
								
								StringBuffer smspeibi=new StringBuffer("");
								StringBuffer smsbuwei=new StringBuffer("");
								if(StringUtil.Null2Blank(hv.getBiaoshi()).length()>0){
									Liqingxixxlilun lqlilun=lqllService.getBeanById(Integer.parseInt(hv.getBiaoshi()));
									smsbuwei.append("使用部位:"+lqlilun.getLlbuwei()+";");
											
									if(StringUtil.Null2Blank(hv.getSjg1()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlg1()).length()>0){
										smspeibi.append("["+lqfieldname.getSjg1()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg1()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlg1())));
										float cailiaowucha=Float.parseFloat(hv.getPersjg1())-Float.parseFloat(lqlilun.getLlg1());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
													
									if(StringUtil.Null2Blank(hv.getSjg2()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlg2()).length()>0){
										smspeibi.append("["+lqfieldname.getSjg2()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg2()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlg2())));
										float cailiaowucha=Float.parseFloat(hv.getPersjg2())-Float.parseFloat(lqlilun.getLlg2());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
												
									if(StringUtil.Null2Blank(hv.getSjg3()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlg3()).length()>0){
										smspeibi.append("["+lqfieldname.getSjg3()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg3()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlg3())));
										float cailiaowucha=Float.parseFloat(hv.getPersjg3())-Float.parseFloat(lqlilun.getLlg3());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
													
									if(StringUtil.Null2Blank(hv.getSjg4()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlg4()).length()>0){
										smspeibi.append("["+lqfieldname.getSjg4()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg4()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlg4())));
										float cailiaowucha=Float.parseFloat(hv.getPersjg4())-Float.parseFloat(lqlilun.getLlg4());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
													
									if(StringUtil.Null2Blank(hv.getSjg5()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlg5()).length()>0){
										smspeibi.append("["+lqfieldname.getSjg5()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg5()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlg5())));
										float cailiaowucha=Float.parseFloat(hv.getPersjg5())-Float.parseFloat(lqlilun.getLlg5());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
													
									if(StringUtil.Null2Blank(hv.getSjg6()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlg6()).length()>0){
										smspeibi.append("["+lqfieldname.getSjg6()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg6()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlg6())));
										float cailiaowucha=Float.parseFloat(hv.getPersjg6())-Float.parseFloat(lqlilun.getLlg6());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
													
									if(StringUtil.Null2Blank(hv.getSjg7()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlg7()).length()>0){
										smspeibi.append("["+lqfieldname.getSjg7()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg7()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlg7())));
										float cailiaowucha=Float.parseFloat(hv.getPersjg7())-Float.parseFloat(lqlilun.getLlg7());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
													
									if(StringUtil.Null2Blank(hv.getSjf1()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlf1()).length()>0){
										smspeibi.append("["+lqfieldname.getSjf1()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjf1()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlf1())));
										float cailiaowucha=Float.parseFloat(hv.getPersjf1())-Float.parseFloat(lqlilun.getLlf1());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
													
									if(StringUtil.Null2Blank(hv.getSjf2()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlf2()).length()>0){
										smspeibi.append("["+lqfieldname.getSjf2()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjf2()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlf2())));
										float cailiaowucha=Float.parseFloat(hv.getPersjf2())-Float.parseFloat(lqlilun.getLlf2());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
													
									if(StringUtil.Null2Blank(hv.getSjtjj()).length()>0 && StringUtil.Null2Blank(lqlilun.getLltjj()).length()>0){
										smspeibi.append("["+lqfieldname.getSjtjj()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjtjj()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLltjj())));
										float cailiaowucha=Float.parseFloat(hv.getPersjtjj())-Float.parseFloat(lqlilun.getLltjj());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
											
									if(StringUtil.Null2Blank(hv.getSjlq()).length()>0 && StringUtil.Null2Blank(lqlilun.getLllq()).length()>0){
										smspeibi.append("["+lqfieldname.getSjlq()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjlq()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLllq())));
										float cailiaowucha=Float.parseFloat(hv.getPersjlq())-Float.parseFloat(lqlilun.getLllq());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
											
									if(StringUtil.Null2Blank(hv.getSjysb()).length()>0 && StringUtil.Null2Blank(lqlilun.getLlysb()).length()>0){
										smspeibi.append("["+lqfieldname.getSjysb()+"]");
										smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getSjysb()))+"目标配比:"+String.format("%1$.2f", Float.parseFloat(lqlilun.getLlysb())));
										float cailiaowucha=Float.parseFloat(hv.getSjysb())-Float.parseFloat(lqlilun.getLlysb());
										smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
									}
								}
												
								List<TopRealMaxLiqingView> NOOFFList = sysService.getMaxlqNOOFFList();//获取最新的数据信息列表
								for(TopRealMaxLiqingView NOOFFObj:NOOFFList){
									//判断是否开启了短信报警功能
									if(StringUtil.Null2Blank(NOOFFObj.getSmsNOOFFbaojin()).equalsIgnoreCase("1")){
										if(StringUtil.Null2Blank(NOOFFObj.getSmsNOOFFPhone()).length()>0){//判断是否输入了报警号码
															
											String smsNOOFFIntervalTime=NOOFFObj.getSmsNOOFFIntervalTime();//提醒间隔时间(分钟)
											String sms="";
											String smsmStr = "%s于%s已%s%s{%s}[%s]";
											String smsmStr2 = "%s于%s已%s[%s]";
											if(StringUtil.StrToFloat(smsNOOFFIntervalTime)>0){
												smsNOOFFIntervalTime="1";
											}
											if(StringUtil.Null2Blank(NOOFFObj.getSmsNOOFFDateTime()).length()==0){//首次开机提醒
												//待发送短信内容
											    //sms = String.format(smsmStr,"开关机提醒", NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！");
												//初始化
												sysService.updateBhzNOOFFData(NOOFFObj.getShebeibianhao(),GetDate.getNowTime("yyyy-MM-dd HH:mm:ss"),"OFF");
											}else{
												try{
													SimpleDateFormat sdflengTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
													Calendar currentDay=Calendar.getInstance();//当前日期时间
													Date baocunshijian=sdflengTime.parse(NOOFFObj.getShijian());//最新出料保存时间
													Date smsNOOFFDateTime=sdflengTime.parse(NOOFFObj.getSmsNOOFFDateTime());//上次发送短信日期
													if(NOOFFObj.getSmsNOOFFSendSign().equals("NO")){//已发送开机提醒
														currentDay.add(Calendar.HOUR, -new Integer(smsNOOFFIntervalTime));//当前日期往前推指定分钟
														if (baocunshijian.before(currentDay.getTime())) {//如果最新的保存日期在当前日期往前推间隔时间之前
															if(StringUtil.Null2Blank(smspeibi.toString()).length()>0 &&
																StringUtil.Null2Blank(smsbuwei.toString()).length()>0){
																sms = String.format(smsmStr, 
																		NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"停止生产！",smsbuwei.toString(),smspeibi.toString(),"关机提醒");
															}else{
																sms = String.format(smsmStr2, 
																		NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"停止生产！","关机提醒");
															}
															sysService.updateBhzNOOFFData(NOOFFObj.getShebeibianhao(),NOOFFObj.getBaocunshijian(),"OFF");
															//关机:发送短信
															//sysService.saveandSendSms(NOOFFObj.getShebeibianhao(), NOOFFObj.getSmsNOOFFPhone(), sms, apitype);
														}
													}else{//已发送关机提醒
														if(baocunshijian.after(smsNOOFFDateTime)){//如果关机短信已发送并且最新数据的保存时间在当前时间之后
															if(StringUtil.Null2Blank(smspeibi.toString()).length()>0 &&
																StringUtil.Null2Blank(smsbuwei.toString()).length()>0){
																sms = String.format(smsmStr, 
																		NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！",smsbuwei.toString(),smspeibi.toString(),"开机提醒");
															}else{
																sms = String.format(smsmStr2, 
																		NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！","开机提醒");
															}
															sysService.updateBhzNOOFFData(NOOFFObj.getShebeibianhao(),NOOFFObj.getBaocunshijian(),"NO");
														}
													}
													//发送开机短信
													HandSet hs = sysService.getRealPhoneNumber2(NOOFFObj.getSmsNOOFFPhone());
													String realphonenumber=null;
													String realphonename=null;
													if (null != hs) {
													    realphonenumber =hs.getPhone();
													   	realphonename =hs.getOwername();
													}
																	
													if(StringUtil.Null2Blank(realphonenumber).length()>0 && StringUtil.Null2Blank(sms).length()>0){
														sysService.saveandSendSms(hv.getBianhao(), hv.getShebeibianhao(), realphonenumber,realphonename,hv.getShijian(), sms, apitype,"lq");
													}
												}catch(Exception e){
													logger.error(e.getMessage());
												}
											}
										}
									}
								}
										
								//开始报警了
								int sendcount = 0;
								StringBuffer sms=new StringBuffer();
								//油石比
								if (StringUtil.StrToFloat(hv.getSjysb())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjysb(), lqsmslow.getSjysb(),lqsmshigh.getSjysb(),
												lqsmsnumber.getJbsj(),lqsmsnumber.getSjysb(), lqsmslow2.getSjysb(), lqsmshigh2.getSjysb(),lqsmslowRight2.getSjysb(),lqsmshighRight2.getSjysb(),
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjysb(), lqsmslow3.getSjysb(), lqsmshigh3.getSjysb(),
												lqsmsnumber3.getJbsj(),lqsmsnumber3.getSjysb(), hv.getSjysb(), hv.getManualwsjysb(),
												hv.getPerllysb(), lqsmssheji.getSjysb(),
												lqsmscontent.getSjysb(), lqfieldname.getSjysb(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 1, sendcount, onefrequency, "%%"));
								}
									
								//石料1
								if (StringUtil.StrToFloat(hv.getSjg1())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg1(), lqsmslow.getSjg1(),lqsmshigh.getSjg1(),
												lqsmsnumber.getJbsj(),lqsmsnumber.getSjg1(), lqsmslow2.getSjg1(),lqsmshigh2.getSjg1(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg1(), lqsmslow3.getSjg1(),lqsmshigh3.getSjg1(),
												lqsmsnumber3.getJbsj(),lqsmsnumber3.getSjg1(), hv.getPersjg1(),hv.getManualwsjg1(),
												hv.getPerllg1(), lqsmssheji.getSjg1(),
												lqsmscontent.getSjg1(), lqfieldname.getSjg1(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 2, sendcount, onefrequency, "%%"));
								}
										
								//石料2
								if (StringUtil.StrToFloat(hv.getSjg2())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg2(), lqsmslow.getSjg2(),lqsmshigh.getSjg2(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg2(), lqsmslow2.getSjg2(),lqsmshigh2.getSjg2(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg2(), lqsmslow3.getSjg2(),lqsmshigh3.getSjg2(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg2(), hv.getPersjg2(),hv.getManualwsjg2(),
												hv.getPerllg2(), lqsmssheji.getSjg2(),
												lqsmscontent.getSjg2(), lqfieldname.getSjg2(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 3, sendcount, onefrequency, "%%"));
								}
										
								//石料3
								if (StringUtil.StrToFloat(hv.getSjg3())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg3(), lqsmslow.getSjg3(),lqsmshigh.getSjg3(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg3(), lqsmslow2.getSjg3(),lqsmshigh2.getSjg3(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg3(), lqsmslow3.getSjg3(),lqsmshigh3.getSjg3(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg3(), hv.getPersjg3(),hv.getManualwsjg3(),
												hv.getPerllg3(), lqsmssheji.getSjg3(),
												lqsmscontent.getSjg3(), lqfieldname.getSjg3(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 4, sendcount, onefrequency, "%%"));
								}
										
								//石料4
								if (StringUtil.StrToFloat(hv.getSjg4())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg4(), lqsmslow.getSjg4(),lqsmshigh.getSjg4(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg4(), lqsmslow2.getSjg4(),lqsmshigh2.getSjg4(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg4(), lqsmslow3.getSjg4(),lqsmshigh3.getSjg4(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg4(), hv.getPersjg4(),hv.getManualwsjg4(),
												hv.getPerllg4(), lqsmssheji.getSjg4(),
												lqsmscontent.getSjg4(), lqfieldname.getSjg4(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 5, sendcount, onefrequency, "%%"));
								}
										
								//石料5
								if (StringUtil.StrToFloat(hv.getSjg5())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg5(), lqsmslow.getSjg5(),lqsmshigh.getSjg5(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg5(), lqsmslow2.getSjg5(),lqsmshigh2.getSjg5(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg5(), lqsmslow3.getSjg5(),lqsmshigh3.getSjg5(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg5(), hv.getPersjg5(),hv.getManualwsjg5(),
												hv.getPerllg5(), lqsmssheji.getSjg5(),
												lqsmscontent.getSjg5(), lqfieldname.getSjg5(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 6, sendcount, onefrequency, "%%"));
								}
										
								//石料6
								if (StringUtil.StrToFloat(hv.getSjg6())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg6(), lqsmslow.getSjg6(),lqsmshigh.getSjg6(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg6(), lqsmslow2.getSjg6(),lqsmshigh2.getSjg6(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg6(), lqsmslow3.getSjg6(),lqsmshigh3.getSjg6(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg6(), hv.getPersjg6(),hv.getManualwsjg6(),
												hv.getPerllg6(), lqsmssheji.getSjg6(),
												lqsmscontent.getSjg6(), lqfieldname.getSjg6(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 7, sendcount, onefrequency, "%%"));
								}
										
								//石料7
								if (StringUtil.StrToFloat(hv.getSjg7())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg7(), lqsmslow.getSjg7(),lqsmshigh.getSjg7(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg7(), lqsmslow2.getSjg7(),lqsmshigh2.getSjg7(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg7(), lqsmslow3.getSjg7(),lqsmshigh3.getSjg7(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg7(), hv.getPersjg7(),hv.getManualwsjg7(),
												hv.getPerllg7(), lqsmssheji.getSjg7(),
												lqsmscontent.getSjg7(), lqfieldname.getSjg7(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 8, sendcount, onefrequency, "%%"));
								}
										
								//粉料1
								if (StringUtil.StrToFloat(hv.getSjf1())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjf1(), lqsmslow.getSjf1(),lqsmshigh.getSjf1(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjf1(), lqsmslow2.getSjf1(),lqsmshigh2.getSjf1(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjf1(), lqsmslow3.getSjf1(),lqsmshigh3.getSjf1(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjf1(), hv.getPersjf1(),hv.getManualwsjf1(),
												hv.getPerllf1(), lqsmssheji.getSjf1(),
												lqsmscontent.getSjf1(), lqfieldname.getSjf1(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 9, sendcount, onefrequency, "%%"));
								}
										
								//粉料2
								if (StringUtil.StrToFloat(hv.getSjf2())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjf2(), lqsmslow.getSjf2(),lqsmshigh.getSjf2(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjf2(), lqsmslow2.getSjf2(),lqsmshigh2.getSjf2(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjf2(), lqsmslow3.getSjf2(),lqsmshigh3.getSjf2(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjf2(), hv.getPersjf2(),hv.getManualwsjf2(),
												hv.getPerllf2(), lqsmssheji.getSjf2(),
												lqsmscontent.getSjf2(), lqfieldname.getSjf2(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 10, sendcount, onefrequency, "%%"));
								}
										
								//沥青
								if (StringUtil.StrToFloat(hv.getSjlq())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjlq(), lqsmslow.getSjlq(),lqsmshigh.getSjlq(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjlq(), lqsmslow2.getSjlq(),lqsmshigh2.getSjlq(),lqsmslowRight2.getSjlq(),lqsmshighRight2.getSjlq(),
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjlq(), lqsmslow3.getSjlq(),lqsmshigh3.getSjlq(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjlq(), hv.getPersjlq(),hv.getManualwsjlq(),
												hv.getPerlllq(), lqsmssheji.getSjlq(),
												lqsmscontent.getSjlq(), lqfieldname.getSjlq(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 11, sendcount, onefrequency, "%%"));
								}
										
								//添加剂
								if (StringUtil.StrToFloat(hv.getSjtjj())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjtjj(), lqsmslow.getSjtjj(),lqsmshigh.getSjtjj(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjtjj(), lqsmslow2.getSjtjj(),lqsmshigh2.getSjtjj(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjtjj(), lqsmslow3.getSjtjj(),lqsmshigh3.getSjtjj(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjtjj(), hv.getPersjtjj(),hv.getManualwsjtjj(),
												hv.getPerlltjj(), lqsmssheji.getSjtjj(),
												lqsmscontent.getSjtjj(), lqfieldname.getSjtjj(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 12, sendcount, onefrequency, "%%"));
								}
								if(StringUtil.Null2Blank(sms.toString()).length()>0){
									doLqbaojing(hv,filename,sms.toString(),lqsmsnumber.getJbsj(),lqsmsnumber2.getJbsj(),lqsmsnumber3.getJbsj(),hv.getBaocunshijian(), hv.getShijian(),lqisshowcfg.getShijian());
								}
									
							}else{
								/*-----------------------------开关机提醒------------------------------------*/
								//提取开关机生产的内容
								StringBuffer smspeibi=new StringBuffer("");
								StringBuffer smsbuwei=new StringBuffer("");
								smsbuwei.append("混合料型号:"+hv.getPeifan()+";");
								if(StringUtil.Null2Blank(hv.getSjg1()).length()>0 && StringUtil.Null2Blank(hv.getLlg1()).length()>0){
									smspeibi.append("["+lqfieldname.getSjg1()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg1()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlg1())));
									float cailiaowucha=Float.parseFloat(hv.getPersjg1())-Float.parseFloat(hv.getLlg1());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
												
								if(StringUtil.Null2Blank(hv.getSjg2()).length()>0 && StringUtil.Null2Blank(hv.getLlg2()).length()>0){
									smspeibi.append("["+lqfieldname.getSjg2()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg2()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlg2())));
									float cailiaowucha=Float.parseFloat(hv.getPersjg2())-Float.parseFloat(hv.getLlg2());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
												
								if(StringUtil.Null2Blank(hv.getSjg3()).length()>0 && StringUtil.Null2Blank(hv.getLlg3()).length()>0){
									smspeibi.append("["+lqfieldname.getSjg3()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg3()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlg3())));
									float cailiaowucha=Float.parseFloat(hv.getPersjg3())-Float.parseFloat(hv.getLlg3());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
												
								if(StringUtil.Null2Blank(hv.getSjg4()).length()>0 && StringUtil.Null2Blank(hv.getLlg4()).length()>0){
									smspeibi.append("["+lqfieldname.getSjg4()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg4()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlg4())));
									float cailiaowucha=Float.parseFloat(hv.getPersjg4())-Float.parseFloat(hv.getLlg4());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
												
								if(StringUtil.Null2Blank(hv.getSjg5()).length()>0 && StringUtil.Null2Blank(hv.getLlg5()).length()>0){
									smspeibi.append("["+lqfieldname.getSjg5()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg5()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlg5())));
									float cailiaowucha=Float.parseFloat(hv.getPersjg5())-Float.parseFloat(hv.getLlg5());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
												
								if(StringUtil.Null2Blank(hv.getSjg6()).length()>0 && StringUtil.Null2Blank(hv.getLlg6()).length()>0){
									smspeibi.append("["+lqfieldname.getSjg6()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg6()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlg6())));
									float cailiaowucha=Float.parseFloat(hv.getPersjg6())-Float.parseFloat(hv.getLlg6());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
												
								if(StringUtil.Null2Blank(hv.getSjg7()).length()>0 && StringUtil.Null2Blank(hv.getLlg7()).length()>0){
									smspeibi.append("["+lqfieldname.getSjg7()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjg7()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlg7())));
									float cailiaowucha=Float.parseFloat(hv.getPersjg7())-Float.parseFloat(hv.getLlg7());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
												
								if(StringUtil.Null2Blank(hv.getSjf1()).length()>0 && StringUtil.Null2Blank(hv.getLlf1()).length()>0){
									smspeibi.append("["+lqfieldname.getSjf1()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjf1()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlf1())));
									float cailiaowucha=Float.parseFloat(hv.getPersjf1())-Float.parseFloat(hv.getLlf1());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
												
								if(StringUtil.Null2Blank(hv.getSjf2()).length()>0 && StringUtil.Null2Blank(hv.getLlf2()).length()>0){
									smspeibi.append("["+lqfieldname.getSjf2()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjf2()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlf2())));
									float cailiaowucha=Float.parseFloat(hv.getPersjf2())-Float.parseFloat(hv.getLlf2());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
												
								if(StringUtil.Null2Blank(hv.getSjtjj()).length()>0 && StringUtil.Null2Blank(hv.getLltjj()).length()>0){
									smspeibi.append("["+lqfieldname.getSjtjj()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjtjj()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLltjj())));
									float cailiaowucha=Float.parseFloat(hv.getPersjtjj())-Float.parseFloat(hv.getLltjj());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
									
								if(StringUtil.Null2Blank(hv.getSjlq()).length()>0 && StringUtil.Null2Blank(hv.getLllq()).length()>0){
									smspeibi.append("["+lqfieldname.getSjlq()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getPersjlq()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLllq())));
									float cailiaowucha=Float.parseFloat(hv.getPersjlq())-Float.parseFloat(hv.getLllq());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
									
								if(StringUtil.Null2Blank(hv.getSjysb()).length()>0 && StringUtil.Null2Blank(hv.getLlysb()).length()>0){
									smspeibi.append("["+lqfieldname.getSjlq()+"]");
									smspeibi.append("实际配比:"+String.format("%1$.2f",Float.parseFloat(hv.getSjysb()))+"施工配比:"+String.format("%1$.2f", Float.parseFloat(hv.getLlysb())));
									float cailiaowucha=Float.parseFloat(hv.getSjysb())-Float.parseFloat(hv.getLlysb());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
											
								List<TopRealMaxLiqingView> NOOFFList = sysService.getMaxlqNOOFFList();//获取最新的数据信息列表
								for(TopRealMaxLiqingView NOOFFObj:NOOFFList){
									//判断是否开启了短信报警功能
									if(StringUtil.Null2Blank(NOOFFObj.getSmsNOOFFbaojin()).equalsIgnoreCase("1")){
										if(StringUtil.Null2Blank(NOOFFObj.getSmsNOOFFPhone()).length()>0){//判断是否输入了报警号码
											String smsNOOFFIntervalTime=NOOFFObj.getSmsNOOFFIntervalTime();//提醒间隔时间(分钟)
											String sms="";
											String smsmStr = "%s于%s已%s%s{%s}[%s]";
											String smsmStr2 = "%s于%s已%s[%s]";
											if(StringUtil.StrToFloat(smsNOOFFIntervalTime)>0){
												smsNOOFFIntervalTime="1";
											}
											if(StringUtil.Null2Blank(NOOFFObj.getSmsNOOFFDateTime()).length()==0){//首次开机提醒
												//待发送短信内容
												//sms = String.format(smsmStr,"开关机提醒", NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！");
												//初始化
												sysService.updateBhzNOOFFData(NOOFFObj.getShebeibianhao(),GetDate.getNowTime("yyyy-MM-dd HH:mm:ss"),"OFF");
											}else{
												try{
													SimpleDateFormat sdflengTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
													Calendar currentDay=Calendar.getInstance();//当前日期时间
													Date baocunshijian=sdflengTime.parse(NOOFFObj.getShijian());//最新出料保存时间
													Date smsNOOFFDateTime=sdflengTime.parse(NOOFFObj.getSmsNOOFFDateTime());//上次发送短信日期
													if(NOOFFObj.getSmsNOOFFSendSign().equals("NO")){//已发送开机提醒
														currentDay.add(Calendar.HOUR, -new Integer(smsNOOFFIntervalTime));//当前日期往前推指定分钟
														if (baocunshijian.before(currentDay.getTime())) {//如果最新的保存日期在当前日期往前推间隔时间之前
															if(StringUtil.Null2Blank(smspeibi.toString()).length()>0 &&
																StringUtil.Null2Blank(smsbuwei.toString()).length()>0){
																sms = String.format(smsmStr, 
																		NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"停止生产！",smsbuwei.toString(),smspeibi.toString(),"关机提醒");
															}else{
																sms = String.format(smsmStr2, 
																		NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"停止生产！","关机提醒");
															}
															sysService.updateBhzNOOFFData(NOOFFObj.getShebeibianhao(),NOOFFObj.getBaocunshijian(),"OFF");
															//关机:发送短信
															//sysService.saveandSendSms(NOOFFObj.getShebeibianhao(), NOOFFObj.getSmsNOOFFPhone(), sms, apitype);
														}
													}else{//已发送关机提醒
														if(baocunshijian.after(smsNOOFFDateTime)){//如果关机短信已发送并且最新数据的保存时间在当前时间之后
															if(StringUtil.Null2Blank(smspeibi.toString()).length()>0 &&
																StringUtil.Null2Blank(smsbuwei.toString()).length()>0){
																sms = String.format(smsmStr, 
																		NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！",smsbuwei.toString(),smspeibi.toString(),"开机提醒");
																}else{
																	sms = String.format(smsmStr2, 
																				NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！","开机提醒");
																}
																sysService.updateBhzNOOFFData(NOOFFObj.getShebeibianhao(),NOOFFObj.getBaocunshijian(),"NO");
															}
														}
														//发送开机短信
														HandSet hs = sysService.getRealPhoneNumber2(NOOFFObj.getSmsNOOFFPhone());
													    String realphonenumber=null;
													    String realphonename=null;
														if (null != hs) {
													    	realphonenumber =hs.getPhone();
													    	realphonename =hs.getOwername();
														}
																	
														if(StringUtil.Null2Blank(realphonenumber).length()>0 && StringUtil.Null2Blank(sms).length()>0){
															sysService.saveandSendSms(hv.getBianhao(), hv.getShebeibianhao(), realphonenumber,realphonename,hv.getShijian(), sms, apitype,"lq");
														}
													}catch(Exception e){
														logger.error(e.getMessage());
													}
												}
											}
										}
									}
									
									//开始报警了
									int sendcount = 0;
									StringBuffer sms=new StringBuffer();
									//油石比
									if (StringUtil.StrToFloat(hv.getSjysb())>0) {
										sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjysb(), lqsmslow.getSjysb(),lqsmshigh.getSjysb(),
													lqsmsnumber.getJbsj(),lqsmsnumber.getSjysb(), lqsmslow2.getSjysb(), lqsmshigh2.getSjysb(),lqsmslowRight2.getSjysb(),lqsmshighRight2.getSjysb(),
													lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjysb(), lqsmslow3.getSjysb(), lqsmshigh3.getSjysb(),
													lqsmsnumber3.getJbsj(),lqsmsnumber3.getSjysb(), hv.getSjysb(), hv.getWsjysb(),
													hv.getLlysb(), lqsmssheji.getSjysb(),
													lqsmscontent.getSjysb(), lqfieldname.getSjysb(),
													hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 1, sendcount, onefrequency, "%%"));
									}
								
									//石料1
									if (StringUtil.StrToFloat(hv.getSjg1())>0) {
										sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg1(), lqsmslow.getSjg1(),lqsmshigh.getSjg1(),
													lqsmsnumber.getJbsj(),lqsmsnumber.getSjg1(), lqsmslow2.getSjg1(),lqsmshigh2.getSjg1(),null,null,
													lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg1(), lqsmslow3.getSjg1(),lqsmshigh3.getSjg1(),
													lqsmsnumber3.getJbsj(),lqsmsnumber3.getSjg1(), hv.getPersjg1(),hv.getWsjg1(),
													hv.getLlg1(), lqsmssheji.getSjg1(),
													lqsmscontent.getSjg1(), lqfieldname.getSjg1(),
													hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 2, sendcount, onefrequency, "%%"));
									}
									
									//石料2
									if (StringUtil.StrToFloat(hv.getSjg2())>0) {
										sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg2(), lqsmslow.getSjg2(),lqsmshigh.getSjg2(),
													lqsmsnumber.getJbsj(), lqsmsnumber.getSjg2(), lqsmslow2.getSjg2(),lqsmshigh2.getSjg2(),null,null,
													lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg2(), lqsmslow3.getSjg2(),lqsmshigh3.getSjg2(),
													lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg2(), hv.getPersjg2(),hv.getWsjg2(),
													hv.getLlg2(), lqsmssheji.getSjg2(),
													lqsmscontent.getSjg2(), lqfieldname.getSjg2(),
													hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 3, sendcount, onefrequency, "%%"));
									}
									
									//石料3
									if (StringUtil.StrToFloat(hv.getSjg3())>0) {
										sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg3(), lqsmslow.getSjg3(),lqsmshigh.getSjg3(),
													lqsmsnumber.getJbsj(), lqsmsnumber.getSjg3(), lqsmslow2.getSjg3(),lqsmshigh2.getSjg3(),null,null,
													lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg3(), lqsmslow3.getSjg3(),lqsmshigh3.getSjg3(),
													lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg3(), hv.getPersjg3(),hv.getWsjg3(),
													hv.getLlg3(), lqsmssheji.getSjg3(),
													lqsmscontent.getSjg3(), lqfieldname.getSjg3(),
													hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 4, sendcount, onefrequency, "%%"));
									}
									
								//石料4
								if (StringUtil.StrToFloat(hv.getSjg4())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg4(), lqsmslow.getSjg4(),lqsmshigh.getSjg4(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg4(), lqsmslow2.getSjg4(),lqsmshigh2.getSjg4(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg4(), lqsmslow3.getSjg4(),lqsmshigh3.getSjg4(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg4(), hv.getPersjg4(),hv.getWsjg4(),
												hv.getLlg4(), lqsmssheji.getSjg4(),
												lqsmscontent.getSjg4(), lqfieldname.getSjg4(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 5, sendcount, onefrequency, "%%"));
								}
									
								//石料5
								if (StringUtil.StrToFloat(hv.getSjg5())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg5(), lqsmslow.getSjg5(),lqsmshigh.getSjg5(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg5(), lqsmslow2.getSjg5(),lqsmshigh2.getSjg5(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg5(), lqsmslow3.getSjg5(),lqsmshigh3.getSjg5(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg5(), hv.getPersjg5(),hv.getWsjg5(),
												hv.getLlg5(), lqsmssheji.getSjg5(),
												lqsmscontent.getSjg5(), lqfieldname.getSjg5(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 6, sendcount, onefrequency, "%%"));
								}
									
								//石料6
								if (StringUtil.StrToFloat(hv.getSjg6())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg6(), lqsmslow.getSjg6(),lqsmshigh.getSjg6(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg6(), lqsmslow2.getSjg6(),lqsmshigh2.getSjg6(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg6(), lqsmslow3.getSjg6(),lqsmshigh3.getSjg6(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg6(), hv.getPersjg6(),hv.getWsjg6(),
												hv.getLlg6(), lqsmssheji.getSjg6(),
												lqsmscontent.getSjg6(), lqfieldname.getSjg6(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 7, sendcount, onefrequency, "%%"));
								}
									
								//石料7
								if (StringUtil.StrToFloat(hv.getSjg7())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjg7(), lqsmslow.getSjg7(),lqsmshigh.getSjg7(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjg7(), lqsmslow2.getSjg7(),lqsmshigh2.getSjg7(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjg7(), lqsmslow3.getSjg7(),lqsmshigh3.getSjg7(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjg7(), hv.getPersjg7(),hv.getWsjg7(),
												hv.getLlg7(), lqsmssheji.getSjg7(),
												lqsmscontent.getSjg7(), lqfieldname.getSjg7(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 8, sendcount, onefrequency, "%%"));
								}
									
								//粉料1
								if (StringUtil.StrToFloat(hv.getSjf1())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjf1(), lqsmslow.getSjf1(),lqsmshigh.getSjf1(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjf1(), lqsmslow2.getSjf1(),lqsmshigh2.getSjf1(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjf1(), lqsmslow3.getSjf1(),lqsmshigh3.getSjf1(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjf1(), hv.getPersjf1(),hv.getWsjf1(),
												hv.getLlf1(), lqsmssheji.getSjf1(),
												lqsmscontent.getSjf1(), lqfieldname.getSjf1(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 9, sendcount, onefrequency, "%%"));
								}
									
								//粉料2
								if (StringUtil.StrToFloat(hv.getSjf2())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjf2(), lqsmslow.getSjf2(),lqsmshigh.getSjf2(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjf2(), lqsmslow2.getSjf2(),lqsmshigh2.getSjf2(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjf2(), lqsmslow3.getSjf2(),lqsmshigh3.getSjf2(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjf2(), hv.getPersjf2(),hv.getWsjf2(),
												hv.getLlf2(), lqsmssheji.getSjf2(),
												lqsmscontent.getSjf2(), lqfieldname.getSjf2(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 10, sendcount, onefrequency, "%%"));
								}
									
								//沥青
								if (StringUtil.StrToFloat(hv.getSjlq())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjlq(), lqsmslow.getSjlq(),lqsmshigh.getSjlq(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjlq(), lqsmslow2.getSjlq(),lqsmshigh2.getSjlq(),lqsmslowRight2.getSjlq(),lqsmshighRight2.getSjlq(),
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjlq(), lqsmslow3.getSjlq(),lqsmshigh3.getSjlq(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjlq(), hv.getPersjlq(),hv.getWsjlq(),
												hv.getLllq(), lqsmssheji.getSjlq(),
												lqsmscontent.getSjlq(), lqfieldname.getSjlq(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 11, sendcount, onefrequency, "%%"));
								}
									
								//添加剂
								if (StringUtil.StrToFloat(hv.getSjtjj())>0) {
									sms.append(dolqmanualsms(hv, filename, hv.getBianhao(), shebeibianhao, hv.getSmsbaojin(), lqissms.getSjtjj(), lqsmslow.getSjtjj(),lqsmshigh.getSjtjj(),
												lqsmsnumber.getJbsj(), lqsmsnumber.getSjtjj(), lqsmslow2.getSjtjj(),lqsmshigh2.getSjtjj(),null,null,
												lqsmsnumber2.getJbsj(), lqsmsnumber2.getSjtjj(), lqsmslow3.getSjtjj(),lqsmshigh3.getSjtjj(),
												lqsmsnumber3.getJbsj(), lqsmsnumber3.getSjtjj(), hv.getPersjtjj(),hv.getWsjtjj(),
												hv.getLltjj(), lqsmssheji.getSjtjj(),
												lqsmscontent.getSjtjj(), lqfieldname.getSjtjj(),
												hv.getBaocunshijian(), hv.getShijian(), lqisshowcfg.getShijian(), hv.getBanhezhanminchen(), 12, sendcount, onefrequency, "%%"));
								}
								if(StringUtil.Null2Blank(sms.toString()).length()>0){
									doLqbaojing(hv,filename,sms.toString(),lqsmsnumber.getJbsj(),lqsmsnumber2.getJbsj(),lqsmsnumber3.getJbsj(),hv.getBaocunshijian(), hv.getShijian(),lqisshowcfg.getShijian());
								}
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}
	
	//水稳
	@Action("swsms")
	public String swsms() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"classes"+File.separator+"sms.ini";			
		File fp = new File(filename);			
		if(!fp.exists()){
			try {
				fp.createNewFile();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		if(fp.exists()){
			try {
				Properties prop=new Properties();
				prop.load(new FileInputStream(fp));					
				String maxbh = prop.getProperty("swbianhao", "1");			
				String curstrbh = prop.getProperty("swcurbianhao", "0");	
				Integer curbh = Integer.parseInt(curstrbh);
				if (curbh == 0) {
					List qlist = dataService.findShuiwenTop();
					if (qlist.size()>0) {
						TopRealMaxShuiwenxixxView tophv = (TopRealMaxShuiwenxixxView)qlist.get(0);
						curbh = tophv.getBianhao();
						FileOutputStream fos = new FileOutputStream(fp);
						prop.setProperty("swcurbianhao", String.valueOf(curbh));
						prop.store(fos, "sms");
						fos.close();
					}
				}
										
				if (curbh > 0 && curbh <= Integer.parseInt(maxbh)) {
					
					
					
					FileOutputStream fos = new FileOutputStream(fp);
					prop.setProperty("swcurbianhao", String.valueOf(curbh+1));
					prop.store(fos, "sms");
					fos.close();
					ShuiwenmanualphbView manualhv = dataService.getSwphbmanualviewById(curbh);
					if(manualhv!=null){
						String shebeibianhao = manualhv.getShebeibianhao();
						Shuiwenziduancfg swissms = sysService.swissmsfindBybh(shebeibianhao);
						Shuiwenziduancfg swsmslow = sysService.swsmslowfindBybh(shebeibianhao);
						Shuiwenziduancfg swsmshigh = sysService.swsmshighfindBybh(shebeibianhao);
						Shuiwenziduancfg swsmsnumber = sysService.swsmsnumberfindBybh(shebeibianhao);
						Shuiwenziduancfg swsmscontent = sysService.swsmscontentfindBybh(shebeibianhao);
						ShuiwenziduancfgView swfieldname = sysService.swfieldnamefindBybh(shebeibianhao);
						Shuiwenziduancfg swsmssheji = sysService.swsmsshejifindBybh(shebeibianhao);
						Shuiwenziduancfg swsmslow2 = sysService.swsmslowfindBybh2(shebeibianhao);
						Shuiwenziduancfg swsmshigh2 = sysService.swsmshighfindBybh2(shebeibianhao);
						//这里需要添加一组中级报警的单元格
						Shuiwenziduancfg swsmslow4 = sysService.swsmslowfindBybh4(shebeibianhao);
						Shuiwenziduancfg swsmshigh4 = sysService.swsmshighfindBybh4(shebeibianhao);
						
						Shuiwenziduancfg swsmsnumber2 = sysService.swsmsnumberfindBybh2(shebeibianhao);
						
						
						
						Shuiwenziduancfg swsmslow3 = sysService.swsmslowfindBybh3(shebeibianhao);
						Shuiwenziduancfg swsmshigh3 = sysService.swsmshighfindBybh3(shebeibianhao);
						Shuiwenziduancfg swsmsnumber3 = sysService.swsmsnumberfindBybh3(shebeibianhao);
						Shuiwenziduancfg swisshowcfg = sysService.shuiwenisshowcfgBybh(shebeibianhao);
						if (null != swissms && null != swsmslow && null != swsmshigh 
											&& null != swsmsnumber && null != swsmscontent 
											&& null != swsmssheji && null != swsmslow2 && null != swsmshigh2 
											&& null != swsmsnumber2 && null != swsmslow3 && null != swsmshigh3 
											&& null != swsmsnumber3) {								
							filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"classes"+File.separator+"system.ini";
							fp = new File(filename);
							int onefrequency = 1;
							int ps = 100;
									
							String apitype="97";
							String panshu = "100";
							if(fp.exists()){
								prop=new Properties();
								try {
									prop.load(new FileInputStream(fp));
									try {
										onefrequency = Integer.parseInt(prop.getProperty("onefrequency", "1"));
									} catch (Exception e) {}					
									panshu = StringUtil.Null2Blank(prop.getProperty("panshu"));
									apitype = StringUtil.Null2Blank(prop.getProperty("apitype", "97"));
								} catch (FileNotFoundException e) {
									logger.error(e.getMessage());
								} catch (IOException e) {
									logger.error(e.getMessage());
								}
							}
							//在这里处理仓位的问题。---一般一个料放置到两个仓之中
							String[] Strshijiziduan={"sjgl1","sjgl2","sjgl3","sjgl4","sjgl5"};
							String[] Strshijicontext=new String[5];
							String[] Strlilunziduan={"llgl1","llgl2","llgl3","llgl4","llgl5"};
							String[] Strliluncontext=new String[5];
							for(int i=0;i<Strshijiziduan.length;i++){
								try{
									Strshijicontext[i]=(String)swfieldname.getClass().getMethod("get"+Strshijiziduan[i].replaceFirst(Strshijiziduan[i].substring(0,1),Strshijiziduan[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(swfieldname,new Object[]{});
									Strliluncontext[i]=(String)swfieldname.getClass().getMethod("get"+Strlilunziduan[i].replaceFirst(Strlilunziduan[i].substring(0,1),Strlilunziduan[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(swfieldname,new Object[]{});
								}catch(Exception ex){}
							}
							//进入筛选条件
							String[] strshiji=MapUtil.findSameNum(Strshijicontext, Strshijiziduan);
							String[] strlilun=MapUtil.findSameNum(Strliluncontext, Strlilunziduan);
							//判断这个料仓是否已经进行了料仓合并
							boolean flag=sysService.ishebing(curbh);
							if(strshiji!=null && strlilun!=null && flag==false){
								double sumsjgl=0;
								double sumllgl=0;
								try{
									for(int i=0;i<strshiji.length;i++){
										sumsjgl+=StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strshiji[i].replaceFirst(strshiji[i].substring(0,1),strshiji[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{}));
										sumllgl+=StringUtil.StrToZero((String)manualhv.getClass().getMethod("get"+strlilun[i].replaceFirst(strlilun[i].substring(0,1),strlilun[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(manualhv,new Object[]{}));
									}
									//
									Shuiwentongji swxixx=sysService.getShuiwenxixx(curbh);
									sysService.invokeSet(swxixx, strshiji[0], "0");
									sysService.invokeSet(swxixx, strshiji[1], String.valueOf(sumsjgl));
									sysService.invokeSet(swxixx, strlilun[0], "0");
									sysService.invokeSet(swxixx, strlilun[1], String.valueOf(sumllgl));
									swxixx.setBeiy3("1");   //这里表示这条数据已经做过料仓合并
									sysService.saveShuiwenxixx(swxixx);
								}catch(Exception ex){}
							}else{
								logger.info("---未出现重复的料仓！！！");
							}
												
							//匹配理论配合比(水泥1或水泥2有值就匹配)
							sysService.swsetLilunphb(manualhv.getBianhao(), shebeibianhao,manualhv.getSjgl1(),manualhv.getSjgl2());
							manualhv = dataService.getSwphbmanualviewById(curbh);
							//计算合成级配和标准级配
							//骨料和粉料做筛分试验
							String[] ArrStr={"sjgl1","sjgl2","sjgl3","sjgl4","sjgl5","sjfl1","sjfl2"};
							//实际掺配率
							String[] ArrStrpers={"persjgl1","persjgl2","persjgl3","persjgl4","persjgl5","persjfl1","persjfl2"}; 
							//将合成级配的数据保存到Shuiwentongji表中
							String[] ArrStrbianhao={"sjglsf1","sjglsf2","sjglsf3","sjglsf4","sjglsf5","sjflsf1","sjflsf2"};
							for(int i=0;i<ArrStr.length;i++){
								Shaifenshiyan shaifen=shaifenService.getShaifenByshebei(manualhv.getShebeibianhao(), ArrStr[i],manualhv.getBiaoshi());
								sysService.Calcjipei(manualhv,shaifen,ArrStrpers[i],ArrStrbianhao[i],curbh);
								
								//sysService.Calcjipeiphb(manualhv,shaifen,ArrStrpers[i],ArrStrbianhao[i],curbh);
							}
							
							/*-----------------------------开关机提醒------------------------------------*/
							//提取开关机生产的内容
							StringBuffer smspeibi=new StringBuffer("");
							StringBuffer smsbuwei=new StringBuffer("");
							if(StringUtil.Null2Blank(manualhv.getBiaoshi()).length()>0){
								Shuiwenxixxlilun swlilun=swllService.getBeanById(Integer.parseInt(manualhv.getBiaoshi()));
								smsbuwei.append("使用部位:"+swlilun.getLlbuwei()+";");
								if(StringUtil.Null2Blank(manualhv.getLlgl1()).length()>0 && StringUtil.Null2Blank(swlilun.getLlg1()).length()>0){
									smspeibi.append("["+swfieldname.getSjgl1()+"]");
									smspeibi.append("施工:"+String.format("%1$.2f",Float.parseFloat(manualhv.getLlgl1()))+"目标:"+String.format("%1$.2f", Float.parseFloat(swlilun.getLlg1())));
									float cailiaowucha=Float.parseFloat(manualhv.getLlgl1())-Float.parseFloat(swlilun.getLlg1());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlgl2()).length()>0 && StringUtil.Null2Blank(swlilun.getLlg2()).length()>0){
									smspeibi.append("["+swfieldname.getSjgl2()+"]");
									smspeibi.append("施工:"+String.format("%1$.2f",Float.parseFloat(manualhv.getLlgl2()))+"目标:"+String.format("%1$.2f", Float.parseFloat(swlilun.getLlg2())));
									float cailiaowucha=Float.parseFloat(manualhv.getLlgl2())-Float.parseFloat(swlilun.getLlg2());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlgl3()).length()>0 && StringUtil.Null2Blank(swlilun.getLlg3()).length()>0){
									smspeibi.append("["+swfieldname.getSjgl3()+"]");
									smspeibi.append("施工:"+String.format("%1$.2f",Float.parseFloat(manualhv.getLlgl3()))+"目标:"+String.format("%1$.2f", Float.parseFloat(swlilun.getLlg3())));
									float cailiaowucha=Float.parseFloat(manualhv.getLlgl3())-Float.parseFloat(swlilun.getLlg3());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlgl4()).length()>0 && StringUtil.Null2Blank(swlilun.getLlg4()).length()>0){
									smspeibi.append("["+swfieldname.getSjgl4()+"]");
									smspeibi.append("施工:"+String.format("%1$.2f",Float.parseFloat(manualhv.getLlgl4()))+"目标:"+String.format("%1$.2f", Float.parseFloat(swlilun.getLlg4())));
									float cailiaowucha=Float.parseFloat(manualhv.getLlgl4())-Float.parseFloat(swlilun.getLlg4());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlgl5()).length()>0 && StringUtil.Null2Blank(swlilun.getLlg5()).length()>0){
									smspeibi.append("["+swfieldname.getSjgl5()+"]");
									smspeibi.append("施工:"+String.format("%1$.2f",Float.parseFloat(manualhv.getLlgl5()))+"目标:"+String.format("%1$.2f", Float.parseFloat(swlilun.getLlg5())));
									float cailiaowucha=Float.parseFloat(manualhv.getLlgl5())-Float.parseFloat(swlilun.getLlg5());
									smspeibi.append("误:"+String.format("%1$.2f", cailiaowucha));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlfl1()).length()>0 && StringUtil.Null2Blank(swlilun.getLlf1()).length()>0){
									smspeibi.append("["+swfieldname.getSjfl1()+"]");
									smspeibi.append("施工:"+String.format("%1$.2f",Float.parseFloat(manualhv.getLlfl1()))+"目标:"+String.format("%1$.2f", Float.parseFloat(swlilun.getLlf1())));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlfl2()).length()>0 && StringUtil.Null2Blank(swlilun.getLlf2()).length()>0){
									smspeibi.append("["+swfieldname.getSjfl2()+"]");
									smspeibi.append("施工:"+String.format("%1$.2f",Float.parseFloat(manualhv.getLlfl2()))+"目标:"+String.format("%1$.2f", Float.parseFloat(swlilun.getLlf2())));
								}
							}
							//开盘两盘不报警
							Boolean yesOrno = false;
							
							List<TopRealMaxShuiwenxixxView> NOOFFList = sysService.getMaxswNOOFFList();//获取最新的数据信息列表
							for(TopRealMaxShuiwenxixxView NOOFFObj:NOOFFList){
								//判断是否开启了短信报警功能
								if(StringUtil.Null2Blank(NOOFFObj.getSmsNOOFFbaojin()).equalsIgnoreCase("1")){						
									//这里目前没有输入手机号
									if(StringUtil.Null2Blank(NOOFFObj.getSmsNOOFFPhone()).length()>0){//判断是否输入了报警号码
										
										String smsNOOFFIntervalTime=NOOFFObj.getSmsNOOFFIntervalTime();//提醒间隔时间(分钟)
										String sms="";
										String smsmStr = "%s于%s已%s[%s]";
										String smsmStr2 = "%s于%s已%s[%s]";
										if(StringUtil.StrToFloat(smsNOOFFIntervalTime)>0){
											smsNOOFFIntervalTime="1";
										}
										if(StringUtil.Null2Blank(NOOFFObj.getSmsNOOFFDateTime()).length()==0){//首次开机提醒
											//待发送短信内容
											//sms = String.format(smsmStr,"开关机提醒", NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！");
											//初始化
											sysService.updateBhzNOOFFData(NOOFFObj.getShebeibianhao(),GetDate.getNowTime("yyyy-MM-dd HH:mm:ss"),"OFF");
										}else{
											try{
												SimpleDateFormat sdflengTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
												Calendar currentDay=Calendar.getInstance();//当前日期时间
												
												Date baocunshijian=sdflengTime.parse(NOOFFObj.getShijianS());//最新出料保存时间
												
												Date smsNOOFFDateTime=sdflengTime.parse(NOOFFObj.getSmsNOOFFDateTime());//上次发送短信日期
												if(NOOFFObj.getSmsNOOFFSendSign().equals("NO")){//已发送开机提醒
													currentDay.add(Calendar.HOUR, -new Integer(smsNOOFFIntervalTime));//当前日期往前推指定分钟
													if (baocunshijian.before(currentDay.getTime())) {//如果最新的保存日期在当前日期往前推间隔时间之前
														if(StringUtil.Null2Blank(smspeibi.toString()).length()>0 &&
																StringUtil.Null2Blank(smsbuwei.toString()).length()>0){
															/*
															sms = String.format(smsmStr, 
																	NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"停止生产！",smsbuwei.toString(),smspeibi.toString(),"关机提醒");
															*/		
															sms = String.format(smsmStr, 
																	NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"停止生产！","关机提醒");
																
														}else{
															/*
															sms = String.format(smsmStr2, 
																	NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"停止生产！","关机提醒");*/
															sms = String.format(smsmStr2, 
																	NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"停止生产！","关机提醒");
														}
														sysService.updateBhzNOOFFData(NOOFFObj.getShebeibianhao(),NOOFFObj.getBaocunshijian(),"OFF");
														
														//关机:发送短信
														//sysService.saveandSendSms(NOOFFObj.getShebeibianhao(), NOOFFObj.getSmsNOOFFPhone(), sms, apitype);
										        	}
												}else{//已发送关机提醒
													if(baocunshijian.after(smsNOOFFDateTime)){//如果关机短信已发送并且最新数据的保存时间在当前时间之后
														Shoupan shoupan;
														if(StringUtil.Null2Blank(smspeibi.toString()).length()>0 &&
																StringUtil.Null2Blank(smsbuwei.toString()).length()>0){
															/*sms = String.format(smsmStr, 
																	NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！",smsbuwei.toString(),smspeibi.toString(),"开机提醒");
															*/
															sms = String.format(smsmStr, 
																	NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！","开机提醒");
															
														}else{
															sms = String.format(smsmStr2, 
																	NOOFFObj.getBanhezhanminchen(), NOOFFObj.getShijian(),"开始生产！","开机提醒");
														}
														//如果开机我就在shoupan表中通过设备编号查找这一台机器，如果找到有，就将panshu设置为第一盘1，如果没有我就新建一条
														if(shoupanService.getShoupanbybianhao(manualhv.getShebeibianhao())!=null){
															shoupan = shoupanService.getShoupanbybianhao(manualhv.getShebeibianhao());															
														}else{
															shoupan = new Shoupan();
															//拿到对应的设备编号、名称
															shoupan.setBanhezhanminchen(manualhv.getBanhezhanminchen());
															shoupan.setShebeibianhao(manualhv.getShebeibianhao());																
														}
														shoupan.setYesorno("on");
														//开机就设置盘数为1
														shoupan.setPanshu(1);
														//开机拿到当前的刷新编号
														System.out.println("当前编号："+manualhv.getBianhao());
														shoupan.setBianhao(manualhv.getBianhao());
														shoupanService.saveOrUpdate(shoupan);
														yesOrno = true;
														sysService.updateBhzNOOFFData(NOOFFObj.getShebeibianhao(),NOOFFObj.getBaocunshijian(),"NO");
														
													}
												}
												//发送开机短信
												HandSet hs = sysService.getRealPhoneNumber2(NOOFFObj.getSmsNOOFFPhone());
								    			String realphonenumber=null;
								    			String realphonename=null;
												if (null != hs) {
								    				realphonenumber =hs.getPhone();
								    				realphonename =hs.getOwername();
												}
												
												if(StringUtil.Null2Blank(realphonenumber).length()>0&&sms!=""){														
													sysService.saveandSendSms(manualhv.getBianhao(), NOOFFObj.getShebeibianhao(), realphonenumber,realphonename,manualhv.getShijianS(), sms, apitype,"sw");
													
												}
											}catch(Exception e){
												logger.error(e.getMessage());
											}
										}

									}
								}
							}
							
												
							if (null != manualhv && StringUtil.Null2Blank(manualhv.getSmssettype()).equalsIgnoreCase("1")) {
								panshu = StringUtil.Null2Blank(manualhv.getPanshu());
							} 
													
							if (panshu.length() > 0) {
								try {
									ps = Integer.valueOf(panshu);
								} catch (Exception e) {
									ps = 100;
								}
							}	
							
							Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
							if (null == xxsms) {
								xxsms = new Xiangxixxsms();
								xxsms.setShebeibianhao(shebeibianhao);
								xxsms.setLeiji(0);
								xxsms.setPanshu(1);
							} else {
								if (xxsms.getPanshu() >= ps) {
									xxsms.setPanshu(1);
									xxsms.setLeiji(0);
								} else {
									xxsms.setPanshu(xxsms.getPanshu()+1);
								}									
							}
							sysService.saveXxsms(xxsms);
													
							boolean isvalided = true;
							if (!isvalided) {
								return null;
							}
							int sendcount = 0;
							StringBuffer sms=new StringBuffer();
																													
							//骨料1
							if(StringUtil.Null2Blank(manualhv.getSjgl1()).length()>0){
								sms.append(doSwsms1(manualhv, filename, manualhv.getBianhao(), shebeibianhao, manualhv.getSmsbaojin(),swissms.getSjgl1(), swsmslow.getSjgl1(),swsmshigh.getSjgl1(),
										swsmsnumber.getSjshui(),swsmsnumber.getSjgl1(), swsmslow2.getSjgl1(),swsmshigh2.getSjgl1(),
										swsmsnumber2.getSjshui(), swsmsnumber2.getSjgl1(), swsmslow3.getSjgl1(),swsmshigh3.getSjgl1(),
										swsmsnumber3.getSjshui(),swsmsnumber3.getSjgl1(),manualhv.getPersjgl1(),manualhv.getManualwgl1(),
										manualhv.getLlg1(), swsmssheji.getSjgl1(),
										swsmscontent.getSjgl1(), swfieldname.getSjgl1(),
										manualhv.getBaocunshijian(),manualhv.getShijian(),swisshowcfg.getShijian(), manualhv.getBanhezhanminchen(), 1, sendcount, onefrequency, "%%","gl",swsmslow4.getSjgl1(),swsmshigh4.getSjgl1()));
							}
										
							//石料2
							if(StringUtil.Null2Blank(manualhv.getSjgl2()).length()>0){
								sms.append(doSwsms1(manualhv, filename, manualhv.getBianhao(), shebeibianhao, manualhv.getSmsbaojin(),swissms.getSjgl2(), swsmslow.getSjgl2(),swsmshigh.getSjgl2(),
										swsmsnumber.getSjshui(),swsmsnumber.getSjgl2(), swsmslow2.getSjgl2(),swsmshigh2.getSjgl2(),
										swsmsnumber2.getSjshui(), swsmsnumber2.getSjgl2(), swsmslow3.getSjgl2(),swsmshigh3.getSjgl2(),
										swsmsnumber3.getSjshui(),swsmsnumber3.getSjgl2(),manualhv.getPersjgl2(),manualhv.getManualwgl2(),
										manualhv.getLlg2(), swsmssheji.getSjgl2(),
										swsmscontent.getSjgl2(), swfieldname.getSjgl2(),
										manualhv.getBaocunshijian(),manualhv.getShijian(),swisshowcfg.getShijian(), manualhv.getBanhezhanminchen(), 2, sendcount, onefrequency, "%%","gl", swsmslow4.getSjgl2(),swsmshigh4.getSjgl2()));
							}
										
							//石料3
							if(StringUtil.Null2Blank(manualhv.getSjgl3()).length()>0){
								sms.append(doSwsms1(manualhv, filename, manualhv.getBianhao(), shebeibianhao, manualhv.getSmsbaojin(),swissms.getSjgl3(), swsmslow.getSjgl3(),swsmshigh.getSjgl3(),
										swsmsnumber.getSjshui(),swsmsnumber.getSjgl3(), swsmslow2.getSjgl3(),swsmshigh2.getSjgl3(),
										swsmsnumber2.getSjshui(), swsmsnumber2.getSjgl3(), swsmslow3.getSjgl3(),swsmshigh3.getSjgl3(),
										swsmsnumber3.getSjshui(),swsmsnumber3.getSjgl3(),manualhv.getPersjgl3(),manualhv.getManualwgl3(),
										manualhv.getLlg3(), swsmssheji.getSjgl3(),
										swsmscontent.getSjgl3(), swfieldname.getSjgl3(),
										manualhv.getBaocunshijian(),manualhv.getShijian(),swisshowcfg.getShijian(), manualhv.getBanhezhanminchen(), 3, sendcount, onefrequency, "%%","gl", swsmslow4.getSjgl3(),swsmshigh4.getSjgl3()));
							}
										
							//石料4
							if(StringUtil.Null2Blank(manualhv.getSjgl4()).length()>0){
								sms.append(doSwsms1(manualhv, filename, manualhv.getBianhao(), shebeibianhao, manualhv.getSmsbaojin(),swissms.getSjgl4(), swsmslow.getSjgl4(),swsmshigh.getSjgl4(),
										swsmsnumber.getSjshui(),swsmsnumber.getSjgl4(), swsmslow2.getSjgl4(),swsmshigh2.getSjgl4(),
										swsmsnumber2.getSjshui(), swsmsnumber2.getSjgl4(), swsmslow3.getSjgl4(),swsmshigh3.getSjgl4(),
										swsmsnumber3.getSjshui(),swsmsnumber3.getSjgl4(),manualhv.getPersjgl4(),manualhv.getManualwgl4(),
										manualhv.getLlg4(), swsmssheji.getSjgl4(),
										swsmscontent.getSjgl4(), swfieldname.getSjgl4(),
										manualhv.getBaocunshijian(),manualhv.getShijian(),swisshowcfg.getShijian(), manualhv.getBanhezhanminchen(), 4, sendcount, onefrequency, "%%","sf", swsmslow4.getSjgl4(),swsmshigh4.getSjgl4()));
							}
										
							//石料5
							if(StringUtil.Null2Blank(manualhv.getSjgl5()).length()>0){
								sms.append(doSwsms1(manualhv, filename, manualhv.getBianhao(), shebeibianhao, manualhv.getSmsbaojin(),swissms.getSjgl5(), swsmslow.getSjgl5(),swsmshigh.getSjgl5(),
										swsmsnumber.getSjshui(),swsmsnumber.getSjgl5(), swsmslow2.getSjgl5(),swsmshigh2.getSjgl5(),
										swsmsnumber2.getSjshui(), swsmsnumber2.getSjgl5(), swsmslow3.getSjgl5(),swsmshigh3.getSjgl5(),
										swsmsnumber3.getSjshui(),swsmsnumber3.getSjgl5(),manualhv.getPersjgl5(),manualhv.getManualwgl5(),
										manualhv.getLlg5(), swsmssheji.getSjgl5(),
										swsmscontent.getSjgl5(), swfieldname.getSjgl5(),
										manualhv.getBaocunshijian(),manualhv.getShijian(),swisshowcfg.getShijian(), manualhv.getBanhezhanminchen(), 5, sendcount, onefrequency, "%%","sf", swsmslow4.getSjgl5(),swsmshigh4.getSjgl5()));
							}
										
							//粉料1
							if(StringUtil.Null2Blank(manualhv.getSjfl1()).length()>0){
								sms.append(doSwsms1(manualhv, filename, manualhv.getBianhao(), shebeibianhao, manualhv.getSmsbaojin(),swissms.getSjfl1(), swsmslow.getSjfl1(),swsmshigh.getSjfl1(),
										swsmsnumber.getSjshui(),swsmsnumber.getSjfl1(), swsmslow2.getSjfl1(),swsmshigh2.getSjfl1(),
										swsmsnumber2.getSjshui(), swsmsnumber2.getSjfl1(), swsmslow3.getSjfl1(),swsmshigh3.getSjfl2(),
										swsmsnumber3.getSjshui(),swsmsnumber3.getSjfl1(),manualhv.getPersjfl1(),manualhv.getManualwfl1(),
										manualhv.getLlf1(), swsmssheji.getSjfl1(),
										swsmscontent.getSjfl1(), swfieldname.getSjfl1(),
										manualhv.getBaocunshijian(),manualhv.getShijian(),swisshowcfg.getShijian(), manualhv.getBanhezhanminchen(), 6, sendcount, onefrequency, "%%","fl", swsmslow4.getSjfl1(),swsmshigh4.getSjfl1()));
							}
										
							//粉料2
							if(StringUtil.Null2Blank(manualhv.getSjfl2()).length()>0){
								sms.append(doSwsms1(manualhv, filename, manualhv.getBianhao(), shebeibianhao, manualhv.getSmsbaojin(),swissms.getSjfl2(), swsmslow.getSjfl2(),swsmshigh.getSjfl2(),
										swsmsnumber.getSjshui(),swsmsnumber.getSjfl2(), swsmslow2.getSjfl2(),swsmshigh2.getSjfl2(),
										swsmsnumber2.getSjshui(), swsmsnumber2.getSjfl2(), swsmslow3.getSjfl2(),swsmshigh3.getSjfl2(),
										swsmsnumber3.getSjshui(),swsmsnumber3.getSjfl2(),manualhv.getPersjfl2(),manualhv.getManualwfl2(),
										manualhv.getLlf2(), swsmssheji.getSjfl2(),
										swsmscontent.getSjfl2(), swfieldname.getSjfl2(),
										manualhv.getBaocunshijian(),manualhv.getShijian(),swisshowcfg.getShijian(), manualhv.getBanhezhanminchen(), 7, sendcount, onefrequency, "%%","fl", swsmslow4.getSjfl2(),swsmshigh4.getSjfl2()));
							}

							if(StringUtil.Null2Blank(sms.toString()).length()>0){
								//报警这里需要先判断是否是开盘
								//如果是开机第一盘，就不报警,这里的panshu指的是超标盘数
								if(yesOrno){
									System.out.println("开机第一盘不报警");
								}else{
									//设置一个默认大于2的数字，默认是报警的
									int i = 3;
									if(shoupanService.getShoupanbybianhao(manualhv.getShebeibianhao())!=null){
										Shoupan sp = shoupanService.getShoupanbybianhao(manualhv.getShebeibianhao());
										i = sp.getPanshu()+1;
								        sp.setPanshu(i);
								        shoupanService.saveOrUpdate(sp);
								        
									}
									if(i>1){
										dobaojing(manualhv,filename,sms.toString(),swsmsnumber.getSjshui(),swsmsnumber2.getSjshui(),swsmsnumber3.getSjshui(),manualhv.getBaocunshijian(), manualhv.getShijian(),swisshowcfg.getShijian());	
									}
								}
							}	
						}
						//揭博业主所定义的开盘报警功能：
						if(StringUtil.Null2Blank(manualhv.getBiaoshi()).length()>0){
							Shuiwenxixxlilun swlilun=swllService.getBeanById(Integer.parseInt(manualhv.getBiaoshi()));
							Shuiwenziduancfg swkaipanissms = sysService.swkaipanissmsfindBybh(shebeibianhao);
							Shuiwenziduancfg swkaipansmslow = sysService.swkaipansmslowfindBybh(shebeibianhao);
							Shuiwenziduancfg swkaipansmshigh = sysService.swkaipansmshighfindBybh(shebeibianhao);
							Shuiwenziduancfg swkaipansmsnumber = sysService.swkaipansmsnumberfindBybh(shebeibianhao);
							if(swlilun!=null && swkaipanissms!=null && swkaipansmslow!=null &&
									swkaipansmshigh!=null && swkaipansmsnumber!=null){
								StringBuffer sms=new StringBuffer();
								if(StringUtil.Null2Blank(manualhv.getLlgl1()).length()>0){
									sms.append(swkaipandosms(manualhv,manualhv.getSmsbaojin(),swkaipanissms.getSjgl1(),swkaipansmslow.getSjgl1(),
											manualhv.getLlgl1(),swlilun.getLlg1(),swfieldname.getSjgl1()));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlgl2()).length()>0){
									sms.append(swkaipandosms(manualhv,manualhv.getSmsbaojin(),swkaipanissms.getSjgl2(),swkaipansmslow.getSjgl2(),
											manualhv.getLlgl2(),swlilun.getLlg2(),swfieldname.getSjgl2()));
								}	
								
								if(StringUtil.Null2Blank(manualhv.getLlgl3()).length()>0){
									sms.append(swkaipandosms(manualhv,manualhv.getSmsbaojin(),swkaipanissms.getSjgl3(),swkaipansmslow.getSjgl3(),
											manualhv.getLlgl3(),swlilun.getLlg3(),swfieldname.getSjgl3()));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlgl4()).length()>0){
									sms.append(swkaipandosms(manualhv,manualhv.getSmsbaojin(),swkaipanissms.getSjgl4(),swkaipansmslow.getSjgl4(),
											manualhv.getLlgl4(),swlilun.getLlg4(),swfieldname.getSjgl4()));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlgl5()).length()>0){
									sms.append(swkaipandosms(manualhv,manualhv.getSmsbaojin(),swkaipanissms.getSjgl5(),swkaipansmslow.getSjgl5(),
											manualhv.getLlgl5(),swlilun.getLlg5(),swfieldname.getSjgl5()));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlfl1()).length()>0){
									sms.append(swkaipandosms(manualhv,manualhv.getSmsbaojin(),swkaipanissms.getSjfl1(),swkaipansmslow.getSjfl1(),
											manualhv.getLlfl1(),swlilun.getLlf1(),swfieldname.getSjfl1()));
								}
								
								if(StringUtil.Null2Blank(manualhv.getLlfl2()).length()>0){
									sms.append(swkaipandosms(manualhv,manualhv.getSmsbaojin(),swkaipanissms.getSjfl2(),swkaipansmslow.getSjfl2(),
											manualhv.getLlfl2(),swlilun.getLlf2(),swfieldname.getSjfl2()));
								}
								
								if(StringUtil.Null2Blank(sms.toString()).length()>0){
									dokaipanbaojing(manualhv,filename,sms.toString(),swkaipansmsnumber.getSjshui(),manualhv.getBaocunshijian(), manualhv.getShijian());
								}
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}
	
	
	@Action("xcsms")
	public String xcsms() {
			ActionContext context = ActionContext.getContext(); 
			HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
			+"classes"+File.separator+"sms.ini";
			String systemfilename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
			+"classes"+File.separator+"system.ini";
			File fp = new File(filename);			
			if(!fp.exists()){
				try {
					fp.createNewFile();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if(fp.exists()){
				try {
					Properties prop=new Properties();
					prop.load(new FileInputStream(fp));					
					String maxtmpbh = prop.getProperty("xctmpbianhao", "1");			
					String curtmpstrbh = prop.getProperty("xctmpcurbianhao", "0");
					String panduantiaoshu = prop.getProperty("panduantiaoshu", "3");
					Integer curtmpbh = Integer.parseInt(curtmpstrbh);
					if (curtmpbh == 0) {
						List qlist = dataService.findTemperatureTop();
						if (qlist.size()>0) {
						  TopRealTemperaturedataView tophv = (TopRealTemperaturedataView)qlist.get(0);
						  curtmpbh = tophv.getTmpid();
						  FileOutputStream fos = new FileOutputStream(fp);
						  prop.setProperty("xctmpcurbianhao", String.valueOf(curtmpbh));
						  prop.store(fos, "sms");
						  fos.close();
						}
					}
										
					if (curtmpbh > 0 && curtmpbh <= Integer.parseInt(maxtmpbh)) {
						FileOutputStream fos = new FileOutputStream(fp);
						if (curtmpbh<Integer.parseInt(maxtmpbh)-Integer.parseInt(panduantiaoshu)) {
							curtmpbh=Integer.parseInt(maxtmpbh)-Integer.parseInt(panduantiaoshu);
						}
						prop.setProperty("xctmpcurbianhao", String.valueOf(curtmpbh+1));
						prop.store(fos, "sms");
						fos.close();
						TemperaturedataView tmpdata = dataService.getTmpdataView(curtmpbh);
						if (null != tmpdata) {
							Xcsmscfg xcsms = sysService.getXcsmscfg(tmpdata.getGprsbianhao());
							if (null != xcsms) {
								doxcsms(tmpdata, xcsms, systemfilename);
							}
						}
					}
					
					String maxspeedbh = prop.getProperty("xcspeedbianhao", "1");			
					String curspeedstrbh = prop.getProperty("xcspeedcurbianhao", "0");	
					Integer curspeedbh = Integer.parseInt(curspeedstrbh);
					if (curspeedbh == 0) {
						List qlist = dataService.findSpeedTop();
						if (qlist.size()>0) {
						  TopRealSpeeddataView tophv = (TopRealSpeeddataView)qlist.get(0);
						  curspeedbh = tophv.getGpsid();
						  FileOutputStream fos = new FileOutputStream(fp);
						  prop.setProperty("xcspeedcurbianhao", String.valueOf(curspeedbh));
						  prop.store(fos, "sms");
						  fos.close();
						}
					}
										
					if (curspeedbh > 0 && curspeedbh <= Integer.parseInt(maxspeedbh)) {
						FileOutputStream fos = new FileOutputStream(fp);
						if (curspeedbh<Integer.parseInt(maxspeedbh)-Integer.parseInt(panduantiaoshu)) {
							curspeedbh=Integer.parseInt(maxspeedbh)-Integer.parseInt(panduantiaoshu);
						}
						prop.setProperty("xcspeedcurbianhao", String.valueOf(curspeedbh+1));
						prop.store(fos, "sms");
						fos.close();
						SpeeddataView speeddata = dataService.getSpeeddataView(curspeedbh);
						if (null != speeddata) {
							Xcsmscfg xcsms = sysService.getXcsmscfg(speeddata.getGprsbianhao());
							if (null != xcsms) {
								doxcsms(speeddata, xcsms, systemfilename);
							}
						}
					}
				} catch (FileNotFoundException e) {
					logger.error(e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		return null;
	}

	/**发送短信报警
	 * @param filename 短信配置文件路径
	 * @param bianhao 当前记录编号
	 * @param shebeibianhao 设备编号
	 * @param smsbaojin 标段是否设置为短信报警
	 * @param issms 字段是否需要短信报警
	 * @param biaozhun 一级下限或者上限，用百分比表示
	 * @param phonenumber 一级手机号码
	 * @param biaozhun2  二级下限或者上限，用百分比表示
	 * @param phonenumber2 二级手机号码
	 * @param biaozhun3 三级下限或者上限，用百分比表示
	 * @param phonenumber3 三级手机号码
	 * @param shijizhi  实际值
	 * @param lilunzhi  理论值
	 * @param sheji     没有理论值的字段的设计值
	 * @param smscontent 发送内容格式 （6个参数）
	 * @param fieldname  没有设置发送格式的情况下传入字段名称
	 * @param baocunshijian 该条数据保存时间
	 * @param bhzminchen 拌和站名称
	 * @param biaozhuntype 短信类型0为下限1为上限
	 * @param ziduantype 字段类型
	 */
	private boolean dosms(Hntview hv, String filename, Integer bianhao, String shebeibianhao, String smsbaojin, String issms, String biaozhun, String jiaobanphonenumber, String phonenumber, 
			String biaozhun2, String jiaobanphonenumber2, String phonenumber2, 
			String biaozhun3, String jiaobanphonenumber3, String phonenumber3, String shijizhi, 
			String lilunzhi, String sheji, String smscontent, String fieldname, 
			String baocunshijian, String Chuliaoshijian, String isshowchuliao, String bhzminchen, int biaozhuntype, int ziduantype, int sendcount, int onefrequency) {
		boolean isnotdo = true;
		if (StringUtil.Null2Blank(shijizhi).length()>0
				&& StringUtil.Null2Blank(biaozhun).length()>0) {
				try {
					if (shijizhi.length()>8) {
						shijizhi = String.format("%1$.2f",Double.valueOf(shijizhi));
					}
					if (lilunzhi.length()>8) {
						lilunzhi = String.format("%1$.2f",Double.valueOf(lilunzhi));
					}
					double shiji = Double.valueOf(shijizhi);
					double bz = -1;
					double bz2 = -1;
					double bz3 = -1;
					double finalbz = -1;
					boolean denji1 = false;
					boolean denji2 = false;
					boolean denji3 = false;	
					String leixin = "低于标准值";
					
					if (StringUtil.Null2Blank(biaozhun).length()>0) {
						try {
							bz = Double.valueOf(biaozhun);
						} catch (Exception e) {
						}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun2).length()>0) {
						try {
							bz2 = Double.valueOf(biaozhun2);
						} catch (Exception e) {
						}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun3).length()>0 ) {
						try {
							bz3 = Double.valueOf(biaozhun3);
						} catch (Exception e) {
						}
					}
					
					double lilun = -1;
					if (StringUtil.Null2Blank(lilunzhi).length()>0) {
						try {
							lilun = Double.valueOf(lilunzhi);
						} catch (Exception e) {
						}
					} else if (StringUtil.Null2Blank(sheji).length()>0) {
						try {
							lilun = Double.valueOf(sheji);
						} catch (Exception e) {
						}
					}
					
					String gujifs = hv.getGujifangshu();
					boolean slok = false;
					if (StringUtil.Null2Blank(gujifs).length()>0) {
						try {
							if (Double.valueOf(gujifs)>0.2) {
								slok = true;
							}
						} catch (Exception e) {
						}
					}
					if (lilun > 0 && shiji > 0 && slok) {
						String sms = "";
					    switch (biaozhuntype) {
						case 0:
							if (bz3 > 0 && shiji<lilun*(1-bz3/100)) {
								finalbz = bz3;
								denji3 = true;
								denji2 = true;
								denji1 = true;
							} else if (bz2 > 0 && shiji<lilun*(1-bz2/100)) {
								finalbz = bz2;
								denji2 = true;
								denji1 = true;
							} else if (bz > 0 && shiji<lilun*(1-bz/100)) {
								finalbz = bz;
								denji1 = true;
							}
							break;

						case 1:
							leixin = "高于标准值";						
							if (bz3 > 0 && shiji>lilun*(1+bz3/100)) {
								finalbz = bz3;
								denji3 = true;
								denji2 = true;
								denji1 = true;
							} else if (bz2 > 0 && shiji>lilun*(1+bz2/100)) {
								finalbz = bz2;
								denji2 = true;
								denji1 = true;
							} else if (bz > 0 && shiji>lilun*(1+bz/100)) {
								finalbz = bz;
								denji1 = true;
							}
							break;
						}					    
					  
					    
					    if (finalbz > 0) {
					    	setSmsTypeDb(filename, bianhao, shebeibianhao, denji2, denji3, biaozhuntype, ziduantype);
					    	Calendar day=Calendar.getInstance();
					    	day.add(Calendar.MINUTE, -30);
					    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    	Date baocun = day.getTime();					    	
								if (StringUtil.Null2Blank(isshowchuliao).equalsIgnoreCase("1") &&
										StringUtil.Null2Blank(Chuliaoshijian).length()>0 && 
										StringUtil.Null2Blank(Chuliaoshijian).length()<22) {
				        		  try {
				        			  baocun = sdf.parse(Chuliaoshijian);
								  } catch (ParseException e) {
									try {
										baocun = sdf.parse(baocunshijian);
										} catch (ParseException ex) {
											
									}
								  }
									
								} else {
									try {
										baocun = sdf.parse(baocunshijian);
										} catch (ParseException ex) {
											
									}
								}
							boolean isnormal = true;
							if (shiji<lilun*0.6 || shiji>lilun*1.4) {
								isnormal = false;
							}
							
					    	if (StringUtil.Null2Blank(smsbaojin).equalsIgnoreCase("1") 
					    			&& StringUtil.Null2Blank(issms).equalsIgnoreCase("1")
					    			&& isnormal && baocun.after(day.getTime()) 
					    			&& getSmsifsend(hv, filename)) {
					    		String apitype = null;
					    		String filepanshu = "100";
					    		String smsmeipan = "[%s]%s:%s实际值%s理论值%s已经%s%s%%";
					    		String smshuizong = "[%s]%s:已搅拌盘数%s累计预警准次数%s";
					    		File fp = new File(filename);
								if(fp.exists()){
									Properties prop=new Properties();
									try {
										prop.load(new FileInputStream(fp));												
										apitype = StringUtil.Null2Blank(prop.getProperty("apitype", "0"));
										filepanshu = StringUtil.Null2Blank(prop.getProperty("panshu", "100"));
										smsmeipan = StringUtil.Null2Blank(prop.getProperty("meipan", "[%s]%s:%s实际值%s理论值%s已经%s%s%%"));
										smshuizong = StringUtil.Null2Blank(prop.getProperty("huizong", "[%s]%s:已搅拌盘数%s累计预警准次数%s"));
									} catch (FileNotFoundException e) {
										logger.error(e.getMessage());
									} catch (IOException e) {
										logger.error(e.getMessage());
									}
									prop.clear();
								}
					    		switch (getSendtype(hv, filename)) {
								case 1:
									//当前盘发送频率小于指定的盘数时发短信
									if (sendcount<onefrequency) {
										String schuliao = baocunshijian;
										if (StringUtil.Null2Blank(isshowchuliao).equalsIgnoreCase("1") && 
												StringUtil.Null2Blank(Chuliaoshijian).length()>0 && 
												StringUtil.Null2Blank(Chuliaoshijian).length()<22) {
											schuliao = "出料"+Chuliaoshijian;
										}
										if (StringUtil.Null2Blank(fieldname).length()>0) {
											if (StringUtil.Null2Blank(smscontent).length()>0) {
												sms = String.format(smscontent, schuliao, bhzminchen, fieldname, shiji, lilun, leixin, finalbz);
											}  else {
												sms = String.format(smsmeipan, 
														schuliao, bhzminchen, fieldname, shiji, lilun, leixin, finalbz);
											}
										}
									}
							    	
									break;
								case 2:
									String panshu = "100";
									int ps = 100;
									if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
										panshu = StringUtil.Null2Blank(hv.getPanshu());
									} else {
										panshu = filepanshu;
									}
									if (panshu.length() > 0) {
										try {
											ps = Integer.valueOf(panshu);
										} catch (Exception e) {
											ps = 100;
										}
									}
									Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
									if (null != xxsms && xxsms.getPanshu() >= ps) {
										sms = String.format(smshuizong, 
												baocunshijian, bhzminchen, xxsms.getPanshu(), xxsms.getLeiji());
										xxsms.setLeiji(0);
										xxsms.setPanshu(0);
										sysService.saveXxsms(xxsms);
									} 
									break;
								default:
									break;
								}
					    		if (sms.length() > 0) {
					    			isnotdo = false;
					    			String realphonenumber = "";
					    			String realphonenumber2 = "";
					    			String realphonenumber3 = "";
					    			
					    			if (denji1) {
					    				realphonenumber = sysService.getRealPhoneNumber(phonenumber);
					    				if (StringUtil.Null2Blank(realphonenumber).length()==0) {
						    				realphonenumber = sysService.getRealPhoneNumber(jiaobanphonenumber);
										}
									}
					    			
					    			if (denji2) {
										realphonenumber2 = sysService.getRealPhoneNumber(phonenumber2);
										if (StringUtil.Null2Blank(realphonenumber2).length()==0) {
						    				realphonenumber2 = sysService.getRealPhoneNumber(jiaobanphonenumber2);
										}
									}
					    			
					    			if (denji3) {
										realphonenumber3 = sysService.getRealPhoneNumber(phonenumber3);
										if (StringUtil.Null2Blank(realphonenumber3).length()==0) {
						    				realphonenumber3 = sysService.getRealPhoneNumber(jiaobanphonenumber3);
										}
									}
					    			
					    			
					    			if (denji1 && StringUtil.Null2Blank(realphonenumber).length()>0) {
					    				sysService.saveandSendSms(shebeibianhao, realphonenumber, sms, apitype);
					    			}
						    	    if (denji2 && StringUtil.Null2Blank(realphonenumber2).length()>0) {
						    	    	sysService.saveandSendSms(shebeibianhao, realphonenumber2, sms, apitype);
						    	    }
						    	    if (denji3 && StringUtil.Null2Blank(realphonenumber3).length()>0) {
						    	    	sysService.saveandSendSms(shebeibianhao, realphonenumber3, sms, apitype);
						    	    }
						    	}					    		
							}
						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
		} 
		return isnotdo;
	}
	
	private boolean dolqsms(LiqingphbView hv, String filename, Integer bianhao, String shebeibianhao, String smsbaojin, String issms, String biaozhun, String jiaobanphonenumber, String phonenumber, 
			String biaozhun2, String jiaobanphonenumber2, String phonenumber2, 
			String biaozhun3, String jiaobanphonenumber3, String phonenumber3, String shijizhi, String wucha, 
			String lilunzhi, String sheji, String smscontent, String fieldname, 
			String baocunshijian, String Chuliaoshijian, String isshowchuliao, String bhzminchen, int ziduantype, int sendcount, int onefrequency, String danwei) {
		boolean isnotdo = true;
		if (StringUtil.Null2Blank(shijizhi).length()>0 && StringUtil.Null2Blank(wucha).length()>0
				&& StringUtil.Null2Blank(biaozhun).length()>0) {
				try {
					if (shijizhi.length()>8) {
						shijizhi = String.format("%1$.2f",Double.valueOf(shijizhi));
					}
					if (wucha.length()>8) {
						wucha = String.format("%1$.2f",Double.valueOf(wucha));
					}
					if (StringUtil.Null2Blank(lilunzhi).length()>0 && lilunzhi.length()>8) {
						lilunzhi = String.format("%1$.2f",Double.valueOf(lilunzhi));
					}
					double shiji = Double.valueOf(shijizhi);
					double sjwucha = 0;
					double bz = -1;
					double bz2 = -1;
					double bz3 = -1;
					double finalbz = -1;
					boolean denji1 = false;
					boolean denji2 = false;
					boolean denji3 = false;	
					String leixin = "误差";
					
					if (StringUtil.Null2Blank(biaozhun).length()>0) {
						try {
							bz = Math.abs(Double.valueOf(biaozhun));
						} catch (Exception e) {
						}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun2).length()>0) {
						try {
							bz2 = Math.abs(Double.valueOf(biaozhun2));
						} catch (Exception e) {
						}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun3).length()>0 ) {
						try {
							bz3 = Math.abs(Double.valueOf(biaozhun3));
						} catch (Exception e) {
						}
					}
					
					double lilun = -1;
					
					boolean issheji = false;
					if (StringUtil.Null2Blank(lilunzhi).length()>0) {
						try {
							lilun = Double.valueOf(lilunzhi);
							sjwucha = Double.valueOf(wucha);
						} catch (Exception e) {
						}
					} else if (StringUtil.Null2Blank(sheji).length()>0) {
						try {
							lilun = Double.valueOf(sheji);
							sjwucha = shiji - lilun;
							issheji = true;
						} catch (Exception e) {
						}
					}
					
					String gujifs = hv.getChangliang();
					boolean slok = false;
					if (StringUtil.Null2Blank(gujifs).length()>0) {
						try {
							if (Double.valueOf(gujifs)>200) {
								slok = true;
							}
						} catch (Exception e) {
						}
					}
					if (lilun > 0 && shiji > 0 && Math.abs(sjwucha) > 0 && slok && (ziduantype!=0 || sjwucha<0) && (ziduantype!=1 || sjwucha<0)) {
						String sms = "";
						if (bz3 > 0 && Math.abs(sjwucha)-bz3>0) {
							finalbz = bz3;
							denji3 = true;
							denji2 = true;
							denji1 = true;
						} else if (bz2 > 0 && Math.abs(sjwucha)-bz2>0) {
							finalbz = bz2;
							denji2 = true;
							denji1 = true;
						} else if (bz > 0 && Math.abs(sjwucha)-bz>0) {
							finalbz = bz;
							denji1 = true;
						}
					    
					    if (finalbz > 0) {	
					    	int biaozhuntype=0;
					    	if (sjwucha > 0) {
								biaozhuntype = 1;
							}
					    	setLqSmsTypeDb(filename, bianhao, shebeibianhao, denji2, denji3, biaozhuntype, ziduantype);
					    	isnotdo = false;
					    	Calendar day=Calendar.getInstance();
					    	day.add(Calendar.MINUTE, -30);
					    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    	Date baocun = day.getTime();					    	
								if (StringUtil.Null2Blank(isshowchuliao).equalsIgnoreCase("1") &&
										StringUtil.Null2Blank(Chuliaoshijian).length()>0 && 
										StringUtil.Null2Blank(Chuliaoshijian).length()<22) {
				        		  try {
				        			  baocun = sdf.parse(Chuliaoshijian);
								  } catch (ParseException e) {
									try {
										baocun = sdf.parse(baocunshijian);
										} catch (ParseException ex) {
											
									}
								  }
									
								} else {
									try {
										baocun = sdf.parse(baocunshijian);
										} catch (ParseException ex) {
											
									}
								}
							boolean isnormal = true;
							/**
							if (issheji) {
								if (shiji<lilun*0.6 || shiji>lilun*1.4) {							
								   isnormal = false;
								}
							} else {
								if (Math.abs(sjwucha)/lilun>0.4) {							
									   isnormal = false;
								}
							}
							**/
					    	if (StringUtil.Null2Blank(smsbaojin).equalsIgnoreCase("1") 
					    			&& StringUtil.Null2Blank(issms).equalsIgnoreCase("1")
					    			&& isnormal && baocun.after(day.getTime()) 
					    			&& getLqSmsifsend(hv, filename)) {
					    		String apitype = null;
					    		String filepanshu = "100";
					    		String smsmeipan = "[%s]%s:%s实际%s"+danwei+"理论%s"+danwei+"%s%s"+danwei+"【江苏东交】";
					    		String smshuizong = "[%s]%s:已搅拌盘数%s累计预警准次数%s";
					    		File fp = new File(filename);
								if(fp.exists()){
									Properties prop=new Properties();
									try {
										prop.load(new FileInputStream(fp));												
										apitype = StringUtil.Null2Blank(prop.getProperty("apitype", "0"));
										filepanshu = StringUtil.Null2Blank(prop.getProperty("panshu", "100"));
										//smsmeipan = StringUtil.Null2Blank(prop.getProperty("manualmeipan", "[%s]%s:%s实际%s%%理论%s%%%s%s%%"));
										smshuizong = StringUtil.Null2Blank(prop.getProperty("huizong", "[%s]%s:已搅拌盘数%s累计预警准次数%s"));
									} catch (FileNotFoundException e) {
										logger.error(e.getMessage());
									} catch (IOException e) {
										logger.error(e.getMessage());
									}
									prop.clear();
								}
					    		switch (getLqSendtype(hv, filename)) {
								case 1:
									//当前盘发送频率小于指定的盘数时发短信
									if (sendcount<onefrequency) {
										String schuliao = baocunshijian;
										if (StringUtil.Null2Blank(isshowchuliao).equalsIgnoreCase("1") && 
												StringUtil.Null2Blank(Chuliaoshijian).length()>0 && 
												StringUtil.Null2Blank(Chuliaoshijian).length()<22) {
											schuliao = "出料"+Chuliaoshijian;
										}
										if (StringUtil.Null2Blank(fieldname).length()>0) {
											if (StringUtil.Null2Blank(smscontent).length()>0) {
												sms = String.format(smscontent, schuliao, bhzminchen, fieldname, shiji, lilun, leixin, sjwucha);
											}  else {
												sms = String.format(smsmeipan, 
														schuliao, bhzminchen, fieldname, shiji, lilun, leixin, sjwucha);
											}
										}
									}
									break;
								case 2:
									String panshu = "100";
									int ps = 100;
									if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
										panshu = StringUtil.Null2Blank(hv.getPanshu());
									} else {
										panshu = filepanshu;
									}
									if (panshu.length() > 0) {
										try {
											ps = Integer.valueOf(panshu);
										} catch (Exception e) {
											ps = 100;
										}
									}
									Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
									if (null != xxsms && xxsms.getPanshu() >= ps) {
										sms = String.format(smshuizong, 
												baocunshijian, bhzminchen, xxsms.getPanshu(), xxsms.getLeiji());
										xxsms.setLeiji(0);
										xxsms.setPanshu(0);
										sysService.saveXxsms(xxsms);
									} 
									break;
								default:
									break;
								}
					    		if (sms.length() > 0) {
					    			String realphonenumber = "";
					    			String realphonenumber2 = "";
					    			String realphonenumber3 = "";
					    			
					    			if (denji1) {
					    				realphonenumber = sysService.getRealPhoneNumber(phonenumber);
					    				if (StringUtil.Null2Blank(realphonenumber).length()==0) {
						    				realphonenumber = sysService.getRealPhoneNumber(jiaobanphonenumber);
										}
									}
					    			
					    			if (denji2) {
										realphonenumber2 = sysService.getRealPhoneNumber(phonenumber2);
										if (StringUtil.Null2Blank(realphonenumber2).length()==0) {
						    				realphonenumber2 = sysService.getRealPhoneNumber(jiaobanphonenumber2);
										}
									}
					    			
					    			if (denji3) {
										realphonenumber3 = sysService.getRealPhoneNumber(phonenumber3);
										if (StringUtil.Null2Blank(realphonenumber3).length()==0) {
						    				realphonenumber3 = sysService.getRealPhoneNumber(jiaobanphonenumber3);
										}
									}
					    			
					    			
					    			if (denji1 && StringUtil.Null2Blank(realphonenumber).length()>0) {
					    				sysService.saveandSendSms(shebeibianhao, realphonenumber, sms, apitype);
					    			}
						    	    if (denji2 && StringUtil.Null2Blank(realphonenumber2).length()>0) {
						    	    	sysService.saveandSendSms(shebeibianhao, realphonenumber2, sms, apitype);
						    	    }
						    	    if (denji3 && StringUtil.Null2Blank(realphonenumber3).length()>0) {
						    	    	sysService.saveandSendSms(shebeibianhao, realphonenumber3, sms, apitype);
						    	    }
						    	}					    		
							}
						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
		} 
		return isnotdo;
	}
	
	
	//水稳
	private boolean doSwsms(ShuiwenmanualphbView hv, String filename, Integer bianhao, String shebeibianhao, String smsbaojin, String issms, String biaozhun, String jiaobanphonenumber, String phonenumber, 
			String biaozhun2, String jiaobanphonenumber2, String phonenumber2, 
			String biaozhun3, String jiaobanphonenumber3, String phonenumber3, String shijizhi, String wucha, 
			String lilunzhi, String sheji, String smscontent, String fieldname, 
			String baocunshijian, String Chuliaoshijian, String isshowchuliao, String bhzminchen, int ziduantype, int sendcount, int onefrequency, String danwei) {
		boolean isnotdo = true;
		if (StringUtil.Null2Blank(shijizhi).length()>0 && StringUtil.Null2Blank(wucha).length()>0
				&& StringUtil.Null2Blank(biaozhun).length()>0) {
				try {
					if (shijizhi.length()>8) {
						shijizhi = String.format("%1$.2f",Double.valueOf(shijizhi));
					}
					if (wucha.length()>8) {
						wucha = String.format("%1$.2f",Double.valueOf(wucha));
					}
					/*
					if (StringUtil.Null2Blank(lilunzhi).length()>0 && lilunzhi.length()>8) {
						lilunzhi = String.format("%1$.2f",Double.valueOf(lilunzhi));
					}*/
					if (StringUtil.Null2Blank(lilunzhi).length()>0) {
						lilunzhi = String.format("%1$.2f",Double.valueOf(lilunzhi));
					}
					double shiji = Double.valueOf(shijizhi);
					double sjwucha = 0;
					double bz = -1;
					double bz2 = -1;
					double bz3 = -1;
					double finalbz = -1;
					boolean denji1 = false;
					boolean denji2 = false;
					boolean denji3 = false;	
					String leixin = "误差";
					
					if (StringUtil.Null2Blank(biaozhun).length()>0) {
						try {
							bz = Double.valueOf(biaozhun);
						} catch (Exception e) {
						}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun2).length()>0) {
						try {
							bz2 = Double.valueOf(biaozhun2);
						} catch (Exception e) {
						}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun3).length()>0 ) {
						try {
							bz3 = Double.valueOf(biaozhun3);
						} catch (Exception e) {
						}
					}
					
					double lilun = -1;
					
					boolean issheji = false;
					if (StringUtil.Null2Blank(lilunzhi).length()>0) {
						try {
							lilun = Double.valueOf(lilunzhi);
							sjwucha = Double.valueOf(wucha);
						} catch (Exception e) {
						}
					} else if (StringUtil.Null2Blank(sheji).length()>0) {
						try {
							lilun = Double.valueOf(sheji);
							sjwucha = shiji - lilun;
							issheji = true;
						} catch (Exception e) {
						}
					}
					/*
					String gujifs = hv.getChangliang();
					boolean slok = false;
					if (StringUtil.Null2Blank(gujifs).length()>0) {
						try {
							if (Double.valueOf(gujifs)>200) {
								slok = true;
							}
						} catch (Exception e) {
						}
					}
					*/
					boolean slok = true;
					
					if (lilun > 0 && shiji > 0 && Math.abs(sjwucha) > 0 && slok && (ziduantype!=0 || sjwucha<0) && (ziduantype!=1 || sjwucha<0)) {
						String sms = "";
						if (bz3 > 0 && Math.abs(sjwucha)-bz3>0) {
							finalbz = bz3;
							denji3 = true;
							denji2 = true;
							denji1 = true;
						} else if (bz2 > 0 && Math.abs(sjwucha)-bz2>0) {
							finalbz = bz2;
							denji2 = true;
							denji1 = true;
						} else if (bz > 0 && Math.abs(sjwucha)-bz>0) {
							finalbz = bz;
							denji1 = true;
						} else if (bz == 0 && sjwucha<0){
							finalbz = bz;
							denji1 = true;
						}
					    
					    if (finalbz >= 0) {	
					    	int biaozhuntype=0;
					    	if (sjwucha > 0) {
								biaozhuntype = 1;
							}
					    	setSwSmsTypeDb(filename, bianhao, shebeibianhao, denji2, denji3, biaozhuntype, ziduantype);
					    	isnotdo = false;
					    	Calendar day=Calendar.getInstance();
					    	day.add(Calendar.MINUTE, -30);
					    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    	Date baocun = day.getTime();					    	
								if (StringUtil.Null2Blank(isshowchuliao).equalsIgnoreCase("1") &&
										StringUtil.Null2Blank(Chuliaoshijian).length()>0 && 
										StringUtil.Null2Blank(Chuliaoshijian).length()<22) {
				        		  try {
				        			  baocun = sdf.parse(Chuliaoshijian);
								  } catch (ParseException e) {
									try {
										baocun = sdf.parse(baocunshijian);
										} catch (ParseException ex) {
											
									}
								  }
									
								} else {
									try {
										baocun = sdf.parse(baocunshijian);
										} catch (ParseException ex) {
											
									}
								}
							boolean isnormal = true;
							if (issheji) {
								if (shiji<lilun*0.6 || shiji>lilun*1.4) {							
								   isnormal = false;
								}
							} else {
								if (Math.abs(sjwucha)/lilun>0.4) {							
									   isnormal = false;
								}
							}
							//System.out.println(baocun.after(day.getTime()));
					    	if (StringUtil.Null2Blank(smsbaojin).equalsIgnoreCase("1") 
					    			&& StringUtil.Null2Blank(issms).equalsIgnoreCase("1")
					    			&& isnormal && baocun.after(day.getTime()) 
					    			&& getSwSmsifsend(hv, filename)) {
					    		String apitype = null;
					    		String filepanshu = "100";
					    		String smsmeipan = "[%s]%s:%s实际%s"+danwei+"理论%s"+danwei+"%s%s"+danwei;
					    		//String smshuizong = "[%s]%s:已搅拌盘数%s累计超标准次数%s";
					    		File fp = new File(filename);
								if(fp.exists()){
									Properties prop=new Properties();
									try {
										prop.load(new FileInputStream(fp));												
										apitype = StringUtil.Null2Blank(prop.getProperty("apitype", "0"));
										filepanshu = StringUtil.Null2Blank(prop.getProperty("panshu", "100"));
										//smsmeipan = StringUtil.Null2Blank(prop.getProperty("manualmeipan", "[%s]%s:%s实际%s%%理论%s%%%s%s%%"));
										//smshuizong = StringUtil.Null2Blank(prop.getProperty("huizong", "[%s]%s:已搅拌盘数%s累计超标准次数%s"));
									} catch (FileNotFoundException e) {
										logger.error(e.getMessage());
									} catch (IOException e) {
										logger.error(e.getMessage());
									}
									prop.clear();
								}
					    		switch (getSwSendtype(hv, filename)) {
								case 1:
									//当前盘发送频率小于指定的盘数时发短信
									if (sendcount<onefrequency) {
										String schuliao = baocunshijian;
										if (StringUtil.Null2Blank(isshowchuliao).equalsIgnoreCase("1") && 
												StringUtil.Null2Blank(Chuliaoshijian).length()>0 && 
												StringUtil.Null2Blank(Chuliaoshijian).length()<22) {
											schuliao = "(出料"+Chuliaoshijian+")";
										}
										bhzminchen=bhzminchen.replaceAll("垄茶","");
										if (StringUtil.Null2Blank(fieldname).length()>0) {
											if (StringUtil.Null2Blank(smscontent).length()>0) {
												sms = String.format(smscontent, schuliao, bhzminchen, fieldname, shiji, lilun, leixin, sjwucha);
											}  else {
												sms = String.format(smsmeipan, 
														schuliao, bhzminchen, fieldname, shiji, lilun, leixin, sjwucha);
											}
										}
									}
									break;
								case 2:
									/*
									String panshu = "100";
									int ps = 100;
									if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
										panshu = StringUtil.Null2Blank(hv.getPanshu());
									} else {
										panshu = filepanshu;
									}
									if (panshu.length() > 0) {
										try {
											ps = Integer.valueOf(panshu);
										} catch (Exception e) {
											ps = 100;
										}
									}
									Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
									if (null != xxsms && xxsms.getPanshu() >= ps) {
										sms = String.format(smshuizong, 
												baocunshijian, bhzminchen, xxsms.getPanshu(), xxsms.getLeiji());
										xxsms.setLeiji(0);
										xxsms.setPanshu(0);
										sysService.saveXxsms(xxsms);
									}
									*/ 
									break;
								default:
									break;
								}
					    		if (sms.length() > 0) {
					    			String realphonenumber = "";
					    			String realphonenumber2 = "";
					    			String realphonenumber3 = "";
					    			
					    			if (denji1) {
					    				realphonenumber = sysService.getRealPhoneNumber(phonenumber);
					    				if (StringUtil.Null2Blank(realphonenumber).length()==0) {
						    				realphonenumber = sysService.getRealPhoneNumber(jiaobanphonenumber);
										}
									}
					    			
					    			if (denji2) {
										realphonenumber2 = sysService.getRealPhoneNumber(phonenumber2);
										if (StringUtil.Null2Blank(realphonenumber2).length()==0) {
						    				realphonenumber2 = sysService.getRealPhoneNumber(jiaobanphonenumber2);
										}
									}
					    			
					    			if (denji3) {
										realphonenumber3 = sysService.getRealPhoneNumber(phonenumber3);
										if (StringUtil.Null2Blank(realphonenumber3).length()==0) {
						    				realphonenumber3 = sysService.getRealPhoneNumber(jiaobanphonenumber3);
										}
									}
					    			
					    			
					    			if (denji1 && StringUtil.Null2Blank(realphonenumber).length()>0) {
					    				sysService.saveandSendSms(shebeibianhao, realphonenumber, sms, apitype);
					    			}
						    	    if (denji2 && StringUtil.Null2Blank(realphonenumber2).length()>0) {
						    	    	sysService.saveandSendSms(shebeibianhao, realphonenumber2, sms, apitype);
						    	    }
						    	    if (denji3 && StringUtil.Null2Blank(realphonenumber3).length()>0) {
						    	    	sysService.saveandSendSms(shebeibianhao, realphonenumber3, sms, apitype);
						    	    }
						    	}					    		
							}
						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
		} 
		return isnotdo;
	}
	
	private boolean doSwsmssn(ShuiwenmanualphbView hv, String filename, Integer bianhao, String shebeibianhao, String smsbaojin, String issms, String biaozhun, String jiaobanphonenumber, String phonenumber, 
			String biaozhun2, String jiaobanphonenumber2, String phonenumber2, 
			String biaozhun3, String jiaobanphonenumber3, String phonenumber3, String shijizhi, String wucha, 
			String lilunzhi, String sheji, String smscontent, String fieldname, 
			String baocunshijian, String Chuliaoshijian, String isshowchuliao, String bhzminchen, int ziduantype,
			int sendcount, int onefrequency, String danwei,String biaozhun1high,String biaozhun2high,String biaozhun3high) {
		boolean isnotdo = true;
		if (StringUtil.Null2Blank(shijizhi).length()>0 && StringUtil.Null2Blank(wucha).length()>0
				&& StringUtil.Null2Blank(biaozhun).length()>0) {
				try {
					if (shijizhi.length()>8) {
						shijizhi = String.format("%1$.2f",Double.valueOf(shijizhi));
					}
					if (wucha.length()>8) {
						wucha = String.format("%1$.2f",Double.valueOf(wucha));
					}
					/*
					if (StringUtil.Null2Blank(lilunzhi).length()>0 && lilunzhi.length()>8) {
						lilunzhi = String.format("%1$.2f",Double.valueOf(lilunzhi));
					}*/
					if (StringUtil.Null2Blank(lilunzhi).length()>0) {
						lilunzhi = String.format("%1$.2f",Double.valueOf(lilunzhi));
					}
					double shiji = Double.valueOf(shijizhi);
					double sjwucha = 0;
					double bz = -1;
					double bz2 = -1;
					double bz3 = -1;
					double bzhigh=-1;
					double bz2high=-1;
					double bz3high=-1;
					double finalbz = -1;
					boolean denji1 = false;
					boolean denji2 = false;
					boolean denji3 = false;	
					String leixin = "误差";
					
					if (StringUtil.Null2Blank(biaozhun).length()>0) {
						try {
							bz = Double.valueOf(biaozhun);
						} catch (Exception e) {}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun2).length()>0) {
						try {
							bz2 = Double.valueOf(biaozhun2);
						} catch (Exception e) {}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun3).length()>0 ) {
						try {
							bz3 = Double.valueOf(biaozhun3);
						} catch (Exception e) {}
					}
					
					if (StringUtil.Null2Blank(biaozhun1high).length()>0) {
						try {
							bzhigh = Double.valueOf(biaozhun1high);
						} catch (Exception e) {}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun2high).length()>0) {
						try {
							bz2high = Double.valueOf(biaozhun2high);
						} catch (Exception e) {}
					}
					
					
					if (StringUtil.Null2Blank(biaozhun3high).length()>0 ) {
						try {
							bz3high = Double.valueOf(biaozhun3high);
						} catch (Exception e) {}
					}
					
					double lilun = -1;
					
					boolean issheji = false;
					if (StringUtil.Null2Blank(lilunzhi).length()>0) {
						try {
							lilun = Double.valueOf(lilunzhi);
							sjwucha = Double.valueOf(wucha);
						} catch (Exception e) {}
					} else if (StringUtil.Null2Blank(sheji).length()>0) {
						try {
							lilun = Double.valueOf(sheji);
							sjwucha = shiji - lilun;
							issheji = true;
						} catch (Exception e) {}
					}
					boolean slok = true;
					
					if (lilun > 0 && shiji > 0 && Math.abs(sjwucha) > 0 && slok && (ziduantype!=0 || sjwucha<0) && (ziduantype!=1 || sjwucha<0)) {
						String sms = "";						
						String lowStr="";
						double highStr=0;
						
						if(bz3 > 0 && Math.abs(sjwucha)-bz3>0){
							finalbz = bz3;
							denji3 = true;
							denji2 = true;
							denji1 = true;
							lowStr = "超高级";
							highStr = bz3high;
						}else if(bz2 > 0 && Math.abs(sjwucha)-bz2>0){
							finalbz = bz2;
							denji2 = true;
							denji1 = true;
							lowStr = "超中级";
							highStr = bz2high;
						}else if(bz > 0 && Math.abs(sjwucha)-bz>0){//实际值小于下限  或者 大于上限  报警
							finalbz =1;
							denji1=true;
							lowStr = "超低级";
							highStr = bzhigh;
						}
						
					    if (finalbz > 0) {	
					    	int biaozhuntype=0;
					    	if (sjwucha > 0) {
								biaozhuntype = 1;
							}
					    	setSwSmsTypeDb(filename, bianhao, shebeibianhao, denji2, denji3, biaozhuntype, ziduantype);
					    	isnotdo = false;
					    	Calendar day=Calendar.getInstance();
					    	day.add(Calendar.MINUTE, -30);
					    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    	Date baocun = day.getTime();					    	
								if (StringUtil.Null2Blank(isshowchuliao).equalsIgnoreCase("1") &&
										StringUtil.Null2Blank(Chuliaoshijian).length()>0 && 
										StringUtil.Null2Blank(Chuliaoshijian).length()<22) {
									try {
										baocun = sdf.parse(Chuliaoshijian);
									} catch (ParseException e) {
										try {
											baocun = sdf.parse(baocunshijian);
										} catch (ParseException ex) {}
									}
								} else {
									try {
										baocun = sdf.parse(baocunshijian);
									} catch (ParseException ex) {}
								}
							boolean isnormal = true;
					    	if (StringUtil.Null2Blank(smsbaojin).equalsIgnoreCase("1") 
					    			&& StringUtil.Null2Blank(issms).equalsIgnoreCase("1")
					    			&& isnormal && baocun.after(day.getTime()) 
					    			&& getSwSmsifsend(hv, filename)) {
					    		String apitype = null;
					    		String filepanshu = "100";
					    		//String smsmeipan = "[%s]%s:%s实际%s"+danwei+"理论%s"+danwei+"%s%s"+danwei;
					    		String smsmeipan = "[%s]%s:%s实际%s"+danwei+"理论%s"+danwei+"%s%s%s"+danwei;
					    		//String smshuizong = "[%s]%s:已搅拌盘数%s累计超标准次数%s";
					    		File fp = new File(filename);
								if(fp.exists()){
									Properties prop=new Properties();
									try {
										prop.load(new FileInputStream(fp));												
										apitype = StringUtil.Null2Blank(prop.getProperty("apitype", "0"));
										filepanshu = StringUtil.Null2Blank(prop.getProperty("panshu", "100"));
										//smsmeipan = StringUtil.Null2Blank(prop.getProperty("manualmeipan", "[%s]%s:%s实际%s%%理论%s%%%s%s%%"));
										//smshuizong = StringUtil.Null2Blank(prop.getProperty("huizong", "[%s]%s:已搅拌盘数%s累计超标准次数%s"));
									} catch (FileNotFoundException e) {
										logger.error(e.getMessage());
									} catch (IOException e) {
										logger.error(e.getMessage());
									}
									prop.clear();
								}
					    		switch (getSwSendtype(hv, filename)) {
								case 1:
									//当前盘发送频率小于指定的盘数时发短信
									if (sendcount<onefrequency) {
										String schuliao = baocunshijian;
										if (StringUtil.Null2Blank(isshowchuliao).equalsIgnoreCase("1") && 
												StringUtil.Null2Blank(Chuliaoshijian).length()>0 && 
												StringUtil.Null2Blank(Chuliaoshijian).length()<22) {
											schuliao = "(出料"+Chuliaoshijian+")";
										}
										bhzminchen=bhzminchen.replaceAll("垄茶","");
										if (StringUtil.Null2Blank(fieldname).length()>0) {
											//[%s]%s:%s实际%s"+danwei+"理论%s"+danwei+"允许范围%s%s%s"+danwei
											if (StringUtil.Null2Blank(smscontent).length()>0) {
												sms = String.format(smscontent, schuliao, bhzminchen, fieldname, shiji, lilun,lowStr, leixin, sjwucha);
											}  else {
												sms = String.format(smsmeipan, 
														schuliao, bhzminchen, fieldname, shiji, lilun, lowStr ,leixin, sjwucha);
											}
										}
									}
									break;
								case 2:
									/*
									String panshu = "100";
									int ps = 100;
									if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
										panshu = StringUtil.Null2Blank(hv.getPanshu());
									} else {
										panshu = filepanshu;
									}
									if (panshu.length() > 0) {
										try {
											ps = Integer.valueOf(panshu);
										} catch (Exception e) {
											ps = 100;
										}
									}
									Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
									if (null != xxsms && xxsms.getPanshu() >= ps) {
										sms = String.format(smshuizong, 
												baocunshijian, bhzminchen, xxsms.getPanshu(), xxsms.getLeiji());
										xxsms.setLeiji(0);
										xxsms.setPanshu(0);
										sysService.saveXxsms(xxsms);
									}
									*/ 
									break;
								default:
									break;
								}
					    		if (sms.length() > 0) {
					    			String realphonenumber = "";
					    			String realphonenumber2 = "";
					    			String realphonenumber3 = "";
					    			
					    			if (denji1) {
					    				realphonenumber = sysService.getRealPhoneNumber(phonenumber);
					    				if (StringUtil.Null2Blank(realphonenumber).length()==0) {
						    				realphonenumber = sysService.getRealPhoneNumber(jiaobanphonenumber);
										}
									}
					    			
					    			if (denji2) {
										realphonenumber2 = sysService.getRealPhoneNumber(phonenumber2);
										if (StringUtil.Null2Blank(realphonenumber2).length()==0) {
						    				realphonenumber2 = sysService.getRealPhoneNumber(jiaobanphonenumber2);
										}
									}
					    			
					    			if (denji3) {
										realphonenumber3 = sysService.getRealPhoneNumber(phonenumber3);
										if (StringUtil.Null2Blank(realphonenumber3).length()==0) {
						    				realphonenumber3 = sysService.getRealPhoneNumber(jiaobanphonenumber3);
										}
									}
					    			
					    			if (denji1 && StringUtil.Null2Blank(realphonenumber).length()>0) {
					    				sysService.saveandSendSms(shebeibianhao, realphonenumber, sms, apitype);
					    			}
						    	    if (denji2 && StringUtil.Null2Blank(realphonenumber2).length()>0) {
						    	    	sysService.saveandSendSms(shebeibianhao, realphonenumber2, sms, apitype);
						    	    }
						    	    if (denji3 && StringUtil.Null2Blank(realphonenumber3).length()>0) {
						    	    	sysService.saveandSendSms(shebeibianhao, realphonenumber3, sms, apitype);
						    	    }
						    	}					    		
							}
						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
		} 
		return isnotdo;
	}
	
	private boolean doxcsms(TemperaturedataView tmpdata, Xcsmscfg xcsms, String filename) {
		boolean isnotdo = true;
		double shiji = -1;
		try {
			shiji = Double.valueOf(tmpdata.getTmpdata());
		} catch (Exception e) {
		}
		double lowbz = -1;
		double highbz = -1;
		double lowbz1 = -1;
		double highbz1 = -1;
		double lowbz2 = -1;
		double highbz2 = -1;
		double finalbz = -1;
		boolean denji1 = false;
		boolean denji2 = false;
		boolean denji3 = false;
		try {
			lowbz = Double.valueOf(xcsms.getTmplow());
		} catch (Exception e) {
		}
		try {
			highbz = Double.valueOf(xcsms.getTmphigh());
		} catch (Exception e) {
		}
		try {
			lowbz1 = Double.valueOf(xcsms.getTmplow1());
		} catch (Exception e) {
		}
		try {
			highbz1 = Double.valueOf(xcsms.getTmphigh1());
		} catch (Exception e) {
		}		try {
			lowbz2 = Double.valueOf(xcsms.getTmplow2());
		} catch (Exception e) {
		}
		try {
			highbz2 = Double.valueOf(xcsms.getTmphigh2());
		} catch (Exception e) {
		}
		
		if (shiji>0) {
			if (lowbz2>0) {
				if (shiji<lowbz2) {
					finalbz = lowbz2;
					denji3 = true;
					denji2 = true;
					denji1 = true;
				}
			}
			if (highbz2>0) {
				if (shiji>highbz2) {
					finalbz = highbz2;
					denji3 = true;
					denji2 = true;
					denji1 = true;
				}
			}
			if (lowbz1>0) {
				if (shiji<lowbz1) {
					finalbz = lowbz1;
					denji2 = true;
					denji1 = true;
				}
			}
			if (highbz1>0) {
				if (shiji>highbz1) {
					finalbz = highbz1;
					denji2 = true;
					denji1 = true;
				}
			}
			if (lowbz>0) {
				if (shiji<lowbz) {
					finalbz = lowbz;
					denji1 = true;
				}
			}
			if (highbz>0) {
				if (shiji>highbz) {
					finalbz = highbz;
					denji1 = true;
				}
			}
		}
		if (finalbz > 0) {	
			saveXctmpjieguo(tmpdata, denji2, denji3, shiji<finalbz);
			logger.info(tmpdata.getGprsbianhao()+"温度预警"+shiji);
			Calendar day=Calendar.getInstance();
	    	day.add(Calendar.MINUTE, -30);
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date baocun = day.getTime();
	    	try {
				baocun = sdf.parse(tmpdata.getTmpshijian());
				} catch (ParseException ex) {
					
			}
				if (StringUtil.Null2Blank(xcsms.getSmsbaojin()).equalsIgnoreCase("1") 
		    			&& baocun.after(day.getTime()) 
		    			&& getTmpSmsifsend(filename)) {
					String smsmeipan = "[%s]%s:摊铺温度实际%s℃,已预警";
					String sms = String.format(smsmeipan, 
							tmpdata.getTmpshijian(), tmpdata.getBanhezhanminchen(), shiji);
					
		    			String realphonenumber = "";
		    			String realphonenumber2 = "";
		    			String realphonenumber3 = "";
		    			
		    			if (denji1) {
		    				realphonenumber = sysService.getRealPhoneNumber(xcsms.getNumberlow());		    				
						}
		    			
		    			if (denji2) {
							realphonenumber2 = sysService.getRealPhoneNumber(xcsms.getNumbermid());							
						}
		    			
		    			if (denji3) {
							realphonenumber3 = sysService.getRealPhoneNumber(xcsms.getNumberhigh());							
						}
		    			
		    			
		    			if (denji1 && StringUtil.Null2Blank(realphonenumber).length()>0) {
		    				sysService.saveandSendSms(tmpdata.getGprsbianhao(), realphonenumber, sms, null);
		    			}
			    	    if (denji2 && StringUtil.Null2Blank(realphonenumber2).length()>0) {
			    	    	sysService.saveandSendSms(tmpdata.getGprsbianhao(), realphonenumber2, sms, null);
			    	    }
			    	    if (denji3 && StringUtil.Null2Blank(realphonenumber3).length()>0) {
			    	    	sysService.saveandSendSms(tmpdata.getGprsbianhao(), realphonenumber3, sms, null);
			    	    }
				}
		}
		return isnotdo;
	}
	
	private boolean doxcsms(SpeeddataView speeddata, Xcsmscfg xcsms, String filename) {
		boolean isnotdo = true;
		double shiji = -1;
		try {
			shiji = Double.valueOf(speeddata.getSudu());
		} catch (Exception e) {
		}
		double lowbz = -1;
		double highbz = -1;
		double lowbz1 = -1;
		double highbz1 = -1;
		double lowbz2 = -1;
		double highbz2 = -1;
		double finalbz = -1;
		boolean denji1 = false;
		boolean denji2 = false;
		boolean denji3 = false;
		try {
			lowbz = Double.valueOf(xcsms.getSpeedlow());
		} catch (Exception e) {
		}
		try {
			highbz = Double.valueOf(xcsms.getSpeedhigh());
		} catch (Exception e) {
		}
		try {
			lowbz1 = Double.valueOf(xcsms.getSpeedlow1());
		} catch (Exception e) {
		}
		try {
			highbz1 = Double.valueOf(xcsms.getSpeedhigh1());
		} catch (Exception e) {
		}		try {
			lowbz2 = Double.valueOf(xcsms.getSpeedlow2());
		} catch (Exception e) {
		}
		try {
			highbz2 = Double.valueOf(xcsms.getSpeedhigh2());
		} catch (Exception e) {
		}
		
		if (shiji>0) {
			if (lowbz2>0) {
				if (shiji<lowbz2) {
					finalbz = lowbz2;
					denji3 = true;
					denji2 = true;
					denji1 = true;
				}
			}
			if (highbz2>0) {
				if (shiji>highbz2) {
					finalbz = highbz2;
					denji3 = true;
					denji2 = true;
					denji1 = true;
				}
			}
			if (lowbz1>0) {
				if (shiji<lowbz1) {
					finalbz = lowbz1;
					denji2 = true;
					denji1 = true;
				}
			}
			if (highbz1>0) {
				if (shiji>highbz1) {
					finalbz = highbz1;
					denji2 = true;
					denji1 = true;
				}
			}
			if (lowbz>0) {
				if (shiji<lowbz) {
					finalbz = lowbz;
					denji1 = true;
				}
			}
			if (highbz>0) {
				if (shiji>highbz) {
					finalbz = highbz;
					denji1 = true;
				}
			}
		}
		if (finalbz > 0) {	
			saveXcspeedjieguo(speeddata, denji2, denji3, shiji<finalbz);
			logger.info(speeddata.getGprsbianhao()+"速度预警"+shiji);
			Calendar day=Calendar.getInstance();
	    	day.add(Calendar.MINUTE, -15);
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date baocun = day.getTime();
	    	try {
				baocun = sdf.parse(speeddata.getShijian());
				} catch (ParseException ex) {
					
			}
				if (StringUtil.Null2Blank(xcsms.getSmsbaojin()).equalsIgnoreCase("1") 
		    			&& baocun.after(day.getTime()) 
		    			&& getTmpSmsifsend(filename)) {
					String smsmeipan = "[%s]%s:碾压速度实际%sm/min,已预警";
					String sms = String.format(smsmeipan, 
							speeddata.getShijian(), speeddata.getBanhezhanminchen(), shiji);
					
		    			String realphonenumber = "";
		    			String realphonenumber2 = "";
		    			String realphonenumber3 = "";
		    			
		    			if (denji1) {
		    				realphonenumber = sysService.getRealPhoneNumber(xcsms.getNumberlow());		    				
						}
		    			
		    			if (denji2) {
							realphonenumber2 = sysService.getRealPhoneNumber(xcsms.getNumbermid());							
						}
		    			
		    			if (denji3) {
							realphonenumber3 = sysService.getRealPhoneNumber(xcsms.getNumberhigh());							
						}
		    			
		    			
		    			if (denji1 && StringUtil.Null2Blank(realphonenumber).length()>0) {
		    				sysService.saveandSendSms(speeddata.getGprsbianhao(), realphonenumber, sms, null);
		    			}
			    	    if (denji2 && StringUtil.Null2Blank(realphonenumber2).length()>0) {
			    	    	sysService.saveandSendSms(speeddata.getGprsbianhao(), realphonenumber2, sms, null);
			    	    }
			    	    if (denji3 && StringUtil.Null2Blank(realphonenumber3).length()>0) {
			    	    	sysService.saveandSendSms(speeddata.getGprsbianhao(), realphonenumber3, sms, null);
			    	    }
				}
		}
		return isnotdo;
	}
	
	private void setSmsTypeDb(String filename, Integer bianhao, String shebeibianhao, 
			boolean denji2, boolean denji3,int biaozhuntype,int ziduantype) {
		Xiangxixxjieguo xxjg = sysService.getXxjieguobybh(bianhao);
		if (null == xxjg) {
			xxjg = new Xiangxixxjieguo();
			xxjg.setXinxibianhao(bianhao);
		}
		String zhuntai = "0";
		switch (biaozhuntype) {
		case 0:
			if (denji3) {
				zhuntai = "3";
			} else if (denji2) {
				zhuntai = "2";
			} else {
				zhuntai = "1";
			}
			break;
		case 1:
			if (denji3) {
				zhuntai = "6";
			} else if (denji2) {
				zhuntai = "5";
			} else {
				zhuntai = "4";
			}
			break;
		default:
			break;
		}
		switch (ziduantype) {
		case 0:
			xxjg.setJiaobanshijian(zhuntai);
			break;
		case 1:
			xxjg.setShuini1_shijizhi(zhuntai);
			break;
		case 2:
			xxjg.setShuini2_shijizhi(zhuntai);
			break;
		case 3:
			xxjg.setShui1_shijizhi(zhuntai);
			break;
		case 4:
			xxjg.setShui2_shijizhi(zhuntai);
			break;
		case 5:
			xxjg.setKuangfen3_shijizhi(zhuntai);
			break;
		case 6:
			xxjg.setFeimeihui4_shijizhi(zhuntai);
			break;
		case 7:
			xxjg.setFenliao5_shijizhi(zhuntai);
			break;
		case 8:
			xxjg.setFenliao6_shijizhi(zhuntai);
			break;
		case 9:
			xxjg.setSha1_shijizhi(zhuntai);
			break;
		case 10:
			xxjg.setShi1_shijizhi(zhuntai);
			break;
		case 11:
			xxjg.setSha2_shijizhi(zhuntai);
			break;
		case 12:
			xxjg.setShi2_shijizhi(zhuntai);
			break;
		case 13:
			xxjg.setGuliao5_shijizhi(zhuntai);
			break;
		case 14:
			xxjg.setWaijiaji1_shijizhi(zhuntai);
			break;
		case 15:
			xxjg.setWaijiaji2_shijizhi(zhuntai);
			break;
		case 16:
			xxjg.setWaijiaji3_shijizhi(zhuntai);
			break;
		case 17:
			xxjg.setWaijiaji4_shijizhi(zhuntai);
			break;
		default:
			break;
		}
		if (null == xxjg.getLeiji()) {
			xxjg.setLeiji(1);
		} else {
			xxjg.setLeiji(xxjg.getLeiji()+1);
		}
		
		sysService.saveXxjieguo(xxjg);
		

		Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
		if (null != xxsms) {
			xxsms.setLeiji(xxsms.getLeiji()+1);
			sysService.saveXxsms(xxsms);
		} 
	}
	
	private void setLqSmsTypeDb(String filename, Integer bianhao, String shebeibianhao, 
			boolean denji2, boolean denji3,int biaozhuntype,int ziduantype) {
		Liqingxixxjieguo lqjg = sysService.getLljieguobybh(bianhao);
		if (null == lqjg) {
			lqjg = new Liqingxixxjieguo();
			lqjg.setLqbianhao(bianhao);
		}
		String zhuntai = "0";
		Integer leixing =0;
		switch (biaozhuntype) {
			case 0:
				if (denji3) {
					zhuntai = "3";
					leixing =3;
				} else if (denji2) {
					zhuntai = "2";
					leixing =2;
				} else {
					zhuntai = "1";
					leixing =1;
				}
				break;
			case 1:
				if (denji3) {
					zhuntai = "6";
					leixing =3;
				} else if (denji2) {
					zhuntai = "5";
					leixing =2;
				} else {
					zhuntai = "4";
					leixing =1;
				}
				break;
			default:
				break;
		}
		switch (ziduantype) {
			case 0:
				lqjg.setJbsj(zhuntai);
				break;
			case 1:
				lqjg.setSjysb(zhuntai);
				break;
			case 2:
				lqjg.setSjg1(zhuntai);
				break;
			case 3:
				lqjg.setSjg2(zhuntai);
				break;
			case 4:
				lqjg.setSjg3(zhuntai);
				break;
			case 5:
				lqjg.setSjg4(zhuntai);
				break;
			case 6:
				lqjg.setSjg5(zhuntai);
				break;
			case 7:
				lqjg.setSjg6(zhuntai);
				break;
			case 8:
				lqjg.setSjg7(zhuntai);
				break;
			case 9:
				lqjg.setSjf1(zhuntai);
				break;
			case 10:
				lqjg.setSjf2(zhuntai);
				break;
			case 11:
				lqjg.setSjlq(zhuntai);
				break;
			case 12:
				lqjg.setSjtjj(zhuntai);
				break;
			case 13:
				lqjg.setGlwd(zhuntai);
				break;
			case 14:
				lqjg.setLqwd(zhuntai);
				break;	
			case 15:
				lqjg.setBeiy1(zhuntai);
				break;	
			case 16:
				lqjg.setBeiy2(zhuntai);
				break;	
			case 17:
				lqjg.setBeiy3(zhuntai);
				break;	
			default:
				break;
		}
		if (null == lqjg.getLeiji()) {
			lqjg.setLeiji(1);
		} else {
			lqjg.setLeiji(lqjg.getLeiji()+1);
		}
    	Calendar day=Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		lqjg.setJieguoshijian(sdf.format(day.getTime()));
		if (lqjg.getLeixing()==null) {
			lqjg.setLeixing(String.valueOf(leixing));
		} else {
			if (leixing>Integer.parseInt(lqjg.getLeixing())) {
				 lqjg.setLeixing(String.valueOf(leixing));
			}
		}
		sysService.saveLqjieguo(lqjg);
		

		Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
		if (null != xxsms) {
			xxsms.setLeiji(xxsms.getLeiji()+1);
			sysService.saveXxsms(xxsms);
		} 
	}
	
	
	private void setSwSmsTypeDb(String filename, Integer bianhao, String shebeibianhao, 
			boolean denji2, boolean denji3,int biaozhuntype,int ziduantype) {
		Shuiwenxixxjieguo lqjg = sysService.getSwjieguobybh(bianhao);
		if (null == lqjg) {
			lqjg = new Shuiwenxixxjieguo();
			lqjg.setSwbianhao(bianhao);
		}
		String zhuntai = "0";
		String leixing="";
		switch (biaozhuntype) {
			case 0:
				if (denji3) {
					zhuntai = "3";
					leixing ="3";
				} else if (denji2) {
					zhuntai = "2";
					leixing ="2";
				} else {
					zhuntai = "1";
					leixing ="1";
				}
				break;
			case 1:
				if (denji3) {
					zhuntai = "6";
					leixing ="3";
				} else if (denji2) {
					zhuntai = "5";
					leixing ="2";
				} else {
					zhuntai = "4";
					leixing ="1";
				}
				break;
			default:
				break;
		}
		switch (ziduantype) {
			case 0:
				lqjg.setSjshui(zhuntai);
				break;
			case 1:
				lqjg.setSjg1(zhuntai);
				break;
			case 2:
				lqjg.setSjg2(zhuntai);
				break;
			case 3:
				lqjg.setSjg3(zhuntai);
				break;
			case 4:
				lqjg.setSjg4(zhuntai);
				break;
			case 5:
				lqjg.setSjg5(zhuntai);
				break;
			case 6:
				lqjg.setSjf1(zhuntai);
				break;
			case 7:
				lqjg.setSjf2(zhuntai);
				break;
			case 8:
				lqjg.setBeiy1(zhuntai);
				break;	
			default:
				break;
		}
		if (null == lqjg.getLeiji()) {
			lqjg.setLeiji(1);
		} else {
			lqjg.setLeiji(lqjg.getLeiji()+1);
		}
		if(StringUtil.Null2Blank(lqjg.getLeixing()).length()>0){
			if(Double.parseDouble(lqjg.getLeixing())<Double.parseDouble(leixing)){
				lqjg.setLeixing(leixing);
			}
		}else{
			lqjg.setLeixing(leixing);
		}		
    	Calendar day=Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		lqjg.setJieguoshijian(sdf.format(day.getTime()));
		sysService.saveSwjieguo(lqjg);
		
		Xiangxixxsms xxsms = sysService.getXxsmsbybh(shebeibianhao);
		if (null != xxsms) {
			xxsms.setLeiji(xxsms.getLeiji()+1);
			sysService.saveXxsms(xxsms);
		} 
	}
	
	
	private void saveXctmpjieguo(TemperaturedataView tmpdata, boolean denji2, boolean denji3,boolean islow) {
		Temperaturejieguo tmpjg = new Temperaturejieguo();
		tmpjg.setTmpno(tmpdata.getTmpno());
		String zhuntai = "0";
		if (islow) {
			if (denji3) {
				zhuntai = "3";
			} else if (denji2) {
				zhuntai = "2";
			} else {
				zhuntai = "1";
			}
		} else {
			if (denji3) {
				zhuntai = "6";
			} else if (denji2) {
				zhuntai = "5";
			} else {
				zhuntai = "4";
			}
		}
		tmpjg.setTmpdata(zhuntai);
		
    	Calendar day=Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tmpjg.setJieguoshijian(sdf.format(day.getTime()));
		sysService.saveTmpjieguo(tmpjg);	
	}
	
	private void saveXcspeedjieguo(SpeeddataView speeddata, boolean denji2, boolean denji3,boolean islow) {
		Speedjieguo speedjg = new Speedjieguo();
		speedjg.setSpeedno(speeddata.getGpsno());
		String zhuntai = "0";
		if (islow) {
			if (denji3) {
				zhuntai = "3";
			} else if (denji2) {
				zhuntai = "2";
			} else {
				zhuntai = "1";
			}
		} else {
			if (denji3) {
				zhuntai = "6";
			} else if (denji2) {
				zhuntai = "5";
			} else {
				zhuntai = "4";
			}
		}
		speedjg.setSpeeddata(zhuntai);
		
    	Calendar day=Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		speedjg.setJieguoshijian(sdf.format(day.getTime()));
		sysService.saveSpeedjieguo(speedjg);	
	}
	
	private boolean getSmsifsend(Hntview hv, String filename) {		
		boolean ifsend = false;
		String smstype = "";
		Integer frequency = 0;		
		File fp = new File(filename);
		Properties prop=new Properties();
		Calendar day=Calendar.getInstance();
		SimpleDateFormat senddf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sendtime = null;
		try {
			sendtime = senddf.parse("2010-01-01 00:00:00");
		} catch (ParseException e2) {}
		if(fp.exists()){
			try {			
				prop.load(new FileInputStream(fp));
				smstype = StringUtil.Null2Blank(prop.getProperty("smstype"));
				frequency = Integer.parseInt(prop.getProperty("frequency"));
				sendtime = senddf.parse(prop.getProperty("sendtime"));
			} catch (FileNotFoundException e1) {
			} catch (Exception e1) {}
		}
		if(fp.exists()){
			if (smstype.equalsIgnoreCase("1")) {
				ifsend = true;
			} else if (smstype.equalsIgnoreCase("0")) {
				String ambegin = StringUtil.Null2Blank(prop.getProperty("ambegin"));	
				String amend = StringUtil.Null2Blank(prop.getProperty("amend"));	
				String pmbegin = StringUtil.Null2Blank(prop.getProperty("pmbegin"));	
				String pmend = StringUtil.Null2Blank(prop.getProperty("pmend"));	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd ");
				ambegin = sdf2.format(day.getTime())+ambegin;
				amend = sdf2.format(day.getTime())+amend;
				pmbegin = sdf2.format(day.getTime())+pmbegin;
				pmend = sdf2.format(day.getTime())+pmend;
				try {
					if ((day.getTime().after(sdf.parse(ambegin)) && 
							day.getTime().before(sdf.parse(amend))) || 
							(day.getTime().after(sdf.parse(pmbegin)) &&
							day.getTime().before(sdf.parse(pmend)))) {
						ifsend = true;
					}
				} catch (ParseException e) {}
			}	
		}	
		if (ifsend) {
			Calendar curday=Calendar.getInstance();
			day.add(Calendar.SECOND, -frequency);
			if (sendtime.before(day.getTime())) {
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(fp);
					prop.setProperty("sendtime", senddf.format(curday.getTime()));
					prop.store(fos, "system");
					fos.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {}
			} else {
				ifsend = false;
			}
		}
		prop.clear();
		return ifsend;
	}
	
	private boolean getLqSmsifsend(LiqingphbView hv, String filename) {		
		boolean ifsend = false;
		String smstype = "";
		Integer frequency = 0;		
		File fp = new File(filename);
		Properties prop=new Properties();
		Calendar day=Calendar.getInstance();
		SimpleDateFormat senddf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sendtime = null;
		try {
			sendtime = senddf.parse("2010-01-01 00:00:00");
		} catch (ParseException e2) {
		}
		if(fp.exists()){
			try {			
				prop.load(new FileInputStream(fp));
				smstype = StringUtil.Null2Blank(prop.getProperty("smstype"));
				frequency = Integer.parseInt(prop.getProperty("frequency"));
				sendtime = senddf.parse(prop.getProperty("sendtime"));
			} catch (FileNotFoundException e1) {
			} catch (Exception e1) {}
		}
		if(fp.exists()){
			if (smstype.equalsIgnoreCase("1")) {
				ifsend = true;
			} else if (smstype.equalsIgnoreCase("0")) {
				String ambegin = StringUtil.Null2Blank(prop.getProperty("ambegin"));	
				String amend = StringUtil.Null2Blank(prop.getProperty("amend"));	
				String pmbegin = StringUtil.Null2Blank(prop.getProperty("pmbegin"));	
				String pmend = StringUtil.Null2Blank(prop.getProperty("pmend"));	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd ");
				ambegin = sdf2.format(day.getTime())+ambegin;
				amend = sdf2.format(day.getTime())+amend;
				pmbegin = sdf2.format(day.getTime())+pmbegin;
				pmend = sdf2.format(day.getTime())+pmend;
				try {
					if ((day.getTime().after(sdf.parse(ambegin)) && 
							day.getTime().before(sdf.parse(amend))) || 
							(day.getTime().after(sdf.parse(pmbegin)) &&
							day.getTime().before(sdf.parse(pmend)))) {
					ifsend = true;
					}
				} catch (ParseException e) {}
			}	
		}
		if (ifsend) {
			Calendar curday=Calendar.getInstance();
			day.add(Calendar.SECOND, -frequency);
			if (sendtime.before(day.getTime())) {
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(fp);
					prop.setProperty("sendtime", senddf.format(curday.getTime()));
					prop.store(fos, "system");
					fos.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {}
			} else {
				ifsend = false;
			}
		}
		prop.clear();
		return ifsend;
	}
	
	private boolean getSwSmsifsend(ShuiwenmanualphbView hv, String filename) {		
		boolean ifsend = false;
		String smstype = "";
		Integer frequency = 0;		
		File fp = new File(filename);
		Properties prop=new Properties();
		Calendar day=Calendar.getInstance();
		SimpleDateFormat senddf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sendtime = null;
		try {
			sendtime = senddf.parse("2010-01-01 00:00:00");
		} catch (ParseException e2) {}
		if(fp.exists()){
			try {			
				prop.load(new FileInputStream(fp));
				smstype = StringUtil.Null2Blank(prop.getProperty("smstype"));
				frequency = Integer.parseInt(prop.getProperty("frequency"));
				sendtime = senddf.parse(prop.getProperty("sendtime"));
			} catch (FileNotFoundException e1) {
			} catch (Exception e1) {}
		}
		if(fp.exists()){
			if (smstype.equalsIgnoreCase("1")) {
				ifsend = true;
			} else if (smstype.equalsIgnoreCase("0")) {
				String ambegin = StringUtil.Null2Blank(prop.getProperty("ambegin"));	
				String amend = StringUtil.Null2Blank(prop.getProperty("amend"));	
				String pmbegin = StringUtil.Null2Blank(prop.getProperty("pmbegin"));	
				String pmend = StringUtil.Null2Blank(prop.getProperty("pmend"));	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd ");
				ambegin = sdf2.format(day.getTime())+ambegin;
				amend = sdf2.format(day.getTime())+amend;
				pmbegin = sdf2.format(day.getTime())+pmbegin;
				pmend = sdf2.format(day.getTime())+pmend;
				try {
					if ((day.getTime().after(sdf.parse(ambegin)) && 
							day.getTime().before(sdf.parse(amend))) || 
							(day.getTime().after(sdf.parse(pmbegin)) &&
							day.getTime().before(sdf.parse(pmend)))) {
						ifsend = true;
					}
				} catch (ParseException e) {}
			}	
		}	
		if (ifsend) {
			Calendar curday=Calendar.getInstance();
			day.add(Calendar.SECOND, -frequency);
			if (sendtime.before(day.getTime())) {
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(fp);
					prop.setProperty("sendtime", senddf.format(curday.getTime()));
					prop.store(fos, "system");
					fos.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {}
			} else {
				ifsend = false;
			}
		}
		prop.clear();
		return ifsend;
	}
	
	private boolean getTmpSmsifsend(String filename) {		
		boolean ifsend = false;
		String smstype = "";
		Integer frequency = 0;		
		File fp = new File(filename);
		Properties prop=new Properties();
		Calendar day=Calendar.getInstance();
		SimpleDateFormat senddf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sendtime = null;
		try {
			sendtime = senddf.parse("2010-01-01 00:00:00");
		} catch (ParseException e2) {
		}
		if(fp.exists()){
			try {			
				prop.load(new FileInputStream(fp));
				smstype = StringUtil.Null2Blank(prop.getProperty("smstype"));
				frequency = Integer.parseInt(prop.getProperty("frequency"));
				sendtime = senddf.parse(prop.getProperty("sendtime"));
			} catch (FileNotFoundException e1) {
			} catch (Exception e1) {}

			if (smstype.equalsIgnoreCase("1")) {
				ifsend = true;
			} else if (smstype.equalsIgnoreCase("0")) {
				String ambegin = StringUtil.Null2Blank(prop.getProperty("ambegin"));	
				String amend = StringUtil.Null2Blank(prop.getProperty("amend"));	
				String pmbegin = StringUtil.Null2Blank(prop.getProperty("pmbegin"));	
				String pmend = StringUtil.Null2Blank(prop.getProperty("pmend"));	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd ");
				ambegin = sdf2.format(day.getTime())+ambegin;
				amend = sdf2.format(day.getTime())+amend;
				pmbegin = sdf2.format(day.getTime())+pmbegin;
				pmend = sdf2.format(day.getTime())+pmend;
				try {
					if ((day.getTime().after(sdf.parse(ambegin)) && 
						day.getTime().before(sdf.parse(amend))) || 
						(day.getTime().after(sdf.parse(pmbegin)) &&
						day.getTime().before(sdf.parse(pmend)))) {
						ifsend = true;
					}
				} catch (ParseException e) {}
			}	
		}	
		
		if (ifsend) {
			Calendar curday=Calendar.getInstance();
			day.add(Calendar.SECOND, -frequency);
			if (sendtime.before(day.getTime())) {
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(fp);
					prop.setProperty("sendtime", senddf.format(curday.getTime()));
					prop.store(fos, "system");
					fos.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {}
			} else {
				ifsend = false;
			}
		}
		prop.clear();
		return ifsend;
	}
	
	private int getLqmanualSendtype(LiqingmanualphbView hv, String filename) {
		int st = 0;
		String sendtype = "";
/*		if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
			sendtype = StringUtil.Null2Blank(hv.getSendtype());	
		} else {*/
			File fp = new File(filename);
			if(fp.exists()){
				Properties prop=new Properties();
				try {
					prop.load(new FileInputStream(fp));
					sendtype = StringUtil.Null2Blank(prop.getProperty("sendtype"));	
					prop.clear();
				} catch (FileNotFoundException e1) {
				} catch (IOException e1) {
				}
			}			
		//}
		if (sendtype.length() > 0) {
			st = Integer.valueOf(sendtype);
		} 
		return st;
	}
	
	private int getSwmanualSendtype(ShuiwenmanualphbView hv, String filename) {
		int st = 0;
		String sendtype = "";
/*		if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
			sendtype = StringUtil.Null2Blank(hv.getSendtype());	
		} else {*/
			File fp = new File(filename);
			if(fp.exists()){
				Properties prop=new Properties();
				try {
					prop.load(new FileInputStream(fp));
					sendtype = StringUtil.Null2Blank(prop.getProperty("sendtype"));	
					prop.clear();
				} catch (FileNotFoundException e1) {
				} catch (IOException e1) {
				}
			}			
		//}
		if (sendtype.length() > 0) {
			st = Integer.valueOf(sendtype);
		} 
		return st;
	}

	

	private int getSendtype(Hntview hv, String filename) {
		int st = 0;
		String sendtype = "";
/*		if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
			sendtype = StringUtil.Null2Blank(hv.getSendtype());	
		} else {*/
			File fp = new File(filename);
			if(fp.exists()){
				Properties prop=new Properties();
				try {
					prop.load(new FileInputStream(fp));
					sendtype = StringUtil.Null2Blank(prop.getProperty("sendtype"));	
					prop.clear();
				} catch (FileNotFoundException e1) {
				} catch (IOException e1) {
				}
			}			
		//}
		if (sendtype.length() > 0) {
			st = Integer.valueOf(sendtype);
		} 
		return st;
	}
	
	private boolean getLqmanualSmsifsend(LiqingmanualphbView hv, String filename) {		
		boolean ifsend = false;
		String smstype = "";
		Integer frequency = 0;		
		File fp = new File(filename);
		Properties prop=new Properties();
		Calendar day=Calendar.getInstance();
		SimpleDateFormat senddf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sendtime = null;
		try {
			sendtime = senddf.parse("2010-01-01 00:00:00");
		} catch (ParseException e2) {
		}
		if(fp.exists()){
			try {			
				prop.load(new FileInputStream(fp));
				smstype = StringUtil.Null2Blank(prop.getProperty("smstype"));
				frequency = Integer.parseInt(prop.getProperty("frequency"));
				sendtime = senddf.parse(prop.getProperty("sendtime"));
			} catch (FileNotFoundException e1) {
			} catch (Exception e1) {}
		}
		if(fp.exists()){
			if (smstype.equalsIgnoreCase("1")) {
				ifsend = true;
			} else if (smstype.equalsIgnoreCase("0")) {
				String ambegin = StringUtil.Null2Blank(prop.getProperty("ambegin"));	
				String amend = StringUtil.Null2Blank(prop.getProperty("amend"));	
				String pmbegin = StringUtil.Null2Blank(prop.getProperty("pmbegin"));	
				String pmend = StringUtil.Null2Blank(prop.getProperty("pmend"));	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd ");
				ambegin = sdf2.format(day.getTime())+ambegin;
				amend = sdf2.format(day.getTime())+amend;
				pmbegin = sdf2.format(day.getTime())+pmbegin;
				pmend = sdf2.format(day.getTime())+pmend;
				try {
					if ((day.getTime().after(sdf.parse(ambegin)) && 
							day.getTime().before(sdf.parse(amend))) || 
							(day.getTime().after(sdf.parse(pmbegin)) &&
							day.getTime().before(sdf.parse(pmend)))) {
						ifsend = true;
					}
				} catch (ParseException e) {}
			}	
		}	
		if (ifsend) {
			Calendar curday=Calendar.getInstance();
			day.add(Calendar.SECOND, -frequency);
			if (sendtime.before(day.getTime())) {
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(fp);
					prop.setProperty("sendtime", senddf.format(curday.getTime()));
					prop.store(fos, "system");
					fos.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {}
			} else {
				ifsend = false;
			}
		}
		prop.clear();
		return ifsend;
	}
	
	
	private boolean getSwmanualSmsifsend(ShuiwenmanualphbView hv, String filename) {		
		boolean ifsend = false;
		String smstype = "";
		Integer frequency = 0;		
		File fp = new File(filename);
		Properties prop=new Properties();
		Calendar day=Calendar.getInstance();
		SimpleDateFormat senddf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sendtime = null;
		try {
			sendtime = senddf.parse("2010-01-01 00:00:00");
		} catch (ParseException e2) {
		}
		if(fp.exists()){
			try {			
				prop.load(new FileInputStream(fp));
				smstype = StringUtil.Null2Blank(prop.getProperty("smstype"));
				frequency = Integer.parseInt(prop.getProperty("frequency"));
				sendtime = senddf.parse(prop.getProperty("sendtime"));
			} catch (FileNotFoundException e1) {
			} catch (Exception e1) {}
		}
		if(fp.exists()){
			if (smstype.equalsIgnoreCase("1")) {
				ifsend = true;
			} else if (smstype.equalsIgnoreCase("0")) {
				String ambegin = StringUtil.Null2Blank(prop.getProperty("ambegin"));	
				String amend = StringUtil.Null2Blank(prop.getProperty("amend"));	
				String pmbegin = StringUtil.Null2Blank(prop.getProperty("pmbegin"));	
				String pmend = StringUtil.Null2Blank(prop.getProperty("pmend"));	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd ");
				ambegin = sdf2.format(day.getTime())+ambegin;
				amend = sdf2.format(day.getTime())+amend;
				pmbegin = sdf2.format(day.getTime())+pmbegin;
				pmend = sdf2.format(day.getTime())+pmend;
				try {
					if ((day.getTime().after(sdf.parse(ambegin)) && 
							day.getTime().before(sdf.parse(amend))) || 
							(day.getTime().after(sdf.parse(pmbegin)) &&
							day.getTime().before(sdf.parse(pmend)))) {
						ifsend = true;
					}
				} catch (ParseException e) {}
			}	
		}	
		if (ifsend) {
			Calendar curday=Calendar.getInstance();
			day.add(Calendar.SECOND, -frequency);
			if (sendtime.before(day.getTime())) {
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(fp);
					prop.setProperty("sendtime", senddf.format(curday.getTime()));
					prop.store(fos, "system");
					fos.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e){}
			} else {
				ifsend = false;
			}
		}
		prop.clear();
		return ifsend;
	}
	
	private int getLqSendtype(LiqingphbView hv, String filename) {
		int st = 0;
		String sendtype = "";
/*		if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
			sendtype = StringUtil.Null2Blank(hv.getSendtype());	
		} else {*/
			File fp = new File(filename);
			if(fp.exists()){
				Properties prop=new Properties();
				try {
					prop.load(new FileInputStream(fp));
					sendtype = StringUtil.Null2Blank(prop.getProperty("sendtype"));	
					prop.clear();
				} catch (FileNotFoundException e1) {
				} catch (IOException e1) {
				}
			}			
		//}
		if (sendtype.length() > 0) {
			st = Integer.valueOf(sendtype);
		} 
		return st;
	}
	
	
	private int getSwSendtype(ShuiwenmanualphbView hv, String filename) {
		int st = 0;
		String sendtype = "";
/*		if (null != hv && StringUtil.Null2Blank(hv.getSmssettype()).equalsIgnoreCase("1")) {
			sendtype = StringUtil.Null2Blank(hv.getSendtype());	
		} else {*/
			File fp = new File(filename);
			if(fp.exists()){
				Properties prop=new Properties();
				try {
					prop.load(new FileInputStream(fp));
					sendtype = StringUtil.Null2Blank(prop.getProperty("sendtype"));	
					prop.clear();
				} catch (FileNotFoundException e1) {
				} catch (IOException e1) {
				}
			}			
		//}
		if (sendtype.length() > 0) {
			st = Integer.valueOf(sendtype);
		} 
		return st;
	}
	
	
	//短信余额提醒
	private void alarmsms() {
		List<Smsrecord> recordlist = smsService.getalarmrecordList();
		for (Iterator iterator = recordlist.iterator(); iterator.hasNext();) {
			Smsrecord smsrecord = (Smsrecord) iterator.next();
			if (null != smsrecord.getBiaoduanid() &&
					smsrecord.getBiaoduanid() > 0 &&
					StringUtil.Null2Blank(smsrecord.getAlarmnumber()).length() > 0) {
				int alarmcount = 2;
				if (null != smsrecord.getAlarmcount()) {
					alarmcount = smsrecord.getAlarmcount();
				}
				if (smsrecord.getSmscount() <= alarmcount) {
					Banhezhanxinxi bhz = sysService.getBhzbybiaoduanid(smsrecord.getBiaoduanid());
					if (null != bhz) {
						smsrecord.setCompletealarm(1);
						smsService.saveOrUpdateSmsrecord(smsrecord);
						sysService.saveandSendSms(bhz.getGprsbianhao(), 
								smsrecord.getAlarmnumber(), "您当前短信余额为"+smsrecord.getSmscount()+"请及时充值!", null);
					}
				}
			}
		}
	}
	
	//获取大屏显示数据
	@Action("getleddata")
	public String getleddata() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
	    String shtoone = request.getParameter("shtoone");
	    if (StringUtil.Null2Blank(shtoone).equalsIgnoreCase("shtoone")) {
	    	String sbbh = request.getParameter("sbbh");
			String gcmc = request.getParameter("gcmc");
			String sgbw = request.getParameter("sgbw");
			String sgdd = request.getParameter("sgdd");
			String biaoduanid = request.getParameter("biaoduanid");
			String xiangmubuid = request.getParameter("xiangmubuid");
			String zuoyeduiid = request.getParameter("zuoyeduiid");
			response.setContentType("text/xml;charset=utf-8");  
	        response.setHeader("Cache-Control", "no-cache");  
	        PrintWriter out;
			try {
				out = response.getWriter();			
				out.print(dataService.getLedData(sbbh, gcmc, sgbw, sgdd, biaoduanid, xiangmubuid, zuoyeduiid));
		        out.flush();
		        out.close();
			} catch (IOException e) {
			}  
		}
        return null; 
	}	
	


	public Map<Integer, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<Integer, String> listmap) {
		this.listmap = listmap;
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

	public List<TopLiqingView> getToplqs() {
		return Toplqs;
	}

	public void setToplqs(List<TopLiqingView> toplqs) {
		Toplqs = toplqs;
	}

	public GenericPageMode getLiqingpgs() {
		return liqingpgs;
	}

	public void setLiqingpgs(GenericPageMode liqingpgs) {
		this.liqingpgs = liqingpgs;
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

	public LiqingView getLqdata() {
		return lqdata;
	}

	public void setLqdata(LiqingView lqdata) {
		this.lqdata = lqdata;
	}
	public ShuiwenziduancfgView getShuiwenisShow() {
		return shuiwenisShow;
	}

	public void setShuiwenisShow(ShuiwenziduancfgView shuiwenisShow) {
		this.shuiwenisShow = shuiwenisShow;
	}

	public ShuiwenziduancfgView getShuiwenField() {
		return shuiwenField;
	}

	public void setShuiwenField(ShuiwenziduancfgView shuiwenField) {
		this.shuiwenField = shuiwenField;
	}

		//出料口自动刷新数据
		@Action("clkrefreshdata")
		public String clkrefreshdata() {
			ActionContext context = ActionContext.getContext(); 
			HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
			+"classes"+File.separator+"sms.ini";			
			File fp = new File(filename);			
			if(!fp.exists()){
				try {
					fp.createNewFile();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			List qlist = dataService.findChuliaokouTemperatureTop();
			if (qlist.size()>0) {			
				if(fp.exists()){
					try {
						Properties prop=new Properties();
						prop.load(new FileInputStream(fp));	
						
						String maxbh = prop.getProperty("clktmpbianhao", "1");					
						TopRealChuliaokouTemperaturedataView hv = (TopRealChuliaokouTemperaturedataView)qlist.get(0);					
						if (null != hv && hv.getTmpid() > Integer.parseInt(maxbh)) {
							FileOutputStream fos = new FileOutputStream(fp);
							prop.setProperty("clktmpbianhao", String.valueOf(hv.getTmpid()));
							prop.store(fos, "sms");
							fos.close();
						}	
					} catch (FileNotFoundException e) {
						logger.error(e.getMessage());
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
				}
			}
			return null;
		}

	//出料口
		@Action("clksms")
		public String clksms() {
			ActionContext context = ActionContext.getContext(); 
			HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
			+"classes"+File.separator+"sms.ini";
			String systemfilename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
			+"classes"+File.separator+"system.ini";
			File fp = new File(filename);			
			if(!fp.exists()){
				try {
					fp.createNewFile();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if(fp.exists()){
				try {
					Properties prop=new Properties();
					prop.load(new FileInputStream(fp));					
					String maxtmpbh = prop.getProperty("clktmpbianhao", "1");			
					String curtmpstrbh = prop.getProperty("clktmpcurbianhao", "0");
					String panduantiaoshu = prop.getProperty("panduantiaoshu", "3");
					Integer curtmpbh = Integer.parseInt(curtmpstrbh);
					if (curtmpbh == 0) {
						List qlist = dataService.findChuliaokouTemperatureTop();
						if (qlist.size()>0) {
							TopRealChuliaokouTemperaturedataView tophv = (TopRealChuliaokouTemperaturedataView)qlist.get(0);
						  curtmpbh = tophv.getTmpid();
						  FileOutputStream fos = new FileOutputStream(fp);
						  prop.setProperty("clktmpcurbianhao", String.valueOf(curtmpbh));
						  prop.store(fos, "sms");
						  fos.close();
						}
					}
										
					if (curtmpbh > 0 && curtmpbh <= Integer.parseInt(maxtmpbh)) {
						FileOutputStream fos = new FileOutputStream(fp);
						if (curtmpbh<Integer.parseInt(maxtmpbh)-Integer.parseInt(panduantiaoshu)) {
							curtmpbh=Integer.parseInt(maxtmpbh)-Integer.parseInt(panduantiaoshu);
						}
						prop.setProperty("clktmpcurbianhao", String.valueOf(curtmpbh+1));
						prop.store(fos, "sms");
						fos.close();
						ChuliaokouTemperaturedataView tmpdata = dataService.getChuliaokouTmpdataView(curtmpbh); 
						if (null != tmpdata) {
							//Xcsmscfg xcsms = sysService.getXcsmscfg(tmpdata.getGprsbianhao());
							Clksmscfg xcsms=sysService.getClksmscfg(tmpdata.getTmpno());
							if (null != xcsms) {
								doclksms(tmpdata, xcsms, systemfilename);
							}
						}
					}
				} catch (FileNotFoundException e) {
					logger.error(e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		return null;
		}



	//出口料温度报警
		private boolean doclksms(ChuliaokouTemperaturedataView tmpdata, Clksmscfg xcsms, String filename) {
			boolean isnotdo = true;
			double shiji = -1;
			try {
				shiji = Double.valueOf(tmpdata.getTmpdata());
			} catch (Exception e) {
			}
			double lowbz = -1;
			double highbz = -1;
			double lowbz1 = -1;
			double highbz1 = -1;
			double lowbz2 = -1;
			double highbz2 = -1;
			double finalbz = -1;
			boolean denji1 = false;
			boolean denji2 = false;
			boolean denji3 = false;
			try {
				lowbz = Double.valueOf(xcsms.getTmplow());
			} catch (Exception e) {
			}
			try {
				highbz = Double.valueOf(xcsms.getTmphigh());
			} catch (Exception e) {
			}
			try {
				lowbz1 = Double.valueOf(xcsms.getTmplow1());
			} catch (Exception e) {
			}
			try {
				highbz1 = Double.valueOf(xcsms.getTmphigh1());
			} catch (Exception e) {
			}		try {
				lowbz2 = Double.valueOf(xcsms.getTmplow2());
			} catch (Exception e) {
			}
			try {
				highbz2 = Double.valueOf(xcsms.getTmphigh2());
			} catch (Exception e) {
			}
			String chaobiaoStr="";//超标值
			String shezhizhi="";//上下限
			if (shiji>0) {
				if (lowbz>0) {
					if (shiji<lowbz) {
						finalbz = lowbz;
						denji1 = true;
						chaobiaoStr="超下限"+(lowbz-shiji);
						shezhizhi="下限"+lowbz;
					}
				}
				if (highbz>0) {
					if (shiji>highbz) {
						finalbz = highbz;
						denji1 = true;
						chaobiaoStr="超上限"+(shiji-highbz);
						shezhizhi="上限"+highbz;
					}
				}
				
				if (lowbz1>0) {
					if (shiji<lowbz1) {
						finalbz = lowbz1;
						denji2 = true;
						denji1 = true;
						chaobiaoStr="超下限"+(lowbz1-shiji);
						shezhizhi="下限"+lowbz1;
					}
				}
				if (highbz1>0) {
					if (shiji>highbz1) {
						finalbz = highbz1;
						denji2 = true;
						denji1 = true;
						chaobiaoStr="超上限"+(shiji-highbz1);
						shezhizhi="上限"+highbz1;
					}
				}
				
				if (lowbz2>0) {
					if (shiji<lowbz2) {
						finalbz = lowbz2;
						denji3 = true;
						denji2 = true;
						denji1 = true;
						chaobiaoStr="超下限"+(lowbz2-shiji);
						shezhizhi="下限"+lowbz2;
					}
				}
				if (highbz2>0) {
					if (shiji>highbz2) {
						finalbz = highbz2;
						denji3 = true;
						denji2 = true;
						denji1 = true;
						chaobiaoStr="超上限"+(shiji-highbz2);
						shezhizhi="上限"+highbz2;
					}
				}
			}
			if (finalbz > 0) {	
				saveClktmpjieguo(tmpdata, denji2, denji3, shiji<finalbz);
				logger.info(tmpdata.getTmpno()+"温度预警"+shiji);
				Calendar day=Calendar.getInstance();
		    	day.add(Calendar.MINUTE, -30);
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	Date baocun = day.getTime();
		    	try {
					baocun = sdf.parse(tmpdata.getTmpshijian());
					} catch (ParseException ex) {
						
				}
					if (StringUtil.Null2Blank(xcsms.getSmsbaojin()).equalsIgnoreCase("1") 
			    			&& baocun.after(day.getTime()) 
			    			&& getTmpSmsifsend(filename)) {
						
						String bhzminchen=tmpdata.getBanhezhanminchen().replaceAll("垄茶","");
						
						String smsmeipan = "[%s]%s:出料口实际%s℃,%s℃,%s℃";
						String sms = String.format(smsmeipan, 
								tmpdata.getTmpshijian(), bhzminchen, shiji,shezhizhi,chaobiaoStr);
						
			    			String realphonenumber = "";
			    			String realphonenumber2 = "";
			    			String realphonenumber3 = "";
			    			
			    			if (denji1) {
			    				realphonenumber = sysService.getRealPhoneNumber(xcsms.getNumberlow());		    				
							}
			    			
			    			if (denji2) {
								realphonenumber2 = sysService.getRealPhoneNumber(xcsms.getNumbermid());							
							}
			    			
			    			if (denji3) {
								realphonenumber3 = sysService.getRealPhoneNumber(xcsms.getNumberhigh());							
							}		    			
			    			if (denji1 && StringUtil.Null2Blank(realphonenumber).length()>0) {
			    				sysService.saveandSendSms(tmpdata.getTmpno(), realphonenumber, sms, null);
			    			}
				    	    if (denji2 && StringUtil.Null2Blank(realphonenumber2).length()>0) {
				    	    	sysService.saveandSendSms(tmpdata.getTmpno(), realphonenumber2, sms, null);
				    	    }
				    	    if (denji3 && StringUtil.Null2Blank(realphonenumber3).length()>0) {
				    	    	sysService.saveandSendSms(tmpdata.getTmpno(), realphonenumber3, sms, null);
				    	    }
					}
			}
			return isnotdo;
		}


		
		//保存出料口温度结果
		private void saveClktmpjieguo(ChuliaokouTemperaturedataView tmpdata, boolean denji2, boolean denji3,boolean islow) {
			ChuliaokouTemperaturejieguo tmpjg = new ChuliaokouTemperaturejieguo();
			tmpjg.setTmpno(tmpdata.getTmpno());
			String zhuntai = "0";
			if (islow) {
				if (denji3) {
					zhuntai = "3";
				} else if (denji2) {
					zhuntai = "2";
				} else {
					zhuntai = "1";
				}
			} else {
				if (denji3) {
					zhuntai = "6";
				} else if (denji2) {
					zhuntai = "5";
				} else {
					zhuntai = "4";
				}
			}
			tmpjg.setTmpdata(zhuntai);
			
	    	Calendar day=Calendar.getInstance();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tmpjg.setJieguoshijian(sdf.format(day.getTime()));
			sysService.saveClkTmpjieguo(tmpjg);	
		}
		
	//水稳
	private String doSwsms1(ShuiwenmanualphbView hv, String filename, Integer bianhao, String shebeibianhao, String smsbaojin, String issms, String biaozhun,String biaozhunhigh, String jiaobanphonenumber, String phonenumber, 
				String biaozhun2, String biaozhunhigh2, String jiaobanphonenumber2, String phonenumber2, 
				String biaozhun3, String biaozhunhigh3, String jiaobanphonenumber3, String phonenumber3, String shijizhi, String wucha, 
				String lilunzhi, String sheji, String smscontent, String fieldname, 
				String baocunshijian, String Chuliaoshijian, String isshowchuliao, String bhzminchen, int ziduantype, int sendcount, int onefrequency, String danwei,String fenlei,String biaozhun4, String biaozhunhigh4) {
		String sms = "";
		if (StringUtil.Null2Blank(shijizhi).length()>0 
				    && StringUtil.Null2Blank(wucha).length()>0){
		//&& (StringUtil.Null2Blank(biaozhun2).length()>0 || StringUtil.Null2Blank(biaozhun4).length()>0)
			try {
				if (shijizhi.length()>8) {
					shijizhi = String.format("%1$.2f",Double.valueOf(shijizhi));
				}
				if (wucha.length()>8) {
					wucha = String.format("%1$.2f",Double.valueOf(wucha));
				}
				if (StringUtil.Null2Blank(lilunzhi).length()>0) {
					lilunzhi = String.format("%1$.2f",Double.valueOf(lilunzhi));
				}
				double shiji = Double.valueOf(shijizhi);
				double sjwucha = 0;
				double bz = -1;
				double bzhigh =0;
				double bz2 = -1;
				double bzhigh2 = -1;
				//中级报警添加的多个范围
				double bz4 = 0;
				double bzhigh4 =0;
				double bz3 = -1;
				double bzhigh3 =0;
				double finalbz = -1;
				boolean denji1 = false;
				boolean denji2 = false;
				boolean denji3 = false;	
						
				if (StringUtil.Null2Blank(biaozhun).length()>0) {
					try {
						bz = Math.abs(Double.valueOf(biaozhun));
					} catch (Exception e) {}
				}
				
				if (StringUtil.Null2Blank(biaozhunhigh).length()>0) {
					try {
						bzhigh = Math.abs(Double.valueOf(biaozhunhigh));
					} catch (Exception e) {}
				}
						
				//这里要考虑到负值的情况
				
				if (StringUtil.Null2Blank(biaozhun2).length()>0) {
					try {
						bz2 = Math.abs(Double.valueOf(biaozhun2));
					} catch (Exception e) {}
				}
				
				if (StringUtil.Null2Blank(biaozhunhigh2).length()>0) {
					try {
						bzhigh2 = Math.abs(Double.valueOf(biaozhunhigh2));
					} catch (Exception e) {}
				}
				if (StringUtil.Null2Blank(biaozhun4).length()>0) {
					try {
						bz4 = Double.valueOf(biaozhun4);
					} catch (Exception e) {}
				}
				
				if (StringUtil.Null2Blank(biaozhunhigh4).length()>0) {
					try {
						bzhigh4 = Double.valueOf(biaozhunhigh4);
					} catch (Exception e) {}
				}
						
				
				
				if (StringUtil.Null2Blank(biaozhun3).length()>0 ) {
					try {
						bz3 = Math.abs(Double.valueOf(biaozhun3));
					} catch (Exception e) {}
				}
				
				if (StringUtil.Null2Blank(biaozhunhigh3).length()>0 ) {
					try {
						bzhigh3 = Math.abs(Double.valueOf(biaozhunhigh3));
					} catch (Exception e) {}
				}
						
				double lilun = -1;
				boolean issheji = false;
				if (StringUtil.Null2Blank(lilunzhi).length()>0) {
					try {
						lilun = Double.valueOf(lilunzhi);
						sjwucha = Double.valueOf(wucha);
					} catch (Exception e) {}
				} else if (StringUtil.Null2Blank(sheji).length()>0) {
					try {
						lilun = Double.valueOf(sheji);
						sjwucha = shiji - lilun;
						issheji = true;
					} catch (Exception e) {}
				}
				boolean slok = true;
				
				if (lilun > 0 && shiji > 0 && Math.abs(sjwucha) > 0 && slok && (ziduantype!=0 || sjwucha<0)) {
					String text = "";
			        if (StringUtil.Null2Blank(fenlei).equalsIgnoreCase("gl")) {
			            if ((bz2 != -1) && (bzhigh2 != -1) && (bz3 != -1) && (sjwucha!=0)) {
			            	//这里要考虑到负数情况，以前的的报警有问题
			            	if(sjwucha>0){
				            	if ((sjwucha > Math.abs(bz2)) && (sjwucha < Math.abs(bzhigh2))) {
				            		finalbz = bz2;
				            		text="中超";
				            		denji1 = true;
				            		denji2 = true;
				            	}
				            	if (sjwucha > Math.abs(bz3)) {
				            		finalbz = bz3;
				            		text="高超";
				            		denji1 = true;
				            		denji2 = true;
				            		denji3 = true;
				            	}
			            	}else if(sjwucha<0){
			            		//假如误差是负值，配置的报警数为正值
			            		double sjwucha0 = Math.abs(sjwucha);
			            		if ((sjwucha0 > Math.abs(bzhigh4)) && (sjwucha0 < Math.abs(bz4))) {
				            		finalbz = bz2;
				            		text="中超";
				            		denji1 = true;
				            		denji2 = true;
				            	}
			            		//高级报警：如果误差是负值，就和bzhigh3做比较，配置时需要配置bzhigh3
			            		if(bzhigh3 != 0){
			            			if (Math.abs(sjwucha) > Math.abs(bzhigh3)) {
					            		finalbz = bzhigh3;
					            		text="高超";
					            		denji1 = true;
					            		denji2 = true;
					            		denji3 = true;
					            	}
			            		}
			            		
			            		
			            	}
			            	
			            }
			        
			        }else if (StringUtil.Null2Blank(fenlei).equalsIgnoreCase("fl")){			  
			            //粉料计算方式和骨料不一样	
			        	
			        	
			        	
			        	//这里要考虑到负数情况，以前的的报警有问题
			            	if(sjwucha>0){
			            		if(Math.abs(sjwucha) > Math.abs(bz)){
				            		finalbz = bz;
					            	text="初超";
					            	denji1 = true;
				            	}
				            	if ((sjwucha > Math.abs(bz2)) && (sjwucha < Math.abs(bzhigh2))) {
				            		finalbz = bz2;
				            		text="中超";
				            		denji1 = true;
				            		denji2 = true;
				            	}
				            	if (sjwucha > Math.abs(bz3)) {
				            		finalbz = bz3;
				            		text="高超";
				            		denji1 = true;
				            		denji2 = true;
				            		denji3 = true;
				            	}
				            	
			            	}else if(sjwucha<0){
			            		if(bzhigh !=0){
			            			if(Math.abs(sjwucha) > Math.abs(bzhigh)){
					            		finalbz = bzhigh;
						            	text="初超";
						            	denji1 = true;
					            	}
			            		}
			            		//假如误差是负值，配置的报警数为正值
			            		double sjwucha0 = Math.abs(sjwucha);
			            		if ((sjwucha0 > Math.abs(bzhigh4)) && (sjwucha0 < Math.abs(bz4))) {
				            		finalbz = bz2;
				            		text="中超";
				            		denji1 = true;
				            		denji2 = true;
				            	}
			            		//高级报警：如果误差是负值，就和bzhigh3做比较，配置时需要配置bzhigh3
			            		if(bzhigh3 != 0){
			            			if (Math.abs(sjwucha) > Math.abs(bzhigh3)) {
					            		finalbz = bzhigh3;
					            		text="高超";
					            		denji1 = true;
					            		denji2 = true;
					            		denji3 = true;
					            	}
			            		}
			            		
			            		
			            	}
			        }else if (StringUtil.Null2Blank(fenlei).equalsIgnoreCase("sf")){			  
			      
			        	//这里要考虑到负数情况，以前的的报警有问题
			            	if(sjwucha>0){
			            		if(Math.abs(sjwucha) > Math.abs(bz)){
				            		finalbz = bz;
					            	text="初超";
					            	denji1 = true;
				            	}
				            	if ((sjwucha > Math.abs(bz2)) && (sjwucha < Math.abs(bzhigh2))) {
				            		finalbz = bz2;
				            		text="中超";
				            		denji1 = true;
				            		denji2 = true;
				            	}
				            	if (sjwucha > Math.abs(bz3)) {
				            		finalbz = bz3;
				            		text="高超";
				            		denji1 = true;
				            		denji2 = true;
				            		denji3 = true;
				            	}
				            	
			            	}else if(sjwucha<0){
			            		if(bzhigh !=0){
			            			if(Math.abs(sjwucha) > Math.abs(bzhigh)){
					            		finalbz = bzhigh;
						            	text="初超";
						            	denji1 = true;
					            	}
			            		}
			            		//假如误差是负值，配置的报警数为正值
			            		double sjwucha0 = Math.abs(sjwucha);
			            		if ((sjwucha0 > Math.abs(bzhigh4)) && (sjwucha0 < Math.abs(bz4))) {
				            		finalbz = bz2;
				            		text="中超";
				            		denji1 = true;
				            		denji2 = true;
				            	}
			            		//高级报警：如果误差是负值，就和bzhigh3做比较，配置时需要配置bzhigh3
			            		if(bzhigh3 != 0){
			            			if (Math.abs(sjwucha) > Math.abs(bzhigh3)) {
					            		finalbz = bzhigh3;
					            		text="高超";
					            		denji1 = true;
					            		denji2 = true;
					            		denji3 = true;
					            	}
			            		}
			            		
			            		
			            	}
			        }
						    
					if (finalbz > 0) {	
						int biaozhuntype=0;  //默认超下限
						if (sjwucha > 0) {
							biaozhuntype = 1;
						}
						setSwSmsTypeDb(filename, bianhao, shebeibianhao, denji2, denji3, biaozhuntype, ziduantype);
						if(StringUtil.Null2Blank(issms).equalsIgnoreCase("1") && StringUtil.Null2Blank(smsbaojin).equalsIgnoreCase("1")){
							if(StringUtil.Null2Blank(fenlei).equalsIgnoreCase("sf")){
								double Sfl4and5 = 0.0;
								if(hv.getSjgl4and5()!=""){
									Sfl4and5 = Double.parseDouble(hv.getSjgl4and5());
								}
								sms="石粉实"+String.format("%1$.2f",Sfl4and5)+"%理"+String.format("%1$.2f",lilun)+"%误"+String.format("%1$.2f", sjwucha)+"%"+"("+text+")";					
								
							}else{
								sms=fieldname+"实"+String.format("%1$.2f",shiji)+"%理"+String.format("%1$.2f",lilun)+"%误"+String.format("%1$.2f", sjwucha)+"%"+"("+text+")";					
							}
						}
						
						
								    		
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} 
		return sms;
	}

	private void dobaojing(ShuiwenmanualphbView hv,String filename, String sms, String phonenumber,
				String phonenumber2, String phonenumber3,
				String baocunshijian, String chuliaoshijian, String chuliaoshijian2) {
		Calendar day=Calendar.getInstance();
	   	day.add(Calendar.MINUTE, -35);
	   	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   	Date baocun = day.getTime();					    	
	   	boolean denji1 = false;
		boolean denji2 = false;
		boolean denji3 = false;
	    try {
			baocun = sdf.parse(chuliaoshijian);
		} catch (ParseException ex) {}
	    if (baocun.after(day.getTime())) {
	    	if (StringUtil.Null2Blank(hv.getSmsbaojin()).equalsIgnoreCase("1")) {
	    		String apitype = "97";
	    		File fp = new File(filename);
				if(fp.exists()){
					Properties prop=new Properties();
					try {
						prop.load(new FileInputStream(fp));												
						apitype = StringUtil.Null2Blank(prop.getProperty("apitype", "97"));
					} catch (FileNotFoundException e) {
						logger.error(e.getMessage());
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
					prop.clear();
				}
				String schuliao = baocunshijian;
				if (StringUtil.Null2Blank(chuliaoshijian).length()>0 && 
							StringUtil.Null2Blank(chuliaoshijian).length()<22) {
					schuliao=chuliaoshijian;
				}
				sms="编号:"+"["+String.valueOf(hv.getBianhao())+"]"+"出料时间:"+"["+schuliao+"]"+hv.getBanhezhanminchen()+sms;
					logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"短信内容"+sms);
						
	    		if (sms.length() > 0) {					    			
	    			String realphonenumber = "";
	    			String realphonenumber2 = "";
	    			String realphonenumber3 = "";
	    			String realphonename = "";
	    			String realphonename2 = "";
	    			String realphonename3 = "";
	    			HandSet  hs= null;
	    			if(sms.indexOf("高")>-1){
	    				denji1=true;
	    				denji2=true;
	    				denji3=true;
	    			}else if(sms.indexOf("中")>-1){
	    				denji1=true;
	    				denji2=true;
	    			}else if(sms.indexOf("初")>-1){
	    				denji1=true;
	    			}
			    	hs = sysService.getRealPhoneNumber2(phonenumber);
			    	if (null != hs) {
			    		realphonenumber =hs.getPhone();
			    		realphonename =hs.getOwername();
					}
		    		if (denji1 && StringUtil.Null2Blank(realphonenumber).length()>0) {
		    			logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"初级号码"+realphonenumber);
		    			sysService.saveandSendSms(hv.getBianhao(), hv.getShebeibianhao(), realphonenumber,realphonename,chuliaoshijian, sms, apitype,"sw");
		    		}
	    			
	    			hs = sysService.getRealPhoneNumber2(phonenumber2);
	    			if (null != hs) {
	    				realphonenumber2 =hs.getPhone();
	    				realphonename2 =hs.getOwername();
					}
	    			
	    			if (denji2 && StringUtil.Null2Blank(realphonenumber2).length()>0) {
	    				logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"中级号码"+realphonenumber2);
		    	    	sysService.saveandSendSms(hv.getBianhao(), hv.getShebeibianhao(), realphonenumber2,realphonename2,chuliaoshijian, sms, apitype,"sw");
		    	    }
	    			
	    			hs = sysService.getRealPhoneNumber2(phonenumber3);
	    			if (null != hs) {
	    				realphonenumber3 =hs.getPhone();
	    				realphonename3 =hs.getOwername();
					}
	    			
		    	    if (denji3 &&StringUtil.Null2Blank(realphonenumber3).length()>0) {
		    	    	logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"高级号码"+realphonenumber3);
		    	    	sysService.saveandSendSms(hv.getBianhao(), hv.getShebeibianhao(), realphonenumber3,realphonename3,chuliaoshijian, sms, apitype,"sw");
		    	    }
		    	}					    		
			}
	    }
	}
	
	/**
	 * 判断是否符合开盘报警的条件
	 * @param smsbaojin   
	 * @param issms
	 * @param biaozhun
	 * @param shijizhi
	 * @param lilunzhi
	 * @param fieldname
	 * @return
	 */
	private String swkaipandosms(ShuiwenmanualphbView manualhv,String smsbaojin, String issms, 
		String biaozhun, String shijizhi,
	    String lilunzhi, String fieldname) {
		String sms = "";
		if (StringUtil.Null2Blank(shijizhi).length()>0 
				&& StringUtil.Null2Blank(biaozhun).length()>0) {
			try {
				if (shijizhi.length()>8) {
					shijizhi = String.format("%1$.2f",Double.valueOf(shijizhi));
				}
				if (StringUtil.Null2Blank(lilunzhi).length()>0) {
					lilunzhi = String.format("%1$.2f",Double.valueOf(lilunzhi));
				}
				double bz = -1;
				double finalbz = -1;
						
				if (StringUtil.Null2Blank(biaozhun).length()>0) {
					try {
						bz = Math.abs(Double.valueOf(biaozhun));
					} catch (Exception e) {}
				}
						
				String wucha = "";
				if(StringUtil.StrToZero(lilunzhi)>0){
					wucha=String.format("%1$.2f",(StringUtil.StrToZero(shijizhi)-StringUtil.StrToZero(lilunzhi)));
				}
						
				if (Math.abs(StringUtil.StrToZero(wucha))>0) {
					if (bz > 0 && Math.abs(StringUtil.StrToZero(wucha))-bz>0) {
						finalbz = bz;
					}
						    
					if (finalbz > 0) {	
						if(StringUtil.Null2Blank(issms).equalsIgnoreCase("1") && StringUtil.Null2Blank(smsbaojin).equalsIgnoreCase("1")){
							sms=fieldname+"实"+String.format("%1$.2f",Double.parseDouble(shijizhi))+"%理"+String.format("%1$.2f",Double.parseDouble(lilunzhi))+"%误"+String.format("%1$.2f", Double.parseDouble(wucha))+"%";
						}		    		
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return sms;
	}	
	
	private void dokaipanbaojing(ShuiwenmanualphbView hv,String filename, String sms, String phonenumber,
			String baocunshijian, String chuliaoshijian) {
		Calendar day=Calendar.getInstance();
	   	day.add(Calendar.MINUTE, -30);
	   	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   	Date baocun = day.getTime();					    	
	    try {
			baocun = sdf.parse(chuliaoshijian);
		} catch (ParseException ex) {}
	    if (baocun.after(day.getTime())) {
	    	if (StringUtil.Null2Blank(hv.getSmsbaojin()).equalsIgnoreCase("1")) {
	    		String apitype = null;
	    		File fp = new File(filename);
				if(fp.exists()){
					Properties prop=new Properties();
					try {
						prop.load(new FileInputStream(fp));												
						apitype = StringUtil.Null2Blank(prop.getProperty("apitype", "0"));
					} catch (FileNotFoundException e) {
						logger.error(e.getMessage());
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
					prop.clear();
				}
				String schuliao = baocunshijian;
				if (StringUtil.Null2Blank(chuliaoshijian).length()>0 && 
							StringUtil.Null2Blank(chuliaoshijian).length()<22) {
					schuliao = chuliaoshijian;
				}
				sms="编号:"+"["+String.valueOf(hv.getBianhao())+"]"+"出料时间"+"["+schuliao+"]"+hv.getBanhezhanminchen()+sms;
					logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"短信内容"+sms);
						
	    		if (sms.length() > 0) {					    			
	    			String realphonenumber = "";
	    			String realphonename = "";
	    			HandSet  hs= null;
			    	hs = sysService.getRealPhoneNumber2(phonenumber);
			    	if (null != hs) {
			    		realphonenumber =hs.getPhone();
			    		realphonename =hs.getOwername();
					}
		    		if (StringUtil.Null2Blank(realphonenumber).length()>0) {
		    			logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"开盘报警号码"+realphonenumber);
		    			sysService.saveandSendSms(hv.getBianhao(), hv.getShebeibianhao(), realphonenumber,realphonename,chuliaoshijian, sms, apitype,"sw");
		    		}
	    			
		    	}					    		
			}
	    }
	}
	
	private void doLqbaojing(LiqingmanualphbView hv,String filename, String sms, String phonenumber,
			String phonenumber2, String phonenumber3,
			String baocunshijian, String chuliaoshijian, String chuliaoshijian2) {
		Calendar day=Calendar.getInstance();
	   	day.add(Calendar.MINUTE, -30);
	   	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   	Date baocun = day.getTime();					    	
	   	boolean denji1 = false;
		boolean denji2 = false;
		boolean denji3 = false;
	    try {
			baocun = sdf.parse(chuliaoshijian);
		} catch (ParseException ex) {}
	    if (baocun.after(day.getTime())) {
	    	if (StringUtil.Null2Blank(hv.getSmsbaojin()).equalsIgnoreCase("1")) {
	    		String apitype = null;
	    		File fp = new File(filename);
				if(fp.exists()){
					Properties prop=new Properties();
					try {
						prop.load(new FileInputStream(fp));												
						apitype = StringUtil.Null2Blank(prop.getProperty("apitype", "0"));
					} catch (FileNotFoundException e) {
						logger.error(e.getMessage());
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
					prop.clear();
				}
				String schuliao = baocunshijian;
				if (StringUtil.Null2Blank(chuliaoshijian).length()>0 && 
							StringUtil.Null2Blank(chuliaoshijian).length()<22) {
					schuliao=chuliaoshijian;
				}
				sms="编号:"+"["+String.valueOf(hv.getBianhao())+"]"+"出料时间:"+"["+schuliao+"]"+hv.getBanhezhanminchen()+sms;
					logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"短信内容"+sms);
					
	    		if (sms.length() > 0) {					    			
	    			String realphonenumber = "";
	    			String realphonenumber2 = "";
	    			String realphonenumber3 = "";
	    			String realphonename = "";
	    			String realphonename2 = "";
	    			String realphonename3 = "";
	    			HandSet  hs= null;
	    			if(sms.indexOf("高")>-1){
	    				denji1=true;
	    				denji2=true;
	    				denji3=true;
	    			}else if(sms.indexOf("中")>-1){
	    				denji1=true;
	    				denji2=true;
	    			}else if(sms.indexOf("初")>-1){
	    				denji1=true;
	    			}
			    	hs = sysService.getRealPhoneNumber2(phonenumber);
			    	if (null != hs) {
			    		realphonenumber =hs.getPhone();
			    		realphonename =hs.getOwername();
					}
		    		if (denji1 && StringUtil.Null2Blank(realphonenumber).length()>0) {
		    			logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"初级号码"+realphonenumber);
		    			sysService.saveandSendSms(hv.getBianhao(), hv.getShebeibianhao(), realphonenumber,realphonename,chuliaoshijian, sms, apitype,"lq");
		    		}
    			
	    			hs = sysService.getRealPhoneNumber2(phonenumber2);
	    			if (null != hs) {
	    				realphonenumber2 =hs.getPhone();
	    				realphonename2 =hs.getOwername();
					}
	    			
	    			if (denji2 && StringUtil.Null2Blank(realphonenumber2).length()>0) {
	    				logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"中级号码"+realphonenumber2);
		    	    	sysService.saveandSendSms(hv.getBianhao(), hv.getShebeibianhao(), realphonenumber2,realphonename2,chuliaoshijian, sms, apitype,"lq");
		    	    }
    			
	    			hs = sysService.getRealPhoneNumber2(phonenumber3);
	    			if (null != hs) {
	    				realphonenumber3 =hs.getPhone();
	    				realphonename3 =hs.getOwername();
					}
	    			
		    	    if (denji3 &&StringUtil.Null2Blank(realphonenumber3).length()>0) {
		    	    	logger.info(hv.getBanhezhanminchen()+hv.getShebeibianhao()+"高级号码"+realphonenumber3);
		    	    	sysService.saveandSendSms(hv.getBianhao(), hv.getShebeibianhao(), realphonenumber3,realphonename3,chuliaoshijian, sms, apitype,"lq");
		    	    }
	    		}					    		
	    	}
	    }
	}
	
	
}