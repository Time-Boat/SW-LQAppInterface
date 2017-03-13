package com.mss.shtoone.action;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.Liqingsclsheji;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class LqsclhsshejiAction extends ActionSupport {
	private static final long serialVersionUID = 1L;	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private QueryService queryService;
	
	private Integer biaoduan;
	private Integer xiangmubu;
	private String shebeibianhao;
	private Integer maxPageItems;
	private Integer pageNo;
	private String startTime;
	private String endTime;
	private String peifang;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<String, String> listmap;
	private Map<String,String> peifanmap;
	private GenericPageMode sgjdpagemode;
	private Liqingsclsheji lqsclsheji;
	private int shejiid;
	public Liqingsclsheji getLqsclsheji() {
		return lqsclsheji;
	}

	public void setLqsclsheji(Liqingsclsheji lqsclsheji) {
		this.lqsclsheji = lqsclsheji;
	}

	public Integer getShejiid() {
		return shejiid;
	}

	public void setShejiid(Integer shejiid) {
		this.shejiid = shejiid;
	}

	public Map<String, String> getPeifanmap() {
		return peifanmap;
	}

	public void setPeifanmap(Map<String, String> peifanmap) {
		this.peifanmap = peifanmap;
	}
	public String getPeifang() {
		return peifang;
	}

	public void setPeifang(String peifang) {
		this.peifang = peifang;
	}

	public GenericPageMode getSgjdpagemode() {
		return sgjdpagemode;
	}

	public void setSgjdpagemode(GenericPageMode sgjdpagemode) {
		this.sgjdpagemode = sgjdpagemode;
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


	@Action("shejilqsclhs")
	public String shejilqsclhs(){
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
		if (StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0) {
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
		if(StringUtil.Null2Blank(request.getParameter("peifang")).length()>0){
			this.setPeifang(request.getParameter("peifang"));
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
		List peifanlist=queryService.getlqxinghao();
		for(int m=0;m<peifanlist.size();m++){
			String str=(String)peifanlist.get(m);
			peifanmap.put(str,str);
		}
		sgjdpagemode=queryService.lqshejisclview(shebeibianhao, peifang, startTime, endTime, biaoduan, xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request),StringUtil.getBiaoshiId(request), pageNo, maxPageItems);
		return SUCCESS;
	}
	
	@Action("shejilqsclhsInput")
	 public String shejilqsclhsInput(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
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
		if(StringUtil.Null2Blank(request.getParameter("shejiid")).length()>0){
			this.setShejiid(Integer.parseInt(request.getParameter("shejiid")));
		}
		if(shejiid>0){
			this.setLqsclsheji(sysService.getlqSheji(shejiid));
		}
		return SUCCESS; 
	 }
	
	@Action(value = "shejilqsclhsAdd", results = @Result(name = "shejilqsclhs", type = "redirect", location = "shejilqsclhs?pid=2&record=14&"))
	public String shejilqsclhsAdd(){
		if(lqsclsheji!=null){
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			lqsclsheji.setBaocunshijian(sdf.format(System.currentTimeMillis()));
			sysService.saveLqSheji(lqsclsheji);
		}
		return "shejilqsclhs";
	}
	
	@Action(value = "delshejilqsclhs", results = @Result(name = "shejilqsclhs", type = "redirect", location = "shejilqsclhs?pid=2&record=14&"))
	public String delshejilqsclhs(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("shejiid")).length()>0){
			this.setShejiid(Integer.parseInt(request.getParameter("shejiid")));
		}
		if(shejiid>0){
			sysService.delLqSheji(shejiid);
		}
		return "shejilqsclhs";
	}
}
