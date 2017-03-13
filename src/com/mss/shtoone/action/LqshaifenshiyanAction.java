package com.mss.shtoone.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingxixxlilunView;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LqshaifenjieguoView;
import com.mss.shtoone.domain.Lqshaifenshiyan;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.LiqingxixxlilunService;
import com.mss.shtoone.service.LqshaifenshiyanService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/system")
public class LqshaifenshiyanAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private LqshaifenshiyanService shaifenService;
	
	@Autowired
	private LiqingxixxlilunService lqlilunService;
	
	@Autowired
	private GetdataService getDataService;

	private Integer maxPageItems;
	private Integer pageNo;
	private String startTime;
	private String endTime;
	private String shebeibianhao;
	private Integer biaoduan;
	private Integer xiangmubu;
	private Integer id;
	private GenericPageMode shaifenpgs;
	private Lqshaifenshiyan shaifen;
	private Lqshaifenshiyan shaifenSjgl1;
	private Lqshaifenshiyan shaifenSjgl2;
	private Lqshaifenshiyan shaifenSjgl3;
	private Lqshaifenshiyan shaifenSjgl4;
	private Lqshaifenshiyan shaifenSjgl5;
	private Lqshaifenshiyan shaifenSjgl6;
	private Lqshaifenshiyan shaifenSjgl7;
	private Lqshaifenshiyan shaifenSjfl1;
	private Lqshaifenshiyan shaifenSjfl2;
	private Lqshaifenshiyan shaifenSjlq;
	private Lqshaifenshiyan shaifenSjtjj;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<String, String> listmap;
	private Integer xxid;
	private String moren;
	private String ziduanminchen;
	private Liqingziduancfg lqziduancfg;
	private Map<Integer,String> lqlilunmap;
	
	public Lqshaifenshiyan getShaifenSjtjj() {
		return shaifenSjtjj;
	}

	public void setShaifenSjtjj(Lqshaifenshiyan shaifenSjtjj) {
		this.shaifenSjtjj = shaifenSjtjj;
	}

	public Lqshaifenshiyan getShaifenSjgl6() {
		return shaifenSjgl6;
	}

	public void setShaifenSjgl6(Lqshaifenshiyan shaifenSjgl6) {
		this.shaifenSjgl6 = shaifenSjgl6;
	}

	public Lqshaifenshiyan getShaifenSjgl7() {
		return shaifenSjgl7;
	}

	public void setShaifenSjgl7(Lqshaifenshiyan shaifenSjgl7) {
		this.shaifenSjgl7 = shaifenSjgl7;
	}

	public Lqshaifenshiyan getShaifenSjlq() {
		return shaifenSjlq;
	}

	public void setShaifenSjlq(Lqshaifenshiyan shaifenSjlq) {
		this.shaifenSjlq = shaifenSjlq;
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

	public Integer getXiangmubu() {
		return xiangmubu;
	}

	public void setXiangmubu(Integer xiangmubu) {
		this.xiangmubu = xiangmubu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GenericPageMode getShaifenpgs() {
		return shaifenpgs;
	}

	public void setShaifenpgs(GenericPageMode shaifenpgs) {
		this.shaifenpgs = shaifenpgs;
	}

	public Lqshaifenshiyan getShaifen() {
		return shaifen;
	}

	public void setShaifen(Lqshaifenshiyan shaifen) {
		this.shaifen = shaifen;
	}

	public Lqshaifenshiyan getShaifenSjgl1() {
		return shaifenSjgl1;
	}

	public void setShaifenSjgl1(Lqshaifenshiyan shaifenSjgl1) {
		this.shaifenSjgl1 = shaifenSjgl1;
	}

	public Lqshaifenshiyan getShaifenSjgl2() {
		return shaifenSjgl2;
	}

	public void setShaifenSjgl2(Lqshaifenshiyan shaifenSjgl2) {
		this.shaifenSjgl2 = shaifenSjgl2;
	}

	public Lqshaifenshiyan getShaifenSjgl3() {
		return shaifenSjgl3;
	}

	public void setShaifenSjgl3(Lqshaifenshiyan shaifenSjgl3) {
		this.shaifenSjgl3 = shaifenSjgl3;
	}

	public Lqshaifenshiyan getShaifenSjgl4() {
		return shaifenSjgl4;
	}

	public void setShaifenSjgl4(Lqshaifenshiyan shaifenSjgl4) {
		this.shaifenSjgl4 = shaifenSjgl4;
	}

	public Lqshaifenshiyan getShaifenSjgl5() {
		return shaifenSjgl5;
	}

	public void setShaifenSjgl5(Lqshaifenshiyan shaifenSjgl5) {
		this.shaifenSjgl5 = shaifenSjgl5;
	}

	public Lqshaifenshiyan getShaifenSjfl1() {
		return shaifenSjfl1;
	}

	public void setShaifenSjfl1(Lqshaifenshiyan shaifenSjfl1) {
		this.shaifenSjfl1 = shaifenSjfl1;
	}

	public Lqshaifenshiyan getShaifenSjfl2() {
		return shaifenSjfl2;
	}

	public void setShaifenSjfl2(Lqshaifenshiyan shaifenSjfl2) {
		this.shaifenSjfl2 = shaifenSjfl2;
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

	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}

	public Integer getXxid() {
		return xxid;
	}

	public void setXxid(Integer xxid) {
		this.xxid = xxid;
	}

	public String getMoren() {
		return moren;
	}

	public void setMoren(String moren) {
		this.moren = moren;
	}

	public String getZiduanminchen() {
		return ziduanminchen;
	}

	public void setZiduanminchen(String ziduanminchen) {
		this.ziduanminchen = ziduanminchen;
	}

	public Liqingziduancfg getLqziduancfg() {
		return lqziduancfg;
	}

	public void setLqziduancfg(Liqingziduancfg lqziduancfg) {
		this.lqziduancfg = lqziduancfg;
	}

	public Map<Integer, String> getLqlilunmap() {
		return lqlilunmap;
	}

	public void setLqlilunmap(Map<Integer, String> lqlilunmap) {
		this.lqlilunmap = lqlilunmap;
	}

	@Action("lqshaifenshiyanlist")
	public String lqshaifenshiyanlist(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			request.setAttribute("pageNumber", pageNo);
		}else{
			setPageNo(1);
		}
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
			request.setAttribute("pager.offset", maxPageItems);
		}else{
			setMaxPageItems(15);
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if(StringUtil.Null2Blank(request.getParameter("startTime")).length()>0){
			setStartTime(request.getParameter("startTime"));
		}
		if(StringUtil.Null2Blank(request.getParameter("endTime")).length()>0){
			setEndTime(request.getParameter("endTime"));
		}	
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
			setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		if(StringUtil.Null2Blank(request.getParameter("moren")).length()>0){
			setMoren(request.getParameter("moren"));
		}
		if(StringUtil.Null2Blank(request.getParameter("ziduanminchen")).length()>0){
			setZiduanminchen(request.getParameter("ziduanminchen"));
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
		List<TopLiqingView> topLqlist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplq : topLqlist) {
			listmap.put(toplq.getShebeibianhao(), toplq.getBanhezhanminchen());
		}
		setShaifenpgs(shaifenService.limitLqsflist(biaoduan,xiangmubu,shebeibianhao,startTime, endTime,
				StringUtil.getQueryFieldNameByRequest(request),StringUtil.getBiaoshiId(request),
				pageNo, maxPageItems,moren,ziduanminchen));
		return SUCCESS; 
	}
	
	@Action("lqshaifenshiyaninput")
	public String lqshaifenshiyaninput(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("id")).length()>0){
			setId(Integer.parseInt(request.getParameter("id")));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		listmap = new LinkedHashMap<String, String>();
		//沥青
		List<TopLiqingView> topLqlist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		if(StringUtil.Null2Blank(shebeibianhao).length()>0){
			for (TopLiqingView toplq : topLqlist) {
				if(StringUtil.Null2Blank(shebeibianhao).equalsIgnoreCase(toplq.getShebeibianhao())){
					listmap.put(toplq.getShebeibianhao(), toplq.getBanhezhanminchen());
				}
			}
		}else{
			for (TopLiqingView toplq : topLqlist) {
				listmap.put(toplq.getShebeibianhao(), toplq.getBanhezhanminchen());
			}
		}
		
		//理论配合比
		lqlilunmap = new LinkedHashMap<Integer, String>();
		List<LiqingxixxlilunView> lqlilunViewList=lqlilunService.getLqlilunByshebeibianhao(request,shebeibianhao);
		for(LiqingxixxlilunView lqlilunView:lqlilunViewList){
			lqlilunmap.put(lqlilunView.getLlid(), lqlilunView.getLlbuwei());
		}
		//从字段配置中获取字段名称
		setLqziduancfg(sysService.liqingfieldnameBybh(shebeibianhao));
		if(id>0){
			setShaifen(shaifenService.getLqshaifenByid(id));
		}else{
			shaifen=new Lqshaifenshiyan();
		}
		return SUCCESS;
	}
	
	@Action("lqshaifenshiyanedit")
	public String lqshaifenshiyanedit(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("id")).length()>0){
			setId(Integer.parseInt(request.getParameter("id")));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		listmap = new LinkedHashMap<String, String>();
		//沥青
		List<TopLiqingView> topLqlist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		if(StringUtil.Null2Blank(shebeibianhao).length()>0){
			for (TopLiqingView toplq : topLqlist) {
				if(StringUtil.Null2Blank(shebeibianhao).equalsIgnoreCase(toplq.getShebeibianhao())){
					listmap.put(toplq.getShebeibianhao(), toplq.getBanhezhanminchen());
				}
			}
		}else{
			for (TopLiqingView toplq : topLqlist) {
				listmap.put(toplq.getShebeibianhao(), toplq.getBanhezhanminchen());
			}
		}
		//理论配合比
		lqlilunmap = new LinkedHashMap<Integer, String>();
		List<LiqingxixxlilunView> lqlilunViewList=lqlilunService.getLqlilunByshebeibianhao(request,shebeibianhao);
		for(LiqingxixxlilunView lqlilunView:lqlilunViewList){
			lqlilunmap.put(lqlilunView.getLlid(), lqlilunView.getLlbuwei());
		}
		//从字段配置中获取字段名称
		setLqziduancfg(sysService.liqingfieldnameBybh(shebeibianhao));
		//定位到当前是修改那一种材料
		if(StringUtil.Null2Blank(request.getParameter("ziduanminchen")).length()>0){
			setZiduanminchen(request.getParameter("ziduanminchen"));
		}
		if(id>0){
			setShaifen(shaifenService.getLqshaifenByid(id));
			try{
				this.getShaifen().setZiduanminchen((String)lqziduancfg.getClass().getMethod("get"+ziduanminchen.replaceFirst(ziduanminchen.substring(0,1),ziduanminchen.substring(0,1).toUpperCase()),new Class[]{}).invoke(lqziduancfg,new Object[]{}));
			}catch(Exception ex){}
		}
		return SUCCESS;
	}
	
	@Action(value="lqshaifenUpdate",results = {@Result(name = "list", type = "redirect", location = "lqshaifenshiyanlist?pid=6&record=28&", 
	params ={"pageNo","%{pageNo}","maxPageItems","%{maxPageItems}","biaoduan","%{biaoduan}","xiangmubu","%{xiangmubu}",
	         "shebeibianhao","%{shebeibianhao}","moren","%{moren}",
	         "startTime","%{startTime}","endTime","%{endTime}","pager.offset","%{maxPageItems}"})
	})
	public String lqshaifenUpdate(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if(StringUtil.Null2Blank(request.getParameter("startTime")).length()>0){
			setStartTime(request.getParameter("startTime"));
		}
		if(StringUtil.Null2Blank(request.getParameter("endTime")).length()>0){
			setEndTime(request.getParameter("endTime"));
		}	
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
			setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		if(StringUtil.Null2Blank(request.getParameter("moren")).length()>0){
			setMoren(request.getParameter("moren"));
		}
		if(shaifen!=null){
			//给其一个修改时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			shaifen.setUpdatetime(sdf.format(System.currentTimeMillis()));
			String [] ArrStr={"passper1","passper2","passper3","passper4","passper5","passper6","passper7","passper8",
					"passper9","passper10","passper11","passper12","passper13","passper14","passper15"};
			int temp=0;
			//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
			try{
				for(int i=0;i<ArrStr.length;i++){
					if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
						temp=i;
		        	}
		        	if(temp!=0 && i>temp){
		        		sysService.invokeSet(shaifen,ArrStr[i],"100");
		        	}
				}
			}catch(Exception ex){}
			shaifenService.saveOrUpdate(shaifen);
		}
		return "list";
	}
	
	@Action(value = "lqshaifenshiyanAdd",results = {@Result(name = "lqshaifenshiyanlist", type = "redirect", location = "lqshaifenshiyanlist?pid=6&record=28&", 
			params ={"biaoduan","%{biaoduan}","xiangmubu","%{xiangmubu}","shebeibianhao","%{shebeibianhao}"})
	})
	 public String lqshaifenshiyanAdd(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
			setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if(shaifen!=null){
			//新建时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String newTime=sdf.format(System.currentTimeMillis());
			String [] ArrStr={"passper1","passper2","passper3","passper4","passper5","passper6","passper7","passper8",
					"passper9","passper10","passper11","passper12","passper13","passper14","passper15"};
			int temp=0;
			//sjgl1
			if(shaifenSjgl1!=null){
				shaifenSjgl1.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjgl1.setMoren(shaifen.getMoren());
				shaifenSjgl1.setLqllid(shaifen.getLqllid());
				shaifenSjgl1.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjgl1,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjgl1.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjgl1.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjgl1.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjgl1.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjgl1.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjgl1.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjgl1.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjgl1.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjgl1.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjgl1.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjgl1.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjgl1.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjgl1.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjgl1.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjgl1.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjgl1.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjgl1.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjgl1.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjgl1.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjgl1.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjgl1.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjgl1.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjgl1.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjgl1.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjgl1.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjgl1.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjgl1.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjgl1.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjgl1.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjgl1.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjgl1.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjgl1.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjgl1.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjgl1.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjgl1.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjgl1.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjgl1.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjgl1.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjgl1.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjgl1.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjgl1.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjgl1.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjgl1.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjgl1.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjgl1.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjgl1);
				
			}
			//sjgl2
			if(shaifenSjgl2!=null){
				shaifenSjgl2.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjgl2.setMoren(shaifen.getMoren());
				shaifenSjgl2.setLqllid(shaifen.getLqllid());
				shaifenSjgl2.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjgl2,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjgl2.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjgl2.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjgl2.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjgl2.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjgl2.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjgl2.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjgl2.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjgl2.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjgl2.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjgl2.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjgl2.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjgl2.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjgl2.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjgl2.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjgl2.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjgl2.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjgl2.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjgl2.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjgl2.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjgl2.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjgl2.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjgl2.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjgl2.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjgl2.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjgl2.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjgl2.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjgl2.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjgl2.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjgl2.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjgl2.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjgl2.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjgl2.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjgl2.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjgl2.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjgl2.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjgl2.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjgl2.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjgl2.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjgl2.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjgl2.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjgl2.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjgl2.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjgl2.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjgl2.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjgl2.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjgl2);
			}
			//sjgl3
			if(shaifenSjgl3!=null){
				shaifenSjgl3.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjgl3.setMoren(shaifen.getMoren());
				shaifenSjgl3.setLqllid(shaifen.getLqllid());
				shaifenSjgl3.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjgl3,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjgl3.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjgl3.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjgl3.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjgl3.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjgl3.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjgl3.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjgl3.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjgl3.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjgl3.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjgl3.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjgl3.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjgl3.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjgl3.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjgl3.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjgl3.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjgl3.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjgl3.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjgl3.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjgl3.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjgl3.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjgl3.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjgl3.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjgl3.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjgl3.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjgl3.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjgl3.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjgl3.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjgl3.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjgl3.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjgl3.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjgl3.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjgl3.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjgl3.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjgl3.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjgl3.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjgl3.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjgl3.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjgl3.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjgl3.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjgl3.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjgl3.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjgl3.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjgl3.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjgl3.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjgl3.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjgl3);
			}
			//sjgl4
			if(shaifenSjgl4!=null){
				shaifenSjgl4.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjgl4.setMoren(shaifen.getMoren());
				shaifenSjgl4.setLqllid(shaifen.getLqllid());
				shaifenSjgl4.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjgl4,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjgl4.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjgl4.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjgl4.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjgl4.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjgl4.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjgl4.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjgl4.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjgl4.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjgl4.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjgl4.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjgl4.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjgl4.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjgl4.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjgl4.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjgl4.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjgl4.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjgl4.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjgl4.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjgl4.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjgl4.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjgl4.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjgl4.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjgl4.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjgl4.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjgl4.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjgl4.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjgl4.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjgl4.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjgl4.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjgl4.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjgl4.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjgl4.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjgl4.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjgl4.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjgl4.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjgl4.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjgl4.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjgl4.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjgl4.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjgl4.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjgl4.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjgl4.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjgl4.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjgl4.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjgl4.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjgl4);
			}
			//sjgl5
			if(shaifenSjgl5!=null){
				shaifenSjgl5.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjgl5.setMoren(shaifen.getMoren());
				shaifenSjgl5.setLqllid(shaifen.getLqllid());
				shaifenSjgl5.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjgl5,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjgl5.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjgl5.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjgl5.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjgl5.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjgl5.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjgl5.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjgl5.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjgl5.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjgl5.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjgl5.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjgl5.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjgl5.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjgl5.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjgl5.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjgl5.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjgl5.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjgl5.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjgl5.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjgl5.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjgl5.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjgl5.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjgl5.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjgl5.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjgl5.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjgl5.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjgl5.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjgl5.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjgl5.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjgl5.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjgl5.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjgl5.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjgl5.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjgl5.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjgl5.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjgl5.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjgl5.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjgl5.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjgl5.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjgl5.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjgl5.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjgl5.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjgl5.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjgl5.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjgl5.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjgl5.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjgl5);
			}
			//sjgl6
			if(shaifenSjgl6!=null){
				shaifenSjgl6.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjgl6.setMoren(shaifen.getMoren());
				shaifenSjgl6.setLqllid(shaifen.getLqllid());
				shaifenSjgl6.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjgl6,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjgl6.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjgl6.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjgl6.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjgl6.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjgl6.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjgl6.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjgl6.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjgl6.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjgl6.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjgl6.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjgl6.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjgl6.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjgl6.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjgl6.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjgl6.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjgl6.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjgl6.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjgl6.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjgl6.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjgl6.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjgl6.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjgl6.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjgl6.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjgl6.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjgl6.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjgl6.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjgl6.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjgl6.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjgl6.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjgl6.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjgl6.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjgl6.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjgl6.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjgl6.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjgl6.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjgl6.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjgl6.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjgl6.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjgl6.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjgl6.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjgl6.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjgl6.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjgl6.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjgl6.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjgl6.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjgl6);
			}
			
			//sjgl7
			if(shaifenSjgl7!=null){
				shaifenSjgl7.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjgl7.setMoren(shaifen.getMoren());
				shaifenSjgl7.setLqllid(shaifen.getLqllid());
				shaifenSjgl7.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjgl7,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjgl7.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjgl7.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjgl7.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjgl7.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjgl7.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjgl7.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjgl7.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjgl7.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjgl7.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjgl7.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjgl7.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjgl7.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjgl7.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjgl7.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjgl7.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjgl7.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjgl7.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjgl7.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjgl7.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjgl7.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjgl7.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjgl7.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjgl7.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjgl7.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjgl7.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjgl7.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjgl7.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjgl7.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjgl7.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjgl7.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjgl7.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjgl7.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjgl7.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjgl7.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjgl7.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjgl7.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjgl7.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjgl7.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjgl7.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjgl7.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjgl7.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjgl7.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjgl7.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjgl7.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjgl7.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjgl7);
			}
			//sjfl1
			if(shaifenSjfl1!=null){
				shaifenSjfl1.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjfl1.setMoren(shaifen.getMoren());
				shaifenSjfl1.setLqllid(shaifen.getLqllid());
				shaifenSjfl1.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjfl1,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjfl1.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjfl1.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjfl1.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjfl1.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjfl1.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjfl1.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjfl1.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjfl1.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjfl1.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjfl1.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjfl1.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjfl1.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjfl1.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjfl1.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjfl1.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjfl1.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjfl1.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjfl1.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjfl1.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjfl1.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjfl1.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjfl1.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjfl1.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjfl1.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjfl1.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjfl1.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjfl1.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjfl1.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjfl1.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjfl1.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjfl1.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjfl1.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjfl1.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjfl1.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjfl1.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjfl1.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjfl1.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjfl1.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjfl1.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjfl1.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjfl1.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjfl1.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjfl1.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjfl1.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjfl1.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjfl1);
			}
			//sjfl2
			if(shaifenSjfl2!=null){
				shaifenSjfl2.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjfl2.setMoren(shaifen.getMoren());
				shaifenSjfl2.setLqllid(shaifen.getLqllid());
				shaifenSjfl2.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjfl2,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjfl2.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjfl2.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjfl2.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjfl2.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjfl2.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjfl2.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjfl2.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjfl2.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjfl2.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjfl2.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjfl2.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjfl2.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjfl2.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjfl2.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjfl2.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjfl2.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjfl2.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjfl2.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjfl2.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjfl2.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjfl2.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjfl2.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjfl2.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjfl2.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjfl2.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjfl2.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjfl2.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjfl2.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjfl2.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjfl2.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjfl2.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjfl2.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjfl2.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjfl2.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjfl2.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjfl2.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjfl2.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjfl2.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjfl2.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjfl2.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjfl2.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjfl2.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjfl2.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjfl2.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjfl2.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjfl2);
			}
			//sjlq
			if(shaifenSjlq!=null){
				shaifenSjlq.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjlq.setMoren(shaifen.getMoren());
				shaifenSjlq.setLqllid(shaifen.getLqllid());
				shaifenSjlq.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjlq,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjlq.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjlq.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjlq.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjlq.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjlq.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjlq.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjlq.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjlq.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjlq.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjlq.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjlq.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjlq.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjlq.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjlq.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjlq.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjlq.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjlq.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjlq.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjlq.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjlq.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjlq.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjlq.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjlq.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjlq.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjlq.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjlq.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjlq.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjlq.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjlq.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjlq.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjlq.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjlq.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjlq.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjlq.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjlq.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjlq.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjlq.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjlq.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjlq.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjlq.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjlq.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjlq.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjlq.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjlq.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjlq.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjlq);
			}
			
			//sjtjj
			if(shaifenSjtjj!=null){
				shaifenSjtjj.setShebeibianhao(shaifen.getShebeibianhao());
				shaifenSjtjj.setMoren(shaifen.getMoren());
				shaifenSjtjj.setLqllid(shaifen.getLqllid());
				shaifenSjtjj.setNewtime(newTime);
				//实验室在做筛分试验时，筛分通过率=100的筛孔的后面筛孔通过率不填写，这种情况程序为其自动补100
				try{
					for(int i=0;i<ArrStr.length;i++){
						if(StringUtil.StrToZero((String)shaifen.getClass().getMethod("get"+ArrStr[i].replaceFirst(ArrStr[i].substring(0,1),ArrStr[i].substring(0,1).toUpperCase()),new Class[]{}).invoke(shaifen,new Object[]{}))==100){
							temp=i;
			        	}
			        	if(temp!=0 && i>temp){
			        		sysService.invokeSet(shaifenSjtjj,ArrStr[i],"100");
			        	}
					}
				}catch(Exception ex){}
				//最大值
				shaifenSjtjj.setMaxpassper1(shaifen.getMaxpassper1());
				shaifenSjtjj.setMaxpassper2(shaifen.getMaxpassper2());
				shaifenSjtjj.setMaxpassper3(shaifen.getMaxpassper3());
				shaifenSjtjj.setMaxpassper4(shaifen.getMaxpassper4());
				shaifenSjtjj.setMaxpassper5(shaifen.getMaxpassper5());
				shaifenSjtjj.setMaxpassper6(shaifen.getMaxpassper6());
				shaifenSjtjj.setMaxpassper7(shaifen.getMaxpassper7());
				shaifenSjtjj.setMaxpassper8(shaifen.getMaxpassper8());
				shaifenSjtjj.setMaxpassper9(shaifen.getMaxpassper9());
				shaifenSjtjj.setMaxpassper10(shaifen.getMaxpassper10());
				shaifenSjtjj.setMaxpassper11(shaifen.getMaxpassper11());
				shaifenSjtjj.setMaxpassper12(shaifen.getMaxpassper12());
				shaifenSjtjj.setMaxpassper13(shaifen.getMaxpassper13());
				shaifenSjtjj.setMaxpassper14(shaifen.getMaxpassper14());
				shaifenSjtjj.setMaxpassper15(shaifen.getMaxpassper15());
				//最小值
				shaifenSjtjj.setMinpassper1(shaifen.getMinpassper1());
				shaifenSjtjj.setMinpassper2(shaifen.getMinpassper2());
				shaifenSjtjj.setMinpassper3(shaifen.getMinpassper3());
				shaifenSjtjj.setMinpassper4(shaifen.getMinpassper4());
				shaifenSjtjj.setMinpassper5(shaifen.getMinpassper5());
				shaifenSjtjj.setMinpassper6(shaifen.getMinpassper6());
				shaifenSjtjj.setMinpassper7(shaifen.getMinpassper7());
				shaifenSjtjj.setMinpassper8(shaifen.getMinpassper8());
				shaifenSjtjj.setMinpassper9(shaifen.getMinpassper9());
				shaifenSjtjj.setMinpassper10(shaifen.getMinpassper10());
				shaifenSjtjj.setMinpassper11(shaifen.getMinpassper11());
				shaifenSjtjj.setMinpassper12(shaifen.getMinpassper12());
				shaifenSjtjj.setMinpassper13(shaifen.getMinpassper13());
				shaifenSjtjj.setMinpassper14(shaifen.getMinpassper14());
				shaifenSjtjj.setMinpassper15(shaifen.getMinpassper15());
				//目标配合比筛余通过率
				shaifenSjtjj.setStandPassper1(shaifen.getStandPassper1());
				shaifenSjtjj.setStandPassper2(shaifen.getStandPassper2());
				shaifenSjtjj.setStandPassper3(shaifen.getStandPassper3());
				shaifenSjtjj.setStandPassper4(shaifen.getStandPassper4());
				shaifenSjtjj.setStandPassper5(shaifen.getStandPassper5());
				shaifenSjtjj.setStandPassper6(shaifen.getStandPassper6());
				shaifenSjtjj.setStandPassper7(shaifen.getStandPassper7());
				shaifenSjtjj.setStandPassper8(shaifen.getStandPassper8());
				shaifenSjtjj.setStandPassper9(shaifen.getStandPassper9());
				shaifenSjtjj.setStandPassper10(shaifen.getStandPassper10());
				shaifenSjtjj.setStandPassper11(shaifen.getStandPassper11());
				shaifenSjtjj.setStandPassper12(shaifen.getStandPassper12());
				shaifenSjtjj.setStandPassper13(shaifen.getStandPassper13());
				shaifenSjtjj.setStandPassper14(shaifen.getStandPassper14());
				shaifenSjtjj.setStandPassper15(shaifen.getStandPassper15());
				shaifenService.saveOrUpdate(shaifenSjtjj);
			}
		}
		return "lqshaifenshiyanlist"; 
	 }
	
	@Action(value = "lqshaifenDel", results = @Result(name = "lqshaifenshiyanlist", type = "redirect", location = "lqshaifenshiyanlist?pid=6&record=28&"))
	public String lqshaifenDel(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("id")).length()>0){
			setId(Integer.parseInt(request.getParameter("id")));
		}
		if(id>0){
			shaifenService.del(id);
		}
		return "lqshaifenshiyanlist"; 
	 }
	
	@Action(value="lqshaifenshiyanmoren",results = {@Result(name = "shaifenshiyanlist", type = "redirect", location = "lqshaifenshiyanlist?pid=6&record=28&", 
	params ={"pageNo","%{pageNo}","maxPageItems","%{maxPageItems}","biaoduan","%{biaoduan}","xiangmubu","%{xiangmubu}",
	         "shebeibianhao","%{shebeibianhao}","moren","%{moren}",
	         "startTime","%{startTime}","endTime","%{endTime}","pager.offset","%{maxPageItems}"})
	})
	public String lqshaifenshiyanmoren(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
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
		if(StringUtil.Null2Blank(request.getParameter("startTime")).length()>0){
			setStartTime(request.getParameter("startTime"));
		}
		if(StringUtil.Null2Blank(request.getParameter("endTime")).length()>0){
			setEndTime(request.getParameter("endTime"));
		}	
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
			setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		if(StringUtil.Null2Blank(request.getParameter("moren")).length()>0){
			setMoren(request.getParameter("moren"));
		}
		if(StringUtil.Null2Blank(request.getParameter("id")).length()>0){
			setId(Integer.parseInt(request.getParameter("id")));
		}
		if(id>0){
			shaifenService.changeNotmoren(id);
		}
		return "shaifenshiyanlist"; 
	}
	
	@Action("lqshowpic")
	public String Lqshowpic(){
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);
		if(StringUtil.Null2Blank(request.getParameter("xxid")).length()>0){
			setXxid(Integer.parseInt(request.getParameter("xxid")));
		}
		String xmlStr="";
		if(xxid!=null){
			LqshaifenjieguoView sfjieguoView=shaifenService.getLqshaifenjieguoViewBylqId(xxid);
			if(sfjieguoView!=null){
				xmlStr=getDataService.chartLqJipeipic(sfjieguoView);
			}else{
				xmlStr="<div style='font-size:26px;margin-top:123px;margin-left:65px;color:red;position: relative;'>请输入理论配合比,并填写筛分试验！</div>";
			}
		}
		
		PrintWriter out= null;
		try {
			response.setContentType("text/xml;charset=utf-8");  
		    response.setHeader("Cache-Control", "no-cache");  
			out = response.getWriter();
			out.print(xmlStr);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}
}
