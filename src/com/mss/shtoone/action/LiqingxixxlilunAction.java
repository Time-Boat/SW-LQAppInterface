package com.mss.shtoone.action;

import java.util.ArrayList;
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
import com.mss.shtoone.domain.Liqingxixxlilun;
import com.mss.shtoone.domain.LiqingxixxlilunView;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.LiqingxixxlilunService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class LiqingxixxlilunAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3805644965382092049L;
	@Autowired
	private LiqingxixxlilunService lqllService;
	
	@Autowired
	private SystemService sysService;
	
	private List<LiqingxixxlilunView> list = new ArrayList<LiqingxixxlilunView>();
	private Liqingxixxlilun lqllxx;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<String, String> listmap;
	private int llid;
	private String llbuwei;
	private String llshebeibianhao;
	private Integer biaoduan;
	private Integer xiangmubu;
	private String shebeibianhao;
	private Liqingziduancfg lqziduanCfg;
	public Liqingziduancfg getLqziduanCfg() {
		return lqziduanCfg;
	}

	public void setLqziduanCfg(Liqingziduancfg lqziduanCfg) {
		this.lqziduanCfg = lqziduanCfg;
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

	public List<LiqingxixxlilunView> getList() {
		return list;
	}

	public void setList(List<LiqingxixxlilunView> list) {
		this.list = list;
	}
	
	public Liqingxixxlilun getLqllxx() {
		return lqllxx;
	}

	public void setLqllxx(Liqingxixxlilun lqllxx) {
		this.lqllxx = lqllxx;
	}

	public int getLlid() {
		return llid;
	}

	public void setLlid(int llid) {
		this.llid = llid;
	}

	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}

	public String getLlbuwei() {
		return llbuwei;
	}

	public void setLlbuwei(String llbuwei) {
		this.llbuwei = llbuwei;
	}

	public String getLlshebeibianhao() {
		return llshebeibianhao;
	}

	public void setLlshebeibianhao(String llshebeibianhao) {
		this.llshebeibianhao = llshebeibianhao;
	}

	@Action("lqllList")
	public String lqllList(){		
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
			listmap.put(toplqView.getShebeibianhao(), toplqView.getBanhezhanminchen());
		}
		setLqziduanCfg(sysService.liqingfieldnameBybh(shebeibianhao));
		setList(lqllService.getAll(biaoduan, xiangmubu, llshebeibianhao, StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)));
		return SUCCESS; 
	}
	
	@Action("lqllInput")
	public String lqllInput(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("llid")).length()>0){
			setLlid(Integer.parseInt(request.getParameter("llid")));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
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
		if(StringUtil.Null2Blank(shebeibianhao).length()>0){
			for (TopLiqingView toplq : toplist) {
				if(StringUtil.Null2Blank(shebeibianhao).equalsIgnoreCase(toplq.getShebeibianhao())){
					listmap.put(toplq.getShebeibianhao(), toplq.getBanhezhanminchen());
				}
			}
		}else{
			for (TopLiqingView toplq : toplist) {
				listmap.put(toplq.getShebeibianhao(), toplq.getBanhezhanminchen());
			}
		}
		//过滤理论配合比列表
		if(llid>=0 && StringUtil.Null2Blank(shebeibianhao).length()>0){
			setLqziduanCfg(sysService.liqingfieldnameBybh(shebeibianhao));
			setLqllxx(lqllService.getBeanById(llid));
		}
		return SUCCESS; 
	}
	
	@Action(value = "lqllAdd", results = @Result(name = "lqllList", type = "redirect", location = "lqllList?pid=5&record=21&"))
	public String lqllAdd(){	
		if(lqllxx!=null){
			lqllService.saveOrUpdate(lqllxx);
		}
		return "lqllList"; 
	}
	
	@Action(value = "lqllDel", results = @Result(name = "lqllList", type = "redirect", location = "lqllList?pid=5&record=21&"))
	public String lqllDel(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("llid")).length()>0){
			setLlid(Integer.parseInt(request.getParameter("llid")));
		}
		if(llid>0){
			lqllService.del(llid);
		}
		return "lqllList"; 
	}
	
	@Action(value = "lqllMoren", results = @Result(name = "lqllList", type = "redirect", location = "lqllList?pid=5&record=21&"))
	public String lqllMoren(){		
		lqllService.moren(llid, llbuwei, llshebeibianhao);
		return "lqllList";
	}
}
