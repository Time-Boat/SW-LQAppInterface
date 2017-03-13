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
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.apache.struts2.convention.annotation.Action;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingclDailyView;
import com.mss.shtoone.domain.SpeeddataView;
import com.mss.shtoone.domain.Tanpujihuaxinxi;
import com.mss.shtoone.domain.TemperaturedataView;
import com.mss.shtoone.domain.TopRealSpeeddata1View;
import com.mss.shtoone.domain.TopRealTemperaturedataView;
import com.mss.shtoone.domain.UserInfo;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.service.TanpujihuaxinxiService;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.LqExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")

public class TanpujihuaxinxiAction extends ActionSupport {

	private static final long serialVersionUID = -6870786428960341318L;
	@Autowired
	private TanpujihuaxinxiService tpjhxxService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private GetdataService getdataService;
	
	@Autowired
	private QueryService queryService;
	
	
	private List list = new ArrayList();

	private Map<Integer, String> biaoduanlistmap;//标段集合
	private Map<Integer, String> xmblistmap;
	private Map<Integer, String> bhzlistmap;
	private List tanpujihualist = new ArrayList();//摊铺计划集合
	private Map<String, String> shebeilistmap;	
	private Tanpujihuaxinxi tanpujihuaxinxiObj;//摊铺计划对象
	private GenericPageMode xcQuerytmppgs;
	private GenericPageMode xcQueryspeedpgs;
	private Integer biaoduan;//标段编号
	private String excelName;
	private int jhId;//计划编号
	
	
	private String startTime;
	private String endTime;
	private Integer xiangmubu;
	private GenericPageMode tanpujihuaxinxis;
	private List<LiqingclDailyView> liqingDailyViewList;
	private String shebeibianhao;
	private int maxPageItems;
	private Integer pageNo;	
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
	public String getShebeibianhao() {
		return shebeibianhao;
	}
	public Map<String, String> getShebeilistmap() {
		return shebeilistmap;
	}
	public void setShebeilistmap(Map<String, String> shebeilistmap) {
		this.shebeilistmap = shebeilistmap;
	}
	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}
	public Integer getXiangmubu() {
		return xiangmubu;
	}
	public void setXiangmubu(Integer xiangmubu) {
		this.xiangmubu = xiangmubu;
	}
	public List<LiqingclDailyView> getLiqingDailyViewList() {
		return liqingDailyViewList;
	}
	public void setLiqingDailyViewList(List<LiqingclDailyView> liqingDailyViewList) {
		this.liqingDailyViewList = liqingDailyViewList;
	}
	public GenericPageMode getTanpujihuaxinxis() {
		return tanpujihuaxinxis;
	}
	public void setTanpujihuaxinxis(GenericPageMode tanpujihuaxinxis) {
		this.tanpujihuaxinxis = tanpujihuaxinxis;
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
	public int getMaxPageItems() {
		return maxPageItems;
	}
	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public int getJhId() {
		return jhId;
	}
	public void setJhId(int jhId) {
		this.jhId = jhId;
	}
	public Tanpujihuaxinxi getTanpujihuaxinxiObj() {
		return tanpujihuaxinxiObj;
	}
	public void setTanpujihuaxinxiObj(Tanpujihuaxinxi tanpujihuaxinxiObj) {
		this.tanpujihuaxinxiObj = tanpujihuaxinxiObj;
	}

	public GenericPageMode getXcQuerytmppgs() {
		return xcQuerytmppgs;
	}
	public void setXcQuerytmppgs(GenericPageMode xcQuerytmppgs) {
		this.xcQuerytmppgs = xcQuerytmppgs;
	}
	public GenericPageMode getXcQueryspeedpgs() {
		return xcQueryspeedpgs;
	}
	public void setXcQueryspeedpgs(GenericPageMode xcQueryspeedpgs) {
		this.xcQueryspeedpgs = xcQueryspeedpgs;
	}
	public List getTanpujihualist() {
		return tanpujihualist;
	}
	public void setTanpujihualist(List tanpujihualist) {
		this.tanpujihualist = tanpujihualist;
	}
	public Integer getBiaoduan() {
		return biaoduan;
	}
	public void setBiaoduan(Integer biaoduan) {
		this.biaoduan = biaoduan;
	}
	public Map<Integer, String> getBiaoduanlistmap() {
		return biaoduanlistmap;
	}
	public void setBiaoduanlistmap(Map<Integer, String> biaoduanlistmap) {
		this.biaoduanlistmap = biaoduanlistmap;
	}
	public TanpujihuaxinxiService getTpjhxxService() {
		return tpjhxxService;
	}
	public void setTpjhxxService(TanpujihuaxinxiService tpjhxxService) {
		this.tpjhxxService = tpjhxxService;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Map<Integer, String> getXmblistmap() {
		return xmblistmap;
	}
	public void setXmblistmap(Map<Integer, String> xmblistmap) {
		this.xmblistmap = xmblistmap;
	}
	public Map<Integer, String> getBhzlistmap() {
		return bhzlistmap;
	}
	public void setBhzlistmap(Map<Integer, String> bhzlistmap) {
		this.bhzlistmap = bhzlistmap;
	}
	//获取摊铺计划横道图XML
	public String getTPJHXML(List<Biaoduanxinxi> bdlist,List<Tanpujihuaxinxi> yearList,List<Tanpujihuaxinxi> list,int width,int height){
		String [] topMenu1=new String[yearList.size()];
		String [] topMenu2=new String[2];
		if(yearList.size()>1){
			int startTime=0;
			int endTime=0;
			int j=0;
			for(Tanpujihuaxinxi tpObj:yearList){
				if(j==0){//第一个
					topMenu1[j]=tpObj.getJihuastarttime()+"&"+tpObj.getJihuashengchanliang()+"-12-31&"+tpObj.getJihuashengchanliang();
					startTime=new Integer(tpObj.getJihuashengchanliang());//所有数据的开始年份
					topMenu2[0]=tpObj.getJihuastarttime();//所有数据的开始日期
				}
				
				if(j==yearList.size()-1){//最后一个
					topMenu1[j]=tpObj.getJihuatanpulicheng()+"-01-01&"+tpObj.getJihuaendtime()+"&"+tpObj.getJihuatanpulicheng();
					endTime=new Integer(tpObj.getJihuatanpulicheng());//所有数据的结束年份
					topMenu2[1]=tpObj.getJihuaendtime();//所有数据的结束日期
				}
				
				j++;
			}
			
			if((startTime+=1)!=endTime){
				int k=1;
				topMenu1[k]=startTime+"-01-01&"+startTime+"-12-31&"+startTime;
				k++;
			}
		}
		
		if(yearList.size()==1){
			Tanpujihuaxinxi tpObj=yearList.get(0);
			topMenu1[0]=tpObj.getJihuastarttime()+"&"+tpObj.getJihuaendtime()+"&"+tpObj.getJihuashengchanliang();
			
			topMenu2[0]=tpObj.getJihuastarttime();
			topMenu2[1]=tpObj.getJihuaendtime();
			
		}
		
		//String [] topMenu1={"2013-01-03&2013-04-20&2013"};
		//String [] topMenu2={"2013-01-03","2013-04-20"};
		String [] leftMenu=new String[bdlist.size()+1];
		int i=0;
		leftMenu[i]="标  段";
		for (Biaoduanxinxi bd : bdlist) {
			i++;
			leftMenu[i]=bd.getId()+"&"+bd.getBiaoduanminchen();
		}
		
		/*
		for(int u=0;u<topMenu1.length;u++){
			System.out.println("topMenu"+u+":"+topMenu1[u]);
		}
		System.out.println(topMenu2[0]+"*****"+topMenu2[1]);
		*/
		return getdataService.TanpujihuaXML(topMenu1, topMenu2, leftMenu, list,width,height);
		// request.setAttribute("strXML", getdataService.TanpujihuaXML(topMenu1, topMenu2, leftMenu, list));
	}
	@Action("tanpujihuaxinxilist")
	public String TanpujihuaxinxiList(){
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		List<Tanpujihuaxinxi> list=tpjhxxService.getTanpujihuaxinxiBybianduan(biaoduan);
		setTanpujihualist(list);//查询到的摊铺计划list

		
		
		List<Tanpujihuaxinxi> yearList=tpjhxxService.getTanpujihuaYearBybianduan(biaoduan,"","");
		
		request.setAttribute("strXML", getTPJHXML(bdlist,yearList,list,1024,500));
		 
		return SUCCESS;
	}
	
	
	@Action(value = "tanpujihuaxinxiinput")
	public String tanpujihuaxinxiinput() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		
		if (jhId > 0) {
			setTanpujihuaxinxiObj(tpjhxxService.getBeanById(jhId));
		}else{
			Tanpujihuaxinxi xxObj=new Tanpujihuaxinxi();
			xxObj.setJihuatanpucengmian("下面层");
			setTanpujihuaxinxiObj(xxObj);
		}
		return SUCCESS;
	}
	
	@Action(value = "tanpujihuaxinxiadd", results = @Result(name = "list", type = "redirect", location = "tanpujihuaxinxilist?pid=2&record=14&"))
	public String tanpujihuaxinxiadd() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		SecurityContext sc = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication auth = null;
		UserInfo pcp = null;
		String username=null;
		if (null != sc) {
			auth = sc.getAuthentication();
			if (null != auth) {
				pcp = (UserInfo) auth.getPrincipal();
				if (null != pcp) {
					username=pcp.getFullname();
				}
			}
		}	
		
		if (null != tanpujihuaxinxiObj && StringUtil.Null2Blank(tanpujihuaxinxiObj.getJihuamingcheng()).length()>0) {
			
			if(null!=tanpujihuaxinxiObj.getJihuabianjiren()&&StringUtil.Null2Blank(tanpujihuaxinxiObj.getJihuabianjiren()).length()>0){	
			}else{
				tanpujihuaxinxiObj.setJihuabianjiren(username);//计划编辑人
				tanpujihuaxinxiObj.setJihuatianxieriqi(GetDate.getNowTime("yyyy-MM-dd"));//计划编辑日期
			}
			
			tpjhxxService.tanpujihuaxinxiAdd(tanpujihuaxinxiObj);
		}
		return "list";
	}
	
	@Action(value = "tanpujihuaxinxidel", results = @Result(name = "list", type = "redirect", location = "tanpujihuaxinxilist?pid=2&record=14&"))
	public String banhezhandel() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		tpjhxxService.del(jhId);
		return "list";
	}
	
	
	@Action("tanpujihuaxinxitongji")
	public String tanpujihuatongji(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		
		String biaoduanName="";
		
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		
		setMaxPageItems(3);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		
		String startTimeOne = request.getParameter("startTime");
		String endTimeOne = request.getParameter("endTime");
		if (null != request.getParameter("startTime") && request.getParameter("startTime").length()>0) {
			setStartTime(startTimeOne);
		}else{
			setStartTime(GetDate.getNowTime("yyyy-MM-dd"));
		}
		if (null != request.getParameter("endTime") && request.getParameter("endTime").length()>0) {
			setEndTime(endTimeOne);
		}else{
			setEndTime(GetDate.getNowTime("yyyy-MM-dd"));
		}

		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.parseInt(request.getParameter("biaoduan")));
			
			for (Biaoduanxinxi bd : bdlist) {
				if(bd.getId()==new Integer(biaoduan)){
					biaoduanName=bd.getBiaoduanminchen();
				}
			}
			
			setTanpujihuaxinxis(tpjhxxService.getTanpujihuaxinxitongji(
					startTimeOne, endTimeOne, new Integer(biaoduan),
					StringUtil.getQueryFieldNameByRequest(request), 
					StringUtil.getBiaoshiId(request), pageNo, maxPageItems));
			
			if (null != tanpujihuaxinxis && null != tanpujihuaxinxis.getDatas() && tanpujihuaxinxis.getDatas().size() > 0) {
				//request.setAttribute("strXML", queryService.getLiqingCailiaoXml(tanpujihuaxinxis.getDatas(), lqbhzField, lqbhzisShow));
				//request.setAttribute("strXMLWucha", queryService.getLqCailiaoWuchaXml(tanpujihuaxinxis.getDatas(), lqbhzField, lqbhzisShow));
				List<LiqingclDailyView> liqingDailyViewList=new ArrayList<LiqingclDailyView>();
				for(Tanpujihuaxinxi tpjhObj:(List<Tanpujihuaxinxi>)tanpujihuaxinxis.getDatas()){
					LiqingclDailyView lqclDailyViewObj=tpjhxxService.getSumLiqingCLByDate(tpjhObj.getJihuastarttime(), tpjhObj.getJihuaendtime(), tpjhObj.getBiaoduanid());
					
					liqingDailyViewList.add(lqclDailyViewObj);
					
				}
				setLiqingDailyViewList(liqingDailyViewList);
				request.setAttribute("strXML",getdataService.TanpujihuaTongjiXML(biaoduanName,tanpujihuaxinxis.getDatas(),liqingDailyViewList));

				
				List<Tanpujihuaxinxi> yearList=tpjhxxService.getTanpujihuaYearBybianduan(biaoduan,startTime,endTime);
				
				List<Biaoduanxinxi> tempbdList=new ArrayList<Biaoduanxinxi>();
				for(Biaoduanxinxi bdObj:bdlist){
					if(bdObj.getId()==new Integer(biaoduan)){
						tempbdList.add(bdObj);
					}
				}
				request.setAttribute("strtpjhXML", getTPJHXML(tempbdList,yearList,tanpujihuaxinxis.getDatas(),1024,300));
				
				
			} else {
				request.setAttribute("strXML", "");
				request.setAttribute("strtpjhXML", "");
			}
		}else{
			request.setAttribute("strXML", "");
			request.setAttribute("strtpjhXML", "");
		}
		return SUCCESS;
	}
	
	@Action(value="tanputemplist")
	public String tanputemplist(){
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
		List<TopRealTemperaturedataView> toptmplist = sysService.limitTemperaturelist(request,biaoduan, xiangmubu);
		for (TopRealTemperaturedataView topView : toptmplist) {
			shebeilistmap.put(topView.getTmpno(), topView.getBanhezhanminchen());
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
		setXcQuerytmppgs(queryService.viewtmplist(shebeibianhao,
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		if (null != xcQuerytmppgs.getDatas() && xcQuerytmppgs.getDatas().size()>0) {
			request.setAttribute("strXML", queryService.getTmpXml(xcQuerytmppgs.getDatas(),shebeibianhao));
		} else {
			request.setAttribute("strXML", "");
		}
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		if(null==chaxun && null!=xiazai && xiazai.equals("123")){
			List<String> dataList = new ArrayList<String>();
			if (null !=xcQuerytmppgs.getDatas() && xcQuerytmppgs.getDatas().size() > 0) {
				for (Object  obj: xcQuerytmppgs.getDatas()) {
					TemperaturedataView tanputemp=(TemperaturedataView)obj;
					StringBuilder databuilder = new StringBuilder();					
					databuilder.append(tanputemp.getBanhezhanminchen());
					databuilder.append("&^&");
					databuilder.append(tanputemp.getTmpshijian());
					databuilder.append("&^&");
					databuilder.append(tanputemp.getTmpdata());
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
				LqExcelUtil.importtanputempExcel(path+"excel/摊铺温度.xls", path + excelName, 
						startTime+"~"+endTime,dataList,bhzname);
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
	
	@Action("tanpuspeedlist")
	public String tanpuspeedlist(){
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
		List<TopRealSpeeddata1View> toplist = sysService.limitTanpuSpeedlist(request, biaoduan, xiangmubu);
		for (TopRealSpeeddata1View topView : toplist) {
			shebeilistmap.put(topView.getGpsno(), topView.getBanhezhanminchen());
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
		setXcQuerytmppgs(queryService.viewtanpuspeedlist(shebeibianhao,
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		if (null != xcQuerytmppgs.getDatas() && xcQuerytmppgs.getDatas().size()>0) {
			request.setAttribute("strXML", queryService.getTanpuSpeedXml(xcQuerytmppgs.getDatas(),shebeibianhao));
		} else {
			request.setAttribute("strXML", "");
		}
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		if(null==chaxun && null!=xiazai && xiazai.equals("123")){
			List<String> dataList = new ArrayList<String>();
			if (null !=xcQuerytmppgs.getDatas() && xcQuerytmppgs.getDatas().size() > 0) {
				for (Object  obj: xcQuerytmppgs.getDatas()) {
					SpeeddataView tanputemp=(SpeeddataView)obj;
					StringBuilder databuilder = new StringBuilder();					
					databuilder.append(tanputemp.getBanhezhanminchen());
					databuilder.append("&^&");
					databuilder.append(tanputemp.getShijian());
					databuilder.append("&^&");
					databuilder.append(tanputemp.getSudu());
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
				LqExcelUtil.importtanpuspeedExcel(path+"excel/摊铺速度.xls", path + excelName, 
						startTime+"~"+endTime,dataList,bhzname);
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
	
	@Action("tanpulichenglist")
	public String tanpulichenglist(){
		return SUCCESS;
	}	
}
